package com.lxq.user.controller;



import com.lxq.param.UserLoginParam;
import com.lxq.param.UserCheckParam;
import com.lxq.pojo.User;
import com.lxq.user.service.UserService;
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
 * @CreateTime: 2023-04-30  10:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("账号为空，不可使用");
        }
        return userService.check(userCheckParam);
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，不可注册！");
        }

        return userService.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，不可登陆！");
        }

        return userService.login(userLoginParam);
    }
}
