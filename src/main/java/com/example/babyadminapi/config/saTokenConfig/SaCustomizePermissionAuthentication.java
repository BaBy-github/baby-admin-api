package com.example.babyadminapi.config.saTokenConfig;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.example.babyadminapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        // 1. 声明权限码集合
        List<String> permissionList = new ArrayList<>();

        // 2. 遍历角色列表，查询拥有的权限码
        for (String roleId : getRoleList(loginId, loginType)) {
            SaSession roleSession = SaSessionCustomUtil.getSessionById("role-" + roleId);
            List<String> list = roleSession.get("Permission_List", () -> {
                return userService.getPermissionsByUserId(Integer.parseInt(loginId.toString()));     // 从数据库查询这个角色所拥有的权限列表
            });
            permissionList.addAll(list);
        }

        // 3. 返回权限码集合
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return session.get("Role_List", () -> {
            return userService.getRolesByUserId(Integer.parseInt(loginId.toString())); // 从数据库查询这个账号id拥有的角色列表
        });
    }
}
