package io.kimmking.dubbo.dbb.provider;

import io.kimmking.dubbo.demo.api.AccountRMBService;
import io.kimmking.dubbo.demo.api.entity.AccountRMBDTO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@MapperScan("io.kimmking.dubbo.dbb.provider.mapper")
@RestController
@EnableTransactionManagement
public class DubboDBBServerApplication {

    @Autowired(required = false)
    private AccountRMBService accountRMBService;

    @GetMapping("/findByUserId")
    public AccountRMBDTO findByUserId(String userId) {
        return accountRMBService.findByUserId(userId);
    }

    public static void main(String[] args) {
        SpringApplication.run(DubboDBBServerApplication.class, args);
    }

}
