package com.liq.service;

import com.liq.bean.Address;
import com.liq.bean.Student;

import java.util.List;


/**
 * @description TODO
 * author: liquan
 * date: 2020/11/18 12:10
 * version: 1.0
 */
public interface StudentService {

    List<Student> findByName(String name);

    boolean add(Student student);

    boolean del(String name);

    boolean updateAge(Student student);

    boolean register(Student student, Address address);
}
