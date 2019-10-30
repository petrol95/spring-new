package com.geekbrains.gbrestdemo.utils;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException (String message) {
        super(message);
    }
}
