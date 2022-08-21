package com.example.babyadminapi.controller.user;

import cn.hutool.core.bean.BeanUtil;
import com.example.babyadminapi.controller.user.UserResponse;
import com.example.babyadminapi.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 23:24
 */
@Component
public class UserMapper {
    public UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        return userResponse;
    }

    public List<UserResponse> toResponses(List<User> users){
        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
