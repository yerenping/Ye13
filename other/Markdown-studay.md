# Markdown快速入门（typora）

## 1、代码块：

```java
//代码块语法：
​```java
  
    
​```shell
```

​	**1.java代码**

```java
package com.yrp.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User {
```

​	**2.shell脚本**

```shell
//linux下spring项目的启动命令
# java -jar blog start

```



## 2、标题：

```java
//标题语法
# 一级标题
## 二级标题
### 三级标题
#### 四级标题    
##### 五级标题
###### 六级标题 
```

# 一级标题
## 二级标题
### 三级标题
#### 四级标题

##### 五级标题
###### 六级标题



## 3、字体

```java
// 加粗
**等不到天黑**
// 代码高亮显示
==我不是孙红雷丶==    
// 删除线
~~被删除的文字~~    
// 斜体
*斜体内容*    
```


**等不到天黑**

==我不是孙红雷丶==    

~~被删除的文字~~    

*斜体内容*   



## 4、引用：

```java
//引用语法
>作者：叶仁平
>>作者：叶仁平   
>>>作者：叶仁平     
```

//引用语法
>作者：叶仁平
>>作者：叶仁平   
>>
>>>作者：叶仁平  





## 5、分割线：

```java
//分割线
---
//分割线2
***    


```



---





***



## 6、图片插入

```java
//在线图片/本地图片
![我的照片](/image/me.png) --图片路径
```

![我的照片](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20211220161123.png)



![](images/me4.png) 注：由于图片在本地，所以大家无法看到，掌握语法即可



## 7、 超链接

```java
//超链接语法
[我的GitHub](https://github.com/yerenping)
```

//超链接语法
[我的GitHub](https://github.com/yerenping)



## 8、列表

```java
// 无需列表
- 目录1
- 目录2
- 目录3    
// 1+. + 名称    
```

- 无序列表1
- 反而威尔
- 额问问而非
- 沃尔夫二翁

1. 首页
2. 分类
3. 标签

## 9、表格

| 课程 | 语文 | 数学 |
| ---- | ---- | ---- |
|      | 78   | 88   |

