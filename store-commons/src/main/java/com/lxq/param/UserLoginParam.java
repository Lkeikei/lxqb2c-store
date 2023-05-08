package com.lxq.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  10:45
 */
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
