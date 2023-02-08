package com.yrp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yrp.entity.Dept;
import com.yrp.mapper.DeptMapper;
import com.yrp.service.DeptService;

/**
 * @author: YeRenping
 * @Date: 2023/1/11
 * @Description:
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    @Autowired
    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public boolean saveDept(Dept dept) {
        return deptMapper.saveDept(dept);
    }

    @Override
    public Dept queryDeptById(Long id) {
        return deptMapper.queryDeptById(id);
    }

    @Override
    public List<Dept> queryDeptList() {
        return deptMapper.queryDeptList();
    }
}
