package com.example.lurenjiaspring.util.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.charset.StandardCharsets;

@Configuration
@PropertySource("classpath:a.properties")
@ConfigurationProperties(prefix = "tht")
@Data
public class PropertiesUtil {
    String name;
    Integer age;
    String url1;
    String url2;

    //@PostConstruct
    public void springPropertiesRead() {
        //spring属性批量读取
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        String s1 = ResourceUtil.getResourceObj(url1).readStr(StandardCharsets.UTF_8);
        System.out.println("ResourceUtil.getResourceObj(url1).readStr(StandardCharsets.UTF_8) = " + s1);
        String s2 = ResourceUtil.getResourceObj(url2).readStr(StandardCharsets.UTF_8);
        System.out.println("ResourceUtil.getResourceObj(url2).readStr(StandardCharsets.UTF_8) = " + s2);
        //System.out.println("url1 = " + url1);
        //System.out.println("url2 = " + url2);
    }

    public static void main(String[] args) {
        //new ClassPathXmlApplicationContext();
        //AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PropertiesUtil.class, ConfigLoader.class);
        //Object propertiesUtil = annotationConfigApplicationContext.getBean("propertiesUtil");
        //System.out.println(propertiesUtil);
        // 用自己的方式去获取配置文件键值信息
        String tht = ConfigLoader.getValue("tht.name");
        String url1 = ConfigLoader.getValue("url1");
        String url2 = ConfigLoader.getValue("url2");
        System.out.println("url1 = " + url1);
        System.out.println("url2 = " + url2);
    }
}
