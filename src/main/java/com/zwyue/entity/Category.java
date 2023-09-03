package com.zwyue.entity;

import java.io.Serializable;

/**
 * 类别表
 * @author yangli
 * @date 2018/12/25
 */
public class Category implements Serializable {
    //主键id
    private Integer id;
    //类别编号
    private String number;
    //类别名称
    private String category;
    //备注
    private String remarks;
    //创建人id
    private Integer createid;
    //创建人姓名
    private String createname;
    //创建时间
    private String crttime;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname == null ? null : createname.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }
}