package com.lxq.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  10:12
 * TODO: 要使用jsr 303的注解，进行参数校验！
 */
@Data
public class UserCheckParam {
    @NotBlank
    private String userName;
}
