package com.example.babyadminapi.service.bo;

import cn.hutool.json.JSONObject;
import com.example.babyadminapi.entity.Menu;
import lombok.Data;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 23:11
 */
@Data
public class MenuLevel2 {
    private Integer id;
    private String path;
    private String name;
    private JSONObject meta;
    private Integer parentId;
}
