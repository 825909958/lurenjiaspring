package com.example.lurenjiaspring.util.fuctiondemo;

import com.example.lurenjiaspring.entity.UserDb;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author THT
 */
public class OptionalDemo {
    public static void main(String[] args) {
        UserDb user = new UserDb("11", "1");
        String a = null;
        a = Optional.ofNullable(user).map(UserDb::getUserName).map(userId -> userId + "abcd").orElseGet(() -> "");
        System.out.println("a = " + a);
        ArrayList<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        System.out.println(list);
    }
}
