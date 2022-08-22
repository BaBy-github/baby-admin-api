package com.example.babyadminapi.repository;

import com.example.babyadminapi.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 21:34
 */
public interface MenuRepo extends JpaRepository<Menu, Integer> {
    List<Menu> findAllByStatus(Integer status);
}
