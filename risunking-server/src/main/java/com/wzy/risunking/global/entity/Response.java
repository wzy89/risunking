package com.wzy.risunking.global.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 固定格式的返回值
 * @author: Wangzy
 * @create: 2018-10-23 10:29
 **/
public final class Response<T> {
    public static final int SUCCESS_RESULT = 0;
    public static final String SUCCESS_RESULT_MSG = "成功";

    /** 查询为空 */
    public static final int EMPTY_RESULT = 1;
    public static final String EMPTY_RESULT_MSG = "查询为空";

    /** 参数错误 */
    public static final int PARAM_ERROR = -1;

    /** 内部代码错误 */
    public static final int INNER_ERROR = -2;

    /** 方法名 -- 方法报错时返回， -- other中固定参数 */
    public static final String ERRORSTACKTRACE = "errorStackTrace";

    private int code = SUCCESS_RESULT;
    private String msg = SUCCESS_RESULT_MSG;
    private T result;
    private int count = 0;
    private Map<String, String> other = new HashMap<>();

    public Response() {
    }

    public Response(int code, String msg, T result, int count) {
        this.code = code;
        this.result = result;
        this.msg = msg;
        this.count = count;
    }

    public void setOther(String key, String value) {
        other.put(key, value);
    }

    public Map<String, String> getOther() {
        return this.other;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg=" + msg +
                ", result=" + result.toString() +
                ", other=" + other + "}";
    }
}
