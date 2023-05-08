package com.lxq.admin.controller;

import com.lxq.admin.service.UserService;
import com.lxq.param.CartListParam;
import com.lxq.param.PageParam;
import com.lxq.pojo.User;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  15:47
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("list")
    public R userList(PageParam param){
        return userService.userList(param);
    }

    @PostMapping("remove")
    public R remove(CartListParam cartListParam){

        return userService.userRemove(cartListParam);
    }

    @PostMapping("update")
    public R update(User user){
        R r = userService.update(user);

        return r;
    }

    @PostMapping("save")
    public R adminUpdate(User user){
        R r = userService.save(user);

        return r;
    }
}
