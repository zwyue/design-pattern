package com.zwyue.entity;

import java.io.Serializable;

/**
 * 教室类别表
 * @author yangli
 * @date 2018/12/25
 */
public class Classtype implements Serializable {
    //主键id
    private Integer id;
    //类别名称
    private String catename;
    //创建时间
    private String crttime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename == null ? null : catename.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }
}