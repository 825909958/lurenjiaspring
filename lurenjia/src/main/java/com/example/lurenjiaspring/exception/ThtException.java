package com.example.lurenjiaspring.exception;

/**
 * @author THT
 */
public class ThtException extends Exception {
    String errorCode;

    public ThtException(String errorCode) {
        this.errorCode = errorCode;
    }
}
