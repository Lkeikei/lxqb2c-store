package com.lxq.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxq.category.mapper.CategoryMapper;
import com.lxq.category.service.CategoryService;
import com.lxq.clients.ProductClient;
import com.lxq.param.PageParam;
import com.lxq.param.ProductHotParam;
import com.lxq.pojo.Category;
import com.lxq.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:45
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ProductClient productClient;

    @Override
    public R byName(String categoryName) {
        //1、封查询参数
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("category_name", categoryName);

        //2、查询数据库
        Category category = categoryMapper.selectOne(wrapper);

        //结果封装

        if (category == null){
            return R.fail("类别查询失败");
        }
        return R.ok("类别查询成功", category);
    }

    @Override
    public R hotsCategory(ProductHotParam param) {
        //封装查询参数
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.in("category_name", param.getCategoryName());
        wrapper.select("category_id");

        //查询数数据库
        List<Object> ids = categoryMapper.selectObjs(wrapper);

        return R.ok("类别查询成功", ids);
    }



    @Override
    public R list() {

        List<Category> categories = categoryMapper.selectList(null);

        return R.ok("类别信息查询成功", categories);
    }

    @Override
    public R listPage(PageParam param) {

        IPage<Category> page = new Page<>(param.getCurrentPage(), param.getPageSize());

        page = categoryMapper.selectPage(page, null);

        return R.ok("类别分页数据查询成功！", page.getRecords(), page.getTotal());
    }

    @Override
    public R adminSave(Category category) {

        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("category_name", category.getCategoryName());
        Long aLong = categoryMapper.selectCount(wrapper);
        if (aLong > 0){
            return R.fail("类别已经存在，添加失败");
        }

        int insert = categoryMapper.insert(category);

        return R.ok("类别添加成功！");
    }

    @Override
    public R adminRemove(Integer categoryId) {

        Long aLong = productClient.adminCount(categoryId);
        if (aLong > 0){
            return R.fail("类别删除失败，有：" + aLong + " 件商品孩子能在引用");
        }

        int i = categoryMapper.deleteById(categoryId);
        return R.ok("类别数据删除成功！");
    }

    @Override
    public R adminUpdate(Category category) {


        return null;
    }
}
