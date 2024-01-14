package com.example.lurenjiaspring.config.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class MyFilterConfig {
    @Autowired
    MdcSetTidFilter myFilter;

    @Bean
    public FilterRegistrationBean<MdcSetTidFilter> setUpMyFilter() {

        FilterRegistrationBean<MdcSetTidFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/*")));

        return filterRegistrationBean;
    }



}
