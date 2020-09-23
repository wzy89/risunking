package com.wzy.risunking.resource.entity;

/**
 * ArticleInfo
 *
 * @author Wangzy
 * @date 2020/6/24 17:30
 */
public class ArticleInfo {

    private String id;
    private String title;
    private String content;
    private String path;
    private String url;
    private String desc;
    private String tagCodes;
    private String tags;
    private String readNum;
    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTagCodes() {
        return tagCodes;
    }

    public void setTagCodes(String tagCodes) {
        this.tagCodes = tagCodes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
