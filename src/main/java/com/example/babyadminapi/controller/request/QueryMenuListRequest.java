package com.example.babyadminapi.controller.request;

import com.example.babyadminapi.entity.Menu;
import com.example.babyadminapi.util.jpa.OrderInfoMap;
import lombok.Getter;

/**
 * @Author: BaBy
 * @Date: 2022/8/24 12:25
 */
@Getter
public class QueryMenuListRequest extends Menu {
    Integer current;
    Integer pageSize;
    OrderInfoMap orderInfoMap;
}
