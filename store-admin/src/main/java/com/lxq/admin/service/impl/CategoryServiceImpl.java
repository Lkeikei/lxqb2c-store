package com.lxq.admin.service.impl;

import com.lxq.admin.service.CategoryService;
import com.lxq.clients.CategoryClient;
import com.lxq.param.PageParam;
import com.lxq.pojo.Category;
import com.lxq.utils.R;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-06  16:30
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryClient categoryClient;

    @Cacheable(value = "list.category", key = "#param.currentPage+'-'+#param.pageSize")
    @Override
    public R pageList(PageParam param) {

        R r = categoryClient.adminPageList(param);
        return r;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R save(Category category) {

        R r = categoryClient.adminSave(category);

        return r;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R remove(Integer categoryId) {

        R r = categoryClient.adminRemove(categoryId);
        return r;
    }

    @Override
    public R update(Category category) {
        R r = categoryClient.adminUpdate(category);
        return r;
    }
}
