package com.example.babyadminapi.repository;

import com.example.babyadminapi.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 21:34
 */
public interface MenuRepo extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {
    List<Menu> findAllByStatus(Integer status);

    Page<Menu> findAll(Pageable pageable);

    @Transactional
    @Modifying
    int deleteAllByIdIn(List<Integer> id);
}
