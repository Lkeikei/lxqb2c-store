package com.lxq.admin.service;

import com.lxq.admin.param.AdminUserParam;
import com.lxq.utils.R;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-05  17:18
 */
public interface AdminUserService {
    R login(AdminUserParam adminUserParam);
}
