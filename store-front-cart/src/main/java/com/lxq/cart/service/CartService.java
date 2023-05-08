package com.lxq.cart.service;

import com.lxq.param.CartListParam;
import com.lxq.param.CartSaveParam;
import com.lxq.pojo.Cart;
import com.lxq.utils.R;

import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  15:10
 */
public interface CartService {
    R save(CartSaveParam cartSaveParam);

    R list(Integer userId);

    R update(Cart cart);

    R remove(Cart cart);

    void clearIds(List<Integer> cartIds);

    R check(Integer productId);
}
