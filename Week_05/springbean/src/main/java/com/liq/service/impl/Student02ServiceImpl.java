package com.liq.service.impl;

import com.liq.bean.Student;
import com.liq.dao.StudentDao;
import com.liq.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 15:23
 * version: 1.0
 */
@Service
public class Student02ServiceImpl implements StudentService {

    private StudentDao studentDao;

    public Student02ServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addOne(Student student) {
        return studentDao.addOne(student);
    }

    @Override
    public int select() {
        System.out.println("select ...");
        return 1;
    }
}
