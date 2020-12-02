package com.liq.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description JDBC数据源接口
 * author: liquan
 * date: 2020/11/20 09:58
 * version: 1.0
 */
public interface JdbcDataSource {

    /**
     * 获取连接
     * @return 返回数据库连接
     * @throws SQLException Sql异常
     */
    Connection getConnection() throws SQLException;

    /**
     * 释放连接
     * @param conn 链接
     * @param st SQL statement
     * @param rs 结果集
     */
    void release(Connection conn, Statement st, ResultSet rs);
}
