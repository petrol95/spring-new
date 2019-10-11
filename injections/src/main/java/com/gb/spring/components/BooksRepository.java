package com.gb.spring.components;

import org.springframework.stereotype.Component;

@Component
public class BooksRepository {

    public String getBookById(int id) {
        return "Harry Potter Book #" + id;
    }

}
