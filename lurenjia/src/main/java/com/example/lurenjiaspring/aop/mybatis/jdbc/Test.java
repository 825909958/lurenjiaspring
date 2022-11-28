package com.example.lurenjiaspring.aop.mybatis.jdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void test0() {
        //1.创建数据源DataSource
        DataSource dataSource = DataSourceUtils.getDataSource();
        //2.创建JdbcTemplate，new JdbcTemplate(dataSource)
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //3.调用JdbcTemplate的方法操作db，如增删改查
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_user");
        System.out.println(maps);
    }
}
