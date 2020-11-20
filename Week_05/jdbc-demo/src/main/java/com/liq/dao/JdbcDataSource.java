package com.liq.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/20 09:58
 * version: 1.0
 */
public interface JdbcDataSource {

    /**
     * 获取连接
     * @return
     */
    Connection getConnection() throws SQLException;

    /**
     * 释放连接
     */
    void release(Connection conn, Statement st, ResultSet rs);
}
