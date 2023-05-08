package com.lxq.admin.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-05  17:13
 */
@Controller
@RequestMapping
public class CaptchaController {

    @RequestMapping("/captcha")
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //默认四个字符长度的验证码
        //默认放入session key = captcha 的位置
        CaptchaUtil.out(request, response);
    }

}