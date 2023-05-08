package com.lxq.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.cart.mapper.CartMapper;
import com.lxq.cart.service.CartService;
import com.lxq.clients.ProductClient;
import com.lxq.param.CartListParam;
import com.lxq.param.CartSaveParam;
import com.lxq.param.ProductCollectParam;
import com.lxq.param.ProductIdParam;
import com.lxq.pojo.Cart;
import com.lxq.pojo.Product;
import com.lxq.utils.R;
import com.lxq.vo.CartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  15:10
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductClient productClient;


    @Override
    public R save(CartSaveParam cartSaveParam) {
        //查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartSaveParam.getProductId());
        Product product = productClient.productDetail(productIdParam);

        if (product == null){
            return R.fail("商品已经被删除，无法添加到购物车");
        }

        //检查库存
        if (product.getProductNum() == 0){
            R ok = R.ok("没有库存数据！无法购买");
            ok.setCode("003");
            return ok;
        }
        //检查是否添加过
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", cartSaveParam.getUserId());
        wrapper.eq("product_id", cartSaveParam.getProductId());
        Cart cart = cartMapper.selectOne(wrapper);
        if (cart != null){
            //证明购物车存在！
            //原有的数量+1
            cart.setNum(cart.getNum() + 1);
            cartMapper.updateById(cart);
            //返回002提示即可
            R ok = R.ok("购物车存在该商品，数量 + 1");
            ok.setCode("002");
            return ok;
        }

        //添加购物车
        cart = new Cart();
        cart.setNum(1);
        cart.setUserId(cartSaveParam.getUserId());
        cart.setProductId(cartSaveParam.getProductId());
        int rows = cartMapper.insert(cart);
        CartVo cartVo = new CartVo(product, cart);

        //结果封装和返回

        return R.ok("购物车数据添加成功！", cartVo);
    }

    @Override
    public R list(Integer userId) {


        //1、用户id查询 购物车数据
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Cart> carts = cartMapper.selectList(wrapper);

        //2、判断是否存在，不存在。返回一个空集合
        if (carts == null || carts.size() == 0){
            carts = new ArrayList<>();
            return R.ok("购物车为空", carts);
        }

        //3、存在获取商品的id集合，并且调用商品服务查询
        List<Integer> productIds = new ArrayList<>();
        for (Cart cart : carts) {

            productIds.add(cart.getProductId());
        }

        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);

        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //4、VO封装
        List<CartVo> cartVoList = new ArrayList<>();

        for (Cart cart : carts) {
            CartVo cartVo = new CartVo(productMap.get(cart.getProductId()), cart);
            cartVoList.add(cartVo);
        }

        return R.ok("数据库数据查询成功", cartVoList);
    }

    @Override
    public R update(Cart cart) {

        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cart.getProductId());
        Product product = productClient.productDetail(productIdParam);

        //判断库存
        if (cart.getNum() > product.getProductNum()){
            return R.fail("修改失败，库存不足");
        }

        //修改数据库
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());
        Cart newCart = cartMapper.selectOne(queryWrapper);

        newCart.setNum(cart.getNum());

        int i = cartMapper.updateById(newCart);

        return R.ok("修改数据库数据成功");
    }

    @Override
    public R remove(Cart cart) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());

        int delete = cartMapper.delete(queryWrapper);

        return R.ok("删除购物车数据成功");
    }

    @Override
    public void clearIds(List<Integer> cartIds) {
        cartMapper.deleteBatchIds(cartIds);
    }

    @Override
    public R check(Integer productId) {

        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);

        Long aLong = cartMapper.selectCount(wrapper);

        if (aLong > 0){
            return R.fail("有"+ aLong + " 件购物车商品引用！删除失败！");
        }
        return R.ok("购物车无商品引用");
    }


}
