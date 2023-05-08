package com.lxq.category.controller;

import com.lxq.category.service.CategoryService;
import com.lxq.param.PageParam;
import com.lxq.pojo.Category;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  16:23
 */
@RestController
@RequestMapping("category")
public class CategoryAdminController {

    @Resource
    private CategoryService categoryService;


    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam param){
        return categoryService.listPage(param);
    }

    @PostMapping("admin/save")
    public R adminSave(@RequestBody Category category){
        return categoryService.adminSave(category);
    }

    @PostMapping("admin/remove")
    public R adminRemove(@RequestBody Integer categoryId){
        return categoryService.adminRemove(categoryId);
    }

    @PostMapping("admin/update")
    public R adminUpdate(@RequestBody Category category){
        return categoryService.adminUpdate(category);
    }
}
