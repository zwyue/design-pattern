package com.zwyue.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 解决跨域问题
 */
@Component
public class SimpleCORSFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCORSFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
        ServletException {
        logger.info(".............执行过滤...................");
        HttpServletResponse response = (HttpServletResponse) res;
        String []  allowDomain= {"http://127.0.0.1:8081",
                "http://192.168.0.146",
                "http://127.0.0.1:8888",
                "http://127.0.0.1:8020",
                "http://192.168.0.151",
                "http://192.168.0.151:8020",
                "http://192.168.1.178",
                "http://127.0.0.1:8020",
                "http://192.168.1.165",
                "http://192.168.0.162:8083",
                "http://192.168.0.162",
                "http://192.168.0.166:8080",
                "http://192.168.0.166",
                "http://192.168.1.148",
                "http://192.168.0.167",
                "http://192.168.0.107",
                "http://192.168.0.107:8083",
                "http://localhost:8081"};
        Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(allowDomain));
        String originHeader=((HttpServletRequest) req).getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
//            response.setHeader("Access-Control-Allow-Origin", originHeader);
////            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
////            response.setHeader("Access-Control-Allow-Headers", "content-type, x-requested-with");
//            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setContentType("text/ml;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "0");
            response.setHeader("Access-Control-Allow-Headers","Origin, No-Cache, X-Requested-With, " +
                    "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, " +
                    "X-E4M-With,userId,token");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("XDomainRequestAllowed", "1");
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}

}