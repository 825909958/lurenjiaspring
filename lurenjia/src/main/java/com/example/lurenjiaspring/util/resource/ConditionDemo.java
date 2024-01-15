package com.example.lurenjiaspring.util.resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class ConditionDemo {
    @Bean("conditionDemoTest")
    @ConditionalOnProperty(prefix = "swich",name = "token",havingValue = "true")
    public List<String> conditionDemoTest() {
        List<String> list = new ArrayList<>();
        list.add("conditionDemo1");
        list.add("conditionDemo2");
        return list;
    }



}
