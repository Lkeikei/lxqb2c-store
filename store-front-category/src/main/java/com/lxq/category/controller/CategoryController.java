package com.lxq.category.controller;

import com.lxq.category.service.CategoryService;
import com.lxq.param.ProductHotParam;
import com.lxq.utils.R;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:41
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName){
        if(StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为null，无法查询类别数据！");
        }

        return categoryService.byName(categoryName);
    }

    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam param, BindingResult result){
        if (result.hasErrors()){
            return R.fail("类别集合查询失败！");
        }

        return categoryService.hotsCategory(param);
    }

    @GetMapping("list")
    public R List(){
        return categoryService.list();
    }
}
