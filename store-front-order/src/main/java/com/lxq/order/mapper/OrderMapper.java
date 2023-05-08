package com.lxq.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxq.pojo.Order;
import com.lxq.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  17:39
 */
public interface OrderMapper extends BaseMapper<Order> {
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset, @Param("pageSize") int pageSize);
}
