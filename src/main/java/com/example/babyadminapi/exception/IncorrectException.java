package com.example.babyadminapi.exception;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:52
 */
public class IncorrectException extends RuntimeException {
    public static final String INCORRECT = "incorrect";

    public IncorrectException(String input) {
        super(String.format("%s %s", input, INCORRECT));
    }

    public IncorrectException() {
        super(INCORRECT);
    }
}
