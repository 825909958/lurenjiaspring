package com.example.lurenjiaspring.util.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:a.properties")
@ConfigurationProperties(prefix = "tht")
@Data
public class PropertiesUtil {
    String name;
    Integer age;

    @PostConstruct
    public void springPropertiesRead() {
        //spring属性批量读取
        //System.out.println("name = " + name);
        //System.out.println("age = " + age);
    }

    public static void main(String[] args) {
        //new ClassPathXmlApplicationContext();
        //AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PropertiesUtil.class, ConfigLoader.class);
        //Object propertiesUtil = annotationConfigApplicationContext.getBean("propertiesUtil");
        //System.out.println(propertiesUtil);
        String tht = ConfigLoader.getValue("tht.name");
        System.out.println("tht = " + tht);
    }
}
