package com.zwyue.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 *     @author zwy
 *        2018/12/1 14:08
 *     email    1092478224.com
 *     desc     权限拦截器
 * </pre>
 */
public class AuthInterceptor implements HandlerInterceptor {
    /**
     * 缓存用户
     * 2018/11/23 13:17
     */
    public static final String CACHE_USER = "teacher" ;

    /**
     * 缓存管理员
     * 2018/12/1 12:16
     */
    public static final String CACHE_ADMIN = "is_admin" ;

    /**
     * 缓存权限
     * 2018/11/23 13:39
     */
    public static final String CACHE_PERMISSION = "permissions" ;

    private final static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 此处做controller层的权限拦截校验
     * 免拦截的uri可在spring-mvc.xml中配置,此处只拦截未登录和没有权限的uri
     * @author zwy
     * 2018/12/1 14:41
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("=======进入拦截器======");
        HttpSession session = request.getSession();

        boolean isAdmin =
            session.getAttribute(CACHE_ADMIN) != null &&
                (Boolean) session.getAttribute(CACHE_ADMIN);

        //请求的uri

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        //管理员不限权限
        return isAdmin ;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("||------AuthorizationInterceptor postCompletion --> ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("||------AuthorizationInterceptor afterCompletion --> ");
    }
}
