package com.example.lurenjiaspring.config.filter;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class MdcSetTidFilter implements Filter {
   private static Snowflake snowflake = IdUtil.createSnowflake(1L, 2L);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
//        System.out.println(chain + "11111");
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获取雪花算法ID
        long id = snowflake.nextId();
        MDC.put("tid", String.valueOf(id));
        chain.doFilter(request, response);
        //System.out.println("Response filtered by MyFilter");
        MDC.remove("tid");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
