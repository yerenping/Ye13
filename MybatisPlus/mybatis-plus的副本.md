# 深入浅出MyBatis-Plus，全网最全笔记



**作者： 是叶十三**



> 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作



## 快速入门

**地址：**https://baomidou.com/guide/quick-start.html

**如何使用第三方组件？步骤如下：**

1、导入对应依赖

2、导入依赖如何配置

3、代码如何编写

4、提高技术扩展能力



> 步骤:

**1、创建`mybatis_plus`数据库**

```sql
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);
-- 真实开发中，version（乐观锁）、deleted（逻辑删除）、gmt_create、gmt_modified

```

**2、插入初始化数据**

```sql

```

**3、创建springboot工程，初始化项目**

**4、在pom.xml中导入相关依赖**

```
		<!--1、数据库驱动-->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>

		<!--2、lombok插件引入-->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.16.18</version>
		</dependency>

		<!--3、引入mybatis_plus-->
		<dependency>
			<groupId>com.baomidou</groupId>
			<artifactId>mybatis-plus-boot-starter</artifactId>
			<version>3.0.5</version>
		</dependency>
```

说明：我们使用mybatis-plus可以节省大量代码，尽量不要同时导入mybati和mybatis-plus的依赖！存在版本差异

**5、创建数据库！这一步与mybatis相同**

`application.xml` 中数据源的配置



```properties
#数据源 mysql 8配置 - 驱动不同、需要增加时区的配置(高版本可兼容低版本)
spring.datasource.name=root
spring.datasource.password=root
spring.datasource.url=jdbc:mysql://47.104.231.144:3306/mybatis_plus?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

# 分析：
Mac电脑需要配置：useSSL=false
MySQL8中时区配置：serverTimezone=UTC
编码格式配置：useUnicode=true&characterEncoding=utf-8
MySQL5.x 驱动：com.mysql.jdbc.Driver
MySQL8 驱动：com.mysql.cj.jdbc.Driver

#数据源 mysql 5配置 - 驱动不同

```



其他方式配置：`application.yml`

```
# 数据源
spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://47.104.231.144:3306/mybatis_plus?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
```





==6、使用传统方式pojo-dao（连接mybatis，配置mapper.xml文件） -service.controller==

**6、使用mybatis-plus之后**

	1. pojo

在com.ye.pojo下创作User.java

```java
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

   	1. mapper

```java
// 在对应的Mapper上面继承接口BaseMapper
@Repository //代表的是持久层的
public interface UserMapper extends BaseMapper<User> {
    //所有的crud操作已经编写完成
    //不需要像以前一样配置一大堆文件了！
}
```

   	1. 开始使用，在测试类中编写代码

```java
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

}
```

==效果如图：==

![image-20200927115546019](https://img-blog.csdnimg.cn/img_convert/9987a3b268831f3d44ad9b798ee006a7.png)



> 思考的问题？

1、SQL谁帮我们写的？mybatis-plus都写好了

2、方法哪里找来的？mybatis-plus都写好了



## 配置日志

我们所有的sql现在是不可见的，我需要它是怎么执行的。

1、在`application.yml`中添加如下配置：

```yml
# 配置日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

2、效果如图：


![](https://img-blog.csdnimg.cn/img_convert/e397333b87f12448a43a8759c0514e33.png)



## CRUD扩展

### 插入操作（Insert）

在`MybatisPlusApplicationTests.java`中修改如下

```java
	//测试插入
    @Test
	public void testInsert(){
		User user = new User();
		user.setName("贺晶晶");
		user.setAge(18);
		user.setEmail("567890qq.com");

        int index = userMapper.insert(user); //自动生成ID
        System.out.println(index);// 受影响的行数
        System.out.println(user); // 结果显示ID会自动回填

    }
```

效果图：

![QQ图片20200927170217](https://img-blog.csdnimg.cn/img_convert/822e0a92bc929f782c15187980c91414.png)

> 数据库ID默认值：全局唯一ID

### 主键生成策略

> 默认==ID_WORKER==全局唯一ID

分布式系统唯一id生成方案汇总：https://www.cnblogs.com/haoxinyue/p/5208136.html

**==雪花算法==**

snowflake是Twitter开源的分布式ID生成算法，结果是一个long型的ID。其核心思想是：使用41bit作为毫秒数，10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），最后还有一个符号位，永远是0。可以保证几乎全球唯一！

> 主键自增

我们需要配置主键自增

1、在实体类的ID字段上增加`@TableId(type = IdType.ID_WORKER)`注解

2、数据库ID字段一定要是自增的

![image-20200927171718211](https://img-blog.csdnimg.cn/img_convert/062754d0781b9f72d7a5a2af505597c9.png)

3、再次测试插入即可

![image-20200927172058233](https://img-blog.csdnimg.cn/img_convert/de23afe7ff884e34214db2c20e5db482.png)



> 源码解释

```java
public enum IdType {
    AUTO(0), //数据库ID自增
    NONE(1), //未设置主键
    INPUT(2),// 手动输入，一旦手动输入后需要自己设置ID
    ID_WORKER(3),//全局默认唯一id
    UUID(4),// 全局唯一id uuid
    ID_WORKER_STR(5);//ID_WORER的字符串表示法

    private int key;

    private IdType(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
```

###  更新操作（Update）

在`MybatisPlusApplicationTests.java`中添加如下代码

```java
    //测试更新
    @Test
    public  void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setAge(99);
        user.setName("叶仁平");

        // 注意：updateById的参数是一个 对象！
        int i = userMapper.updateById(user);
        System.out.println(i);

    }
```

效果如图：

![image-20200927174223043](https://img-blog.csdnimg.cn/img_convert/15452f9368c22b40e5ce922070c403e6.png)

修改age=99后，继续运行，效果如下

![image-20200927174356086](https://img-blog.csdnimg.cn/img_convert/1650d42e60c14e6b483147a0107aaf01.png)

### 查询操作（Select）

在测试类中编写代码如下：

1、单个用户

```java
    //测试测试 -单个用户
    @Test
    public void testSelect(){
        User user = userMapper.selectById(1l);
        System.out.println(user);

    }
```

2、多个用户

```java
//查询多个用户
@Test
public void testSelect2(){
    List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
    users.forEach(System.out::println);

}
```

查询前3个用户，结果如图

![image-20200928133105114](https://img-blog.csdnimg.cn/img_convert/857c566454f8c2e7a147bca7ed0ff6fb.png)

3、条件查询-map

```java
    //条件查询 -map
    @Test
    public void testSelcts(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","贺晶晶");
        //自定义要查询的条件
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
```





**总结发现：**

在mybatis-plus 中，所有的动态SQL都是自动生成的！简直就是偷懒神器啊





### 自动填充

创建时间、修改时间，这些操作都希望是自动完成的， 我们不再希望手动去更新它们！

阿里巴巴开发手册：所有的表：`gmt_carete`、`gmt_modified`、像这两个字段一般所有的表都必须配置上！而且都是自动化的！

> 方式一：数据库级别

1、在表中新增字段`create_time` 、`update_time`

![image-20200927180227228](https://img-blog.csdnimg.cn/img_convert/06f79fb7a1f8a4b2dffe3f7539b6d28b.png)

设置update_time更新时候 ：  触发器->勾选



2、先把实体类同步，再次测试插入方法！

同步实体类User，通过驼峰式

```java
@Data
public class User {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Data createTime;
    private Data updateTime;
}
```

运行update方式，效果如下：

![image-20200927181226801](https://img-blog.csdnimg.cn/img_convert/1069bf5d7df8d5cd398f41236d405aa1.png)



> 方式二：代码级别

1、删除数据库的默认值、更新操作！



2、这个时候实体类User字段上需要增加注解操作

```java
@Data
public class User {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    //织田添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Data createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Data updateTime;
}
```

3、编写处理器`MyDataObjectHandler.java`处理注解

```
@Component//一定不要忘记把处理器组件添加到IOC容器中！（Component）
@Slf4j
public class MyDataObjectHandler implements MetaObjectHandler{
    //插入时候的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入执行.......");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    //更新时候的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新执行.......");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
```

4、测试插入



5、测试更新，观察数据即可



### 乐观锁

==说白了：所有的记录加一个version==

乐观锁实现方式：

- 取出记录时，获取当前version
- 更新时，带上这个version
- 执行更新时， set version = newVersion where version = oldVersion
- 如果version不对，就更新失败



> mabatis-plus乐观锁配置

1、 给表添加一个字段，默认值设为1

﻿![img](https://img-blog.csdnimg.cn/img_convert/8a5389f2e4ffa0beda4a50ad1931c153.png)﻿

2、实体类添加对应的字段

﻿![img](https://img-blog.csdnimg.cn/img_convert/812fad574ccda289b66bad463a45ef3c.png)﻿

3、注册组件

在config包下，创建`MybatisPlusConfig.java`，修改如下

```java
/*扫描mapper文件夹*/
@MapperScan("com.ye.mapper")

@EnableTransactionManagement //事务管理

@Configuration //配置类
public class MybatisPlusConfig {
	//插件配置springboot项目下
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
```

4、测试乐观锁

```java
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
```



### 分页查询

以往

> 1、原始的limit进行分页
>
> 2、pageHelper第三方插件
>
> 3、



**MP内置的分页插件-如何使用？**

1、配置拦截器组件即可

在MybatisPlusConfig.java中添加如下代码

```java
    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
           return new PaginationInterceptor();
    }
```

2、直接使用page对象即可！



### 删除操作

1、基本的删除操作

```java
    //测试删除
    @Test
    public void testDelete(){

        userMapper.deleteById(1L);
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
```

### 逻辑删除

> 物理删除：从数据库中直接移除
>
> 逻辑删除：在数据库并没有真正的删除，而是通过一个变量让它失效delete = 0   =>  delete>=1

例子：管理员可以查看被删除的记录！防止数据丢失，类似于回收站。



测试一下

**步骤如下：**

1、在表中增加字段deleted，默认值为0﻿![img](https://img-blog.csdnimg.cn/img_convert/ec37e1b1a391112cb35a9aeb521b16b0.png)﻿



2、pojo实体类中添加属性+注解`@TableLogic`

```java
    @TableLogic//逻辑删除
    private Integer deleted;
```

 

3、配置，在`Application.yml中添加如下数据`

```yml
#配置logic删除
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: flag  # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

4、测试删除

执行下方测试代码：

```java
//测试删除
@Test
public void testDelete(){

    userMapper.deleteById(1310147365178114052L);
}
```

**结果：**

﻿![img](https://img-blog.csdnimg.cn/img_convert/eabefb30fd2b613862e9b02fe537e399.png)﻿

由下图可知只是将该用户的deleted的值修改为1，而非将该用户删除

![img](https://img-blog.csdnimg.cn/img_convert/77170a37d113a0623d035bc0944b4ce7.png)



## 性能分析插件

由于在在我们的平时开发中，会遇到一些慢sql，测试！durd...操作

mybatis-plus提供性能分析插件，如果超过这个时间就停止运行！

1、导入插件 

在`MybatisPlusConfig.java`中配置如下，在springboot中配置环境为dev或test

```java
    /**
     * sql执行效率插件
     */
    @Bean
    @Profile({"dev","test"}) //设置dev环境和test环境开启
    public  PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor pi = new PerformanceInterceptor();
        pi.setMaxTime(100); //设置sql能够执行最大时间，如果超过了则不执行
        pi.setFormat(true);//是否格式化
        return new PerformanceInterceptor();
    }

```

2、测试使用

只要超过了设置的时间，就会抛出异常！

﻿![img](https://img-blog.csdnimg.cn/img_convert/f49750f6f983ed0d34410212396ef0f2.png)﻿



## 条件构造器(Wrapper)

官网文档：https://baomidou.com/guide/wrapper.html#abstractwrapper

十分重要，在写复杂的sql语句时候会用到

![image-20200929073520238](https://img-blog.csdnimg.cn/img_convert/12d485d56f6a7f3452a6a635b3832def.png)	



==**案例：**==

测试1 ：查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于19

```java
@Test
void contextLoads() {
    // 
    QueryWrapper<User> qw = new QueryWrapper<>();
    qw
            .isNotNull("name")
            .isNotNull("email")
            .ge("age",19);
    userMapper.selectList(qw).forEach(System.out::println);
}
```



﻿![img](https://img-blog.csdnimg.cn/img_convert/c5792e0a186fc34fd5ad7c80406a8e19.png)﻿

测试2：查询name=是叶十三 的用户，单个用户使用`selectOne`

```java
    @Test
    void testSelect() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .eq("name","是叶十三");
        System.out.println(userMapper.selectOne(qw));
    }
```



测试3：查询年龄在20到30岁之间的用户

```java
@Test
void testSelect2() {
    QueryWrapper<User> qw = new QueryWrapper<>();
    qw
            .between("age",20,30);
    userMapper.selectList(qw).forEach(System.out::println);
}
```

﻿![img](https://img-blog.csdnimg.cn/img_convert/2b37fdaf2f49e628e748714fd814c1f8.png)﻿

测试4：模糊查询：名字里面不包含“叶”的

```java
    @Test
    void testSelect3() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .notLike("name","叶");
        userMapper.selectList(qw).forEach(System.out::println);
    }
```

测试5：子查询，查询id小于3的人

```java
    @Test
    void testSelect4() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw
                .inSql("id","select id from user where id<3");
        userMapper.selectList(qw).forEach(System.out::println);
    }
```



﻿![img](https://img-blog.csdnimg.cn/img_convert/b50b50a6c011a42d2a80658b5f47f861.png)﻿

测试6：通过id降序排序

﻿![img](https://img-blog.csdnimg.cn/img_convert/7e621b96400086b10267bc355a07f98b.png)﻿

## ==代码自动生成器==

AutoGenerator 是 MyBatis-Plus 的代码生成器，通过 AutoGenerator 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码，极大的提升了开发效率。

### 实现步骤

**1、导入mybatis-plus到配置到`pom.xml`中**

```xml
		<!--1、数据库驱动-->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>

		<!--2、lombok插件引入-->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.16.18</version>
		</dependency>

		<!--3、引入mybatis_plus-->
		<dependency>
			<groupId>com.baomidou</groupId>
			<artifactId>mybatis-plus-boot-starter</artifactId>
			<version>3.0.5</version>
		</dependency>

		<!-- 4、模板引擎 -->
		<dependency>
			<groupId>org.apache.velocity</groupId>
			<artifactId>velocity-engine-core</artifactId>
			<version>2.0</version>
		</dependency>

		<!--5、swagger配置-->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.8.0</version>
		</dependency>
```

**2、配置yml文件**

```yml
# 1、数据源
spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://47.104.231.144:3306/mybatis_plus?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
  profiles:
    active: dev # 2、设置开发环境


# 3、配置日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # 4、配置逻辑删除
  global-config:
    db-config:
      logic-delete-field: flag  # 全局逻辑删除的实体字段名(since 3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
# 5、配置服务端口
server:
  port:8081 
```

**2、创建配置文件`MybatisPlusConfig.java`**

```java
/*扫描mapper文件夹*/
@MapperScan("com.ye.mapper")

@EnableTransactionManagement //事务管理

@Configuration //配置类
public class MybatisPlusConfig {

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }


    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return new PaginationInterceptor();
    }

    //逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }


    //sql执行效率插件
    @Bean
    @Profile({"dev","test"}) //设置dev环境和test环境开启
    public  PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor pi = new PerformanceInterceptor();
        pi.setMaxTime(100); //设置sql能够执行最大时间，如果超过了则不执行
        pi.setFormat(true);
        return new PerformanceInterceptor();
    }
}
```

3、编写代码自动生成器`YeCode.java`

```java
package com.ye;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

// 代码自动生成器
public class YeCode{
    public static void main(String[] args) {
        // 构建一个代码自动生成器对象
        AutoGenerator mg =  new AutoGenerator();

        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("是叶十三");
        gc.setOpen(false);//是否打开文件
        gc.setFileOverride(false);// 是否覆盖
        gc.setServiceName("%sService");//去Service的I前缀
        gc.setIdType(IdType.ID_WORKER); //id全局唯一
        gc.setDateType(DateType.ONLY_DATE);// 日期类型
        gc.setSwagger2(true);

        mg.setGlobalConfig(gc);//将配置丢到自动生成器里面

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.104.231.144:3306/mybatis_plus_code?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mg.setDataSource(dsc);


        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("com.ye");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("servie");
        pc.setController("controller");
        mg.setPackageInfo(pc);


        //3、策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("t_blog","t_blog_tags","t_comment","t_tag","t_tag_copy");
        sc.setNaming(NamingStrategy.underline_to_camel); //下滑线转驼峰命名
        sc.setColumnNaming(NamingStrategy.underline_to_camel);//列-下滑线转驼峰命名
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        sc.setEntityLombokModel(true); //自动生成lombok
        sc.setLogicDeleteFieldName("deleted"); //逻辑删除配置
        sc.setRestControllerStyle(true);

        // 自动填充配置
        // 自动填充配置
        TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(create_time);
        tableFills.add(update_time);
        sc.setTableFillList(tableFills);


        // 乐观锁配置：
        sc.setVersionFieldName("version");


        //开启restfull的驼峰命名
        sc.setRestControllerStyle(true);

        // url地址变为下划线
        /**
         *
         * localhost:8080/hello_id_23
         */
        sc.setControllerMappingHyphenStyle(true);
        mg.setStrategy(sc);

        //执行
        mg.execute();
    }
}
```

生成代码：

﻿![img](https://img-blog.csdnimg.cn/img_convert/4c181ad4945ff4c33658866e0abb4a60.png)﻿



