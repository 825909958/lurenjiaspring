package com.example.lurenjiaspring.domain;

import java.util.Map;

public interface IUserDomain {
    public Map<String, Object> noTransactional(Long id) throws Exception;
}
