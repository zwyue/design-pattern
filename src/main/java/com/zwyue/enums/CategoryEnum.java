package com.zwyue.enums;

/**
 * CategoryEnum class
 *
 * @author zwy
 * @date 2018/11/28 16:47
 */
public enum  CategoryEnum {

    /**
     * 数字化建设
     *
     * @date 2018/11/28 16:57
     */
    DIGITAL_CONSTRUCT("0","数字化建设"),

    /**
     * 教材建设
     *
     * @date 2018/11/28 16:57
     */
    TEACH_MATERIAL_CONSTRUCT("1","教材建设")

    ;

    public final String code ;

    public final String desc ;

    CategoryEnum(String code,String desc){
        this.code = code ;
        this.desc = desc ;
    }

    public static CategoryEnum returnEnumByCode(String code){
        for (CategoryEnum categoryEnum: values()){
            if (categoryEnum.code.equals(code)){
                return categoryEnum ;
            }
        }
        throw new IllegalArgumentException("no matching code for [" + code + "]");
    }
}
