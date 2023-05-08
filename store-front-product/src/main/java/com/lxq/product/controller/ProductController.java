package com.lxq.product.controller;

import com.lxq.param.*;
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
 * @CreateTime: 2023-04-30  16:55
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromoParam param, BindingResult result){


        if (result.hasErrors()){
            return R.fail("数据查询失败！");
        }

        return productService.promo(param.getCategoryName());
    }

    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam param, BindingResult result){
        if (result.hasErrors()){
            return R.fail("数据查询失败");
        }
        return productService.hots(param);
    }


    @PostMapping("category/list")
    public R clist(){
        return productService.clist();
    }

    @PostMapping("bycategory")
    public R byCategory(@RequestBody @Validated ProductIdsParam param, BindingResult result){


        if (result.hasErrors()){
            return R.fail("类别商品查询失败");
        }

        return productService.byCategory(param);
    }

    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParam param, BindingResult result){
        if (result.hasErrors()){
            return R.fail("类别商品查询失败");
        }

        return productService.byCategory(param);
    }

    @PostMapping("detail")
    public R detail(@RequestBody @Validated ProductIdParam param, BindingResult result){
        if (result.hasErrors()){
            return R.fail("商品详情查询失败！");
        }

        return productService.detail(param.getProductID());
    }

    @PostMapping("pictures")
    public R pictures(@RequestBody @Validated ProductIdParam param, BindingResult result){
        if (result.hasErrors()){
            return R.fail("商品详情查询失败！");
        }

        return productService.pictures(param.getProductID());
    }


    @PostMapping("search")
    public R search(@RequestBody ProductSearchParam param){
        return productService.search(param);
    }

}
