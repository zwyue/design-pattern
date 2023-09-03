package com.zwyue.enums;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/27 15:03
 *     email        1092478224@qq.com
 *     desc         权限类型
 * </pre>
 */
public enum PowerTypeEnum {

    /**
     *  权限类型
     *
     * @date 2018/12/27 15:09
     */
    MENU("1","菜单")

    ,BUTTON("2","按钮")

    ;

    private final String code ;

    private final String msg ;

    PowerTypeEnum(String code, String msg){
        this.code = code ;
        this.msg = msg ;
    }

    public PowerTypeEnum returnEnumByCode(String code,String msg){
        for (PowerTypeEnum value : values()) {
            if(value.code.equals(code)){
                return value ;
            }

            if(value.msg.equals(msg)){
                return value ;
            }
        }
        throw new IllegalArgumentException("there is no code [" + code + "] for enum");
    }
}
