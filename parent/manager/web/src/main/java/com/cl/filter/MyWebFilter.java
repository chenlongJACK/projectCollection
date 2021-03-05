package com.cl.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description
 * @auther chenlong
 * @date 2021/2/2616:15
 */
@WebFilter("/*")

public class MyWebFilter implements Filter {

    final String[] resourcePath={"/manager"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        System.out.println(uri);
        for (String s : resourcePath) {
           if(uri.startsWith(s)){
               filterChain.doFilter(servletRequest,servletResponse);
               break;
           }
        }
    }

    @Override
    public void destroy() {

    }
}
