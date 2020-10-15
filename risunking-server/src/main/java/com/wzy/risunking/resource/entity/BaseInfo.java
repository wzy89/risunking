package com.wzy.risunking.resource.entity;

import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;

/**
 * BaseInfo
 *
 * @author Wangzy
 * @date 2020/9/28 19:33
 */
public class BaseInfo {
    public String id;
    public int page = 0;
    public int size = 0;
    public int from = 0;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void initFrom(){
        if (this.page <= 0 || this.size <= 0) {
            throw new CommandException(Response.PARAM_ERROR, "page或size参数错误");
        }
        int from = (page - 1) * size;
        setFrom(from);
    }
}
