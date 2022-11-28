package com.example.lurenjiaspring;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.example.lurenjiaspring.security.entity.LoginBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LurenjiaspringApplication.class)
public class RedisTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("1",
                JSON.toJSONString(new LoginBody("王我", "1111", "1111", "11111")));


        LoginBody loginBody = JSON.parseObject(redisTemplate.opsForValue().get("1").toString(), LoginBody.class);

         //redisTemplate.opsForHash().s
        System.out.println(loginBody);
    }

    @Test
    public void test2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("姓名", "谭宏涛");
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        hashOperations.putAll("qaq", map);
        //hashOperations.putIfAbsent();
    }

    @Test
    public void tes3() {
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<>("tht",95.0);
        ZSetOperations.TypedTuple<Object> typedTuple2 = new DefaultTypedTuple<>("yzk",91.0);
        ZSetOperations.TypedTuple<Object> typedTuple3 = new DefaultTypedTuple<>("yh",100.0);
        tuples.add(typedTuple1);
        tuples.add(typedTuple2);
        tuples.add(typedTuple3);
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();

        zSetOperations.add("三国杀", tuples);

        Set<Object> set = zSetOperations.reverseRange("三国杀", 0, 2);
        System.out.println("set = " + set);
    }
}
