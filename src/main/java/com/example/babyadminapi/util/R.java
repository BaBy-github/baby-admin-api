package com.example.babyadminapi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: BaBy
 * @date 2022/2/26 10:42
 * @ClassName: R
 * {
 *     "code": 200,
 *     "msg": "success",
 *     "data": {}
 * }
 */
public class R {
    public static final int ARCO_DESIGN_RESPONSE_SUCCESS_CODE = 20000;
    public static final int ARCO_DESIGN_RESPONSE_ERROR_CODE = 50000;
    public static final String DEFAULT_ERROR_MSG = "未知异常，请联系管理员";
    public static final String DEFAULT_SUCCESS_MSG = "success";

    public int code;
    public String msg;
    public Object data;

    public R() {
        code = ARCO_DESIGN_RESPONSE_SUCCESS_CODE;
        msg = DEFAULT_SUCCESS_MSG;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        R r = new R();
        r.msg = msg;
        return r;
    }

    public static R ok(Map<String, Object> data) {
        R r = new R();
        r.data = data;
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static R error(String msg) {
        return error(ARCO_DESIGN_RESPONSE_ERROR_CODE, msg);
    }

    public static R error() {
        return error(ARCO_DESIGN_RESPONSE_ERROR_CODE, DEFAULT_ERROR_MSG);
    }

    /**
     * 禁止与data()配合使用
     */
    public R put(String key, Object value) {
        if (data == null) {
            data = new HashMap<String, Object>();
            ((Map<String, Object>) data).put(key, value);
        } else if(data instanceof Map){
            ((Map<String, Object>) data).put(key, value);
        } else {
            throw new RuntimeException("< data is not a map.");
        }
        return this;
    }

    /**
     * 将data中的数据直接存入data属性中
     */
    public R data(Object data) {
        this.data = data;
        return this;
    }
}