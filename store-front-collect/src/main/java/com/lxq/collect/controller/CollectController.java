package com.lxq.collect.controller;

import com.lxq.collect.service.CollectService;
import com.lxq.param.CartListParam;
import com.lxq.pojo.Collect;
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
 * @CreateTime: 2023-05-01  11:23
 */
@RestController
@RequestMapping("collect")
public class CollectController {


    @Resource
    public CollectService collectService;

    @PostMapping("save")
    public R save(@RequestBody Collect collect){

        return collectService.save(collect);

    }

    @PostMapping("list")
    public R list(@RequestBody Collect collect){

        return collectService.list(collect.getUserId());

    }


    @PostMapping("remove")
    public R remove(@RequestBody Collect collect){
        return collectService.remove(collect);
    }


    @PostMapping("remove/product")
    public R removeByPid(@RequestBody Integer productId){
        return collectService.removeByPid(productId);
    }

}
