package com.gb.spring.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
//@Component("booksComponentID")
public class BooksComponent {

    //    @Autowired
    private BooksRepository booksRepository;

    @Value("testValue")
    private String testField;

    public BooksRepository getBooksRepository() {
        return booksRepository;
    }

    //    @Autowired
    public void setBooksRepository(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Autowired
    public BooksComponent(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void test() {
        System.out.println("BooksComponents getBook = " + booksRepository.getBookById(1));
    }
}
