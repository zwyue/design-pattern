package com.zwyue.entity;

import java.io.Serializable;

/**
 * 学期表
 * @author yangli
 * @date 2018/12/25
 */
public class Term implements Serializable {
    //主键id
    private Integer id;
    //学期名称
    private String term;
    //学期开始时间
    private String starttime;
    //学期结束时间
    private String endtime;
    //是否可用（0：可用、1：不可用）
    private String isenable;
    //可用状态文字描述
    private String isEnableTxt ;
    //创建时间
    private String crttime;
    //创建人id
    private Integer userid;
    //创建人姓名
    private String username;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
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

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable == null ? null : isenable.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIsEnableTxt() {
        return isEnableTxt;
    }

    public void setIsEnableTxt(String isEnableTxt) {
        this.isEnableTxt = isEnableTxt;
    }
}