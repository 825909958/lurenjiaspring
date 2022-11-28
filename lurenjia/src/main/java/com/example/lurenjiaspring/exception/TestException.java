package com.example.lurenjiaspring.exception;

/**
 * @author THT
 */
public class TestException {
    public static void main(String[] args) throws ThtException {
        RuleException.RULE_IS_INVALID.DATA_NOT_EXIST(false);
    }
}
