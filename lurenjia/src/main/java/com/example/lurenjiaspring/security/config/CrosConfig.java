package com.example.lurenjiaspring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//全局配置类--配置跨域请求
@Configuration
public class CrosConfig implements WebMvcConfigurer {

    static final String ORIGINS[] = new String[]{"GET", "POST", "PUT", "DELETE","OPTIONS"};

    //@Override
    //public void addCorsMappings(CorsRegistry registry) {
    //    registry.addMapping("/**") // 所有的当前站点的请求地址，都支持跨域访问。
    //            .allowedOriginPatterns("*") // 所有的外部域都可跨域访问。 如果是localhost则很难配置，因为在跨域请求的时候，外部域的解析可能是localhost、127.0.0.1、主机名
    //            .allowCredentials(true) // 是否支持跨域用户凭证
    //            .allowedMethods(ORIGINS) // 当前站点支持的跨域请求类型是什么
    //            .maxAge(3600); // 超时时长设置为1小时。 时间单位是秒。
    //}

    //@Override
    //public void addCorsMappings(CorsRegistry registry) {
    //    registry.addMapping("/**")  // 拦截所有的请求
    //            //.allowedOrigins("*")  // 可跨域的域名，可以为 *
    //            .allowedOriginPatterns("*")
    //            .allowCredentials(true)
    //            .allowedMethods("*")   // 允许跨域的方法，可以单独配置
    //            .allowedHeaders("*");  // 允许跨域的请求头，可以单独配置
    //}

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        //config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}


