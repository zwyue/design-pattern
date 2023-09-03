package com.zwyue.entity;

import java.io.Serializable;

/**
 * 教师表
 * @author yangli
 * @date 2018/12/25
 */
public class Teacher implements Serializable {
    //主键id
    private Integer id;
    //教师工号
    private String tnumber;
    //教师姓名
    private String tname;
    //密码
    private String password;
    //性别（0：男、1：女）
    private String sex;
    //年龄
    private Integer age;
    //联系方式
    private String phone;
    //身份证号
    private String sfzh;
    //出生日期
    private String birthdate;
    //民族
    private String nation;
    //身份证地址
    private String sfzaddress;
    //现地址
    private String address;
    //工作单位
    private String workunit;
    //班级id
    private String classid;
    //班级名称
    private String classname;
    //状态（0：启用、1：停用）
    private String status;
    //状态文字
    private String statusTxt ;
    //入职时间
    private String crttime;
    //角色id
    private String roleid;
    //离职时间
    private String leavedate;
    //专业id
    private String majorid;
    //专业名称
    private String majorname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTnumber() {
        return tnumber;
    }

    public void setTnumber(String tnumber) {
        this.tnumber = tnumber == null ? null : tnumber.trim();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit == null ? null : workunit.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname == null ? null : majorname.trim();
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSfzaddress() {
        return sfzaddress;
    }

    public void setSfzaddress(String sfzaddress) {
        this.sfzaddress = sfzaddress;
    }

    public String getStatusTxt() {
        return statusTxt;
    }

    public void setStatusTxt(String statusTxt) {
        this.statusTxt = statusTxt;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tnumber='" + tnumber + '\'' +
                ", tname='" + tname + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", sfzh='" + sfzh + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", nation='" + nation + '\'' +
                ", sfzaddress='" + sfzaddress + '\'' +
                ", address='" + address + '\'' +
                ", workunit='" + workunit + '\'' +
                ", classid='" + classid + '\'' +
                ", classname='" + classname + '\'' +
                ", status='" + status + '\'' +
                ", statusTxt='" + statusTxt + '\'' +
                ", crttime='" + crttime + '\'' +
                ", roleid='" + roleid + '\'' +
                ", leavedate='" + leavedate + '\'' +
                ", majorid=" + majorid +
                ", majorname='" + majorname + '\'' +
                '}';
    }
}