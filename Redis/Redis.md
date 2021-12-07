

#  Redis怎么入门，有这篇文章就足够了！

官方网站：https://redis.io/

中文官网：http://www.redis.cn/



## 下载和安装（Linux）

### 1、下载获取安装包(

地址：https://redis.io

![image-20201002082444704](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002082444704.png) 

### 2、安装

> 前提是以及安装的c环境：

```shell
# yum install gcc-c++

检查是后安装成功
# gcc -v

# make && make install
```



1、通过ftp工具将redis安装包上传到远程Linux服务器/usr/local/tmp文件夹下

![image-20201002083441099](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002083441099.png)

2、在 /usr/local/ 下创建 redis ⽂件夹并进⼊

```shell
# cd /usr/local
# mkdir redis
# cd redis
```

3、复制redis安装到redis文件夹中并解压

```shell
# cp /usr/local/tmp/redis-6.0.8.tar.gz  /usr/local/redis
# tar zxvf redis-6.0.8.tar.gz 
```

解压后文件如图（最重要的`redis.conf`）

![image-20201002084842424](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002084842424.png)

4、执行命令`make &&make install`，安装成功

![image-20201002085807894](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002085807894.png)

5、redis默认安装路径`usr/local/bin`

![image-20201002085942415](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002085942415.png)

6、将我们的redis配置文件复制到当前目录下

```shell
# mkdir kconfig 
# cp  /usr/local/redis/redis-5.0.9/redis.conf ./kconfig
```

![image-20201002090435001](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002090435001.png)

7、redis默认不是后台启动的，我们需要修改配置文件！

![image-20201002090925822](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002090925822.png)

7、在bin目录下 通过指定的配置文件（`yconfig/redis.conf `）启动redis服务

```shell
# redis-server yconfig/redis.conf 
```

运行截图![image-20201002091207544](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002091207544.png)



8、测试用redis-cli客户端进行连接，检测是后连接成功！

```
redis-cli -p 6379
```



![image-20201002091735671](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002091735671.png)

8、查看redis的启动情况

```shell
# ps aux|grep redis 
```

![image-20201002092338073](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002092338073.png)



10、如何关闭redis服务呢？	+`exit`

![image-20201002092502494](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002092502494.png) 



11、再次查看进程是否存在

```shell
# ps aux|grep redis 
# ps -ef|grep redis
```

![image-20201002092601650](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002092601650.png)



## 性能测试

redis 性能测试工具可选参数如下所示：

![image-20201002095927877](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002095927877.png)



### 简单测试

以下实例我们使用了多个参数来测试 redis 性能：

```shell
# 测试100个并发连接 100000个请求
 redis-benchmark -h localhost -p 6379 -c 100 -n 100000

```

![image-20201002101010748](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002101010748.png)

- 如何查看这些分析呢？

![image-20201002101243378](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201002101243378.png)



## 基本知识：

1、redis中默认有16个数据库（0~16）

> 为什么redis是单线程的？

明白redis 是很快的，官方表示：Redis是基于内存操作的，CPU不是Redis的性能瓶颈，Redis的瓶颈是根据机器的内存和网络的带宽，既然可以使用单线程来实现，就使用单线程了！



Redis 是C语言写的，官方说100000+的QPS，说明这个完全不比同样使用（key-value）的 Memecache差！



**Redis是单线程的为什么还这么快？**

1、误区1：高性能的服务器一定是多线程的！

2、误区2：多线程（CPU上下文切换：消耗时间的操作）一定比单线程效率高！



核心：redis是将所有的数据全部放到内存里的，的所以说使用单线程操作效率就是最高的，多线程（CPU上下文切换：消耗时间的操作），对于内存系统来说，如果没有上下文切换就是效率最高！多次读写都是在一个CPU上的，在内存的情况下，这个就是最佳的解决方案~



## 五大数据类型



![image-20201003105203212](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201003105203212.png)

Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作==数据库==、==缓存==和==消息中间件==。 它支持多种类型的数据结构，如 字符串（strings）， 散列（hashes）， 列表（lists）， 集合（sets）， 有序集合（sorted sets） 与范围查询， bitmaps， hyperloglogs 和 地理空间（geospatial） 索引半径查询。 Redis 内置了 复制（replication），LUA脚本（Lua scripting）， LRU驱动事件（LRU eviction），事务（transactions） 和不同级别的 磁盘持久化（persistence）， 并通过 Redis哨兵（Sentinel）和自动 分区（Cluster）提供高可用性（high availability）。

### Redis-Key

详细命令地址：https://www.runoob.com/redis/redis-commands.html

```shell
# 清空全部数据库中的数据
flushall

# 清除当前数据库db
flush db

# 查看数据中所有内容
keys *

# 切换数据库
select (数据库编号)- 例如:select 1

# 判断某个值是否存在
exist yerenping	

# 1表示当前数据库-移除当前数据库1 name属性
move name 1

# 10秒后name的值将会国企
xpire name 10

# 查看当前key的剩余时间
ttl name

# 查看key的类型
type name
```



### String （字符串）

```shell
# 追加
append k1 12345

# 自动增加
INCR k1

# 自动降低
DECR k1

# 范围内截取 0 3
getrange k1 0 3

# 范围内设置
setrange k1 3 hello


```

![image-20201003113521481](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201003113521481.png) 



### List（列表）



![image-20201003122709662](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201003122709662.png)



在redis 里面，我们可以把list玩成堆、栈、阻塞队列。redis不区分大小写命令

Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）

一个列表最多可以包含 232 - 1 个元素 (4294967295, 每个列表超过40亿个元素)。

**实例**

```
redis 127.0.0.1:6379> LPUSH runoobkey redis
(integer) 1
redis 127.0.0.1:6379> LPUSH runoobkey mongodb
(integer) 2
redis 127.0.0.1:6379> LPUSH runoobkey mysql
(integer) 3
redis 127.0.0.1:6379> LRANGE runoobkey 0 10

1) "mysql"
2) "mongodb"
3) "redis"
```

在以上实例中我们使用了 **LPUSH** 将三个值插入了名为 **runoobkey** 的列表当中。

下表列出了列表相关的基本命令：

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [BLPOP key1 [key2 \] timeout](https://www.runoob.com/redis/lists-blpop.html) 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 2    | [BRPOP key1 [key2 \] timeout](https://www.runoob.com/redis/lists-brpop.html) 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 3    | [BRPOPLPUSH source destination timeout](https://www.runoob.com/redis/lists-brpoplpush.html) 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
| 4    | [LINDEX key index](https://www.runoob.com/redis/lists-lindex.html) 通过索引获取列表中的元素 |
| 5    | [LINSERT key BEFORE\|AFTER pivot value](https://www.runoob.com/redis/lists-linsert.html) 在列表的元素前或者后插入元素 |
| 6    | [LLEN key](https://www.runoob.com/redis/lists-llen.html) 获取列表长度 |
| 7    | [LPOP key](https://www.runoob.com/redis/lists-lpop.html) 移出并获取列表的第一个元素 |
| 8    | [LPUSH key value1 [value2\]](https://www.runoob.com/redis/lists-lpush.html) 将一个或多个值插入到列表头部 |
| 9    | [LPUSHX key value](https://www.runoob.com/redis/lists-lpushx.html) 将一个值插入到已存在的列表头部 |
| 10   | [LRANGE key start stop](https://www.runoob.com/redis/lists-lrange.html) 获取列表指定范围内的元素 |
| 11   | [LREM key count value](https://www.runoob.com/redis/lists-lrem.html) 移除列表元素 |
| 12   | [LSET key index value](https://www.runoob.com/redis/lists-lset.html) 通过索引设置列表元素的值 |
| 13   | [LTRIM key start stop](https://www.runoob.com/redis/lists-ltrim.html) 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 |
| 14   | [RPOP key](https://www.runoob.com/redis/lists-rpop.html) 移除列表的最后一个元素，返回值为移除的元素。 |
| 15   | [RPOPLPUSH source destination](https://www.runoob.com/redis/lists-rpoplpush.html) 移除列表的最后一个元素，并将该元素添加到另一个列表并返回 |
| 16   | [RPUSH key value1 [value2\]](https://www.runoob.com/redis/lists-rpush.html) 在列表中添加一个或多个值 |
| 17   | [RPUSHX key value](https://www.runoob.com/redis/lists-rpushx.html) 为已存在的列表添加值 |



### Set（集合）

Redis 的 Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。

Redis 中集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。

集合中最大的成员数为 232 - 1 (4294967295, 每个集合可存储40多亿个成员)。

**实例**

```shell
redis 127.0.0.1:6379> SADD myset redis
(integer) 1
redis 127.0.0.1:6379> SADD myset mongodb
(integer) 1
redis 127.0.0.1:6379> SADD myset mysql
(integer) 1
redis 127.0.0.1:6379> SADD myset mysql
(integer) 0
redis 127.0.0.1:6379> SMEMBERS myset

1) "mysql"
2) "mongodb"
3) "redis"
```

在以上实例中我们通过 **SADD** 命令向名为 **runoobkey** 的集合插入的三个元素。



下表列出了 Redis 集合基本命令：

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [SADD key member1 [member2\]](https://www.runoob.com/redis/sets-sadd.html) 向集合添加一个或多个成员 |
| 2    | [SCARD key](https://www.runoob.com/redis/sets-scard.html) 获取集合的成员数 |
| 3    | [SDIFF key1 [key2\]](https://www.runoob.com/redis/sets-sdiff.html) 返回第一个集合与其他集合之间的差异。 |
| 4    | [SDIFFSTORE destination key1 [key2\]](https://www.runoob.com/redis/sets-sdiffstore.html) 返回给定所有集合的差集并存储在 destination 中 |
| 5    | [SINTER key1 [key2\]](https://www.runoob.com/redis/sets-sinter.html) 返回给定所有集合的交集 |
| 6    | [SINTERSTORE destination key1 [key2\]](https://www.runoob.com/redis/sets-sinterstore.html) 返回给定所有集合的交集并存储在 destination 中 |
| 7    | [SISMEMBER key member](https://www.runoob.com/redis/sets-sismember.html) 判断 member 元素是否是集合 key 的成员 |
| 8    | [SMEMBERS key](https://www.runoob.com/redis/sets-smembers.html) 返回集合中的所有成员 |
| 9    | [SMOVE source destination member](https://www.runoob.com/redis/sets-smove.html) 将 member 元素从 source 集合移动到 destination 集合 |
| 10   | [SPOP key](https://www.runoob.com/redis/sets-spop.html) 移除并返回集合中的一个随机元素 |
| 11   | [SRANDMEMBER key [count\]](https://www.runoob.com/redis/sets-srandmember.html) 返回集合中一个或多个随机数 |
| 12   | [SREM key member1 [member2\]](https://www.runoob.com/redis/sets-srem.html) 移除集合中一个或多个成员 |
| 13   | [SUNION key1 [key2\]](https://www.runoob.com/redis/sets-sunion.html) 返回所有给定集合的并集 |
| 14   | [SUNIONSTORE destination key1 [key2\]](https://www.runoob.com/redis/sets-sunionstore.html) 所有给定集合的并集存储在 destination 集合中 |
| 15   | [SSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/sets-sscan.html) 迭代集合中的元素 |



### Hash（哈希）

特别的Map集合，`key-map`<=>key-<key,value>

set myhash field ye

Redis hash 是一个 string 类型的 field（字段） 和 value（值） 的映射表，hash 特别适合用于存储对象。

Redis 中每个 hash 可以存储 232 - 1 键值对（40多亿）。

实例

```shell
127.0.0.1:6379>  HMSET runoobkey name "redis tutorial" description "redis basic commands for caching" likes 20 visitors 23000
OK
127.0.0.1:6379>  HGETALL runoobkey
1) "name"
2) "redis tutorial"
3) "description"
4) "redis basic commands for caching"
5) "likes"
6) "20"
7) "visitors"
8) "23000"
```

在以上实例中，我们设置了 redis 的一些描述信息(name, description, likes, visitors) 到哈希表的 **runoobkey** 中。



下表列出了 redis hash 基本的相关命令：

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [HDEL key field1 [field2\]](https://www.runoob.com/redis/hashes-hdel.html) 删除一个或多个哈希表字段 |
| 2    | [HEXISTS key field](https://www.runoob.com/redis/hashes-hexists.html) 查看哈希表 key 中，指定的字段是否存在。 |
| 3    | [HGET key field](https://www.runoob.com/redis/hashes-hget.html) 获取存储在哈希表中指定字段的值。 |
| 4    | [HGETALL key](https://www.runoob.com/redis/hashes-hgetall.html) 获取在哈希表中指定 key 的所有字段和值 |
| 5    | [HINCRBY key field increment](https://www.runoob.com/redis/hashes-hincrby.html) 为哈希表 key 中的指定字段的整数值加上增量 increment 。 |
| 6    | [HINCRBYFLOAT key field increment](https://www.runoob.com/redis/hashes-hincrbyfloat.html) 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。 |
| 7    | [HKEYS key](https://www.runoob.com/redis/hashes-hkeys.html) 获取所有哈希表中的字段 |
| 8    | [HLEN key](https://www.runoob.com/redis/hashes-hlen.html) 获取哈希表中字段的数量 |
| 9    | [HMGET key field1 [field2\]](https://www.runoob.com/redis/hashes-hmget.html) 获取所有给定字段的值 |
| 10   | [HMSET key field1 value1 [field2 value2 \]](https://www.runoob.com/redis/hashes-hmset.html) 同时将多个 field-value (域-值)对设置到哈希表 key 中。 |
| 11   | [HSET key field value](https://www.runoob.com/redis/hashes-hset.html) 将哈希表 key 中的字段 field 的值设为 value 。 |
| 12   | [HSETNX key field value](https://www.runoob.com/redis/hashes-hsetnx.html) 只有在字段 field 不存在时，设置哈希表字段的值。 |
| 13   | [HVALS key](https://www.runoob.com/redis/hashes-hvals.html) 获取哈希表中所有值。 |
| 14   | [HSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/hashes-hscan.html) 迭代哈希表中的键值对。 |



### Zset（有序集合）

Redis 有序集合和集合一样也是string类型元素的集合,且不允许重复的成员。

不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。

有序集合的成员是唯一的,但分数(score)却可以重复。

集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。 集合中最大的成员数为 232 - 1 (4294967295, 每个集合可存储40多亿个成员)。

**实例**

```
redis 127.0.0.1:6379> ZADD runoobkey 1 redis
(integer) 1
redis 127.0.0.1:6379> ZADD runoobkey 2 mongodb
(integer) 1
redis 127.0.0.1:6379> ZADD runoobkey 3 mysql
(integer) 1
redis 127.0.0.1:6379> ZADD runoobkey 3 mysql
(integer) 0
redis 127.0.0.1:6379> ZADD runoobkey 4 mysql
(integer) 0
redis 127.0.0.1:6379> ZRANGE runoobkey 0 10 WITHSCORES

1) "redis"
2) "1"
3) "mongodb"
4) "2"
5) "mysql"
6) "4"
```

在以上实例中我们通过命令 **ZADD** 向 redis 的有序集合中添加了三个值并关联上分数。

------



下表列出了 redis 有序集合的基本命令:

| 序号 | 命令及描述                                                   |
| :--- | :----------------------------------------------------------- |
| 1    | [ZADD key score1 member1 [score2 member2\]](https://www.runoob.com/redis/sorted-sets-zadd.html) 向有序集合添加一个或多个成员，或者更新已存在成员的分数 |
| 2    | [ZCARD key](https://www.runoob.com/redis/sorted-sets-zcard.html) 获取有序集合的成员数 |
| 3    | [ZCOUNT key min max](https://www.runoob.com/redis/sorted-sets-zcount.html) 计算在有序集合中指定区间分数的成员数 |
| 4    | [ZINCRBY key increment member](https://www.runoob.com/redis/sorted-sets-zincrby.html) 有序集合中对指定成员的分数加上增量 increment |
| 5    | [ZINTERSTORE destination numkeys key [key ...\]](https://www.runoob.com/redis/sorted-sets-zinterstore.html) 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 destination 中 |
| 6    | [ZLEXCOUNT key min max](https://www.runoob.com/redis/sorted-sets-zlexcount.html) 在有序集合中计算指定字典区间内成员数量 |
| 7    | [ZRANGE key start stop [WITHSCORES\]](https://www.runoob.com/redis/sorted-sets-zrange.html) 通过索引区间返回有序集合指定区间内的成员 |
| 8    | [ZRANGEBYLEX key min max [LIMIT offset count\]](https://www.runoob.com/redis/sorted-sets-zrangebylex.html) 通过字典区间返回有序集合的成员 |
| 9    | [ZRANGEBYSCORE key min max [WITHSCORES\] [LIMIT]](https://www.runoob.com/redis/sorted-sets-zrangebyscore.html) 通过分数返回有序集合指定区间内的成员 |
| 10   | [ZRANK key member](https://www.runoob.com/redis/sorted-sets-zrank.html) 返回有序集合中指定成员的索引 |
| 11   | [ZREM key member [member ...\]](https://www.runoob.com/redis/sorted-sets-zrem.html) 移除有序集合中的一个或多个成员 |
| 12   | [ZREMRANGEBYLEX key min max](https://www.runoob.com/redis/sorted-sets-zremrangebylex.html) 移除有序集合中给定的字典区间的所有成员 |
| 13   | [ZREMRANGEBYRANK key start stop](https://www.runoob.com/redis/sorted-sets-zremrangebyrank.html) 移除有序集合中给定的排名区间的所有成员 |
| 14   | [ZREMRANGEBYSCORE key min max](https://www.runoob.com/redis/sorted-sets-zremrangebyscore.html) 移除有序集合中给定的分数区间的所有成员 |
| 15   | [ZREVRANGE key start stop [WITHSCORES\]](https://www.runoob.com/redis/sorted-sets-zrevrange.html) 返回有序集中指定区间内的成员，通过索引，分数从高到低 |
| 16   | [ZREVRANGEBYSCORE key max min [WITHSCORES\]](https://www.runoob.com/redis/sorted-sets-zrevrangebyscore.html) 返回有序集中指定分数区间内的成员，分数从高到低排序 |
| 17   | [ZREVRANK key member](https://www.runoob.com/redis/sorted-sets-zrevrank.html) 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序 |
| 18   | [ZSCORE key member](https://www.runoob.com/redis/sorted-sets-zscore.html) 返回有序集中，成员的分数值 |
| 19   | [ZUNIONSTORE destination numkeys key [key ...\]](https://www.runoob.com/redis/sorted-sets-zunionstore.html) 计算给定的一个或多个有序集的并集，并存储在新的 key 中 |
| 20   | [ZSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/sorted-sets-zscan.html) 迭代有序集合中的元素（包括元素成员和元素分值） |





##  geospatial 地理位置

附近的人，朋友定位，打车距离计算

Redis的Geo在3.2版本就推出了！这个功能可以推算出地理位置信息，比如两地之间的距离，方圆几里的人！

城市地理位置经度纬度查询：http://www.jsons.cn/lngcode/

> geo底层原理是Zset，我们可以用Zset命令来操作geo！

只有6个命令

#### GEOADD

```shell
# geoadd 添加地理位置
# 地球两极是无法添加，我们一般会下城市数据，直接通过java程序一次性导入！
# 参数key 值（纬度、经度、名称）


127.0.0.1:6379> GEOADD china:city 116.40 39.90 bejing # 北京
(integer) 1
127.0.0.1:6379> GEOADD china:city 115.99 29.71 jiujiang # 九江
(integer) 1 
127.0.0.1:6379> GEOADD china:city 100.29 26.87 lijiang # 丽江
(integer) 1
```



#### GEOPOS

```shell
127.0.0.1:6379> GEOPOS china:city jiujiang # 获取九江的经纬度
1) 1) "115.9900018572807312"
   2) "29.71000026741588584"
127.0.0.1:6379> 


127.0.0.1:6379> GEOPOS china:city jiujiang bejing # 获取九江和北京的经纬度
1) 1) "115.9900018572807312"
   2) "29.71000026741588584"
2) 1) "116.39999896287918091"
   2) "39.90000009167092543"
127.0.0.1:6379> 

```



#### GEODIST

```shell
127.0.0.1:6379> GEODIST china:city jiujiang lijiang # 丽江到九江的距离（m）
"1568437.6149"
127.0.0.1:6379> GEODIST china:city jiujiang lijiang km # 丽江到九江的距离（km）
"1568.4376"
```



#### GEORADIUS

附近的人？（获取附近的人的地址、定位！）通过圆形半径来查询！

```shell
127.0.0.1:6379> GEORADIUS china:city 110 30 1500 km # 以（ 110 30 ）为中心，方圆1500km的城市有些
1) "lijiang"
2) "jiujiang"
3) "bejing"

127.0.0.1:6379> GEORADIUS china:city 110 30 1000 km # 以（ 110 30 ）为中心，方圆1000km的城市有些
1) "jiujiang"



# 以（ 110 30 ）为中心，方圆1500km的城市有些，并且显示距离
127.0.0.1:6379> GEORADIUS china:city 110 30 1500 km withdist 

1) 1) "lijiang"
   2) "1011.0600"
2) 1) "jiujiang"
   2) "578.6601"
3) 1) "bejing"
   2) "1245.2858"


# 以（ 110 30 ）为中心，方圆1500km的城市有些，并且显示出经度纬度！
127.0.0.1:6379> GEORADIUS china:city 110 30 1500 km withcoord
1) 1) "lijiang"
   2) 1) "100.29000252485275269"
      2) "26.86999982636731232"
2) 1) "jiujiang"
   2) 1) "115.9900018572807312"
      2) "29.71000026741588584"
3) 1) "bejing"
   2) 1) "116.39999896287918091"
      2) "39.90000009167092543"

```



#### GEORADIUSBYMEMBER

```shell
# 以九江中小，查出方圆2000 km的城市
127.0.0.1:6379> GEORADIUSBYMEMBER china:city jiujiang 2000 km
1) "lijiang"
2) "jiujiang"
3) "bejing"

```



#### GEOHASH

```shell
# 将（bejing jiujiang 城市的）二维经纬度转化成一维字符转，如果这两个字符串越相近，那么则距离越近
127.0.0.1:6379> GEOHASH china:city jiujiang bejing
1) "wt63p2t36c0"
2) "wx4fbxxfke0"
```



==geo底层原理是Zset，我们可以用Zset命令来操作geo！==

```shell
# 查看有哪些城市
127.0.0.1:6379> ZRANGE china:city 0 -1
1) "lijiang"
2) "jiujiang"
3) "bejing"

# 移除北京
127.0.0.1:6379> ZREM china:city bejing
(integer) 1

```



## 事务

==**面试常问**==

==原子性：==要么同时成功，要么同时失败！

==Redis单条命令是保证原子性的，但事务是不保证原子性的！==

==Redis事务没有隔离级别的概念！==

> redis事务的本质：一组命令的集合！
>
> 一个事务所有的命令都会被序列化，在事务执行过程中，会按照顺序执行！
>
> 一次性、顺序性、排他性

```
--------队列 set set set 执行
set
set 
set 
--------------------------
```



Redis的事务：

	1. 开启事务（multi）

   	2. 命令入队（其他命令.....）
      	3. 执行事务（exec）

> 正常执行事务！

```shell
127.0.0.1:6379> multi # 开启事务
OK
# 命令入队
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED

127.0.0.1:6379> exec # 执行事务
1) OK
2) OK
3) "v2"
4) OK

```

命令效果：

![image-20201004162403800](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201004162403800.png) 



> 取消事务
>
> 



```shell
127.0.0.1:6379> multi # 开启事务
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k10 v10 # 设值不成功
QUEUED
127.0.0.1:6379> DISCARD # 取消事务
OK
127.0.0.1:6379> get k10
(nil)
```

![image-20201004162920975](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201004162920975.png) 



> 编译形异常（代码命令有错！），事务中所有命令都不会执行！



![image-20201004163337817](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201004163337817.png)



> 运行时异常（1/0），如果事务队列中有点语法性错误，那么执行命令的时候，其他命令是可以正常执行的！错误命令会抛出异常



![image-20201004163703666](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201004163703666.png) 



> 监控 Watch



**悲观锁**

- 很悲观，认为什么时候都会出问题，无论什么时候都加锁！

**乐观锁**

- 很乐观，认为什么时候都不会出问题，所以不会上锁！更新数据的时候会去判断一下，在此期间是后有人改动过数据！获
- version
- 更新的时候比较version

> Redis监测实现测试

(1)、单线程正常执行

```shell
127.0.0.1:6379> set money 100 
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> WATCH money  # 监控money
OK
127.0.0.1:6379> MULTI # 开启事务
OK
127.0.0.1:6379> DECRBY money 20
QUEUED
127.0.0.1:6379> INCRBY out 20
QUEUED
127.0.0.1:6379> EXEC # 执行事务，数据期间没有发生任何变动，这个时候就正常执行成功！
1) (integer) 80
2) (integer) 20

```



（2）、多线程修改值，使用watch可以当做Redis 的乐观锁！

线程一：

```shell
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> set out 0
OK
127.0.0.1:6379> WATCH money
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379>  DECRBY money 10
QUEUED
127.0.0.1:6379> INCRBY out 10
QUEUED
127.0.0.1:6379> exec # 在执行事务之前，money的值被线程二修改了，导致执行失败（nil）
(nil)
127.0.0.1:6379> 

```



线程二：在线程一还未执行exec之前，修改了money 的值，导致线程一执行失败

```shell
127.0.0.1:6379> get money
"100"
127.0.0.1:6379> set money 500
OK
127.0.0.1:6379> 
```



==解决方法==

1、 先解锁，解除监视

```
unwatch
```



![image-20201004171710340](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201004171710340.png)

2、再重新加锁，加监视，重新启动事务！

```shell
127.0.0.1:6379> watch money # 获取最新的值，重新加锁，加监视 select version
OK
127.0.0.1:6379> multi # 添加事务
OK
127.0.0.1:6379> decrby money 1
QUEUED
127.0.0.1:6379> incrby out 1
QUEUED
127.0.0.1:6379> exec # 执行事务（如果没有发生变化，即执行成功）
1) (integer) 499
2) (integer) 1

```

![image-20201004171949835](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201004171949835.png)





## Jedis

是Java操作redis 的一个工具

> 什么是Jedis？ 是Redis 官方推荐的Java连接工具！是Java连接Redis的一个中间件！如果想要用Java操作Redis，那么一定要熟悉Jeids！



### 使用方式

1、创建一个maven项目

2、导入对应的依赖

```xml
        <!--导入jedis包-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.2.0</version>
        </dependency>

        <!--fastjson-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.62</version>
        </dependency>
```

3、编码测试

```
public class TestPing {

    public static void main(String[] args) {
        // new Jddis 对象
        Jedis jedis = new Jedis("47.104.231.144",6379);

        System.out.println("结果为："+jedis.ping());

    }

}
```

测试连接，结果如图

![image-20201005113954465](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201005113954465.png) 

> 如果此处连接无法连接成功，提示异常可以参考：《Jedis连接搭建在阿里云(CentOS7)服务器上的Redis连接异常问题解决》

《Jedis连接搭建在阿里云(CentOS7)服务器上的Redis连接异常问题解决》

> ```
> // try catch 快捷键:Ctrl +Alt +T
> ```



### Redis-事务-Java

1、测试操作正常

```java
public class TestPing {

    public static void main(String[] args) {
        // new Jddis 对象
        Jedis jedis = new Jedis("47.104.231.144",6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","ye");
        jsonObject.put("password","admin123");
        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();

        try {
            multi.set("str1",result);
            multi.set("str2",result);
       
            multi.exec();//执行事务
        } catch (Exception e) {
            multi.discard(); // 放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("str1"));
            System.out.println(jedis.get("str2"));
            jedis.close(); // 关闭连接
        }


    }
```

结果

2、异常事务

```java
public class TestPing {

    public static void main(String[] args) {
        // new Jddis 对象
        Jedis jedis = new Jedis("47.104.231.144",6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","ye");
        jsonObject.put("password","admin123");
        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();

        try {
            multi.set("str1",result);
            multi.set("str2",result);
            int i = 1/0; // 代码抛出异常事务，执行失败！

            multi.exec();//执行事务
        } catch (Exception e) {
            multi.discard(); // 放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("str1"));
            System.out.println(jedis.get("str2"));
            jedis.close(); // 关闭连接
        }


    }
```

结果图：

![](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201005154008979.png)  



## 整合springboot

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
    host: 47.104.231.144
    port: 6479
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

![image-20201006144725189](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006144725189.png)





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
public class User implements{

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





## redis.conf分析

工作中，一些小小的配置，就可以让你脱颖而出！



> 单位

![image-20201006162534731](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006162534731.png)

- 配置文件unit单位 对大小写不敏感

![image-20201006162735528](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006162735528.png)

- 就好比我们学习Spring、import、include（jsp）

![image-20201006162941440](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006162941440.png)

![image-20201006163156475](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006163156475.png)

![image-20201006163310458](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006163310458.png)

> 网络

```bash
# bind 127.0.0.1 绑定的IP
protected-mode no # 保护模式
port # 默认端口
```



![](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006163423412.png)

![image-20201006163714463](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006163714463.png)

> 通用GENERAL

```bash
daemonize yes # 以守护进程的方式运行，默认是no，我们将其改为yes 

pidfile /var/run/redis_6379.pid # 如果以后台的方式运行，我们就需要指定一个pid进程文件!
```





![image-20201006163931256](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006163931256.png)

> 日志级别

```bash
# Specify the server verbosity level.
# This can be one of:
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)//生产环境
# warning (only very important / critical messages are logged)
loglevel notice

```

![image-20201006164200418](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006164200418.png)

```bash
logfile ""  # 文件名及其路径(日志)
```





![image-20201006164307113](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006164307113.png)

> 默认有16个数据库



 

![image-20201006164419378](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006164419378.png)

> 启动动画



```bash
always-show-logo yes # 启动logo，默认开启
```





![image-20201006164615183](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006164615183.png)



> 快照

持久化，在规定时间内，执行了多少次操作，册会持久化到.rdb、.aof

redis是内存数据库，没有持久化，那么数据就会断电失！

```bash
# 如果90 秒内，有一个key进行了修改，我们就进行持久化操作
save 900 1
# 如果300 秒内，有一个10 key进行了修改，我们就进行持久化操作
save 300 10
# 如果60 秒内，有一个10000 key进行了修改，我们就进行持久化操作
save 60 10000

# 之后学习，我们会自定义

stop-writes-on-bgsave-error yes #持久化如果出错，是否需要继续工作！


rdbcompression yes # 是否压缩rdb文件，需要消耗一些资源！

rdbchecksum yes # 是否校验rdb文件

# rdb文件保存的目录
dir ./

```







![image-20201006165344576](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006165344576.png)

> REPLICATION 复制，我们后面讲主从复制的，redis集群的时候在使用它







![image-20201006165506648](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006165506648.png)

> SECURITY 安全

(1)、设置密码-方式1：修改配置文件

![image-20201006165835456](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006165835456.png) 

(2)、通过set命令方式修改密码，启动redis-cli客户端

![image-20201006170341131](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006170341131.png)

```bash
127.0.0.1:6379> config get requirepass # 获取客户端密码
1) "requirepass"
2) "" # 结果为空
127.0.0.1:6379> config set requirepass admin123 # 设置密码为admin123
OK
127.0.0.1:6379> ping # ping一下，发现失败，因为需要登录密码才能ping成功
(error) NOAUTH Authentication required.
127.0.0.1:6379> auth admin123 # 登录密码
OK # 成功啦！！！！！
```









> CLIENT客户端限制

![image-20201006171055833](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006171055833.png)

```bash
# maxclients 10000 #设置redis的最大客户端数量
# maxmemory <bytes> # reids配置最大内存容量
# maxmemory-policy noeviction # 内存到达上限的处理策略

策略：maxmemory-policy 六种方式

1、volatile-lru：只对设置了过期时间的key进行LRU（默认值） 

2、allkeys-lru ： 删除lru算法的key  

3、volatile-random：随机删除即将过期key  

4、allkeys-random：随机删除  

5、volatile-ttl ： 删除即将过期的  

6、noeviction ： 永不过期，返回错误
	
```







![image-20201006172012795](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201006172012795.png)

> APPEND ONLY 模式 aof配置

```bash
appendonly no # 默认不开启，因为默认是使用rdb方式持久化的，在大部分情况下，rdb完全够用了！

# The name of the append only file (default: "appendonly.aof")

appendfilename "appendonly.aof" # 持久化文件的名字


# appendfsync always # 每次修改都会同步
appendfsync everysec # 默认设置是每秒执行一次sync，但是可能丢失这一秒的数据！
# appendfsync no # 不自信sync，这个时候操作系统自己同步数据，速度最快！


```

具体的配置，在Redis持久化中会详细分析！





## Redis持久化

面试和工作，持久化是重点！

redis是内存数据库，如果不把他保存到硬盘中，就会出现断电即失的问题

### RDB(Redis DataBase)

> 什么是RDB?

![image-20201008124302565](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201008124302565.png)

在指定时间间隔内将内存数据集快照写入磁盘，也就是Snapshot快照，它恢复时是快照文件直接读取到内存里。

**优点：**

1、适合大规模 数据恢复！

2、对数据完整性要求不高

**缺点：**

1、需要在一定时间间隔内操作，如果redis意外宕机，最后一次的修改数据将会丢失！

2、fork进程的时候，会占用一定的内存空间！



### AOF（Append Only File）

> 什么是AOP?

视频地址：https://www.bilibili.com/video/BV1S54y1R7SB?p=29

![image-20201008125538481](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201008125538481.png)

==Aof保存的文件是append.aof==

以日志级别记录我们所有的操作

**优点：**

1、每一次修改都同步，文件的完整性会更加好！

2、每秒同步一次，可能会丢失一秒的数据！

3、从不同步，效率是最高的！

**缺点：**

1、相对于数据文件来说，aof远远大于rdb，修复的速度也比rdb慢！

2、aof运行效率也比adb慢，所以redis默认配置是是rdb持久化，而非aof！





## Redis发布订阅

![image-20201009104547309](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201009104547309.png)







## Redis主从复制



主从复制，读写分离！80%的情况下都是进行读操作！可以减缓服务器压力！

主机数据更新后根据配置和策略，自动同步到master/slaver机制，Master以写为主，Slave以读为主！

**主要作用：**

![image-20201009112020632](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201009112020632.png) 





> 一主三从示例

![image-20201009105157540](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201009105157540.png) 



### reids集群搭建

> 主机可以写，从机不能写只能读！主机所有的信息和数据，都会自动同步到从机！





## 哨兵模式

> 当master出现问题的时候，slave们会自动选举一个作为master



![image-20201009135253258](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201009135253258.png) 





## 作者

<p align="center">
  <a href="https://space.bilibili.com/393270022"><img src="https://img.shields.io/badge/关注bilibili-green.svg" alt="关于十三的bilibili"></a>
    <a href="#微信"><img src="https://img.shields.io/badge/weChat-微信号-blue.svg" alt="十三微信"></a> 
  <a href="#公众号"><img src="https://img.shields.io/badge/订阅公众号-是叶十三-teal.svg" alt="公众号"></a>
  <a href="https://www.youtube.com/channel/UCZVu2GM10DHyDVjsAPuJUCg?view_as=subscriber"><img src="https://img.shields.io/badge/订阅-YouTube-lightgrey.svg" alt="YouTube"></a>
    <a href="https://www.toutiao.com/c/user/token/MS4wLjABAAAA4pdtT6tFF0OxupgYp9AUfjnxyHgnC0wEdRP3oBVbmnk/#mid=1655341804997644"><img src="https://img.shields.io/badge/关注-今日头条-critical.svg" alt="今日头条"></a>
  <a href="https://www.zhihu.com/people/xie-ren-ping-12"><img src="https://img.shields.io/badge/关注-知乎-critical.svg" alt="知乎"></a>
  <a href="https://weibo.com/sye13/profile?rightmod=1&wvr=6&mod=personinfo"><img src="https://img.shields.io/badge/微博-是叶十三-brightgreen.svg" alt="微博"></a>
  <a href="https://gitee.com/yerenping"><img src="https://img.shields.io/badge/关注-码云-brightgreen.svg" alt="码云"></a>
    <a href="https://juejin.im/user/773672546605783"><img src="https://img.shields.io/badge/juejin-掘金-blue.svg" alt="公众号"></a>
 </p>

> 全网同名： 是叶十三

![up主公众号-是叶十三](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesup主公众号-是叶十三.png) 

本文已收录到[GitHub](https://github.com/yerenping/ye13)开源仓库【[Ye13](https://github.com/yerenping/ye13)】：[点击跳转](https://github.com/yerenping/ye13)

想要获取更多关于编程的内容，可以收藏star十三的开源项目，也可以关注十三的公众号哦！

