package com.example.lurenjiaspring.entity;

public class User {
    Long userId;
    String userName;
    String nickName;
    public User(){

    }

    public User(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
