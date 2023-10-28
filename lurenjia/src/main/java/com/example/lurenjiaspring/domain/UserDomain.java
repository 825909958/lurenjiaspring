package com.example.lurenjiaspring.domain;

import com.example.lurenjiaspring.dao.UserDao;
import com.example.lurenjiaspring.entity.UserDb;
import com.example.lurenjiaspring.exception.ThtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDomain implements IUserDomain {
    Logger logger = LoggerFactory.getLogger(UserDomain.class);

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private IUserDomain userDomain;

    @Autowired
    private ApplicationContext applicationContext;

    //    @Async
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public Map<String, Object> queryUserById(Long id) throws Exception {
        userDao.queryUserById(id);
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
//            Integer user = userDao.createUser(new UserDb("THT", "666"));
////            int a = 1 / 0;
//        });
        userDao.createUser(new UserDb("tht", "OutTransactional", "123456"));
        UserDomain userDomain = (UserDomain) applicationContext.getBean("userDomain");

        // 事务request_new  里面事务回滚，外面事务继续执行不回滚
        //try {
        //    userDomain.noTransactional(id);
        //} catch (Exception e) {
        //    System.out.println(e+"qaq");
        //}
        try {
            userDomain.noTransactional(id);
        } catch (Exception e) {
            logger.error("inTransactionalException");
        }
        if (false) {
            throw new ThtException("OutTransactional");
        }
//        voidCompletableFuture.join();
        return userDao.queryUserById(id);
    }


    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Map<String, Object> noTransactional(Long id) throws Exception {
        userDao.createUser(new UserDb("noTHT", "inTransactional"));
        Map<String, Object> stringStringMap = userDao.queryUserById(id);
        if (true) {
            throw new ThtException("inTransactionalException");
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

    /**
     * 通过AopContext获取代理类
     * @return StudentService代理类
     */
    private UserDomain getService(){
        return Objects.nonNull(AopContext.currentProxy()) ? (UserDomain) AopContext.currentProxy() : this;
    }

}
