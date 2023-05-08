package com.lxq.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  15:27
 */
@Data
public class CartListParam {


    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
