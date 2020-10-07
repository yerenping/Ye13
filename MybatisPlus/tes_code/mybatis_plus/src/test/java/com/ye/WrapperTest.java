package com.ye;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ye.mapper.UserMapper;
import com.ye.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于19
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",19);
        userMapper.selectList(qw).forEach(System.out::println);
    }



    @Test
    void testSelect() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .eq("name","是叶十三");
        System.out.println(userMapper.selectOne(qw));
    }

    @Test
    void testSelect2() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .between("age",20,30);
        userMapper.selectList(qw).forEach(System.out::println);
    }

    @Test
    void testSelect3() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .notLike("name","叶");
        userMapper.selectList(qw).forEach(System.out::println);
    }

    @Test
    void testSelect4() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .inSql("id","select id from user where id<3");
        userMapper.selectList(qw).forEach(System.out::println);
    }


    @Test
    void testSortById() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        // 通过id进行排序
        qw.orderByDesc("id");
        List<User> list = userMapper.selectList(qw);
        list.forEach(System.out::println);

    }


}
