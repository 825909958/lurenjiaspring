package com.example.lurenjiaspring.util.scan.imprtselect;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test1 {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AutoConfig.class);
        applicationContext.refresh();
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

    }
}
