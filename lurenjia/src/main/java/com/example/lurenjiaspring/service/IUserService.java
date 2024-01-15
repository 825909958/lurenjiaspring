package com.example.lurenjiaspring.service;

import com.example.lurenjiaspring.entity.UserDO;

import java.util.List;
import java.util.Map;

public interface IUserService {
    public Map<String, Object> noTransactional(Long id) throws Exception;
    public void insertBatchUsers(List<UserDO> userList) ;
    public void insertBatchUsers2(List<UserDO> userList) ;
}
