package com.example.babyadminapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 20:07
 */
@Getter
@Setter
@Entity
@Table(name = "tb_menu")
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String metaStr;

    @Column(nullable = false)
    private Integer parentId;

    @Column(nullable = false)
    private Integer status;
}
