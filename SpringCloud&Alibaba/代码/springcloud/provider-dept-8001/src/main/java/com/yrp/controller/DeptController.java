package com.yrp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yrp.entity.Dept;
import com.yrp.service.DeptService;

/**
 * @author: YeRenping
 * @Date: 2023/1/11
 * @Description:
 */
@RestController
public class DeptController {
    private final DeptService deptService;

    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){
        System.out.println(dept);
        return deptService.saveDept(dept);
    }


    @GetMapping("/dept/{id}")
    public Dept queryDeptById(@PathVariable("id") Long id){
        return deptService.queryDeptById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryDeptList(){
        System.out.println("查询部门列表");
        return deptService.queryDeptList();
    }

}
