package com.zwyue.exception;

/**
 * 异常枚举类
 *
 * @author admin
 * 2018/11/28 14:07
 */
public enum ExceptionEnum {

     /**
      * Exception Enum
      */
    SUCCESS(0,"成功")

    ,NULL_EXCEPT(10001,"用户名或密码不能为空")

    ,NOT_EXIST(10002,"用户不存在")

    ,ACCOUNT_FORBID(10003,"账户被停用")

    ,NO_RIGHT(10004,"您当前无登陆权限")

    ,LOGIN_FAILED(10005,"登陆失败")

    ,TEACHER_EXIST(20001,"教师已存在")

    ,DELETE_FAILED(20002,"删除失败")

    ,UPDATE_FAILED(20003,"更新失败")

    ,ALREADY_SIGNED(30001,"该生已注册过其中%s门课程")
    ;

    public final Integer errorCode ;

    public final String errorMessage ;

    ExceptionEnum(Integer errorCode, String errorMessage){
        this.errorCode = errorCode ;
        this.errorMessage = errorMessage ;
    }
}
