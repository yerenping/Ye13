# 1. Spring



![image-20220701185208013](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220701185208.png)







## 1.1 IOC



> 控制反转：对象的==创建的控制权==发生改变，又原来的==new== 变为 ==外部提供==



- 原始方式

![image-20220801111024859](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220801111025.png)



![image-20220701185503471](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220701185503.png)



### **1.1.2 IOC中的三个问题**

> 1、什么是IOC?
>
> IOC容器管理的对象
>
> 2、什么是IOC容器？
>
> spring提供的一个容器，称为IOC，用来充当**外部**
>
> 3、什么是Bean？
>
> ios容器中，被创建或管理的对象成为bean，例如service 、dao，都是由IOC容器进行管理





![image-20220701185926550](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220701185926.png)





## 1.2 DI

https://www.bilibili.com/video/BV1Fi4y1S7ix?p=5

06:58

> 什么是DI？

![image-20220701190413598](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220701190413.png)



## **1.3 IOC与DI的目的**

> 降低耦合度：
>
> 1、使用ioc容器管理bean（例如管理serice 、 dao）
>
> 2、将bean进行绑定（DI），即将service与dao这两个bean进行绑定



![image-20220701190609513](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220701190609.png)





## 1.4 IOC入门



![image-20220701200839238](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220701200839.png)



### **1.4.1  快速构建spring项目**

**1.导入spring的坐标spring-context，对应版本是5.2.10.RELEASE**

在pom.xml中添加如下代码

```xml
 <!--导入spring依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
```

**2.配置bean**

> id属性标示给bean起名字
> class属性表示给bean定义类型

```xml
		 <!-- 1、配置bean对象（dao）-->
    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl"/>
		<!-- 1、配置bean对象（service）-->
    <bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
    </bean>
```

**3、DI-绑定有依赖关系的bean**

在本例中，serice依赖dao，因此，只需要在service的bean中添加一个property标签，将dao引入，更新完后的代码如下

> property标签表示配置当前bean的属性
>
> name属性表示配置哪一个具体的属性
>
> ref属性表示参照哪一个bean

```xml
<bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl"/>

    <bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
        <!--7.配置server与dao的关系-->
        <!--property标签表示配置当前bean的属性
        name属性表示配置哪一个具体的属性
        ref属性表示参照哪一个bean-->
        <property name="bookDao" ref="bookDao"/>
    </bean>
```



**4、初始化IOC容器，通过容器获取bean对象**

```java
public class App2 {
    public static void main(String[] args) {
        //1.获取IoC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.获取bean（根据bean配置id获取）
        //获取dao层
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        // 获取service层
        BookService bookService = (BookService) ctx.getBean("bookService");
        bookService.save();
    }
}
```



注：本案例中service层代码与dao层代码

- dao

```java
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("book dao save ...");
    }
}
```

- service

```java
public class BookServiceImpl implements BookService {
    //  删除业务层中使用new的方式创建的dao对象
    // 作废 ~~private BookDao bookDao = new BookDaoImpl();~~
    private BookDao bookDao;

    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
    // 提供对应的set方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
```



## 1.5 Bean

### **1.5.1 bean的基础配置**

- id属性
- class属性
- property中的name与ref

```xml
<bean id = "bookService" name="serice1 bookservice " class = "com.yrp.service.impl.BookServiceImpl">
  <property name="bookDao" ref="bookDao"/>
</bean>
```



### **1.5.2 bean别名作用**

设置别名，通过name属性，可设置多个

```java
<bean id = "bookService" name="serice1 bookservice " class = "com.yrp.service.impl.BookServiceImpl">
        <property name="bookDao" ref="bookDao"/>
</bean>
```

目的：通过不同的名字可以引用相同的bean对象，一般不建议这样操作。



### **1.5.3 bean作用范围**

bean默认是采用单例模式`scope="singleton">`

如果需要使用多利模式，可修改为`scope="prototype"`

```xml
 <bean id="bookDao" class = "com.yrp.dao.impl.BookDaoImpl" scope="singleton">
```



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220702173432.png" alt="image-20220702173432595" style="zoom:50%;" /> 



## 1.6 Bean的实例化

### **1.6.1 方式一：使用无参构造方法**

- dao代码撰写

```java
public class BookDaoImpl implements BookDao {
	
  	// 利用反射技术
    public/private BookDaoImpl() {
        System.out.println("book dao constructor is running ....");
    }

    public void save() {
        System.out.println("book dao save ...");
    }
}
```

- applicationContext.xml配置

```xml
    <!--方式一：构造方法实例化bean-->
    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl"/>
```

- 编写启动类AppForInstanceBook.java

```java
public class AppForInstanceBook {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
    }
}
```

- 运行测试结果如下

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220702180119.png" alt="image-20220702180119139" style="zoom:50%;" /> 

### **1.6.2 方式二：使用静态工厂构造生成bean**

1、编写dao

- OrderDao接口

```java
public interface OrderDao {
    public void save();
}
```

- OrderDaoImpl实现类

```java
public class OrderDaoImpl implements OrderDao {
    public void save() {
        System.out.println("order dao save ...");
    }
}
```

2、静态工厂创建对象`OrderDaoFactory.java`

```java
public class OrderDaoFactory {
  
  	// 静态方法
    public static OrderDao getOrderDao(){
        System.out.println("factory setup....");
        return new OrderDaoImpl();
    }
}
```

3、`applicationContext.xml`配置

> class = 工厂类名
>
> factory-method  = 工厂类的静态方法（用于造bean对象）

```xml
 <!--方式二：使用静态工厂实例化bean-->
<bean id="orderDao" class="com.itheima.factory.OrderDaoFactory" factory-method="getOrderDao"/>
```





### **1.6.3 方式三：实例工厂初始化bean**

1、编写dao

- UserDao

```java
public interface UserDao {
    public void save();
}
```

- OrderDaoImpl实现类

```java
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("user dao save ...");
    }
}
```

2、工厂创建对象`UserDaoFactory.java`

```java
public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
```

3、`applicationContext.xml`配置

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220702181903.png" alt="image-20220702181902974" style="zoom: 50%;" /> 



### **1.6.4 方式四：实例化工厂FactoryBean （方式三的改进版本）**

2、工厂创建对象`UserDaoFactoryBean.java`

```java
//FactoryBean创建对象
public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    //代替原始实例工厂中创建对象的方法
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }
    // 返回bean的类型
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    // 设置是否使用单例模式，默认是单例模式
    @Override
    public boolean isSingleton() {
//        return FactoryBean.super.isSingleton();
        return false; // 非单例模式，true 是单例
    }
}
```



3、`applicationContext.xml`配置

```xml
<bean id="userDao" class="com.itheima.factory.UserDaoFactoryBean"/>
```



## **1.7 Bean的生命周期**

> 方式一：自定义init()与desory()，并通过applicationContext对其进行配置

1.7.1 创建init()与desory()

```java
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("book dao save ...");
    }

    //表示bean初始化对应的操作
    public void init() {
        System.out.println("init...");
    }

    //表示bean销毁前对应的操作
    public void destory() {
        System.out.println("destory...");
    }

}
```

1.7.2 配置applicationContext.xml

```xml
<bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl" init-method="init" destroy-method="destory"/>

```

1.7.3 运行测试

```java
public static void main( String[] args ) {
  ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
  BookDao bookDao = (BookDao) ctx.getBean("bookDao");
  bookDao.save();
  //注册关闭钩子函数，在虚拟机退出之前回调此函数，关闭容器
  //ctx.registerShutdownHook();
  //关闭容器 --暴力方式
  ctx.close();
}
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220702190126.png" alt="image-20220702190126659" style="zoom:50%;" /> 

> 方式二：使用spring自身提供的方式，则无需配置applicationContext.xml

实现方式：

第一步：将BookServiceImpl实现InitializingBean,DisposableBean接口，会生成afterPropertiesSet()、afterPropertiesSet() 

```java
public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        System.out.println("set .....");
        this.bookDao = bookDao;
    }

    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }

    public void destroy() throws Exception {
        System.out.println("service destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }
}
```



第二步：在IOC容器中配置BookServiceImpl这个bean对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--init-method：设置bean初始化生命周期回调函数-->
    <!--destroy-method：设置bean销毁生命周期回调函数，仅适用于单例对象-->
    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl" init-method="init" destroy-method="destory"/>
  
		<-- ******在这里配置bookService*******-->
    <bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
        <property name="bookDao" ref="bookDao"/>
    </bean>
</beans>
```



## 1.8 DI依赖注入两种方式

![image-20220703182350826](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703182402.png)



![image-20220703114325052](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703114325.png)



### 1.8.1 setter注入



1、简单类型

![image-20220703114643580](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703114643.png)

2、引用类型

之前的代码都是用的引用类型



### 1.8.2构造器注入

1、引用类型

![image-20220703122813780](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703122834.png)



2、简单类型

```xml
    标准书写
    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl">
        根据构造方法参数名称注入
        <constructor-arg name="connectionNum" value="10"/>
        <constructor-arg name="databaseName" value="mysql"/>
    </bean>
    <bean id="userDao" class="com.itheima.dao.impl.UserDaoImpl"/>
    <bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
        <constructor-arg name="userDao" ref="userDao"/>
        <constructor-arg name="bookDao" ref="bookDao"/>
    </bean>
```



```xml
    解决形参名称的问题，与形参名不耦合
    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl">
        根据构造方法参数类型注入
        <constructor-arg type="int" value="10"/>
        <constructor-arg type="java.lang.String" value="mysql"/>
    </bean>
    <bean id="userDao" class="com.itheima.dao.impl.UserDaoImpl"/>

    <bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
        <constructor-arg name="userDao" ref="userDao"/>
        <constructor-arg name="bookDao" ref="bookDao"/>
    </bean>
```



```xml
<!--解决参数类型重复问题，使用位置解决参数匹配-->
<bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl">
    <!--根据构造方法参数位置注入-->
    <constructor-arg index="0" value="mysql"/>
    <constructor-arg index="1" value="100"/>
</bean>
<bean id="userDao" class="com.itheima.dao.impl.UserDaoImpl"/>

<bean id="bookService" class="com.itheima.service.impl.BookServiceImpl">
    <constructor-arg name="userDao" ref="userDao"/>
    <constructor-arg name="bookDao" ref="bookDao"/>
</bean>
```





### **1.8.3 DI自动装配**

![image-20220703123519407](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703123519.png)

配置方式

>  autowire="byType”
>
>  autowire="byName"

```xml
<bean class="com.itheima.dao.impl.BookDaoImpl"/>
<!--autowire属性：开启自动装配，通常使用按类型装配-->
<bean id="bookService" class="com.itheima.service.impl.BookServiceImpl" autowire="byType"/>
```



## **1.9 集合注入**

- applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl">
        <!--数组注入-->
        <property name="array">
            <array>
                <value>100</value>
                <value>200</value>
                <value>300</value>
            </array>
        </property>
        <!--list集合注入-->
        <property name="list">
            <list>
                <value>itcast</value>
                <value>itheima</value>
                <value>boxuegu</value>
                <value>chuanzhihui</value>
            </list>
        </property>
        <!--set集合注入-->
        <property name="set">
            <set>
                <value>itcast</value>
                <value>itheima</value>
                <value>boxuegu</value>
                <value>boxuegu</value>
            </set>
        </property>
        <!--map集合注入-->
        <property name="map">
            <map>
                <entry key="country" value="china"/>
                <entry key="province" value="henan"/>
                <entry key="city" value="kaifeng"/>
            </map>
        </property>
        <!--Properties注入-->
        <property name="properties">
            <props>
                <prop key="country">china</prop>
                <prop key="province">henan</prop>
                <prop key="city">kaifeng</prop>
            </props>
        </property>
    </bean>
</beans>

```



- BookDaoImpl

```java
public class BookDaoImpl implements BookDao {

    private int[] array;

    private List<String> list;

    private Set<String> set;

    private Map<String,String> map;

    private Properties properties;



    public void setArray(int[] array) {
        this.array = array;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    public void save() {
        System.out.println("book dao save ...");

        System.out.println("遍历数组:" + Arrays.toString(array));

        System.out.println("遍历List" + list);

        System.out.println("遍历Set" + set);

        System.out.println("遍历Map" + map);

        System.out.println("遍历Properties" + properties);
    }
}
```



## **1.10 数据源对象管理**

第一步：导入依赖（MySQL驱动、c3p0 / druid），在pom.xml添加如下代码

```xml
			<!--druid数据源注入-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.16</version>
        </dependency>
        <!--c3p0驱动-->
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>

        <!--MySQL驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
```

第二步，在applicationContext.xml 中配置druid数据源相关参数

```xml
   <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
       <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
       <property name="url" value="jdbc:mysql://47.97.22.187/:3306/yrp"/>
       <property name="username" value="root"/>
       <property name="password" value="a.123456"/>
    </bean>

```

在applicationContext.xml 中配置c3p0数据源相关参数

```java
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="com.mysql.jdbc.Driver"/>
        <property name="jdbcUrl" value="jdbc:mysql://47.97.22.187/:3306/yrp"/>
        <property name="user" value="root"/>
        <property name="password" value="a.123456"/>
    </bean>
```

大功告成，over！



## **1.11 加载properities文件**

![image-20220703180702371](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703180702.png)

前提条件，创建jdbc.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://47.97.22.187:3306/yrp
jdbc.username=root
jdbc.password=a.123456
```



第一步，开启context命名空间

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context
            http://www.springframework.org/schema/context/spring-context.xsd
            ">
```

第二步，使用context空间加载properties文件

```xml
<!--    1.开启context命名空间-->
<!--    2.使用context空间加载properties文件-->
<!--    <context:property-placeholder location="jdbc.properties" system-properties-mode="NEVER"/>-->
<!--    <context:property-placeholder location="jdbc.properties,jdbc2.properties" system-properties-mode="NEVER"/>-->
<!--    classpath:*.properties  ：   设置加载当前工程类路径中的所有properties文件-->
<!--    system-properties-mode属性：是否加载系统属性-->
    <!--    <context:property-placeholder location="*.properties" system-properties-mode="NEVER"/>-->

    <!--classpath*:*.properties  ：  设置加载当前工程类路径和当前工程所依赖的所有jar包中的所有properties文件-->
<!--    <context:property-placeholder location="classpath*:*.properties" system-properties-mode="NEVER"/>-->

<!--    3.使用属性占位符${}读取properties文件中的属性-->
<!--    说明：idea自动识别${}加载的属性值，需要手工点击才可以查阅原始书写格式-->
<!--    <bean class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <bean id="bookDao" class="com.itheima.dao.impl.BookDaoImpl">
        <property name="name" value="${username}"/>
    </bean>-->



<!--system-properties-mode="NEVER"不加载系统环境属性，防止冲突-->
    <context:property-placeholder location="jdbc.properties" system-properties-mode="NEVER"/>
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
```

运行测试，发现可行，over！



## 1.12容器

![image-20220703182006796](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703182033.png)

![image-20220703182211250](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703182211.png)



1、获取bean

![image-20220703181425270](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703181425.png)



2、创建容器的方式

![image-20220703181513370](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703181513.png)



## 1.13 注解

1. **使用@Componet定义bean**

![image-20220703205218524](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703205218.png)



**2. Spring提供了@Componet注解的三个衍生注解**

![image-20220703205325999](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703205326.png)



## 1.14 纯注解开发（配置类）

1. **撰写配置类**

```java
@Configuration// 将当前类设置为配置类
@ComponentScan("com.itheima") // 用于设定扫描的路径，这个注解只能添加一次，多个数据需要用数组格式
public class SpringConfig {
		
}
```

2. **重新编写启动测试程序**

- 加载配置类的方式：` ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);`

```java
public class AppForAnnotation {
    public static void main(String[] args) {
        //AnnotationConfigApplicationContext加载Spring配置类初始化Spring容器
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        System.out.println(bookDao);
        //按类型获取bean
        BookService bookService = ctx.getBean(BookService.class);
        System.out.println(bookService);
    }
}
```



## 1.15 纯注解开发-Bean

#### bean的管理与生命周期

- 设置单例或非单例Scope注解

`@Scope("singleton")`

`@Scope("prototype")`

- 声明周期

@PostConstruct ：在生成bean时候，调用**构造方法之后**的方法

@PreDestroy		在**bean的销毁前调**用的方法

```java
@Repository
//@Scope设置bean的作用范围
@Scope("singleton")
public class BookDaoImpl implements BookDao {

    public void save() {
        System.out.println("book dao save ...");
    }
    //@PostConstruct设置bean的初始化方法
    @PostConstruct
    public void init() {
        System.out.println("init ...");
    }
    //@PreDestroy设置bean的销毁方法
    @PreDestroy
    public void destroy() {
        System.out.println("destroy ...");
    }
}
```



## 1.16 纯注解开发中的DI依赖注入

### 1、引用类型的注入

只需要一个autowired注解即可，这个注解是按照类型(byType)注入的

使用@Qualifier自动装配bean时**按bean名称**装配 ：@Qualifier("bookDao")

```java
@Service
public class BookServiceImpl implements BookService {
    //@Autowired：注入引用类型，自动装配模式，默认按类型装配
    @Autowired
    //@Qualifier：自动装配bean时按bean名称装配
    @Qualifier("bookDao")
    private BookDao bookDao;

    public void save() {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
```

### 2、简单类型的注入

` @Value("${name的值}")`

```java
@Repository("bookDao")
public class BookDaoImpl implements BookDao {
    //@Value：注入简单类型（无需提供set方法）
    @Value("${name}")
  	@Value("${是叶十三}")
    private String name;

    public void save() {
        System.out.println("book dao save ..." + name);
    }
}
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703214906.png" alt="image-20220703214906039" style="zoom:50%;" /> 



##  **1.17 管理第三方bean**

1 简单类型注入

![image-20220703221341966](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703221342.png)



2 引用类型注入

![image-20220703221423180](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703221423.png)





## 1.19 XML与注解配置对比

![image-20220703221627528](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703221627.png)



## 1.20 spring整合mybatis与jdbc

- ### spring整合jdbc

**1、导入数据库连接池jdbc**

第0步：在`pom.xml`导入相关依赖

```xml
 <dependencies>
    <!--spring相关依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
    <!--数据库连接池-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.16</version>
    </dependency>
    <!--spring整合mybatis-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.0</version>
    </dependency>
    <!--mybatis自身依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.6</version>
    </dependency>
    <!--MySQL驱动-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.47</version>
    </dependency>
    <!--spring操作数据库-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
  </dependencies>
```



第1步：创建jdbc.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://47.97.22.187:3306/yrp
jdbc.username=root
jdbc.password=a.123456
```



第2步：编写配置类`SpringConfig`

```java
@Configuration
@ComponentScan("com.itheima")
//@PropertySource：加载类路径jdbc.properties文件
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class,MybatisConfig.class})
public class SpringConfig {
  
}
```

第3步：编写JdbcConfig

```java
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }
}
```



- ### spring整合MyBatis

#### **（1）传统xml方式整个MyBatis**



第一步：创建j`dbc.properties`文件

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://47.97.22.187:3306/yrp
jdbc.username=root
jdbc.password=a.123456

```

第二步：编写配置文件`SqlMapConfig.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="jdbc.properties"></properties>
    <!--需要引用的实体类所在的包 -->
    <typeAliases>
        <package name="com.itheima.pojo"/>
    </typeAliases>
    <environments default="mysql">
        <environment id="mysql">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"></property>
                <property name="url" value="${jdbc.url}"></property>
                <property name="username" value="${jdbc.username}"></property>
                <property name="password" value="${jdbc.password}"></property>
            </dataSource>
        </environment>
    </environments>
     <!--dao层所在的位置 -->
    <mappers>
        <package name="com.itheima.dao"></package>
    </mappers>
</configuration>
```

  

![image-20220703222458993](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220703222607.png)



第三步：编写测试类`App`

  ```java
public class App {
    public static void main(String[] args) throws IOException {
        // 1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 2. 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml.bak");
        // 3. 创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        // 4. 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 5. 执行SqlSession对象执行查询，获取结果User
        AccountDao accountDao = sqlSession.getMapper(AccountDao.class);
        Account ac = accountDao.findById(2);
        System.out.println(ac);
        // 6. 释放资源
        sqlSession.close();
    }
}
  ```

  

####   **（2）注解方式整个MyBatis**

![image-20220704105417486](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704105417.png)





![image-20220704105439603](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704105439.png)



第0步：在`pom.xml`导入相关依赖

```xml
 <dependencies>
    <!--spring相关依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
    <!--数据库连接池-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.16</version>
    </dependency>
    <!--spring整合mybatis-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.0</version>
    </dependency>
    <!--mybatis自身依赖-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.6</version>
    </dependency>
    <!--MySQL驱动-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.47</version>
    </dependency>
    <!--spring操作数据库-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
  </dependencies>
```



第一步：创建j`dbc.properties`文件

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://47.97.22.187:3306/yrp
jdbc.username=root
jdbc.password=a.123456

```

第二步：编写配置类`MybatisConfig`

```java
public class MybatisConfig {
    //定义bean，SqlSessionFactoryBean，用于产生SqlSessionFactory对象
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("com.itheima.pojo");
        ssfb.setDataSource(dataSource);
        return ssfb;
    }
    //定义bean，返回MapperScannerConfigurer对象
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.itheima.dao");
        return msc;
    }
}
```

  编写主配置类`SpringConfig`

```java
@Configuration
@ComponentScan("com.itheima")
//@PropertySource：加载类路径jdbc.properties文件
@PropertySource("classpath:jdbc.properties")
@Import(MybatisConfig.class)
public class SpringConfig {

}
```

第三步：编写测试类`App2`

  ```java
public class App2 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = ctx.getBean(AccountService.class);
        Account ac = accountService.findById(1);
        System.out.println(ac);
    }
}
  ```





## 1.21 Spring整合junit

1、导入依赖（①junit相关依赖②spring整合junit的依赖），在pom.xml中添加如下代码

```xml
    <!--junit-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <!--spring整合junit-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
```



2、创建测试类`AccountServiceTest`



3、设置spring整合junit的专用的类运行器

```java
//设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {}
```

4、设置Spring环境对应的配置类

```java
//设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring环境对应的配置类
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest {
   
}
```



5、定义测试的接口（这里以业务层接口`AccountService`为例），通过Autowired注解将其自动装配，编写测试方法进行测试即可。

```java
//设置类运行器
@RunWith(SpringJUnit4ClassRunner.class)
//设置Spring环境对应的配置类
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest {
    //支持自动装配注入bean
    @Autowired
    private AccountService accountService;

    @Test
    public void testFindById(){
        System.out.println(accountService.findById(1));
    }
    @Test
    public void testFindAll(){
        System.out.println(accountService.findAll());
    }
}
```



## 1.22 AOP

> 面向切面编程，是一种编程范式
>
> 作用：在不惊动原始设计的基础上为其进行新增功能
>
> Spring提倡的理念：无侵入式



![image-20220704200301283](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704200301.png)



### （1）AOP相关概念

![image-20220704210820270](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704210820.png)

### （2）AOP案例

0、思路分享

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704214617.png" style="zoom:50%;" /> 



1、在`pom.xml`中导入依赖

通过下图可知，`spring-context`中包含了aop所需要的包，印务无需单独导入aop的jar

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704215135.png" alt="image-20220704215135050" style="zoom:50%;" /> 



```xml
  <dependencies>
    <!--spring-context中包含了-aop-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
    <!--AOP 需要的aspect依赖-->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.9.4</version>
    </dependency>
  </dependencies>
```



2、制作接入点方法

```java
@Repository
public class BookDaoImpl implements BookDao {

    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("book dao save ...");
    }

    public void update(){
        System.out.println("book dao update ...");
    }
}
```

3、制作共性公共能（通知类+通知）

4、定义切入点

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704220647.png" alt="image-20220704220647302" style="zoom:33%;" /> 

5、绑定共性功能（通知）与切入点，也就是制作切面

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704220705.png" alt="image-20220704220705099" style="zoom:50%;" />

6、将通知类交给ioc容器管理

> //通知类必须配置成Spring管理的bean
> @Component

7、并且将该类作为AOP的配置类

> //设置当前类为切面类类
> @Aspect

![image-20220704220343039](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220704220343.png)

```java
//通知类必须配置成Spring管理的bean
@Component
//设置当前类为切面类类
@Aspect
public class MyAdvice {
    //设置切入点，要求配置在方法上方
    @Pointcut("execution(void com.itheima.dao.BookDao.update())")
    private void pt(){

    }

    //设置在切入点pt()的前面运行当前操作（前置通知）
    // @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
}
```



8、告诉spring，我是用注解开发的AOP

`@EnableAspectJAutoProxy`

```JAVA
@Configuration
@ComponentScan("com.itheima")
//开启注解开发AOP功能
@EnableAspectJAutoProxy
public class SpringConfig {
  
}
```





### （3）AOP切入点表达式

![image-20220705094708201](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705094708.png)



![image-20220705094849416](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705094849.png)



### （4）AOP切入点表达式中的通配符





<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705095158.png" alt="image-20220705095158129" style="zoom:50%;" /> 



（5）书写表达式

![image-20220705105255952](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705105310.png)



### (5)AOP的通知

![image-20220705110948471](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705110948.png)



![image-20220705111307793](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705111308.png)



1、制作共性公共能（通知类+通知）

```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(void com.itheima.dao.BookDao.update())")
    private void pt(){}
    @Pointcut("execution(int com.itheima.dao.BookDao.select())")
    private void pt2(){}

    //@Before：前置通知，在原始方法运行之前执行
//    @Before("pt()")
    public void before() {
        System.out.println("before advice ...");
    }

    //@After：后置通知，在原始方法运行之后执行
//    @After("pt2()")
    public void after() {
        System.out.println("after advice ...");
    }

    //@Around：环绕通知，在原始方法运行的前后执行
//    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        //表示对原始操作的调用
        Object ret = pjp.proceed();
        System.out.println("around after advice ...");
        return ret;
    }
  
//    @Around("pt2()")
    public Object aroundSelect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        //表示对原始操作的调用
        Integer ret = (Integer) pjp.proceed();
        System.out.println("around after advice ...");
        return ret;
    }

    //@AfterReturning：返回后通知，在原始方法执行完毕后运行，且原始方法执行过程中未出现异常现象
//    @AfterReturning("pt2()")
    public void afterReturning() {
        System.out.println("afterReturning advice ...");
    }

    //@AfterThrowing：抛出异常后通知，在原始方法执行过程中出现异常后运行
    @AfterThrowing("pt2()")
    public void afterThrowing() {
        System.out.println("afterThrowing advice ...");
    }
}

```

2、将通知类添加到config中

```java
@Configuration
@ComponentScan("com.itheima")
@EnableAspectJAutoProxy
public class SpringConfig {
}
```





## 1.23 AOP的应用（业务层接口万次执行时间统计）

1、制作共性公共能（通知类+通知）

```java
@Component
@Aspect
public class ProjectAdvice {
    //匹配业务层的所有方法
    @Pointcut("execution(* com.itheima.service.*Service.*(..))")
    private void servicePt(){}

    //设置环绕通知，在原始操作的运行前后记录执行时间
    @Around("ProjectAdvice.servicePt()")
    public void runSpeed(ProceedingJoinPoint pjp) throws Throwable {
        //获取执行的签名对象
        Signature signature = pjp.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
           pjp.proceed();
        }
        long end = System.currentTimeMillis();
        System.out.println("万次执行："+ className+"."+methodName+"---->" +(end-start) + "ms");
    }
}
```



2、将通知类添加到config中

```java
@Configuration
@ComponentScan("com.itheima")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class,MybatisConfig.class})
@EnableAspectJAutoProxy
public class SpringConfig {
}
```



## 1.24 AOP通知获取数据

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705152651.png" alt="image-20220705152651223" style="zoom:50%;" /> 

1、获取切入点的参数

<img src="/Users/ouyangyansong/Library/Application%20Support/typora-user-images/image-20220705153619930.png" alt="image-20220705153619930" style="zoom: 33%;" /> 



2、获取切入点方法的返回值

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705153727.png" alt="image-20220705153726946" style="zoom: 33%;" /> 

 



3、获取切入点方法的异常

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705153756.png" alt="image-20220705153756060" style="zoom:33%;" /> 





## 1.25、AOP百度网盘提取码的密码处理

1、创建通知、通知类

```java
@Component
@Aspect
public class DataAdvice {
    @Pointcut("execution(boolean com.itheima.service.*Service.*(*,*))")
    private void servicePt(){}

    @Around("DataAdvice.servicePt()")
    public Object trimStr(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            //判断参数是不是字符串
            if(args[i].getClass().equals(String.class)){
                args[i] = args[i].toString().trim();
            }
        }
        Object ret = pjp.proceed(args);
        return ret;
    }
}
```

2、将通知类添加到SpringConfig中类

```java
@Configuration
@ComponentScan("com.itheima")
@EnableAspectJAutoProxy
public class SpringConfig {
}
```



## 1.26 Spring事务

### 1、Spring事务介绍

![image-20220705155602893](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705155603.png)



### 2、Spring事务-转账案例

![image-20220705155745112](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705155745.png)

转账操作中开启Spring事务步骤如下

**第一步：配置==事务管理器==，mybatis使用的是jdbc事务，在`JdbcConfig.java`中添加如下代码**

```java
    //配置事务管理器，mybatis使用的是jdbc事务
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
      	// 注入DataSource
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
```

**第二步：在业务层给指定接口添加`@Transactional`**

```java
public interface AccountService {
    /**
     * 转账操作
     * @param out 传出方
     * @param in 转入方
     * @param money 金额
     */
    //配置当前接口方法具有事务
    @Transactional
    public void transfer(String out,String in ,Double money) ;
}
```



**第三步：在SpringConfig中开启事务管理功能`@EnableTransactionManagement`**

```java
@Configuration
@ComponentScan("com.itheima")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class,MybatisConfig.class})
//开启注解式事务驱动
@EnableTransactionManagement
public class SpringConfig {
}

```



## 1.27 Spirng事务角色

![image-20220705163531023](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705163531.png)

![image-20220705163408472](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705163408.png)



## 1.28 Spring事务相关配置

![image-20220705163633479](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705163633.png)

**==特殊：事务的传播行为:propagation==** 

```java
public interface LogService {
    //propagation设置事务属性：传播行为设置为当前操作需要新事务
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void log(String out, String in, Double money);
}
```

![image-20220705165447955](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705165448.png)



# 2、SpringMVC



> - SpringMVC技术是与Sevlet技术功能相同，均属于web层开发技术
>
> - 表现层框架：替代原生servelt
>
> - SpringMVC是一种基于Java实现MVC模型的轻量级Web框架



![image-20220705193231584](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705193231.png)



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220705193050.png" alt="image-20220705193050549" style="zoom:50%;" /> 



## 2.1 入门案例

1、在pom.xml中导入Spring MVC相关依赖

```java
  <!--servlet-->
  <dependencies>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <!--springMVC相关依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.2.10.RELEASE</version>
    </dependency>
  </dependencies>
```

2、由于该项目是webapp 项目，因此需要配置tomcat，因此可以通过在maven加入tomcat7插件，从而简化tomcat配置。

在pom.xml中添加如下代码，目的是导入tomcat7相关依赖，用于web项目的启动

```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.1</version>
        <configuration>
          <port>80</port>
          <path>/</path>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

3、编写控制器类

- 3.1 将控制类当做bean，加入到ioc容器中来

> ```java
> //定义表现层控制器bean
> @Controller
> ```

- 编写控制器方法

> ```java
> //设置映射路径为/save，即外部访问路径
> @RequestMapping("/save")
> // ResponseBody : 设置当前操作返回值的类型
> //设置当前操作返回结果为指定json数据（本质上是一个字符串信息）
> @ResponseBody
> public String save(){
>  System.out.println("user save ...");
>  return "{'info':'springmvc'}";
> }
> //设置映射路径为/delete，即外部访问路径
> @RequestMapping("/delete")
> @ResponseBody
> public String delete(){
>  System.out.println("user save ...");
>  return "{'info':'springmvc'}";
> }
> ```

4、SpringMVC配置类编写，加载controller对应的bean

```java
//springmvc配置类，本质上还是一个spring配置类
@Configuration
@ComponentScan("com.itheima.controller")
public class SpringMvcConfig {

}
```

5、编写tomcat容器启动的配置类 `WebApplication`

```java
// 定义一个servelt容器启动的配置类，在里面加载spring的配置
public class WebApplication extends AbstractDispatcherServletInitializer {
    // 加载SpringMVC容器配置类，在tomcat容器启动的时候，就会加载SpringMVC相关配置了
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }
    // 设置那些请求归属SpringMVC处理
    // "/" ：所有请求归springMVC处理
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    // 加载spring容器配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
```



## 2.2 SpringMVC简介

- **入门案例流程分析**

![image-20220707201423920](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220707201424.png)



- **SpringMVC与Spring加载bean（controller）冲突问题**

![image-20220707201953441](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220707201953.png)



![image-20220707202025382](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220707202025.png)



- **解决方案**

![image-20220707202054859](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220707202055.png)

（1）方式一：只需要修改SpringConfig 精确扫描即可，只扫描service 、 dao包==（不推荐）==

```java
@Configuration
@ComponentScan({"com.itheima.service","com.itheima.dao"})
public class SpringConfig {
	
}
```

（2）方式二：在SpringConfig中配置扫描com.yrp，排除掉controller包

- 第一步：修改SpringConfig

```java
@ComponentScan(
    value="com.itheima",
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ANNOTATION, // ANNOTATION按注解过滤
        classes = Controller.class // 过滤@Controller注解
    )
)
public class SpringConfig {
}
```

- 第二步：去掉SpringMvcConfig中`@Configuration`注解

  - ==无什么要去掉`@Conifguration`：如果加了@Conifguration注解，SpringMvcConfig.java这个类也会被Spirng扫描成bean，下面的配置过滤无效！==
  - **解决方案：**

  ==如果非要留下@Conifguration这个注解，则唯一的解决方案是将`SpringMvcConfig.java`这个配置移动到其它包下，例如：项目根目录、com等==

  ```
  //@Configuration
  @ComponentScan("com.itheima.controller")
  public class SpringMvcConfig {
  
  }
  ```

- 第三步：修改ServletContainersInitConfig

>   protected WebApplicationContext createRootApplicationContext() {
>        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
>        ctx.register(SpringConfig.class);
>        return ctx;
>    }

```java
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringConfig.class);
        return ctx;
    }
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```



- 第三步2：修改ServletContainersInitConfig简化，让他继承`AbstractDispatcherServletInitializer`的子类`AbstractAnnotationConfigDispatcherServletInitializer`，具体代码如下

```java
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
 }
```



## 2.3 请求与相应

### **（1）请求映射路径**

![image-20220707220241468](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220707220241.png)

案例

```java
@Controller
//类上方配置的请求映射与方法上面配置的请求映射连接在一起，形成完整的请求映射路径
@RequestMapping("/user")
public class UserController {
    //请求路径映射
    @RequestMapping("/save")
    @ResponseBody
    public String save(){
        System.out.println("user save ...");
        return "{'module':'user save'}";
    }
    //请求路径映射
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        System.out.println("user delete ...");
        return "{'module':'user delete'}";
    }
}
```



### （2）解决POST请求中文乱码问题

在`ServletContainersInitConfig`中添加如下代码

```java
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // 字符编码过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter charFilter = new CharacterEncodingFilter("utf-8");
        return new Filter[]{charFilter};
    }
}
```



## 2.4 普通参数传递

**1、普通参数**

![image-20220708093938679](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708093938.png)

**2、普通参数-名称不对应**

> 需要借助@RequestParam

![image-20220708094025675](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708094025.png)



**3、实体类参数传递**

![image-20220708094207802](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708094208.png)

**4、嵌套实体类参数传递**

![image-20220708094334623](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708094334.png)



**5、数组传递**

![image-20220708094407174](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708094407.png)



**6、集合对象**

![image-20220708094439398](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708094439.png)



## 2.5 Json数据传参

**第一步、导入json所依赖的jar包**

在pom.xml中添加如下代码

```xml
  <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.0</version>
    </dependency>
  </dependencies>
```



**第二步、开启Spring MVC 将json自动转化成为对象的功能**	

在`SpringMvcConfig`上添加`@@EnableWebMvc`注解

```java
@Configuration
@ComponentScan("com.itheima.controller")
//开启json数据类型自动转换对象的功能
@EnableWebMvc
public class SpringMvcConfig {
}
```



**第三步、发送的时候postman设置如下**

![image-20220708102135945](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708102136.png)



**第四步、接收数据的时候记得添加上@RequestBody注解**

`@ResponseBody`

```java
    //集合参数：json格式
    //1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
    //2.使用@RequestBody注解将外部传递的json数组数据映射到形参的保存实体类对象的集合对象中，要求属性名称一一对应
    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User> list){
        System.out.println("list pojo(json)参数传递 list ==> "+list);
        return "{'module':'list pojo for json param'}";
    }

```



>  总结

![image-20220708102421040](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708102421.png)

## 2.6 日期参数传递



![image-20220708114738422](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708114738.png)

```java
//日期参数
//使用@DateTimeFormat注解设置日期类型数据格式，默认格式yyyy/MM/dd
@RequestMapping("/dataParam")
@ResponseBody
public String dataParam(Date date,
                        @DateTimeFormat(pattern="yyyy-MM-dd") Date date1,
                        @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss") Date date2){
    System.out.println("参数传递 date ==> "+date);
    System.out.println("参数传递 date1(yyyy-MM-dd) ==> "+date1);
    System.out.println("参数传递 date2(yyyy/MM/dd HH:mm:ss) ==> "+date2);
    return "{'module':'data param'}";
}
```



## 2.8 响应



- 应用案例

![image-20220708121647325](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708121647.png)

```java
@Controller
public class UserController {

    //响应页面/跳转页面
    //返回值为String类型，设置返回值为页面名称，即可实现页面跳转
    @RequestMapping("/toJumpPage")
    public String toJumpPage(){
        System.out.println("跳转页面");
        return "page.jsp";
    }
    //响应文本数据
    //返回值为String类型，设置返回值为任意字符串信息，即可实现返回指定字符串信息，需要依赖@ResponseBody注解
    @RequestMapping("/toText")
    @ResponseBody
    public String toText(){
        System.out.println("返回纯文本数据");
        return "response text";
    }
    //响应POJO对象
    //返回值为实体类对象，设置返回值为实体类类型，即可实现返回对应对象的json数据，需要依赖@ResponseBody注解和@EnableWebMvc注解
    @RequestMapping("/toJsonPOJO")
    @ResponseBody
    public User toJsonPOJO(){
        System.out.println("返回json对象数据");
        User user = new User();
        user.setName("itcast");
        user.setAge(15);
        return user;
    }

    //响应POJO集合对象
    //返回值为集合对象，设置返回值为集合类型，即可实现返回对应集合的json数组数据，需要依赖@ResponseBody注解和@EnableWebMvc注解
    @RequestMapping("/toJsonList")
    @ResponseBody
    public List<User> toJsonList(){
        System.out.println("返回json集合数据");
        User user1 = new User();
        user1.setName("传智播客");
        user1.setAge(15);
        User user2 = new User();
        user2.setName("黑马程序员");
        user2.setAge(12);
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        return userList;
    }
}
```

## 2.9 REST风格

### **（1）简介**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708123928.png" alt="image-20220708123928329" style="zoom: 50%;" /> 



![image-20220708155541779](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708155542.png) 

### （2）入门案例

![image-20220708162345715](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708162346.png)



![image-20220708162902699](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708162903.png)



![image-20220708162915698](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708162916.png)



> 用例代码

```java
//@Controller
//@ResponseBody配置在类上可以简化配置，表示设置当前每个方法的返回值都作为响应体
//@ResponseBody
@RestController     //使用@RestController注解替换@Controller与@ResponseBody注解，简化书写
@RequestMapping("/books")
public class BookController {

    @PostMapping        //使用@PostMapping简化Post请求方法对应的映射配置
    public String save(@RequestBody Book book){
        System.out.println("book save..." + book);
        return "{'module':'book save'}";
    }

    @DeleteMapping("/{id}")     //使用@DeleteMapping简化DELETE请求方法对应的映射配置
    public String delete(@PathVariable Integer id){
        System.out.println("book delete..." + id);
        return "{'module':'book delete'}";
    }

    @PutMapping         //使用@PutMapping简化Put请求方法对应的映射配置
    public String update(@RequestBody Book book){
        System.out.println("book update..."+book);
        return "{'module':'book update'}";
    }

    @GetMapping("/{id}")    //使用@GetMapping简化GET请求方法对应的映射配置
    public String getById(@PathVariable Integer id){
        System.out.println("book getById..."+id);
        return "{'module':'book getById'}";
    }

    @GetMapping             //使用@GetMapping简化GET请求方法对应的映射配置
    public String getAll(){
        System.out.println("book getAll...");
        return "{'module':'book getAll'}";
    }
}
```



## 2.10 REST风格实战-与页面数据交互

### **第一步：制作SpringMVC控制器，通过PostMan测试接口功能**

```java
@RestController
@RequestMapping("/books")
public class BookController {

    @PostMapping        //使用@PostMapping简化Post请求方法对应的映射配置
    public String save(@RequestBody Book book){
        System.out.println("book save..." + book);
        return "{'module':'book save'}";
    }
    @GetMapping
    public List<Book> showBooks(){
        // 造数据
        ArrayList<Book> list= new ArrayList<>();
        Book book = new Book("计算机类","MySQL45讲","这是一本MySQL入门必备精品书籍！");
        Book book2 = new Book("计算机类","深入理解Java虚拟机","这是一本JVM入门必备精品书籍！");
        list.add(book);
        list.add(book2);
        return list;
    }
}
```



![image-20220708172503448](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708172503.png)



### **第二步:SpringMvc对静态资源的访问放行**

2.1 编写一个配置类，继承`WebMvcConfigurationSupport`，覆盖`addResourceHandlers`方法，并将该类添加`@Configuration`注解

```java
@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    //设置静态资源访问过滤，当前类需要设置为配置类，并被扫描加载
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}

```

2.1 在SpringMvc配置类中扫描该类

`com.itheima.config`

```java
@Configuration
@ComponentScan({"com.itheima.controller","com.itheima.config"})
@EnableWebMvc	
public class SpringMvcConfig {
}
```





## 2.11 拦截器

**1、相关概念**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709164236.png" alt="image-20220709164235905" style="zoom:50%;" />

 

### **执行流程**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709175922.png" alt="image-20220709175922216" style="zoom:50%;" /> 



**2、拦截器（Interceptor）与过滤器(Filter)的区别**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709164326.png" alt="image-20220709164325813" style="zoom:50%;" /> 



**3、配置拦截器步骤**

1、创建一个interceptor包、在该包下面创建一个ProjectInterceptor类，实现HandlerInterceptor接口，覆盖实现三个方法

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709175253.png" alt="image-20220709175252980" style="zoom:50%;" /> 

 

2、加该类注入到Spring容器中，也就是加上`@Component`注解

代码如下：

```java
@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle.........");
        return true;// 如果reture false，postHandle、afterCompletion将不会执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle.........");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("postHandle.........");
    }
}

```

3、让SpringMVC容器扫描该类

> @ComponentScan({com.itheima.interceptor"})

```java
@Configuration
@ComponentScan({"com.itheima.controller","com.itheima.interceptor"})
@EnableWebMvc
//实现WebMvcConfigurer接口可以简化开发，但具有一定的侵入性
public class SpringMvcConfig  {

}
```

4、在SpringMVC的配置类`SpringMvcSupport.java`中进去配置、注册这个拦截器

```java
@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
  	// 注入这个bean
    @Autowired
    private ProjectInterceptor projectInterceptor;
  
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册配置projectInterceptor拦截器，以及设定拦截的范围
      	// "/books/*" 拦截所有以books开头的请求
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books","/books/*");
    }
}
```

5、如果没有创建SpringMvcSupport这个类，创建后记得让SpringMVC扫描到它哦

```java
//@Configuration
@ComponentScan({"com.yrp.controller","com.yrp.interceptor","com.yrp.config"})
// 功能很多，例如直接返回json数据
@EnableWebMvc
public class SpringMvcConfig {

}
```



> 方式二：配置

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709175825.png" alt="image-20220709175824675" style="zoom:50%;" /> 



![image-20220709180837049](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709180837.png)



![image-20220709180847367](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709180847.png)



![image-20220709180903890](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709180904.png)



### **多重拦截器**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709181439.png" alt="image-20220709181438463" style="zoom:50%;" /> 



- 情况1：三个拦截器全部`retun true`

![image-20220709181528426](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709181528.png)





情况2：3、2/1的拦截器分别``retun false`

![image-20220709181756622](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709181757.png)





# 3、SSM整合



### 1、后端开发环境搭建及代码撰写



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220708181349.png" alt="image-20220708181348892" style="zoom:50%;" /> 

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709090518.png" alt="image-20220709090518548" style="zoom: 50%;" /> 

 



![image-20220709090632557](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709090633.png)



### ==**2、前后端联调协议**==

![image-20220709090731475](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709090732.png)



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709090806.png" alt="image-20220709090806371" style="zoom:50%;" /> 





### 3、异常处理器

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709094856.png" alt="image-20220709094856114" style="zoom:33%;" /> 



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709094947.png" alt="image-20220709094947169" style="zoom:33%;" /> 



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709095127.png" alt="image-20220709095127396" style="zoom:50%;" /> 



![image-20220709101750222](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709101750.png)



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709102125.png" alt="image-20220709102124880" style="zoom:50%;" /> 



![image-20220709104738625](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709104739.png)

 ![image-20220709104627407](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709104627.png)



![image-20220709104640184](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709104640.png)



4、拦截异常并处理，在com.yrp.controller中创建`ProjectExceptionAdvice.java`类，用于拦截异常



```java
package com.yrp.controller;

// rest风格的异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 第一大类：系统异常
    // 拦截所有的SystemException类型的异常
    @ExceptionHandler(SystemException.class)
    public Result dpSystemException(SystemException ex){
        // 记录日志
        // 发送消息给运维
        // 发送邮件给开发人员
        // 把消息返回出去
        return new Result(ex.getCode(),null,ex.getMessage());
    }
    // 第二大类：业务异常
    // 拦截所有的SystemException类型的异常
    @ExceptionHandler(BusinessException.class)
    public Result dpBusinessException(BusinessException ex){
        // 把消息返回出去
        return new Result(ex.getCode(),null,ex.getMessage());
    }
    // 第三大类：其他异常
    // 拦截所有的Exception类型的异常
    @ExceptionHandler(Exception.class)
    public Result dpException(Exception ex){
        return new Result(Code.SYSTEM_UNKNOWN_ERR,null,"系统繁忙，请稍后重试！");
    }
}

```

包布局

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709161309.png" alt="image-20220709161308872" style="zoom:50%;" /> 







### 4、前端页面显示

![image-20220709112742793](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220709112743.png)



### 5、SSM整合代码实现

> 图书管理系统（增、删、改、查）

**0、创建tlb_books表,以及填充测试数据**

**1、配置pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.yrp</groupId>
    <artifactId>ssm_total</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <dependencies>
        <!--****************【一、Spirng相关依赖】***********************-->

        <!--spring-context-->
        <!--包含了spring-aop、spring-core、spring-beans、spring-expression-->
        <!--为什么这里隐藏掉呢？srping-webmvc包含了：aop、beans、context、core、web、expression-->
        <!--        <dependency>-->
        <!--            <groupId>org.springframework</groupId>-->
        <!--            <artifactId>spring-context</artifactId>-->
        <!--            <version>5.2.10.RELEASE</version>-->
        <!--        </dependency>-->
        <!--AOP 需要的aspect依赖-->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>

        <!--****************【二、Spirng整和MyBatis相关依赖】***********************-->
        <!--数据库连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.16</version>
        </dependency>
        <!--spring整合mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>
        </dependency>
        <!--mybatis自身依赖-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>
        <!--MySQL驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!--spring-jdbc操作数据库-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.10.RELEASE</version>
        </dependency>
        <!--****************【三、SpringMVC】***********************-->

        <!--springMVC相关依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.10.RELEASE</version>
        </dependency>
        <!--servlet-->
        <!--
        pom里使用的依赖包指定了 <scope>provided</scope>。
        provided的含义是这个依赖只在编译和测试的时候使用，在打包运行的时候不用，这是用来避免包冲突的。
        -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
        <!--json数据传输-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.0</version>
        </dependency>
        <!--****************【附加：Spirng整和Junit相关依赖】***********************-->
        <!--junit-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <!--spring-test-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.2.10.RELEASE</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.1</version>
                <configuration>
                    <port>80</port>
                    <path>/</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

**2、所有的配置文件**

- Spirng的配置类：`SpringConfig.java`

```java
//@Configuration
@ComponentScan({"com.yrp.controller","com.yrp.config"})
// 功能很多，例如直接返回json数据
@EnableWebMvc
public class SpringMvcConfig {

}
```

- MyBaties的配置类

`Jdbc.properties`

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://47.97.22.187:3306/yrp?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false
jdbc.username=root
jdbc.password=a.123456
```

`MyBatisConfig.java`

```java
public class MyBatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 依靠数据源dataSource
        factoryBean.setDataSource(dataSource);
        // 扫描pojo包(实体类),自动取别名（小写）
        factoryBean.setTypeAliasesPackage("com.yrp.pojo");
        return factoryBean;
    }

    // 扫描dao层的接口（mapper）
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.yrp.dao");
        return msc;
    }
}
```

`JdbcConfig.java`

```java
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Bean
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
```

- SpringMVC配置类

`SpringMvcConfig.java`

```java
//@Configuration
@ComponentScan({"com.yrp.controller","com.yrp.config"})
// 功能很多，例如直接返回json数据
@EnableWebMvc
public class SpringMvcConfig {

}
```

`ServletConfig.java`

```java
public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // post请求表单提交过滤：防治中文乱码
    // 字符编码过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("utf-8");
        return new Filter[]{cef};
    }
}
```

`SpringMvcSupport.java`，设置静态资源访问过滤器，防治被SpringMVC给拦截

```java
@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    //设置静态资源访问过滤，当前类需要设置为配置类，并被扫描加载
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}
```





