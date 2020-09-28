package com.wzy.risunking.resource.entity;

/**
 * BaseInfo
 *
 * @author Wangzy
 * @date 2020/9/28 19:33
 */
public class BaseInfo {
    public String id;
    public String page;
    public String size;
    public int from = 0;
    public int to = 100;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
