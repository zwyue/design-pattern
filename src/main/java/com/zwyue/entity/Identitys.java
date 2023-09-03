package com.zwyue.entity;

import java.io.Serializable;

/**
 * 学生身份表
 * @author yangli
 * @date 2018/12/25
 */
public class Identitys implements Serializable {
    //主键id
    private Integer id;
    //职务
    private String duties;
    //创建时间
    private String crttime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }
}
