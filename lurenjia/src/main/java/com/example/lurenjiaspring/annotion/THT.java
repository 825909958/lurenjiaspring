package com.example.lurenjiaspring.annotion;

import java.lang.annotation.*;

/**
 * @author THT
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface THT {
    String value() default "THT";
}
