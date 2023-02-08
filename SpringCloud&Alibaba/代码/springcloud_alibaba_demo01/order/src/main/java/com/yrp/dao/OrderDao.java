package com.yrp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: YeRenping
 * @Date: 2023/1/8
 * @Description:
 */
@Mapper
public interface OrderDao {
    /**
     * 保存订单
     * @param username
     * @return
     */
    int save(@Param("username") String username);

}
