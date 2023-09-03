package com.zwyue.entity;

import java.io.Serializable;

/**
 * 档案管理--校园活动表
 * @author yangli
 * @date 2018/12/25
 */
public class Activities implements Serializable {
    //主键id
    private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //创建时间
    private String crttime;
    //填写人id
    private Integer userid;
    //填写人邢敏
    private String username;
    //时间
    private String times;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}