package com.example.lurenjiaspring.domain;

import com.example.lurenjiaspring.dao.UserDao;
import com.example.lurenjiaspring.entity.UserDb;
import com.example.lurenjiaspring.exception.ThtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDomain implements IUserDomain{
    @Autowired
    private UserDao userDao;

//    @Autowired
//    private IUserDomain userDomain;

    @Autowired
    private ApplicationContext applicationContext;

//    @Async
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> queryUserById(Long id) throws Exception {
        Map<String, String> map = userDao.queryUserById(id);
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
//            Integer user = userDao.createUser(new UserDb("THT", "666"));
////            int a = 1 / 0;
//        });
        Integer user = userDao.createUser(new UserDb("THT", "777"));
        UserDomain userDomain = (UserDomain)applicationContext.getBean("userDomain");
        userDomain.noTransactional(id);
        if (!CollectionUtils.isEmpty(map)) {
            throw new ThtException("11");
        }
//        voidCompletableFuture.join();
        return userDao.queryUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public Map<String, String> noTransactional(Long id) throws Exception {
        Integer user = userDao.createUser(new UserDb("noTHT", "no666"));
        Map<String, String> stringStringMap = userDao.queryUserById(id);
        if (!CollectionUtils.isEmpty(stringStringMap)) {
            throw new ThtException("11");
        }
        return stringStringMap;
    }

    public UserDb listTreeUser() {
        Map<Long, UserDb> collect = userDao.queryUserTree().stream()
                .collect(Collectors.toMap(UserDb::getId, obj -> obj, (pre, next) -> pre));
        for (Map.Entry<Long, UserDb> next : collect.entrySet()) {
            UserDb value = next.getValue();
            UserDb userDb = Optional.ofNullable(collect.get(value.getParentId())).orElse(null);
            if (Objects.isNull(userDb)) {
                continue;
            }
            userDb.getChildren().add(value);
        }
        return collect.get(1L);
    }
}
