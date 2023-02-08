package com.yrp.methodReference;

import java.util.ArrayList;
import java.util.List;

import com.yrp.entity.Author;
import com.yrp.entity.Book;

/**
 * @author: YeRenping
 * @Date: 2022/12/21
 * @Description: com.yrp.methodReference
 */
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
        subAuthorName("b站：是叶十三", String::substring);
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
}
