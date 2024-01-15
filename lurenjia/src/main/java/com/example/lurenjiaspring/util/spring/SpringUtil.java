package com.example.lurenjiaspring.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author THT
 */
@Component
public class SpringUtil implements InitializingBean,ApplicationContextAware {
    public static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getSpringContext(Class<T> beanName) {
        return context.getBean(beanName);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException
    {
        return (T) context.getBean(name);
    }
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
