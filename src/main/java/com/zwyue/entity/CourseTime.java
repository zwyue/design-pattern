package com.zwyue.entity;

import java.io.Serializable;

/**
 * 课程时间表
 * @author yangli
 * @date 2018/12/25
 */
public class CourseTime implements Serializable {
    //主键id
    private Integer id;
    //时间段
    private String time;
    //时间段代表int
    private String type;
    //0：上午、1：下午
    private String timetype;
    //创建时间
    private String crttime;

    private String status ;

    private String coursename ;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTimetype() {
        return timetype;
    }

    public void setTimetype(String timetype) {
        this.timetype = timetype == null ? null : timetype.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}