package com.example.lurenjiaspring;

import cn.hutool.core.collection.CollectionUtil;
import com.entity.App;
import com.example.lurenjiaspring.dao.UserDao;
import com.example.lurenjiaspring.entity.UserDO;
import com.example.lurenjiaspring.entity.dto.Column;
import com.example.lurenjiaspring.entity.dto.MybatisManyParam;
import com.example.lurenjiaspring.service.IUserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.LogManager;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LurenjiaspringApplication.class)
public class MybatisTest {
    Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Resource
    private IUserService userService;

    @Test
    public void testDDL() {
        InputStream is = App.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("tableName", "users");
            List<Column> columns = new ArrayList<>();
            columns.add(new Column("id", "INT(11)", "PRIMARY KEY","",true,"ID"));
            columns.add(new Column("name", "varchar(64)", "default NULL","",false,"名称"));
            columns.add(new Column("create_by", "varchar(64)", "default NULL","",false,"创建人"));
            columns.add(new Column("create_time", "datetime", "default NULL","",false,"创建时间"));
            columns.add(new Column("update_by", "varchar(64)", "default NULL","",false,"更新人"));
            columns.add(new Column("update_time", "datetime", "default NULL","",false,"更新时间"));
            columns.add(new Column("remark", "VARCHAR(500)", "default NULL","",false,"标记原因"));
            params.put("columns", columns);

            List<String> indexes = new ArrayList<>();
            indexes.add("INDEX idx_name (name)");
            indexes.add("INDEX idx_remark (remark)");
            params.put("indexes", indexes);

            session.update("com.example.lurenjiaspring.dao.UserDao.createTable", params);
            session.commit();
        }
    }
    @Test
    public void testBatchDealWith() {

        List<UserDO> userDOS =
                CollectionUtil.newArrayList(UserDO.builder().userName("1").nickName("1").createTime(new Date()).build(),
                UserDO.builder().userName("1").nickName("2").createTime(new Date()).build());

        userService.insertBatchUsers(userDOS);
        //userService.insertBatchUsers2(userDOS);
    }
    @Test
    public void testManyParam() {
        //test();
        InputStream is = App.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = factory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        List<UserDO> list = userDao.queryDataByManyParam(new MybatisManyParam("ry","18","用户1",2,"sys_user"));

        list.stream().filter(Objects::nonNull).forEach(i->logger.info(i.toString()));
    }

    @Test
    public void test() {
        // 指定自定义日志配置文件的路径
        String logConfigFile = "src/main/resources/logback-spring.xml";

        try {
            // 读取自定义日志配置文件
            FileInputStream configFile = new FileInputStream(logConfigFile);
            LogManager.getLogManager().readConfiguration(configFile);

            // 使用自定义配置的日志记录器
            logger.info("This is a custom log message");
        } catch (IOException e) {
            logger.error("Failed to load custom log configuration: " + e.getMessage());
        }
    }
}
