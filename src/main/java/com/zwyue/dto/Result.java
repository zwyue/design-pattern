package com.zwyue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int code;
    private String msg;

    private T result;


    public enum ResCode{
        SUCCEED(200,"请求成功"),
        FIELD(40,"请求失败")
        ;

        @Getter
        private final int code ;

        @Getter
        private final String msg ;

        ResCode(int code,String msg){
            this.code = code ;
            this.msg = msg ;
        }
    }
}
