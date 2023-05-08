package com.lxq.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:36
 */

@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;
}
