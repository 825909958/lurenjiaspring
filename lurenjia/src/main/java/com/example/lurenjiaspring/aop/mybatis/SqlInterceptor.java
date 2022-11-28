package com.example.lurenjiaspring.aop.mybatis;

import org.apache.ibatis.plugin.Invocation;

/**
 * sql处理类接口，提供sql处理方法，并将处理后的sql返回
 *
 * @版权：SINOSERVICES 版权所有 (c) 2013
 * @author:admin
 * @version Revision 1.0.0
 * @email:admin
 * @see:
 * @创建日期：2018年1月18日
 * @功能说明：
 * @begin
 * @修改记录:
 * @修改后版本 修改人 修改内容
 * @2018年1月18日 admin 创建
 * @end
 */
public interface SqlInterceptor{
    /**
     * @param invocation
     * @param sql
     * @return
     * @author:admin
     * @email:admin
     * @创建日期:2018年1月10日
     * @功能说明： 拦截查询的接口方法，通过原始的sql，判断是否含有占位符。如果没有占位符，则直接返回。否
     * 则，根据数据权限查询得到数据权限的sql替换占位符后返回。
     */
    public String doInterceptor(Invocation invocation, String sql);
}
