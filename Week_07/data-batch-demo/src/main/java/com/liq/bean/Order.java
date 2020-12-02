package com.liq.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String order_id;

    private int user_id;

    private int good_id;

    private int good_price;

    private int count;

    private int amount;

    private int status;

    private int create_time;

    private int update_time;

}
