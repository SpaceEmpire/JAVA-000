package com.liq.service.impl;


import com.liq.service.StudentService;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 15:23
 * version: 1.0
 */
public class StudentServiceImpl implements StudentService {


    @Override
    public void select() {
        System.out.println("select ...");
//        return 1;
    }

    @Override
    public int getCount() {
        System.out.println("getCount ...");
        return 1;
    }
}
