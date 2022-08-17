# 1、MySQL逻辑架构

![image-20220802164851008](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802164851.png)



# 2、存储引擎

https://www.bilibili.com/video/BV1wV411h7Fb/?spm_id_from=333.788&vd_source=34ebfc8e3ab7f2caf1d123d472a401ab

1. InnoDB （行锁）
2. MYISAM (表锁)

![image-20220802165448819](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802165448.png)

![image-20220806112831684](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806112831.png)



![image-20220806112901432](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806112901.png)



![image-20220806112951364](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806112951.png)



![image-20220806113137510](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806113137.png)

> myisam
>
> 非聚簇索引

![image-20220806113341076](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806113341.png)



> innodb
>
> 聚簇索引

![image-20220806161311025](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806161311.png)





### **2.1 MyISAM** **与** **InnoDB对比**



**MyISAM的索引方式都是“非聚簇”的，与InnoDB包含1个聚簇索引是不同的。**

**小结两种引擎中索引的区别：**

① 在InnoDB存储引擎中，我们只需要根据主键值对`聚簇索引`进行一次查找就能找到对应的记录，而在`MyISAM`中却需要进行一次`回表`操作，意味着MyISAM中建立的索引相当于全部都是`二级索引`。 

② InnoDB的数据文件本身就是索引文件，而MyISAM索引文件和数据文件是`分离的`，索引文件仅保存数据记录的地址。

③ InnoDB的非聚簇索引data域存储相应记录`主键的值`，而MyISAM索引记录的是`地址`。换句话说，InnoDB的所有非聚簇索引都引用主键作为data域。

④ MyISAM的回表操作是十分`快速`的，因为是拿着地址偏移量直接到文件中取数据的，反观InnoDB是通过获取主键之后再去聚簇索引里找记录，虽然说也不慢，但还是比不上直接用地址去访问。

⑤ InnoDB要求表`必须有主键`（`MyISAM可以没有`）。如果没有显式指定，则MySQL系统会自动选择一个可以非空且唯一标识数据记录的列作为主键。如果不存在这种列，则MySQL自动为InnoDB表生成一个隐含字段作为主键，这个字段长度为6个字节，类型为长整型。









# 3、性能下降SQL慢

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802170556.png" alt="image-20220802170556563" style="zoom:50%;" /> 

 

# 4、Join查询

## **4.1 SQL的执行顺序**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802173302.png" alt="image-20220802173302766" style="zoom:50%;" /> 



![image-20220802174011470](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802174011.png)



## **4.2 七中join连接**

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802174115.png" alt="image-20220802174115625" style="zoom:50%;" /> 



### （1） 内连接

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802174841.png" alt="image-20220802174841149" style="zoom: 33%;" /> 

 

```mysql
select * from tableA A inner join tableB B on A.key = B.key
```



### （2）左连接

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802174938.png" alt="image-20220802174938242" style="zoom:33%;" /> 



```mysql
select * from tableA A left join tableB B on A.key = B.key
```



### （3）右连接

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802175010.png" alt="image-20220802175010866" style="zoom:33%;" /> 



```mysql
select * from tableA A right join tableB B on A.key = B.key
```



### （4）左连接-掏空

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802175532.png" alt="image-20220802175532209" style="zoom:33%;" /> 



```mysql
select * from tableA A left join tableB B on A.key = B.key
where B.key is null
```



### （5）右连接-掏空

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802175626.png" alt="image-20220802175625994" style="zoom:50%;" /> 



```mysql
select * from tableA A right join tableB B on A.key = B.key
where A.key is null
```

### （6）全连接



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802175852.png" alt="image-20220802175852619" style="zoom:33%;" /> 



```mysql
select * from tableA A full outer join tableB B on A.key = B.key
```



### （7）全连接-掏空

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802180057.png" alt="image-20220802180057656" style="zoom:33%;" /> 

```mysql
select * from tableA A full outer join tableB B on A.key = B.key
where A.key is null or B.key is null 
```

## 4.2 7种SQL语句的编写

> 建表语句

```sql
CREATE TABLE `tbl_emp` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(20) DEFAULT NULL,
`deptId` int(11) DEFAULT NULL,
PRIMARY KEY (`id`) ,
KEY `fk_dept_id`(`deptId`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

CREATE TABLE `tbl_dept` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`deptName` varchar(30) DEFAULT NULL,
`locAdd` varchar(40) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8;

insert into tbl_dept(deptName,locAdd) values('RD',11); 
insert into tbl_dept(deptName,locAdd) values('HR',12);
insert into tbl_dept(deptName,locAdd) values('MK',13);
insert into tbl_dept(deptName,locAdd) values('MIS',14);
insert into tbl_dept(deptName,locAdd) values('FD',15);

insert into tbl_emp(NAME,deptId) values('z3',1);
insert into tbl_emp(NAME,deptId) values('z4',1);
insert into tbl_emp(NAME,deptId) values('z5',1);
insert into tbl_emp(NAME,deptId) values('w5',2);
insert into tbl_emp(NAME,deptId) values('w6',2);
insert into tbl_emp(NAME,deptId) values('s7',3);
insert into tbl_emp(NAME,deptId) values('s8',4);
insert into tbl_emp(NAME,deptId) values('s9',51);
```



### （1）内连接

```sql
-- 内连接
select * from tbl_emp a  inner join  tbl_dept b
on a.deptId = b.id; 
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802182010.png" alt="image-20220802182010729" style="zoom:50%;" /> 

==结论：内连接是得到两表的公共部分，也是交集部分（两圆交集部分）==



### （2）左内连接

```sql
select * from tbl_emp a left join tbl_dept b
on a.deptId = b.id; 
```



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802182736.png" alt="image-20220802182736291" style="zoom:50%;" /> 



==结论：内连接是得到两表的公共部分，也是交集部分（两圆交集部分），以及A表的独有部分==



### （3） 右内连接

```sql
select * from tbl_emp a right join tbl_dept b
on a.deptId = b.id; 
```



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802182934.png" alt="image-20220802182934833" style="zoom:50%;" /> 



==结论：内连接是得到两表的公共部分，也是交集部分（两圆交集部分），以及B表的独有部分==

### （4）左连接-掏空

```sql
-- 4、左连接-掏空 (A的独有部分)
select * from tbl_emp a left join tbl_dept b
on a.deptId = b.id
where b.id is null;
```



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802183432.png" alt="image-20220802183432747" style="zoom:50%;" /> 



==结论：要求得A表的独有部分，则B的key一定是null==

### （5）右连接-掏空

```sql
select * from tbl_emp a right join tbl_dept b
on a.deptId = b.id
where a.deptId is null;
```



![image-20220802183920342](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802183920.png)

==结论：要求得B表的独有部分，则A的key一定是null==



### （6）全连接

```sql
-- 6、全连接(MySQL不支持)
select * from tbl_emp a full outer join tbl_dept b
on a.deptId = b.id

-- 6.1 左内连接 +union+  右内连接
-- union 合并加去重的功能
select * from tbl_emp a left join tbl_dept b
on a.deptId = b.id
union
select * from tbl_emp a right join tbl_dept b
on a.deptId = b.id; 
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802184918.png" alt="image-20220802184918805" style="zoom:33%;" /> 

### （7）全连接-掏空

 ```sql
 -- 7.1  A的独有 +union+ B的独有
 -- A的独有（左连接-掏空）
 select * from tbl_emp a left join tbl_dept b
 on a.deptId = b.id
 where  b.id is null
 union
 -- A的独有（右连接-掏空）
 select * from tbl_emp a right join tbl_dept b
 on a.deptId = b.id
 where a.deptId is null;
 ```

 <img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220802185731.png" alt="image-20220802185731555" style="zoom: 50%;" /> 

 



# 5、索引

## 前置知识（b树 b+树）

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806100249.png" alt="image-20220806100249308" style="zoom:50%;" /> 





![image-20220806100814531](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806100814.png)



>  B+树形成

![image-20220806101648308](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806101648.png)



- **==问题一：为什么b+树的高度一般都不会超过4层呢？==**

① 树的层次越低，我们IO的次数就越少

![image-20220806102202110](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806102202.png)





![image-20220806142927719](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806142927.png)









### 问题： MySQL索引为什么采用b+树？而不是hash，平衡二叉树，b树，红黑树

> Closed Hash Tables (Open Addressing)

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806182540.png" alt="image-20220806182539983" style="zoom: 33%;" /> 

 

1、hash的值是无序的，因此hash索引只能满足（=）（!=、<>）和(in)查询，而不能进行`range查询`（> <），如果进行范围查询则退化为O(n)复杂度；**而树型的“有序”特性，依旧能够保持到O(long2n)**

2、hash的值是无序的，也就是数据存储是没有顺序的，在order by 的情况下，使用hash索引还需要对数据进行重新排序

3、对于联合索引的情况，Hash值是将联合索引合并后一起来计算的，无法对单独的一个键或者几个索引键进行查询

3、加入要查找某个数，值相同且个数有很多（等值查询且个数很多 ），但是由于hash值不相同，查找的效率就很低



![image-20220806184258527](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806184258.png)

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806184428.png" alt="image-20220806184428494" style="zoom:50%;" /> 









> AVL Trees (Balanced binary search trees)

1、查找速度与树的层次有关，树的层次越多，查找的效率越低

2、例如查询>5的数，**回旋查找**数字效率很低



> B Trees
>
> 特点：
>
> 1、一个节点可以存在两个值（降低高度）

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806192741.png" alt="image-20220806192741513" style="zoom:33%;" /> 



1、优点：数的层次减少，查找速度变快（avl tree -> b tree）

==2、依旧没有解决回旋查找问题，例如查询>5的数，**回旋查找**数字效率很低==



> B+ 树

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806192959.png" alt="image-20220806192959182" style="zoom: 33%;" /> 



> 特点：
>
> 1、一个节点可以存在两个值（降低高度）
>
> 3、非叶子节点只存储key，不存储value
>
> 4、叶子节点即存储key，又存储value

==彻底解决回旋查找问题，例如查询>5的数，**回旋查找**数字效率很低==





### **问题2：为了减少io，索引会一次加载到内存中吗？**

![image-20220806221103056](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806221103.png)



### **问题3：b+树的存储能力如何？为什么说一般查询行记录，只要1~3次磁盘io即可？**

![image-20220807085449639](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220807085449.png)



### **问题4：为什么说B+树比B树更适合实际应用中操作系统的文件索引和数据库索引？**

![image-20220807085847780](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220807085847.png)



### **问题5：Hash索引与B+ 树索引的区别？**

![image-20220807090559178](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220807090559.png)

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220807090716.png" alt="image-20220807090716289" style="zoom:50%;" /> 





## 5.1 定义

> 索引是帮助MySQL高效获取数据的数据结构
>
> 索引的本质：索引是数据结构



## 5.2 索引存在的目的

> 索引存在的目的是为了提高查找效率，可以可以类比字典（找某个字，通过索引就很快了）
>
> Where 后面
>
> order by 后面的



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803080340.png" alt="image-20220803080340472" style="zoom:50%;" /> 



**==索引可以理解为“一种排好序的快速查找数据结构”==**

![image-20220803081058378](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803081058.png)



![image-20220803081359108](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803081359.png)



## 5.3 为什么公司采用逻辑删除？而不真正的删除数据？

- 为了维护索引
- 为了保留历史操作记录（便于数据分析、行为分析）



## 5.4 索引的优势

1、提高数据的检索效率（查找）

2、降低数据的排序成本（排序）



## 5.5 索引的劣势

![image-20220803111214438](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220806170059.png)

## 5.5 索引的代价



## 5.6 索引的分类

### 1、单值索引

一个索引包含一个单值，一个表可以包含有多个**单值索引**



### 2、唯一索引

索引值必须唯一，但允许有空值



### 3、复合索引

一个索引包含了多个列



## 5.7 索引的基本语法

1、创建

```sql
--           索引名		    表名         字段名
create index indexName on myTable(columnname(length))

alter myTable add index[indexName]  on (columname(length))
```



2、删除

```sql
drop index[indexName]  on MyTable
```



3、查看

```sql
show index from MyTable
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803112441.png" alt="image-20220803112441588" style="zoom:50%;" /> 



4、修改索引

![image-20220803112542921](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803112543.png)

## 5.8 MySQL索引结构

> **1、Btree索引（重点）**
>
> 2、Hash索引（了解）
>
> 3、full-text 全文索引（了解）
>
> 4、R-Tree索引（了解）



## 5.9 那些情况下需要创建索引？

![image-20220803114939714](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803114939.png)



## 5.10 那些情况不需要创建索引？

1、表记录太少（1w，2w）

2、经常增 删 改 的表

3、数据==重复（重复太多）==且 ==分布平均== 的表字段创建索引意义不大

 

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803115316.png" alt="image-20220803115316573" style="zoom:50%;" /> 



## 5.11 索引的代价

索引是个好东西，可不能乱建，它在空间和时间上都会有消耗：

- **空间上的代价**

每建立一个索引都要为它建立一棵B+树，每一棵B+树的每一个节点都是一个数据页，一个页默认会占用`16KB`的存储空间，一棵很大的B+树由许多数据页组成，那就是很大的一片存储空间。

- **时间上的代价**

每次对表中的数据进行`增、删、改`操作时，都需要去修改各个B+树索引。而且我们讲过，B+树每层节点都是按照索引列的值`从小到大的顺序排序`而组成了`双向链表`。不论是叶子节点中的记录，还是内节点中的记录（也就是不论是用户记录还是目录项记录）都是按照索引列的值从小到大的顺序而形成了一个单向链表。而增、删、改操作可能会对节点和记录的排序造成破坏，所以存储引擎需要额外的时间进行一些`记录移位`，`页面分裂`、`页面回收`等操作来维护好节点和记录的排序。如果我们建了许多索引，每个索引对应的B+树都要进行相关的维护操作，会给性能拖后腿。





# 6、性能分析

- MySQL Query Optimizer

![image-20220803120203947](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803120204.png)

- MySQL常见的瓶颈

![image-20220803120318381](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220803120318.png)



# 7、Explain

## （1）使用方法

```sql
explain + sql语句
```



## （2）目的

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804173431.png" alt="image-20220804173431841" style="zoom:50%;" /> 

## （3）explain执行后的表头分析

![image-20220804173714308](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804173714.png)

### 3.1 id

![image-20220804174720573](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804174720.png)



### 3.2 select_type

> 主要区别：普通查询、联合查询、子查询等复杂查询

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804174923.png" alt="image-20220804174923594" style="zoom:50%;" /> 

#### （1） SIMPLE

简单的select查询，查询中不包含子查询或者union



#### （2） PRIMARY

查询中若包含任何复杂的子部分，最外层的查询则标记为PRIMARY



#### （3） SUBQUERY





子查询，也就是括号里的

![image-20220804175254009](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804175254.png)



#### （4） DERIVED

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804175417.png" alt="image-20220804175417014" style="zoom:50%;" /> 



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804175553.png" alt="image-20220804175553715" style="zoom:50%;" />



#### （5） **UNION**

![image-20220804175640984](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804175641.png)

#### （6）UNION RESULT

从UNION表中获取结果的SELECT

![image-20220804180251825](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804180252.png)



### 3.3 table

指的是当前操作的表名



### 3.4 type

显示查询使用了**何种类型**，主要有以下类型

![image-20220804180453753](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804180453.png)

- **==从最好到最差依次是system > const > eq_ref > ref > range > index > ALL==**

ALL :  全表扫描（eplain select * from emp）



#### （1）system

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804180916.png" alt="image-20220804180916641" style="zoom:50%;" /> 

#### （2）const

![image-20220804181007461](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804181007.png)

![image-20220804181546077](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804181546.png)



#### （3） eq_ref

![image-20220804181659839](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804181700.png)



#### （4）ref

![image-20220804184441503](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804184441.png)



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804184819.png" alt="image-20220804184819468" style="zoom:50%;" /> 

#### （5）range

![image-20220804184929362](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804184929.png)

![image-20220804184955195](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804184955.png)



#### （6）index

> 全索引扫描

![image-20220804185117447](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804185117.png)



![image-20220804185213794](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220804185213.png)



#### （7）ALL

> 全表扫描
>
> 如果百万级别的数据出现了all，则需要进行优化



### 3.5 possible_keys

> 显示可以应该在这张表上的索引，一个或多个
>
> 查询涉及到的字段上若存在索引，则该索引将被列出，==但不一定被查询实际使用==

![image-20220805093558776](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805093558.png)



### 3.6 key

> 实际上用到的索引



### 3.7 key_len

![image-20220805095542077](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805095542.png)



### 3.8 ref

![image-20220805100537727](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805100537.png)

![image-20220805103742708](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805103742.png)

### 3.9 rows

![image-20220805104206041](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805104206.png)

3.10 Extra 

> 包含不合适在其他列显示，但十分重要的信息

### （1）Using filesort

![image-20220805153451197](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805153451.png)

![image-20220805152555510](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805152555.png)

### ==（1）Using temporaory==

![image-20220805153439404](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805153439.png)



### ==（2）Using index==

![image-20220805194558895](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805194559.png)



![image-20220805195323062](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805195323.png)



### ==（3）Covering Index==

> 覆盖索引

![image-20220805203952661](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805203952.png)

![image-20220805204121733](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805204121.png)



### （4）Using where

> 表名使用了where 过滤



### （5）using join buffer

> 当join 特别较多的时候，使用了缓存

### （6） impossible where 

> where 字句的值总是false，不能用来获取任何元组

![image-20220805204955430](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805204955.png)



### （7） select tables optimized away

![image-20220805205028723](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805205028.png)



### （8）distinc 

![image-20220805205125524](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805205125.png)



## （4）expain的应用

![image-20220805213615010](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220805213615.png)

# 8、索引优化



- 单表

![image-20220807095732976](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220807095733.png)

![image-20220807100110810](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220807100111.png)





- 两表连接

> 左连接

![image-20220809094301269](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809094301.png)



![image-20220809095350023](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809095350.png)



> 右连接

```sql
-- 左连接
-- 左连接索引应该建立在右表上
alter table book add index Y(`card`);

explain select * from class left join book 
on class.card = book.card;


-- 右连接
-- 右连接索引应该建立在左表
alter table class add index X(`card`);
explain select * from class right join book 
on class.card = book.card;
```



- 三连连接

> 没有优化的效果

![image-20220809105016199](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809105016.png)



> 创建两个索引进行优化

```sql
alter table book add index X(`card`);
alter table phone add index Y(`card`);
```



![image-20220809105336000](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809105336.png)





# 9、索引失效

> 1、带头大哥不能四
>
> 2、中间兄弟不能断
>
> 3、索引列上无计算
>
> 4、like%加右边
>
> 5、范围之后全失效
>
> 6、字符串里有引号
>
> 

![image-20220809110551843](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809110552.png)

![image-20220809110752889](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809110753.png)

## 1、全局匹配我最爱（带头大哥不能死）

```sql
explain select * from staffs where name = 'July';
explain select * from staffs where name = 'July' and age = 25;
explain select * from staffs where name = 'July' and age = 25 and pos = 'dev'


```



## 2、最佳左前缀原则（中间兄弟不能断）

```sql
-- 只用到一个索引，因为违背了MySQL的最佳左前缀原则（中间兄弟不能断）
explain select * from staffs where name = 'July'  and pos = 'dev'
```



## 3、不在索引列上做任何操作（计算、函数、（自动or手动）类型转换），这些都会导致索引失效

![image-20220809171307163](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809171307.png)



## 4、MySQL不能使用索引中范围右边的列

> 范围之后都失效

![image-20220809172229442](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809172229.png)



## 5、尽量使用覆盖索引，减少`select * `

```sql
explain select name,age,pos from staffs where name = 'July' and age = 25 and pos = 'dev'
```

![image-20220809172906868](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809172907.png)



## 6、MySQL在使用不等于（！= 或<>）的时候无法使用索引，会导致全表扫描

```sql
explain select * from staffs where name != 'July';
explain select * from staffs where name <> 'July';
```



![image-20220809174059603](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809174059.png)



## 7、`is null , is not null`也会导致索引失效



![image-20220809174427753](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809174427.png)



![image-20220809174349530](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809174349.png)



## 8、like以通配符(%abc..)，MySQL索引会失效

![image-20220809174713139](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809174713.png)



![image-20220809174740044](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809174740.png)



![image-20220809174932489](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220809175038.png)



- ==怎么解决(%abc..)索引失效的问题呢？加入一定要用(%name%)？==

答：可以使用覆盖索引解决问题，例如上面创建了一个name,age,pos的索引，我们可以使用下面的sql让索引依旧生效

```sql
-- 解决双%问题，使用覆盖索引
explain select id,name,age,pos from staffs where name like '%July%';
```

![image-20220810105713911](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220810105714.png)



## 9、字符串不加单引号会导致索引失效

![image-20220810110539120](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220810110539.png)

## 10、少用or ，否则容易索引失效

![image-20220810112448360](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220810112448.png)





> 例题1

![image-20220810113044444](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220810113044.png)



![image-20220812212658021](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220812212658.png)





# 11、查询截取分析





![image-20220815094155392](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815134349.png)

## （1）Order By

![image-20220815104106040](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815104106.png)





<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815105153.png" alt="image-20220815105153358" style="zoom:50%;" /> 





## （2）Group By

均和order一样

# 12 慢查询日志

![image-20220815105525794](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815105526.png)



- 查询状态

```sql
 show variables like '%slow_query_log%';
```



![image-20220815110322739](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815110322.png)



- 开启慢查询日志

```sql
set global slow_query_log = 1;
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815110506.png" alt="image-20220815110506731" style="zoom:50%;" /> 

- 永久开启慢查询日志

![image-20220815110627140](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815110627.png)



- 设置超时时间

```sql
set global long_query_time = 3;
```



- 查询超时时间

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815112302.png" alt="image-20220815110850537" style="zoom:50%;" /> 



```sql
show variables like 'long_query_time%';
```

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815112239.png" alt="image-20220815112239095" style="zoom:50%;" /> 





<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815114319.png" alt="image-20220815110850537" style="zoom:50%;" /> 



- 查询日志

![image-20220815114230428](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815114308.png)



- 分析工具mysqldumpslow

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815113843.png" alt="image-20220815113843176" style="zoom:50%;" /> 

 

```sql
mysqldumpslow --help
```



# 13、mysql锁机制

- 查看锁

```sql
 show open tables;
```

- 加锁

```sql
lock table mylock read,book write;
```

- 解锁

```sql
unlock tables;
```



> 例子：在session1中给mylock表加上read锁

这个时候，session1可以度mylock表，但是不能修改mylock表。

此刻，session1中只能执行度mylock表操作，不能执行任何update、read其他表操作

而在session2中：可以读取mylock表，也能读取其他表和修改其他表，但是不能修改mylock表



> 例子：在session1中给mylock表加上write锁

在session1中：可以对该表进行修改 + 插入 + 更新操作

在session2中：不可以对该表做任何操作，例如查询该表，必须先等待write锁释放



## （1）表锁



## （2）行锁

![image-20220816111559657](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220816111559.png)

## （3）页锁

- 无索引行锁升级为表锁



- 间隙锁的危害

![image-20220815203835318](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815203835.png)



```sql
show status like 'innodb_row_lock%';
```

![image-20220816111910034](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220816111910.png)





# 14、并发事务带来的问题

## 1、更新丢失

![image-20220815190605662](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815190605.png)

## 2、脏读

![image-20220815190634387](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815190634.png)





## 3、不可重复读

![image-20220815190651197](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815190651.png)

## 4、幻读

![image-20220815190735378](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815190735.png)





# 15、数据库中四大特性ACID



-  **原子性（Atomicity):**

事务是一个不可分割的单位，是一个最小的操作单元；这个单元的操作要么全部成功，要么全部不成功。如果某一个SQL语句执行失败了，那么执行回滚操作。实现原理：基于**Undo log**。Undo log会记录之前所有操作，一旦发生回滚，数据库就会根据Undo log做相反的操作，比如记录的是insert，那么数据库便会进行delete操作；我之前指定的是一个delete操作，那么就会执行一个相反的操作insert



-  **一致性（Consistency)**

事务执行之后，数据库的完整性约束没有被破坏，事务执行前后都是一个合法的数据状态。完整性体现在比如数据库的主键要唯一，字段类型大小要符合要求，外键的约束要符合要求。一致性是事务追求的最终目标。原子性、持久性、隔离性都是为了保证数据库最终的一致性。如果另外三个特性无法保证，那么一致性肯定也保证不了



- **隔离性（Isolation)**

写写操作：通过锁机制，保证当前只能有一个事务来操作某个数据。

写读操作：MVCC

读操作会存在：脏读、不可重复度、幻读



- **持久性（Durability)**

**背景知识：**

MySQL的数据是存储在磁盘中的，假如每次度取数据都要磁盘的io，那么效率会很低，因此MySQL的搜索引擎innodb提供了缓存buffer，这个buffer包含了磁盘中部分数据页的映射，作为访问数据的缓冲。

- 当需要从数据库中读取数据的时候，就会先从这个buffer中找，是否存在该数据，如果存在，直接返回。如果不存在，才从磁盘中读取，在读取完的同时会备份一份到buffer缓存中

- 当向数据库中写入数据的时候也是同样的处理方式，首先会将数据写入buffer，然后定期将buffer中的数据刷新到磁盘上，进行持久化的操作

 ==存在问题：存在buffer中的数据还没有来的及同步到磁盘上，这个时候MySQL宕机了，那么buffer中的数据就会丢失掉，进而造成数据的丢失，数据丢失了，持久性的就无法保障了，就是是因为上面这个背景，才引入了redo log==



实现原理：redo log。当数据库要进行新增和修改的时候，除了要把数据写到Buffer中去，还会把操作记录到redo log里面，如果Mysql 宕机了，那么可以通过Redo log去恢复数据。Redo log是**预写式日志**，这个预写式日志是什么意思呢？会把新增、修改（操作）先写入到Redo log中，再更新到Buffer中。

==执行流程：对数据进行操作 -> redo log  -> buffer==

 ==存在问题：为什么加入Redo log 比 直接写入到buffer中快呢？==

​    1.因为buffer中的数据持久化是**随机写的IO**，每次修改的数据位置随机。Redo log是属于**追加模式**的，在文件尾部去追加，它是属于一种顺序IO的操作，例如像kafka这个信息队列，就是采用这种追加模式。

​    2.MySQL数据页大小默认是16k,每执行一个小小的修改，都要把整页的数据重新写入。而Redo log只需要写入真正需要的部分就可以了，无效的IO就大大减少了。所以Redo log要比buffer同步数据快很多。



==存在问题：redo log 什么时候会真正同步到磁盘里去呢？==

redo log在没有同步到磁盘之前是存在与缓存区中，这个缓存区叫redo log缓冲区，他说放到这个缓存区里的，





# 16、事务的隔离级别

![image-20220815191014258](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220815191014.png)







# 17、MySQL读写分离

>  实现方式：通过多台MySQL数据库搭建：主从复制

- 原始状态

![image-20220723172506679](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723172506.png)



- 读写分离

![image-20220723173520933](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723173521.png)



### 5.1 MySQL主从复制配置

![image-20220723173803746](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723173803.png)



（1）在主数据库master中配置如下

![image-20220723174517005](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723174517.png)

（2）重启主数据库中MySQL服务

![image-20220723174628443](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723174628.png) 

（3）登录master主数据库，创建用户，执行授权SQL

![image-20220723174758976](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723174759.png)



（4）修改从slave数据库

![image-20220723175022475](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723175022.png) 

（5）重启MySQL服务

（6）在从数据库中执行下面的命令，目的是绑定关系

![image-20220723175149473](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723175149.png)

（7）从数据库启动线程

```
start slave
```

（8）查看状态

![image-20220723175427773](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723175427.png) 



### 5.2 读写分离测试案例

![image-20220723175911026](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723175911.png)



### Sharding -JDBC 框架

![image-20220723180332168](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723180332.png)



![image-20220723181138708](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723181138.png)



1、导入坐标

```java
        <dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
            <version>4.0.0-RC1</version>
        </dependency>

```

2、配置主数据库与从数据库的数据源

```yml
spring:
  application:
    name: ccTakeOut
  shardingsphere:
    datasource:
      names:
        master,slave
      # 主库（增删改操作）
      master:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://121.89.200.204:3306/ruiji?characterEncoding=utf-8
        username: root
        password: 333
      # 从数据源（读操作）
      slave:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://121.36.51.170:3306/ruiji?characterEncoding=utf-8
        username: root
        password: 333
    masterslave:
      # 读写分离配置
      load-balance-algorithm-type: round_robin #轮询（如果有多个从库会轮询着读）
      # 最终的数据源名称
      name: dataSource
      # 主库数据源名称
      master-data-source-name: master
      # 从库数据源名称列表，多个逗号分隔
      slave-data-source-names: slave
    props:
      sql:
        show: true #开启SQL显示，默认false
  main:
    allow-bean-definition-overriding: true #允许bean数据源覆盖

```

3、允许bean定义覆盖

![在这里插入图片描述](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20220723181426.png)

