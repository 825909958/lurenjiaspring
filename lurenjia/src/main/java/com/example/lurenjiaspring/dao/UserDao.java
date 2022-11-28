package com.example.lurenjiaspring.dao;

import com.example.lurenjiaspring.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author THT
 */
@Mapper
public interface UserDao {
    public Map<String, String> queryUserById(Long id);
    Integer createUser(User user);

    Integer updateUser(User user);

}
