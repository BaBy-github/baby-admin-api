package com.example.babyadminapi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.babyadminapi.controller.user.LoginRequest;
import com.example.babyadminapi.controller.user.UserMapper;
import com.example.babyadminapi.service.MenuService;
import com.example.babyadminapi.service.UserService;
import com.example.babyadminapi.entity.User;
import com.example.babyadminapi.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:32
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public R login(@RequestBody LoginRequest request) {
        User user = userService.login(request.getUsername(), request.getPassword());
        String token = StpUtil.getTokenInfo().getTokenValue();
        return R.ok()
                .put("token", token);
    }

    @PostMapping("/logout")
    @SaCheckLogin
    public R logout() {
        StpUtil.logout();
        return R.ok();
    }

    @PostMapping("/menu")
    @SaCheckLogin
    public R getMenu() {
        return R.ok()
                .data(menuService.getMenu());
    }
}