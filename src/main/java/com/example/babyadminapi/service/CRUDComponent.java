package com.example.babyadminapi.service;

import com.example.babyadminapi.util.CRUDTokenCatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: BaBy
 * @Date: 2022/8/31 22:13
 */
@Component
public class CRUDComponent {
    @Autowired
    private CRUDTokenCatch crudTokenCatch;
    public void update(Integer id, String field, Object value, String updateOperateToken) throws NoSuchFieldException, IllegalAccessException {
        CRUDService service = crudTokenCatch.get(updateOperateToken);
        service.update(id, field, value, updateOperateToken);
    }
}
