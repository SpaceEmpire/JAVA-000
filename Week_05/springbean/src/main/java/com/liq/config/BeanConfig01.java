package com.liq.config;

import com.liq.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/16 15:02
 * version: 1.0
 */

@Configuration
public class BeanConfig01 {

    @Bean(name = "student")
    public Student getStudent() {
        return new Student();
    }
}
