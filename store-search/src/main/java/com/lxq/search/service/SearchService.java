package com.lxq.search.service;

import com.lxq.param.ProductSearchParam;
import com.lxq.pojo.Product;
import com.lxq.utils.R;

import java.io.IOException;

public interface SearchService {
    /**
     * 根据关键字和分页进行数据库数据查询
     * @param param
     * @return
     */
    R search(ProductSearchParam param);

    R save(Product product) throws IOException;

    R remove(Integer productId) throws IOException;
}
