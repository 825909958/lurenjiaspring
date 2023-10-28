package com.example.lurenjiaspring;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.controller.es.EsController;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author THT
 */
@SpringBootApplication
//@EnableAsync(proxyTargetClass=true)
@Import(value = EsController.class)
@EnableAspectJAutoProxy(exposeProxy = true)
//@ComponentScan(basePackages = "com.controller")
@MapperScan(basePackages = {"com.service", "com.example.lurenjiaspring.dao"})
public class LurenjiaspringApplication implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(LurenjiaspringApplication.class);

    @PostConstruct
    public void logIsPrint() {
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }

    public static void main(String[] args) {
        //日志还没加载
        //logger.info("info");
        //logger.error("error");
        SpringApplication.run(LurenjiaspringApplication.class, args);
        //logger.debug("debug");
        //logger.info("info");
        //logger.error("error");
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
    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(100000);

    public static Boolean addData(Integer data) {
        try {
            return queue.add(data);
        } catch (Exception e) {
            logger.error("add error {}", e);
            return false;
        }
    }

    public static Integer takeData() {
        Integer take = null;
        try {
            take = queue.take();
        } catch (InterruptedException e) {
            logger.error("take error {}", e);
        }
        return take;
    }

    public void readData() {
        ExecutorService customExecutor = Executors.newFixedThreadPool(10, new ThreadFactoryBuilder()
                .setNamePrefix("queue-custom-")
                .setThreadFactory(Executors.defaultThreadFactory())
                .setDaemon(true)
                .build()
        );
        CompletableFuture.runAsync(new Task(), customExecutor);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //readData();
    }
}
