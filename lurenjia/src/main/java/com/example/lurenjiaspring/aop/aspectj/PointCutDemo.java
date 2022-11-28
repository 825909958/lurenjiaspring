package com.example.lurenjiaspring.aop.aspectj;

import org.springframework.stereotype.Component;

@Component
public class PointCutDemo {
    @PointcutFlag
    public void a() {
        System.out.println("a");
        this.b();
    }

    @PointcutFlag
    public void b() {

        System.out.println("b");
    }

}
