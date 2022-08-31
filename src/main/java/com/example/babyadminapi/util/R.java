package com.example.babyadminapi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: BaBy
 * @date 2022/2/26 10:42
 * {
 *     "code": 200,
 *     "msg": "success",
 *     "data": {}
 * }
 */
public class R {

    public int code;
    public String msg;
    public Object data;
    public static final String DEFAULT_SUCCESS_MSG = "success";
    public static final String DEFAULT_ERROR_MSG = "Unknown exception occurred, please contact the administrator.";


    public enum CodeEnum {
        REQUEST_SUCCESS(20000),
        BAD_REQUEST(40000),
        UNLOGIN(40001),
        PERMISSION_DENIED(40002),
        NOT_VALID(40003),
        NOT_FOUND(40004),
        UNKNOW_ERROR(50000);

        final int code;

        CodeEnum(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public R() {
        code = CodeEnum.REQUEST_SUCCESS.getCode();
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

    public static R error(CodeEnum codeEnum, String msg) {
        R r = new R();
        r.code = codeEnum.code;
        r.msg = msg;
        return r;
    }

    public static R error() {
        return error(CodeEnum.UNKNOW_ERROR, DEFAULT_ERROR_MSG);
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