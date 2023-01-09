package com.example.lurenjiaspring.security.exception.user;

import com.example.lurenjiaspring.security.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @author tht
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
