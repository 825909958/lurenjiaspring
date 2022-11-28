package com.example.lurenjiaspring.entity;

import org.springframework.stereotype.Component;

/**
 * @author THT
 */
@Component
public class Student {
    public String name;
    public Integer age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
