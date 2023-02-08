## IDEA翻译插件

1、显示翻译对话框...

-  Control + Commend + I

2、对选中的文本进行翻译

-  Control + Commend + U



## idea快捷键

commond + p 弹出提示信息







## 1、lambda

匿名内部类 -> lambda

函数式编程关注的是具体的方法体和参数列表



> 案例1

```java
public static void main(String[] args) {
    // 常规写法
    new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("新线程中的run方法被执行了");
        }
    }).start();

    // lambda 表达式
    new Thread(()->{
        System.out.println("新线程中的run方法被执行了");
    }).start();
}
```

>  案例2

```java
public class LambdaDemo02 {
    public static void main(String[] args) {
        int i = calculateNum((left, right) -> {
            return left + right;
        });
        System.out.println(i);
    }

    public static int calculateNum(IntBinaryOperator op){
        int a =  10;
        int b =  20;
        return op.applyAsInt(20, 20);
    }
}
```

> 案例3

```java
public class LambdaDemo03 {
    public static void main(String[] args) {
        // lambda表达式
        printNum((int value) -> {
            return value % 2 == 0;
        });
    }
    public static void printNum(IntPredicate predicate) {
        int[] arr = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }
}
```



>  案例4

```java
public static void main(String[] args) {
    Integer res = typeConver(new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return Integer.parseInt(s);
        }
    });
    System.out.println(res);
   // lambda表达式
    Integer res = typeConver((String s)-> {
            return Integer.parseInt(s);
        });
        System.out.println(res);
}


public static <R>R typeConver(Function<String,R> function) {
    String s = "123";
    R result = function.apply(s);
    return result;
}
```



省略规则

1. 参数类型可以省略
2. 方法体只有一句代码的时候，大括号return和唯一一句代码的分号可以省略
3. 参数只有一个的时候，小括号也可以省略 



## 2、stream

- 需求

![image-20221213222822897](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221213222823.png)

- Sream流实现需求

```java
public static void main(String[] args) {
    List<Author> authorList = getAuthors();
    // 把集合转化成流，泛型是Author
    authorList.stream()
             // 取出重复
            .distinct()
             // 过滤掉年龄<=18的作家
            .filter(author -> author.getAge() <= 18)
             // 遍历输出 （forEach是终结方法）
            .forEach( author -> System.out.println(author.getName())
      );

}
```



### 2.1 Stream流常用操作

#### 2.1.1集合转stream流

- 单列集合：`集合对象.stream()`

```java
List<Author> authorList2 = getAuthors();
Stream<Author> stream = authorList2.stream();
```

- 数组

```java
Integer[] arr = {1,3,4,5,6};
// 方式1
Stream<Integer> stream1 = Arrays.stream(arr);
// 方式2
Stream<Integer> stream2 = Stream.of(arr);
```

- 多列集合map

```JAVA
// 3.1 先转化成set集合
Set<Map.Entry<String, Integer>> entries = map.entrySet();
// 3.2 将set集合转化成stream流 泛型：ap.Entry<String, Integer
Stream<Map.Entry<String, Integer>> stream3 = entries.stream();
```





#### 2.1.2 中间操作

##### 1. distinct （去重对象）

```java
private static void test05(){
    List<Author> authors = getAuthors();
    authors.stream().distinct()
            .forEach(author-> System.out.println(author.getName()));

}
```

##### 2. map（重构对象成分  + 计算）

```java
private static void test04(){
    List<Author> authors = getAuthors();
    authors.stream().map(author -> author.getAge())
            .map(age->age+10)
            .forEach(age-> System.out.println(age));

}
```



##### 3. sorted 

1、空参

1.1 必须让比较对象去实现Comparable接口，实现重写compareTo方法

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author implements Comparable<Author> {
    // id
    private Long id;
    //姓名
    private String name;

    // 年龄
    private Integer age;

    //简介
    private String intro;
    //作品
    private List<Book> books;

    @Override
    public int compareTo(Author author) {
        return this.getAge() - author.getAge();
    }
}
```



```java
private static void test06(){
    List<Author> authors = getAuthors();
    authors.stream().distinct()
            .sorted()
            .forEach(author-> System.out.println(author.getName() + ":" + author.getAge()));
}
```





- 有参

```java
private static void test06(){
    List<Author> authors = getAuthors();
    authors.stream().distinct()
            .sorted(new Comparator<Author>() {
                @Override
                public int compare(Author o1, Author o2) {
                    return o1.getAge() - o2.getAge();
                }
            })
            .forEach(author-> System.out.println(author.getName() + ":" + author.getAge()));
}
```

lambda

```java
private static void test06(){
    List<Author> authors = getAuthors();
    authors.stream().distinct()
            .sorted((o1, o2) -> o1.getAge() - o2.getAge()
            )
            .forEach(author-> System.out.println(author.getName() + ":" + author.getAge()));
}
```



##### 4. flatMap

> 例子1

```java
private static void test07(){
    List<Author> authors = getAuthors();
    authors.stream()
    .flatMap(author -> author.getBooks().stream())
    .distinct()
    .forEach(book -> System.out.println(book.getName()));

}
```

> 例子2

```java
/**
 * 打印现有书籍的全部分类。要求对分类进行去重。不能出现这种格式：言情、历史
 */
private static void test08(){
    List<Author> authors = getAuthors();
    authors.stream()
            .flatMap(author -> author.getBooks().stream())
            .distinct()
            .flatMap(book -> Arrays.stream(book.getCategory().split("、")))
            .distinct()
            .forEach(category -> System.out.println(category));
}
```

 

#### 2.1.3 终结操作

##### 1. forEach

> 遍历操作



##### 2.count

```java
 /**
     * 打印这些作家所处书籍的数目，注意去重
*/
private static void test09(){
    List<Author> authors = getAuthors();
    long count = authors.stream()
            .flatMap(author -> author.getBooks().stream())
            .distinct()
            .count();
    System.out.println(count);
}
```

##### 3.  max

##### 4.  min

```java
/**
 * 分别获取这些作家所处书籍的最高分 和 最低分
 */
private static void test10(){
    List<Author> authors = getAuthors();
    Optional<Integer> max = authors.stream()
            .flatMap(author -> author.getBooks().stream())
            .map(book -> book.getScore())
            .max(( score1,  score2)->score1 - score2);
    System.out.println(max.get());

    Optional<Integer> min = authors.stream()
            .flatMap(author -> author.getBooks().stream())
            .map(book -> book.getScore())
            .min((score1, score2) -> score1 - score2);
    System.out.println(min.get());
}
```



##### 5. collect
把当前的流转换成一个list集合
> 例子:获取一个存放所有作者名字的List集合
```java
/**
 * :获取一个存放所有作者名字的List集合
 */
private static void test11(){
    List<Author> authors = getAuthors();
    List<String> list = authors.stream()
            .map(author -> author.getName())
            .distinct()
            .collect(Collectors.toList());
    System.out.println(list);
}
```

> 例子:获取一个所有书名的set集合

```java
    /**
     * 获取一个所有书名的set集合
     */
    private static void test12(){
        List<Author> authors = getAuthors();
        Set<String> set = authors.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .map(author -> author.getName())
                .collect(Collectors.toSet());
        System.out.println(set);
    }

```



```java
/**
 * 获取一个map集合，map的key为作者名，value为 : List<Book>
 */
private static void test13(){
    List<Author> authors = getAuthors();
    Map<String, List<Book>> map = authors.stream()
            .distinct()
            .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
    System.out.println(map);
}
```

 

##### 6. anyMatch

> 只要有一个为符合条件，就返回true

```
/**
 * 判断是否有年龄在29岁以上的作家
 */
private static void test14(){
    List<Author> authors = getAuthors();
    boolean b = authors.stream()
            .distinct()
            .anyMatch(author -> author.getAge() > 29);
    System.out.println(b);

}
```

##### 7. allMatch

> 只有所有的都符合条件，才返回true

```java
/**
 * 判断是否所有的作家年龄都大于18岁
 */
private static void test14(){
    List<Author> authors = getAuthors();
    boolean b = authors.stream()
            .distinct()
            .anyMatch(author -> author.getAge() > 18);
    System.out.println(b);

}
```



##### 7. noMatch

> 所有的条件都不满足，才返回true

```
/**
 * 判断是否所有的作家年龄都不大于100岁
 */
private static void test15(){
    List<Author> authors = getAuthors();
    boolean b = authors.stream()
            .distinct()
            .noneMatch(author -> author.getAge() > 100);
    System.out.println(b);
}
```



## 3、Optional



### 1、ofNullable() || ifPresent()

```java
public static void main(String[] args) {
    Optional<Author> author = getAutor();
    author.ifPresent(author1 -> System.out.println(author1.getName()));
}

public static Optional<Author> getAutor(){
    Author author = new Author(1L,"贺晶晶",33,"牛逼的人",null);
    return Optional.ofNullable(author);
}
```





### 2、get()方法

```java
public static void main(String[] args) {
    Optional<Author> author = getAutor();
    Author author2 = author.get();
    System.out.println(author2.getName());
}

public static Optional<Author> getAutor(){
    Author author = new Author(1L,"贺晶晶",33,"牛逼的人",null);
    return Optional.ofNullable(null);
}
```



![image-20221219215421864](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221219215422.png)



### 3、orElseGet()

> orElse()
>
> 不论当前的authorOptional本身就是否有值，都返回我们传入的对象

>  如果authorOptional本身就是有值，则返回该值
>
> 如果authorOptional对象本身没有值，则返回我们设置的值[ new Author(1L,"叶仁平", 18, "牛逼",null) ]

```java
public static void main(String[] args) {
    Optional<Author> authorOptional = getAutor();
    Author author = authorOptional.orElseGet(() -> new Author(1L,"叶仁平", 18, "牛逼",null));
    System.out.println(author);
}

public static Optional<Author> getAutor(){
    Author author = new Author(2L,"贺晶晶",33,"牛逼的人",null);
    return Optional.ofNullable(null);
}
```



### 4、orElseThrow()

> 如果authorOptional本身就是有值，则返回该值
>
> 如果authorOptional对象本身没有值，则返回我们设置的值[ new RuntimeException("数据为null")   ]



```java
public static void main(String[] args) {
    Optional<Author> authorOptional = getAutor();
    try {
        Author author = authorOptional.orElseThrow( () ->new RuntimeException("数据为null")      );
    } catch (Throwable throwable) {
        throwable.printStackTrace();
    }
}

public static Optional<Author> getAutor(){
    Author author = new Author(1L,"贺晶晶",33,"牛逼的人",null);
    return Optional.ofNullable(null);
}
```

### 5、filter

> 过滤掉不符合要求的对象



```java
public static void main(String[] args) {
    Optional<Author> authorOptional = getAutor();
    Optional<Author> o2 = authorOptional.filter(author -> author.getAge() <= 18);
}

public static Optional<Author> getAutor(){
    Author author = new Author(1L,"贺晶晶",33,"牛逼的人",null);
    return Optional.ofNullable(author);
}
```



### 6、isPresent() 

> 是否存在

```java
public static void main(String[] args) {
    Optional<Author> authorOptional = getAutor();
    // 如果该对象存在，才进行打印用户名
    if (authorOptional.isPresent()) {
        System.out.println(authorOptional.get().getName());
    }
}

public static Optional<Author> getAutor(){
    Author author = new Author(1L,"贺晶晶",33,"牛逼的人",null);
    return Optional.ofNullable(author);
}
```

### 7、map

```java
public static void main(String[] args) {
    Optional<Author> authorOptional = getAutor();
    Optional<List<Book>> booksOptional = authorOptional.map(author -> author.getBooks());
    booksOptional.ifPresent(book -> System.out.println(book));
}

public static Optional<Author> getAutor(){
    Author author = new Author(1L,"贺晶晶",33,"牛逼的人",null);
    // 3 书籍
    List<Book> b1 = new ArrayList<>();

    b1.add(new Book(1L, "《明朝那些事》", "历史、小说", 99, "牛逼的历史小说"));
    b1.add(new Book(2L, "《乡村爱情》", "言情、小说", 99, "爱情记录"));
    author.setBooks(b1);
    return Optional.ofNullable(author);
}
```



## 4、常用的函数式接口

###  1、Consumer 消费型接口

> 根据其中抽象方法的参数列表和返回值可以知道，我们可以在方法中传入参数进行消费

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221220093244.png" alt="image-20221220093244526" style="zoom: 50%;" /> 



### 2、Function 计算机转换接口

> 根据其中抽象方法的参数列表和返回值可以知道，在方法传入参数计算或者转换，把结果返回

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221220093442.png" alt="image-20221220093442402" style="zoom:50%;" /> 





### 3、Predicate 判断接口

> 根据其中抽象方法的参数列表和返回值可以知道，我可以在方法中传入参数判断条件，返回判断结果

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221220093711.png" alt="image-20221220093711091" style="zoom:50%;" /> 



### 4、Supplier 生产型接口

> 根据其中抽象方法的参数列表和返回值可以知道，我可以在方法创建对象，把对象返回

<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221220093832.png" alt="image-20221220093831831" style="zoom:50%;" /> 



### 5、常见的默认方法

- and

- or

- negate

```
/**
 * 打印年龄不大于17的作家姓名
 */
private static void test16() {
    List<Author> authors = getAuthors();
    authors.stream()
            .filter(new Predicate<Author>() {
                @Override
                public boolean test(Author author) {
                    return author.getAge() > 17;
                }
            }.negate())
            .forEach(author -> System.out.println(author.getName()));

}
```



## 5、方法引用

类名/对象  :: 方法名

> ![image-20221221154710542](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221154710.png)

### （1）引用类的静态方法

> 类名 ::方法名

![image-20221221154825405](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221154825.png)

优化前：

```java
    Stream<Author> authorStream = getAuthors().stream();
    authorStream.map(author -> author.getAge())
            .map(new Function<Integer, String>() {
                @Override
                public String apply(Integer age) {
                    return String.valueOf(age);
                }
            });
}
```

优化后：👇🏻

```java
public static void main(String[] args) {
    Stream<Author> authorStream = getAuthors().stream();
    authorStream.map(author -> author.getAge())
            .map(String::valueOf);
}
```



### （2）引用对象的成员方法



对象名：成员方法名



**使用前提**

> ![image-20221221161357782](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221161358.png)

优化前：

```java
private static void test02() {
        List<Author> authors = getAuthors();
        StringBuilder sb = new StringBuilder();
        authors.stream()
                .map(author -> author.getName())
                // 成员方法
                .forEach(new Consumer<String>() {
                    @Override
                    public void accept(String str) {
                        sb.append(str);
                    }
                });
        System.out.println(sb);

    }
```



优化后👇🏻：

```java
    private static void test02() {
        List<Author> authors = getAuthors();
        StringBuilder sb = new StringBuilder();
        authors.stream()
                .map(author -> author.getName())
                // 成员方法 
                .forEach(sb::append);
        System.out.println(sb);
    }
```



### （3）引用类的实例方法

格式：`类名::方法名`

![image-20221221223111052](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221223111.png)

```java
public class MethodReferenceDemo2 {

    interface UseString{
        String use(String str, int start, int length);
    }

    public static String subAuthorName(String str, UseString useString){
        int start = 0;
        int length = 1;
        return useString.use(str, start, length);
    }

    public static void main(String[] args) {

        subAuthorName("b站：是叶十三", new UseString() {
            @Override
            public String use(String str, int start, int length) {
                return str.substring(start, length);
            }
        });
    }   
}
```

优化后👇🏻v

```java
public static void main(String[] args) {
    subAuthorName("b站：是叶十三", String::substring);
}
```

### （4）构造器引用

格式：`类名::new`



前提

![image-20221221223342285](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221223342.png)

例子

```java
private static void test03() {
    List<Author> authors = getAuthors();
    authors.stream()
            .map(author -> author.getName())
            .map(new Function<String, StringBuilder>() {
                @Override
                public StringBuilder apply(String name) {
                    return new StringBuilder(name);
                }
            })
            .map(sb -> sb.append("是叶十三").toString())
            .forEach(str -> System.out.println(str));
}
```

转化后👇🏻

```java
private static void test03() {
    List<Author> authors = getAuthors();
    authors.stream()
            .map(author -> author.getName())
            .map(StringBuilder::new)
            .map(sb -> sb.append("是叶十三").toString())
            .forEach(str -> System.out.println(str));
}
```



## 6、高级用法

### （1）Stream对基本数据类的优化

mapToInt

mapToLong

aptoDouble

flatMapToInt

flatMapToLong

flatMapToDouble



<img src="https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221225957.png" alt="image-20221221225957151" style="zoom:50%;" /> 

### （2）并行流

![image-20221221230104402](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221230104.png)

![image-20221221230500039](https://yerenping.oss-cn-beijing.aliyuncs.com/img/20221221230500.png)



方式一：authors.parallelStream()

方法二：

```
authors.stream()
        .parallel()
```

方法三：打印当前执行的线程（调试stream并行流）

```java
authors.stream()
        .parallel()
        .peek(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author.getName() + "->"+ Thread.currentThread().getName());
            }
        })
```



key  k v k v

