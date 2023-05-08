package com.lxq.admin.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-05  17:16
 */
@Data
public class AdminUserParam {

    @Length(min = 6)
    private String userAccount; //账号
    @Length(min = 6)
    private String userPassword; //密码
    @NotBlank
    private String verCode;  //验证码

}