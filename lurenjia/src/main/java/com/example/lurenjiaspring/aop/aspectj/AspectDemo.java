package com.example.lurenjiaspring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectDemo {

    @Pointcut("@annotation(com.example.lurenjiaspring.aop.aspectj.PointcutFlag)")
    public void webPointcut() {
    }

    @Before("webPointcut()")
    public void beforeLog(JoinPoint joinPoint){
        System.out.println("advice");
    }
}
