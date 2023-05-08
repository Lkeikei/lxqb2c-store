package com.lxq.param;

import com.lxq.pojo.Product;
import lombok.Data;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  17:25
 */
@Data
public class ProductSaveParam extends Product {

    private String pictures;
}
