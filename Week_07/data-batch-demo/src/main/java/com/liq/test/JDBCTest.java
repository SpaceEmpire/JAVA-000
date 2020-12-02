package com.liq.test;

import com.liq.dao.JdbcDataSource;
import com.liq.dao.impl.JdbcDataSourceImpl;
import com.liq.service.OrderService;
import com.liq.service.impl.OrderServiceImpl;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/01 00:05
 * version: 1.0
 */
public class JDBCTest {

    public static void main(String[] args) {
        JdbcDataSource jdbcDataSource = new JdbcDataSourceImpl();
        OrderService orderService = new OrderServiceImpl(jdbcDataSource);

//        CreateOrderTest createOrderTest = new CreateOrderTest(orderService,100);
//        createOrderTest.addOne();

        CreateOrderTest createOrderTest = new CreateOrderTest(orderService, 10, 100);
//        createOrderTest.addBatch();

        createOrderTest.threadsAddBatch();
    }
}
