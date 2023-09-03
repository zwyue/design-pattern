package com.zwyue.entity;

import java.io.Serializable;

/**
 * 学委会成员表
 * @author yangli
 * @date 2018/12/25
 */
public class StudyMeet implements Serializable {
    //主键id
    private Integer id;
    //学生id
    private Integer sid;
    //学生名称
    private String sname;
    //职务
    private String business;
    //加入时间
    private String crttime;
    //班级id
    private String classid;
    //班级名称
    private String classname;
    //联系方式
    private String phone;
    //家属联系方式
    private String emergency;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }
}