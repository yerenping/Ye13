package com.yrp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yrp.entity.Dept;

/**
 * @author: YeRenping
 * @Date: 2023/1/11
 * @Description:
 */
public interface DeptService {

    /**
     * 新增部门
     * @param dept
     * @return
     */
    public boolean saveDept(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public Dept queryDeptById(@Param("id") Long id);
    /**
     * 根据所有部门
     * @return
     */
    public List<Dept> queryDeptList();

}
