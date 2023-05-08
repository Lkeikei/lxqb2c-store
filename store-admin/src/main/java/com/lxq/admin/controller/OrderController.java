package com.lxq.admin.controller;

import com.lxq.admin.service.OrderService;
import com.lxq.param.PageParam;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  21:01
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    public R list(PageParam param){
        return orderService.list(param);
    }
}
