package com.lxq.product.controller;

import com.lxq.param.ProductCollectParam;
import com.lxq.product.service.ProductService;
import com.lxq.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  11:37
 */
@RestController
@RequestMapping("product")
public class ProductCollectController {

    @Resource
    private ProductService productService;

    @PostMapping("collect/list")
    public R productIds(@RequestBody @Validated ProductCollectParam param, BindingResult result){
        if (result.hasErrors()){
            return R.ok("没有收藏数据");
        }

        return productService.ids(param.getProductIds());
    }
}
