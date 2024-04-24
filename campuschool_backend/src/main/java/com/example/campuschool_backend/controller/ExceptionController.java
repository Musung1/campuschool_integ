package com.example.campuschool_backend.controller;

import com.example.campuschool_backend.exception.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResult illegalExHandle(RuntimeException e) {
        return new ErrorResult("BAD", e.getMessage());
    }
}
