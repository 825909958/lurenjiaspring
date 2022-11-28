package com.example.lurenjiaspring;

import com.controller.es.EsController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author THT
 */
@SpringBootApplication
//@EnableAsync(proxyTargetClass=true)
@Import(value = EsController.class)
@EnableAspectJAutoProxy
//@ComponentScan(basePackages = "com.controller")
@MapperScan(basePackages = {"com.service","com.example.lurenjiaspring.dao"})

public class LurenjiaspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LurenjiaspringApplication.class, args);
    }

    //@Bean
    //public RestTemplate restTemplete() {
    //    RestTemplate restTemplate = new RestTemplate();
    //    HashMap<String, String> map = MapUtil.newHashMap();
    //    map.put("tht", "24");
    //    restTemplate.setDefaultUriVariables(map);
    //    return restTemplate;
    //}
    //
    //@Bean("aaaaaaaaaa")
    //public Student aaaaaaaaaa(RestTemplate restTemplate) {
    //    UriTemplateHandler uriTemplateHandler = restTemplate.getUriTemplateHandler();
    //    Object o = JSON.toJSON(uriTemplateHandler);
    //    System.out.println(o);
    //    return new Student();
    //}

}
