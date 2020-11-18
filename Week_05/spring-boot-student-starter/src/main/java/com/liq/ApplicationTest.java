package com.liq;

import com.liq.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/17 22:49
 * version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testSelect() {
        studentService.select();
    }
}
