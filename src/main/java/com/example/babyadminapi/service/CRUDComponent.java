package com.example.babyadminapi.service;

import cn.dev33.satoken.stp.StpUtil;
import com.example.babyadminapi.util.CRUDTokenCatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: BaBy
 * @Date: 2022/8/31 22:13
 */
@Component
public class CRUDComponent {
    public void update(Integer id, String field, Object value) throws NoSuchFieldException, IllegalAccessException {
        CRUDService service = (CRUDService) StpUtil.getTokenSession().get("updateService");
        service.update(id, field, value);
    }
}
