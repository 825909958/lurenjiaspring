//package com.example.lurenjiaspring.aop.mybatis;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyBatisSqlInterceptorConfiguration implements ApplicationContextAware, BeanPostProcessor, InstantiationAwareBeanPostProcessor {
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
//        sqlSessionFactory.getConfiguration().addInterceptor(new MybatisSqlInterceptor());
//    }
//}
//
//
