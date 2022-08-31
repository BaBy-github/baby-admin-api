package com.example.babyadminapi.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.hutool.core.bean.BeanUtil;
import com.example.babyadminapi.controller.request.DeleteMenusByIdsRequest;
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

    @PostMapping
    @SaCheckRole("admin")
    public R queryMenuList(@RequestBody QueryMenuListRequest request) {
        Menu menuParams = new Menu();
        BeanUtil.copyProperties(request, menuParams);
        PageUtils<Menu> pageUtils = menuService.queryMenuList(request.getCurrent(), request.getPageSize(), menuParams, request.getOrderInfoMap());
        return R.ok()
                .data(pageUtils);
    }

    @PostMapping("deleteMenusByIds")
    @SaCheckRole("admin")
    public R deleteMenusByIds(@RequestBody DeleteMenusByIdsRequest request) {
        int row = menuService.deleteMenuByIds(request.getIds());
        return R.ok()
                .put("row", row);
    }

    @GetMapping("applyForUpdate")
    @SaCheckRole("admin")
    public R applyForUpdate() {
        return R.ok()
                .put("updateOperateToken", menuService.getTokenApplyForUpdate());
    }
}
