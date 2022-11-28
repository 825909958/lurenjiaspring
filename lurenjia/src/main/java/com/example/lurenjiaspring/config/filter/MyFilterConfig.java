package com.example.lurenjiaspring.Controller.countpeople;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class MyFilterConfig {
    @Autowired
    FilterDemo myFilter;

    @Bean
    public FilterRegistrationBean<FilterDemo> setUpMyFilter() {

        FilterRegistrationBean<FilterDemo> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/*")));

        return filterRegistrationBean;
    }



}
