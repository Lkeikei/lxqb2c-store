package com.lxq.admin.service.impl;

import com.lxq.admin.service.ProductService;
import com.lxq.clients.ProductClient;
import com.lxq.clients.SearchClient;
import com.lxq.param.ProductSaveParam;
import com.lxq.param.ProductSearchParam;
import com.lxq.pojo.Product;
import com.lxq.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  17:08
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private SearchClient searchClient;

    @Resource
    private ProductClient productClient;


    @Override
    public R search(ProductSearchParam param) {

        R search = searchClient.search(param);

        return search;
    }

    @Override
    public R save(ProductSaveParam param) {
        R r = productClient.adminCount(param);

        return r;
    }

    @Override
    public R update(Product product) {
        R r = productClient.adminUpdate(product);
        return r;
    }

    @Override
    public R adminRemove(Integer productId) {

        R r = productClient.adminRemove(productId);
        return r;
    }
}
