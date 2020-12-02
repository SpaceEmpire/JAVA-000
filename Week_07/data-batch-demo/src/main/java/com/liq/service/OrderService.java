package com.liq.service;

import com.liq.bean.Order;

import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/01 00:05
 * version: 1.0
 */
public interface OrderService {

    /**
     * 添加订单
     * @param order
     */
    int addOne(Order order);

    /**
     * 批量插入
     * @param orders
     * @return
     */
    int addBatch(List<Order> orders);
}
