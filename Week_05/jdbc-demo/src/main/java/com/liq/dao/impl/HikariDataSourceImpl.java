package com.liq.dao.impl;

import com.liq.dao.JdbcDataSource;
import com.liq.dao.JdbcProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/20 10:02
 * version: 1.0
 */
public class HikariDataSourceImpl implements JdbcDataSource {

    /**
     * 创建DataSource是一个非常昂贵的操作，所以通常DataSource实例总是作为一个全局变量存储，并贯穿整个应用程序的生命周期。
     */
    static DataSource ds = null;

    static {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(JdbcProperties.profiles.get("jdbc.driver_class"));
        config.setJdbcUrl(JdbcProperties.profiles.get("jdbc.url"));
        config.setUsername(JdbcProperties.profiles.get("jdbc.username"));
        config.setPassword(JdbcProperties.profiles.get("jdbc.password"));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);

    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
