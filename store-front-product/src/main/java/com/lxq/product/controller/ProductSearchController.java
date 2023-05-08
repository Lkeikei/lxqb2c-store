package com.lxq.product.controller;

import com.lxq.pojo.Product;
import com.lxq.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  22:17
 */
@RestController
@RequestMapping("product")
public class ProductSearchController {
    @Resource
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> allList(){
        return productService.allList();
    }
}
