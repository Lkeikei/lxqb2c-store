package com.lxq.admin.service.impl;

import com.lxq.admin.service.OrderService;
import com.lxq.clients.OrderClient;
import com.lxq.param.PageParam;
import com.lxq.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  21:02
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderClient orderClient;

    @Override
    public R list(PageParam param) {

        R r = orderClient.list(param);
        return r;
    }
}
