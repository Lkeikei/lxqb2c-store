package com.lxq.admin.service;

import com.lxq.param.ProductSaveParam;
import com.lxq.param.ProductSearchParam;
import com.lxq.pojo.Product;
import com.lxq.utils.R;

public interface ProductService {
    R search(ProductSearchParam param);

    R save(ProductSaveParam param);

    R update(Product product);

    R adminRemove(Integer productId);
}
