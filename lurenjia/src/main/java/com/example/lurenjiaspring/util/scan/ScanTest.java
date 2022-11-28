package com.example.lurenjiaspring.util.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ScanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        //List<String> strings = Arrays.asList(annotationConfigApplicationContext.getBeanDefinitionNames());
        //List arrayList = new ArrayList<>(strings);
        //System.out.println("beanDefinitionNames = " + arrayList);

        //annotationConfigApplicationContext.scan("org.slf4j.impl");
        annotationConfigApplicationContext.register(config.class);
        annotationConfigApplicationContext.refresh();
        List<String> beanDefinitionNames = Arrays.stream(annotationConfigApplicationContext.getBeanDefinitionNames()).collect(Collectors.toList());
        System.out.println("beanDefinitionNames = " + beanDefinitionNames);
    }
}
