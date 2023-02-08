package com.yrp.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.yrp.entity.Author;
import com.yrp.entity.Book;

/**
 * @author: YeRenping
 * @Date: 2022/12/13
 * @Description: com.yrp.stream
 */
public class StreamDemoPrepare {

    public static void main(String[] args) {
        // test01(authorList);
        // test02(authorList);
        // test03();
//         test04();
//         test06();
//           test07();
//           test08();
//        test09();
//        test10();
//          test12();
//          test13();
//          test14();
//          test15();
        test16();
    }

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


    private static void test02(List<Author> authorList) {
        // 姓名常用大于1的作家
        authorList.stream()
                .filter((Author author) -> author.getName().length() >1)
                .forEach( author-> System.out.println(author.getName())
         );
    }

    private static void test01(List<Author> authorList) {
        // 把集合转化成流，泛型是Autor
        authorList. stream()
                .distinct()
                .filter(author -> author.getAge() <= 18)
                .forEach( author -> System.out.println(author.getName())
          );
    }


    private static List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        // 4 作家
        Author author = new Author(1L, "猛犸", 16, "冒险王，旅行者", null);
        Author author2 = new Author(2L, "余华", 15, "代表作《或者》", null);
        Author author3 = new Author(3L, "当年明月", 20, "公务员", null);
        Author author4 = new Author(4L, "yrp", 21, "现实主义作家", null);

        // 3 书籍
        List<Book> b1 = new ArrayList<>();
        List<Book> b2 = new ArrayList<>();
        List<Book> b3 = new ArrayList<>();

        b1.add(new Book(1L, "《明朝那些事》", "历史、小说", 99, "牛逼的历史小说"));
        b1.add(new Book(2L, "《乡村爱情》", "言情、小说", 99, "爱情记录"));

        b2.add(new Book(3L, "《明朝那些事2》", "历史、小说", 99, "牛逼的历史小说22"));
        b2.add(new Book(4L, "《清明图》", "言情、小说", 99, "牛逼的历史小说222"));

        b3.add(new Book(5L, "《特种兵在都市》", "历史、小说", 99, "牛逼的历史小说222"));
        b3.add(new Book(6L, "《特种兵在都市2》", "都市、小说", 99, "言情"));
        b3.add(new Book(7L, "《特种兵在都市3》", "都市、小说", 99, "言情"));

        author.setBooks(b1);
        author2.setBooks(b2);
        author3.setBooks(b3);
        author4.setBooks(b3);

        authors.add(author);
        authors.add(author2);
        authors.add(author3);
        authors.add(author3);
        return authors;
    }
    private static void test03(){
        List<Author> authors = getAuthors();
        authors.stream().map(new Function<Author, String>() {
            @Override
            public String apply(Author author) {
                return author.getName();
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }


    private static void test04(){
        List<Author> authors = getAuthors();
        authors.stream().map(author -> author.getAge())
                .map(age->age+10)
                .forEach(age-> System.out.println(age));
    }

    private static void test05(){
        List<Author> authors = getAuthors();
        authors.stream().distinct()
                .forEach(author-> System.out.println(author.getName()));

    }

    private static void test06(){
        List<Author> authors = getAuthors();
        authors.stream().distinct()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge()
                )
                .forEach(author-> System.out.println(author.getName() + ":" + author.getAge()));
    }
    private static void test07(){
        List<Author> authors = getAuthors();
        authors.stream()
        .flatMap(author -> author.getBooks().stream())
        .distinct()
        .forEach(book -> System.out.println(book.getName()));

    }

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



}
