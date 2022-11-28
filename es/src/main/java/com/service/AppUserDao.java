package com.service;

import com.entity.AppUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author THT
 */
@Mapper
@Repository
public interface AppUserDao {
    public Map<String, String> queryUserById(Long id);
    Integer createUser(AppUser user);

    Integer updateUser(AppUser user);

    List<AppUser> appUserList(AppUser user);
}
