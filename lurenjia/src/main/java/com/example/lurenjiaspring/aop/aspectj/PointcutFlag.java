package com.example.lurenjiaspring.aop.aspectj;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PointcutFlag {
}
