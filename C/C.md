**话所在前面：**《C语言入门指南》，全文分为3篇，共计34248字，此为合集版，适用初学者入门C语言，非初学者也可以通过本文复习C语言相关知识点，强化记忆！十三发布这篇笔记也是为了复习C语言！





## 发展史

### C 语言的诞生小故事

1、为什么发明 C 语言：C 语言的诞生是和 UNIX 操作系统的开发密不可分的，原先的 UNIX 操作系统都是用汇编 

语言写的，1973 年 UNIX 操作系统的核心用 C 语言改写，从此以后，C 语言成为编写操作系统的主要语言 

2) 、C 语言对其它语言的影响：很多编程语言都深受 C 语言的影响，比如 C++（原先是 C 语言的一个扩展）、C#、 

Java、PHP、Javascript、Perl、LPC 和 UNIX 的 C Shell 等。 

3、掌握 C 语言的人，再学其它编程语言，大多能很快上手，触类旁通，很多大学将 C 语言作为计算机教学的入门 

语言 

4、发明人

![image-20201014104602003](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014104602003.png) 



### 发展历程

![image-20201014104710539](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014104710539.png) 

==说明：需要知道 C 语言的两个重要的版本 1. ANSI C (标准 C), C89 2. C99==



### C 语言的特点

1、**代码级别的跨平台**：由于标准的存在，使得几乎同样的 C 代码可用于多种操作系统，如 Windows、DOS、UNIX 

等等；也适用于多种机型。 

2、**使允许直接访问物理地址，对硬件进行操作**: 由于 C 语言允许直接访问物理地址，可以直接对硬件进行操作， 

因此它既具有高级语言的功能，又具有低级语言的许多功能，C 语言可用来写系统软件(比如操作系统, 数据库, 

杀毒软件，防火墙, 驱动， 服务器程序)

3、 C 语言是一个有**结构化程序设计**、具有**变量作用域**（variable scope）以及**递归功能**的过程式语言 

4、C 语言传递参数可以是**值传递**（pass by value，值），也可以**传递指针**（a pointer passed by value， 地址） 

5、C 语言中，**没有对象**，不同的变量类型可以用结构体（struct）组合在一起 

6、**预编译处理**（preprocessor）, 生成目标**代码质量高**，程序**执行效率**高







## 快速入门HelloWorld



> 1、VC++配置启动按钮，步骤如下

1、点击按钮

![image-20201014091344647](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014091344647.png)

2、点击自定义

![image-20201014091449024](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014091449024.png) 

3、添加命令

![image-20201014091524822](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014091524822.png)

4、点击如下 Z                                                                                                         

![image-20201014091620658](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014091620658.png)

5、点击确认按钮，效果如下

![image-20201014091722451](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014091722451.png)



> 创建一个项目，让其输出 `hello world！`

**1、创建项目如图**

![image-20201014091850749](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014091850749.png) 

**2、在test01.c中编写如下代码**



```c
// 这是一个main函数，是程序执行的入口	

#include <stdio.h> //引入头文件
void main(){
	// printf 是一个函数，需要引入头文件才能使用
	// printf 是在<stdio.h> 下的一个文件，需要引入它才行
	printf("hello world!");

	getchar();// 让窗口停留
}
```

3、点击运行，结果如图



![image-20201014092025915](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014092025915.png)



## 程序运行机制

> 文件描述

1、编辑：就是我们编写的 **xx.c文件**，就是源代码

2、编译：将这个xx.c文件翻译成 **目标文件（xx.obj）** 

3、链接：将 **目标文件（.obj）+库文件** 生成 **可执行文件（xx.exe）** 

4、执行可执行文件（xx.exe）

==编译和链接都是在计算机底层实现的==



> C程序运行机制 -图解

![image-20201014095622685](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014095622685.png)

==编译cl.exe 和连接link.exe 是在vs2010软件文件目录的bin目录下==



目标文件（xx.obj） 、可执行文件（xx.exe） 在计算机中可以找到

![image-20201014092731042](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014092731042.png)



![image-20201014092804620](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014092804620.png)



## 基础知识



### 转义字符

6个最常用的转义字符

|      |                                     |      |
| ---- | ----------------------------------- | ---- |
| \n   | 换行(LF) ，将当前位置移到下一行开头 | 010  |
| \r   | 回车(CR) ，将当前位置移到本行开头   | 013  |
| \t   | 水平制表(HT) （跳到下一个TAB位置）  | 009  |
| \ \  | 代表一个反斜线字符’\’               | 092  |
| \’   | 代表一个单引号（撇号）字符          | 039  |
| \”   | 代表一个双引号字符                  | 034  |




### 注释



1、 单行注释

```c
// 被注释的内容
```



2、多行注释

```c
/*
被注释的内容
*/
```



### 标识符

**C 标识符：**用来标识变量、函数，或任何其他用户自定义项目的名称。

**什么是标识符？**

==一个标识符以字母 A-Z 或 a-z 或下划线 _ 开始，后跟零个或多个字母、下划线和数字（0-9）。==

C 标识符内不允许出现标点字符，比如 @、$ 和 %。C 是**区分大小写**的编程语言。因此，在 C 中，*Manpower* 和 *manpower* 是两个不同的标识符。下面列出几个有效的标识符：

```c
mohd       zara    abc   move_name  a_123
myname50   _temp   j     a23b9      retVal
```



> 标识符的命名规则和规范

1、程序中不得出现仅靠大小写区分的相似的标识符，int x, X; 变量x 与X 容易混淆

2、所有宏定义、枚举常数、常量(只读变量)全用大写字母命名，用下划线分隔单词，

比如： 

```c
const double TAX_RATE = 0.08;//TAX_RATE 只读变量#define FILE_PATH "/usr/tmp"
```



3、定义变量别忘了初始化。定义变量时编译器并不一定清空了这块内存，它的值可能是无效的数据, 运行程序，会异常退出.

4、变量名、函数名：多单词组成时，第一个单词首字母小写，第二个单词开始每个单词首字母大写: xxxYyyZzz [驼峰法，小驼峰， 比如 short stuAge = 20;] 



### 关键字

下表列出了 C 中的保留字。这些保留字不能作为常量名、变量名或其他标识符名称。

| 关键字   | 说明                                                         |
| :------- | :----------------------------------------------------------- |
| auto     | 声明自动变量                                                 |
| break    | 跳出当前循环                                                 |
| case     | 开关语句分支                                                 |
| char     | 声明字符型变量或函数返回值类型                               |
| const    | 定义常量，如果一个变量被 const 修饰，那么它的值就不能再被改变 |
| continue | 结束当前循环，开始下一轮循环                                 |
| default  | 开关语句中的"其它"分支                                       |
| do       | 循环语句的循环体                                             |
| double   | 声明双精度浮点型变量或函数返回值类型                         |
| else     | 条件语句否定分支（与 if 连用）                               |
| enum     | 声明枚举类型                                                 |
| extern   | 声明变量或函数是在其它文件或本文件的其他位置定义             |
| float    | 声明浮点型变量或函数返回值类型                               |
| for      | 一种循环语句                                                 |
| goto     | 无条件跳转语句                                               |
| if       | 条件语句                                                     |
| int      | 声明整型变量或函数                                           |
| long     | 声明长整型变量或函数返回值类型                               |
| register | 声明寄存器变量                                               |
| return   | 子程序返回语句（可以带参数，也可不带参数）                   |
| short    | 声明短整型变量或函数                                         |
| signed   | 声明有符号类型变量或函数                                     |
| sizeof   | 计算数据类型或变量长度（即所占字节数）                       |
| static   | 声明静态变量                                                 |
| struct   | 声明结构体类型                                               |
| switch   | 用于开关语句                                                 |
| typedef  | 用以给数据类型取别名                                         |
| unsigned | 声明无符号类型变量或函数                                     |
| union    | 声明共用体类型                                               |
| void     | 声明函数无返回值或无参数，声明无类型指针                     |
| volatile | 说明变量在程序执行中可被隐含地改变                           |
| while    | 循环语句的循环条件                                           |





### 变量

变量相当于内存中一个数据存储空间的表示，你可以把变量看做是一个房间的门牌号，通过门牌号我们可以找 到房间，而通过变量名可以访问到变量(值)。

变量的三要素： (变量名+值+数据类型)

> 变量使用案例

```c
# include <stdio.h>
void main(){
	
	int num = 1 ; //整型 
	double score = 2.3; //小数 
	char gender = 'A';//字符 
	char name[] = "尚硅谷";//字符串
	/**
	如果输出的是整数 -->  %d
	如果输出的是小数 -->  %f ，如果希望保留到小数点2位 就 --> %.2f 
	如果输出的是一个字符char  -->  %c
	如果输出的是一个字符串  ---> %s
	*/
	printf("num=%d score=%.2f gender=%c name=%s",num,score,gender,name);
	getchar();// 让窗口停留
}
```

**==变量使用注意事项==**

1) 变量表示内存中的一个存储区域（不同的数据类型，占用的空间大小不一样） 

2) 该区域有自己的名称 和类型 

3) 变量必须先声明，后使用 

4) 该区域的数据可以在**同一类型范围**内不断变化 

5) 变量在同一个作用域内不能重名 

7) 代码演示 





### 数据类型

在 C 语言中，数据类型指的是用于声明不同类型的变量或函数的一个广泛的系统。变量的类型决定了变量存储占用的空间，以及如何解释存储的位模式。

**C 中的类型可分为以下几种：**

![image-20201014122525733](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014122525733.png)

**==注意：==**

1、在c中，没有字符串类型，是使用字符串数组来表示字符串

2、在不同系统上，部分数据类型的字节长度不一样：例如int ，字节数可能为2，也可能为4 



#### 整数类型



下表列出了关于标准整数类型的存储大小和值范围的细节：

| 类型                 | 存储大小        | 值范围                                                   |
| :------------------- | :-------------- | :------------------------------------------------------- |
| char                 | 1 字节          | -128 到 127 或 0 到 255                                  |
| unsigned char        | 1 字节          | 0 到 255                                                 |
| signed char          | 1 字节          | -128 到 127                                              |
| **int / signed int** | **2 或 4 字节** | **-32,768 到 32,767 或 -2,147,483,648 到 2,147,483,647** |
| **unsigned int**     | **2 或 4 字节** | **0 到 65,535 或 0 到 4,294,967,295**                    |
| short                | 2 字节          | -32,768 到 32,767                                        |
| unsigned short       | 2 字节          | 0 到 65,535                                              |
| long                 | 4 字节          | -2,147,483,648 到 2,147,483,647                          |
| unsigned long        | 4 字节          | 0 到 4,294,967,295                                       |

解释：signed int 就是不区分正负的类型   精度> int的精度





**==整型的使用细节==**

在不同操作系统和不同位数系统下，整型字节的差异

![image-20201014131639731](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014131639731.png) 

1、各种类型的存储大小与操作系统、系统位数和编译器有关*，目前通用的以 64 位系统为主

在实际工作中，c 程序通常运行在 linux/unix 操作系统下

2、 C 语言的整型类型，分为有符号 signed 和无符号 unsigned 两种，默认是 signed 

3、C 程序中整型常声明为 int 型，除非不足以表示大数，才使用 long long 

4、 bit(位): 计算机中的最小存储单位。byte(字节):计算机中基本存储单元。 

1byte = 8bit [二进制再详细说，简单举例一个 short 3 和 int 3 ] 



示意图

**short 3** 在内存中占有 2 字节 

![image-20201014143609277](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014143609277.png)

**int 3** 在内存中占有 4 个字节

![image-20201014143831186](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014143831186.png)



#### 浮点型

下表列出了关于标准浮点类型的存储大小、值范围和精度的细节：

| 类型          | 存储大小 | 值范围               | 精度      |
| :------------ | :------- | :------------------- | :-------- |
| float 单精度  | 4 字节   | 1.2E-38 到 3.4E+38   | 6 位小数  |
| double 双精度 | 8 字节   | 2.3E-308 到 1.7E+308 | 15 位小数 |

浮点数都是近似值

==**细节案例：**==

```c
# include <stdio.h>
void main(){
	float num1 = 1.1; //“初始化”: 从“double”到“float”截断
	//float num1 = 1.1f;// 1.1f就是float
	//double num3 = 1.3; // ok
	//double d4 = 5.12
	double num5 = .512;// 等价于0.512
	double num6 = 5.12e2;// 等价于5.12*(10的二次方
	double num6 = 5.12e-2;// 等价于5.12*(10的负二次方)
	getchar();
}
```

#### 字符类型char

字符类型可以表示单个字符,**字符类型是** **char**，char 是 1 个字节(可以存**字母或者数字**)，多个字符称为**字符串**，在 

C 语言中 使用 char 数组 表示，数组不是基本数据类型，而是构造类型[关于数组我们后面详细讲解.] 

**简单案例：**

```c
# include <stdio.h>
void main(){
	char c1 = 'A'; 
	char c2 = '0';
	char c3 = '\t';
	printf("c1=%c c3=%c c2=%c", c1, c3, c2); //%c 表示以字符的形式输出 
	getchar(); 
}
```

**结果：**

![image-20201014150648276](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014150648276.png) 

(1)、字符常量是用单引号('')括起来的单个字符。例如：char c1 = 'a'; 

char c3 = '9'; 

(2)、 C 中还允许使用转义字符‘\’来将其后的字符转变为特殊字符型常量。例如：char c3 = ‘\n’; // '\n'表示换 

行符

(3)、 在 C 中，char 的本质是一个整数，在输出时，是 ASCII 码对应的字符。 

(4)、 可以直接给 char 赋一个整数，然后输出时，会按照对应的 ASCII 字符输出 [97] 

(5)、 char 类型是可以进行运算的，相当于一个整数，因为它都对应有 Unicode 码. 

(6)、 案例演示 

```c
# include <stdio.h>
void main(){
	char c1 = 'a'; 
	char c2 = 'b';
	char c3 = 97;//这时当我们以%c 输出时，就会安装 ASCII 编码表(理解 字符 <==> 数字 对应关系 ) 对应的 97 对应字 符输出
	int num = c1+10; // =97+10 = 107
	printf("c1=%c c2=%c c3=%c", c1, c2, c3); //%c 表示以字符的形式输出 
	

	// 注意：
	// 1、vs2010 编辑器是c89
	// 2、要求变量的定义在语句之前
	printf("num=%d",num); //“num”: 未声明的标识符

	getchar(); 
}
```

**字符类型本质探讨:**

字符型 存储到 计算机中，需要将字符对应的码值（整数）找出来 

存储：字符'a'——>码值 (97)——>二进制 (1100001)——>存储() 

读取：二进制(1100001)——>码值(97)——> 字符'a'——>读取(显示)



#### 布尔类型(boolean)

1、 C 语言标准(C89)没有定义布尔类型，所以 C 语言判断真假时以 0 为假，非 0 为真 [案例] 

2、但这种做法不直观，所以我们可以借助 C 语言的宏定义 。 

3、C 语言标准(C99)提供了_Bool 型，_Bool 仍是整数类型，但与一般整型不同的是，_Bool 变量只能赋值为 0 或 1， 

非 0 的值都会被存储为 1，C99 还提供了一个头文件 <stdbool.h> 定义了 bool 代表_Bool，true 代表 1，false 代 

表 0。只要导入 stdbool.h ，就能方便的操作布尔类型了 , 比如 bool flag = false

- 条件控制语句； if 

- 循环控制语句； while ...

```c
# include <stdio.h>
void main(){
	int isPass = -1; 
	if(isPass) { // 0表示假，非0表示真
		printf("通过考试");
	}
}
```



**宏定义案例：**

```c
# include <stdio.h>

// 宏定义
#define BOOL int 
#define TURE 1
#define FALSE 0

void main(){
	BOOL isOK = TURE; // 等价于int isOK = 1
	if(isOK){
		printf("ok");
	}
	getchar();	
}
```





#### 基本数据类型转换

> 自动类型转换

当 C 程序在进行赋值或者运算时，**精度小的类型自动转换为精度大的数据类型**

![image-20201014180451782](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014180451782.png)



> 数据类型自动转换表规则

![image-20201014180736675](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014180736675.png) 

**演示1：**

char->int->double（精度低的转化成精度高的）

```c
# include <stdio.h>
void main(){
	char c1 = 'a'; //ok
	int num1 = c1;  // ok
	double d1 = num1; //ok
	printf("d1= %.2f",d1);
	getchar();
}
```

**演示2:** 

不同类型相加：精度低的相转化为精度高的再进行相加（short转成int，再和int相加）

```c
# include <stdio.h>
void main(){
	short s1 = 10;
	int num2 = 20; 
	int num3 = s1 + num2;	//ok
	printf("num3 = %d",num3);
	getchar();
}


```

**演示3：**

精度高的转化为精度低的类型（double赋值给float），出现精度损失现象

```c
# include <stdio.h>
void main(){
	float f1 = 1.1f; //ok 
	double d2 = 4.58667435; 
	f1 = d2; // 出现精度损失 (double -> float ) 
	printf("f1=%.8f", f1); // 期望： 4.58667435 
	getchar(); 
}
```

运行结果：

![image-20201014182654610](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014182654610.png) 



#### 强制类型转换

将精度高的数据类型转换为精度小的数据类型。使用时要加上强制转换符 ( )，但可能造成精度降低或溢出,格 外要注意。

```c
# include <stdio.h>
void main(){
	double d1 = 1.934; 
	int num = (int)d1; //这里注意，不是进行四舍五入，而是直接截断小数后的部分
	//printf("num = %d",num);
    
    
    //强制转换只对最近的数有效, 如果希望针对更多的表达式转换，使用（） 
	int num2 = (int)3.5 * 10 + 6 * 1.5; // 	3 * 10 + 6 * 1.5 = 30 + 9.0 = 39.0 
	int num3 = (int)(3.5 * 10 + 6 * 1.5); // 35.0 + 9.0 = 44.0 -> int = 44
    
	getchar();
}	
```

练习案例：

```c
# include <stdio.h>
void main(){
	char c = 'a'; 
	int i = 5; 
	float d = .314F; 
	double d2 = 1.0; 
	//double result = c+i+d; 
	// float -> double 
	char result = c+i+d+d2; // 提示? // 警告 double -> char 
	getchar();
}
```

总结：

- 当进行数据的从 精度高——>精度低，就需要使用到强制转换 
- 强转符号只针对于**最近的操作数有效**，往往会使用**小括号提升优先**级







### 键盘输入语句

在编程中，需要接收用户输入的数据，就可以使用键盘输入语句来获取。

演示：

```c
# include <stdio.h>
void main() {
	char name[10] = "";
	int age = 0;
	double sal = 0.0;
	char gender = ' ';
	
	printf("请输入用户名：");
	scanf("%s",name);

	printf("请输入年龄：");
	scanf("%d",&age); // 因为我们将得到输入存放到 age 变量指向地址,因此需要加 &
	
	printf("请输入薪水：");
	scanf("%lf",&sal); // lf%:接受double类型

	printf("请输入性别（m/f）：");
	scanf("%c",&gender); //回车被gender接受了，所以为空
	scanf("%c",&gender);// 这个地方才是等待用户输入

	printf("\n name %s age %d sal %.2f  gender %c",name,age,sal,gender);
	getchar();//接受到一个回车
	getchar();//这个getchar()才会让控制台暂停
}
```



![image-20201015152855218](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201015152855218.png) 







### ASCII 码介绍(了解)

1、在计算机内部，所有数据都使用==二进制==表示。每一个二进制位（bit）有 0 和 1 两种状态，因此 8 个二进制 

位就可以组合出 256 种状态，这被称为一个字节（byte）。一个字节一共可以用来表示 ==256 （2的8次方）==种不同的状态， 

每一个状态对应一个符号，就是 256 个符号，从 0000000 到 11111111。 

2、 ASCII 码：上个世纪 60 年代，美国制定了一套字符编码，对英语字符与二进制位之间的关系，做了统一规定。 

这被称为 ASCII 码。ASCII 码一共规定了 127 个字符的编码，比如空格“SPACE”是 32（二进制 00100000）， 

大写的字母 A 是 65（二进制 01000001）。这 128 个符号（包括 32 个不能打印出来的控制符号），只占用了一 

个字节的后面 7 位，最前面的 1 位统一规定为 0。 

3、 看一个完整的 ASCII 码表 

 ASCII 码表参考地址：https://tool.oschina.net/commons?type=4







### C 语言标准库 – 参考手册

> 介绍：C 标准库是一组 C 内置函数、常量和头文件，比如 <stdio.h>、<stdlib.h>、<math.h>，等等。这个标 
>
> 准库可以作为 C 程序员的参考手册。

标准库参考地址：https://www.runoob.com/cprogramming/c-data-types.html

![image-20201014114703478](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014114703478.png) 

 



## 以上内容课后习题

题目1：

```c
#include <stdio.h>
void main(){

	/*要求： 

	a、用变量将姓名、年龄、成绩、性别、爱好存储 

	b、添加适当的注释 

	c、添加转义字符*/ 

	//分析：使用不同的变量来保存对应的数据 

	char name[10] = "张三"; //字符数组，存放字符串 
	short age = 23; 
	float score = 78.5f; 
	char gender = 'M'; //男生尚硅谷高校大学生 C 语言课程 
	char hobby[20] = "篮球，足球"; 
	printf("姓名\t 年龄\t 成绩\t 性别 \t 爱好\n%s\t%d\t%.2f\t%c\t%s", name,age,score,gender,hobby); 
	getchar(); 
}
```

题目2：

```c
#include <stdio.h>
void main(){

	int number1; 
	int number2; 
	int number3; 
	int number4 = 50; 
	int number5; 
	number1 = 10; 
	number2 = 20; 
	number3 = number1 + number2; //30 
	printf("\nNumber3 = %d" , number3);//30 
	number5 = number4 - number3;//20 
	printf("\nNumber5 = %d" , number5);//20 
	getchar(); 

}
```

题目3、4：

```c
#include <stdio.h> 

	void main() { 

		// ************************************* 
		// 小小计算器 
		//************************************* 
		//10 + 5 =15 
		//10 - 5 = 5 
		//10 * 5 = 50 
		//10 / 5 = 2 
		//分析 
		//1. 定义两个 int 
		//2. 根据要求进行计算，得到不同结果，可以再定义变量 
		int n1 = 10; 
		int n2 = 5; 
		int sum = n1 + n2; 
		int sub = n1 - n2; 
		int mul = n1 * n2; 
		int div = n1 / n2; 
		int mod = n1 % n2;
		int num = 11; 
		//输出 
		printf("\n*************************************"); 
		printf("\n 	小小计算器"); 
		printf("\n*************************************"); 
		printf("\n %d + %d = %d", n1, n2, sum); 
		printf("\n %d - %d = %d", n1, n2, sub); 
		printf("\n %d * %d = %d", n1, n2, mul); 
		printf("\n %d / %d = %d", n1, n2, div); 
		printf("\n %d 模 %d = %d", n1, n2, mod); 
		//判断 num 是不是偶数还是基数 
		//if-else 后面要学习的分支结构，后面会详解讲解 
		if(num % 2 == 0) { //偶数 
			printf("\n%d 是偶数", num); 

		} else { 
			printf("\n%d 是奇数", num); 
		}
		getchar(); 
}
```



![image-20201014202401601](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014202401601.png) 





## 常量



### 整数常量

整数常量可以是十进制、八进制或十六进制的常量。前缀指定基数：0x 或 0X 表示十六进制，0 表示八进制，不带前缀则默认表示十进制。整数常量也可以带一个后缀，后缀是 U 和 L 的组合，U 表示无符号整数（unsigned），L 表示长整数（long）。后缀可以是大写，也可以是小写，U 和 L 的顺序任意

整数常量举例说明：

```c
85 /* 十进制 */

0213 /* 八进制 */

0x4b /* 十六进制 */ 八进制和十六进制后面解释

30 /* 整数 */

30u /* 无符号整数 */

30l /* 长整数 */

30ul /* 无符号长整数 */
```



### 浮点常量

浮点常量由整数部分、小数点、小数部分和指数部分组成。您可以使用小数形式或者指数形式来表示浮点常量

```c
3.14159; //double 常量
314159E-5; // 科学计数法
3.1f; //float常量
```



### 字符常量

字符常量是括在单引号中，例如，'x' 可以存储在 char 类型的变量中。字符常量可以是一个普通的字符（例如 'x'）、一个转义序列（例如 '\t'）

```c
'X' 
'Y' 
'A'
'b'
'1' 
'\t
```



### 字符串常量

```c
"hello, world" 
"北京"
"hello \
world"
```



### 常量的定义

1、使用 #define 预处理器
2、使用 const 关键字



> #define 预处理器

```c
#define 常量名 常量值
```

```c
#include <stdio.h>
#define PI 3.14 //定义常量 PI 常量值 3.14 

int  main() { 
		//PI = 3.1415 可以吗?=》 不可以修改，因为 PI 是常量 
		//可以修改 PI 值? 
		//PI = 3.1415; //提示 = 左值 必须是可修改的值 
		double area; 
		double r = 1.2;//半径 
		area = PI * r * r; 
		printf("area=%.2f",area);
		getchar();
		return 0;
}
```





> const

可以使用 const 声明指定类型的常量

```c
const 数据类型 常量名 = 常量值; //即就是一个语句
```

```c
#include <stdio.h>
//1. const 是一个关键字，规定好，表示后面定义了一个常量 
//2. PI 是常量名，即是一个常量，常量值就是 3.14 
//3. PI 因为是常量，因此不可以修改 
//4. const 定义常量时，需要加 分号
const double PI = 3.14;  
int main() {
	//PI = 3.1415 可以吗?
	double area; 
	double r = 1.2;
	area = PI * r * r;
	printf("面积 : %.2f", area);
	getchar();
	return 0;
}
```



> const 和 #define 的区别 

1、const 定义的常量时，带类型，define 不带类型 

2、const 是在 ==编译、运行==的时候起作用，而 define 是在编译的==预处理==阶段起作用 

3、define 只是简单的替换，没有类型检查。简单的字符串替换会导致==边界效应==

4、const 常量可以进行调试的，define 是不能进行调试的，主要是预编译阶段就已经替换掉了，调试的时候就没它 

了 

5、const 不能重定义，不可以定义两个一样的，而 define 通过 undef 取消某个符号的定义，再重新定义 

6、define 可以配合#ifdef、 #ifndef、 #endif 来使用， 可以让代码更加灵活，比如我们可以通过#define 来 启动 

或者关闭 调试信息。

**区别6的案例：**

```c
#include <stdio.h> 
#define DEBUG 
	void main() { 
	#ifdef DEBUG //如果定义过 DEBUF 
			printf("ok, 调试信息"); 
	#endif 
	#ifndef DEBUG //如果没有定义过 DEBUF 
			printf("hello, 另外的信息"); 
	#endif 
			getchar(); 
}
```



## 运算符

> 运算符是一种特殊的符号，用以表示数据的运算、赋值和比较等。
> 1) 算术运算符 (+, -, * , / , %)
> 2) 赋值运算符 (= += -= ..)
> 3) 关系运算符（比较运算符）(比如 > >= < <= == 等等)
> 4) 逻辑运算符 (&& 逻辑与 || 逻辑或 ! 逻辑非)
> 5) 位运算符 (& 按位与 | 按位或 ^ 按位异或 ~ 按位取反等等)
> 6) 三元运算符 ( 表达式 ? 表达1 : 表达2)

### 算术运算符

算术运算符是对==数值类型的变量==进行运算的，在C程序中使用的非常多。

![image-20201015121651850](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201015121651850.png)

案例演示：

```c
#include <stdio.h> 

void main() { 
	//处理的流程 10 / 4 = 2.5 ==截取整数==> 2 => 2.00000 
	double d1 = 10 / 4; // 
	double d2 = 10.0 / 4; //如果希望保留小数，参与运算数必须有浮点数 
	//给大家一个取模的公式 
	// a % b = a - a / b * b 
	int res1 = 10 % 3; // 求 10/3 的余数 1 
	int res2 = -10 % 3; // = -10 - (-10) / 3 * 3 = -10- (-3) * 3 = -10 + 9 = -1 
	int res3 = 10 % -3; // 10 - 10 / (-3) * (-3) = 10 - 9 = 1 
	int res4 = -10 % -3; // ? -1 
	//++ 的使用 
	int i = 10;
	int j = i++; // 运算规则等价是 int j = i; i = i + 1; ==> j = 10, i=11 
	int k = ++i; // 运算规则等价 i = i + 1; int k = i; ===> i=12, k =12 
	printf("\n i=%d j=%d", i, j); //i=12 
	j=10; 
	printf("\n i=%d k=%d", i ,k );// i = 12 k = 12 
	printf("\nd1=%f d2=%f res1=%d res2=%d res3=%d res4=%d", d1, d2, res1, res2, res3, res4); 
	//++ 或者 -- 还可以独立使用, 就相当于自增 
	//k++ 等价于 k = k +1 
	//++k 等价于 k= k +1 
	//如果独立使用 ++k 和 k++ 完全等价 
	k++; // k = 13 
	++k;// k = 14 
	printf("\nk=%d", k); //k = 14 
	getchar(); 
}
```



>  细节说明

1. 对于除号“/”，它的整数除和小数除是有区别的：整数之间做除法时，只保留整数部分而舍弃小数部分。 例如：int x= 10/3 ,结果是 
2.  当对一个数取模时，可以等价 a%b=a-a/b*b ， 这样我们可以看到 取模的一个本质运算。
3. 当 自增 当做一个独立语言使用时，不管是 ++i; 还是 i++; 都是一样的，等价
4. 当 自增 当做一个 表达式使用时 j = ++i 等价 i = i + 1; j = i;
5.  当 自增 当做一个 表达式使用时 j = i++ 等价 j = i; i = i + 1;





### 关系运算符

> 1、关系运算符的结果要么是==真(非0 表示)==，要么是==假(0 表示)==
> 2、关系表达式 经常用在 if结构的条件中或循环结构的条件中

![image-20201015124250735](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201015124250735.png) 

演示案例：

```c

```





> 细节说明

1、 关系运算符的结果要么是真(非0 表示, 默认使用1)，要么是 假(0 表示) 

2、 关系运算符组成的表达式，我们称为**关系表达式**。 a > b 

3、比较运算符 "=="(关系运算符) 不能误写成 "=" (赋值)



### 逻辑运算符

> 用于连接多个条件（一般来讲就是关系表达式），最终的结果要么是真(非0 
> 表示)，要么是 假(0 表示) 。

**逻辑运算符一览**

下表显示了 C 语言支持的所有逻辑运算符。假设变量 **A** 的值为 1，变量 **B** 的值为 0

![image-20201015124907056](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201015124907056.png)

演示案例：



1、`&&`的使用

```c
# include <stdio.h>
void main() {
	double score = 70;//成绩
	if(score>=60 && score<=80){
		printf("ok1");
	} else {
		printf("ok2");
	}
	getchar();
}
```



```c
# include <stdio.h>
void main() {
	int a = 10, b = 99;
	if(a < 20 && b++>100) { 
		printf("ok100");
	}
	printf("b=%d\n", b); //b=100
	getchar();
}
```



3、`||`的使用

```c
# include <stdio.h>
void main() {
	int a = 10, b = 99;
	if(a < 5 || b++>100) {
		printf("ok100");
	}
	printf("b=%d\n", b);
	getchar();
}
```





### 赋值运算符



> 赋值运算符就是将=某个运算后的值=，赋给==指定的变量==。



![image-20201015130605315](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201015130605315.png) 



> 赋值运算符特点



1、运算顺序从右往左

2、赋值运算符的左边 只能是变量,右边 可以是变量、表达式、常量值

3、复合赋值运算符等价于下面的效果

比如：a+=3;等价于a=a+3;



### 位运算符

位运算符作用于位， 并逐位执行操作。

![image-20201015130834027](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201015130834027.png) 



### 三目运算符

> 基本语法：

==条件表达式 ? 表达式1: 表达式2;==

1)、如果条件表达式为非0 (真)，运算后的结果是表达式1；

2)、如果条件表达式为0 (假)，运算后的结果是表达式2；

3)、口诀: 一灯大师 =》 一真大师

演示案例：

```
# include <stdio.h>
void main() {
	int a = 10;
	int b = 99;
	int res = a > b ? a++ : b--;
	printf("res=%d",res); ///res =99
	getchar();
}
```

演示2：求3个数之间的最大值

```c
int a = 10;
int b = 100;
int c = 199;
int max = a > b ? a : b
int max2 = max>c ? max : c // max2为三个数中的最大值
```



### 运算符优先级

>  运算符优先级和结合性一览表

表地址：https://blog.csdn.net/u013630349/article/details/47444939



**上表中可以总结出如下规律：**

1. 结合方向只有三个是从右往左，其余都是从左往右。
2. 所有==赋双目运算符中只有值运算符==的结合方向是从右往左。
3. 另外两个从右往左结合的运算符也很好记，因为它们很特殊：一个是==单目运算符==，一个是==三目运算符==。
4. C语言中有且只有一个三目运算符。
5. ==逗号运算符的优先级最低==，要记住。
6. 此外要记住，对于优先级：==算数运算符 > 关系运算符 > 逻辑运算符 > 赋值运算符 > 逗号运算符==。逻辑运算符中“逻辑非 !”除外。
7. 不需要刻意去记忆，在使用中去熟练它！

**==一些容易出错的优先级问题==**

上表中，优先级同为1 的几种运算符如果同时出现，那怎么确定表达式的优先级呢？这是很多初学者迷糊的地方。下表就整理了这些容易出错的情况：

| 优先级问题                                    | 表达式               | 经常误认为的结果                                        | 实际结果                                                     |
| --------------------------------------------- | -------------------- | ------------------------------------------------------- | ------------------------------------------------------------ |
| . 的优先级高于 *（-> 操作符用于消除这个问题） | *p.f                 | p 所指对象的字段 f，等价于： (*p).f                     | 对 p 取 f 偏移，作为指针，然后进行解除引用操作，等价于： *(p.f) |
| [] 高于 *                                     | int *ap[]            | ap 是个指向 int 数组的指针，等价于： int (*ap)[]        | ap 是个元素为 int 指针的数组，等价于： int *(ap [])          |
| 函数 () 高于 *                                | int *fp()            | fp 是个函数指针，所指函数返回 int，等价于： int (*fp)() | fp 是个函数，返回 int*，等价于： int* ( fp() )               |
| == 和 != 高于位操作                           | (val & mask != 0)    | (val &mask) != 0                                        | val & (mask != 0)                                            |
| == 和 != 高于赋值符                           | c = getchar() != EOF | (c = getchar()) != EOF                                  | c = (getchar() != EOF)                                       |
| 算术运算符高于位移 运算符                     | msb << 4 + lsb       | (msb << 4) + lsb                                        | msb << (4 + lsb)                                             |
| 逗号运算符在所有运 算符中优先级最低           | i = 1, 2             | i = (1,2)                                               | (i = 1), 2                                                   |


这些容易出错的情况，希望读者好好在编译器上调试调试，这样印象会深一些。一定要多调试，光靠看代码，水平是很难提上来的。调试代码才是最长水平的。



### 运算符总结案例：

```c
# include <stdio.h>
void main() {
	//定义变量保存 秒数，打印输出 xx 小时 xx 分钟 xx 秒 
	//思路 
	//1. 定义变量保存 秒数 second 
	//2. 定义变量保存 小时 hour 
	//3. 定义变量保存 分钟 min 
	//4. 定义变量保存 剩余描述 leftSecond 
	int second = 894567;
	int hour = second / 3600 ; // 一个小时有 3600 秒 
	int min = second % 3600 / 60; // 
	int leftSecond = second % 60; 
	printf("%d 秒 合%d 小时%d 分钟%d 秒", second, hour, min, leftSecond); 
	getchar(); 

}
```





## 二进制和位运算 

### （1）、其他进制转成10进制

> 二进制转换成十进制示例

```
0123 = 3 * 8 ^ 0 + 2 * 8 ^ 1 + 1 * 8^2 = 3+16+64=83
```

> 十六进制转换成十进制示例

```
0x34A = 10 * 16 ^ 0 + 4 * 16 ^1 + 3 * 16^2 = 10+64+768=842
```



### （2）、十进制转其他进制

>十进制转换成二进制

==规则：==将该数不断除以2，直到商为0为止，然后将每步得到的余数倒过来，就是对
应的二进制。
案例：请将 56 转成二进制

```
56 => 111000
```

如图：

![image-20201019093058273](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019093058273.png)



> 十进制转换成八进制

规则：将该数不断除以8，直到商为0为止，然后将每步得到的余数倒过来，就是对
应的八进制。 案例：请将 156 转成八进制

156 =>234

计算过程如图：

![image-20201019093544396](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019093544396.png) 





> 十进制转换成十六进制

规则：将该数不断除以16，直到商为0为止，然后将每步得到的余数倒过来，就是
对应的十六进制。
案例：请将 356 转成十六进制

356 => 164

**计算过程如图：**

![image-20201019093718527](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019093718527.png)



### （3）、二进制转换成八进制、十六进制

> 二进制转换成八进制



规则：从低位开始,将二进制数每==三位一组==，转成对应的==8进制==数即可。
案例：请将 11 010 101 转成八进制

```c
11010101 => 0 3 2 5
```

> 二进制转换成十六进制



规则：低位开始，将二进制数每四位一组，转成对应的==16进制==数即可。
案例：请将 1101**0101** 转成十六进制

```
11010101 = 0xD5
```



### （4）、八进制、十六进制转成二进制



> 八进制转换成二进制



规则：将八进制数每1位，转成对应的一个3位的二进制数即可。
案例：请将 0237 转成二进制

```
0237 = 10011111
```



> 十六进制转换成二进制

规则：将十六进制数每1位，转成对应的4位的一个二进制数即可。
案例：请将 0x23B 转成二进制

```
B=11=> 1011
0x23B = 1000111011
```



>  位运算的思考题 

==请看下面的代码段，回答 a,b,c,d,e 结果是多少?==

```c
# include <stdio.h>
void main() {
	int a=1>>2; // 1 向右位移2位 , 这里还涉及到二进制中 原码，反码，补码
	int b=-1>>2;
	int c=1<<2;//
	int d=-1<<2;//
	//a,b,c,d,e结果是多少
	printf("a=%d b=%d c=%d d=%d ",a,b,c,d);
	getchar();
}
```





==请回答在C中，下面的表达式运算的结果是: (位操作)==

```c
~2=? // 按位取反
2&3=?
2|3=?
~-5=?
13&7=?
5|4=?
-3^3=?
```









>  二进制再运算中的说明

二进制是逢2进位的进位制，0、1是基本算符。
现代的电子计算机技术全部采用的是二进制，因为它只使用0、1两个数字符号
，非常简单方便，易于用==电子方式实现==。计算机内部处理的信息，都是采用二进
制数来表示的。二进制（Binary）数用0和1两个数字及其组合来表示任何数。进
位规则是“逢2进1”，数字1在不同的位上代表不同的值，按从右至左的次序，这
个值以二倍递增





### 原码、反码、补码



1. 二进制的最高位是符号位: 0表示正数,1表示负数
2.  正数的原码，反码，补码都一样 (三码合一)
3.  ==负数的反码===它的原码符号位不变，其它位取反(0->1,1->0)
4.  ==负数的补码===它的反码+1 
5.  0的反码，补码都是0 
6. 在计算机运算的时候，都是==以补码的方式==来运算的



### 位运算符

>  中位运算符介绍

![image-20201019145355176](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019145355176.png)



> 它们的运算规则是：

按位与& ： 两位全为１，结果为1，否则为0
按位或| : 两位有一个为1，结果为1，否则为0
按位异或 ^ : 两位一个为0,一个为1，结果为1，否则为0
按位取反 : 0->1 ,1->0
比如：~2=?    ~-5=?    2&-3=?     2|3=? 2^3=?



**~2=?** 

![image-20201019152524917](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019152524917.png)



求-3的补码：

![image-20201019154321108](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019154321108.png)













## 程序的控制结构

在程序中，程序运行的流程控制决定程序是如何执行的，是我们必须掌握的，主要有三大流程控制语句。



### 顺序结构

程序**从上到下逐行**地执行，中间没有任何判断和跳转 

![image-20201019162723689](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019162723689.png) 



### 选择结构

分支控制if-else介绍：单分支、双分支、多分支

> 单分支

基本语法：

```c
if(条件表达式){
	执行代码块；
}
```

==说明：当条件表达式为真 (非0) 时，就会执行 { } 的代码，返回假(0) 时，不会执行{ } 的代码==



流程图演示：

![image-20201019163018855](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019163018855.png) 



> 双分支

基本语法：

```c
if(条件表达式){
	执行代码块1; 
}
else{
	执行代码块2; 
}
```

==说明：当条件表达式成立(为真)，执行代码块1，否则执行代码块2==



双分支对应的流程图:

![image-20201019163136776](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019163136776.png) 



> 多分支

基本语法：

```c
if(条件表达式1){
	执行代码块1;
}
else if (条件表达式2){
	执行代码块2;
}
……
else{
	执行代码块n;
}
```

![image-20201019163334640](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019163334640.png)



> 嵌套分支

在一个分支结构中又完整的嵌套了另一个完整的分支结构，里面的分支的结构称为内层分支外面的分支结构称为外层分支。

嵌套分支不适合过多，最多不要超过3层

基本语法：

```c
if(){
	if(){ 
        //被包含的可以是单分支，双分支，多分支
	}else{ 
    
    } 
}
```



**案例：**

参加百米运动会，如果用时8秒以内进入决赛，否则提示淘汰。并且根据性别提示进入男子组或女子组。【可以让学员先练习下】, 输入成绩和性别，进行判断。1分钟思考
思路

double second; char gender;

```c
# include <stdio.h>
void main() {
	/*

	参加百米运动会，如果用时8秒以内进入决赛，否则提示淘汰。并且根据性别提示进入男子组或女子组。
	【可以让学员先练习下】, 输入成绩和性别，进行判断。1分钟思考
	分析：
	1、用double second;保存用的时间
	2、用char gender 保存性别	
	double second; char gender;

	*/
	double second = 0.0; 
	char gender = '  ';
	printf("请输入运动成绩-时间(s)：");
	scanf("%lf",&second);

	if(second <=8 ){
		printf("请输入性别(m/f)"); // 接收到上次回车 scanf("%c", &gender); 
		scanf("%c", &gender); 	// 这次才接收到性别

		if(gender == 'm'){
			printf("请进入男子组");
		}else{
			printf("请进入女子组");
		}
	}else{
		printf("你被淘汰了！");
	}
	getchar();
	getchar();
}
```





> switch分支结构

基本语法:

```c
switch(表达式){
    case 常量1： //当表达式 值等于常量1
    语句块1;
    break; //退出switch
    
    case 常量2; // 含义一样
    语句块2;
    break;
    ...
    case 常量n;
    语句块n;
    break;
    default:
    default语句块;
	break; 
}
```

流程图：

![image-20201019171226592](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201019171226592.png)



演示案例：

请编写一个程序，该程序可以接收一个字符，比如: a,b,c,d,e,f,g 

a 表示星期一，b 表示星期 

二 … 根据用户的输入显 

示相依的信息.要求使用 

switch 语句完成 

```c
# include <stdio.h>
void main() {
	char c1 = ' '; 
	char c2 = 'a'; 
	printf("请输入一个字符(a,b,c,d)"); 
	scanf("%c", &c1); 
	//switch 
	//表达式： 任何有值都可以看成是一个表达式 
	switch(c1) { 
	case 'a' : //'a' => 97 
		printf("今天星期一, 猴子穿新衣"); 
		break; //退出 switch 
	case 'b' : 
		printf("今天星期二, 猴子当小二"); 
		break;
	case 'c' : 
		printf("今天星期二, 猴子当小二"); 
		break;
	case	 'd':
		printf("今天星期四，猴子当小四");
		break;
	case 'e':
		printf("今天星期五，猴子当小五");
		break;
	case 'f':
		printf("今天星期六，猴子当小六");
		break;
	case 'g':
		printf("今天星期天，猴子当小天");
		break;
	default:
		printf("输入错误！");
	}
	getchar(); 
	getchar();
}
```



**switch 细节讨论** 

1. switch 语句中的 expression 是一个常量表达式，必须是一个整型(charshort, int, long 等) 或枚举类型 
2.  case 子句中的值必须是常量,而不能是变量 
3.  default 子句是可选的，当没有匹配的 case 时，执行 default 
4. break 语句用来在执行完一个 case 分支后使程序跳出 switch 语句块； 
5.  如果没有写 break，会执行下一个 case 语句块，直到遇到 break 或者执行到 switch 结尾, 这个现象称为穿透.









### 循环结构

基本介绍:听其名而知其意,就是让你的代码可以循环的执行.

#### for 循环

循环变量定义:

```c
for(①循环变量初始化;②循环条件;④循环变量迭代){ 

③循环操作(多条语句); 

} 
```



==注意事项和细节说明==

1、循环条件是 返回一个表示真(非0)假(0) 的表达式
2、or(;循环判断条件;) 中的初始化和变量迭代可以不写（写到其它地方），但是两边的分号不能省略。
3、 循环初始值可以有多条初始化语句，但要求类型一样，并且中间用逗号隔开，循环变量迭代也可以有多条变量迭代语句，中间用逗号隔开。

`for(i = 0, j = 0; j < count; i++, j += 2)`



例题：

1、打印1~100之间所有是9的倍数的整数的个数及总和. [使用for完成]

```c
# include <stdio.h>
void main() {
	int count = 0;
	int sum = 0;
	int i	;
	for(i = 1; i<=100; i++){
		if(i%9 == 0){
			sum+=i;
			count++;
		}
	}
	printf("个数：%d,总和：%d",count,sum);
	getchar();
}

```



2、完成下面的表达式输出

![image-20201020091612847](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020091612847.png) 

```c
# include <stdio.h>
void main() {
	int i = 0;
	int j = 0;
	for(i=0; i<=6; i++){
		for(j=0; j<=6; j++){
			if(i+j == 6){
				printf("%d+%d\n",i,j);
			}
		}
	}
	getchar();
}
```





#### while循环



``` c
①循环变量初始化;
while(②循环条件){
    
	③循环体(多条语句);
	④循环变量迭代; 
}
```

案例：输出5个`你好！贺晶晶`

```c
# include <stdio.h>
void main() {
	int i = 1;
	while(i<=5){
		printf("你好！贺晶晶 \n");
		i++;
	}
	getchar();
}
```

![image-20201020092041512](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020092041512.png) 



**流程图分析：**

![image-20201020092743447](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020092743447.png) 



联系案例：

```c
# include <stdio.h>
# include <string.h>
void main() {
	char  name[10] = "";
	while(strcmp(name,"exit") != 0){
		printf("请输入名字：");
		scanf("%s",name);// 这里不需要加&，因为数组的名称就是地址
		printf("你输入的名字是=%s \n",name);
	}
	getchar();
	getchar();

}
```



#### do while循环



```c
①循环变量初始化;
do{
	②循环体(多条语句);
	③循环变量迭代; }
	while(④循环条件);
	
注意：do – while 后面有一个 分号，不能省略
```

![image-20201020094717016](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020094717016.png) 



案例：输出5个`你好！贺猪猪`

```c
#include <stdio.h> 
void main() { 
	//输出 
	int i = 1; //循环变量初始化 
	while( i <= 5) { //循环条件 
		printf("你好 贺猪猪 i=%d \n" , i); //循环语句 
		i++; // 变量迭代 
	}
	getchar(); 

}
```



案例2：

如果老公同意老婆购物，则老婆将一直购物，直到老公说不同意为止   [printf("老婆问：我可以继续购物吗？y/n")]

```c
#include <stdio.h> 
void main() { 
	char answer = ' ';
	do{
		printf("老婆问：我可以继续购物吗？y/n：");
		scanf("%c",&answer);
		getchar(); //其回车
	}while(answer == 'y' );

	printf("我的天，老婆终于不购物了~~~~");
	getchar();
}
```

测试运行结果：

![image-20201020100225047](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020100225047.png) 



#### 多重循环控制

1、  将一个循环放在另一个循环体内，就形成了嵌套循环。其中，for ,while ,do…while均可以作为外层循环和内层循环。【建议一般使用两层，最多不要超过3层】, 如果嵌套循环过多，会造成可读性降低
2、 实质上，嵌套循环就是把内层循环当成外层循环的循环体。当只有内层循环的循环条件为false时，才会完全跳出内层循环，才可结束外层的当次循环，开始下一次的循环, 举例说明。
3、设外层循环次数为m次，内层为n次，则内层循环体实际上需要执行m*n次。



**案例：**

1、统计3个班成绩情况，每个班有5名同学，求出各个班的平均分和所有班级的平均分==[学生的成绩从键盘输入]==。

2、统计三个班及格人数，每个班有5名同学。

```c
#include <stdio.h> 
void main() { 
	int classNumber = 3; // 班级个数
	int stuNumber = 5; // 学生个数
	double classTotalScore =0.0; // 个人班级总分
	double allClassTotalScore =0.0; // 个人班级总分
	double score = 0.0; // 接收学生的成绩
	int count = 0; // 及格人数
	int i,j;

	for(i=1;i<=classNumber;i++){
		//每次给一个班级输出成绩时，需要清零
		classTotalScore=0.0;
		for(j=1; j<=stuNumber; j++){
			printf("\n请输入第%d班级的第%d个学生的成绩",i,j);
			scanf("%lf",&score);
			if(score>=60){
				count++;
			}
			classTotalScore +=score;
		}
		allClassTotalScore += classTotalScore;
		printf("第%d班的平均分是%.2f",i,classTotalScore/stuNumber);

	}
	printf("所有班级的平均分%.2f",allClassTotalScore/(stuNumber*classNumber));
	printf("所有班级的及格人数%d",count);
	getchar();// 去回车
	getchar();
}
```



3、打印九九乘法表



```c
#include <stdio.h>
void main(){
	int i,j;
	for(i=1;i<=9;i++){
		for(j=1;j<=i;j++){
			printf("%d * %d = %d ",j,i,i*j);
		}
		printf("\n");
	}
	getchar();
}
```

4、打印金字塔

```c
#include <stdio.h>
void main(){
	int i,j,k;
	int totalLevel = 10;
	for(i=1;i<=totalLevel;i++){
		//输出空格
		for(k=1;k<=totalLevel-i;k++){
			printf(" ");
		}
		// 输出*
		for(j=1;j<=2*i-1;j++){
			printf("*");
		}
        //换行
		printf("\n");
	}
	getchar();
}
```

4、打印空心金字塔

```
#include <stdio.h>
void main(){
	int i,j,k;
	int totalLevel = 10;
	for(i=1;i<=totalLevel;i++){
		//输出空格
		for(k=1;k<=totalLevel-i;k++){
			printf(" ");
		}
		// 输出*
		for(j=1;j<=2*i-1;j++){
			if(j==1 || j==2*i-1 || i ==totalLevel){
				printf("*");
			}else{
				printf(" ");
			}
		}
		//换行
		printf("\n");
	}
	getchar();
}
```

![image-20201020113441431](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020113441431.png) 



==**作业：打印空心菱形：**==

**实现思路：**

**第一步：先实现打印实心矩形**

> 直接通过双重for循环实现

```
*******
*******
*******
*******
*******
*******
*******
```

**第二步：打印实心金字塔**



```
   *  // 3个空格 
  *** // 2个空格
 ***** //1个空格
*******//0个空格 
规律totalLevel -i 个*

   *  // 1个*
  *** // 2个*
 ***** //5个*
*******/ 7个*
规律：2*i-1个*
```

代码实现：

```c
#include <stdio.h>
void main(){
	printArc(10);
}

void printArc(int num){
	int i,j,k;
	for(i=1;i<=num;i++){
		//输出空格
		for(k=1;k<=num-i;k++){
			printf(" ");
		}
		// 输出*
		for(j=1;j<=2*i-1;j++){
			printf("*");
		}
		//换行
		printf("\n");
	}
	getchar();
}
```

![image-20201020120004016](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020120004016.png) 

**第三步：打印实心菱形**

思路：就是再写一个循环打印对称三角形

```
   *  
  *** 
 ***** 
*******
 *****
  ***
   *
```











#### break

> break只是跳出当前循环

![image-20201020134522403](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020134522403.png) 

==假如：随机生成一个数，直到生成了97这个数，看看你一共用了几次?==

解决：

在执行循环的过程中，当满足某个条件时，可以提前退出该循环, 这时，就可能使用break

代码实现：

```c
#include <stdio.h>
void main(){
	int i = 0;
	while(1) {
		printf("\n输出%d",i++);
		if(i==97) {
			break;
		}
	}
	getchar();
}
```







#### continue

>  continue语句用于==结束本次循环==，继续执行下一次循环。



流程图：

![image-20201020134327215](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020134327215.png) 



演示案例：

```c
#include <stdio.h>
void main() {
	int i,j;
	for( j = 0; j < 4; j++){
		for( i = 0; i < 10; i++){
			if(i == 2){
				//看看分别输出什么值，并分析
				continue ;
			}
			printf("i = %d\n" , i);
		}
		printf("================\n");
	}
	getchar();
}// 输出 4次 i=0, 1 ,3,4,5,6,7,8,9

```

结果如图：

![image-20201020122627104](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020122627104.png) 



**注意事项和细节说明:**

continue语句，==只能配合循环语言使用==，不能单独和switch/if使用



**练习题：**

(1)、从键盘读入个数不确定的整数，并判断读入的正数和负数的个数，输入为0时结束程序【使用for循环 ，break, continue完成】 ==【positive 正数，negative】==

```c
#include <stdio.h>
void main() {
	int positive = 0;
	int negative = 0;
	int num = 0; //从控制台输入的个数
	for(; ;) {  //死循环 等效 while(1){}
		printf("请输入一个整数：");
		scanf("%d",&num);
		if(num==0){
			break; // 跳出for循环
		}
		if(num>0){
			positive++;
			continue;
		}
		negative++;
	}
	printf("正数个数%d，负数个数%d",positive,negative);
	getchar();
	getchar();
}
```

运行测试：

![image-20201020132229727](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020132229727.png) 



(2)、某人有 100,000 元,每经过一次路口，需要交费,规则如下: 

当现金>50000 时,每次交 5% 

当现金<=50000 时,每次交 1000 

编程计算该人可以经过多少次路口,使用 while break 方式完成 

```
#include <stdio.h>
void main() {
	/*

	某人有 100,000 元,每经过一次路口，需要交费,规则如下: 
	当现金>50000 时,每次交 5% 
	当现金<=50000 时,	每次交 1000
	编程计算该人可以经过多少次路口,
	使用 while  方式完成
	*/

	//分析：money<1000的时候就过不了
	double money = 100000;
	int count = 0;
	while(1){

		//判断money是后小于1000	
		if(money<1000){
			break;
		}
		if(money>50000){
			money=money*(1-0.05);
			count++;
		}else if(money<=50000){
			money-=1000;
			count++;
		}
	}
	printf("过路口次数为：%d，剩余%.2f",count,money);
	getchar();

}

```

运行结果如图：

![image-20201020134234090](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020134234090.png) 



#### go to

1、C 语言的 goto 语句可以无条件地转移到程序中指定的行。

2、goto语句通常与条件语句配合使用。可用来实现条件转移，跳出循环体等功能。

3、在C程序设计中==一般不主张使用==`goto语句`， 以免造成程序流程的混乱，使理解

和调试程序都产生困难

![image-20201020134657552](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020134657552.png) 

>  基本语法

```c
goto label 

.. . 

label: statement
```





演示：

```c
#include <stdio.h>
void main() {
	printf("start\n");
	goto lable1; //lable1 称为标签
	printf("ok1\n");
	printf("ok2\n");
lable1: 
	printf("ok3\n");
	printf("ok4\n");
	getchar();
}
```

运行结果：

![image-20201020134917477](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020134917477.png) 



### 综合练习：

(1)、判断一个年份是否是闰年

> 分析：
>
> 闰年的判断条件：1、能被400整除的 2、能被4整数且不能被100整除
>
> 程序语句实现
>
> if(year%400==0)
>
> if(year%4==0 && year%100 != 0)

```c
#include <stdio.h>
void main()
{
    int year;
    printf("请输人年份：\n");
    scanf("%d",&year);
	if(year%400 == 0 || (year%4==0 && year%100 != 0)){
		 printf("%d 此年是闰年\n",year);
	} else{
		 printf("%d 此年不是闰年\n",year);
	}
	getchar();
	getchar();
}
```

（2）、1000以内水仙花数：==（例如：153=1^3+5^3+3^3）==

```c
#include <stdio.h>
int main()
{
	int i;
	for(i = 0; i<1000; i++){
		int ge = i%10; // 个位
		int shi = i%100/10; //  十位
		int bai= i/100; //百位
		if(i = ge*ge*ge + shi*shi*shi + bai*bai*bai){
			printf("%d是水仙花数\n",i);
		}
	}
	getchar();
}
```

运行结果如图：

![image-20201020141614776](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020141614776.png) 



(3)、编写程序，根据输入的`月份和年份`，求出该月的天数（1-12）==注意：需要考虑闰年(2 月份 29)和平年(2 月份 28)==

```c
#include <stdio.h>
void main(){
	int year = 2019; 
	int month = 2;
	switch(month) { 
	case 1: 
	case 3: 
	case 5: 
	case 7: 
	case 8: 
	case 10: 
	case 12: 
		printf("%d 年的 %d 月是%d天", year, month, 31); 
		break; 
	case 2: 
		//判断 year 是闰年还是平年 
		if( (year % 4 == 0 && year % 100 !=0) || year % 400 == 0) { 
			printf("%d 年的 %d 月是%d天", year, month, 29); 
		} else{
			printf("%d 年的 %d 月是%d天", year, month, 28); 
		}
		break; 
	default: 
		printf("%d 年的 %d 月是%d天", year, month, 30); 
		break;
	}
	getchar(); 
}
```

运行结果：

![image-20201020142615724](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020142615724.png) 



（4）、看下面代码输出什么?     ==重点：优先级问题==

```c
#include <stdio.h>
void main(){
	int b1=0,b2=0; 
	// 将 b2==5>0 改成 b2=5>0 又输出什么 
	// 充分考虑运算符的优先级问题 
	if((b1==2>3) && (b2=5>0)){ 
		printf("\n(b1=2>3) && (b2=5>0)为真"); //输出 
	}
	printf("\nb1= %d ;b2= %d", b1,b2);// b1=0 b2=1 
	getchar(); 
}
```

输出结果：

![image-20201020143825540](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020143825540.png) 



(5)、输出小写的 a-z 以及大写的 Z—A 

```c
#include <stdio.h>
void main()
{
	char c1;
	for(c1 = 'a'; c1<'z';c1++){
		printf("%c ",c1);
	}
	for(c1 = 'A'; c1<'Z';c1++){
		printf("%c ",c1);
	}
	getchar();
}
```

(6)、求出 1-1/2+1/3-1/4…..1/100 的和 

> 找规律分析解题：
>
> 1-1/2+1/3-1/4…..1/100  = (1/1)-(1/2)+(1/3)-(1/4)…..(1/100)
>
> 分母为奇数：符号为+
>
> 分母为偶数：符号为-

```c
#include <stdio.h>
void main()
{
	//定义一个变量 sum 来统计和 
	double sum = 0.0; 
	int i ; 
	for(i = 1; i <= 100; i++) { 
		//如果 i 是奇数 
		if(i % 2 != 0) { 
			sum += 1.0/i; // 注意，考虑保留小数 1.0 而不是 1 
		} else { 
			sum -= 1.0/i; 
		} 
	}
	printf("sum=%.2f", sum); 
	getchar();
}
```







## 枚举（enum）

> 1、枚举是 C 语言中的一种构造数据类型，它可以让数据更简洁，更易读, 对于只有几个有限的特定数据，可以使用枚举.
> 2、枚举对应英文(enumeration, 简写 enum)
> 3、枚举是一组常量的集合，包含一组有限的特定的数据
> 4、枚举语法定义格式为



### 快速入门案例：

```c
#include <stdio.h>
int main() {
	enum DAY
	{
		MON=1, TUE=2, WED=3, THU=4, FRI=5, SAT=6, SUN=7	// 这里DAY 就是枚举类型, 包含了7个枚举元素
	}; 
	enum DAY day;  // enum DAY 是枚举类型， day 就是枚举变量
	day = WED; 		//	给枚举变量 day 赋值，值就是某个枚举元素
	printf("%d",day);	// 3 ， 每个枚举元素对应一个值
	getchar();
	return 0;
}
```





### 枚举的遍历

C 语言中，枚举类型是被当做 int 或者 unsigned int 类型来处理的，枚举类型必须连续是可以实现有条件的 遍历。以下实例使用 for 来遍历枚举的元素 

```c
#include <stdio.h>
enum DAY {
	MON=1, TUE, WED, THU, FRI, SAT, SUN //如果没有给赋值，就会按照顺序赋值
} day; // 表示 定义了一个枚举类型 enum Day ,同时定义了一个变量 day(类型是 enum DAY)
int main() {
	// 遍历枚举元素
	//day++ 会给出警告，但是可以运行
	for (day = MON; day <= SUN; day++) { // 要求枚举元素是连续赋值
		printf("枚举元素：%d \n", day);
	}
	getchar();
	return 0;
}
```



### 枚举在switch中的应用

```c
#include <stdio.h>
int main() { 
	enum SEASONS {SPRING=1, SUMMER, AUTUMN, WINTER}; //定义枚举类型 enum SEASONS 
	enum SEASONS season;//定义了一个枚举类型变量 season(类型 enum SEASONS )
	printf("请输入你喜欢的季节: (1. spring, 2. summer, 3. autumn 4 winter): ");
	scanf("%d", &season);
	switch (season) {
	case SPRING:
		printf("你喜欢的季节是春天");
		break;
	case SUMMER:
		printf("你喜欢的季节是夏天");
		break;
	case AUTUMN:
		printf("你喜欢的季节是秋天");
		break;
	case WINTER:
		printf("你喜欢的季节是冬天");
		break;
	}
	getchar();
	getchar();
}
```



![image-20201020152548481](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020152548481.png) 



==**枚举类型使用注意事项和细节**==

1、第一个枚举成员的默认值为整型0，后续枚举成员的值在前一个成员上加1我们在这个实例中把第一个枚举成员的值定义为 1，第二个就为 2，以此类推
2、 在定义枚举类型时改变枚举元素的值 [案例]
3、 枚举变量的定义的形式1-先定义枚举类型，再定义枚举变量
4、 枚举变量的定义的形式2-定义枚举类型的同时定义枚举变量
5、枚举变量的定义的形式3-省略枚举名称，直接定义枚举变量 

```c
#include <stdio.h>
int main() { 
    //定义方式1 -定义枚举类型的同时定义枚举变量
	enum DAY {
		MON=1, TUE, WED, THU, FRI, SAT, SUN
	} day;
    // 定义方式2 -省略枚举名称，直接定义枚举变量
	enum { 
		MON=1, TUE, WED, THU, FRI, SAT, SUN 
	} day; // 这样使用枚举，该枚举类型只能使用一次.
    
    //定义方式3  先定义枚举类型，再定义枚举变量
    enum DAY {
	MON=1, TUE, WED, THU, FRI, SAT, SUN 
};
    enum DAY day;
}
```

6、可以将整数转换为对应的枚举值 

```c
#include <stdio.h>
int main() {
	enum SEASONS {SPRING=1, SUMMER, AUTUMN, WINTER};
	enum SEASONS season;
	int n = 4;
	season = (enum SEASONS) n; // 将整型int 4 变成了 SEASONS类型
	printf("season=:%d",season);
	getchar();
	return 0;
}
```

 





## 函数

1、为完成某一功能的程序指令(语句)的集合,称为函数。
2、在C语言中,函数分为: 自定义函数、系统函数(查看C语言函数手册)
3、函数还有其它叫法，比如方法等，在本视频课程中，我们统一称为 函数。



### 函数的定义

> 函数的形式：

```c
返回类型 函数名（形参列表）{
	执行语句...; // 函数体
	return 返回值; // 可选
}
```



### 头文件

在实际的开发中，我们往往需要在不同的文件中，去调用其它文件的定义的函数，比如hello.c中，去使用myfuns.c 文件中的函数，如何实现？ ->==使用头文件==

> 头文件基本概念

1、 头文件是扩展名为 .h 的文件，包含了 C 函数声明和宏定义，被多个源文件中引用共享。有两种类型的头文件：==程序员编写的头文件==和==C标准库自带的头文件==
2、 在程序中要使用头文件，需要使用 C ==预处理指令 #include==来引用它。前面我们已经看过 `stdio.h 头文件`，它是C标准库自带的头文件
3、 ==#include叫做文件包含命令==，用来引入对应的头文件（.h文件）。#include 也是C语言预处理命令的一种。

4) #include 的处理过程很简单，就是将头文件的内容插入到该命令所在的位置，从而把头文件和当前源文件连接成一个源文件，这与复制粘贴的效果相同。但是我们不会直接在源文件中复制头文件的内容，因为这么做很容易出错，特别在程序是由多个源文件组成的时候。
5、建议把所有的常量、宏、系统全局变量和函数原型写在头文件中，在需要的时候随时引用这些头文件

> 工作原理图：

![image-20201020201127813](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020201127813.png)





### 头文件快速入门

说明：头文件快速入门-C 程序相互调用函数，我们将 cal 声明到文件 myFun.h , 在 myFun.c 中定义 cal 函数， 当其它文件需要使用到 myFun.h 声明 的函数时，可以#include 该头文件，就可以使用了.



1、创建`myfun.h`并声明`int add(int a,int b)`函数

```c
//声明函数int add(int a,int b);
int add(int a ,int b);
```

2、创建`myfun.c`并实现`int add(int a,int b)`函数

```
#include <stdio.h>
// 实现函数int add(int a,int b);

int add(int a,int b){
	return a+b;
}
```

3、创建测试类`Test.c`去测试`int add(int a,int b)`函数

```c
#include <stdio.h>
// 实现函数int add(int a,int b);

int add(int a,int b){
	return a+b;
}
```

运行结果如图：

![image-20201020202735139](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201020202735139.png) 



### 头文件的注意事项和细节说明

1、引用头文件相当于复制头文件的内容

2、源文件的名字 可以不和头文件一样，但是为了好管理，一般头文件名和源文件名一样.

3、**==C 语言中 include <> 与include "" 的区别==**

- include <>：引用的是编译器的类库路径里面的头文件，用于引用`系统头文件`

- include ""：引用的是你程序目录的相对路径中的头文件，如果在程序目录没有找到引用的头文件则到`编译器的类库路径的目录`下找该头文件，用于引用用户头文件

- 说明：

  ==引用 系统头文件，两种形式都会可以，include <> 效率高==

  ==引用 用户头文件，只能使用 include ""==

4、一个 #include 命令只能包含一个头文件，多个头文件需要多个 #include 命令

5、同一个头文件如果被多次引入，多次引入的效果和一次引入的效果相同，因为头文件在代码层面有防止重复引入的机制 [举例]

6、在一个被包含的文件(.c)中又可以包含另一个文件头文件(.h)

7、不管是标准头文件，还是自定义头文件，都只能包含变量和函数的声明，不
能包含定义，否则在多次引入时会引起重复定义错误(!!!!)





### 函数-调用机制

如何理解方法这个概念,给大家举个通俗的示例:

![image-20201021091334484](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021091334484.png) 



> 举例说明+图解

无返回值型：

![image-20201021092119249](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021092119249.png)

有返回值型：

![image-20201021092706065](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021092706065.png)

==4、如果有函数有返回值，则将返回值赋给接收的变量==

==5、当一个函数返回后，该函数对应的栈空间也会销毁==



**举例说明：**

![image-20201021093423804](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021093423804.png)



### 函数的递归

一个函数在`函数体内`又`调用了本身`，我们称为==递归调用==





#### 递归调用快速入门

```c
#include <stdio.h>
void test(int n) {
	if(n > 2) {
		test(n -1);
	}
	printf("\n n=%d", n);
}

void main() {
	test(4);  // 输出什么呢？
	getchar();
}
```

结果如图：

![image-20201021095050120](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021095050120.png) 

> 图解分析：

![image-20201021094749260](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021094749260.png)



#### 函数递归需要遵守的重要原则:

1、执行一个函数时，就创建一个新的受保护的独立空间(新函数栈)
2、函数的局部变量是独立的，不会相互影响
3、递归必须向退出递归的条件逼近，否则就是无限递归，死龟了:)
4、当一个函数执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁



#### 递归函数练习题

1、斐波那契数

请使用递归的方式，求出斐波那契数1,1,2,3,5,8,13...给你一个整数n，求出它的斐波那契数是多少？

```c
#include <stdio.h>
/*
题目：求出斐波那契数1,1,2,3,5,8,13...给你一个整数n
分析：
	当n=1和n=2时候，返回值为1
	当n>2的时候，斐波那契数=前两个数的和：fbn(n-1)+fbn(n-2)

*/
int fbn(int n){
	if(n ==1 || n ==2 ){
		return 1;
	}else{
		return fbn(n-1)+fbn(n-2);
	}
}

void main(){
	int result = fbn(4);
	printf("%d",result);
	getchar();
}

```



2、求函数值

已知 f(1)=3; f(n) = 2*f(n-1)+1; 请使用递归的思想编程，求出 f(n)的值?

```c
#include <stdio.h>
/*
题目：已知 f(1)=3; f(n) = 2*f(n-1)+1; 请使用递归的思想编程，求出 f(n)的值?


*/

int f(int n){
	if(n ==1){
		 return 3;
	}else{
		return  2*f(n-1)+1;
	}
}
void main(){
	int result = f(10);
	printf("%d",result);
	getchar();
}
```





3、猴子吃桃子问题

有一堆桃子，猴子第一天吃了其中的一半，并再多吃了一个！以后每天猴子都吃其中的一半，然后再多吃一个。当到第十天时，想再吃时（还没吃），发现只有1个桃子了。==问题：最初共多少个桃子？==

```c
#include<stdio.h>
/*
有一堆桃子，猴子第一天吃了其中的一半，并再多吃了一个！
以后每天猴子都吃其中的一半，然后再多吃一个。
当到第十天时，想再吃时（还没吃），发现只有1个桃子了。
问题：最初共多少个桃子？

分析：day10  只有1个桃子
		推出 day9 = (day10+1)*2 = 4
				day8 = (4+1)*2= 10;
				day7 = (10+1)*2= 22;

*/

int peach(int day){
	if(day == 10){
		return 1;
	}else {
		return (peach(day+1)+1)*2;
	}
}
void main(){
	int peachNum = peach(1);
	printf("第一天有%d个桃子",peachNum);
	getchar();
}
```





### 函数注意事项和细节讨论

1、 函数的形参列表可以是多个。
2、C语言传递参数可以是值传递（pass by value），也可以传递指针（a pointer passed by value）也叫引用传递。
3、函数的命名遵循标识符命名规范，首字母不能是数字，可以采用 驼峰法 或者 下 划线法 ，比如 getMax() get_max()。
4、函数中的变量是局部的，函数外不生效【案例说明】

5、 基本数据类型默认是值传递的，即进行值拷贝。在函数内修改，不会影响到原来
的值。【案例演示】

```c
#include<stdio.h>
void f2(int n) { 
	n++; 
	printf("\nf2 中的 n=%d", n); // n=10 
}

void main() { 
	//函数中的变量是局部的，函数外不生效 
	//printf("num=%d", num); 
	int n = 9; 
	f2(n); 
	printf("\nmain 函数中 n=%d", n); //9 
	getchar(); 
}
```



6、如果希望函数内的变量能修改函数外的变量，可以传入变量的地址&，函数内以指针的方式操作变量。从效果上看类似引用(即传递指针) 【案例演示:】

**==指针传递==**

```c
#include<stdio.h>
void f3(int *p) { 
	(*p)++;// 修改会对函数外的变量有影响 
}

void main() { 
	//函数中的变量是局部的，函数外不生效 
	//printf("num=%d", num);
	int n = 9; 
	f3(&n); 
	printf("\nmain 函数中 n=%d", n); //10 
	getchar(); 
}
```

**内存图分析：**

![image-20201021121626749](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021121626749.png)







7、C语言==不支持函数重载==，C语言支持==可变参数函数==

```c
#include<stdio.h>
/*

	请编写一个函数 swap(int \*n1, int \*n2) 可以交换 n1 和 n2的值


*/
void swap(int *n1, int *n2){
	int temp = *n1; // 表示将n1 指针指向的变量的值赋值给temp 
	*n1 = *n2; // 将n2指针指向的值赋值给n1指针指向的变量的值
	*n2 = temp; // 将temp 的值赋值给 n2指针指向的变量的值
}

void main(){
	int a = 3;
	int b = 4;
	swap(&a,&b);
	printf("a=%d,b=%d",a,b); // 此时 a = 4 , b = 3
	getchar();
}
```



练习题：请编写一个函数 swap(int \*n1, int \*n2) 可以交换 n1 和 n2的值

```c
#include<stdio.h>
/*
	请编写一个函数 swap(int \*n1, int \*n2) 可以交换 n1 和 n2的值

*/
void swap(int *n1, int *n2){
	int temp = *n1; // 表示将n1 指针指向的变量的值赋值给temp 
	*n1 = *n2; // 将n2指针指向的值赋值给n1指针指向的变量的值
	*n2 = temp; // 将temp 的值赋值给 n2指针指向的变量的值
}

void main(){
	int a = 3;
	int b = 4;
	swap(&a,&b);
	printf("a=%d,b=%d",a,b);
	getchar();
}
```



### 函数参数的传递方式



我们在讲解函数注意事项和使用细节时，已经讲过C语言传递参数可以是==值传递（pass by value）==，也可以==传递指针（a pointer passed by value）==也叫==传递地址==或者 ==引用传递==



>  两种传递方式:
>
>  1、值传递
>
>  2、引用传递(传递指针、地址)

其实，不管是值传递还是引用传递，传递给函数的都是变量的副本，不同的是，值传递的是值的拷贝，引用传递的是地址的拷贝，一般来说，地址拷贝效率高，因为数据量小，而值拷贝决定拷贝的数据大小，数据越大，效率越低。



#### 值传递和引用传递使用特点

1、值传递：变量直接存储值，内存通常在栈中分配【案例: 示意图】
2、==**默认是值传递的数据类型有**== 1. `基本数据类型` 2. `结构体` 3. `共用体` 4. `枚举类型`
3、引用传递：变量存储的是一个地址，这个地址对应的空间才真正存储数据(值)。
4、==**默认引用传递的数据类型有**==：`指针`和`数组`

5、如果希望函数内的变量能修改函数外的变量，可以传入变量的地址&，函数内以指针的方式操作变量(*指针)。从效果上看类似引用 【案例演示: 画出示意图】, 比如修改结构体的属性.



### 变量作用域

所谓变量作用域（Scope），就是指变量的有效范围 

1、函数内部声明定义的局部变量，作用域仅限于函数内部。

2、 函数的参数，**形式参数**，被当作该函数内的局部变量，如果与全局变量同名它们会优先使用局部变量

3、 在一个**代码块**，比如 for / if中 的局部变量，那么这个变量的的作用域就在该代码块

4、在所有**函数外部定义的变量**叫全局变 量，作用域在整个程序有效。 



> 初始化局部变量和全局变量

1、局部变量，系统不会对其默认初始化，必须对局部变量初始化后才能使用，否则，程序运行后可能会异常退出

2、全局变量，系统会自动对其初始化，如下所示

![image-20201021132322550](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021132322550.png) 



> 作用域的注意事项和细节



1、全局变量(Global Variable)保存在内存的全局存储区中，占用静态的存储单元，它的作用域默认是整个程序，也就是所有的代码文件，包括源文件（.c文件）和头文件（.h文件）。【c程序内存布局图!!!】
2、局部变量(Local Variable)保存在栈中，函数被调用时才动态地为变量分配存储单元，它的作用域仅限于函数内部。

参考：↓↓↓↓↓

<p><a href="#c程序内存布局图">c程序内存布局图<img 



3、C语言规定，只能从小的作用域向大的作用域中去寻找变量，而不能反过来，使用更小的作用域中的变量
4、在同一个作用域，变量名不能重复，在不同的作用域，变量名可以重复，使用时编译器采用就近原则.
5、由{ }包围的代码块也拥有独立的作用域





<a name="c程序内存布局图"></a>

> c程序内存布局图

![image-20201021135523300](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021135523300.png)





### static关键字

static关键字在c语言中比较常用，使用恰当能够大大提高程序的模块化特性，有利于扩展和维护。

> 局部变量使用static修饰

1、局部变量被 static 修饰后，我们称为静态局部变量

2、对应静态局部变量在声明时未赋初值，编译器也会把它初始化为0。

3、静态局部变量**存储于进程的**静态存储区**(全局性质)，**只会被初始一次，即使函数返回，它的值也会保持不变 [案例+图解]

```c
#include<stdio.h>
void fn(void){
	int n = 10; //普通变量
	printf("n=%d\n", n);
	n++;
	printf("n++=%d\n", n);
}
void fn_static(void) {
	static int n = 10; //静态局部变量 -只初始化一次
	printf("static n=%d\n", n);
	n++;
	printf("n++=%d\n", n);
}
int main(void) {
	//fn();
	printf("--------------------\n");
	fn_static();
	printf("--------------------\n");
	//fn();
	printf("--------------------\n");
	fn_static();
	getchar();
	return 0; 
}
```



![image-20201021141700777](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021141700777.png) 



> 全局变量使用static修饰

1、普通全局变量对整个工程可见，其他文件可以使用extern外部声明后直接使用。也就是说其他文件不能再定义一个与其相同名字的变量了（否则编译器会认为它们是同一个变量），静态全局变量仅对当前文件可见，其他文件不可访问，其他文件可以定义与其同名的变量，两者互不影响 

(1)、创建带有全局变量的类`file01.c`，代码如下：

```c
int n = 10; //普通全局变量
//static int n = 20;  //静态全局变量，只能在本文件中使用，而不能在其他文件中使用！
```



(2)、创建`测试类file02.c`，代码如下：

```c
#include <stdio.h>
//#include "file01.c"; //方式一：通过头文件引入

// 在一个文件，使用另外一个文件的全局变量，使用extern来引入即可
extern int n;
extern int m;

void main(){
	printf("%d",n);
	getchar();
}
```

2、定义不需要与其他文件共享的全局变量时，加上static关键字能够有效地降低程序模块之间的耦合，避免不同文件同名变量的冲突，且不会误使用





> 函数使用static修饰

(1)、创建带有普通函数类`file03.c`，代码如下：

```c
#include <stdio.h>
void fun1(void) {//普通函数(非静态函数)
printf("hello from fun1.\n");
}
static void fun2(void) {//静态函数 - 只能在本文件中使用
printf("hello from fun2.\n");
}
```



(2)、创建`测试类file04.c`，代码如下：

```c
#include <stdio.h>
extern void fun1(void)
//extern void fun2(void); //肯定会报错
void main(){
	fun1();
	//fun2();  //肯定会报错
	getchar();
}
```

==发现：static函数只能在本类中使用被调用，而不能被外部的类调用==



### 系统函数

标准库参考地址：https://www.runoob.com/cprogramming/c-data-types.html

> 字符串系统函数

1、得到字符串的长度

`size_t strlen(const char *str)`
计算字符串 str 的长度，直到空结束字符，但不包括空结束字符。

2、拷贝字符串
`char *strcpy(char *dest, const char *src)`

把 src 所指向的字符串复制到 dest。
3、 连接字符串
`char *strcat(char *dest, const char *src)`

把 src 所指向的字符串追加到 dest 所指向的字符串的结尾。



测试案例：

```c
#include<stdio.h>
#include<string.h>

void main(){
	char src[50], dest[50];
	char * str = "abcdff";
	printf("str.len=%d", strlen(str));  //返回6

	strcpy(src, "hello ");  //  输出【src = hello】，将原来的abcdff给覆盖了
	strcpy(dest, "叶仁平"); 
	strcat(dest, src); // 将src的内容追加到desc中去，不会覆盖原有的内容 【结果为：叶仁平，hello】
	printf("最终的目标字符串： |%s|", dest);//【结果为：叶仁平，hello】
	getchar();
}
```



> 时间和日期相的函数

(1)、获取当前时间

`char *ctime(const time_t *timer)`

返回一个表示当地时间的字符串，当地时间是基于参数 timer。



**案例演示1：**

```c
#include <stdio.h>
#include <time.h>
int main () {
	time_t curtime; // time_t是一个结构体类型
	time(&curtime); // time() 可以完成初始化任务
	printf("当前时间 = %s", ctime(&curtime)); //  返回一个表示当地时间的字符串，当地时间是基于参数 timer。
	getchar();
	return(0);
}
```

结果：显示当地当前时间

![image-20201021150936740](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021150936740.png) 



**演示案例2：**

通过执行两个for循环花费的时间

```
#include <stdio.h>
#include <time.h>
void test() {
	int i = 0;
	int sum = 0;
	int j = 0;
	for(i = 0; i < 77777777;i++) {
		sum = 0;
		for (j = 0; j< 10;j++) {
			sum += j;
		}
	} 
}
void main () {
	// 定义两个变量star_t表示开始时间，end_t表示结束时间
	time_t start_t,end_t; 
	double diff_t;	// 两者的时间差
	printf("*********程序启动************\n");
	time(&start_t);		//获取当前时间	
	test(); // 进行测试
	time(&end_t);		//获取结束时的时间
	diff_t = diff_t = difftime(end_t,start_t);  // 两者的时间差即为当前时间
	printf("执行test函数耗时%.2fs",diff_t);
	getchar();
}

```

结果：

![image-20201021152053082](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021152053082.png) 





> 数学函数math.h


1、`double exp(double x)`
返回 e 的 x 次幂的值。
1、`double log(double x)`
返回 x 的自然对数（基数为 e 的对数）
3、` double pow(double x, double y)`
返回 x 的 y 次幂。
4、` double sqrt(double x)`
返回 x 的平方根。
5、` double fabs(double x)`

返回 x 的绝对值。

**案例：**

```c
#include <stdio.h>
#include <math.h>
void main (){
	double d1 = pow(2.0,3.0); // 2的3次方
	double d2 = sqrt(5.0); // 返回5.0的平方根
	printf("d1=%.2f", d1);
	printf("d2=%.2f", d2); 
	getchar();
}
```



> 基本数据类型和字符串类型的转换

在程序开发中，我们经常需要将基本数据类型转成字符串类型(即 ==char数组==)。 或者将字符串类型转成基本数据类型。

**sprintf函数的用法:**

1、 sprintf和平时我们常用的printf函数的功能很相似。sprintf函数打印到字符串中，而printf函数打印输出到屏幕上。sprintf函数在我们完成其他数据类型转换成字符串类型的操作中应用广泛
2、该函数包含在stdio.h的头文件中

演示案例：

```c
#include<stdio.h>
void main() {
	char str1[20]; //字符数组，即字符串
	char str2[20];
	char str3[20];
	int a=20984,b=48090;
	double d=14.309948;
	sprintf(str1,"%d %d",a,b);
	sprintf(str2, "%.2f", d); 
	sprintf(str3, "%8.2f", d); 
	printf("str1=%s str2=%s str3=%s", str1, str2, str3);
	getchar();
}
```



> 字符串类型转基本数据类型

 atoi (字符串)

将字符串数组转成整数

atof (字符串)

将字符串数组转成小数

**案例：**

```c
#include<stdio.h>
#include<stdlib.h>
void main() {
	char str[10] = "123456";
	char str2[10] = "12.67423";
	char str3[3] = "ab";
	char str4[4] = "111";
	int num1 = atoi(str); // 将str转成一个int整数
	short s1 = atoi(str4); 
	double d = atof(str2);// 将str2转成double类型
	char c = str3[0]; //  获取字符串的第一个元素a
	printf("num1=%d d=%f c=%c s1=%d", num1, d, c, s1);
	getchar();
}
```

==**注意：**==

在将char 数组 类型转成 基本数据类型时，要确保能够转成有效的数据，比如 我们可以把 "123" , 转成一个整数，但是不能把 "hello" 转成一个整数；如果格式不正确，会默认转成 0 或者 0.0 [案例演示]





### 函数的练习题

（1）、函数可以没有返回值案例，编写一个函数,从终端输入一个整数打印出对应的金子塔。 【课后练习】 -> 将原 来的代码封装到函数中即可！

```c
#include <stdio.h>
void printfA(int totalLevel){
	int i,j,k;
	for(i=1;i<=totalLevel;i++){
		//输出空格
		for(k=1;k<=totalLevel-i;k++){
			printf(" ");
		}
		// 输出*
		for(j=1;j<=2*i-1;j++){
			if(j==1 || j==2*i-1 || i ==totalLevel){
				printf("*");
			}else{
				printf(" ");
			}
		}
		//换行
		printf("\n");
	}
}

void main(){
	int totalLevel = 0;
	printf("请输入金字塔层数：");
	scanf("%d",&totalLevel);
	printfA(totalLevel);
	getchar();
	getchar();
}


```

（2）、编写一个函数,从终端输入一个整数(1—9),打印出对应的乘法表

```c
#include <stdio.h>
void print99(int num){
	int i,j;
	for(i = 1;i <= num; i++){
		for(j = 1; j<=i; j++){
			printf("%d * %d = %d",j,i,i*j);
		}
		printf("\n");
	}
	getchar();
}

void main(){
    int num = 0;
	printf("请输入：");
	scanf("%d",&num);
	print99(num);
}

```

(3)、定义函数，实现求两个 double 数字的最大值，并返回

```c
double getMax(double a,double b){
	return a>=b?a:b;
}

void main(){

	printf("最大值：%.2f",getMax(3.2,2.1));
	getchar();
}
```



## 预处理命令

1、使用库函数之前，应该用#include 引入对应的头文件。这种==**以#号开头的命令称为预处理命令**==。 

2、这些在编译之前对源文件进行简单加工的过程，就称为预处理（即预先处理、提前处理） 

3、预处理主要是处理以#开头的命令，例如#include <stdio.h>等。预处理命令要放在所有函数之外，而且一般都放 在源文件的前面 

4、 预处理是 C 语言的一个重要功能，由预处理程序完成。**当对一个源文件进行编译时，系统将自动调用预处理程 序对源程序中的预处理部分作处理**，处理完毕自动进入对源程序的编译 

5、C 语言提供了多种预处理功能，如宏定义、文件包含、条件编译等，合理地使用它们会使编写的程序便于阅读、 修改、移植和调试，也有利于模块化程序设计



#### 预处理命令快速入门

> 问题：
>
> 开发一个C语言程序，让它暂停 5 秒以后再输出内容 "helllo, 叶仁平!~"，并且要求跨平台，在 Windows 和 Linux 下都能运行，如何处理



==**提示**==

1、 Windows 平台下的暂停函数的原型是`void Sleep(DWORD dwMilliseconds)`，参数的单位是“毫

秒”，位于 `<windows.h>` 头文件。

2) Linux 平台下暂停函数的原型是`unsigned int sleep (unsigned int seconds)`，参数的单位是

“秒”，位于` <unistd.h>` 头文件

3) #if、#elif、#endif 就是预处理命令，它们都是在编译之前由预处理程序来执行的。



**解决问题：**

```c
#include<stdio.h>
#if _WIN32  // 如果是win32平台，就执行#include <windows.h>，引入：windows.h
#include <windows.h>
#elif __linux__ // 否则如果是linux平台，就执行#include <unistd.h>，引入：unistd.h
#include <unistd.h>
#endif //结束 
int main() {
	//不同的平台下调用不同的函数
#if _WIN32 //识别windows平台
	Sleep(5000);
#elif __linux__ //识别linux平台
	sleep(5);
#endif //结束
	// 正文
	puts("hello, 叶仁平~"); // 5秒后 -输出hello,叶仁平~
	getchar();
	return 0; 
}
```

==**说明：**==

在Windows操作系统和Linux操作系统下，**生成的源码不一样**！

- 在Windows下生成的源码是：

```c
#include<stdio.h>
include <windows.h>
int main() {
	puts("hello, 叶仁平~"); // 5秒后 -输出hello,叶仁平~
	getchar();
	return 0; 
}
```

- 在Linux下生成的源码是

```c
#include<stdio.h>
#include <unistd.h>
int main() {
	puts("hello, 叶仁平~"); // 5秒后 -输出hello,叶仁平~
	getchar();
	return 0; 
}
```



### 宏定义define

#define 叫做宏定义命令，它也是 C 语言预处理命令的一种。

**所谓宏定义，就是用一个标识符来表示一个字符串**,如果在后面的代码中出现了该标识符，那么就全
部替换成指定的字符串



> 快速回顾

![image-20201021193926687](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021193926687.png)

==**宏定义注意事项和细节**==

1、 宏定义是用宏名来表示一个字符串，在宏展开时又以该字符串取代宏名，这只是一种简单的替换。字符串中可以含任何字符，它可以是常数、表达式、if 语句、函数等，预处理程序对它不作任何检查，如有错误，只能在编译已被宏展开后的源程序时发现。
2、宏定义不是说明或语句，在行末不必加分号，如加上分号则连分号也一起替换
3、宏定义必须写在函数之	外，其作用域为宏定义命令起到源程序结束。如要终止其作用域可使用#undef命令  **案例**

↓案例如下

```c
#include<stdio.h>
#define PI 3.14159
int main(){
	printf("PI=%f", PI);
	return 0;
}
#undef PI //取消宏定义
void func(){
	// Code
	printf("PI=%f", PI);//错误,这里不能使用到PI了
}
```



4、代码中的宏名如果被引号包围，那么预处理程序不对其作宏代替 **案例4**

![image-20201021194950642](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021194950642.png) 

5、宏定义允许嵌套，在宏定义的字符串中可以使用已经定义的宏名，在宏展开时由预处理程序层层代换  **案例5**

![image-20201021194958969](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021194958969.png) 

6、习惯上**宏名用大写字母表示**，以便于与变量区别。但也允许用小写字母

7、可用宏定义==表示数据类型==，使书写方便 [案例]

```c
#define UINT unsigned int
	void main() {
	UINT a, b; // 宏替换 unsigned int a, b;
}
```

8、宏定义表示数据类型和用 typedef 定义数据说明符的区别：宏定义只是简单的**字**符串替换**，由预处理器来处理；而 typedef是在编译阶段由编译器处理的**，它并不是简单的字符串替换，而给原有的数据类型起一个**新的名字**，将它作为一种新的数据类型。





### 带参宏定义define

1、 C语言允许宏带有参数。在宏定义中的参数称为“形式参数”，在宏调用中的参数称为“实际参数”，这点和函数有些类似
2、对带参数的宏，在展开过程中不仅要进行字符串替换，还要用实参去替换形参
3、带参宏定义的一般形式为#define 宏名(形参列表) 字符串 ,在字符串中可以含有各个形参
4、带参宏调用的一般形式为 : 宏名 (实参列表); [案例+说明 ]



**举例说明：**

创建一个带参的宏定义，代码如下：

```c
#define MAX(a,b) (a>b) ? a : b
int main(){
	int x , y, max;
	printf("input two numbers: ");
	scanf("%d %d", &x, &y);
	// 1、MAX(x,y)；调用带参宏定义
	// 2、在宏替换时（预处理，由预处理完成），会进行字符串的替换，同时会使用实参替换形参
	// 即 MAX(x, y) //宏替换后 (x>y) ? x : y	
	max = MAX(x, y);
	printf("max=%d\n", max);
	getchar();
	getchar();
	return 0;
}
```

![image-20201021200338301](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021200338301.png) 

> 带参宏定义的注意事项和细节



1、带参宏定义中，形参之间可以出现空格，但是宏名和形参列表之间不能有空格出现

```c
#define MAX(a,b) (a>b)?a:b 如果写成了 #define MAX (a, b) (a>b)?a:b
将被认为是无参宏定义，宏名 MAX 代表字符串(a,b) (a>b)?a:b
而不是 : MAX(a,b) 代表 (a>b) ? a: b 了
```



2、在带参宏定义中，不会为形式参数分配内存，因此不必指明数据类型。而在宏调用中，实参包含了具体的数据，要用它们去替换形参，因此实参必须要指明数据类型

3、在宏定义中，字符串内的形参通常要用括号括起来以避免出错。【案例+说明 】



```c
#include <stdio.h>
#include <stdlib.h>
#define SQ(y) (y)*(y) // 带参宏定义,字符串内的形参通常要用括号括起来以避免出错
int main(){
	int a, sq;
	printf("input a number: ");
	scanf("%d", &a);
	sq = SQ(a+1); // 宏替换 (a+1) * (a+1)
	printf("sq=%d\n", sq);
	system("pause");
	return 0;
}
```



> 带参宏定义和函数的区别

1、宏展开仅仅是字符串的替换，不会对表达式进行计算；宏在编译之前就被处理掉了，它没有机会参与编译，也不会占用内存。

2、函数是一段可以重复使用的代码，会被编译，会给它分配内存，每次调用函数，就是执行这块内存中的代码

3、案例说明 ：要求 使用函数计算平方值， 使用宏计算平方值， 并总结二者的区别

案例分析区别：==**Fuction VS Define**==

函数：function

```
#include <stdio.h>
#include <stdlib.h>
int SQ(int y){
	return ((y)*(y));
}
int main(){
	int i=1;
	while(i<=5){ // 1, 4, 9, 16, 25
		printf("%d^2 = %d\n", (i-1), SQ(i++)); //为什么写i-1 ，因为想执行	SQ(i++)
	}
	system("pause");
	return 0;
}
```

宏定义define --> ==其实就是替换==

```c
#include <stdio.h>
#include <stdlib.h>
#define SQ(y) ((y)*(y))
int main(){
	int i=1;
	while(i<=5){ // 这里相当于计算了 1,3,5的平方					i = 	1  3  5
		printf("%d^2 = %d\n", (i-2), SQ(i++)); //SQ(i++) = ((i++)*(i++)) 1 9 25  
	}
	system("pause");
	return 0; 
}
```





### C语言预处理命令总结

预处理指令是以#号开头的代码行，# 号必须是该行除了任何空白字符外的第一个字符。# 后是指令关键字，在关键字和 # 号之间允许存在任意个数的空白字符，整行语句构成了一条预处理指令，该指令将在编译器进行编译之前对源代码做某些转

| 指令     | 说明                                                      |
| -------- | --------------------------------------------------------- |
| #        | 空指令，无任何效果                                        |
| #include | 包含一个源代码文件                                        |
| #define  | 定义宏                                                    |
| #undef   | 取消已定义的宏                                            |
| #if      | 如果给定条件为真，则编译下面代码                          |
| #ifdef   | 如果宏已经定义，则编译下面代码                            |
| #ifndef  | 如果宏没有定义，则编译下面代码                            |
| #elif    | 如果前面的#if给定条件不为真，当前条件为真，则编译下面代码 |
| #endif   | 结束一个#if……#else条件编译块                              |



>  预处理指令使用注意事项



1、预处理功能是C语言特有的功能，它是在对源程序正式编译前由预处理程序完成的，程序员在程序中用预处理命令来调用这些功能。
2、 宏定义可以带有参数，宏调用时是以实参代换形参，而不是“值传送”。
3、为了避免宏代换时发生错误，宏定义中的字符串应加括号，字符串中出现的形式参数两边也应加括号。

4、文件包含是预处理的一个重要功能，它可用来把多个源文件连接成一个源文件进行编译，结果将生成一个目标文件。

5、条件编译允许只编译源程序中满足条件的程序段，使生成的目标程序较短，从而减少了内存的开销并提高了程序的效率。
6、使用预处理功能便于程序的修改、阅读、移植和调试，也便于实现模块化程序设计



## 数组

数组可以存放==多个同一类型数据==。数组也是==一种数据类型==，是==构造类型==。传递是以引用的方式传递(即传递的是地址)

![image-20201022121407941](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022121407941.png)

> 快速入门

一个养鸡场有 6 只鸡，它们的体重分别是 3kg,5kg,1kg, 3.4kg,2kg,50kg 。请问这六只鸡的总体重是多少?平 均体重是多少? 请你编一个程序

![image-20201022115814686](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022115814686.png) 

代码实现：

```c
#include <stdio.h>
#include <stdlib.h>
#define SQ(y) ((y)*(y))
int main(){
	// 定义一个6个鸡的数组
	double hens[6];
	double totalWeight = 0.0;
	double avrageWeight = 0.0;
	int i;
	// 初始每只鸡的体重
	hens[0] = 3;
	hens[1] = 3.3;
	hens[2] = 2.3;
	hens[3] = 2;
	hens[4] = 1;
	hens[5] = 4.5;

	for(i=0; i<6; i++){
		totalWeight+=hens[i];
	}
	printf("六只鸡的总体重是%.2f kg,均体重是%.2f kg",totalWeight,totalWeight/6);
	getchar();
}
```



### 数组的定义和内存分布

> 数组的定义

```c
数据类型 数组名 [数组大小];

int a [5]; // a 数组名，类型 int , [5] 大小， 即 a 数组最多存放 5个int 数据

赋初值 a[0] = 1; a[1] = 30; ....
```



![image-20201022122313295](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022122313295.png) 

###### **==说明==**

1. 数组名 就代表 该数组的首地址 ，即 a[0]地址
2. 数组的各个元素是 连续分布的， 假如 a[0] 地址 0x1122 a[1] 地址= a[0]的地址+int字节数(4) = 0x1122 + 4 = 0x1126
   ,后面 a[2] 地址 = a[1]地址 + int 字节数(4) = 0x1126 + 4 = 0x112A, 依次类推





> 3种初始化数组的方式

```c
int arr[3]; //先定义，再复制，给定长度
arr[0] = 1;
arr[1] = 1;
arr[2] = 1;

int arr2[3] = {4,5,6};// 边定义，边赋值，给定长度

int arr3[] = {7,8,9,10};// 边定义，边赋值，不限定长度
 
```





> 数组的使用

问题：从终端循环输入5个成绩，保存到double数组,并输出

代码实现

```c
#include <stdio.h>
/*
从终端循环输入5个成绩，保存到double数组,并输出
*/

int main(){
	double arr[5];
	// 总字节数/单个double所占字节数 = 数组长度
	int arrLen = sizeof(arr)/sizeof(double);
	int i;
	for(i=0; i<5; i++){
		printf("\n请输入一个小数：");
		scanf("%lf",&arr[i]);
	}
	for(i=0; i<5; i++){
		printf("arr[%d] =%.2f",i,arr[i]);
	}
	getchar();//过滤回车
	getchar();
}
```

效果如图：

![image-20201022131355670](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022131355670.png) 



**数组使用注意事项和细节**

1、数组是多个相同类型数据的组合,一个数组一旦声明/定义了,其长度是固定的, 不能动态变化。

2、数组创建后，如果没有赋值，则遵守如下规则

- 全局数组默认值 0
- 非全局数组初值是机器垃圾值==(即：原来系统分配给这块空间的值)==

3、使用数组的步骤 1. 定义数组 2 给数组各个元素赋值 3 使用数组, 也可以一步到位 

4、数组的下标是从 0 开始的, 不是从 1 开始

5、数组下标必须在指定范围内使用，编译通过，在运行时会因为数组越界而异常中断: 比如 int arr [5] 有效下标为 0-4 

6、C 的数组属==构造类型==， 是引用传递(传递的是地址)，因此当把一个数组传递给一个函数时/或者变量，函数/变 

量操作数组会影响到原数组

![image-20201022132356247](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022132356247.png)



### 数组的应用案例：

1、创建一个char类型的26个元素的数组，分别 放置'A'-'Z‘。使用for循环访问所有元素并打印出来。==提示：==字符数据运算 'A'+1 -> 'B

```c
#include <stdio.h>

int main(){
	char arr[26];
	int i;
	//存入
	for(i = 0; i <26; i++){
		arr[i] = 'A'+i;
	}
	// 打印
	for(i = 0; i <26; i++){
		printf("\n %c",arr[i]);
	}
	getchar();
}
```



2、请求出一个数组的最大值，并得到对应的下标

```c
#include <stdio.h>

int main(){
	int a[] = {1,2,3,7,4};
	int max =  a[0];
	int index = 0;
	int i;
	// 数组长度= 总字节数/单个double所占字节数
	int len = sizeof(a)/sizeof(int);
	for(i=0; i<len; i ++){
		if(a[i]>max){
			max = a[i];
			index = i;
		}
	}
	printf("数组中最大的数是%d,下标是%d",max,index);
	getchar();
}
```



### 字符数组与字符串

用来存放字符的数组称为字符数组,

字符数组实际上是一系列字符的集合，也就是字符串（String）。在 C 语言中，没有专门的字符串变量，没有 string 类型，通常就用一个字符数组来存放一个字符串 

案例：

```c
1) char a[10]; //一维字符数组, 长度为 10 

2) char b[5][10]; //二维字符数组, 后面我们详细介绍二维数组 

3) char c[20]={'c', ' ', 'p', 'r', 'o', 'g', 'r', 'a','m'}; // 给部分数组元素赋值
```



**字符串注意事项：**

1、在 C 语言中，字符串实际上是使用 null 字符 ('\0') 终止的一维字符数组。因此，一个以 null 结尾的字符串， 包含了组成字符串的字符。



2、'\0'是 ASCII 码表中的第 0 个字符，用 NUL 表示，称为空字符。该字符既不能显示，也不是控制字符，输出该 字符不会有任何效果，它在 C 语言中仅作为字符串的结束标志。

3、**字符数组(字符串)在内存中的布局分析** 

![image-20201022163629261](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022163629261.png)

4、思考`char str[3] = {'a','b','c'}` 输出什么？ 为什么?

输出 abc #$%$#$%；

==原因：结束字符\0没有地方放==

**解决：给数组扩容成str[4]**



**结论：**

如果在给某个字符数组赋值时，(1)赋给的元素的个数小于该数组的长度，则会自动在后面加 '\0', 表示 字符串结束，(2)赋给的元素的个数等于该数组的长度，则不会自动添加 '\0' char str2[] = {'t','m','o'} 输出什么? 输出的是 tmo 乱码





> 使用字符指针变量和字符数组两种方法表示字符串的讨论

1、字符数组由若干个元素组成，每个元素放一个字符；而字符指针变量中存放的是地址（字符串/字符数组的首地址），绝不是将字符串放到字符指针变量中（是字符串首地址） [图]

2、对字符数组只能对各个元素赋值，不能用以下方法对字符数组赋值

```c
char str[14];
str=" hello tom"; //错误
str[0] = 'i'; //ok
```

![image-20201022170148642](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022170148642.png)



3、对字符指针变量，采用下面方法赋值, 是可以的

```c
char* a="yes";
a=" hello tom";
```

4、如果定义了一个字符数组，那么它有确定的内存地址(即字符数组名是一个常量)；而定义一个字符指针变量时，它并未指向某个确定的字符数据，并且可以多次赋值 [代 码+图解]



### 字符串相关函数

> 常用字符串函数一览

![image-20201022171800976](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022171800976.png) 



> 字符串(字符数组)使用注意事项和细节

1、程序中往往依靠检测 '\0' 的位置来判定字符串是否结束，而不是根据数组的长度来决定字符串长度。因此，字符串长度不会统计 '\0', 字符数组长度会统计 [案例]

2、在定义字符数组时应估计实际字符串长度，保证数组长度始终大于字符串实际长度，否则，在输出字符数组时可能出现未知字符.

3、系统对字符串常量也自动加一个'\0'作为结束符。例如"C Program”共有9个字符，但在内存中占10个字节，最后一个字节'\0'是系统自动加上的。（通过sizeof()函数可验证）

4定义字符数组时，如果 给的字符个数 比 数组的长度小，则系统会默认将剩余的元素空间，全部设置为 '\0', 比如 char str[6] = "ab" , str内存布局就是`[a][b][\0][\0][\0][\0]`



5、字符数组定义和初始化的方式比较多，比如

```c
#include <stdio.h>

int main(){
	char str1[ ]={"I am happy"}; // 默认后面加 '\0'
	char str2[ ]="I am happy"; // 省略{}号 ,默认后面加 '\0'
	char str3[ ]={'I',' ','a','m',' ','h','a','p','p','y'}; // 字符数组后面不会加 '\0', 可能有乱码
	char str4[5]={'C','h','i','n','a'}; //字符数组后面不会加 '\0', 可能有乱码
	char * pStr = "hello"; //可以，正确！
	printf("str=%s",pStr);
	getchar();
}
```



## 排序和查找

排序也称排序算法(Sort Algorithm)，排序是将一组数据，依指定的顺序进行排列的过程。

>  排序的分类：

**1、内部排序:** 

指将需要处理的所有数据都加载到`内部存储器(内存)`中进行排序。 

**2、 外部排序法：** 

数据量过大，无法全部加载到内存中，需要借助外部存储进行排序



### 冒泡排序

冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）,依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐 向上冒

![image-20201022172950862](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022172950862.png) 

因为排序的过程中，各元素不断接近自己的位置，==如果一趟比较下来没有进行过交换，就说明序列有序==，因此要在排序过程中设置
一个标志flag判断元素是否进行过交换。从而减少不必要的比较



**规律探寻：**

![image-20201022202334393](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022202334393.png) 

代码实现，第一尝试

```c
#include<stdio.h>
void main(){

	int arr[] = {3,9,-1,10,-2};

	// 第1轮排序
	int j;
	int t; //临时变量
	for(j=0;j<4;j++){
		if(arr[j]>arr[j+1]){ //如果前面的数大于后面的就交换
			t = arr[j];
			arr[j] = arr[j+1];
			arr[j+1] = t;
		}
	}
	// 第1轮排序后，结果
	for(j=0;j<5;j++){
		printf("%d ",arr[j]);
	}

	printf("\n------------------------\n");

	// 第2轮排序
	for(j=0;j<3;j++){
		if(arr[j]>arr[j+1]){ //如果前面的数大于后面的就交换
			t = arr[j];
			arr[j] = arr[j+1];
			arr[j+1] = t;
		}
	}
	// 第2轮排序后，结果
	for(j=0;j<5;j++){
		printf("%d ",arr[j]);
	}


	printf("\n------------------------\n");
	// 第3轮排序
	for(j=0;j<2;j++){
		if(arr[j]>arr[j+1]){ //如果前面的数大于后面的就交换
			t = arr[j];
			arr[j] = arr[j+1];
			arr[j+1] = t;
		}
	}
	// 第3轮排序后，结果
	for(j=0;j<5;j++){
		printf("%d ",arr[j]);
	}

		printf("\n------------------------\n");
	// 第4轮排序
	for(j=0;j<1;j++){
		if(arr[j]>arr[j+1]){ //如果前面的数大于后面的就交换
			t = arr[j];
			arr[j] = arr[j+1];
			arr[j+1] = t;
		}
	}
	// 第4轮排序后，结果
	for(j=0;j<5;j++){
		printf("%d ",arr[j]);
	}
	getchar();
}
```

![image-20201022202219433](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201022202219433.png) 



代码实现，进一步优化，实现==最终结果==：

```c
#include<stdio.h>
void main(){

	int arr[] = {3,9,-1,10,-2};

	int j;
	int i;
	int t; //临时变量
	int arrLen = sizeof(arr)/sizeof(int); //数组的长度
	for(i=0;i<arrLen-1;i++){
		for(j=0;j<arrLen-1-i;j++){ //4是递减
			if(arr[j]>arr[j+1]){ //如果前面的数大于后面的就交换
				t = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = t;
			}
		}
	}

	//结果
	for(j=0;j<5;j++){
		printf("%d ",arr[j]);
	}
	getchar();
}  
```



### 顺序查找

```c
#include<stdio.h>

int queryNum (int arr[], int len, int val){
	int i;
	for(i=0; i<len; i++){
		if(arr[i]==val){
			return i;
		}
	}
	return 1;
}
void main(){
	int arr[] = {1,2,3,4};
	int len =  sizeof(arr)/sizeof(int);
	int index = queryNum(arr,len,3);
	if(index == -1){
		printf("抱歉！找不到啊");
	}else{
		printf("找到了，下标为%d",arr[index]);
	}
	getchar();
}
```





### 二分查找

>  前提条件：必须是有序数组

```c
#include<stdio.h>
// 二分查找
int queryNum (int arr[], int leftIndex, int rightIndex	,int findVal){

	// 先找到中间这个数midVal;
	int midIndex = (leftIndex + rightIndex)/2;
	int midVal = arr[midIndex];

	// 如果leftIndex > rightIndex ，说明这个数组都比较过了，没有找到
	if(leftIndex>rightIndex){
		return -1; 
	}
	// 如果midVal > findVal  说明应该在左边查找
	if(midVal > findVal){
		queryNum(arr,leftIndex,midIndex-1,findVal);
	// 如果midVal > findVal  说明应该在左边查找
	}else if(midVal > findVal){
		queryNum(arr,midIndex + 1,rightIndex,findVal);
	}else{
		return midIndex; // 找到了，就是中间这个数，返回中间这个数的下标
	}

}
void main(){
	int arr[] = {1,2,3,4,6,10,11};
	int len = sizeof(arr)/sizeof(int);
	int index = queryNum(arr,0,len-1,51111);
	if(index != -1){
		printf("找到了，数组下标为%d",index);
	}else{
		printf("没有找到！");
	}
	getchar();
}

```



### 多维数组-二维数组

![image-20201023121149417](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201023121149417.png) 

#### 快速入门案例：

请用二维数组输出如下图形

```
0 0 0 0 0 0 

0 0 1 0 0 0 

0 2 0 3 0 0 

0 0 0 0 0 0 
```

```c
#include <stdio.h> 
void main() {
	//a[4][6] : 表示一个 4 行 6 列的二维数组 
	int a[4][6]; // 没有初始化，则是分配的内存垃圾值 
	int i, j; 
	//全部初始化 0 
	for(i = 0; i < 4; i++) { //先遍历行 
		for(j = 0; j < 6; j++) {//遍历列 
			a[i][j] = 0; 
		} 
	}
	a[1][2] = 1; 
	a[2][1] = 2; 
	a[2][3] = 3; 
	//输出二维数组 
	for(i = 0; i < 4; i++) { 
		for(j = 0; j < 6; j++) { 
			printf("%d ", a[i][j]); 
		}
		printf("\n"); 
	}

	////看看二维数组的内存布局 
	printf("\n 二维数组 a 的首地址=%p", a); 
	printf("\n 二维数组 a[0]的地址=%p", a[0]);
	printf("\n 二维数组 a[0][0]的地址=%p", &a[0][0]); 
	printf("\n 二维数组 a[0][1]的地址=%p", &a[0][1]); 
	printf("\n"); 
	for(i = 0; i < 4; i++) { 
		printf("a[%d]的地址=%p ", i, a[i]); 
		for(j=0; j < 6; j++) { 
			printf("a[%d][%d]的地址=%p ", i, j , &a[i][j]); 
		}
		printf("\n"); 
	}
	getchar(); 
}
```



> 使用方式 2

```
 类型 数组名[大小][大小] = {{值 1,值 2..},{值 1,值 2..},{值 1,值 2..}}; 2) 或者 
 类型 数组名[大小][大小] = { 值 1,值 2,值 3,值 4,值 5,值 6 ..};
```

![image-20201023121739469](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201023121739469.png) 



注意事项：

1、可以只对部分元素赋值，未赋值的元素自动取“零”值

2、如果对全部元素赋值，那么第一维的长度可以不给出

```
int a[3][3] = {1, 2, 3, 4, 5, 6, 7, 8, 9}; 

int a[][3] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
```

3、二维数组可以看作是由一维数组嵌套而成的；如果一个数组的每个元素又是一个数组，那么它就是二维数组。

```c
二维数组a[3][4]可看成三个一维数组，它们的数组名分别为 a[0]、a[1]、a[2]。

这三个一维数组都有 4 个元素，如，一维数组 a[0] 的元素为 a[0][0]、a[0][1]、a[0][2]、a[0][3]
```



## 断点调试

> 一个实际需求:

在开发中，程序员发现一个非常诡异的错误，怎么看源代码都发现不了这个错误，这时老程序员就会温馨提示，可以使用断点调试，一步一步的看源码执行的过程，从而发现错误所在。



> 什么是断点调试

**百度百科：**断点调试是指自己在程序的某一行设置一个**断点**，调试时，程序运行到这一行就会停住，然后你可以一步一步往下调试，调试过程中可以看各个变量当前的值，出错的话，调试到出错的代码行即显示错误，停下。然后程序可以进行分析从而找到这个。



> 快捷键

```c
f5： 开始调试 、执行到下一个断点
f11: 逐句执行代码, 会进入到函数体中
f10: 逐过程执行(遇到函数，不会进入到函数体)
shift+f11: 跳出(跳出某个函数, 跳出前，会将该函数执行完)
shift+f5: 终止调试
```



## 指针

指针表示一个地址（存放的是地址），如下图

![image-20201014195149538](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014195149538.png)

```c
//指针入门
# include <stdio.h>
void main(){
	int num = 1;
	int *ptr = &num;
	// num的地址
	// 如果要输出一个变量的地址，使用的格式是%p
	printf("num 的地址为：%p",&num);
	printf("\n ptr 的地址为：%p ,  ptr存放值的一个地址为：%p ， ptr指向的地址的值是多少%d：",&ptr,ptr,*ptr);
	getchar();
}
```

运行结果：

![image-20201014195352094](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014195352094.png) 



练习案例：

```c
//指针入门
# include <stdio.h>
void main(){
	//写一个程序，获取一个 int 变量 num 的地址，并显示到终端尚硅谷高校大学生 C 语言课程 
	//将 num 的地址赋给指针 ptr , 并通过 ptr 去修改 num 的值. 
	//分析 ptr 的类型 是 int * , 注意指针的类型和 该指针指向的变量类型是对应关系 
	//并画出案例的内存布局图 
	//int num = 88; 
	//int * ptr = # 
	////先输出 num 没有修改的情况 
	//printf("\nnum 的值=%d num 的地址=%p", num, &num); // num= 88 
	//*ptr = 99; // 通过 ptr 去修改 num 的值, num 变量的值也就相应的被修改 
	//printf("\n num 的值=%d num 的地址=%p", num, &num);// num = 99 
	int a = 300; // a = 300 
	int b = 400; // b = 400 
	int * ptr = &a; //ok ptr 指向 a 
	*ptr = 100; // a = 100 
	ptr = &b; // ok ptr 指向 b 
	*ptr = 200; // b = 200 
	printf("\n a=%d,b=%d,*ptr=%d", a, b, *ptr); //a = 100, b = 200, *ptr = 200 
	getchar(); 
}
```





### 指针细节说明 

1、**基本类型，都有对应的指针类型**， 形式为 数据类型 *，比如 int 的对应的指针就是 int *, float 对应的指针类型就是 float * , 依次类推。 

2、 此外还有**指向数组的指针**、**指向结构体的指针**，**指向共用体的指针**，(二级指针，多级指针)后面我们再讲到数 

组、结构体和共用体时，还会详细讲解。

 

### 值传递和地址传递



1、 默认==传递值==的类型：基本数据类型 (整型类型、小数类型，字符类型), 结构体, 共用体。 

2、默认==地址传递（指针传递）==  的类似：指针、数组

![](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201014201241230.png)



### 指针的运算符

指针是一个用数值表示的地址。可以对指针执行算术运算。可以对指针进行四种算术运算：++、--、+、-。

> 指针递增操作(++)

```c
#include<stdio.h>
const int MAX = 3;
int main () {
	int var[] = {10, 100, 200}; // 
	int i, *ptr; // 
	ptr = var;
	for ( i = 0; i < MAX; i++) {
		printf("var[%d] 地址= %p \n", i, ptr );
		printf("存储值：var[%d] = %d\n", i, *ptr );
		ptr++;
	}
	getchar();
	return 0; 
}
```

运行结果：

![image-20201025113320587](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025113320587.png) 



> 指针递增操作(--)

```c
#include<stdio.h>
const int MAX = 3;
int main () {
	int var[] = {10, 100, 200};
	int i, *ptr;
	/* 指针中最后一个元素的地址 */
	ptr = &var[MAX-1];
	for ( i = MAX; i > 0; i--) {
		printf("ptr存放的地址=%p, var[%d] 的地址= %p\n", ptr, i
			&var[i-1] );
		printf("存储值：var[%d] = %d\n", i-1, *ptr );
		ptr--; }
	getchar();
	return 0;
}
```

运行结果：

![image-20201025113419577](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025113419577.png) 



> 指针+

```
#include<stdio.h>
int main () {
	int var[] = {10, 100, 200};
	int i, *ptr;
	ptr = var;
	ptr += 2; //
	printf("var[2]=%d var[2]的地址=%p ptr=%p ptr指向的地址的内容=%d" ,var[2], &var[2], ptr, *ptr);
		getchar();
	return 0; 

}

```

运行结果：

![image-20201025113652578](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025113652578.png) 





### 指针数组

要让数组的元素 指向 int 或其他数据类型的地址(指针)。可以使用指针数组

> 指针数组定义

```c
数据类型 *指针数组名[大小];
比如： int *ptr[3];
```

1、 ptr 声明为一个指针数组
2、由 3 个整数指针组成。因此，ptr 中的每个元素，都是一个指向 int 值的指针。





#### 快速入门

```c
#include<stdio.h>
void main (){
	int var[] = {10, 100, 200};
	int i, *ptr[3];
	int MAX = 3;
	for ( i = 0; i < MAX; i++)
	{
		ptr[i] = &var[i]; /* 赋值为整数的地址 */ }
	for ( i = 0; i < MAX; i++)
	{
		printf("Value of var[%d] = %d\n", i, *ptr[i] );
	}
	getchar();
}
```





>  字符串指针数组

请编写程序，定义一个指向字符的指针数组来存储字符串列表(四大名著书名)， 并通过遍历 该指针数组，显示字符串信息 ， (即：定义一个指针数组，该数组的每个元素，指向的是一个字符串)

![image-20201025121318301](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025121318301.png) 

代码实现：

```c
#include<stdio.h>
void main(){
	char *books[] = {
	"水浒传",
	"三国演义",
	"西游记",
	"红楼梦",
	"水浒传"
	};
	int i ,len = 4;
	for(i=0; i<len; i++){
		printf("\nboos[%d]指向的字符串是《%s》",i,books[i] ); // 不用加*
	}
	getchar();

}

```





### 多重指针

指向指针的指针是一种**多级间接寻址**的形式，或者说是一个指针链。通常，一个指针包含一个变量的地址。当我们定义一个指向指针的指针时，第一个指针包含了第二个指针的地址，第二个指针指向包含实际值的位置(如下图)

![image-20201025123217972](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025123217972.png) 

```c
#include<stdio.h>
int main () {
	int var;
	int *ptr;
	int **pptr;
	var = 3000;
	ptr = &var;
	pptr = &ptr;
	printf("var的地址=%p var = %d \n", &var, var );
	printf("ptr 的本身的地址=%p ptr存放的地址=%p *ptr = %d \n", &ptr, ptr, *ptr );
		printf("pptr 本身地址 = %p pptr存放的地址=%p **pptr = %d\n", &pptr, pptr, **pptr);
		getchar();
	return 0;
}
```

![image-20201025123532988](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025123532988.png) 





### 返回指针的函数

请编写一个函数 strlong()，返回两个字符串中较长的一个。

```c
#include<stdio.h>
#include <string.h>
char *strlong(char *str1, char *str2){ //函数返回的char * (指针)
	printf("\nstr1的长度%d str2的长度%d", strlen(str1), strlen(str2));
		if(strlen(str1) >= strlen(str2)){
			return str1;
		}else{
			return str2;
		}
}
int main(){
	char str1[30], str2[30], *str;
	printf("\n请输入第1个字符串");
	gets(str1);
	printf("\n请输入第2个字符串");
	gets(str2);
	str = strlong(str1, str2);
	printf("\nLonger string: %s \n", str);
	getchar();
	return 0;
}
```

结果如图：

![image-20201025144702950](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025144702950.png) 



1、用指针作为函数返回值时需要注意，函数运行结束后会销毁在它内部定义的所有局部数据，包括局部变量、局部数组和形式参数，函数返回的指针不能指向这些数据【案例演示】

![image-20201025145430722](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201025145430722.png) 

2、**函数运行结束后会销毁该函数所有的局部数据，** **这里所谓的销毁并不是将局部数据所占用的内****存全部清零**，而是程序放弃对它的使用权限，后面的代码可以使用这块内存

3、C 语言不支持在调用函数时返回局部变量的地址，如果确实有这样的需求，需要定义局部变量为 **static** 变量 





### 函数指针

1、 一个函数总是占用一段连续的内存区域，函数名在表达式中有时也会被转换为该函数所在内存区域的首地址，这和数组名非常类似。

2、把函数的这个首地址（或称入口地址）赋予一个指针变量，使指针变量指向函数所在的内存区域，然后通过指针变量就可以找到并调用该函数。这种指针就是函数指针

`returnType (*pointerName)(param list);`

```
1、 `returnType `为函数返回值类型

2、`pointerName `为指针名称

3、`param list` 为函数参数列表

4、参数列表中可以同时给出参数的类型和名称，也可以只给出参数的类型，省略参数的名称

5、注意( )的优先级高于*，第一个括号不能省略，如果写作r`eturnType *pointerName(param list);`就成了函数原型，它表明函数的返回值类型为`returnType `
```



演示案例：

```c
    #include<stdio.h>
    int max(int a, int b);
    int main(){
        int x, y, maxVal;
        // 说明：函数指针	
        // 函数指针的名字：pmax
        // int 表示：函数指针指向的方式返回值是int类型
        // (int, int) 表示该函数指针指向的函数形参是接受两个int
        // 在定义指针函数时，也可以写上形参名：例如-int (*pmax)(int x, int y) =max
        int (*pmax)(int, int) = max; 
        printf("Pleace input two numbers:");
        scanf("%d %d", &x, &y);
        maxVal = (*pmax)(x, y);
        printf("Max value: %d\n", maxVal);
        getchar();
        getchar();
        return 0;
    }
    int max(int a, int b){
        return a>b ? a : b;
    }
```



> 回调函数

1、 函数指针变量可以作为某个函数的参数来使用的，回调函数就是一个通过函数指针调用的函数。

2、简单的讲：回调函数是由别人的函数执行时调用你传入的函数（通过函数指针完成）



案例演示：

```c
#include<stdio.h>
#include <stdlib.h> 


	/*
		回调函数)
		f 就是一个函数指针，他可以接收的函数式 (返回 int，没有形参的函数)
	*/
void initArray(int *array, int arraySize, int (*f)(void)) {
	int i ;
	for ( i=0; i<arraySize; i++)
		array[i] = f(); // 循环10次，回调了我们的getNextRandomValue(void)函数
}
// 获取随机值
int getNextRandomValue(void) {
	return rand();  //系统函数，可以返回一个随机整数！
}
int main(void) {
	int myarray[10],i;
	/*
		说明：
		1、调用initArray函数
		2、传入一个函数名getNextRandomValue(地址)，需要使用函数指针介绍
		initArray(myarray, 10, getNextRandomValue);

	*/
	initArray(myarray, 10, getNextRandomValue);
	for(i = 0; i < 10; i++) {
		printf("%d ", myarray[i]);
	}
	printf("\n");
	getchar();
	return 0;
}
```











1 指针变量存放的是地址，从这个角度看指针的本质就是地址。

2变量声明的时候，如果没有确切的地址赋值，为指针变量赋一个 NULL 值是好的编程习惯。

3 赋为 NULL 值的指针被称为空指针，NULL 指针是一个定义在标准库 <stdio.h>中的值为零的常量 **#define NULL 0** 

```c
#include<stdio.h>
void main(){

	int *p = NULL; // 空指针p
	int num =33;
	p = &num;
	printf("p= %d",*p);
	getchar();
}
```

4、 指针使用一览 (见后)

### 空指针

 赋为 NULL 值的指针被称为空指针，NULL 指针是一个定义在标准库 <stdio.h>中的值为零的常量 **#define NULL 0** 

```c
#include<stdio.h>
void main(){

	int *p = NULL; // 空指针p
	int num =33;
	p = &num;
	printf("p= %d",*p);
	getchar();
}
```



### 动态内存分配

![C程序内存分布图](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201021135523300.png) 

1、全局变量——内存中的静态存储区 

2、非静态的局部变量——内存中的动态存储区——stack 栈

3、临时使用的数据---建立动态内存分配区域，需要时随时开辟，不需要时及时释放——heap 堆

4、根据需要向系统申请所需大小的空间，由于未在声明部分定义其为变量或者数组，不能通过变量名或者数组名来引用这些数据，只能通过指针来引用



> 内存动态分配的相关函数

1、头文件 `#Include <stdlib.h> `声明了四个关于内存动态分配的函数

2、函数原型

```c 
 void * malloc（usigned int size） //malloc = memory allocation 
```

- 作用——在内存的动态存储区(堆区)中分配一个长度为size的连续空间。
- 形参size的类型为无符号整型，函数返回值是所分配区域的第一个字节的地址，即此函数是一个指针型函数，返回的指针指向该分配域的开头位置。
- `malloc(100);` 开辟100字节的临时空间，返回值为其第一个字节的地址

3、函数原型 

```c
void *calloc（unsigned n,unsigned size）
```

- 作用——在内存的动态存储区中分配n个长度为size的连续空间，这个空间一般比较大，足以保存一个数组
- 用calloc函数可以为一维数组开辟动态存储空间，n为数组元素个数，每个元素长度为size.
- 函数返回指向所分配域的起始位置的指针；分配不成功，返回NULL。 
- p = calloc(50, 4); //开辟 50*4 个字节临时空间，把起始地址分配给指针变量 p



4、函数原型：`void free（void *p）`

- 作用——释放变量p所指向的动态空间，使这部分空间能重新被其他变量使用。
- p是最近一次调用calloc或malloc函数时的函数返回值 
- free函数无返回值 
- free(p); // 释放p 所指向的已分配的动态空间

5、函数原型 `void *realloc（void *p，unsigned int size)`

- 作用——重新分配malloc或calloc函数获得的动态空间大小，将p指向的动态空间大小改变为size，p的值不变，分配失败返回NULL
- realloc(p, 50); // 将 p 所指向的已分配的动态空间 改为50字节

6、返回类型说明

C99标准把malloc，calloc，relloc函数的基本类型定位void类型，这种指针称为无类型指针(typeless pointer),就是指不指向某一种特定的类型的数据，仅提供一个存地址，而不指向任何具体的对象。

![image-20201026084948569](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201026084948569.png)

![image-20201026085006288](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201026085006288.png)



> 应用实例

动态创建数组，输入 5 个学生的成绩，另外一个函数检测成绩低于 60 分的，输出不合格的成绩

代码实现：

```c

```







## 结构体

张老太养了两只猫猫:一只名字叫小白,今年3岁,白色。还有一只叫小花,今年100岁,花色。请编写一个程序，当用户输入小猫的名字时，就显示该猫的名字，年龄，颜色。如果用户输入的小猫名错误，则显示 张老太没有这只猫猫

**目前所学知识-传统方案：**

```
void main() {
char cat1Name[10] = "小白";
int cat1Age = 3;
char cat1Color[10] = "白色";


char *catsName[2] = {
	"小白",
	"小花"
} ;
int catsAge[] = {3,100}
```

==缺点：==

不利于数据管理和维护，因为本身的猫的三个属性（名字，年龄，颜色）是一个整体，传统方式会将其拆解，这是就需要用到结构体！



### 结构体快速入门

结构体与结构体变量的关系示意图

![image-20201026174344084](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201026174344084.png)

==说明：==除了可以有Cat 结构体，还可以有 Person 结构体， 有 Fish 结构体等等



>  结构体与结构体变量的关系示意图

![image-20201026174517698](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201026174517698.png)



**快速入门-面向对象的方式(struct)解决养猫问题**

![image-20201026174552169](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201026174552169.png) 

代码实现：

```c
#include<stdio.h>
void main(){

	struct Cat{ //结构体名为Cat，Cat是我们自己构成的一个数据类型！
		char *name; // 名字，使用指针，指向一个字符串
		int age; // 年龄
		char *color; // 颜色
	};

	// 使用结构体Cat，创建对应的变量
	struct Cat cat1; 
	
	cat1.name ="小白";
	cat1.age = 3;
	cat1.color = "白色";


	// 使用结构体Cat，创建对应的变量
	struct Cat cat2; 
	cat2.name ="小花";
	cat2.age = 100;
	cat2.color = "花色";

	// 输出两只猫的信息
	printf("\n第一只猫 name = %s age= %d color = %s",cat1.name,cat1.age,cat1.color);
	printf("\n第二只猫 name = %s age= %d color = %s",cat2.name,cat2.age,cat2.color);
	getchar();
}
```





> 结构体和结构体变量的区别和联系



结构体是==自定义的数据类型==，表示的是一种==数据类型==.

结构体变量代表一个具体变量，好比

```c
nt num1 ; // int 是数据类型, 而num1 是一个具体的int变量
struct Cat cat1; // Cat 是结构体数据类型， 而cat1 是一个Cat变量
```

Cat 就像一个“模板”，定义出来的结构体变量都含有相同的成员。也可以将结构体比作“图纸”，将结构体变量比作“零件”，根据同一张图纸生产出来的零件的特性都是一样的



>  结构体内存分析：

![image-20201026202638057](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201026202638057.png)



### 声明结构体



```c
struct 结构体名称 { // 结构体名首字母大写，比如Cat, Person
	成员列表; 
}
```

举例:

```c
#include<stdio.h>
void main(){
	struct Student{
		char *name; //姓名
		int num; //学号
		int age; //年龄
		char group; //所在学习小组
		float score; //成绩
		//成员也可以是结构体

	};
}
```



### 注意事项和细节说明

1、成员声明语法同变量，示例： 数据类型成员名;
2、 字段的类型可以为：**基本类型、数组或指针、结构体等**
3、 在创建一个结构体变量后，需要给成员赋值，如果没有赋值就使用可能导致程序异常终止。[ 案例演示 ]：

```c
#include<stdio.h>
void main(){
	struct Cat{ // 结构体名字建议首写字母大写
		char *name; //名字 , 这里需要使用指针类型
		int age; //年龄
		char *color; // 颜色
	}; 
	struct Cat cat1;
	printf("\n名字=%s 年龄=%d 颜色=%s ", cat1.name, cat1.age, cat);
	getchar();
}
```



4、 不同结构体变量的成员是独立，互不影响，一个结构体变量的成员 更改，不影响另外一个。[案例演示+图(Monster)]

![image-20201026203318902](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201026203318902.png)





### 创建结构体和结构体变量



> 方式1-先定义结构体，然后再创建结构体变量

```c
#include<stdio.h>
void main(){
	struct Stu{
		char *name; //姓名
		int num; //学号
		int age; //年龄
		char group; //所在学习小组
		float score; //成绩
	};
	struct Stu stu1, stu2;
	//定义了两个变量 stu1 和 stu2，它们都是 Stu 类型，都由 5 个成员组成
	//注意关键字struct不能少
}
```



> 方式2-在定义结构体的同时定义结构体变量

```c
#include<stdio.h>
void main(){
		struct Stu{
		char *name; //姓名
		int num; //学号
		int age; //年龄
		char group; //所在学习小组
		float score; //成绩
	} stu1, stu2;
	//在定义结构体Stu 的同时，创建了两个结构体变量 stu1 和 stu2
}
```



> 方式3-如果只需要 stu1、stu2 两个变量，`后面不需要再使用结构体数据类型，定义其他变量，在定义时也可以不给出结构体名`

```c
struct { //没有写 Stu
char *name; //姓名
int num; //学号
int age; //年龄
char group; //所在学习小组
float score; //成绩
} stu1, stu2;
stu1.name = "tom"; stu1.num = 100;....
//1. 该结构体数据类型，没有名, 匿名结构体
//2. stu1 和 stu2 就是 该结构体的两个变量
```



案例2：

```
#include<stdio.h>
void main(){
	struct{
		char *name; //姓名
		int num; //学号
		int age; //年龄
		char group; //所在小组
		float score; //成绩
	} stu1 = {"贾宝玉", 11, 18, 'B', 90.50}, stu2 = { "林黛玉", 12, 16, 'A', 100 };
}
```



### 应用案例

1、 编写一个Dog结构体，包含name(char[10])、age(int)、weight(double)属性

```c
#include<stdio.h>

struct Dog{
	char *name;
	int age;
	double weight;
};

// say函数
char *say(struct Dog dog){
	// 将这个信息放入一个字符串中
	static char info[100]; // 局部变量
	sprintf(info, "name=%s age = %d weight = %.2f",dog.name,dog.age,dog.weight);
	return info;
}

void main(){
	//定义结构体变量
	struct Dog dog = {"贺晶晶",18,99.9};
	char* info = NULL;
	info = say(dog);
	printf("小狗信息为：%s",info);
	getchar();
}

```



**景区门票案例：**

2.请编写游人结构体(Visitor)，根据年龄段决定能够购买的门票价格并输出 

3.规则：年龄>18 , 门票为 20 元，其它情况免费。 

4.可以循环从控制台输入名字和年龄，打印门票收费情况, 如果名字输入 n ,则退出程序。

```c
#include<stdio.h>
#include<string.h>
//定义结构体 
struct Visitor { 
	char name[10]; 
	int age; 
	double pay; //应付票价 
}; 
void ticket(struct Visitor * visitor) { 
	//判断 
	if( (*visitor).age > 18) { 
		(*visitor).pay = 20; 
	} else { 
		(*visitor).pay = 0; 
	} 
}

void main() { 
	//测试 
	//创建结构体变量(创建一个游客) 
	struct Visitor visitor; 
	//循环的输入名字和年龄 
	while(1) { 
		printf("\n 请输入游客名字"); 
		scanf("%s", visitor.name); 
		//判断如果名字输入 n ,则退出程序 
		if(!strcmp("n", visitor.name) ) { 
			break; 
		}
		printf("\n 请输入游客年龄"); 
		scanf("%d", &visitor.age);
		//调用函数 ticket ， 获取应付的票价 
		ticket(&visitor); 
		printf("\n 该游客应付票价=%.2f", visitor.pay); 
	}
	printf("退出程序"); 
	getchar(); 
	getchar(); 
}
```



**盒子案例：**

编程创建一个 Box 结构体，在其中定义三个成员表示一个立方体的长、宽和高，长宽高可以通过控制台输入。 定义一个函数获取立方体的体积(volume)。 创建一个结构体，打印给定尺寸的立方体的体积。 

```c

```





## 共用体

现有一张关于学生信息和教师信息的表格。学生信息包括姓名、编号、性别、职业、分数，教师的信息包括姓名、编号、性别、职业、教学科目。请看下面的表格：

![image-20201027110700932](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201027110700932.png) 



**传统的方式来解决：**

```c
struct Person{
char name[20];
int num;
char sex;
char profession;
float score; // 学生使用 score
char course[20]; // 老师使用course 
};
```

==会造成 空间的浪费 , 比如学生只使用 score ,但是 也占用了course 成员的20个字节==



**解决方案**

(1)、做 struct Stu 和 struct Teacher [但如果职业很多，就会对应多个结构体类型, 不利于管理

(2)、使用共用体



### 什么是共用体

1、共用体（Union）属于 构造类型，它可以包含多个类型不同的成员。和结构体非常类似, 但是也有不同的地方.
共用体有时也被称为==联合==或者==联合体==, 定义格式如下：

```c
union 共用体名{
	成员列表
};
```

结构体和共用体的区别在于：结构体的各个成员会占用不同的内存，互相之间没有影响；而共用体的所有成员占用同一段内存，修改一个成员会影响其余所有成员



### 快速入门

> 定义共用体类型和共用体变量的三种方式(和结构体一样)

1：先定义共用体，然后再创建共用体变量

```c
union data{
int n;
char ch;
double f;
};
union data a, b, c;
```

2: 定义共用体的同时也创建共用体变量

```c
union data{
int n;
char ch;
double f;
} a, b, c;
```

3：匿名共用体

```c
union{
int n;
char ch;
double f;
} a, b, c;
```



案例演示：

```c
#include<stdio.h>

union data{  // data就是一个共用体数据类型，三个成员公用数据空间，该空间的大小以占用最大的成员为准
	int n; // 4个字节
	char ch; // 1 个字节
	short m; // 2个字节
};
void main(){
	union data a; // 定义一个共用体变量 a
	printf("%d  --  %d\n",sizeof(a),sizeof(union data)); // 4个字节
	a.n = 0x40; // 16进制
	printf("%d, %c, %d\n", a.n, a.ch, a.m);
	a.ch = '9';
	printf("%d, %c, %d\n", a.n, a.ch, a.m);
	a.m = 0x2059;
	printf("%d, %c, %d\n", a.n, a.ch, a.m);
	a.n = 0x3E25AD54;
	printf("%d, %c, %d\n", a.n, a.ch, a.m);
	getchar();

}
```



![image-20201027121701379](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201027121701379.png) 



### 共用体内存布局分析

要深入理解为什么前面的案例输出的结果，就需要剖析共用体在内存中是如何布局的

![image-20201028144337955](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201028144337955.png) 





### 案例实践

现有一张关于学生信息和教师信息的表格。学生信息包括姓名、编号、性别、职业、分数，教师的信息包括姓名、编号、性别、职业、教学科目。请看下面的表格-请使用共用体编程完成.

![image-20201028145256082](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201028145256082.png) 





```c
#include<stdio.h>
#define TOTAL 2 //人员总数

// 定义了一个结构体Persion，包含了一个匿名共用体sc
struct Person{
	char name[20]; // name
	int num;// 编号
	char sex; // 性别 f = 女 m = 男
	char profession; // 职业 s=>学生 t=>老师
	union{ //sc是一个共用体变量
		float score;
		char course[20];
	} sc;
};
void main(){
	int i;
	struct Person persons[TOTAL]; // 定义了一个结构体数组
	//输入人员信息
	for(i=0; i<TOTAL; i++){
		printf("Please input infomation: ");
		scanf("%s %d %c %c", persons[i].name, &(persons[i].num), &(persons[i].sex), &(persons[i].profession)); // 对于int 和char字符，需要在前面加& 用于取地址
		if(persons[i].profession == 's'){ //如果是学生
			printf("Please input the student's score:");
			scanf("%f", &persons[i].sc.score);
		}else{ //如果是老师
			printf("Please input the teacher's course");
			scanf("%s", persons[i].sc.course);
		}
		fflush(stdin); // 刷新输入
	}

	// 输出成员信息
	printf("\nName\t\tNum\tSex\tProfession\tScore / Course\n");
	for(i=0; i<TOTAL; i++){
		if(persons[i].profession=='s'){
			printf("\n%s\t\t%d\t%c\t%c\t%f\n",persons[i].name,persons[i].num,persons[i].sex,persons[i].profession,persons[i].sc.score);
		}else{
			printf("\n%s\t\t%d\t%c\t%c\t%s\n",persons[i].name,persons[i].num,persons[i].sex,persons[i].profession,persons[i].sc.course);
		}
	}
	getchar(); // 去回车 
	getchar();
}
```



## CRM系统（项目）

```c
#include <stdio.h>
#include <string.h>

struct Customer {
	int id;
	int age;
	char name[10];
	char gender;
	char  phone[10];
	char  email[10];
};
char key ;
char loop=1;
int customerNum=1;


//客户结构体数组
struct Customer customers[20];

//得到一个客户的信息
void getInfo(struct Customer *customer) {
	
	/*sprintf(info, "\n%d\t%s\t%c\t%d\t%s\t%s", (*customer).id, (*customer).name, 
		(*customer).gender, (*customer).age, (*customer).phone,(*customer).email);*/
	printf("\n%d\t%s\t%c\t%d\t%s\t%s", (*customer).id, (*customer).name, 
		(*customer).gender, (*customer).age, (*customer).phone,(*customer).email);
}

//提供各种操作


//1. 添加
void add(){
	//编号自动增长
	customers[customerNum].id = customerNum + 1;
	printf("\n---------------------添加客户---------------------");
	printf("\n姓名：");
	scanf("%s", customers[customerNum].name);
	getchar();
	printf("\n性别：");
	scanf("%c", &(customers[customerNum].gender));
	getchar();
	printf("\n年龄：");
	scanf("%d", &(customers[customerNum].age));
	getchar();
	printf("\n电话：");
	scanf("%s", customers[customerNum].phone);
	getchar();
	printf("\n邮箱：");
	scanf("%s",customers[customerNum].email);
	getchar();
	printf("\n---------------------添加完成---------------------");
	customerNum++;
	
}

//根据输入的id去找对应的下标，如果找不到返回-1
int findIndex(int id){
	int index = -1;
	int i;
	for (i = 0; i < customerNum ; i++) {
		if (customers[i].id == id) {
			index = i;
			break;
		}
	}
	return index;
}

//2. 删除客户
int del(int id){
	//找到id对应的元素下标
	int index = findIndex(id);
	int i;
	if (index == -1) {
		return 0;//说明这个客户不存在..
	}else {
		//找到,就从index+1开始整体前移
		for (i = index + 1; i < customerNum; i++) {
			customers[i - 1] = customers[i];
		}
		--customerNum;
		return 1;
	}
}

//显示部分

//1. 显示所有
void showList(){
	int i = 0;
	printf("\n---------------------------客户列表---------------------------");
	printf("\n编号\t姓名\t性别\t年龄\t电话\t邮箱");
	for (i = 0; i < customerNum; i++) {
		getInfo(&customers[i]);
	}
}

//2. 完成删除 界面
//---------------------删除客户---------------------
//请选择待删除客户编号(-1退出)：1
//确认是否删除(Y/N)：y
//---------------------删除完成---------------------

void delView(){
	int id;
	char choice = ' ';
	printf("\n---------------------删除客户---------------------");
	printf("\n请选择待删除客户编号(-1退出)：");
	scanf("%d", &id);
	getchar();
	if (id == -1) {
		printf("\n---------------------删除没有完成---------------------");
		return;
	}

	printf("确认是否删除(Y/N)：");
	scanf("%c", &choice);
	getchar();
	if (choice == 'Y') {
		if(del(id)){
			printf("\n---------------------删除完成---------------------");
		}else{
			printf("\n---------------------删除没有完成，无此id---------------------");
		}
	} 
}


//3. 主菜单
void mainMenu() {
	do {
		printf("\n-----------------客户信息管理软件-----------------");
		printf("\n                 1 添 加 客 户");
		printf("\n                 2 修 改 客 户");
		printf("\n                 3 删 除 客 户");
		printf("\n                 4 客 户 列 表");
		printf("\n                 5 退          出");
		printf("\n请选择(1-5):");
		scanf("%c", &key);
		getchar();

		switch (key) {
		case '1':
			add();
			break;
		case '2':
			break;
		case '3':
			delView();
			break;
		case '4':
			showList();
			break;
		case '5':
			loop = 0;
			break;
		default:
			printf("\n输入错误，请重新输入");
			break;
		}

	} while (loop);

	printf("\n你已经成功的退出了系统....");
	getchar();
}


void main() {
	////为了测试方便
	customers[0].id = 1;
	customers[0].age = 18;
	strcpy(customers[0].email , "shiye13@foxmail.com");
	customers[0].gender = 'f';
	strcpy(customers[0].name , "叶十三");
	strcpy(customers[0].phone , "110");

	mainMenu();

	return ;
}
```





## 文件操作

文件，对我们并不陌生,文件是数据源(保存数据的地方)的一种,比如大家经常使用的word文档，txt文件，excel文件...都是文件。文件最

主要的作用就是保存数据,它既可以保存一张图片,也可以保持视频,声音...



> 文件在程序中是以流的形式来操作

![image-20201030121359722](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201030121359722.png) 

**流：数据在数据源(文件)和程序(内存)之间经历的路径**

**输入流：数据从数据源(文件)到程序(内存)的路径**

**输出流：数据从程序(内存)到数据源(文件)的路径**



==C 标准库 - stdio .h==该头文件定义了三个变量类型、一些宏和各种函数来执行输入和输出, 在开发过程中，可以来查询

![image-20201030121700985](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201030121700985.png) 



### C 输入& 输出

1、当我们提到==输入==时，这意味着要向程序写入一些数据。输入可以是以文件的形式或从命令行中进行。C 语言提供了一系列内置的函

数来读取给定的输入，并根据需要写入到程序中。

2、当我们提到==输出==时，这意味着要在屏幕上、打印机上或任意文件中显示一些数据。C 语言提供了一系列内置的函数来输出数据到计

算机屏幕上和保存数据到文本文件或二进制文件中



### 标准文件

==**C 语言把所有的设备都当作文件。所以设备（比如显示器）被处理的方式与文件相同。**==以下三个文件会在程序执行时自动打开，以便访问键盘和屏幕

![image-20201030122047600](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201030122047600.png) 

2、==**文件指针是访问文件的方式**==，我们会讲解如何从屏幕读取值以及如何把结果输出到屏幕上。

3、C 语言中的 I/O (输入/输出) 通常使用` printf() `和 `scanf() `两个函数。`scanf() `函数用于从标准输入（键盘）读取并格式

化，` printf() `函数发送格式化输出到标准输出（屏幕）





>  案例演示, 将内容输出到屏幕

```c
#include <stdio.h> // 执行 printf() 函数需要该库
int main()
{
	printf("hello"); //显示引号中的内容
	return 0;
}
```





### getchar()&putchar()

> getchar()

`int getchar(void) `函数从屏幕读取下一个可用的字符，并把它返回为一个整数。这个函数在同一个时间内只会读取一个单一的字

符。您可以在循环内使用这个方法，以便从屏幕上读取多个字符。



> putchar()

`int putchar(int c) `函数把字符输出到屏幕上，并返回相同的字符。这个函数在同一个时间内只会输出一个单一的字符。

您可以在循环内使用这个方法，以便在屏幕上输出多个字符



测试案例

```c
#include <stdio.h> 
int main()
{	
	int c;
	printf("Please enter a value:"); 
	c = getchar(); // 读取一个char，并返回一个int
	printf("\nYou entered：");
	putchar( c );// 可以在屏幕上显示
	printf("\n");

	getchar(); // 过滤回车
	getchar(); // 停止界面
	return 0;
}
```



###  gets() & puts()

> gets()

1、`char *gets(char *s) `函数从 stdin 读取一行到 s 所指向的缓冲区，直到一个终止符或 EOF。



> puts()

2、 `int puts(const char *s) `函数把字符串 s 和一个尾随的换行符写入到 stdout。



应用案例：

```c
#include <stdio.h>
int main( )
{
	char str[100];
	printf( "Please enter a character :");
	gets( str );
	printf( "\nYou entered: ");
	puts( str );
	getchar();

	return 0;
}
```



### scanf() 和 printf() 

1、`int scanf(const char *format, ...) `函数从标准输入流 stdin 读取输入，并根据提供的 format 来浏览输入。

2、 `int printf(const char *format, ...) `函数把输出写入到标准输出流 stdout ，并根据提供的格式产生输出。

3、format 可以是一个简单的常量字符串，但是您可以分别指定 %s、%d、%c、%f 等来输出或读取字符串、整数、字符或浮点数。还有许多其他可用的格式选项，可以根据需要使用。如需了解完整的细节，可以查看这些函数的参考手册。现在让我们通过下面这个简单的实例来加深理解



**应用实例：**

==您输入一个文本并按下回车键时，程序读取输入， 但是要求格式要匹配==

```
#include <stdio.h>
int main( ) {
	char str[100];
	int i;
	printf( "please input  a value :");
	scanf("%s %d", str, &i);
	getchar(); // 过滤回车
	printf( "\nYou entered: %s %d ", str, i);
	printf("\n");
	getchar(); // 界面停止
	return 0;
}
```



![image-20201030131527571](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201030131527571.png) 





### C文件读写

 

1、讲解了 C 语言处理的标准输入和输出设备。我们将介绍 如何创建、打开、关闭文本文件或二进制文件。

2、一个文件，无论它是文本文件还是二进制文件，都是代表了一系列的字节。C 语言不仅提供了访问顶层的函数，也提供了底层（OS）调用来处理存储设备上的文件。==（重要）==





### 打开文件 

使用` fopen( ) `函数来创建一个新的文件或者打开一个已有的文件，这个调用会初始化类型 FILE 的一个对象，类型 FILE 包含了所

有用来控制流的必要的信息。下面是这个函数调用的原型：

```c
FILE *fopen( const char * filename, const char * mode );
```

**说明：**

1、ilename 是字符串,用来命名文件

2、访问模式 mode 的值可以是下列值中的一个：

如果处理的是二进制文件(图片，视频..)，则需使用下面的访问模式: "rb", "wb", "ab", "rb+", "r+b", "wb+", "w+b", "ab+", "a+b" //b :binary 二进制 

![image-20201030132040557](https://yerenping.oss-cn-beijing.aliyuncs.com/imagesimage-20201030132040557.png) 

### 关闭文件

```
int fclose( FILE *fp );
```

1、 如果成功关闭文件，fclose( ) 函数返回零，如果关闭文件时发生错误，函数返回 EOF。这个函数实际上，会清空缓冲区中的数

据，关闭文件，并释放用于该文件的所有内存。EOF 是一个定义在头文件 stdio.h 中的常量。

2、 C 标准库提供了各种函数来按字符或者以固定长度字符串的形式读写文件。

3、==使用完文件后(读，写)，一定要将该文件关闭==



### 写入文件

下面是把字符写入到流中的函数

```c
int fputc( int c, FILE *fp );
```

**说明：**

函数 fputc() 把参数 c 的字符值写入到 fp 所指向的输出流中。如果写入成功，它会返回写入的字符，如果发生错误，则会返回 EOF。

您可以使用下面的函数来把一个以 null 结尾的字符串写入到流中：

```
int fputs( const char *s, FILE *fp );
```

**说明：**

函数 fputs() 把字符串 s 写入到 fp 所指向的输出流中。如果写入成功，它会返回一个非负值，如果发生错误，则会返回 EOF。您也可

以使用 int fprintf(FILE *fp,const char *format, ...) 函数来写把一个字符串写入到文件中



应用案例：

```c
#include <stdio.h>
int main( ) {
	FILE *fp = NULL;
	fp = fopen("d:/a.txt","w+");  //w+覆盖原先内容，非追加
	// 将内容写入到文件中
	fprintf(fp,"你好，是叶十三\n");
	fputs("你好，胡佳妮\n",fp);

	//close file ，if we not close the file,our content is not saved to the file
	fclose(fp);
	printf("创建，写入信息完成");
	getchar();
}
```



### 读取文件

下面是从文件读取单个字符的函数

```c
int fgetc( FILE * fp );
```

==**说明：**==

fgetc() 函数从 fp 所指向的输入文件中读取一个字符。返回值是读取的字符，如果发生错误则返回 EOF。

下面的函数从流中读取一个字符串：

```
char *fgets( char *buf, int n, FILE *fp );
```

1、 说明：函数 fgets() 从 fp 所指向的输入流中读取 n - 1 个字符。它会把读取的字符串复制到缓冲区 buf，并在最后追加一个 null 字符来终止字符串。如果这个函数在读取最后一个字符之前就遇到一个换行符 '\n' 或文件的末尾 EOF，则只会返回读取到的字符，包括换行符。

2、也可以使用` int fscanf(FILE *fp, const char *format, ...) `函数来从文件中读取字符串，但是在遇到第一个空格字符时，它会停止读取



应用案例：

```c
#include <stdio.h>
int main( ) {
	// 创建一个文件指针
	FILE *fp = NULL;
	// 定义一个缓冲区
	char buff[1024];
	// 打开文件
	fp = fopen("d:/a.txt","r");
	// 方法1 ：从fp所指向的文件读取一行到buff中
	//fscanf(fp,"%s",buff);
	//printf("%s\n",buff);
	//getchar();
	// 方法2 ：从fp所指向的文件读取整个文件到buff中
	/*
			说明：循环读取fp指向的文件内容，如果读取NULL，就结束
	*/
	while(fgets(buff,1024,fp)!=NULL){
		printf("%s",buff);
	}
	// closed file
	fclose(fp);
	getchar();
}
```

























