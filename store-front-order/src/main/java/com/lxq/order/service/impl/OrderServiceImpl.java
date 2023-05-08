package com.lxq.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxq.clients.ProductClient;
import com.lxq.order.mapper.OrderMapper;
import com.lxq.order.service.OrderService;
import com.lxq.param.OrderParam;
import com.lxq.param.PageParam;
import com.lxq.param.ProductCollectParam;
import com.lxq.pojo.Order;
import com.lxq.pojo.Product;
import com.lxq.to.OrderToProduct;
import com.lxq.utils.R;
import com.lxq.vo.AdminOrderVo;
import com.lxq.vo.CartVo;
import com.lxq.vo.OrderVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  17:39
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private ProductClient productClient;
    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    private OrderMapper orderMapper;

    /**
     * 1、将购物车数据转成订单数据
     * 2、进行订单数据的批量插入
     * 3、商品库存修改消息
     * 4、发送购物车库存修改消息
     * @param param
     * @return
     */
    @Transactional
    @Override
    public R save(OrderParam param) {

        List<Integer> cartIds = new ArrayList<>();

        List<OrderToProduct> orderToProducts = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();

        Integer userId = param.getUserId();
        long orderId = System.currentTimeMillis();
        for (CartVo cartVo : param.getProducts()) {

            cartIds.add(cartVo.getId());
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setNum(cartVo.getNum());
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProducts.add(orderToProduct);

            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(orderId);
            order.setUserId(userId);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order);
        }

        //发送购物车消息
        rabbitTemplate.convertAndSend("topic.ex", "clear.cart", cartIds);


        //发送商品服务消息
        rabbitTemplate.convertAndSend("topic.ex", " sub.number", orderToProducts);

        return R.ok("订单保存成功！");
    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Order> list = list(wrapper);
        
        //分组
        Map<Long, List<Order>> orderMap = list.stream().collect(Collectors.groupingBy(Order::getOrderId));

        //查询商品数据
        List<Integer> productIds = list.stream().map(Order::getProductId).collect(Collectors.toList());

        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);

        List<Product> productList = productClient.cartList(productCollectParam);

        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //结果封装
        List<List<OrderVo>> result = new ArrayList<>();
        for (List<Order> orders : orderMap.values()) {
            List<OrderVo> orderVos = new ArrayList<>();
            for (Order order : orders) {
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order, orderVo);
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVos.add(orderVo);
            }
            result.add(orderVos);
        }


        return R.ok("订单数据获取成功！", result);
    }

    @Override
    public R check(Integer productId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);

        Long aLong = baseMapper.selectCount(wrapper);

        if (aLong > 0){
            return R.fail("订单中"+ aLong + "件商品引用，不可删除！");
        }


        return R.ok("无订单引用，可以删除");
    }

    @Override
    public R adminList(PageParam param) {

        int offset = (param.getCurrentPage() - 1) * param.getPageSize();

        int pageSize = param.getPageSize();

        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrder(offset, pageSize);
        return R.ok("订单数据查询成功！", adminOrderVoList);
    }
}
