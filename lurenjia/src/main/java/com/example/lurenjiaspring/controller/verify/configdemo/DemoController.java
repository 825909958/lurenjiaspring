package com.example.lurenjiaspring.controller.verify.configdemo;

import com.example.lurenjiaspring.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author THT
 */
@RestController
public class DemoController {

    @Autowired
    private ApplicationContext context;
//
//    @Autowired
//    private org.apache.catalina.core.ApplicationContext tomcatContext;

    @Autowired
    WebApplicationContext context1;

    //@Resource
    //private RedisTemplate<String, Object> restTemplate;

    @Autowired
    private Map<String, RedisTemplate> redisTemplateMap;


    @PostConstruct
    public void a() {
        //System.out.println("context.getBean(\"redisTemplateLocal\") = " + context.getBean("redisTemplate"));
        //System.out.println("context.getBean(\"redisTemplateLocal\") = " + context1.getBean("redisTemplateLocal"));
        //输出多个redisTemplateMap
//redisTemplateMap.forEach((x,y)-> System.out.println("key+ "+x+"value +"+y.hashCode()));
//        System.out.println("restTemplate = " + redisTemplateMap);
        //System.out.println("redisTemplateLocal = " + redisTemplateLocal);
    }

    @RequestMapping("/a")
    public String A(){
        List<UserDO> list = new ArrayList<>();

        Object student = context.getBean("student");
        Object student1 = context1.getBean("student");
        System.out.println("student1 = " + student1);
        return student.toString()+student1.toString();
    }

}
