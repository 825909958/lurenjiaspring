package com.example.lurenjiaspring.aop.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

class BoundSqlSqlSource implements SqlSource {


    private BoundSql boundSql;


    public BoundSqlSqlSource(BoundSql boundSql) {
        this.boundSql = boundSql;
    }


    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return boundSql;
    }
}
