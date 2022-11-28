package com.example.lurenjiaspring;

import com.controller.es.EsController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author THT
 */
@SpringBootApplication
//@EnableAsync(proxyTargetClass=true)
//@MapperScan(basePackages = {"com.service","com.example.lurenjiaspring.dao"})
@Import(value = EsController.class)
//@ComponentScan(basePackages = "com.controller")
@MapperScan(basePackages = {"com.service","com.example.lurenjiaspring.dao"})
public class LurenjiaspringApplication {

    public static void main(String[] args) {
        SpringApplication.run(LurenjiaspringApplication.class, args);
    }

}
