package com.wzy.risunking.resource.entity;

/**
 * ArticleInfo
 *
 * @author Wangzy
 * @date 2020/6/24 17:30
 */
public class GuestInfo {

    private String id;
    private String expand;
    private String content;
    private String nickName;
    private String email;
    private String createDate;
    private String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate.contains(".0")?createDate.substring(0,createDate.length()-2):createDate;
    }
}
