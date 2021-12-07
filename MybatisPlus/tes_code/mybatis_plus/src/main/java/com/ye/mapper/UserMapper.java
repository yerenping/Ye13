package com.ye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ye.pojo.User;
import org.springframework.stereotype.Repository;

// 在对应的Mapper上面继承接口BaseMapper
@Repository //代表的是持久层的
public interface UserMapper extends BaseMapper<User> {
    //所有的crud操作已经编写完成
    //不需要像以前一样配置一大堆文件了！
}