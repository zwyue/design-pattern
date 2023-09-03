package com.zwyue.entity;

import java.io.Serializable;

/**
 * 教学管理表
 * @author yangli
 * @date 2018/12/25
 */
public class TeachManager implements Serializable {
    //主键id
    private Integer id;
    //教师id
    private Integer tid;
    //教师姓名
    private String tname;
    //班级id
    private Integer classid;
    //班级名称
    private String classname;
    //分类（0：请假、1：停课、2：倒课）
    private String type;
    //开始时间
    private String starttime;
    //结束时间
    private String endtime;
    //日期
    private String leavedate;
    //补课开始时间
    private String bkkssj;
    //补课结束时间
    private String bkjssj;
    //补课教室id
    private Integer roomid;
    //补课教室名称
    private String roomname;
    //审核人id
    private Integer auditorid;
    //审核人姓名
    private String auditorname;
    //审核意见
    private String issure;
    //创建时间
    private String crttime;
    //周次
    private String weeks;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getBkkssj() {
        return bkkssj;
    }

    public void setBkkssj(String bkkssj) {
        this.bkkssj = bkkssj;
    }

    public String getBkjssj() {
        return bkjssj;
    }

    public void setBkjssj(String bkjssj) {
        this.bkjssj = bkjssj;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname == null ? null : roomname.trim();
    }

    public Integer getAuditorid() {
        return auditorid;
    }

    public void setAuditorid(Integer auditorid) {
        this.auditorid = auditorid;
    }

    public String getAuditorname() {
        return auditorname;
    }

    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname == null ? null : auditorname.trim();
    }

    public String getIssure() {
        return issure;
    }

    public void setIssure(String issure) {
        this.issure = issure == null ? null : issure.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }
}