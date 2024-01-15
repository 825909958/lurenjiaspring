package com.example.lurenjiaspring.util.fuctiondemo;

import com.example.lurenjiaspring.entity.UserDO;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author THT
 */
public class OptionalDemo {
    public static void main(String[] args) {
        UserDO user = new UserDO("11", "1");
        String a = null;
        a = Optional.ofNullable(user).map(UserDO::getUserName).map(userId -> userId + "abcd").orElseGet(() -> "");
        System.out.println("a = " + a);
        ArrayList<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        System.out.println(list);
    }
}
