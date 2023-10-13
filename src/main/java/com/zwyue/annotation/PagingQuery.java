package com.zwyue.annotation;

import java.lang.annotation.*;

/**
 * 分页注解
 *
 * @author zwy
 * 2018/12/7 9:28
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PagingQuery {

    //页号参数名
    String pageNumParameterName() default "page" ;

    //每页行数参数名
    String pageSizeParameterName() default "size" ;
}
