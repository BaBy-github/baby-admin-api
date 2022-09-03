package com.example.babyadminapi.repository;

import com.example.babyadminapi.entity.CRUDClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author: BaBy
 * @Date: 2022/9/3 20:38
 */
@Repository
public interface CRUDClassRepo extends JpaRepository<CRUDClass, Integer>, JpaSpecificationExecutor<CRUDClass> {
}
