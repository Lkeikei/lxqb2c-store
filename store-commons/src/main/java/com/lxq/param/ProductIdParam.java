package com.lxq.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  21:43
 */
@Data
public class ProductIdParam {
    @NotNull
    private Integer productID;
}
