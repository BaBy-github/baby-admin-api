package com.example.babyadminapi.service;

import cn.dev33.satoken.stp.StpUtil;
import com.example.babyadminapi.entity.*;
import com.example.babyadminapi.exception.incorrect.UsernameOrPasswordIncorrectException;
import com.example.babyadminapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: BaBy
 * @Date: 2022/8/20 17:39
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private ActionRepo actionRepo;

    @Autowired
    private ModelRepo modelRepo;

    public void initUser(String username, String password){
        Model model = new Model();
        model.setCode("USER");
        Model savedModel = modelRepo.save(model);

        Action action1 = new Action();
        action1.setCode("INSERT");
        Action savedAction1 = actionRepo.save(action1);
        Action action2 = new Action();
        action2.setCode("DELETE");
        Action savedAction2 = actionRepo.save(action2);
        Action action3 = new Action();
        action3.setCode("UPDATE");
        Action savedAction3 = actionRepo.save(action3);
        Action action4 = new Action();
        action4.setCode("SELECT");
        Action savedAction4 = actionRepo.save(action4);

        Permission permission1 = new Permission(savedModel, savedAction1);
        Permission permission2 = new Permission(savedModel, savedAction2);
        Permission permission3 = new Permission(savedModel, savedAction3);
        Permission permission4 = new Permission(savedModel, savedAction4);
        Permission savedPermission1 = permissionRepo.save(permission1);
        Permission savedPermission2 = permissionRepo.save(permission2);
        Permission savedPermission3 = permissionRepo.save(permission3);
        Permission savedPermission4 = permissionRepo.save(permission4);
        Set<Permission> permissions = new HashSet<>();
        permissions.add(savedPermission1);
        permissions.add(savedPermission2);
        permissions.add(savedPermission3);
        permissions.add(savedPermission4);

        Role role = new Role();
        role.setRoleName("admin");
        role.setPermissions(permissions);
        Role savedRole = roleRepo.save(role);

        Set<Role> roles = new HashSet<>();
        roles.add(savedRole);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roles);
        userRepo.save(user);
    }
    public User login(String username, String password) {
        User loginUser = userRepo.findByUsernameAndPassword(username, password);
        if(Objects.isNull(loginUser)){
            throw new UsernameOrPasswordIncorrectException();
        }
        StpUtil.login(loginUser.getId());
        return loginUser;
    }
}
