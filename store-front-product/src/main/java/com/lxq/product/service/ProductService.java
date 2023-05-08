package com.lxq.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxq.param.*;
import com.lxq.pojo.Product;
import com.lxq.to.OrderToProduct;
import com.lxq.utils.R;

import java.util.List;

public interface ProductService extends IService<Product> {

    /**
     * 单类别名称，查询热门商品，至多7条数据
     * @param categoryName
     * @return
     */
    R promo(String categoryName);

    /**
     * 多类别热门商品查询，根据类别名称集合 最多查询7条
     * @param param
     * @return
     */
    R hots(ProductHotParam param);

    R clist();

    R byCategory(ProductIdsParam param);

    /**
     * 根据商品id，查询商品详细信息
     * @param productID
     * @return
     */

    R pictures(Integer productID);

    /**
     * 搜索服务调用，获取全部商品数据！
     * 进行同步
     * @return
     */
    List<Product> allList();

    R search(ProductSearchParam param);

    R ids(List<Integer> productIds);

    R detail(Integer productID);

    List<Product> cartList(List<Integer> productIds);

    void subNumber(List<OrderToProduct> orderToProducts);

    Long adminCount(Integer categoryId);

    R adminSave(ProductSaveParam param);

    R adminUpdate(Product product);

    R adminRemove(Integer productId);
}
