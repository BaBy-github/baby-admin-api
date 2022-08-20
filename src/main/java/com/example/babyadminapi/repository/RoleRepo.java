package com.example.babyadminapi.repository;

import com.example.babyadminapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: BaBy
 * @Date: 2022/8/14 15:58
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
}
