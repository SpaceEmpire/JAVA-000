package com.liq;

import com.liq.bean.Address;
import com.liq.bean.Student;
import com.liq.service.StudentService;
import com.liq.service.impl.StudentServiceImpl;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/18 11:12
 * version: 1.0
 */
public class jdbcTest {

    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();

        Student student = new Student();
        student.setId(5);
        student.setAge(15);
        student.setName("kitty11");

        // 查询
        studentService.findByName("11");

        // 添加
        studentService.add(student);

        // 修改
        studentService.updateAge(student);

        // 删除
        studentService.del("tony");

        // 测试事物
        Address address = new Address();
        address.setStuId(5);
        address.setAddress("beijing");
        studentService.register(student, address);
    }
}
