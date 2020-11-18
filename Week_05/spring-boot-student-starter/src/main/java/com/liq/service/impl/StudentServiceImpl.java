package com.liq.service.impl;

import com.liq.service.StudentService;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/18 09:47
 * version: 1.0
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public int select() {
        System.out.println("select 查询...");
        return 1;
    }
}
