package com.lxq.category.service;

import com.lxq.param.PageParam;
import com.lxq.param.ProductHotParam;
import com.lxq.pojo.Category;
import com.lxq.utils.R;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:45
 */
public interface CategoryService {
    R byName(String categoryName);

    R hotsCategory(ProductHotParam param);

    /**
     * 查询类别信息。进行返回
     * @return
     */
    R list();

    R listPage(PageParam param);

    R adminSave(Category category);

    R adminRemove(Integer categoryId);

    R adminUpdate(Category category);
}
