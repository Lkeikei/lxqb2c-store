package com.lxq.collect.service;

import com.lxq.pojo.Collect;
import com.lxq.utils.R;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  11:25
 */
public interface CollectService {
    R save(Collect collect);

    R list(Integer userId);

    R remove(Collect collect);

    R removeByPid(Integer productId);
}
