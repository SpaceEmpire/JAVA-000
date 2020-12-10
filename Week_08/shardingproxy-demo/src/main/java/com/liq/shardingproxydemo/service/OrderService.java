package com.liq.shardingproxydemo.service;

import com.liq.shardingproxydemo.bean.Order;

import java.util.List;

/**
 * 订单操作接口
 *
 * @author liquan
 * date: 2020/12/10 08:57
 * version: 1.0
 */
public interface OrderService {

    /**
     * 根据orderId查询订单
     *
     * @param orderId orderId
     * @return 订单对象
     */
    List<Order> findByOrderId(Long orderId);

    /**
     * 更新订单
     *
     * @param order 订单对象
     * @return boolean
     */
    boolean updateOrderId(Order order);

    /**
     * 根据orderId删除订单
     *
     * @param orderId orderId
     * @return boolean
     */
    boolean deleteByOrderId(Integer orderId);

    /**
     * 创建订单
     *
     * @param order 订单对象
     * @return boolean
     */
    boolean addOrder(Order order);

}
