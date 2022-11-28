package com.example.lurenjiaspring.util.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:a.properties")
@ConfigurationProperties(prefix = "tht")
public class PropertiesUntil {
    String name;
    Integer age;

    public static void main(String[] args) {
        //new ClassPathXmlApplicationContext();
        new AnnotationConfigApplicationContext(PropertiesUntil.class);
    }
}
