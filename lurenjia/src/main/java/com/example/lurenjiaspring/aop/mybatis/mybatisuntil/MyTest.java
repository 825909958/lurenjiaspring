package com.example.lurenjiaspring.aop.mybatis.mybatisuntil;

import com.example.lurenjiaspring.dao.UserDao;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class MyTest {
    public static void main(String[] args) {
        // 获得SqlSession
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        Map<String, String> u = (Map<String, String>) sqlSession.selectOne("com.example.lurenjiaspring.dao.UserDao.queryUserById",
                2L);
        System.out.println(u);
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        Map<String, Object> stringStringMap = mapper.queryUserById(2L);
        // getMapper
//        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
//        List<User> userList = userDao.getUserList(1);

//        for (User user:userList) {
//            System.out.println(user);
//        }
        sqlSession.close();

    }
}
