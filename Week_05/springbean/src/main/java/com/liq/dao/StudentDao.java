package com.liq.dao;

import com.liq.bean.Student;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 15:21
 * version: 1.0
 */
@Repository
public class StudentDao {

    public int addOne(Student student) {
        System.out.println("添加成功：" + student.getName());
        return 1;
    }
}
