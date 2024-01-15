package com.example.lurenjiaspring;

import com.example.lurenjiaspring.util.spring.SpringUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LurenjiaspringApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LurenjiaspringApplicationTests {
    @Value("${name}")
    private String name;
    @Value("${spring.application.version}")
    private String version;
    @Value("${spring.application.name}")
    private String applicationName;

    @Test
    public void a() {
        System.out.println("name = " + name);
        System.out.println("version = " + version);
        System.out.println("applicationName = " + applicationName);
    }

    @Test
    public void noSpringEnvi() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(LurenjiaspringApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        //ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();
    }
@Test
    public void test() {
        List<String> test1 = SpringUtil.getBean("conditionDemoTest");
        System.out.println("conditionDemoBean = " + test1);
    }

}
