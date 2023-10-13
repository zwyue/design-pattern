package com.zwyue.exception;

/**
 * 系统自定义异常
 *
 * @author admin
 * 2018/11/28 14:06
 */
public class TJOAException extends Exception {

    //异常信息
    private String message ;

    public TJOAException(String message){
        super(message);
        this.message = message ;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
