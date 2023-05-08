package com.lxq.admin.service;

import com.lxq.param.PageParam;
import com.lxq.pojo.Category;
import com.lxq.utils.R;

public interface CategoryService {
    R pageList(PageParam param);

    R save(Category category);

    R remove(Integer categoryId);

    R update(Category category);
}
