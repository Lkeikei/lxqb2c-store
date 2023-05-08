package com.lxq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  14:34
 */


@Data
@TableName("address")
public class Address  implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank
    private String linkman;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;

    @TableField("user_id")
    private Integer userId;

}
