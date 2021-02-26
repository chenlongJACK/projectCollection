package com.cl.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description
 * @auther chenlong
 * @date 2021/2/2616:15
 */
@javax.servlet.annotation.WebFilter
public class WebFilter implements javax.servlet.Filter {

    final String[] resourcePath={""};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        System.out.println(uri);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
