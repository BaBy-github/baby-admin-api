package com.example.babyadminapi.controller.user;

import cn.dev33.satoken.stp.StpUtil;
import com.example.babyadminapi.service.UserService;
import com.example.babyadminapi.entity.User;
import com.example.babyadminapi.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private UserMapper userMapper;

    @PostMapping("/login")
    public R login(@RequestBody LoginRequest request) {
        User user = userService.login(request.getUsername(), request.getPassword());
        String token = StpUtil.getTokenInfo().getTokenValue();
        return R.ok()
                .put("token", token)
                .put("user", userMapper.toResponse(user));
    }
}