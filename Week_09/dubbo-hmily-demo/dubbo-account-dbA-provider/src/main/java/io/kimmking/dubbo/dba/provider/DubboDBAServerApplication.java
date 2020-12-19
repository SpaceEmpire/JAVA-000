package io.kimmking.dubbo.dba.provider;

import io.kimmking.dubbo.demo.api.AccountDollarService;
import io.kimmking.dubbo.demo.api.AccountRMBService;
import io.kimmking.dubbo.demo.api.entity.AccountDollarDTO;
import io.kimmking.dubbo.demo.api.entity.AccountRMBDTO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@MapperScan("io.kimmking.dubbo.dba.provider.mapper")
@RestController
public class DubboDBAServerApplication {

    @Autowired(required = false)
    private AccountRMBService accountRMBService;

    @Autowired(required = false)
    private AccountDollarService accountDollarService;

    @GetMapping("/rmb/findByUserId")
    public AccountRMBDTO findByUserId(String userId) {
        return accountRMBService.findByUserId(userId);
    }

    @GetMapping("/dollar/findByUserId")
    public AccountDollarDTO findDollarByUserId(String userId) {
        return accountDollarService.findByUserId(userId);
    }

    public static void main(String[] args) {
        SpringApplication.run(DubboDBAServerApplication.class, args);
    }

}
