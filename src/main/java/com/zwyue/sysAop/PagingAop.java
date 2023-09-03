package com.zwyue.sysAop;

import com.github.pagehelper.PageHelper;
import com.zwyue.annotation.PagingQuery;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2018/12/7 9:32
 *     email        1092478224@qq.com
 *     desc         全局分页处理
 * </pre>
 */
@Aspect
@Component
public class PagingAop {

    private static final Logger logger = LoggerFactory.getLogger(PagingAop.class);

    @Around("@annotation(pagingQuery)")
    public Object pagingQuery(ProceedingJoinPoint joinPoint, PagingQuery pagingQuery) throws Throwable {

        logger.info(".............切面进入分页...............");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Class<?> returnType = signature.getMethod().getReturnType();
        //切点方法是否返回的是list
        if (returnType == List.class) {

            //是否有分页参数pageNum和pageSize
            String pageNumParameterName = pagingQuery.pageNumParameterName();
            String pageSizeParameterName = pagingQuery.pageSizeParameterName();
            //获取request，从中获取分页参数
            ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes();
            HttpServletRequest request = currentRequestAttributes.getRequest();
            String pageNum = request.getParameter(pageNumParameterName);
            String pageSize = request.getParameter(pageSizeParameterName);
            if (StringUtils.isNotBlank(pageNum) && StringUtils.isNotBlank(pageSize)) {
                try {
                    PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
                    Object result = joinPoint.proceed();
                    return (List<?>) result;
                } finally {//保证线程变量被清除
                    if (PageHelper.getLocalPage() != null){
                        PageHelper.clearPage();
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}
