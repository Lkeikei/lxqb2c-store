package com.lxq.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  17:35
 */
@Data
public class OrderToProduct implements Serializable {

    public static final Long serialVersionUID = 1L;
    private Integer productId;

    private Integer num;

}
