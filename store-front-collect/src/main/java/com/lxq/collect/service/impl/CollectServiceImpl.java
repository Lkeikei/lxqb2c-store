package com.lxq.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxq.clients.ProductClient;
import com.lxq.collect.mapper.CollectMapper;
import com.lxq.collect.service.CollectService;
import com.lxq.param.ProductCollectParam;
import com.lxq.pojo.Collect;
import com.lxq.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  11:25
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private ProductClient productClient;
    @Override
    public R save(Collect collect) {
        //1、查询是否存在
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", collect.getUserId());
        wrapper.eq("product_id", collect.getProductId());

        Long count = collectMapper.selectCount(wrapper);

        if (count > 0){
            return R.fail("收藏已经添加，无需二次添加");
        }

        //2、不存在进行添加
        collect.setCollectTime(System.currentTimeMillis());;

        int rows = collectMapper.insert(collect);



        return R.ok("收藏添加成功");
    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.select("product_id");

        List<Object> idsObject = collectMapper.selectObjs(wrapper);

        ProductCollectParam productCollectParam = new ProductCollectParam();

        List<Integer> ids = new ArrayList<>();

        for (Object o : idsObject) {
            ids.add((Integer) o);
        }
        productCollectParam.setProductIds(ids);


        return productClient.productIds(productCollectParam);
    }

    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", collect.getUserId());
        wrapper.eq("product_id", collect.getProductId());

        int rows = collectMapper.delete(wrapper);

        return R.ok("收藏移除成功！");
    }

    @Override
    public R removeByPid(Integer productId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);

        int rows = collectMapper.delete(wrapper);

        return R.ok("收藏商品删除成功！");
    }
}

