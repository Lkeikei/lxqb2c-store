package com.lxq.user.service;


import com.lxq.param.PageParam;
import com.lxq.param.UserLoginParam;
import com.lxq.param.UserCheckParam;
import com.lxq.pojo.User;
import com.lxq.utils.R;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  10:18
 */
public interface UserService {
    R check(UserCheckParam userCheckParam);

    /**
     * 注册业务
     * @param user 参数已经校验，但是密码是明文！
     * @return
     */
    R register(User user);

    R login(UserLoginParam userLoginParam);

    R listPage(PageParam param);

    R remove(Integer userId);

    R update(User user);

    R save(User user);
}
