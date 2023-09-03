package com.zwyue.entity;

import java.io.Serializable;

/**
 * 专业表
 * @author yangli
 * @date 2018/12/25
 */
public class Profession implements Serializable {
    //主键id
    private Integer id;
    //专业序号
    private String majornumber;
    //专业名称
    private String majorname;
    //创建人id
    private Integer createid;
    //创建人姓名
    private String createname;
    //类别id
    private Integer cateid;
    //类别名称
    private String catename;
    //创建时间
    private String crttime;
    //类别序号
    private String numbers;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajornumber() {
        return majornumber;
    }

    public void setMajornumber(String majornumber) {
        this.majornumber = majornumber;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname == null ? null : majorname.trim();
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

}