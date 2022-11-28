package com.example.lurenjiaspring.exception;


public interface BaseException {
    String ERROR_CODE = null;

    default String getErrorCode() {
        return ERROR_CODE;
    }

    default void DATA_NOT_EXIST(Boolean expression) throws ThtException {
        if (!expression) {
            throw new ThtException(this.getErrorCode());
        }
    }
}
