package com.example.babyadminapi.exception.incorrect;

import com.example.babyadminapi.exception.IncorrectException;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 11:11
 */
public class UsernameOrPasswordIncorrectException extends IncorrectException {
    public UsernameOrPasswordIncorrectException() {
        super("Username or Password");
    }
}
