package com.lxq.order.controller;

import com.lxq.order.service.OrderService;
import com.lxq.param.CartListParam;
import com.lxq.param.OrderParam;
import com.lxq.param.PageParam;
import com.lxq.pojo.Order;
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
 * @CreateTime: 2023-05-01  17:37
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;


    @PostMapping("save")
    public R save(@RequestBody OrderParam param){
        return orderService.save(param);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam param, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，查询失败");
        }
        return orderService.list(param.getUserId());
    }


    @PostMapping("remove/check")
    public R check(@RequestBody Integer productId){
        return orderService.check(productId);
    }

    @PostMapping("remove/list")
    public R adminList(@RequestBody PageParam param){
        return orderService.adminList(param);
    }

}
