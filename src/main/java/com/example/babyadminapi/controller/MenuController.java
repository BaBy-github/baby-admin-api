package com.example.babyadminapi.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.example.babyadminapi.controller.request.QueryMenuListRequest;
import com.example.babyadminapi.entity.Menu;
import com.example.babyadminapi.service.MenuService;
import com.example.babyadminapi.util.PageUtils;
import com.example.babyadminapi.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BaBy
 * @Date: 2022/8/22 20:55
 */
@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    @SaCheckRole("admin")
    public R queryMenuList(QueryMenuListRequest request) {
        PageUtils<Menu> pageUtils = menuService.queryMenuList(request.getCurrent(), request.getPageSize());
        return R.ok()
                .data(pageUtils);
    }
}
