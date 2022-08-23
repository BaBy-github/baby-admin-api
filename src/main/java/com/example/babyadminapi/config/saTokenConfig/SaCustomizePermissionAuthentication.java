package com.example.babyadminapi.config.saTokenConfig;

import cn.dev33.satoken.stp.StpInterface;
import com.example.babyadminapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class SaCustomizePermissionAuthentication implements StpInterface {

    @Autowired
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return userService.getPermissionsByUserId(Integer.parseInt(loginId.toString()));
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return userService.getRolesByUserId(Integer.parseInt(loginId.toString()));
    }
}
