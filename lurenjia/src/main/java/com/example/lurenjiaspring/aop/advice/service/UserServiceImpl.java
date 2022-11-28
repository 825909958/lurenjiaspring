package com.example.lurenjiaspring.aop.advice.service;


public class UserServiceImpl implements UserService {

    @Override
    public void addUser(String name) {
        System.out.println("新增用户："+name);

    }

    @Override
    public String updateUser() {
        System.out.println("用户修改业务...");
        return "已修改";

    }

    @Override
    public String deleteUser() {
        System.out.println("用户删除业务...");
        return "已删除";
    }

    @Override
    public void queryUser() {
        System.out.println("用户查询业务...");
        System.out.println(0/20);//抛出异常
    }

}
