# 1. SpringBoot简介

## **1.1 spring程序与springboot程序对比**

![image-20220711105410626](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711105410.png)



# **2. SpringBoot起步依赖**

![image-20220711113645675](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711113645.png)



- 自带tomcat

![image-20220711113814620](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711113814.png)



# **3. 配置文件(3种)**

![image-20220711114533461](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711114533.png ) 

- 配置格式

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711114601.png" alt="image-20220711114600967" style="zoom:50%;" /> 

- 优先级顺序

![image-20220711114626924](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711114626.png)

 

# 4、yaml格式

![image-20220711114901931](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711114901.png)

- 基本语法规则



![image-20220711115024978](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711115025.png)

- 数组格式

![image-20220711115107391](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711115107.png)



# 5、yaml数据读取方式（3种）

## **1、 `@Value`属性，主要用于读取单个对象**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711120649.png" alt="image-20220711120649030" style="zoom:50%;" />

## **2、封装全部的数据到Eviroment对象**

![image-20220711120820174](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711120820.png)



## **3、自己造一个实体类，一一对应（常用配置）**



- 加上`@Compent`，将该类加载到spring容器中，让其成为一个bean
- `@ConfigurationProperties(prefix = “enterprise”) `// 从配置文件中读取前缀为enterprise的文件

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711120937.png" alt="image-20220711120937346" style="zoom:33%;" /> 

注意：在上述操作中可能会报警告⚠️，只需要将下面这个依赖加入到pom.xml文件中即可

![image-20220711121155050](/Users/ouyangyansong/Library/Application%20Support/typora-user-images/image-20220711121155050.png)





# 6、配置多环境开发

![image-20220711121445290](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711121445.png)



![image-20220711121453104](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711121453.png)



- 启动

![image-20220711151434166](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711151434.png)



![image-20220711151617897](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711151617.png)





# 7、SpringBoot整合junit

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711153805.png" alt="image-20220711153805051" style="zoom:50%;" /> 



# 8、SpringBoot整合SSM框架

- SpringBoot整合Spring（不存在）
- SpringBoot整合Spring（勾选web包即可）
- SpringBoot整合MyBatis （主要的）



> 以下这些配置文件，通通不需要
>
> 唯独配置两个东西：
>
> - ==数据源==
> - ==定义映配置setBasePackege(com.yrp.dao/com.yrp.mapper)==
>
> 在springboot中，通过在dao层接口上添加`@Mapper`注解，让spring知道dao层中哪些接口要进行自动代理，生成实现类



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220711154332.png" alt="image-20220711154332529" style="zoom:50%;" /> 



## **1、创建project**

- 勾选web模块
- 勾选MySQL驱动
- 勾选MyBatis



## **2、配置连接MySQL的数据源，在application.yml添加如下代码**

```yml
# 数据源配置
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://47.97.22.187:3306/yrp?serverTimezone=UTC
    username: root
    password: a.123456
    type: com.alibaba.druid.pool.DruidDataSource
# 服务器端口
server:
  port: 80
```



## **3、编写dao层的时候，记得在接口上添加`@Mapper`**

```java
@Mapper
public interface BookDao {
    @Select("select * from tbl_book where id = #{id}")
    public Book selBookById(Integer id);
}

```



==做完1,2,3步骤，其实springboot整合MyBaties已经完成，但是数据源使用的是springboot默认的，我们通常会选择自定义的数据源，例如druid，c3p0。==

## **4、引入德鲁伊druid数据源**

```xml
<!--德鲁伊数据源-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.16</version>
</dependency>
```

**==综合1、 2、 3步骤，springboot整合ssm完毕==**



# **9、SpringBoot自动配置**











# 10、热部署

## **（1）手动热部署**

1、导入依赖



2、command + f9 

![image-20220716114553241](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716114553.png)



## **（2）自动热部署**

> 解决command+f9问题

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716115139.png" alt="image-20220716115139737" style="zoom:50%;" /> 



1、在idea的设置中开启`build project automatically`

![image-20220716115557071](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716115557.png)

2、command+alt + shif + / ，打开registry，设置如下

![image-20220716115524979](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716115525.png)





![image-20220716115747552](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716115747.png)



### (2) 手工设热部署作用范围

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716120214.png" alt="image-20220716120214907" style="zoom:50%;" /> 

- 手动设置

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716120243.png" alt="image-20220716120232391" style="zoom:50%;" /> 



## **（3） 关闭热部署**

在application.yml设置如下

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220716120341.png" alt="image-20220716120341761" style="zoom:50%;" /> 

# 11、SpringBoot整合腾讯云短信

0、在腾讯云短信服务那边创建好【签名】、【模板】，获取另外的两个参数appid，appkey

> 参数1：签名



![image-20220719112943190](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220719112943.png)



> 参数2：模板id

![image-20220719113028553](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220719113028.png)



> 参数3：appid
>
> 参数4：appkey

![image-20220719113112792](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220719113112.png)

1、在application.yml配置如下

```yml
server:
  port: 80
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://47.97.22.187:3306/yrp?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false
    username: root
    password: a.123456
tx:
  sms:
    smsSign: 是叶十三
    templateId: 1478571
    appid: 1400707771
    appkey: 833c7958b237504904048c65176d79b4
```

2、导入腾讯云短信服务依赖的jar包

```xml
        <!--腾讯云短信服务-->
        <dependency>
            <groupId>com.github.qcloudsms</groupId>
            <artifactId>qcloudsms</artifactId>
            <version>1.0.6</version>
        </dependency>
        <dependency>
            <groupId>com.tencentcloudapi</groupId>
            <artifactId>tencentcloud-sdk-java</artifactId>
            <version>3.1.270</version><!-- 注：这里只是示例版本号（可直接使用），可获取并替换为 最新的版本号，注意不要使用4.0.x版本（非最新版本） -->
        </dependency>
```



3、发送短信工具类

```java
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class SMSUtils {
    // 短信应用 SDK AppID
    @Value("${tx.sms.appid}")
    private   Integer appid; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    @Value("${tx.sms.appkey}")
    private  String appkey;
    // 短信模板 ID，需要在短信应用中申请
    @Value("${tx.sms.templateId}")
    private   Integer templateId; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 签名
    @Value("${tx.sms.smsSign}")
    private   String smsSign; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
    /**
     * 新增图书
     *
     */
    public  void sendSMS(String phone, String code ,String time) {
        try {
            ArrayList<String> params  = new ArrayList<>();
            params.add(code);
            if (time!= null && !time.equals("")){
                params.add(time);
            }
            System.out.println(phone);
            System.out.println(params);
            System.out.println("appid:"+appid);
            System.out.println("appkey:"+appkey);
            System.out.println("templateId:"+templateId);
            System.out.println("smsSign:"+smsSign);
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone, templateId, params, smsSign, "", "");

        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
    }
}

```

# 12、SpringBoot整合Redis

> Spring Data Redis	



SpringBoot操作数据：springdata jpa jdbc mongdb redis！

SpringData也是和SpringBoot同级别的项目！

说明：在SpringBoot 2.x之后，原来的jedis被替换为了lettuce？

jedis：底层采用的是直连server，多个线程操作的话是不安全的，如果想要解决这个问题，使用jedis pool连接池！更像BIO模式！

lettuce：底层采用的是netty，实例可以多个线程中共享，不存在线程不安全的情况！可以减少线程数量，更像NIO模式！

 

1、创建springboot项目，勾选如下

![image-20201005161107840](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201005161107840.png)



2、导入依赖porm.xml

```xml
        <!--操作redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>springboot-starter-data-redis</artifactId>
        </dependency>
```



3、配置properties文件

```yaml
spring:
  redis:
    port: 6379
    host: 47.104.231.144
#    password: 12345
    database: 0
```

4、测试

> IDEA快捷键：Ctrl+N按名字搜索**类**

```java
@SpringBootTest
class Redis02SpringbootApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;


	@Test
	void contextLoads() {
		// reidsTemplate
		// opsForValue 操作字符串，类似于String
		// opsForList 操作List， 类似于List
		// opsForSet
		// opsForZSet
		// opsForHash


		// 除了基本的操作，我们可以通过RedisTemplate操作，比如事务和基本的CRUD
		//RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		//connection.flushDb();
		// connection.flushAll();

		redisTemplate.opsForValue().set("name","公众号：是叶十三");
		System.out.println(redisTemplate.opsForValue().get("name"));
	}

}
```

运行结果：

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006144725189.png" alt="image-20201006144725189" style="zoom:50%;" /> 



`RedisTemplate.java`值的==序列化配置==

（1）、打开RedisTemplate类

![image-20201006145751149](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006145751149.png)

（2）默认序列化

![image-20201006151026335](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006151026335.png)









```yml
# SpringBoot 所有的配置类，都有一个自动配置类 ：RedisAutoConfiguration
# 自动配置类都会绑定一个properties配置文件 ：RedisProperties
```

### RedisAutoConfiguration自动配置 源码分析

在项目的`Extrenal Libraries`下找到`spring-boot-autoconfigure`-->`META-IN`-->`spring.facttories`

![image-20201005162730927](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201005162730927.png)

打开`spring.factories`搜索redis可以找到`RedisAutoConfiguration.java`

![image-20201005163021100](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201005163021100.png)

双击打开`RedisAutoConfiguration.java`

```java
@ConditionalOnClass({RedisOperations.class})
@EnableConfigurationProperties({RedisProperties.class})//自动配置类绑定的RedisPropertie配置文件
@Import({LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class})
public class RedisAutoConfiguration {
    public RedisAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean(
        name = {"redisTemplate"}
    ) // 我们可以自定义一个redisTemlate 来替换这个
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean //由于String是我们Redis中最常使用的类型，所以单独提出来的当一个方法！
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}

```

==解释：==

​		RedisTemplate 模板类

​		StringRedisTemplate

找到了自动配置类绑定的配置文件：`RedisProperties`，双机进入该文件

```java
@ConfigurationProperties(
    prefix = "spring.redis"
)
public class RedisProperties {
    private int database = 0;
    private String url;
    private String host = "localhost";
    private String password;
    private int port = 6379;
    private boolean ssl;
    private Duration timeout;
    private String clientName; // 客户端名字
    private RedisProperties.Sentinel sentinel; // 哨兵
    private RedisProperties.Cluster cluster; // 集群
    private final RedisProperties.Jedis jedis = new RedisProperties.Jedis();
    private final RedisProperties.Lettuce lettuce = new RedisProperties.Lettuce();

    public RedisProperties() {
    }
```



### 关于对象的保存

（1）、直接传对象

- 对象user，==未序列化==

```java
@Component  //组件
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private int age;


}
```

结果：

![image-20201006153719121](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006153719121.png)

（2）、加上==序列化==

```java
@Component  //组件
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private String name;
    private int age;


}
```

结果：

![image-20201006154323112](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006154323112.png)



> 在RedisConfig.java中自定义一个**==RedisTemplate==**，序列化规则

```java
package com.yrp.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * reids自定义配置类
 */
@Configuration
public class RedisConfig {

    // 自定义了一个RedisTemplate
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        // 我们为了自己开开发方便，一般直接使用String，Object类型
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        // json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
```



### 工具类RedisUtil

```java
// 在我们企业的真实开发中，一般可以看到一个公司自己封装的RedisUtil
@Component
public final class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // =============================common============================
    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time 时间(秒)
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    // ============================String=============================

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 普通缓存放入并设置时间
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */

    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 递增
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 递减
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    // ================================Map=================================

    /**
     * HashGet
     * @param key  键 不能为null
     * @param item 项 不能为null
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * HashSet 并设置时间
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }


    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }


    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */

    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 将list放入缓存
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */

    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}

```

使用工具类：

在测试类中添加测试如下：

```java
@Test
public void testUserObject2() throws JsonProcessingException {
   redisUtil.set("name","是叶十三");
   System.out.println(redisUtil.get("name"));

}
```

![image-20201006161336719](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006161336719.png)

# 13、Spring Cache 整合Redis



![image-20220722174904449](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220722174904.png)



![image-20220723150839177](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723150839.png)



# 14、SpringBoot 整合阿里云oss存储图片

1、引入坐标

```xml
        <!--aliyun oss dependence-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alicloud-oss</artifactId>
            <version>2.2.0.RELEASE</version>
        </dependency>
```

2、配置application.yml

```yml
spring:
  cloud:
    alicloud:
      access-key: LTAI5t6jqwXERu4fEe1WdgDg
      secret-key: vD9oaWYX0ataScYp0UK8eECXdqJlNc
      oss:
        endpoint: oss-cn-beijing.aliyuncs.com
        bucketname: yerenping
```



3、OssUtil

```java
package com.kk.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: Aliyun OSS
 * @author: Spike Wong
 * @Created: 2022/5/15
 */
@Component
public class OssUtil implements InitializingBean {
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessKeyID;
    @Value("${spring.cloud.alicloud.secret-key}")
    private String accesskeySecret;
    @Value("${spring.cloud.alicloud.oss.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = accessKeyID;
        ACCESS_KEY_SECRET = accesskeySecret;
        BUCKET_NAME = bucketName;
    }
}
```



4、编写OssService.java

```java
import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFile(MultipartFile file);
}
```



5、编写实现类OssServiceImpl.java

```java
package com.yrp.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.yrp.service.OssService;
import com.yrp.utils.OssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFile(MultipartFile file) {
        String url = null;
        String endpoint = OssUtil.END_POINT;
        String accessKeyId = OssUtil.ACCESS_KEY_ID;
        String accessKeySecret = OssUtil.ACCESS_KEY_SECRET;
        String bucketName = OssUtil.BUCKET_NAME;
        //build Oss connection
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            //need 2 pcs of stream，one for MD5，the other for upload to Oss。https://blog.csdn.net/xueyijin/article/details/121526772
            InputStream inputStream = file.getInputStream();
            //
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            String md5 = DigestUtils.md5DigestAsHex(baos.toByteArray());
            log.info("md5:{}", md5);
            InputStream inputStreamForMD5 = new ByteArrayInputStream(baos.toByteArray());
            InputStream inputStreamForOss = new ByteArrayInputStream(baos.toByteArray());
            /*其实用下面这个也是一样的，但是哪个更高效和通用？？？
            InputStream inputStreamForMD5 = file.getInputStream();
            InputStream inputStreamForOss = file.getInputStream();
             */
            //get original name
            String filename = file.getOriginalFilename();
            //get suffix
            String fileSuffix = filename.substring(file.getOriginalFilename().lastIndexOf("."));
            //获取一个md加密
            String md5OfFileName = DigestUtils.md5DigestAsHex(inputStreamForMD5);
            //这里是为了按上传时间分配目录。精确到月,这里用到一个第三方的jar包，记得笔记2022/5/18
            String dateMark = DateTime.now().toString("yyyyMMdd/");
            //拼接成完整的文件名。
            final String uploadKey = dateMark + md5OfFileName + fileSuffix;
            log.info("uploadKey:{}", uploadKey);
            //upload them
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, uploadKey, inputStreamForOss);
            //阿里云返回eTag为全大写的md5值
            String eTag = putObjectResult.getETag();
            log.info("eTag:{}", eTag);
            //拼接url，这个就是地址
            url = "https://" + bucketName + "." + endpoint + "/" + uploadKey;
            log.info("Image URL：{}", url);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }
}

```

6、工具类（url地址中的资源（图片）转成输入流inputSatream）

```java
package com.yrp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Description:
 * @Author:Spike Wong
 * @Date:2022/7/5
 */
public class InputStreamUtils {

    //url地址中的资源（图片）转成输入流inputSatream
    public static InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("[Exception of getting image stream]，image url path is：" + url);
            e.printStackTrace();
        }
        return null;
    }
}
```

7、文件上传与下载功能实现（控制器层 ）

```java
package com.yrp.controller.common;

import com.yrp.common.Code;
import com.yrp.common.R;
import com.yrp.exception.SystemException;
import com.yrp.service.OssService;
import com.yrp.utils.InputStreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 1、文件上传
 * 2、文件下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {


    @Autowired
    OssService ossService;
  
		  /**
     * 文件下载到阿里云oss
     *
     * @param name 文件名
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        // 判断上传文件的大小
        if (file.getSize() > 1024 * 1024 * 5) {
            return R.error("Size of file can exceed 5 MB");
        }
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        String originalFilename = file.getOriginalFilename();
        // 重新命名
        //  1、获取后缀
        //  2、获取随机数uuid
        String uuid = UUID.randomUUID().toString();
        //  3、重新拼接
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 3.1 判断文件的格式
//        if (!"jpg,jpeg,JPG,PNG,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
//            return R.error("文件格式错误，只需要上传jpg,jpeg,gif,png！");
//        }
        // 4、将文件上传到阿里云oss，接收返回的url路径
        String url = ossService.uploadFile(file);
        log.info("url = {}", url);
        return R.success(url);
    }
    /**
     * 从到阿里云oss下载文件
     *
     * @param name 文件名
     * @return
     */

    @GetMapping("/download")
    public void download(String name, HttpServletResponse resp) {
        // 日志
        log.info("name = {}", name);
        // 特判
        if (name == null || name.equals("")) {
            throw new SystemException(Code.SYSTEM_ERR, "文件名不能为空");
        }
        ServletOutputStream sos = null;
        InputStream fis = InputStreamUtils.getImageStream(name);
        int len = 0;
        // 处理
        try {
            // 通过InputStreamUtils 将url所在资源转成inputStream
            // 输出流，通过输出流将文件写回到浏览器
            sos = resp.getOutputStream();
            // 位置
//            resp.setContentType("image/jpeg");
            resp.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(name, "UTF-8"));
            byte[] b = new byte[1024];
            while ((len = fis.read(b)) != -1){
                sos.write(b,0,len);
                sos.flush();
            }
        } catch (IOException e) {
            throw new SystemException(Code.SYSTEM_ERR, "文件下载失败，请稍后重试");
        } finally {
            try {
                if (sos != null) {
                    sos.close();
                }
            } catch (IOException e) {
                throw new SystemException(Code.SYSTEM_ERR, "文件下载失败，请稍后重试");
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                throw new SystemException(Code.SYSTEM_ERR, "文件下载失败，请稍后重试");
            }
        }
    }

}
```

# 15、springboot整合邮箱注册/登录

```yml
spring:
  mail:
    host: smtp.163.com # 网站发送邮件邮箱服务 host
    port: 465
    # 邮箱
    username: yerenping123@163.com
    # 密码(邮箱的授权码)
    password: FXIDSPBKWKUXSWMJ
```



# 16、springboot整合七牛云云存储

```properties
#####################################################################################################
###################################### 七牛云相关配置，必填：文件上传使用 ################################
#####################################################################################################
custom-config.upload-file.qiniu.accessKey=jiWteVE2572wJCDVdX9OUyjPi3MUdAzvWtOSFrcX
custom-config.upload-file.qiniu.secretKey=6zAVYj_bZdKDIPDqwr5gZZ4eV84iAveQwPsdl_JW
custom-config.upload-file.qiniu.bucketName=rg1cfconl.hd-bkt.clouddn.com
# 访问域名，例如 https://static.developers.pub/
custom-config.upload-file.qiniu.accessDomain=http://rg1cfconl.hd-bkt.clouddn.com/

```

