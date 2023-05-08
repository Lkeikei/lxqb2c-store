package com.lxq.admin.service;

import com.lxq.param.CartListParam;
import com.lxq.param.PageParam;
import com.lxq.pojo.User;
import com.lxq.utils.R;

public interface UserService {
    R userList(PageParam param);


    R userRemove(CartListParam cartListParam);

    R update(User user);

    R save(User user);
}
