package com.example.babyadminapi.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:52
 */
public class NotFoundException extends RuntimeException {
    public static final String NOT_FOUND = "not found";

    public NotFoundException(String entityName) {
        super(String.format("%s %s", entityName, NOT_FOUND));
    }

    public NotFoundException() {
        super(NOT_FOUND);
    }
}
