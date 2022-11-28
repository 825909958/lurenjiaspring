package com.example.lurenjiaspring.exception;


public enum RuleException implements BaseException {
    RULE_IS_INVALID("11111111111111");

    String ERROR_CODE;


    RuleException(String errorCode) {
        this.ERROR_CODE = errorCode;
    }
     @Override
    public String getErrorCode() {
        return ERROR_CODE;
    }
}
