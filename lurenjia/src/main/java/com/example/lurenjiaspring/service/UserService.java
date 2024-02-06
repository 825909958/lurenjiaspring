package com.example.lurenjiaspring.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lurenjiaspring.dao.UserDao;
import com.example.lurenjiaspring.entity.UserDO;
import com.example.lurenjiaspring.exception.ThtException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends ServiceImpl<UserDao, UserDO> implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private IUserDomain userDomain;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;

    @PostConstruct
    public void init() {
        sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    public void insertBatchUsers(List<UserDO> userList) {
        List<UserDO> batchList = new ArrayList<>();
        int batchSize = 100; // 设置批处理的大小

        for (int i = 0; i < userList.size(); i++) {
            batchList.add(userList.get(i));
            if (i > 0 && i % batchSize == 0) {
                this.saveBatch(batchList);
                batchList.clear();
            }
        }

        if (!batchList.isEmpty()) {
            this.saveBatch(batchList);
        }
    }

    @Override
    public void insertBatchUsers2(List<UserDO> userList) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            for (UserDO userDO : userList) {
                mapper.insert(userDO);
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    //    @Async
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Map<String, Object> queryUserById(Long id) throws Exception {
        userDao.queryUserInfoById(id);
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
//            Integer user = userDao.createUser(new UserDO("THT", "666"));
////            int a = 1 / 0;
//        });
        userDao.createUser(new UserDO("tht", "OutTransactional", "123456"));
        UserService userService = (UserService) applicationContext.getBean("userService");

        // 事务request_new  里面事务回滚，外面事务继续执行不回滚
        //try {
        //    userDomain.noTransactional(id);
        //} catch (Exception e) {
        //    System.out.println(e+"qaq");
        //}
        try {
            userService.noTransactional(id);
        } catch (Exception e) {
            logger.error("inTransactionalException");
        }
        if (false) {
            throw new ThtException("OutTransactional");
        }
//        voidCompletableFuture.join();
        return userDao.queryUserInfoById(id);
    }


    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Map<String, Object> noTransactional(Long id) throws Exception {
        userDao.createUser(new UserDO("noTHT", "inTransactional"));
        Map<String, Object> stringStringMap = userDao.queryUserInfoById(id);
        if (true) {
            throw new ThtException("inTransactionalException");
        }
        return stringStringMap;
    }

    public UserDO listTreeUser() {
        Map<Long, UserDO> collect = userDao.queryUserTree().stream()
                .collect(Collectors.toMap(UserDO::getId, obj -> obj, (pre, next) -> pre));
        for (Map.Entry<Long, UserDO> next : collect.entrySet()) {
            UserDO value = next.getValue();
            UserDO UserDO = Optional.ofNullable(collect.get(value.getParentId())).orElse(null);
            if (Objects.isNull(UserDO)) {
                continue;
            }
            UserDO.getChildren().add(value);
        }
        return collect.get(1L);
    }

    /**
     * 通过AopContext获取代理类
     *
     * @return StudentService代理类
     */
    private UserService getService() {
        return (UserService) AopContext.currentProxy();
    }

}
