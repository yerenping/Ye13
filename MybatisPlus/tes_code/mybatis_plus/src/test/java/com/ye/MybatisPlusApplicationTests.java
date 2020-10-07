package com.ye;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ye.mapper.UserMapper;
import com.ye.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

	// 因为我们继承了BaseMapper，所有的方法都来自父类
	// 我们也可以扩展自己的方法
	@Autowired
	private UserMapper userMapper;



	@Test
	void contextLoads() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		userList.forEach(System.out::println);
	}


	//测试插入
    @Test
	public void testInsert(){
		User user = new User();
		user.setName("b站up主：是叶十三");
		user.setAge(88);
		user.setEmail("2343434@aliyun.com");

        int index = userMapper.insert(user); //自动生成ID
        System.out.println(index);// 受影响的行数
        System.out.println(user); // 结果显示ID会自动回填

    }

    //测试更新
    @Test
    public  void testUpdate(){
        User user = new User();
        user.setId(1310147365178114052L);
        user.setAge(18);
        user.setName("我不是孙红雷");

        // 注意：updateById的参数是一个 对象！
        int i = userMapper.updateById(user);
        System.out.println(i);

    }


    // 测试一下！（成功 案例）





    @Test
    public void testOptimisticLocker(){
	    // 1、查询用户信息
        User user = userMapper.selectById(1L);

        // 2、修改用户信息
        user.setName("地理热巴");
        user.setAge(38);
        // 3、执行更新操作
        userMapper.updateById(user);

    }

    // 测试一下！ （失败 案例） -------多线程情况下
    @Test
    public void testOptimisticLocker2(){
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("地理热巴11111111111111");
        user.setAge(38);

        //模拟另一个线程执行了插队操作

        User user2 = userMapper.selectById(1L);
        user2.setName("地理热巴2222222222222222222");
        user2.setAge(38);
        userMapper.updateById(user2);


        userMapper.updateById(user); //如果没有乐观锁就会覆插队线程的值！

    }

    //测试查询 单个用户
    @Test
    public void testSelect(){
        User user = userMapper.selectById(1l);
        System.out.println(user);

    }

    //查询多个用户
    @Test
    public void testSelect2(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);

    }

    //调价查询 -map
    @Test
    public void testSelcts(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","贺晶晶");
        //自定义要查询的条件
        List<User> users = userMapper.selectByMap(map);

    }


    // 测试分页查询
    @Test
    public void testPage(){

	    // 参数 1 ，当前页
        // 参数2 ，页面大小
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page,null);
        System.out.println("当前页总数"+page.getTotal());
        page.getRecords().forEach(System.out::println);


    }

    //测试删除
    @Test
    public void testDelete(){

        userMapper.deleteById(1310147365178114052L);
    }

    @Test
    //批量删除
    public void testDeleteBatchId(){
	    userMapper.deleteBatchIds(Arrays.asList(1L,2L,3L));
    }

    @Test
    //通过map删除
    public void testDleleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","等不到天黑");
	    userMapper.deleteByMap(map);
    }
}
