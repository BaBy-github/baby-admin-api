package com.example.babyadminapi.config;

import com.example.babyadminapi.entity.CRUDClass;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: BaBy
 * @Date: 2022/9/3 14:39
 */
@Component
public class CRUDClassMapCache extends HashMap<Class<?>, CRUDClass> {

}
