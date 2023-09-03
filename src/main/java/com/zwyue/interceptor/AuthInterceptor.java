package com.zwyue.interceptor;

import com.zwyue.entity.Power;
import com.zwyue.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.zwyue.constant.SysConstant.*;

/**
 * copyright    <a href="http://www.qaqavr.com/>中锐</a>
 * <pre>
 *     @author  zwy
 *     @date    2018/12/1 14:08
 *     email    1092478224.com
 *     desc     权限拦截器
 * </pre>
 */
public class AuthInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 此处做controller层的权限拦截校验
     * 免拦截的uri可在spring-mvc.xml中配置,此处只拦截未登录和没有权限的uri
     *
     * @author zwy
     * @date 2018/12/1 14:41
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("=======进入拦截器======");
        HttpSession session = request.getSession();

        boolean isAdmin = session.getAttribute(CACHE_ADMIN)==null?false:(Boolean) session.getAttribute(CACHE_ADMIN);
        Teacher teacher = (Teacher) session.getAttribute(CACHE_USER);
        List<Power> authorities = (List<Power>) session.getAttribute(CACHE_PERMISSION);

        //请求的uri
        String uri = request.getRequestURI();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

        //管理员不限权限
        if(isAdmin){
            return true ;
        }

        //未登录
        if(teacher==null){
            //跳入登陆页面
//            request.getRequestDispatcher(LOGIN_URI).forward(request, response);
            response.getWriter().write("未登录，请重新登录后操作");
            return false ;
        }

        if(authorities!=null && authorities.stream().anyMatch(auth->auth.getRules().contains(uri))){
            return true ;
        }else {
            //无权限
            response.getWriter().write("您没有操作的权限");;
            return false ;
        }
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
