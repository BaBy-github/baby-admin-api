package com.example.babyadminapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: BaBy
 * @Date: 2022/8/16 22:33
 */
@Getter
@Setter
@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 30)
    private String password;

    @ManyToMany(cascade = {CascadeType.REFRESH},
            targetEntity = Role.class,
            fetch = FetchType.EAGER)
    @JoinTable(name = "m_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    public Set<Permission> getPermission() {
        Set<Permission> permissions = new HashSet<>();
        roles.forEach(role -> {
            permissions.addAll(role.getPermissions());
        });
        return permissions;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}