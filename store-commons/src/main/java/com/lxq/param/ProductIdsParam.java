package com.lxq.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  21:17
 */


@Data
public class ProductIdsParam  extends PageParam{

    @NotNull
    private List<Integer> categoryID;
}
