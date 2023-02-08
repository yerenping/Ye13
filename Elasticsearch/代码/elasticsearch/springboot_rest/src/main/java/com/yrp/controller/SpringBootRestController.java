package com.yrp.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yrp.entity.User;

/**
 * @author: YeRenping
 * @Date: 2023/1/23
 * @Description:
 */
@RestController
@Slf4j
public class SpringBootRestController {

    /**
     * RequstMapping 包含 GetMapping PutMapping PostMapping DeleteMapping
     */
    @GetMapping("find/{id}/{name}")
    public User findById(@PathVariable Long id, @PathVariable String name){
        log.info("id={},name = {}", id, name);
        return new User().setId(id).setName(name);
    }

    /**
     * 添加一个用户
     * @param u
     * @return
     */
    @PutMapping("save")
    public Boolean saveUser(@RequestBody  User u){
        log.info("user={}",u);
        return true;
    }


    /**
     * 更新一个用户
     * @param u
     * @return
     */
    @PostMapping("update")
    public Boolean updateUser(@RequestBody User u){
        log.info("user={}",u);
        return true;
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public Boolean deleteUser(@PathVariable Long id){
        log.info("id={}",id);
        return true;
    }
}
