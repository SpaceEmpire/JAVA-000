package com.liq.hmilydemo.account.controller;

import com.liq.hmilydemo.account.service.AccountService;
import com.liq.hmilydemo.common.account.entity.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/payment")
    public Boolean payment(@RequestBody AccountDTO accountDO) {
        return accountService.payment(accountDO);
    }

}
