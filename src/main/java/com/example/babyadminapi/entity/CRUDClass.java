package com.example.babyadminapi.entity;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: BaBy
 * @Date: 2022/9/3 18:00
 */
@Getter
@Setter
@Entity
@Table(name = "tb_crud_class")
@AllArgsConstructor
@NoArgsConstructor
public class CRUDClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String className;

    @Column(nullable = false)
    private String allowUpdateFieldNameJsonArray;

    /**
     * {
     *     "name": {
     *         "type": "String"
     *     },
     *     "age": {
     *         "type": "Number"
     *     },
     *     "role": {
     *         "type": "Object",
     *     },
     *     "permissions": {
     *         "type": "Set.Object",
     *     },
     *     "status1": {
     *         "type": "Enum.Integer",
     *         "enumClass": [0,1]
     *     },
     *     "status2": {
     *         "type": "Enum.String",
     *         "enumClass": ["on","off"]
     *     }
     * }
     */
    @Column(nullable = false)
    private String fieldTypeJsonObject;

    @Transient
    private List<String> allowUpdateFieldNameList;

    @Transient
    private HashMap<String, String> allowUpdateFieldNameTypeMap;

    public List<String> getAllowUpdateFieldNameList() {
        if (allowUpdateFieldNameList == null) {
            JSONArray jsonArray = JSONUtil.parseArray(allowUpdateFieldNameJsonArray);
            allowUpdateFieldNameList = jsonArray.toList(String.class);
        }
        return allowUpdateFieldNameList;
    }

}
