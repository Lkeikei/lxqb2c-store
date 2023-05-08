package com.lxq.user.controller;

import com.lxq.param.CartListParam;
import com.lxq.param.PageParam;
import com.lxq.pojo.User;
import com.lxq.user.service.UserService;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  15:39
 */

@RestController
@RequestMapping("user")
public class UserAdminController {

    @Resource
    private UserService userService;


    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam param){

        return userService.listPage(param);
    }

    @PostMapping("admin/remove")
    public R remove(@RequestBody CartListParam cartListParam){

        return userService.remove(cartListParam.getUserId());
    }

    @PostMapping("admin/update")
    public R update(@RequestBody User user){

        return userService.update(user);
    }

    @PostMapping("admin/save")
    public R save(@RequestBody User user){

        return userService.save(user);
    }
}
