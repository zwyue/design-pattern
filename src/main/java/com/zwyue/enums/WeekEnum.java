package com.zwyue.enums;

/**
 * 
 * <pre>
 *     @author      zwy
 *     @date        2018/12/10 18:01
 *     email        1092478224@qq.com
 *     desc
 * </pre>
 */
public enum  WeekEnum {

    /**
     * 星期枚举
     * @date 2018/12/10 18:05
     */
    MONDAY("周一","1"),

    TUESDAY("周二","2"),

    WEDNESDAY("周三","3"),

    THURSDAY("周四","4"),

    FRIDAY("周五","5"),

    SATURDAY("周六","6")

    ;

    public final String day ;

    public final String code ;

    WeekEnum(String day,String code){
        this.day = day ;
        this.code = code ;
    }

    public static WeekEnum returnDayByCode(String code){
        for (WeekEnum we: values()) {
            if(we.code.equals(code)){
                return we ;
            }
        }
        return null ;
    }

}
