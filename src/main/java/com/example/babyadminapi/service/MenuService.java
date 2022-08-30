package com.example.babyadminapi.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.example.babyadminapi.entity.Menu;
import com.example.babyadminapi.repository.MenuRepo;
import com.example.babyadminapi.service.bo.MenuLevel1;
import com.example.babyadminapi.service.bo.MenuLevel2;
import com.example.babyadminapi.util.PageUtils;
import com.example.babyadminapi.util.jpa.OrderInfoMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 21:37
 */
@Service
public class MenuService {
    public static final int ENABLE_STATUS = 1;
    public static final String ACRO_DESIGN_SORT_KEY_ASCEND = "ascend";
    public static final String ACRO_DESIGN_SORT_KEY_DESCEND = "descend";
    @Autowired
    private MenuRepo menuRepo;

    @PersistenceContext
    private EntityManager entityManager;

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

    public PageUtils<Menu> queryMenuList(Integer current, Integer pageSize, Menu menuParams, OrderInfoMap orderInfoMap) {
        entityManager.clear();
        Specification<Menu> menuSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            // 动态带参查询
            if (menuParams.getId() != null) {
                list.add(criteriaBuilder.equal(root.get("id"), menuParams.getId()));
            }
            if (StrUtil.isNotBlank(menuParams.getName())) {
                list.add(criteriaBuilder.like(root.get("name"), "%" + menuParams.getName() + "%"));
            }
            if (menuParams.getParentId() != null) {
                list.add(criteriaBuilder.equal(root.get("parentId"), menuParams.getParentId()));
            }
            if (menuParams.getStatus() != null) {
                list.add(criteriaBuilder.equal(root.get("status"), menuParams.getStatus()));
            }

            // 排序
            List<Order> orderList = new ArrayList<>();
            orderInfoMap.forEach((key, value) -> {
                if (ACRO_DESIGN_SORT_KEY_ASCEND.equals(value)) {
                    orderList.add(criteriaBuilder.asc(root.get(key)));
                } else if(ACRO_DESIGN_SORT_KEY_DESCEND.equals(value)){
                    orderList.add(criteriaBuilder.desc(root.get(key)));
                }
            });
            return query.orderBy(orderList).where(list.toArray(new Predicate[0])).getRestriction();
        };

        List<Menu> menus = menuRepo.findAll(menuSpecification, PageRequest.of(current - 1, pageSize)).toList();
        long count = menuRepo.count(menuSpecification);
        return new PageUtils<>(menus, count, current, pageSize);
    }

    public int deleteMenuByIds(List<Integer> ids) {
        return menuRepo.deleteAllByIdIn(ids);
    }
}
