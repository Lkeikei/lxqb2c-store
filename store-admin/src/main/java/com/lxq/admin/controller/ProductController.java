package com.lxq.admin.controller;

import com.lxq.admin.service.ProductService;
import com.lxq.admin.utils.AliyunOSSUtils;
import com.lxq.param.ProductSaveParam;
import com.lxq.param.ProductSearchParam;
import com.lxq.pojo.Product;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  17:05
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private AliyunOSSUtils aliyunOSSUtils;

    @GetMapping("list")
    public R adminList(ProductSearchParam param){
        return productService.search(param);
    }

    @PostMapping("upload")
    public R adminUpload(@RequestParam("img") MultipartFile img) throws Exception {
        String filename = img.getOriginalFilename();
        filename = UUID.randomUUID().toString().replaceAll("-","")
                +filename;
        String contentType = img.getContentType();

        byte[] content = img.getBytes();

        int hours = 1000;

        String url = aliyunOSSUtils.uploadImage(filename, content, contentType, hours);
        System.out.println("url = " + url);
        return R.ok("图片上传成功!",url);
    }

    @PostMapping("save")
    public R adminSave(ProductSaveParam param){
        return productService.save(param);
    }

    @PostMapping("update")
    public R adminUpdate(Product product){
        return productService.update(product);
    }

    @PostMapping("remove")
    public R adminRemove(Integer productId){
        return productService.adminRemove(productId);
    }


}

