package com.lxq.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxq.clients.*;
import com.lxq.param.*;
import com.lxq.pojo.Category;
import com.lxq.pojo.Picture;
import com.lxq.pojo.Product;
import com.lxq.product.mapper.PictureMapper;
import com.lxq.product.mapper.ProductMapper;
import com.lxq.product.service.ProductService;
import com.lxq.to.OrderToProduct;
import com.lxq.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:59
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private CategoryClient categoryClient;

    @Resource
    private PictureMapper pictureMapper;

    @Resource
    private CartClient cartClient;

    @Resource
    private CollectClient collectClient;

    @Resource
    private OrderClient orderClient;

    @Resource
    private SearchClient searchClient;
    /**
     * 1、根据类别名称，调用feign客户端访问类别服务获取类别的数据
     * 2、成功：继续根据类别id查询商品数据
     * 3、结果封装
     * @param categoryName
     * @return
     */
    @Cacheable(value = "list.product", key = "#categoryName", cacheManager = "cacheManagerDay")
    @Override
    public R promo(String categoryName) {
        R r = categoryClient.byName(categoryName);

        if (r.getCode().equals(R.FAIL_CODE)){
            return r;
        }

        LinkedHashMap<String, Object> map =(LinkedHashMap<String, Object>) r.getData();

        Integer categoryId =(Integer) map.get("category_id");

        //封装查询参数
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);
        wrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        page = productMapper.selectPage(page, wrapper);

        List<Product> records = page.getRecords();
        long total = page.getTotal();


        return R.ok("数据查询成功", records);
    }
    @Cacheable(value = "list.product", key = "#param.categoryName")
    @Override
    public R hots(ProductHotParam param) {
        R r = categoryClient.hotsCategory(param);

        if (r.getCode().equals(R.FAIL_CODE)){
            return r;
        }

        List<Objects> ids = (List<Objects>) r.getData();
        //进行商品数据查询
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.in("category_id", ids);
        wrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1,7);

        page = productMapper.selectPage(page, wrapper);

        List<Product> records = page.getRecords();

        R ok = R.ok("多类别热门商品查询成功！", records);

        return ok;
    }

    @Override
    public R clist() {
        return categoryClient.list();
    }
    @Cacheable(value = "list.product", key = "#param.categoryID+'-'+#param.currentPage+'-'+#param.pageSize")
    @Override
    public R byCategory(ProductIdsParam param) {
        List<Integer> categoryID = param.getCategoryID();

        QueryWrapper<Product> wrapper = new QueryWrapper<>();

        if (!categoryID.isEmpty()){
            wrapper.in("category_id", categoryID);
        }

        IPage<Product> page = new Page<>(param.getCurrentPage(), param.getPageSize());

        page = productMapper.selectPage(page, wrapper);

        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());

        return ok;
    }


    @Cacheable(value = "product", key = "#productID")
    @Override
    public R detail(Integer productID) {
        Product product = productMapper.selectById(productID);
        return R.ok(product);
    }

    @Override
    public List<Product> cartList(List<Integer> productIds) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(wrapper);

        return productList;
    }

    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {
        //将集合转成map，productId orderToProduct
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));

        //获取商品的id集合
        Set<Integer> productIds = map.keySet();

        //查询集合对应的商品信息
        List<Product> productList = productMapper.selectBatchIds(productIds);
        //修改商品信息
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum() - num);;
            product.setProductSales(product.getProductSales() + num); ;
        }
        //批量更新
        this.updateBatchById(productList);

    }

    @Override
    public Long adminCount(Integer categoryId) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id", categoryId);

        Long count = baseMapper.selectCount(wrapper);


        return count;
    }


    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminSave(ProductSaveParam param) {

        Product product = new Product();
        BeanUtils.copyProperties(param, product);

        int rows = productMapper.insert(product);

        String pictures = param.getPictures();

        if (!StringUtils.isEmpty(pictures)){
            String[] urls = pictures.split("\\+");
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                pictureMapper.insert(picture);
            }
        }

        searchClient.saveOrUpdate(product);

        return R.ok("商品数据添加成功");
    }

    @Override
    public R adminUpdate(Product product) {
        productMapper.updateById(product);

        searchClient.saveOrUpdate(product);
        return R.ok("商品数据更新成功！");
    }

    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminRemove(Integer productId) {
        R r = cartClient.check(productId);

        if (r.getCode().equals("004")){
            return r;
        }

        r = orderClient.check(productId);

        if (r.getCode().equals("004")){
            return r;
        }

        productMapper.deleteById(productId);


        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);

        pictureMapper.delete(wrapper);

        collectClient.removeByPid(productId);

        return R.ok("商品删除成功！");
    }

    @Override
    public R pictures(Integer productID) {

        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productID);

        List<Picture> pictures = pictureMapper.selectList(wrapper);



        return R.ok(pictures);
    }

    @Override
    public List<Product> allList() {
        return productMapper.selectList(null);
    }

    @Override
    public R search(ProductSearchParam param) {
        return searchClient.search(param);
    }


    @Cacheable(value = "list.product", key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(wrapper);

        return R.ok("类别信息查询成功", productList);
    }



}
