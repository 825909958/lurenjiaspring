package com.example.lurenjiaspring.extend;

import org.springframework.beans.factory.config.BeanPostProcessor;

public class Parent implements BeanPostProcessor {
    static {
        System.out.println("aa");
    }
    protected String a(){
        System.out.println("i an a parent");
        return "a";
    }

    static void b(){
        System.out.println("static b");
    }

}
