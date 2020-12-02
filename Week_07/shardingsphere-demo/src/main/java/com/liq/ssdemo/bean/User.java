package com.liq.ssdemo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String nickname;

    private String mobile;

    private int createTime;

    private int updateTime;

}
