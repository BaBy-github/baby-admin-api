package com.example.babyadminapi.service;

/**
 * @Author: BaBy
 * @Date: 2022/8/31 22:17
 */
public interface CRUDService {
    int update(Integer id, String field, Object value, String updateOperateToken) throws IllegalAccessException, NoSuchFieldException;

    String getTokenApplyForUpdate();
}
