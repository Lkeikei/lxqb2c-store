package com.lxq.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  15:06
 */
@Data
public class CartSaveParam {
    @JsonProperty("product_id")
    @NotNull
    private Integer productId;


    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
