package com.lxq.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxq.constants.UserConstants;
import com.lxq.param.PageParam;
import com.lxq.param.UserLoginParam;
import com.lxq.param.UserCheckParam;
import com.lxq.pojo.User;
import com.lxq.user.mapper.UserMapper;
import com.lxq.user.service.UserService;
import com.lxq.utils.MD5Util;
import com.lxq.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  10:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public R check(UserCheckParam userCheckParam) {
        //参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_name", userCheckParam.getUserName());

        //数据库查询
        Long total = userMapper.selectCount(queryWrapper);


        //查询结果处理

        if (total == 0){

            return R.ok("账号不存在，可以使用");
        }
        return R.fail("账号已经存在，不可注册！");
    }

    @Override
    public R register(User user) {
        //1、检查账户是否存在
        QueryWrapper<User> wrapper =  new QueryWrapper<>();

        wrapper.eq("user_name", user.getUserName());

        Long total = userMapper.selectCount(wrapper);

        if (total > 0){
            return R.fail("账号已经存在，不可注册！");
        }
        //2、密码解密处理，加盐
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        //3、插入数据库数据

        int rows = userMapper.insert(user);
        //4、返回封装结果

        if (rows == 0){
            return R.fail("注册失败！请稍后再试！");
        }
        return R.ok("注册成功！");
    }

    @Override
    public R login(UserLoginParam userLoginParam) {
        //1、密码处理
        String pass = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);



        //2、数据库查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("user_name", userLoginParam.getUserName());
        wrapper.eq("password", pass);

        User user = userMapper.selectOne(wrapper);

        //3、结果处理
        if (user == null){
            return R.fail("账号或者密码错误！");
        }


        user.setPassword(null);
        return R.ok("登陆成功", user);
    }

    @Override
    public R listPage(PageParam param) {

        IPage<User> page = new Page<>(param.getCurrentPage(), param.getPageSize());

        page = userMapper.selectPage(page, null);

        List<User> records = page.getRecords();

        long total = page.getTotal();
        return R.ok("用户管理查询成功！", records, total);
    }

    @Override
    public R remove(Integer userId) {

        int i = userMapper.deleteById(userId);
        return R.ok("用户数据删除成功");
    }

    @Override
    public R update(User user) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        wrapper.eq("password", user.getPassword());
        Long aLong = userMapper.selectCount(wrapper);
        if (aLong == 0){
            user.setPassword(MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT));
        }

        int i = userMapper.updateById(user);

        return R.ok("用户信息修改成功！");
    }

    @Override
    public R save(User user) {
        //1、检查账户是否存在
        QueryWrapper<User> wrapper =  new QueryWrapper<>();

        wrapper.eq("user_name", user.getUserName());

        Long total = userMapper.selectCount(wrapper);

        if (total > 0){
            return R.fail("账号已经存在，不可添加！");
        }
        //2、密码解密处理，加盐
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        //3、插入数据库数据

        int rows = userMapper.insert(user);
        //4、返回封装结果

        if (rows == 0){
            return R.fail("添加失败！请稍后再试！");
        }
        return R.ok("添加成功！");
    }
}
