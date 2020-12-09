package com.liq.hmilydemo.common.order.mapper;

import com.liq.hmilydemo.common.order.entity.Order;

public interface OrderMapper {

    int save(Order order);

    int update(Order order);
}
