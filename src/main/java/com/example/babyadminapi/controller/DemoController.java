package com.example.babyadminapi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: BaBy
 * @Date: 2022/8/22 20:55
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/welcome/{username}")
    public String hello(@PathVariable String username) {
        return "Test-API Hello, world! Welcome: " + username;
    }

    @GetMapping("/is-login")
    public String isLogin() {
        return "is login?" + StpUtil.isLogin();
    }

    @GetMapping("/get-roles")
    @SaCheckLogin
    public String getRole() {
        return "role: " + StpUtil.getRoleList();
    }

    @GetMapping("/get-permissions")
    @SaCheckLogin
    public String getPermission() {
        return "permission: " + StpUtil.getPermissionList();
    }

    @GetMapping("/check-have-admin-role")
    @SaCheckLogin
    public String checkHaveAdminRole() {
        return "userId=" + StpUtil.getLoginId().toString() + " check have admin role: " + StpUtil.getRoleList().contains("admin");
    }

    @GetMapping("/check-have-user-select-permission")
    @SaCheckLogin
    public String checkHaveUSERSELECTPermission() {
        return "userId=" + StpUtil.getLoginId().toString() + " check have USER:SELECT permission: " + StpUtil.getPermissionList().contains("USER:SELECT");
    }

    @GetMapping("/annotation-check-have-user-insert-permission")
    @SaCheckPermission("USER:INSERT")
    public String annotationCheckHaveUserInsertPermission() {
        return "userId=" + StpUtil.getLoginIdAsInt() + " check have USER:INSERT permission: true";
    }

    @GetMapping("/annotation-check-have-admin-role")
    @SaCheckRole("admin")
    public String annotationCheckHaveAdminRole() {
        return "userId=" + StpUtil.getLoginIdAsInt() + " check have admin role: true";
    }
}
