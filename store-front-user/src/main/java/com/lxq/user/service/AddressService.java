package com.lxq.user.service;

import com.lxq.pojo.Address;
import com.lxq.utils.R;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  14:41
 */
public interface AddressService {
    R list(Integer userId);

    R save(Address address);

    R remove(Integer id);
}
