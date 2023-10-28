package com.example.lurenjiaspring.config.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
//        System.out.println(chain + "11111");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.put("tid", uuid);
        chain.doFilter(request, response);
        //System.out.println("Response filtered by MyFilter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
