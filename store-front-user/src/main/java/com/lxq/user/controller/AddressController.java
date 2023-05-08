package com.lxq.user.controller;

import com.lxq.param.AddressListParam;
import com.lxq.param.AddressParam;
import com.lxq.param.AddressRemoveParam;
import com.lxq.pojo.Address;
import com.lxq.user.service.AddressService;
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
 * @CreateTime: 2023-04-30  14:38
 */
@RestController
@RequestMapping("/user/address")
public class AddressController {


    @Resource
    private AddressService addressService;

    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，查询失败");
        }

        return  addressService.list(addressListParam.getUserId());
    }

    @PostMapping("save")
    public R save(@RequestBody @Validated AddressParam addressParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，保存失败");
        }

        Address address = addressParam.getAdd();
        address.setUserId(addressParam.getUserId());

        return  addressService.save(address);
    }

    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveParam address, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，删除失败");
        }

        return  addressService.remove(address.getId());
    }


}
