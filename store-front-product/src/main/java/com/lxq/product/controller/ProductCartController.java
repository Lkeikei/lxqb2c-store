package com.lxq.product.controller;

import com.lxq.param.ProductCollectParam;
import com.lxq.param.ProductIdParam;
import com.lxq.pojo.Product;
import com.lxq.product.service.ProductService;
import com.lxq.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  14:50
 */
@RestController
@RequestMapping("product")
public class ProductCartController {

    @Resource
    private ProductService productService;

    @PostMapping("cart/detail")
    public Product cdetail(@RequestBody @Validated ProductIdParam param,
                           BindingResult result){
        if (result.hasErrors()){
            return null;
        }
        R detail = productService.detail(param.getProductID());

        Product product = (Product) detail.getData();
        return product;
    }


    @PostMapping("cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam param, BindingResult result){
        if (result.hasErrors()){
            return new ArrayList<Product>();
        }

        return productService.cartList(param.getProductIds());

    }

}
