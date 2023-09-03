package com.zwyue.entity;

import java.io.Serializable;

/**
 * 班级工作计划表
 * @author yangli
 * @date 2018/12/25
 */
public class WorkPlan implements Serializable {
    //主键id
    private Integer id;
    //教师id
    private Integer tid;
    //教师名称
    private String tname;
    //标题
    private String title;
    //日志内容
    private String content;
    //记录时间
    private String recordtime;
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

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
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