package com.example.babyadminapi.service.bo;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 23:11
 */
@Data
public class MenuLevel1 {
    @JsonIgnore
    private Integer id;
    private String name;
    private JSONObject meta;
    @JsonIgnore
    private Integer parentId;
    private List<MenuLevel2> children = new ArrayList<>();
}
