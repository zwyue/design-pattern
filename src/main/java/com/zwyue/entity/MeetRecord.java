package com.zwyue.entity;

import java.io.Serializable;

/**
 * 班会记录表
 * @author yangli
 * @date 2018/12/25
 */
public class MeetRecord implements Serializable {
    //主键id
    private Integer id;
    //周次（1-20周）
    private String weeksort;
    //教师id
    private Integer tid;
    //教师姓名
    private String tname;
    //班会主题
    private String content;
    //记录日期
    private String crttime;
    //班级id
    private Integer classid;
    //班级名称
    private String classname;
    //备注
    private String remark;
    //会议时间
    private String meettime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeeksort() {
        return weeksort;
    }

    public void setWeeksort(String weeksort) {
        this.weeksort = weeksort == null ? null : weeksort.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMeettime() {
        return meettime;
    }

    public void setMeettime(String meettime) {
        this.meettime = meettime;
    }
}