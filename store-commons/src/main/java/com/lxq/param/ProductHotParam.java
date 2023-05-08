package com.lxq.param;

import com.lxq.pojo.Category;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  20:44
 */
@Data
public class ProductHotParam {

    @NotEmpty
    List<String> categoryName;
}
