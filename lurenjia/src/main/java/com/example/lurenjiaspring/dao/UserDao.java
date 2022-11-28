package com.example.lurenjiaspring.dao;

import com.example.lurenjiaspring.entity.UserDb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author THT
 */
@Mapper
public interface UserDao {
    public Map<String, Object> queryUserById(Long id);

    public UserDb queryUserByUserName(String userName);

    public List<UserDb> queryUserTree();

    Integer createUser(UserDb user);

    Integer updateUser(UserDb user);

}
