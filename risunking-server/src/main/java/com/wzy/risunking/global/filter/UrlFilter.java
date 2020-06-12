package com.wzy.risunking.global.filter;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * @description: url过滤器
 * @author: Wangzy
 * @create: 2018-10-23 17:56
 **/
@WebFilter(urlPatterns = "/")
@Order(value = 1)
@Configuration
public class UrlFilter implements Filter {
    org.slf4j.Logger logger = LoggerFactory.getLogger(UrlFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("UrlFilter:init filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if (judgeUrlPath(path)) {
            //不需要处理的url进入的方法
            filterChain.doFilter(request, response);
        } else {
            //需要处理的url进入的方法
            dealUrlPath(path);
        }
    }

    /**
     * 过滤urlPath方法
     * @param path
     * @return boolean
     * @Create Wangzy 2018/10/26 10:09
     * @Update Wangzy 2018/10/26 10:09
     */
    private boolean judgeUrlPath(String path){
        return true;
    }

    /**
     * 处理urlPath方法
     * @param path
     * @return java.lang.String
     * @Create Wangzy 2018/10/26 10:11
     * @Update Wangzy 2018/10/26 10:11
     */
    private void dealUrlPath(String path){
        logger.error("不合法得urlPath："+path);
    }

    @Override
    public void destroy() {
        logger.info("UrlFilter:destroy filter");
    }
}
