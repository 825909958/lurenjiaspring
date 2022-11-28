package com.example.lurenjiaspring.util.resource.curresource;


import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@MyScope //@1
public class User {

    private String username;

    public User() {
        System.out.println("---------创建User对象" + this); //@2
        this.username = UUID.randomUUID().toString(); //@3
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}