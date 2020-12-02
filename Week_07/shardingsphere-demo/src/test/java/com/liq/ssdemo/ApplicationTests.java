package com.liq.ssdemo;

import com.liq.ssdemo.bean.User;
import com.liq.ssdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        User user = new User();
        user.setId(1);
        user.setNickname("小乙");
        int result = userService.updateUserById(user);
        System.out.println("修改状态：" + result);

        user = userService.findById(user.getId());
        System.out.println(user);
    }
}
