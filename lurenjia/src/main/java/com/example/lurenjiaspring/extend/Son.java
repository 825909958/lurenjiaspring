package com.example.lurenjiaspring.extend;

import java.util.function.Function;

public class Son extends Parent implements InterfaceDemo {
    private String name = "son";

    public Son() {
//        super.a();
    }


//    public static void main(String[] args) {
//         Parent.b();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String a() {

        return InterfaceDemo.super.c();
    }

    public <T extends Parent> void acceptSonFunction(T t, Function<T, Object> function) {
        System.out.println(function.apply(t).toString());
    }

//    public void acceptSonFunction2(Parent parent, Function<Parent, Object> function) {
//        System.out.println(function.apply(parent).toString());
//    }
//
//    private static Object apply(Parent x) {
//        return "";
//    }

    public void aaa() {
        Function<Son, Object> function = Son::getName;
        Son son = new Son();
        acceptSonFunction(son, function);

        Function<GrandSon, Object> getT = GrandSon::getT;
        GrandSon grandSon = new GrandSon();
        acceptSonFunction(grandSon, getT);
//        acceptSonFunction2(son, function);
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.aaa();
    }
}
