package com.example.lurenjiaspring.util.reflectutil.classdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author THT
 */
public class Demo1<T> {
    List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static void a(List<? extends SubClass> parents) {
        System.out.println(parents);
    }

    public static void main(String[] args) {
        Demo1<Parent> sonDemo1 = new Demo1<>();
        sonDemo1.setData(Collections.emptyList());
        List<Parent> data = sonDemo1.getData();
        a(data);

        List<SubClass> subClasses = new ArrayList();
        List<Parent> parents = new ArrayList();

        subClasses.add(new Parent());
        subClasses.addAll(parents);
    }
}

class Parent extends SubClass {

}

class SubClass {
}
