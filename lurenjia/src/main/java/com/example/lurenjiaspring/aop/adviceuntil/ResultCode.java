package com.example.lurenjiaspring.aop.adviceuntil;

public enum ResultCode {
    SUCCESS("000000", "成功"),
    USER_NOT_LOGIN("A0001","登录认证失败，请重新登录")
    ;
    String code;
    String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCode(String code, String message) {
        this.code = code;
        this.msg = message;
    }
}
