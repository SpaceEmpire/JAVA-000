package com.liq.hmilydemo.account.service.impl;

import com.liq.hmilydemo.account.service.AccountService;
import com.liq.hmilydemo.common.account.entity.AccountDTO;
import com.liq.hmilydemo.common.account.mapper.AccountMapper;
import lombok.extern.log4j.Log4j2;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/09 19:14
 * version: 1.0
 */
@Log4j2
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @Autowired(required = false)
    public AccountServiceImpl(final AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean payment(AccountDTO accountDTO) {
        log.info("============执行try付款接口===============");
        accountMapper.update(accountDTO);
        return true;
    }

    public boolean confirm(final AccountDTO accountDTO) {
        log.info("============执行confirm 付款接口===============");
        return accountMapper.confirm(accountDTO) > 0;
    }

    public boolean cancel(final AccountDTO accountDTO) {
        log.info("============执行cancel 付款接口===============");
        return accountMapper.cancel(accountDTO) > 0;
    }
}
