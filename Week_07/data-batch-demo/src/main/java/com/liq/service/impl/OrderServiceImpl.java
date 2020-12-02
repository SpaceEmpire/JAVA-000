package com.liq.service.impl;

import com.liq.bean.Order;
import com.liq.dao.JdbcDataSource;
import com.liq.service.OrderService;

import java.sql.*;
import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/01 00:11
 * version: 1.0
 */
public class OrderServiceImpl implements OrderService {

    private JdbcDataSource dataSource;

    public OrderServiceImpl(JdbcDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int addOne(Order order) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            String sql = "insert into `order`(order_id,user_id,good_id,good_price,`count`,amount,status,create_time)VALUES(?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, order.getOrder_id());
            st.setInt(2, order.getUser_id());
            st.setInt(3, order.getGood_id());
            st.setInt(4, order.getGood_price());
            st.setInt(5, order.getCount());
            st.setInt(6, order.getAmount());
            st.setInt(7, order.getStatus());
            st.setInt(8, order.getCreate_time());
            return st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataSource.release(conn, st, rs);
        }
        return 0;
    }

    @Override
    public int addBatch(List<Order> list) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();

            String sql = "insert into `order`(order_id,user_id,good_id,good_price,`count`,amount,status,create_time)VALUES(?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (int i = 0; i < list.size(); i++) {
                Order order = list.get(i);
                st.setString(1, order.getOrder_id());
                st.setInt(2, order.getUser_id());
                st.setInt(3, order.getGood_id());
                st.setInt(4, order.getGood_price());
                st.setInt(5, order.getCount());
                st.setInt(6, order.getAmount());
                st.setInt(7, order.getStatus());
                st.setInt(8, order.getCreate_time());
                //加入批处理
                st.addBatch();
            }
            st.executeBatch();
            conn.commit();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataSource.release(conn, st, rs);
        }
        return 0;
    }
}
