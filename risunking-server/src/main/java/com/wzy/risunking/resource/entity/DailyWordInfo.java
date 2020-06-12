package com.wzy.risunking.resource.entity;

/**
 * DailyWordInfo
 *
 * @author Wangzy
 * @date 2020/4/23 17:20
 */
public class DailyWordInfo {

    private int id;
    private int trdId;
    private String src;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrdId() {
        return trdId;
    }

    public void setTrdId(int trdId) {
        this.trdId = trdId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
