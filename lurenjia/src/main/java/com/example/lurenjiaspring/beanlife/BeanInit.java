//package com.example.lurenjiaspring.beanlife;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author THT
// */
//@Component
//public class BeanInit implements InitializingBean,BeanPostProcessor {
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("bean = " + bean);
//        System.out.println("beanName = " + beanName);
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("bean = " + bean);
//        System.out.println("beanName = " + beanName);
//        return bean;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//    }
//}
