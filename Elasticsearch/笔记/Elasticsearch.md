优质教程：https://www.bilibili.com/video/BV1SQ4y1m7Ds



## 1、RestFul

- Rest-表现层状态转化：Representational State Transfer

  ​         资源的表现层状态转化：Resource Representational State Transfer

> 是一组**设计原则**，设计约束，设计思路，设计约定

- RestFul：是一种软件架构风格

> 一个架构的设计如果遵循**Rest设计原则**了，我们称这个架构为RestFul架构风格



---

**相关概念**

（1）资源（Resource）

> 1、把网络中的一切事物都抽象成资源
>
> 2、每一个资源都存在一个唯一的资源标识符

（2）表现层（Representational）

> 将资源具体呈现出来的形式，称之为表现层

（3）状态转化（State Transfer）

> 客户端通过操作服务器中资源 使得资源发生某种状态转变

（4）原则

> 1、使用Rest的URL替换 传统的URL
>
> 传统URL
>
> ​	**http://localhost:8080/项目名/user/findOne**?id=21
>
> ​	**http://localhost:8080/项目名/emp/findOne**?id=21
>
> Rest的URL
>
> ​	**http://localhost:8080/项目名/user/findOne/21**
>
> 2、四种动作对应服务端的四种操作（CRUD增删改重） Create -Retrieve-Update-Delete
>
> GET(查询)
>
> POST （**更新**、添加）
>
> PUT （**添加** 、更新）
>
> DELETE 删除



==总结：==

REST原则就是指一个URL代表一个唯一的资源，并且通过HTTP协议里的四个动词:GET 、PUT、POST、DELETE对应服务器端的基本操作：GET用于获取资源，POST用于添加资源（也可以用于更新资源）,PUT用于更新资源，DELETE用于删除资源。



### SpringBoot整合Rest

1、引入web包

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

2、编写控制器类

> 实现CRUD
>
> 请求路径中的参数：@PathVariable
>
> 请求体重的参数：

```java
@RestController
@Slf4j
public class SpringBootRestController {

    /**
     * RequstMapping 包含 GetMapping PutMapping PostMapping DeleteMapping
     */
  	
  	/**
  	* 查询一个用户
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
```

![image-20230123125545299](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123125545.png)



## 2、全文检索lucene

> **全文检索是计算机程序通过扫描文章中的每一个词，对每一个词建立一个索引，指明该词在文章中出现的次数和位置。当用户查询时根据建立的索引查找，类似于通过字典的检索字表查字的过程。**

- 检索：
  - 索（建立索引）
  - 检（根据已建立的索引，进行快速查询）
- 全文检索（Full-Text Retrieval(检索)）
以文本作为检索对象，找出含有指定词汇的文本。**全面、准确和快速是衡量全文检索系统的关键指标。**

- 特点：

```
1. 只处理文本。

2. 不处理语义。

3. 搜索时英文不区分大小写。

4. 结果列表有相关度排序。
```



## 3、Elasticsearch介绍和安装以及相关概念

### 1、介绍

官网：https://www.elastic.co/cn

优质文档：https://www.letianbiji.com/elasticsearch/es7-search-sort.html

> ElasticSearch 简称 ES ，是基于Apache Lucene构建的开源搜索引擎，是当前流行的企业级搜索引擎。Lucene本身就可以被认为迄今为止性能最好的一款开源搜索引擎工具包，但是lucene的API相对复杂，需要深厚的搜索理论。很难集成到实际的应用中去。但是ES是采用java语言编写，提供了简单易用的RestFul API，开发者可以使用其简单的RestFul API，开发相关的搜索功能，从而避免lucene的复杂性。

![image-20230123165148132](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123165148.png)

==作用：我们给它（es）数据，它（es）帮我构建索引，日后我们基于这个索引进行数据检索==



### 2、基本概念

![image-20230123173652606](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123173652.png)

![image-20230123214910938](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123214911.png)



==注意==

从 ES 7.0 开始，Type 被废弃

在 7.0 以及之后的版本中 Type 被废弃了。一个 index 中只有一个默认的 type，即 `_doc`。

ES 的Type 被废弃后，库表合一，Index 既可以被认为对应 MySQL 的 Database，也可以认为对应 table。

也可以这样理解：

- **ES 实例：对应 MySQL 实例中的一个 Database。**
- **Index 对应 MySQL 中的 Table 。**
- **Document 对应 MySQL 中表的记录。**







#### **（1）接近实时(NRT Near Real Time )**

Elasticsearch是一个接近实时的搜索平台。这意味着，从索引一个文档直到这个文档能够被搜索到有一个轻微的延迟(通常是1秒内)



#### **（2）索引-Index(类比MySQL中的database)**

> 类似于mysql中的database



一个索引就是一个拥有几分相似特征的文档的集合。比如说，你可以有一个客户数据的索引，另一个产品目录的索引，还有一个订单数据的索引。**一个索引由一个名字来标识(必须全部是小写字母的)**，并且当我们要对这个索引中的文档进行索引、搜索、更新和删除的时候，都要使用到这个名字。索引类似于关系型数据库中Database的概念。在一个集群中，如果你想，可以定义任意多的索引。





#### （3）类型Type（类比MySQL中的table）

> 类比MySQL中的table

在一个索引中，你可以定义一种或多种类型。一个类型是你的索引的一个逻辑上的分类/分区，其语义完全由你来定。通常，会为具有一组共同字段的文档定义一个类型。比如说，我们假设你运营一个博客平台并且将你所有的数 据存储到一个索引中。在这个索引中，你可以为用户数据定义一个类型，为博客数据定义另一个类型，当然，也可 以为评论数据定义另一个类型。类型类似于关系型数据库中Table的概念。

==NOTE: 在5.x版本以前可以在一个索引中定义多个类型,6.x之后版本也可以使用,但是不推荐,在7~8.x版本中彻底移除一个索引中创建多个类型==



#### （4）映射Mapping（类比MySQL中的table的schema：表结构）

> 类比MySQL中的table的schema（表结构）

Mapping是ES中的一个很重要的内容，它类似于传统关系型数据中table的schema，用于定义一个索引(index)中的类型(type)的数据的结构。 在ES中，我们可以手动创建type(相当于table)和mapping(相关与schema),也可以采用默认创建方式。在默认配置下，ES可以根据插入的数据自动地创建type及其mapping。 mapping中主要包括字段名、字段数据类型和字段索引类型

#### （4）文档(document)

> 类比MySQL数据库表中的一条记录

**一个文档是一个可被索引的基础信息单元，类似于表中的一条记录。**比如，你可以拥有某一个员工的文档,也可以拥有某个商品的一个文档。文档以采用了轻量级的数据交换格式JSON(Javascript Object Notation)来表示。



#### （5）补充概念

- ES集群： 多台elesticsearch服务器组合在一起
- ES节点：集群当中的一台es服务器称为节点
- ES分片：分片是对索引的进一步划分

- ES副本：备份是对分片的备份，一个副本可以包含多个分片





#### （6）Docker安装ES

前提条件：linux服务器下已经安装了docker

##### **1、下载elasticsearch镜像，通过docker命令一键下载**

在docker hub 中找到需要下载的版本，我这里以7.9.3为例子

```shell
docker pull elasticsearch:7.9.3
```



下载地址：https://hub.docker.com/_/elasticsearch/tags?page=1&name=7.9.3

![image-20230124183907496](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124183907.png)



##### 2、运行elasticsearch且让外部能够访问

```shell
docker run -d --name es -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.9.3
```

##### 3、验证是否安装成功

```shell
curl http://localhost:9200
```

##### 4、开启服务器的9200和9300端口

在阿里云或者腾讯云的后台进行配置









## 4、ES数据可视化-Kibana

### （0）Docker方式

- 下载镜像

```shell
docker pull kibana:7.9.3
```

- 安装运行

> -d 后台运行
>
> –name mykibana -p 5601:5601 kibana:7.9.3

```shell
docker run -d -p 
```

- 配置kibana

==编辑kibana.yml ，让kibana关联我们的elasticsearch==

> 1、关联es的访问地址
>
> 2、启动与es的链接 ，true

![image-20230124190017480](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124190017.png)

- 重启kibana

```shell
# 找到kibana镜像的id
docekr ps 
# 启动
docker restart kibana的镜像id
```





- Docker用数据链方式启动kibana

![image-20230124190742953](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124190743.png)





### **（1）下载**

> macos下Kibana安装教程：https://zhuanlan.zhihu.com/p/74766291 

下载地址：https://www.elastic.co/cn/downloads/past-releases/kibana-7-9-3



### **（2）配置**

修改kibana配置文件kibana.yml，修改内容如下

![image-20230123193325044](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123193325.png)

![image-20230123193602694](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123193602.png)



 **（3）启动kibana**

- 双击启动`kibana-plugin`，弹出警告不用理会

![image-20230123194226134](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123194226.png)



- 在终端通过命令启动`kibana`

> 在bin目录下
>
> ./ kibana -d 可以实现后台启动

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123194359.png" alt="image-20230123194359207" style="zoom:50%;" /> 







### （3）Dev tool

地址：http://127.0.0.1:5601/app/dev_tools#/console

- 查询集群下索引的健康状态

```
GET /_cat/indices?v
```

![image-20230123200829490](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123200829.png)

red 索引不完整，不可用

yellow索引可用，但不健壮

green 索引可用且健壮



## 5、索引（index）的基本操作

```shell
# *******索引操作***********
# 创建索引
PUT /ye13

# 删除索引
DELETE /ye13

# 删除所有索引[慎用:ElasticSearch会自带两个索引,如果删除kibana客户端将不能使用,只有重启]
DELETE /*	

# 查询ES下所有索引

GET /_cat/indices?v


```



## 6、类型Type(Mapping)的基本操作

> ==Es7.x 将Type这个概念给废弃掉了==
>
> 默认用_doc表示_



### **（1）创建类型Type**

```shell
# 创建索引（index） 并且 创建类型(type)

# 例子 ：创建索引（student_ms） 并且 创建类型(student)
# student(id, name, introduce、money)
PUT /student_ms
{
  "mappings": {
    "properties": {
      "id":{"type":"integer"},
      "name":{"type":"keyword"},
      "introduce":{"type":"text"},
      "salary":{"type":"double"}
    }
  }
}
```





### **（2）查看索引下的类型映射信息**

```shell
# 查看student_ms索引下的类型映射信息
GET /student_ms/_mapping
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230123225033.png" alt="image-20230123225033599" style="zoom:50%;" /> 

## 7、文档的基本操作

### （1）Es7.0以前的版本（有type的概念）

```shell
#******文档的操作********

# 添加文档
PUT /student_ms/_doc/1{
  "id":"1",
  "introduce": "张三是也",
  "name":"张三",
  "salary":"3000"
}
# 查看文档
GET /student_ms/_doc/1

# 删除文档
DELETE /student_ms/_doc/1


 POST /索引/类型/id/_update
    {
      "doc":{
        "属性名":"值"
      }
    }


```



> 更新文档

```shell

# 方式一：在原有数据基础上进行更新

POST /book/musicBook/1/_update
{
  "doc": {
    "price":"40"
  }
}

# 第二种方式：添加新的约束的数据
这种方式，type并没有color属性，但你添加的时候，ES会给你进行相应的匹配

POST /book/musicBook/1/_update
{
  "doc": {
    "color":"红色"
  }
}

# 第三种方式：根据script脚本进行更新[使用较少]

POST /book/musicBook/1/_update
{
  "script": "ctx._source.price += 5.0"
}
```



> 文档批量操作

```shell
# 同时索引两个文档
PUT /book/musicBook/_bulk
{"index":{"_id": "3"}}
  { "name":"童话书", "price":"21","des":"这是一本童话书"}
{"index":{"_id": "4"}}
  {"name":"冒险书","price":"24","desc":"这是一本冒险书"}
  
# 同时修改和删除文档
PUT /book/musicBook/_bulk
{"update":{"_id":"3"}}
{"doc":{"name":"修改后的童话书"}}
{"delete":{"_id":"4"}}

```



### （2）Es7.0以后的版本（我没有type的概念）

1、创建index

```json
PUT student
{
  "mappings" : {
    "properties" : {
      "uid": {
        "type" : "integer"
      },
      "name" : {
        "type" : "keyword"
      },
      "age" : {
        "type" : "integer"
      }
    }
  },
  "settings" : {
    "index" : {
      "number_of_shards" : 10,
      "number_of_replicas" : 1
    }
  }
}

```

2、在索引中添加/修改/查看/删除一条文档

- **添加**

```shell
PUT student/_doc/1
{
  "uid": 1,
  "name": "张三",
  "age": 10
}
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124113015.png" alt="image-20230124113015192" style="zoom:50%;" />  

- **修改**

```shell
# 方式一：不保留原始数据
# 将原来的student下id为1的文档 姓名由“张三” -> "叶仁平"
# 存在问题：它的操作是先将原有的id = 1的文档进行删除，然后再重新插入一条新的数据
POST student/_doc/1
{
  "uid": 1,
  "name": "叶仁平",
  "age": 10
}
#方式二：保留原始数据 “_update”
POST student/_doc/1/_update
{
  "doc":{
    "name": "李四"
  }
}


```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124113137.png" alt="image-20230124113137748" style="zoom:50%;" /> 

- 查看文档的信息

```shell
GET /student/_doc/1
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124113310.png" alt="image-20230124113310526" style="zoom:50%;" /> 

- 删除

```shell
DELETE /student/_doc/1
```

- 文档的批量操作

```shell
# _bluk 文档的批量操作
#   index : 新增标识符
#   update : 修改标识符
#   delete : 修改标识符
PUT /student/_doc/_bulk
{"index":{"_id":"2"}}
  {"name": "李四2222"}
{"delete":{"_id":"3"}}

```





## 8、ES中高级检索（Query）

![image-20230124120605605](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124120605.png)



> QueryString ： 在url中拼接参数 ，类比get请求
>
> QueryDSL : 在请求体中进行传参（json），类比post请求



- 准备测试数据

```shell


# 创建索引
PUT student
{
  "mappings" : {
    "properties" : {
      "name" : {
        "type" : "keyword"
      },
      "age" : {
        "type" : "integer"
      }
    }
  }
}

# 批量新增文档
POST _bulk
{ "index" : { "_index" : "student", "_id" : "1" } }
{ "name" : "张三", "age": 12}
{ "index" : { "_index" : "student", "_id" : "2" } }
{ "name" : "李四", "age": 10 }
{ "index" : { "_index" : "student", "_id" : "3" } }
{ "name" : "王五", "age": 11 }
{ "index" : { "_index" : "student", "_id" : "4" } }
{ "name" : "陈六", "age": 11 }

POST /student/_doc/_bulk
{ "index" : {}}
{ "name" : "叶七", "age": 15}
{ "index" : {}}
{ "name" : "赖八", "age": 16 }
{ "index" : {}}
{ "name" : "马九", "age": 17 }
{ "index" : {}} 
{ "name" : "牛十", "age": 28 }
```





### （1）QueryString 基于URL

```shell
# 查询student下所有文档
GET student/_search?q=*

# 查询student下所有文档 安装年龄升序排序（默认升序）
GET student/_search?q=*&sort=age

# 查询student下所有文档 安装年龄降序排序
GET student/_search?q=*&sort=age:desc

# 分页查询 from=起始页，size=每页显示的条数
GET student/_search?q=*&from=2&size=2

# 查询，只展示指定的字段
GET student/_search?q=*&_source=name
```

### （2）QueryDSL 基于requestBody

```shell
# 查询student下所有文档
GET /student/_search
{
  "query":{
    "match_all":{}
  }
}


# 查询student下所有文档 安装年龄升序排序
GET /student/_search
{
  "query": {
    "match_all": {}
  }
  , "sort": [
    {
      "age": {
        "order": "asc"
      }
    }
  ]
}

# 查询student下所有文档 安装年龄降序排序
GET /student/_search
{
  "query": {
    "match_all": {}
  }
  , "sort": [
    {
      "age": {
        "order": "desc"
      }
    }
  ]
}

# 分页查询 from=起始页，size=每页显示的条数
GET /student/_search
{
  "query": {
    "match_all": {}
  }
  , "sort": [
    {
      "age": {
        "order": "desc"
      }
    }
  ]
  , "from": 0
  , "size": 3
}

# 过滤，只需要指定的字段
# 例如：只显示name
GET /student/_search
{
  "query": {
    "match_all": {}
  }
  , 
  "_source": "name"
}
# 例如：只显示name 和 age【数组格式】
GET /student/_search
{
  "query": {
    "match_all": {}
  }
  , 
  "_source": ["name","age"] 
}
```





### （3）关键词查询（term）

> 基于指定的关键词查询
>
> 1、type:text 类型分词
>
> 2、type:keyword 不分词
>
> 3、ES中默认的分词器是标准分词器，对于中文是单字分词（张三 = > 张 三）
>
> 4、在ES中只有text分词

```shell
GET /student/_search
{
  "query": {
    "term": {
      "name": {
        "value": "赖八"
      }
    }
  }
}
```



> text 分词

- 英文

```shell
GET /_analyze
{
  "text": "i love so much you!"
}
```

分词结果（按照单词进行分词）

```shell
{
  "tokens" : [
    {
      "token" : "i",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "<ALPHANUM>",
      "position" : 0
    },
    {
      "token" : "love",
      "start_offset" : 2,
      "end_offset" : 6,
      "type" : "<ALPHANUM>",
      "position" : 1
    },
    {
      "token" : "so",
      "start_offset" : 7,
      "end_offset" : 9,
      "type" : "<ALPHANUM>",
      "position" : 2
    },
    {
      "token" : "much",
      "start_offset" : 10,
      "end_offset" : 14,
      "type" : "<ALPHANUM>",
      "position" : 3
    },
    {
      "token" : "you",
      "start_offset" : 15,
      "end_offset" : 18,
      "type" : "<ALPHANUM>",
      "position" : 4
    }
  ]
}
```



- 中文

```shell
GET /_analyze
{
  "text": "我爱你中国"
}
```

结果

```shell
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "<IDEOGRAPHIC>",
      "position" : 0
    },
    {
      "token" : "爱",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "<IDEOGRAPHIC>",
      "position" : 1
    },
    {
      "token" : "你",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "<IDEOGRAPHIC>",
      "position" : 2
    },
    {
      "token" : "中",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "<IDEOGRAPHIC>",
      "position" : 3
    },
    {
      "token" : "国",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "<IDEOGRAPHIC>",
      "position" : 4
    }
  ]
}

```



### （4）范围查询(range)

```shell
# 查询年龄大于等于10并且小于等于20的学生
GET /student/_search
{
  "query": {
    "range": {
      "age": {
        "gte": 10,
        "lte": 20
      }
    }
  }
}
```



### （5）基于关键词的前缀查询(prefix)

```shell
# 基于关键词的前缀查询
# 例如：查询姓名中以叶开头的
GET /student/_search
{
  "query": {
    "prefix": {
      "name": {
        "value": "叶"
      }
    }
  }
}
```



### （6）通配符查询（wildcard）

```shell
# 通配符查询
# 例如：查询姓名中以叶开头的，且名字只含有两个字的
GET /student/_search
{
  "query": {
    "wildcard": {
      "name": {
        "value": "叶?"
      }
    }
  }
}
# 例如：查询姓名中以叶开头的所有学生
GET /student/_search
{
  "query": {
    "wildcard": {
      "name": {
        "value": "叶*"
      }
    }
  }
}
```

### （7）模糊查询（fuzzy）

![image-20230124193722621](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124193722.png)

```shell
GET /student/_search
{
  "query": {
    "fuzzy": {
      "name":"张思牛逼"
    }
  }
}
```

### （8）布尔查询（must、should、must_not）

![image-20230124193927799](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124193928.png)



### （9）多字段查询muti_match

![image-20230124194432421](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124194432.png)



### （10）默认字段分词查询（query_string）

> 先分词，后查询

![image-20230124194534272](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124194534.png)





### （11）查询结果高亮显示

> 默认

```shell
# 例如：查询姓名中以叶开头的所有学生
GET /student/_search
{
  "query": {
    "term": {
      "name": {
        "value": "张三"
      }
    }
  }
  , "highlight": {
    "fields": {
      "name":{}
    }
  }
  
}
```

运行结果

![image-20230124195251879](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124195252.png)



> 高亮以红色显示，且去掉写题

```shell
GET /student/_search
{
  "query": {
    "term": {
      "name": {
        "value": "张三"
      }
    }
  }
  , "highlight": {
      "pre_tags": ["<span style='color:red;'> "],
      "post_tags": ["</span>"],
      "fields": {
        "name":{}
      }
  }
  
```

运行结果

![image-20230124200115004](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124200115.png)







## 9、ES索引库的底层原理

![image-20230124175439944](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230124175440.png)



（1）索引区：分词结果以及对应的文档id

（2）元数据区：一条条的文档（document）和文档id



## 10、ES索引原理-索引倒排

>  倒排索引（Inverted Index）也叫反向索引

- 正向索引：通过 key  找 value
- **反向索引：通过value 找 key**，在找到完整的文档

- es的索引区里面不仅存储了value->key这样的映射，还存储了每个词出现的次数，和字段的长度

例如：text：蓝月亮洗衣液很高效

==**很：[djsklkj:1:9]  ————> 文档的id：出现的次数：字段长度**==



  









## 11、Docker-Compose 一键启动ES和Kibana

> Docker 脚本一键安装并且启动es 和 kibana





## 12、分词器

### 1、Analysis 和Analyzer

> 文本分析是全文本转换成一系列单词（term/token）的过程，也叫分词（Analyzer）
>
> Analysis是通过Analyzer实现的
>
> 分词就是将文档通过Analyzer分成一个一个的term,每一个term都指向他包含这个term的文档



### 2、Analyzer的组成

在es中有一个标准分词器：StandardAnalyzer，它的特点是：

- 英文：单词分词
- 中文：单字分词

> 分词器（analyzer）是由三部分组成：character filters ，tokenizers，token filters

**（1）Chracter filter 字符过滤器**

```java
<span>hellow </span> ---> hellow
& -->and
```

**（2）tokenizers 分词器**

英文单词可以根据空格进行分隔，中文就比较复杂了，可以采用机器学习算法来进行分词

**（3）token filters Token过滤器**

将切分的单词进行加工。如大写转成小写（将Smart - > smart），去掉停用词（例如：and a the），加入同义词（jump 和 leap）



### 3、ES内置分词器

![image-20230128082240409](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230128082240.png)



### 4、IK中文分词器

> 安装：只需要将从GitHub上下载插件包放到plugins下面，

#### ik分词器支持两种粒度的分词

- Ik_samrt：会做最粗粒度的拆分

- Ik_max_word：将文本做最细粒度的拆分

> ik_smart

```shell
POST _analyze
{
  "analyzer": "ik_word",
  "text":     "中华人民共和国国歌"
}
```

结果

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230128104436.png" alt="image-20230128104435956" style="zoom:50%;" /> 



> ik_max_smart

```shell
POST /_analyze
{
  "analyzer": "ik_max_word",
  "text":     "中华人民"
}
```



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230128104518.png" alt="image-20230128104517634" style="zoom:50%;" /> 



#### 扩展词&停用词

- 扩展词

> 见名之意，有些词不是关键词，但是希望被ES做为关键词的进行检测

- 停用词

> 有些词不希望被ES作为关键词进行检索

- 配置扩展词和停用词（步骤如下）

![image-20230128110006043](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230128110006.png)



## 13、过滤查询（Filter Query）

> 过滤查询：先过滤，后查询，对性能进一步优化
>
> 参考：https://www.elastic.co/guide/en/elasticsearch/reference/7.17/query-filter-context.html#query-filter-context



term fillter

> 关键词过滤

```shell
# 过滤出name含有“张三”的
GET /student/_search
{
  "query": {
    "bool": {
      "must": [
        {"match_all": {}}
      ],
      "filter": [
        {"term": {
          "name": "王五"
        }}
      ]
    }
  }
}
```



range filter

> 过滤出某个范围内的记录

```shell
# 过滤出年龄在10~20岁之间的所有学生
GET /student/_search
{
  "query": {
    "bool": {
      "must": [
        {"match_all": {}}
      ],
      "filter": [
        {"range": {
          "age": {
            "gte": 10,
            "lte": 20
          }
        } }
      ]
    }
  }
}
```



exists fillter

> 过滤出存在某个字段的记录

```shell
# 过滤出设置了年龄的学生
GET /student/_search
{
  "query": {
    "bool": {
      "must": [
        {"match_all": {}}
      ],
      "filter": [
        {
          "exists": {
            "field": "age"
          }
        }
      ]
    }
  }
}
```



ids fillter

> 过滤出含有指定id的记录

```shell
# 过滤出id为1的学生
GET /student/_search
{
  "query": {
    "bool": {
      "must": [
        {"match_all": {}}
      ],
      "filter": [
        {"ids": {
          "values": [
            "1"
          ]
        }}
      ]
    }
  }
}
```



## 14、SpringBoot整合ES

### 1、引入依赖

```xml
<!--引入springboot整合es-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```

### 2、配置客户端`RestClientConfig.java`

- ElasticsearchOperations 始终以面向对象的方式与es进行交互
- RestHighLevelClient （推荐）
- 相关概念：
  - 索引：用来存放相似文档的集合
  - 映射：用来决定放入文档的每个字段以什么样的方式进行录入到es中（字段类型、分词器）
  - 文档：可以被索引的最小单元json 数据格式



```java
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
```



### 3、测试springboot操作es（ElasticsearchOperations方式）

（1）创建实体类，与es进行映射

```java
@Accessors(chain = true)
@Data
@Document(indexName = "product",createIndex = true)
public class Product {
    /**
     * 用来将放入对象的id 值，作为文档_id进行映射
     */
    @Id
    private Integer id;
    @Field(type = FieldType.Text , analyzer="ik_max_word")
    private String titile;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String description;


}
```



2、编写测试类

```java
/**
 * @author: YeRenping
 * @Date: 2023/1/28
 * @Description:
 */
@SpringBootTest
@Slf4j
public class ElasticsearchOprationalTest {

    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ElasticsearchOprationalTest(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    /**
     *  新增一条文档
     *  save()当文档不存在的时候是添加一条文档
     *  save()当文档已经存在的时候是更新一条文档
     */
    @Test
    public void add(){
        Product product = new Product();
        elasticsearchOperations.save(new Product()
                .setId(2)
                .setPrice(300.0)
                .setTitile("北京烤鸭")
                .setDescription("烧鸡")
                );
    }

    /**
     * 删除一条文档
     */
    @Test
    public void del(){
        // 方式1
//        elasticsearchOperations.delete("1", Product.class);
        // 方式2
        elasticsearchOperations.delete(new Product().setId(1));
        // 删除所有文档
        elasticsearchOperations.delete(Query.findAll());
    }


    /**
     * 查询一条文档
     */
    @Test
    public void get(){
        Product product = elasticsearchOperations.get("1", Product.class);
        log.info("product= {}",product);
        // 查询所有文档
        SearchHits<Product> productList = elasticsearchOperations.search(Query.findAll(), Product.class);
        log.debug("productList={}",productList);
        for (SearchHit<Product> productSearchHit : productList) {
            Product content = productSearchHit.getContent();
            log.info("productList={}",content);
        }
    }
}
```



### 4、测试springboot操作es（RestHighLevelClient）

> High Level Client，基于 Low level Client，做了一些封装，比较好用。具体文档见 https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.2/java-rest-high.html。

#### （1）索引操作

```java
@SpringBootTest
@Slf4j
public class RestHighLevelClientForDocumentTest {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientForDocumentTest(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }


    /**
     * 创建索引以及对应的映射
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("products");
        // 参数1：指定json的存储结构
        // 参数2：指定数据的类型
        createIndexRequest.mapping("{\n" + "    \"properties\": {\n" + "      \"title\":{\n"
                + "        \"type\": \"text\", \n" + "        \"analyzer\": \"ik_max_word\"\n" + "      },\n"
                + "      \"price\":{\n" + "        \"type\": \"double\"\n" + "      },\n" + "      \"description\":{\n"
                + "        \"type\": \"text\",\n" + "        \"analyzer\": \"ik_max_word\"\n" + "      },\n"
                + "      \"create_time\":{\n" + "        \"type\": \"date\"\n" + "      }\n" + "    }\n" + "  }",
                XContentType.JSON);
        // 参数1：创建索引的请求对象
        // 参数2：请求配置对象
        CreateIndexResponse createIndexRsp= restHighLevelClient.indices().create(createIndexRequest,
                RequestOptions.DEFAULT);
        log.info("创建后的状态->{}", createIndexRsp.isAcknowledged());

    }


    /**
     * 删除索引
     */
    @Test
    public void delIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("products");
        AcknowledgedResponse deleteRsp = restHighLevelClient.indices().delete(deleteIndexRequest,
                RequestOptions.DEFAULT);
        log.info("删除后的状态->{}", deleteRsp.isAcknowledged());
    }


}

```



#### （2）文档操作

##### 2.1 简单的增删改查

```java
@SpringBootTest
@Slf4j
public class RestHighLevelClientForDocumentTest {

    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public RestHighLevelClientForDocumentTest(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    /**
     * 创建文档
     */
    @Test
    public void saveDoc() throws IOException {

        // 索引的名称
        IndexRequest indexReq = new IndexRequest("products");
        // 文档的内容 id = 1，source内容
        indexReq.id("1")
                .source("{\n" +
                        "  \"title\":\"贺猪猪\",\n" +
                        "  \"price\":\"88.8\",\n" +
                        "  \"description\":\"我的老婆\",\n" +
                        "  \"create_time\":\"2023-01-01\"\n" +
                        "}", XContentType.JSON);
        // 参数1：索引的请求对象 参数2：索引的配置对象
        IndexResponse indexRsp = restHighLevelClient.index(indexReq, RequestOptions.DEFAULT);
        System.out.println(indexRsp.status());
    }

    /**
     * 更新文档
     */
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("products", "1");
        updateRequest.doc("{\n" +
                "    \"title\":\"红色\"\n" +
                "  }",XContentType.JSON);
        UpdateResponse updateRsp = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateRsp.status());
    }

    /**
     * 删除文档
     */
    @Test
    public void delDoc() throws IOException {
        DeleteRequest deleteReq= new DeleteRequest("products","1");
        DeleteResponse deleteRsp = restHighLevelClient.delete(deleteReq, RequestOptions.DEFAULT);
        System.out.println(deleteRsp.status());
    }
    /**
     * 根据id去查询文档
     */
    @Test
    public void queryById() throws IOException {
        GetRequest getRequest = new GetRequest("products", "1");
        GetResponse searchRsp = restHighLevelClient.get(getRequest,RequestOptions.DEFAULT);
        String id = searchRsp.getId();
        String res = searchRsp.getSourceAsString();
        System.out.println(id);
        System.out.println(res);
    }
}
```



##### 2.2 复杂的查询操作

- 查询所有

```java
@Test
public void searchAll() throws IOException {
    SearchRequest searchReq = new SearchRequest("products");
    // 查询条件构造器
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    // 配置查询所有matchAll
    sourceBuilder.query(QueryBuilders.matchAllQuery());
    // 传入“查询条件构造器”
    searchReq.source(sourceBuilder);
    SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);

    // 获取总条数
    long count = searchRsp.getHits().getTotalHits().value;
    log.info("count = {}",count);
    // 获取最大得分max_score
    double maxScore = searchRsp.getHits().getMaxScore();
    log.info("maxScore = {}", maxScore);
    // 获取每一条文档
    SearchHit[] hits = searchRsp.getHits().getHits();
    log.info("-------------------------------");
    for (SearchHit hit : hits) {
        log.info("{}",hit);
    }
}
```



- 范围查询

```java
/**
 * 范围查询
 * @throws IOException
 * GET /products/_search
 * {
 *   "query": {
 *     "range": {
 *       "price": {
 *         "gte": 80,
 *         "lte": 120
 *       }
 *     }
 *   }
 * }
 */
@Test
public void searchByRange() throws IOException {
    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");
    rangeQueryBuilder.gte("80");
    rangeQueryBuilder.lte("120");
    searchByCondition(rangeQueryBuilder);
}
/**
*
* @param queryBuilder 查询条件
* @throws IOException
*/
private  void searchByCondition(QueryBuilder queryBuilder) throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        // 查询条件构造器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 配置查询所有matchAll
        sourceBuilder.query(queryBuilder);
        // 传入“查询条件构造器”
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }
```

- 通配符wildcard 查询

```java
/**
 *  通配符wildcard 查询
 * GET /products/_search
 * {
 *   "query": {
 *     "wildcard": {
 *       "title": {
 *         "value": "贺*"
 *       }
 *     }
 *   }
 * }
 * }
 * @throws IOException
 */
@Test
public void searchByWildcard() throws IOException {
    WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("title", "贺*");
    searchByCondition(wildcardQueryBuilder);
}
```



- 布尔查询

```java
/**
 * # 布尔查询
 * # 标题里面必须带有猪猪
 * GET /products/_search
 * {
 *   "query": {
 *     "bool": {
 *       "must": [
 *         {
 *           "term": {
 *             "title": {
 *               "value": "猪猪"
 *             }
 *           }
 *         }
 *       ]
 *     }
 *   }
 * }
 * @throws IOException
 */
@Test
public void searchByBoolMust() throws IOException {
    BoolQueryBuilder mustQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("title", "猪猪"));
    searchByCondition(mustQueryBuilder);
}
```

##### 2.3 分页查询

```java
/**
 * 分页查询 -查询所有（每页显示10条）
 * GET /products/_search
 * {
 *   "query": {
 *     "match_all": {}
 *   }
 *   , "from": 0
 *   , "size": 10
 * }
 */
@Test
private  void searchPage() throws IOException {
    SearchRequest searchReq = new SearchRequest("products");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.matchAllQuery())
            .from(0) // 起始页
            .size(10); // 每页显示的条数
    searchReq.source(sourceBuilder);
    SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
    // 输出查询到的所有文档
    for (SearchHit hit : searchRsp.getHits().getHits()) {
        System.out.println(hit);
    }
}
```

##### 2.4 查询结果高亮显示

```java
/**
     * # 高亮查询结果
     * GET /products/_search
     * {
     *   "query": {
     *     "term": {
     *       "description": {
     *         "value": "老婆"
     *       }
     *     }
     *   }
     *   , "highlight": {
     *       "pre_tags": ["<span style='color:red;'> "],
     *       "post_tags": ["</span>"],
     *       "fields": {
     *         "name":{}
     *       }
     *   }
     * }
     * @throws IOException
     */
    @Test
    public void searchHightLight() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 高亮字段
        highlightBuilder.field("description");
        // 红色标签
        highlightBuilder.preTags("<span style='color:red;'> ");
        highlightBuilder.postTags("</span>");
        sourceBuilder.query(QueryBuilders.termQuery("description", "老婆"))
                .highlighter(highlightBuilder);
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }
```

##### 2.5 查询结果排序

```java
    /**
     * # 对查询结果进行排序sort
     * # asc 升序 desc 降序
     * GET /products/_search
     * {
     *   "query": {
     *     "match_all": {}
     *   }
     *   , "sort": [
     *     {
     *       "price": {
     *         "order": "asc"
     *       }
     *     }
     *   ]
     * }
     * @throws IOException
     */
    @Test
    public void searchBySort() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery())
                .sort("price",SortOrder.ASC);
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }
```







##### 2.6 返回需要的指定字段

```java
/**
 * # 返回想要的指定字段
 *
 * GET /products/_search
 * {
 *   "query": {
 *     "match_all": {}
 *   }
 *   , "_source": ["title","description"]
 * }
 * @throws IOException
 */
@Test
public void searchWantField() throws IOException {
    SearchRequest searchReq = new SearchRequest("products");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    String[] fields = {"title","description"};
    sourceBuilder.query(QueryBuilders.matchAllQuery())
            .fetchSource(fields,new String[]{}); // include  包含  exludes 排除
    searchReq.source(sourceBuilder);
    SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
    // 输出查询到的所有文档
    for (SearchHit hit : searchRsp.getHits().getHits()) {
        System.out.println(hit);
    }
}
```





##### 2.7 过滤查询（Fillter Search）

> 在大量数据中进行筛选出本次查询相关的数据

```java
/**
     *# filter query 过滤查询： 先过滤 后查询
     * # 过滤查询 ，price在80~120之间的 description带有“老婆”的
     * GET /products/_search
     * {
     *   "query": {
     *     "term": {
     *       "description": {
     *         "value": "老婆"
     *       }
     *     }
     *   }
     *   , "post_filter": {
     *     "range": {
     *       "price": {
     *         "gte": 80,
     *         "lte": 120
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public  void filterQuery() throws IOException {
        SearchRequest searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 过滤查询
        sourceBuilder.postFilter(QueryBuilders.rangeQuery("price").gte(80).lte(120))
                .query(QueryBuilders.termQuery("description", "老婆"));
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);
        // 输出查询到的所有文档
        for (SearchHit hit : searchRsp.getHits().getHits()) {
            System.out.println(hit);
        }
    }
```





```
UserTalkConfigCacheImpl
```





#### （3）Java对象存入到ES

```java
/**
 * 将对象放入es中
 */
@Test
public void addObject() throws IOException {
    Product lyl = new Product().setId(1).setTitile("蓝牙亮").setDescription("洗白白").setPrice(19.9);
    // 录入es
    IndexRequest indexReq = new IndexRequest("products");
    indexReq.id(lyl.getId().toString()) // 文档id
            .source(new ObjectMapper().writeValueAsString(lyl), XContentType.JSON); // 将对象转成json
    IndexResponse indexRsp = restHighLevelClient.index(indexReq, RequestOptions.DEFAULT);

}
```

#### （4）ES中文档转化成对象

```java
/**
     * ES中文档转化成对象（含高亮的结果跑）
     */
    @Test
    public void JsonToObject() throws IOException {
        SearchRequest products = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("description", "白白"))
                .from(0)
                .size(20)
                .highlighter(new HighlightBuilder().preTags("<span style='color:red;'> ").postTags("</span>").field("description"));
        products.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(products, RequestOptions.DEFAULT);
        // 获取json数据
        SearchHit[] hits = search.getHits().getHits();
        List<Product> list = Arrays.stream(hits)
                .map(new Function<SearchHit, Product>() {
                    @Override
                    public Product apply(SearchHit documentFields) {
                        String sourceAsString = documentFields.getSourceAsString();
                        Product product = JSON.parseObject(sourceAsString, Product.class);
                        // 处理高亮
                        Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
                        if (highlightFields.containsKey("description")) {
                            String description = highlightFields.get("description").fragments()[0].toString();
                            product.setDescription(description);
                        }
                        return product;
                    }
                })
                .collect(Collectors.toList());
        System.out.println(list);
    }
```

![image-20230130093538292](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230130093538.png)



## 15、聚合查询Aggs

### 1、介绍

![image-20230130132053080](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230130132053.png)

### 2、聚合查询DSL

#### 2.1 根据价格进行分组

```java

GET /products/_search
{
  "query": {
    "match_all": {}
  }
  , "aggs": {
    "price_group": {
      "terms": {
        "field": "price",
        "size": 10
      }
    }
  }
}
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230130225924.png" alt="image-20230130225924270" style="zoom:50%;" /> 

> 代码实现

```java
@Test
public void testAggregation() throws IOException {
    SearchRequest  searchReq = new SearchRequest("products");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    //用来设置聚合处理
    sourceBuilder.aggregation(AggregationBuilders.terms("price_group").field("price"))
            .size(0);
    searchReq.source(sourceBuilder);
    SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);

    // 处理聚合的结果
    Aggregations aggregations = searchRsp.getAggregations();
    ParsedDoubleTerms parsedDoubleTerms = aggregations.get("price_group");
    List<? extends Terms.Bucket> buckets = parsedDoubleTerms.getBuckets();
    buckets.stream().forEach(new Consumer<Terms.Bucket>() {
        @Override
        public void accept(Terms.Bucket bucket) {
            double key = (double)bucket.getKey();
            long docCount = bucket.getDocCount();
            log.info("key = {}, docCount = {}",key,docCount);
        }
    });
}
```

 

#### 2.2  求最大值

```
GET /products/_search
{
  "query": {
    "match_all": {}
  }
  , "aggs": {
    "max_price": {
      "max": {
        "field": "price"
      }
    }
  }
}
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230130230020.png" alt="image-20230130230020376" style="zoom:50%;" /> 

> 代码实现-同 求平均价值

#### 2.3 求最小值

```java
GET /products/_search
{
  "query": {
    "match_all": {}
  }
  , "size": 0
  , "aggs": {
    "min_price": {
      "min": {
        "field": "price"
      }
    }
  }
}
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230130230058.png" alt="image-20230130230058372" style="zoom:50%;" /> 

> 代码实现-同 求平均价值

#### 2.4 求平均值

```java
GET /products/_search
{
  "query": {
    "match_all": {}
  }
  , "size": 0
  , "aggs": {
    "avg_price": {
      "avg": {
        "field": "price"
      }
    }
  }
}
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20230130230139.png" alt="image-20230130230139436" style="zoom:50%;" /> 



> 代码实现

```java
/**
     * 聚合操作 求平均值
     * @throws IOException
     */
    @Test
    public void testAggregationGetMax() throws IOException {
        SearchRequest  searchReq = new SearchRequest("products");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //用来设置聚合处理
        sourceBuilder
                .query(QueryBuilders.matchAllQuery())
                .aggregation(AggregationBuilders.avg("avg_sum").field("price"))
                .size(0);
        searchReq.source(sourceBuilder);
        SearchResponse searchRsp = restHighLevelClient.search(searchReq, RequestOptions.DEFAULT);

        // 处理聚合的结果
        Aggregations aggregations = searchRsp.getAggregations();
        ParsedAvg priceMax = aggregations.get("avg_sum");
        System.out.println(priceMax.getValue());
    }
```



## 16、ES集群(Cluster)

### 1、核心概念

> 集群cluster

一个集群是由一个或多个节点组织在一起，他们共同持久你的整个数据，并一起提供索引和搜索功能

单节点存在的问题：

1、物理压力问题（存储）

2、单节点发生故障

3、并发压力



> 节点node

一个节点就是es集群中的一台服务器，一个节点有一个名字

> 索引index

一组相识文档的集合

> 映射mapping

文档的存储结构

> 文档doucument

可以被索引的最小单元

> 分片shards

- es提供将索引划分成多份的能力

- 当你创建索引的时候，你可以指定你想要创建的分片数量
- 每个分片本身也是一个功能完善且独立的“索引”

- 这个“索引”可以放置到es集群中任意的节点上

> 复制 replicas

index分片中的一份或多份副本



### 2、集群搭建

2.1 集群规范





































































