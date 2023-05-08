package com.lxq.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lxq.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  17:34
 */
//订单参数
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderParam implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;

}