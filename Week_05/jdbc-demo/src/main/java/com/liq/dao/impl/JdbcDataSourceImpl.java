package com.liq.dao.impl;

import com.liq.dao.JdbcDataSource;
import com.liq.dao.JdbcProperties;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/20 10:01
 * version: 1.0
 */
public class JdbcDataSourceImpl implements JdbcDataSource {


    static {
        try {
            Class.forName(JdbcProperties.profiles.get("jdbc.driver_class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JdbcProperties.profiles.get("jdbc.url"), JdbcProperties.profiles.get("jdbc.username"), JdbcProperties.profiles.get("jdbc.password"));
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
