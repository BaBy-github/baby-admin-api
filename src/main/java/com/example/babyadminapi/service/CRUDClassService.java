package com.example.babyadminapi.service;

import com.example.babyadminapi.config.CRUDClassMapCache;
import com.example.babyadminapi.entity.CRUDClass;
import com.example.babyadminapi.repository.CRUDClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BaBy
 * @Date: 2022/9/3 20:29
 */
@Service
public class CRUDClassService {
    @Autowired
    private CRUDClassRepo crudClassRepo;

    @Autowired
    private CRUDClassMapCache crudClassMapCache;


    public List<String> loadCRUDClass2CRUDClassMapCache() {
        List<CRUDClass> crudClassList = crudClassRepo.findAll();
        List<String> classNotFoundList = new ArrayList<>();
        crudClassList.forEach(crudClass -> {
            try {
                crudClassMapCache.put(Class.forName(crudClass.getClassName()), crudClass);
            } catch (ClassNotFoundException ignored) {
                classNotFoundList.add(crudClass.getClassName());
            }
        });
        return classNotFoundList;
    }
}
