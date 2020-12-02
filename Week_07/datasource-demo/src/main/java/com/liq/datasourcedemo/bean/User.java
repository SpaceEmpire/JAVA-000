package com.liq.datasourcedemo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String nickname;

    private String mobile;

    private int createTime;

    private int updateTime;

}
