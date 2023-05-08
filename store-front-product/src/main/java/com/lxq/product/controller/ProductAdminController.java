package com.lxq.product.controller;

import com.lxq.param.ProductSaveParam;
import com.lxq.pojo.Product;
import com.lxq.product.service.ProductService;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  16:44
 */
@RestController
@RequestMapping("product")
public class ProductAdminController {

    @Resource
    private ProductService productService;

    @PostMapping("admin/count")
    public Long adminCount(@RequestBody Integer categoryId){
        return productService.adminCount(categoryId);
    }

    @PostMapping("admin/save")
    public R adminSave(@RequestBody ProductSaveParam param){
        return productService.adminSave(param);
    }

    @PostMapping("admin/update")
    public R adminUpdate(@RequestBody Product product){
        return productService.adminUpdate(product);
    }

    @PostMapping("admin/remove")
    public R adminRemove(@RequestBody Integer productId){
        return productService.adminRemove(productId);
    }
}
