package com.example.lurenjiaspring.domain.redis;

import cn.hutool.core.collection.CollUtil;
import com.example.lurenjiaspring.constants.Constants;
import com.example.lurenjiaspring.dao.UserDao;
import com.example.lurenjiaspring.entity.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 并发修改问题，比如没有钱了还在减,如下已经解决
     */
    public Integer updateUser(String userName, int count) throws Exception {
        UserDb user = new UserDb();
        Map<String, Object> map = userDao.queryUserById(1008L);
        if (CollUtil.isEmpty(map)) {
            throw new Exception("数据不存在");
        }
        user.setUserName(userName);
        user.setVersion((Integer) map.get("version"));
        //Long aLong = Optional.ofNullable(((Long) map.get("blance")))
        //        .map(item -> item - 10L).orElse(-1L);
        //if (aLong.compareTo(0L) < 0) {
        //    throw new Exception("余额不足");
        //}
        Integer integer = userDao.updateUser(user);
        if (integer <= 0) {
            if (count > 3) {
                throw new Exception("修改失败");
            }
            Thread.sleep(100);
            System.out.println("count = " + count);
            updateUser(userName, ++count);
        }
        redisTemplate.delete(Constants.userMark + "1008");
        return integer;
    }

    public Map<String, Object> selcetUserById(Long id) {
        return userDao.queryUserById(id);
    }


}
