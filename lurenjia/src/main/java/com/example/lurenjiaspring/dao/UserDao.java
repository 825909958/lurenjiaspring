package com.example.lurenjiaspring.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.lurenjiaspring.entity.UserDO;
import com.example.lurenjiaspring.entity.dto.MybatisManyParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author THT
 */
@Mapper
public interface UserDao extends BaseMapper<UserDO> {
    public Map<String, Object> queryUserInfoById(@Param("id") Long id);

    public UserDO queryUserByUserName(String userName);

    public List<UserDO> queryUserTree();

    Integer createUser(UserDO user);

    Integer updateUser(UserDO user);

    List<UserDO> queryDataByManyParam(@Param("req") MybatisManyParam req);

    void createTable(Map<String, Object> req);
}
