package com.example.babyadminapi.controller.user;

import lombok.Data;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:37
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
