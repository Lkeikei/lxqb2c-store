package com.lxq.cart.controller;

import com.lxq.cart.service.CartService;
import com.lxq.param.CartListParam;
import com.lxq.param.CartSaveParam;
import com.lxq.pojo.Cart;
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
 * @CreateTime: 2023-05-01  15:07
 */
@RestController
@RequestMapping("cart")
public class CartController {

    @Resource
    private CartService cartService;

    @PostMapping("save")
    public R save(@RequestBody @Validated CartSaveParam cartSaveParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("核心参数为null，添加失败！");
        }

        return cartService.save(cartSaveParam);
    }


    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam param, BindingResult result){
        if (result.hasErrors()){
            return R.fail("购物车数据查询失败1！");
        }

        return cartService.list(param.getUserId());
    }

    @PostMapping("update")
    public R update(@RequestBody Cart cart){

        return cartService.update(cart);

    }

    @PostMapping("remove")
    public R remove(@RequestBody Cart cart){

        return cartService.remove(cart);

    }


    @PostMapping("remove/check")
    public R removeCheck(@RequestBody Integer productId){
        return cartService.check(productId);
    }



}
