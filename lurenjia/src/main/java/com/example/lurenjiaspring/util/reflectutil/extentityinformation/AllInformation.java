package com.example.lurenjiaspring.util.reflectutil.extentityinformation;

import lombok.ToString;

/**
 * @author THT
 */
@ToString
public class AllInformation {
    String name;
    String age;
    String height;
    String weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
