package com.example.babyadminapi.service;

import cn.hutool.json.JSONUtil;
import com.example.babyadminapi.entity.Menu;
import com.example.babyadminapi.repository.MenuRepo;
import com.example.babyadminapi.service.bo.MenuLevel1;
import com.example.babyadminapi.service.bo.MenuLevel2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 21:37
 */
@Service
public class MenuService {
    public static final int ENABLE_STATUS = 1;
    @Autowired
    private MenuRepo menuRepo;

    public List<MenuLevel1> getMenu() {
        List<MenuLevel1> result = new ArrayList<>();

        List<Menu> allMenu = menuRepo.findAllByStatus(ENABLE_STATUS);
        allMenu.stream()
                .filter(menu -> menu.getParentId() == 0)
                .forEach(menu -> {
                    MenuLevel1 menuLevel1 = new MenuLevel1();
                    BeanUtils.copyProperties(menu, menuLevel1);
                    menuLevel1.setMeta(JSONUtil.parseObj(menu.getMetaStr()));
                    result.add(menuLevel1);
                });

        List<Menu> list = allMenu.stream().filter(menu -> menu.getParentId() != 0).collect(Collectors.toList());
        list.forEach(menu -> {
                    MenuLevel2 menuLevel2 = new MenuLevel2();
                    BeanUtils.copyProperties(menu, menuLevel2);
                    menuLevel2.setMeta(JSONUtil.parseObj(menu.getMetaStr()));

                    result.stream()
                            .filter(menuLevel1 -> Objects.equals(menuLevel1.getId(), menu.getParentId()))
                            .findFirst()
                            .ifPresent(menuLevel1 -> menuLevel1.getChildren().add(menuLevel2));
                });
        return result;
    }
}
