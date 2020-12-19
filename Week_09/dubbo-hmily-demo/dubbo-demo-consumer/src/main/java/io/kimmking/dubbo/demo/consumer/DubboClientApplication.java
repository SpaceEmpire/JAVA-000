package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.*;
import io.kimmking.dubbo.demo.api.entity.AccountDollarDTO;
import io.kimmking.dubbo.demo.api.entity.AccountRMBDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class DubboClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class, args);
    }

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/test")
    public int test() {
        exchangeService.exchange("A001", "A002");
        return 0;
    }

//    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
//    private AccountRMBService dbaRMBService;
//
//    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
//    private AccountDollarService dbaDollarService;
//
//    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12346")
//    private AccountRMBService dbbRMBService;
//
//    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12346")
//    private AccountDollarService dbbDollarService;
//
//    @Bean
//    public ApplicationRunner runner() {
//        return args -> {
//
//            AccountRMBDTO aRMB = new AccountRMBDTO();
//            aRMB.setUserId("A001");
//            AccountDollarDTO aDollar = new AccountDollarDTO();
//            aDollar.setUserId("A001");
//            aDollar.setFreezeAmount(new BigDecimal(1));
//
//
//            AccountRMBDTO bRMB = new AccountRMBDTO();
//            bRMB.setUserId("A002");
//            bRMB.setFreezeAmount(new BigDecimal(7));
//            AccountDollarDTO bDollar = new AccountDollarDTO();
//            bDollar.setUserId("A002");
//
//            AccountRMBDTO accountRMBDTO = dbaRMBService.findByUserId("A001");
//            System.out.println(accountRMBDTO.toString());
//
//            accountRMBDTO = dbbRMBService.findByUserId("A002");
//            System.out.println(accountRMBDTO.toString());
//
//        };
//    }

}
