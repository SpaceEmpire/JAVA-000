package com.liq.shardingproxydemo.service.impl;

import com.liq.shardingproxydemo.bean.Order;
import com.liq.shardingproxydemo.mapper.OrderMapper;
import com.liq.shardingproxydemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/10 08:57
 * version: 1.0
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {


    private final OrderMapper orderMapper;

    @Autowired(required = false)
    public OrderServiceImpl(final OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> findByOrderId(final Long orderId) {
        return orderMapper.findByOrderId(orderId);
    }

    @Override
    public boolean updateOrderId(final Order order) {
        orderMapper.updateOrderId(order);
        return true;
    }

    @Override
    public boolean deleteByOrderId(final Integer orderId) {
        orderMapper.deleteByOrderId(orderId);
        return true;
    }

    @Override
    public boolean addOrder(final Order order) {
        orderMapper.addOne(order);
        return true;
    }
}
