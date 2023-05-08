package com.lxq.admin.service.impl;

import com.lxq.admin.service.UserService;
import com.lxq.clients.UserClient;
import com.lxq.param.CartListParam;
import com.lxq.param.PageParam;
import com.lxq.pojo.User;
import com.lxq.utils.R;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  15:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserClient userClient;


    @Cacheable(value = "list.user", key = "#param.currentPage+'-'+#param.pageSize")
    @Override
    public R userList(PageParam param) {
        return userClient.adminListPage(param);
    }

    @CacheEvict(value = "list.user", allEntries = true)
    @Override
    public R userRemove(CartListParam cartListParam) {

        R r = userClient.adminRemove(cartListParam);

        return r;
    }

    @CacheEvict(value = "list.user", allEntries = true)
    @Override
    public R update(User user) {

        R r = userClient.update(user);
        return r;
    }

    @CacheEvict(value = "list.user", allEntries = true)
    @Override
    public R save(User user) {

        R r = userClient.save(user);
        return r;
    }


}
