package com.zwyue.enums;

/**
 * StatusEnum enum
 * 状态类的枚举
 *
 * @author zwy
 * 2018/11/30 14:01
 */
public enum StatusEnum {

    /**
     * 状态是否可用
      * 2018/11/30 14:09
     */
    IS_ENABLE("0","可用"),
    IS_DISABLED("1","不可用")
    ;

    public final String code ;

    public final String msg ;

    StatusEnum(String code,String msg){
        this.code = code ;
        this.msg = msg ;
    }

    public static StatusEnum returnEnumByCode(String code){
        for (StatusEnum statusEnum: values()) {
            if (statusEnum.code.equals(code)){
                return statusEnum ;
            }
        }
        throw new IllegalArgumentException("there is no code [" + code + "] for enum");
    }
}
