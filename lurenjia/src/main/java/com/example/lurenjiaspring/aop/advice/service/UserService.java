package com.example.lurenjiaspring.aop.advice.service;


//用户服务接口
public interface UserService {
    //用户新增
    public void addUser(String name);

    //用户修改
    public String updateUser();

    //用户删除
    public String deleteUser();

    //用户查询
    public void queryUser();
}


