package com.example.lurenjiaspring.util.reflectutil.classdemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    //public <T> void a(T t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    //    Class<?> aClass = t.getClass();
    //    Method a = aClass.getMethod("a", String.class);
    //    a.invoke(t, "111");
    //}

    public void ab(Object... t) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Object o : t) {
            Class<?> aClass = o.getClass();

            Method a = aClass.getMethod("a", String.class);
            a.invoke(o, "111");
        }

    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Demo demo = new Demo();
        TestDemo testDemo = new TestDemo();
        demo.ab(testDemo);
    }

    static class TestDemo {
        public void a(String www) {
            System.out.println("Test" + www);
        }

    }
}
