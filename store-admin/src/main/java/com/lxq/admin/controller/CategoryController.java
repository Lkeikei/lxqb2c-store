package com.lxq.admin.controller;

import com.lxq.admin.service.CategoryService;
import com.lxq.param.PageParam;
import com.lxq.pojo.Category;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  16:29
 */

@RestController
@RequestMapping("category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("list")
    public R pageList(PageParam param){
        return categoryService.pageList(param);
    }

    @PostMapping("save")
    public R pageSave(Category category){
        return categoryService.save(category);
    }

    @PostMapping("remove")
    public R remove(Integer categoryId){
        return categoryService.remove(categoryId);
    }

    @PostMapping("update")
    public R update(Category category){
        return categoryService.update(category);
    }
}
