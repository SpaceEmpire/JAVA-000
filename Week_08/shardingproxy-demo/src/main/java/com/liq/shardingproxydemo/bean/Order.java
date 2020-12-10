package com.liq.shardingproxydemo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;

    private int userId;

    private int amount;

    private int status;

    private Integer createTime;

    private Integer updateTime;

}
