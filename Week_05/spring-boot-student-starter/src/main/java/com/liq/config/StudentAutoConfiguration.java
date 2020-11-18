package com.liq.config;

import com.liq.service.StudentService;
import com.liq.service.impl.StudentServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/18 09:51
 * version: 1.0
 */

@Configuration
public class StudentAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(StudentService.class)
    @ConditionalOnProperty(prefix = "stu_service", value = "enabled", havingValue = "true")
    public StudentService studentService() {
        return new StudentServiceImpl();
    }
}
