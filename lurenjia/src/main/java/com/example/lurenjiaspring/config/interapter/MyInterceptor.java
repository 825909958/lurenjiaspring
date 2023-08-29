package com.example.lurenjiaspring.config.interapter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在控制器方法执行之前进行拦截和处理
        // 可以对请求进行修改、验证、记录等操作
        return true; // 返回 true 表示继续执行后续的拦截器和控制器方法，返回 false 表示中断执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在控制器方法执行之后、视图渲染之前进行拦截和处理
        // 可以对响应进行修改、记录等操作
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在视图渲染之后进行拦截和处理
        // 可以进行资源清理等操作
    }
}
