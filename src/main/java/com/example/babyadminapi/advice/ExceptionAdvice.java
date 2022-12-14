package com.example.babyadminapi.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.example.babyadminapi.exception.IncorrectException;
import com.example.babyadminapi.exception.NotFoundException;
import com.example.babyadminapi.util.R;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:45
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        if (e instanceof NotLoginException) {
            return R.error(R.CodeEnum.UNLOGIN, "Please login.");
        } else if (e instanceof NotRoleException || e instanceof NotPermissionException) {
            return R.error(R.CodeEnum.PERMISSION_DENIED, e.getMessage());
        }

        if (e instanceof IncorrectException) {
            return R.error(R.CodeEnum.BAD_REQUEST, e.getMessage());
        } else if (e instanceof NotFoundException) {
            return R.error(R.CodeEnum.NOT_FOUND, e.getMessage());
        }

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return R.error(R.CodeEnum.NOT_VALID, Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
        }

        if(e instanceof IllegalAccessException || e instanceof NoSuchFieldException){
            return R.error();
        }

        System.out.println(e.getMessage());
        return R.error();
    }
}
