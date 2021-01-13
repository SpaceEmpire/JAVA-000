package com.liq.demo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * User
 * @author liquan
 * date: 2021/01/13 15:34
 * version: 1.0
 */
@Setter
@Getter
@ToString
public class User implements Serializable {

    private int age;

    private String name;
}
