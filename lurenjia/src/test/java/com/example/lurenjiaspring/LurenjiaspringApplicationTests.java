package com.example.lurenjiaspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = LurenjiaspringApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LurenjiaspringApplicationTests {
    @Value("${name}")
    private String name;
    //@Value("${spring.name}")
    //private String sName;

    @Test
    public void a() {
        System.out.println("name = " + name);
        //System.out.println("sName = " + sName);
    }

    @Test
    public void noSpringEnvi() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(LurenjiaspringApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        //ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();
    }

}
