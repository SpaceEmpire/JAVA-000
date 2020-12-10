package com.liq.shardingproxydemo.mapper;

import com.liq.shardingproxydemo.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper
 *
 * @author liquan
 * date: 2020/12/02 12:21
 * version: 1.0
 */
@Repository
public interface OrderMapper {

    /**
     * 根据orderId查询订单
     *
     * @param orderId orderId
     * @return List<Order>
     */
    List<Order> findByOrderId(Long orderId);

    /**
     * 更新订单
     *
     * @param order order
     * @return int
     */
    int updateOrderId(Order order);

    /**
     * 据orderId删除订单
     *
     * @param orderId orderId
     * @return int
     */
    int deleteByOrderId(int orderId);

    /**
     * 创建订单
     *
     * @param order order
     * @return int
     */
    int addOne(Order order);
}
