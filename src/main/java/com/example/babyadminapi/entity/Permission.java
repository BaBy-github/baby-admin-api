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
 * @Date: 2022/8/16 23:03
 */
@Getter
@Setter
@Entity
@Table(name = "tb_permission")
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 30)
    private String code;

    @ManyToMany(targetEntity = Role.class, mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    private Model model;

    public Permission(String code) {
        this.code = code;
    }

    public Permission(Model model, Action action) {
        this.model = model;
        this.action = action;
        this.code = model.getCode()+":"+action.getCode();
    }
}
