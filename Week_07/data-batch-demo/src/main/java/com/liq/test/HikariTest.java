package com.liq.test;

import com.liq.dao.JdbcDataSource;
import com.liq.dao.impl.HikariDataSourceImpl;
import com.liq.service.OrderService;
import com.liq.service.impl.OrderServiceImpl;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/01 00:05
 * version: 1.0
 */
public class HikariTest {

    public static void main(String[] args) {
        JdbcDataSource jdbcDataSource = new HikariDataSourceImpl();
        OrderService orderService = new OrderServiceImpl(jdbcDataSource);

        CreateOrderTest createOrderTest = new CreateOrderTest(orderService,15, 1000);
//        createOrderTest.addOne();
//        createOrderTest.addBatch();
        createOrderTest.threadsAddBatch();
    }

}
