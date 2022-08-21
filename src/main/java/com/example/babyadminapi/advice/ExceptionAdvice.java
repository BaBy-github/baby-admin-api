package com.example.babyadminapi.advice;

import com.example.babyadminapi.exception.IncorrectException;
import com.example.babyadminapi.util.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:45
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        if(e instanceof IncorrectException){
            return R.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return R.error();
    }
}
