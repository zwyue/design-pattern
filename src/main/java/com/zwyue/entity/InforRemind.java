package com.zwyue.entity;

import java.io.Serializable;

/**
 * 信息提醒表
 * @author yangli
 * @date 2018/12/25
 */
public class InforRemind implements Serializable {
    //主键id
    private Integer id;
    //消息分类（0：教师请假、1：上课提醒）
    private String type;
    //消息内容
    private String content;
    //创建人id
    private Integer createid;
    //创建人姓名
    private String createname;
    //提醒人id
    private Integer calltid;
    //提醒人姓名
    private String calltname;
    //状态（0：已处理、1：未处理）
    private String status;
    //来源信息id
    private Integer sourceid;
    //创建时间
    private String crttime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public Integer getCalltid() {
        return calltid;
    }

    public void setCalltid(Integer calltid) {
        this.calltid = calltid;
    }

    public String getCalltname() {
        return calltname;
    }

    public void setCalltname(String calltname) {
        this.calltname = calltname == null ? null : calltname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }
}