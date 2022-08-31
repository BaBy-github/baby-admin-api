package com.example.babyadminapi.controller.user;

import com.example.babyadminapi.controller.request.UpdateFieldRequest;
import com.example.babyadminapi.service.CRUDComponent;
import com.example.babyadminapi.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: BaBy
 * @Date: 2022/8/31 21:28
 */
@RestController
@RequestMapping("/data")
public class CRUDController {
    @Autowired
    CRUDComponent crudComponent;

    @PostMapping("/update")
    public R update(@Valid @RequestBody UpdateFieldRequest request) throws NoSuchFieldException, IllegalAccessException {
        crudComponent.update(request.getId(), request.getField(), request.getValue(), request.getUpdateOperateToken());
        return R.ok();
    }
}
