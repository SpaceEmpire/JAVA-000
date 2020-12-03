package com.liq.ssdemo;

import com.liq.ssdemo.bean.User;
import com.liq.ssdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setId(1);
        user.setNickname("小贾");
        int result = userService.updateUserById(user);
        System.out.println("修改返回结果：" + result);

        user = userService.findById(user.getId());
        System.out.println("查询返回结果：" + user);
    }
}
