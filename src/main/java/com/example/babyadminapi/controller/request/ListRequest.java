package com.example.babyadminapi.controller.request;

import lombok.Data;

/**
 * @Author: BaBy
 * @Date: 2022/8/24 12:25
 */
@Data
public class ListRequest {
    private Integer current;
    private Integer pageSize;
}
