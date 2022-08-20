package com.example.babyadminapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author: BaBy
 * @Date: 2022/8/16 23:03
 */
@Getter
@Setter
@Entity
@Table(name = "tb_action")
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 30)
    private String code;

    @OneToMany(mappedBy = "action")
    private Set<Permission> permissions;

    public Action(String code) {
        this.code = code;
    }
}
