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
 * @Date: 2022/8/16 22:35
 */
@Getter
@Setter
@Entity
@Table(name = "tb_role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 30)
    private String roleName;

    @ManyToMany(cascade = {CascadeType.REFRESH},
            targetEntity = Permission.class,
            fetch = FetchType.EAGER)
    @JoinTable(name = "m_role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(String roleName) {
        this.roleName = roleName;
    }
}