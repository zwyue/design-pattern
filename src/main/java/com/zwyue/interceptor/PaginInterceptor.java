package com.zwyue.interceptor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author zwy
 *     @date 2018/12/7 11:31
 *     email       1092478224@qq.com
 *     desc
 * </pre>
 */
@Aspect
@Component
public class PaginInterceptor {
    @Around("execution(* com.zwyue.controller.*.*Paging(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if(args.length<2) {
            //规定原方法的参数最后两个是当前页和每页条数
            throw new Exception("参数不够分页");
        }
        PageHelper.startPage((Integer)args[0],(Integer)args[1]);
        List list = (List) point.proceed();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
