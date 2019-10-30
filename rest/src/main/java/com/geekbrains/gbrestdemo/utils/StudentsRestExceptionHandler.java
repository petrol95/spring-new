package com.geekbrains.gbrestdemo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentsRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<StudentsErrorResponse> handleAllException(StudentNotFoundException exc) {
        StudentsErrorResponse studentsErrorResponse = new StudentsErrorResponse();
        studentsErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentsErrorResponse.setMessage(exc.getMessage());
        studentsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(studentsErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
