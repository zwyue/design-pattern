package com.zwyue.entity;

import java.io.Serializable;

/**
 * 班级问题和建议清单表
 * @author yangli
 * @date 2018/12/25
 */
public class ClassSuggest implements Serializable {
    //主键id
    private Integer id;
    //序号
    private Integer sort;
    //教师id
    private Integer tid;
    //教师名称
    private String tname;
    //标题
    private String title;
    //问题和建议内容
    private String content;
    //建议时间
    private String suggesttime;
    //回复情况
    private String reply;
    //创建时间
    private String crttime;
    //班级id
    private Integer classid;
    //班级名称
    private String classname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public String getSuggesttime() {
        return suggesttime;
    }

    public void setSuggesttime(String suggesttime) {
        this.suggesttime = suggesttime;
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }
}