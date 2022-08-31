package com.example.babyadminapi.controller.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: BaBy
 * @Date: 2022/8/31 21:34
 */
@Data
public class UpdateFieldRequest {
    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id不能小于1")
    private Integer id;

    @NotBlank(message = "field不能为空")
    private String field;

    @NotNull(message = "value不能为空")
    private Object value;

    @NotBlank(message = "修改时token不能为空")
    private String updateOperateToken;
}
