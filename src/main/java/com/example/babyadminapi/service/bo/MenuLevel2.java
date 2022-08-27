package com.example.babyadminapi.service.bo;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @Author: BaBy
 * @Date: 2022/8/21 23:11
 */
@Data
public class MenuLevel2 {
    @JsonIgnore
    private Integer id;
    private String name;
    private JSONObject meta;
    @JsonIgnore
    private Integer parentId;
}
