package com.yrp.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yrp.entity.Author;
import com.yrp.entity.Book;

/**
 * @author: YeRenping
 * @Date: 2022/12/19
 * @Description: com.yrp.optional
 */
public class OptionalDemo02 {


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

}
