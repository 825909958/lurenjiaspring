package com.example.lurenjiaspring.Controller.redis;

import com.example.lurenjiaspring.constants.Constants;
import com.example.lurenjiaspring.domain.redis.UserService;
import com.example.lurenjiaspring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author THT
 */
@RestController
public class RedisMysqlController {
    @Resource
    UserService userService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/redis/updateUser")
    public void updateData(String userName) {
        User user = new User();
        user.setUserName(userName);
        Integer integer = userService.updateUser(user);
        if (integer > 0) {
            redisTemplate.delete(Constants.userMark + "1008");
        }
    }

    @RequestMapping("/redis/getUser")
    public String getUserData() throws InterruptedException {
        Object user_name = redisTemplate.opsForHash()
                .get(Constants.userMark + "1008", "user_name");
        if (!ObjectUtils.isEmpty(user_name)) {
            return user_name.toString();
        }
        Map<String, String> map = userService.selcetUserById(Long.parseLong("1008"));
        Thread.sleep(10000);
        map.remove("user_id");
        redisTemplate.opsForHash().putAll(Constants.userMark + "1008", map);
        return (String) map.get("user_name");
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
