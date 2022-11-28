package com.example.lurenjiaspring.domain.redis;

import com.example.lurenjiaspring.dao.UserDao;
import com.example.lurenjiaspring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public Integer updateUser(User user){
        return userDao.updateUser(user);
    }

    public Map<String, String> selcetUserById(Long id){
        return userDao.queryUserById(id);
    }


}
