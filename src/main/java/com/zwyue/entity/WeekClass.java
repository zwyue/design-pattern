package com.zwyue.entity;


/**
 * 
 * <pre>
 *     @author      zwy
 *     @date        2018/12/10 15:50
 *     email        1092478224@qq.com
 *     desc         课程表组合
 * </pre>
 */
public class WeekClass {

    //教室
    private String classroom ;

    //时间段
    private String timeSlot ;

    //班级名称 + 授课老师名称
    private String monday ;

    private String tuesday ;

    private String wednesday ;

    private String thursday ;

    private String friday ;

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
