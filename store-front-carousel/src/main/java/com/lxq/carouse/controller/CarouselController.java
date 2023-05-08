package com.lxq.carouse.controller;

import com.lxq.carouse.service.CarouselService;
import com.lxq.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:06
 */
@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Resource
    private CarouselService carouselService;


    @PostMapping("list")
    public R list(){
        return carouselService.list();
    }
}
