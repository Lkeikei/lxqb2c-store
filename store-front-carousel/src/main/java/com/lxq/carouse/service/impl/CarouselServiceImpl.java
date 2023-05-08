package com.lxq.carouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.carouse.mapper.CarouselMapper;
import com.lxq.carouse.service.CarouselService;
import com.lxq.pojo.Carousel;
import com.lxq.utils.R;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:09
 */

@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Cacheable(value = "list.carousel", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public R list() {
        QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("priority");
        List<Carousel> list = carouselMapper.selectList(wrapper);

        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());

        return R.ok(collect);
    }
}
