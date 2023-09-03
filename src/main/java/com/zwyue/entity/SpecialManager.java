package com.zwyue.entity;

import java.io.Serializable;

/**
 * 特殊学员管理档案表
 * @author yangli
 * @date 2018/12/25
 */
public class SpecialManager implements Serializable {
    //主键id
    private Integer id;
    //学生id
    private Integer sid;
    //学生姓名
    private String sname;
    //学生性别（0：男、1：女）
    private String sex;
    //建档时间
    private String filtime;
    //建档原因（0:退班、1：转班、2：休学、3：开除）
    private String types;
    //主要情况
    private String content;
    //时间
    private String times;
    //地点
    private String address;
    //教师id
    private Integer tid;
    //教师姓名
    private String tname;
    //针对问题
    private String question;
    //辅导情况
    private String options;
    //班级id
    private Integer classid;
    //班级名称
    private String classname;
    //联系方式
    private String phone;
    //学号
    private String stunumber;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFiltime() {
        return filtime;
    }

    public void setFiltime(String filtime) {
        this.filtime = filtime;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        this.tname = tname;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
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
        this.classname = classname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber;
    }
}