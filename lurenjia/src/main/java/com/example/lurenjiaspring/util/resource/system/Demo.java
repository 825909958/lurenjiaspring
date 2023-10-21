package com.example.lurenjiaspring.util.resource.system;

public class Demo {
    public static void main(String[] args) {
//        String classpath = System.getProperty("java.class.path");
        String classpath = System.getProperty("user.home");
        String replace = classpath.replace(";", "\n");
        System.out.println("Classpath: " + replace);

    }
}
