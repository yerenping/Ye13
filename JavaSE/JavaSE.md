## 1、Java的三大版本

**（1）、JDK、JRE、JVM**

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1guduyczozij60r80fk3zl02.jpg" alt="image-20210912150552190" style="zoom:50%;" />  

**（2）、三者关系**

![image-20210912150650845](https://tva1.sinaimg.cn/large/008i3skNgy1guduzaa4wdj61c80mc79502.jpg)

**==注：JDK包含了JRE以及JWM==**







## 2、环境搭建

> macOS中JDK下载及环境搭建：https://www.cnblogs.com/xixihuang/p/9726871.html




配置环境变量

**（1）新建一个.bash_profile的隐藏配置文件**，打开终端，在终端中输入“touch .bash_profile”命令：

$ touch .bash_profile

**（2）打开.bash_profile文件**，在终端中输入以下命令（如图 5）：

$ open -e .bash_profile

![图5](https://tva1.sinaimg.cn/large/008i3skNgy1gudv5jjg98j60zk06q0tw02.jpg)

（图 5）



**（3）打开.bash_profile文件中输入配置参数（区分大小写）**，新添加以下行（如图 6），并保存（按esc :wq或者command + S）退出：

```properties
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home
PATH=$JAVA_HOME/bin:$PATH:.
CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:.
export JAVA_HOME
export PATH
export CLASSPATH
```

==注：第一行中代表的是jdk的路径（step 3路径）==



![img](https://tva1.sinaimg.cn/large/008i3skNgy1gudv5k6d08j60zo09idi602.jpg)

（图 6）



（4）使配置文件生效，在终端中输入以下命令：

$ source .bash_profile

 

4. 检查刚刚设置的环境变量的路径，查看是否配置成功。

在终端中输入以下命令，如果能正确查看java的版本信息（如图 7），即表示已经配置成功。

$ echo $JAVA_HOME

![img](https://tva1.sinaimg.cn/large/008i3skNgy1gudv5kvxcpj61000c0juz02.jpg)

(图7)

**总结：**

Mac OS X 操作系统里面自带jdk，由于版本号过旧，可自己去 oracle的官网下载最新的对应的jdk版本进行替换。





## 3、Java的运行机制

1、编译型

2、解释型



## 4、开发工具IDEA

 参考：《IDEA操作手册》



## 5、Java基础知识



### **一、标识符**

（1）、关键字

![image-20210912155344061](https://tva1.sinaimg.cn/large/008i3skNgy1gudwc39rrmj60xg0oktas02.jpg)



（2）、注意

- 所有的标识符都是以字母（A-Z或者a-z），美元符（$）、或者下划线（_）开始_
- 首字符之后可以是字母（A-Z或者a-z），美元符（$）、或者下划线（_）或者数字的任意字母组合
- ==不能使用关键字作为变量名或方法名==
- 标识符是==大小写敏感==

![image-20210912155946758](https://tva1.sinaimg.cn/large/008i3skNgy1gudwid9reej60rw06e0t902.jpg) 



### 二、数据类型

**1、Java是强类型语言**，要求变量使用严格符合规定，所有的变量必须先定义后使用

**2、弱类型语言**

3、Java的数据类型分为两种类型

- 基本类型
- 引用类型

![image-20210912174946773](https://tva1.sinaimg.cn/large/008i3skNgy1gudzov9layj61cw0msmzf02.jpg)



```java
public class HelloWord {
    public static void main(String[] args) {

        /**
         * 八大数据类型
         */


        // 整数：整型
        byte num1 = 10; // 最常用
        int num2 = 20;
        long num3 = 30L; // long类型后面要加上L
        short num4 = 40;

        //小数：浮点型
        float num5 = 10.1F; // float必须在后面家F
        double num6 = 3.1415926;

        // 字符
        char name6 = '叶';

        // 字符串,String不是关键字，是一个类
        String name7 = "叶仁平"

        //布尔类型boolean
//        boolean flag = true;
        boolean flag = false;

    }
}

```



> 什么是字节呢？

![image-20210912180257764](https://tva1.sinaimg.cn/large/008i3skNgy1gue02jomqxj61nk0rw0xd02.jpg)

```java
public class Demo02 {
    public static void main(String[] args) {
        //整数拓展：     进制      二进制0b       十进制         八进制0         十六进制0x
        int i=10; // 10进制
        int i2=010;  // 8进制
        int i3 = 0x10;  // 十六进制

        System.out.println(i);
        System.out.println(i2);
        System.out.println(i3);

        /**
         * 银行业务怎么处理？怎么表示钱? 用flaot double？
         *flaot  有限 离散 误差大 大约 接近但不等于
         * double
         *
         */
        float qian1 = 0.1F;
        double qian2 = 1.0/10;
        System.out.println(qian1 == qian2);

        float d1 = 567898765678456789L;
        float d2 = d1+1;
        System.out.println(d1 == d2); // 竟然是true ，因为flaot能表示的字长是有限


    }
}
```





### 三、类型转换

```java
import com.sun.org.apache.xpath.internal.operations.String;

public class Demo03 {
    public static void main(String[] args) {
        int num=128;
        byte num2 = (byte) num;

        /**
         * 强制转换：（类型）变量名 高--->低
         * 自动转换：（类型）变量名 低--->高
         * int num=128;
         * double num2 = num;
         */

        // 注意：
        // 1、不能把布尔值进行转换
        // 2、转换的时候，可能存在内存溢出

        System.out.println(num);
        System.out.println(num2);

    }

}
```

### 四、常量

> final 之常量

```java

public class Demo04 {

    static final double PI = 3.14; //常量final

    public static void main(String[] args) {
        System.out.println(PI);
    }

}

```



### 五、变量的命名规范

![image-20210912185804499](https://tva1.sinaimg.cn/large/008i3skNgy1gue1nvurvij61ow0hqwhy02.jpg)



## 6、运算符

![image-20210912190010694](https://tva1.sinaimg.cn/large/008i3skNgy1gue1q2krg8j615q0oujtl02.jpg)



### **（1）、加减乘除**

```java
package operator;

public class Demo01 {
    public static void main(String[] args) {
        // 二元运算符
        int a = 10;
        int b = 20;
        int c = 25;
        int d = 25;
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b); //10/20= 0.5 舍去则为0

    }
}
```



```java
package operator;

public class Demo02 {
    public static void main(String[] args) {
        long a = 234567898765676789L;
        int b = 123;
        short c= 10;
        byte d = 8;

        System.out.println(a + b + c + d); // Long
        System.out.println(b + c + d); // int
        System.out.println( c + d); // int

    }
}
```



![image-20210912190730328](https://tva1.sinaimg.cn/large/008i3skNgy1gue1xpa0xvj614i05w0t702.jpg)



### **（2）、关系运算符**

```java
package operator;

public class Demo02 {
    public static void main(String[] args) {

        //关系应算符结果： flase or true 布尔值
        int b = 123;
        int a = 1;
        System.out.println(a > b);
        System.out.println(a<b);
        System.out.println(a==b);
        System.out.println(a!=b);
    }
}
```



### **（3）、自增、自减运算符**

```java
package operator;

public class Demo03 {
    public static void main(String[] args) {
        // ++ -- 自增 自减

        int a =3;
        int b = a++; // a=a+1
        int c= ++a; // 先a自增，再赋值
        System.out.println(a); //5
        System.out.println(b); // 3
        System.out.println(c); // 5
        System.out.println(c);// 5


        // 幂运算 2^3 2*2*2
        double s = Math.pow(2,3);
        System.out.println(s);

    }
}
```

### **（4）、&& ||  ! 与或非**

```java
package operator;

public class Demo04 {
    public static void main(String[] args) {
        // 逻辑运算符 : 与 或 非（取反）
        boolean a = false;
        boolean b = true;
        System.out.println("a && b:"+ (a && b ) ); // 逻辑与运算，两个都为真，才为真。 // false
        System.out.println("a || b:"+ (a || b ) ); // 逻辑或运算，两个变量有一个为真，结果就为真 //
        System.out.println("!(a && b):"+ !(a && b ) ); // 如果是真则为假，如果是假则为真。
        
    }
}
```

（5）、位运算

![image-20210912192923193](https://tva1.sinaimg.cn/large/008i3skNgy1gue2kgpx16j60gs0bwq3702.jpg) 



![image-20210912192940873](https://tva1.sinaimg.cn/large/008i3skNgy1gue2krnoe3j60jk0ja3z702.jpg) ``

```java
package operator;

public class Demo05 {
    public static void main(String[] args) {
        System.out.println(2<<3); // 16
    }
}
```



（4）、字符串拼接（面试细节题）

```java
package operator;

public class Demo05 {
    public static void main(String[] args) {
        int a =10;
        int b =20;
        System.out.println(""+a+b); //“”在前面会拼接
        System.out.println(a+b+""); //“”在后面会相加
    }
}
```

![image-20210912193445196](https://tva1.sinaimg.cn/large/008i3skNgy1gue2q1xvbhj60h606474d02.jpg) 

（5）、三目运算

```java
package operator;

/**
 * 三目运算
 */
public class Demo06 {
    public static void main(String[] args) {
        int score =80;
        String type = score<60?"不及格":"及格";
        System.out.println(type);
    }
}

```

![image-20210912193747947](https://tva1.sinaimg.cn/large/008i3skNgy1gue2t7momoj608w054jr902.jpg)  



### **（5）、优先级**

使用（）解决问题







## 7、用户交互Scanner



```java
public class Demo01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "使用next的方式接收");
        //判断用户有没有输入字符串
        if (scanner.hasNext()){
            String str = scanner.next();
            System.out.println("输入的内容为:"+str);
        }
        // 关闭IO流
        scanner.close();
    }
}
```

**==注意：==**

（1）、next 不能带有空格

（2）、nextLine可以带有空格，可以接收空白



## 8、Java方法

### **（1）、重载**

![image-20210914211517852](https://tva1.sinaimg.cn/large/008i3skNgy1guggvdf1tzj61n10u043j02.jpg) 





## 9、封装

> 快捷键：生成get set方法 `Commend +N` ，`commend+H`打开类的结构图





（1）、程序设计注意**==“高内聚，低耦合”==**

（2）、get/set

（3）、数据隐藏，属性私有，禁止直接访问一个对象中数据的实际表示，而应通过接口操作来访问，这是信息的隐藏！





## 10、重写

`生成@override快捷键 Commend +N`

定义：子类重写父类的方法！

1、方法名必须相同

2、参数列表必须相同

3、修饰符：范围可扩大：public>protected>default>private

4、抛出的异常：范围可以被缩小，但不能扩大：ClassNotFoundException --> Exception(大)



=：方法名保持一致

 >=：子类的修饰符可以>=父类的

<= <=：子类的返回值类型<=父类的返回值类型；子类声明异常类型不能超过父类异常类型









==为什么需要重写？==

1、父类的功能子类不一定需要，或者不一定满足



## 11、多态

> 1、多态是方法的多态
>
> 2、父类与子类存在继承关系，有联系。（ClassCastException）
>
> 3、存在条件：继承关系，方法需要重写，父类的引用指向子类！Father son = Son()

注意：

1、static 方法：属于类，他不属于实例

2、final 常量：是在常量池里

3、private 方法：私有方法，不能重写，即不存在多态！



**例子：**

```java
package com.yerenping.duotai;

public class Person {
    public void run(){
        System.out.println("run");
    }

}
```



```java
package com.yerenping.duotai;

public class Student extends Person{
    @Override
    public void run() {
        System.out.println("son");
    }


    public void eat(){
        System.out.println("吃");
    }
}
```



```java
package com.yerenping.duotai;

import com.yerenping.duotai.Student;
import com.yerenping.duotai.Person;



public class TestMain {
    public static void main(String[] args) {
        // 一个对象的实际类型是确定的 new Student();

        // 但是它可以指向的引用类型就不确定了Person、Object

        Student s1 = new Student();
        Person s2 = new Student();
        Object s3 = new Student();


        s2.run();// 子类重写了父类的方法，执行子类的方法
        s1.run();
        //s2.eat();
        // 总结：对象能执行那些方法，主要看对象的左边类型，和右边关系不大
    }
}
```

## 12、抽象类



abstract抽象类

1、不能new这个抽象类，只能靠子类去实现它：约束！

2、抽象类中可以写普通方法、成员变量、构造方法（但不能被使用），普通类中不能写抽象方法

3、包含抽象方法的类一定是抽象类



## 13、接口

![image-20210916132744886](https://tva1.sinaimg.cn/large/008i3skNgy1guielh2rhfj61bo0s245d02.jpg)



注意：

接口可以多继承，类只能单继承

2、类实现接口：implement







![image-20210916133601156](https://tva1.sinaimg.cn/large/008i3skNgy1guieu145qcj60ug0e6gnb02.jpg)





## 14、集合

> ctrl+shif+r 运行项目
>
> 结构图

![image-20210917191907391](https://tva1.sinaimg.cn/large/008i3skNgy1gujudguvjmj612k0hkabb02.jpg)

1、单例集合（Collection）

2、双利集合（Map）



> Collection 之ArrayList联系



```java
package com.yerenping.collection;

import java.util.ArrayList;
import java.util.List;
//List
public class CollectionDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("叶仁平");
        list.add(10);
        list.add(true);
//        System.out.println(list.contains("叶仁平"));
//        System.out.println(list.size());
//        System.out.println(list.isEmpty());
//        list.clear();
        List list2 = new ArrayList();
        list2.add("贺晶晶");
        list2.add(18);
        list.addAll(list2);

        list.removeAll(list2);
        System.out.println("list:"+list);
    }
}
```





> 迭代器的使用-Iterator

```java
package com.yerenping.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionDemo02 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Dog("小黄",18));
        list.add(new Dog("大黄",99));
        list.add(new Dog("大壮",22));

        // 输出显示
        for (Object dog : list) {
            System.out.println("dog="+dog);
        }
        System.out.println("--------------------");
        // 使用迭代器输出
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object dog =  iterator.next();
            System.out.println("dog"+dog);
        }

    }
}
class Dog{
    private String name;
    private int age;

    public Dog() {

    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```









## 13、常用类











