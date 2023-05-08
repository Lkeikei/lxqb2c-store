package com.lxq.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lxq.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-05  16:39
 */

@Data
public class AddressParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;

}
