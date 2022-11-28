package com.example.lurenjiaspring.config;

import com.example.lurenjiaspring.config.GuoJiHua.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

/**
 * @author THT
 */
@RestController
public class ConfigVerifyController {

    @Autowired
    private ApplicationContext context;
//
//    @Autowired
//    Verify verify;
//
//    @Autowired
//    private org.apache.catalina.core.ApplicationContext tomcatContext;

    @Autowired
    WebApplicationContext context1;

    @RequestMapping("/verifyConfig")
    public String A() {
        System.out.println(context.getBean("a"));
        return context.getBean("a").toString();
    }

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(ConfigurationVerify.class);
//        Object a1 = applicationContext.getBean("a");
//        Object a2 = applicationContext.getBean("a");
//        Object a3= applicationContext.getBean("a");
        AnnotationConfigApplicationContext guoJiHua =
                new AnnotationConfigApplicationContext(Demo.class);
        System.out.println(guoJiHua.getMessage("name", null, null));
        System.out.println(guoJiHua.getMessage("name", null, Locale.CHINA));
        //CHINA对应：zh_CN
        System.out.println(guoJiHua.getMessage("name", new String[]{"0", "1", "2"}, Locale.UK)); //UK对应

    }
}
