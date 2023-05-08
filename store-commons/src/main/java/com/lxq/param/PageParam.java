package com.lxq.param;

import lombok.Data;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  21:39
 */
@Data
public class PageParam {

    private int currentPage = 1; //默认值1
    private int pageSize = 15; //默认值 15
}
