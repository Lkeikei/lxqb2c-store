package com.lxq.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  14:37
 */


@Data
public class AddressListParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}