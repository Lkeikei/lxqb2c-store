package com.lxq.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lxq.pojo.Order;
import lombok.Data;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  18:20
 */
//查询订单需要返回结果
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderVo extends Order {

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;

}
