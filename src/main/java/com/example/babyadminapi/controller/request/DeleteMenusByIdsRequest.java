package com.example.babyadminapi.controller.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: BaBy
 * @Date: 2022/8/29 16:46
 */
@Data
public class DeleteMenusByIdsRequest {
    private List<Integer> ids;
}
