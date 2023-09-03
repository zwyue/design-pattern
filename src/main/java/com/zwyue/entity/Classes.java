package com.zwyue.entity;

import java.io.Serializable;

/**
 * 班级表
 * @author yangli
 * @date 2018/12/25
 */
public class Classes implements Serializable {
    //主键id
    private Integer id;
    //班级序号
    private String number;
    //班级名称
    private String classname;
    //班级计划人数
    private Integer plansize;
    //班级实际人数
    private Integer actualsize;
    //专业id
    private Integer majorid;
    //专业名称
    private String majorname;
    //班主任id
    private Integer headmaster;
    //班主任名称
    private String headmastername;
    //班长姓名
    private String monitor;
    //学习委员
    private String studyer;
    //安全委员
    private String safer;
    //授课教师id
    private Integer tid;
    //授课教师名称
    private String tname;
    //类别id
    private Integer cateid;
    //类别名称
    private String catename;
    //创建时间
    private String crttime;

    //任课教师联系方式
    private String phone ;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Integer getPlansize() {
        return plansize;
    }

    public void setPlansize(Integer plansize) {
        this.plansize = plansize;
    }

    public Integer getActualsize() {
        return actualsize;
    }

    public void setActualsize(Integer actualsize) {
        this.actualsize = actualsize;
    }

    public Integer getMajorid() {
        return majorid;
    }

    public void setMajorid(Integer majorid) {
        this.majorid = majorid;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname == null ? null : majorname.trim();
    }

    public Integer getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(Integer headmaster) {
        this.headmaster = headmaster;
    }

    public String getHeadmastername() {
        return headmastername;
    }

    public void setHeadmastername(String headmastername) {
        this.headmastername = headmastername == null ? null : headmastername.trim();
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor == null ? null : monitor.trim();
    }

    public String getStudyer() {
        return studyer;
    }

    public void setStudyer(String studyer) {
        this.studyer = studyer == null ? null : studyer.trim();
    }

    public String getSafer() {
        return safer;
    }

    public void setSafer(String safer) {
        this.safer = safer == null ? null : safer.trim();
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

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}