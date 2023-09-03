package com.zwyue.filter;

import com.zwyue.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.zwyue.constant.SysConstant.*;

/**
 * AuthFilter class 全局用户过滤
 *
 * @author zwy
 * @date 2018/11/22 11:27
 */
public class AuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class) ;

    /**
     * 登陆过滤,主要过滤jsp，这里不处理权限，因为过滤器会拦截所有请求
     *
     * @author zwy
     * @date 2018/11/22 13:18
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.info("=======进入过滤器======");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute(CACHE_USER);
        Boolean isAdmin = (Boolean) session.getAttribute(CACHE_ADMIN);

        String uri = request.getRequestURI();

        // 不过滤的uri,因为此过滤器在web.xml中设置了只过滤jsp,因此，不过滤的jsp可在此设置
        //ArrayList更适合读取数据，linkedList更多的时候添加或删除数据
        List<String> notFilterList = new ArrayList<String>();
//        notFilterList.add("common/common_left.jsp");

        boolean ifFilter = notFilterList.stream().filter(nf -> uri.contains(nf)).count()==1 ;

        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

        if (ifFilter || isAdmin!=null || teacher!=null){
            filterChain.doFilter(request, response);
        }else {
            //跳转入登陆
            response.sendRedirect(basePath+LOGIN_URI);
        }
    }

    @Override
    public void destroy() {

    }
}
