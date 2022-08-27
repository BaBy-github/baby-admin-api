package com.example.babyadminapi.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: BaBy
 * @date 2022/2/16 9:42
 */
@Data
public class  PageUtils<T> implements Serializable {
    private long totalCount;
    private int pageSize;
    private int totalPage;
    private int pageIndex;
    private List<T> list;

    public PageUtils(List<T> list, long totalCount, int pageIndex, int pageSize) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}
