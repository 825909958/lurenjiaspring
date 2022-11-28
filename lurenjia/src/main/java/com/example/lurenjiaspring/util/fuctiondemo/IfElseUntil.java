package com.example.lurenjiaspring.util.fuctiondemo;

public class IfElseUntil {
    static FiveteenDay ifElse(Boolean b) {
        return (item1, item2) -> {
            if (b) {
                item1.nonaramNoReturn();
            } else {
                item2.nonaramNoReturn();
            }
        };
    }

    public static void main(String[] args) {
        // a()代表调用 并不是函数体
        ifElse(Boolean.TRUE).ifElseUntil(()-> System.out.println("one"), IfElseUntil::b);
    }

    static void a() {
        System.out.println("one");
    }

    static void b() {
        System.out.println("two");
    }
}
