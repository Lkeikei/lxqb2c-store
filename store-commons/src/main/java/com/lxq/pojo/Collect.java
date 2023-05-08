package com.lxq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lxq.utils.R;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  11:18
 */
@Data
@TableName("collect")
public class Collect implements Serializable {
    public static final Long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)
    private Integer id;


    @TableField("user_id")
    @JsonProperty("user_id")
    private Integer userId;


    @TableField("product_id")
    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("collect_time")
    @TableField("collect_time")
    private Long collectTime;


}
