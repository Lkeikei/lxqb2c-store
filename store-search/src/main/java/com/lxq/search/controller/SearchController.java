package com.lxq.search.controller;

import com.lxq.param.ProductSearchParam;
import com.lxq.pojo.Product;
import com.lxq.search.service.SearchService;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  22:58
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Resource
    private SearchService searchService;

    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParam param){
        return searchService.search(param);
    }

    @PostMapping("save")
    public R SaveProduct(@RequestBody Product product) throws IOException {
        return searchService.save(product);
    }

    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {
        return searchService.remove(productId);
    }
}
