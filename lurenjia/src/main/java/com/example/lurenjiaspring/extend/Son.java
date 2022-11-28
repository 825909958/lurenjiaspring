package com.example.lurenjiaspring.extend;

public class Son extends Parent implements InterfaceDemo{
    public Son(){
//        super.a();
    }

    public static void main(String[] args) {
         Parent.b();
    }

    @Override
    public String a() {

        return InterfaceDemo.super.c();
    }
}
