package com.wzy.risunking.global.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 固定格式的返回值
 * @author: Wangzy
 * @create: 2018-10-23 10:29
 **/
public final class Response {
    public static final int PARAM_ERROR = -1;
    public static final int NO_METHOD = -2;
    public static final int INNER_ERROR = -3;
    public static final int UNAUTHORIZED_ACCESS = -4;
    public static final int RESTRICTING_ACCESS = -5;
    public static final String EXCEPTIOMKEY = "exception";


    private String type;
    private int code = 0;
    private Object result = "";
    private Map<String, String> other = new HashMap<>();

    public Response() {
    }

    public Response(String type, Object result) {
        this.type = type;
        this.result = result;
    }

    public void setOther(String key, String value) {
        other.put(key, value);
    }

    public Map<String, String> getOther() {
        return this.other;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", code=" + code +
                ", result=" + result +
                ", other=" + other +
                '}';
    }
}
