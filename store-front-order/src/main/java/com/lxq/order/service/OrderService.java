package com.lxq.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxq.param.OrderParam;
import com.lxq.param.PageParam;
import com.lxq.pojo.Order;
import com.lxq.utils.R;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  17:38
 */
public interface OrderService extends IService<Order> {

    R save(OrderParam param);

    R list(Integer userId);

    R check(Integer productId);

    R adminList(PageParam param);
}
