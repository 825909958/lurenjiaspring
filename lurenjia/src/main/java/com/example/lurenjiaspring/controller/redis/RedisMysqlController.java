package com.example.lurenjiaspring.controller.redis;

import com.alibaba.fastjson.JSONObject;
import com.example.lurenjiaspring.constants.Constants;
import com.example.lurenjiaspring.service.redis.TestRedisMysqlModifyService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author THT
 */
@RestController
public class RedisMysqlController {

    @Resource
    private TestRedisMysqlModifyService testRedisMysqlModifyService;

    // 注入两个会被覆盖，那个被那个覆盖不清楚，所以不要这么写
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/redis/updateUser/{userName}")
    public void testRedisMysqlModify(@PathVariable String userName) throws Exception {
        //Thread.sleep(100000);
        testRedisMysqlModifyService.updateUserAmount(userName,0);


    }

    @RequestMapping("/redis/getUser")
    public Object getUserData() throws InterruptedException {
        Map<Object, Object> entries = redisTemplate.opsForHash()
                .entries(Constants.userMark + "12");
        if (!ObjectUtils.isEmpty(entries)) {
            return JSONObject.toJSON(entries);
        }
        Map<String, Object> map = testRedisMysqlModifyService.selcetUserById(Long.parseLong("12"));
//        Thread.sleep(10000);
        // 序列化时long不能转换成string,java.lang.Integer cannot be cast to java.lang.String,所以不用默认的序列化工具
        map.remove("user_id");
        map.remove("blance");
        //map.remove("")
        redisTemplate.opsForHash().putAll(Constants.userMark + "12", map);
        return JSONObject.toJSON(map);
    }

    /**
     * redis 使用redis做消息队列
     * @param key
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/getRedisListPop")
    public String getRedisListPop(String key) throws InterruptedException {
        Object code = redisTemplate.opsForList().rightPop(key,0, TimeUnit.SECONDS);
        if (code==null){
            return "数据读取超时！";
        }
        return code.toString();
    }


    /**
     * 性能优化
     */
    public void bulkOperations() {
        SessionCallback<String> sessionCallback = new SessionCallback<String>() {
            //执行流水线
            @Override
            public String execute(RedisOperations operations) throws DataAccessException {
                //批量处理的内容
                for (int i = 0; i < 10000; i++) {
                    operations.opsForValue().set("redistest:" + "k" + i, "v" + i);
                }
                //注意这里一定要返回null，最终pipeline的执行结果，才会返回给最外层
                return null;
            }
        };
        List<Object> result = redisTemplate.executePipelined(sessionCallback);
    }
}
