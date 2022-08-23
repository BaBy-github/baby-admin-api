package com.example.babyadminapi.exception.notFound;

import com.example.babyadminapi.exception.NotFoundException;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:57
 */
public class UserNotFoundException extends NotFoundException {

    public static final String ENTITY_NAME = "User";

    public UserNotFoundException() {
        super(ENTITY_NAME);
    }
}
