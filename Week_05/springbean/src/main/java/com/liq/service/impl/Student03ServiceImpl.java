package com.liq.service.impl;

import com.liq.bean.Student;
import com.liq.dao.StudentDao;
import com.liq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 15:23
 * version: 1.0
 */
@Service
public class Student03ServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public int addOne(Student student) {
        return studentDao.addOne(student);
    }

    @Override
    public int select() {
        System.out.println("select ...");
        return 1;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
