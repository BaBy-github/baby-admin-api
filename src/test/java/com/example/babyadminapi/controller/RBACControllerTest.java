package com.example.babyadminapi.controller;

import com.example.babyadminapi.entity.*;
import com.example.babyadminapi.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RBACControllerTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private ModelRepo modelRepo;

    @Autowired
    private ActionRepo actionRepo;

    @BeforeEach
    void setUp(){
        userRepo.deleteAll();
        roleRepo.deleteAll();
        permissionRepo.deleteAll();
        modelRepo.deleteAll();
        actionRepo.deleteAll();
    }

    @Test
    void doSetUp(){
    }

    @Test
    void should_return_have_2_permissions_user_when_save_2_permissions_into_user_given_role_and_2_permissions(){
        // given
        Role role = new Role("admin");
        Permission permission1 = permissionRepo.save(new Permission("USER:INSERT"));
        Permission permission2 = permissionRepo.save(new Permission("USER:UPDATE"));
        role.setPermissions(new HashSet<>(){{
            add(permission1);
            add(permission2);
        }});
        Role savedRole = roleRepo.save(role);
        User user = new User("123", "123");
        // when
        user.setRoles(new HashSet<>(){{add(savedRole);}});
        User savedUser = userRepo.save(user);
        // then
        assertEquals(2, savedUser.getPermission().size());
    }

    @Test
    void should_return_have_2_permissions_user_when_add_1_permission_into_role_given_have_1_permission_role_and_1_model_and_2_actions(){
        // given
        Model model = new Model();
        model.setCode("USER");
        model.setPermissions(new HashSet<>());
        Model savedModel = modelRepo.save(model);

        Action action = new Action();
        action.setCode("INSERT");
        action.setPermissions(new HashSet<>());
        Action savedAction = actionRepo.save(action);

        Permission permission = new Permission(savedModel, savedAction);
        Permission savedPermission = permissionRepo.save(permission);

        Role role = new Role("admin");
        role.setPermissions(new HashSet<>(){{add(savedPermission);}});
        Role savedRole = roleRepo.save(role);

        User user = new User("123", "123");
        user.setRoles(new HashSet<>(){{add(savedRole);}});
        User savedUser = userRepo.save(user);
        System.out.println(savedUser);

        User findByIdUser = userRepo.findById(savedUser.getId()).orElseThrow();
        assertEquals(1, findByIdUser.getRoles().size());

        // 给User拥有的Role新增permission

        Action action2 = new Action();
        action2.setCode("UPDATE");
        action2.setPermissions(new HashSet<>());
        Action savedAction2 = actionRepo.save(action2);

        Permission permission2 = new Permission(savedModel, savedAction2);
        Permission savedPermission2 = permissionRepo.save(permission2);

        savedRole.getPermissions().add(savedPermission2);
        roleRepo.save(savedRole);

        findByIdUser = userRepo.findById(savedUser.getId()).orElseThrow();
        assertEquals(2, findByIdUser.getPermission().size());
    }
}