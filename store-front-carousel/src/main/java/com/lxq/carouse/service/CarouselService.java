package com.lxq.carouse.service;

import com.lxq.utils.R;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:09
 */
public interface CarouselService {
    /**
     * 查询优先级最高的6条轮播图
     * @return
     */
    R list();
}
