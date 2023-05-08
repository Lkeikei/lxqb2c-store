package com.lxq.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  11:36
 */
@Data
public class ProductCollectParam {

    @NotEmpty
    private List<Integer> productIds;
}
