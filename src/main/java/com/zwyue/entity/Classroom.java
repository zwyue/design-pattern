package com.zwyue.entity;

import java.io.Serializable;

/**
 * 教室表
 * @author yangli
 * @date 2018/12/25
 */
public class Classroom implements Serializable {
    //主键id
    private Integer id;
    //教室名称
    private String classroom;
    //教室类别id
    private String cateid;
    //类别名称
    private String catename;
    //教室用途
    private String purpose;
    //教室地址
    private String address;
    //是否可用
    private String isuser;
    //创建时间
    private String crttime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid == null ? null : cateid.trim();
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename == null ? null : catename.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIsuser() {
        return isuser;
    }

    public void setIsuser(String isuser) {
        this.isuser = isuser == null ? null : isuser.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }
}