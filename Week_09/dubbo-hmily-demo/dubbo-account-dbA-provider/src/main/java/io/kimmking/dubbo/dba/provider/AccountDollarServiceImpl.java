package io.kimmking.dubbo.dba.provider;

import io.kimmking.dubbo.demo.api.AccountDollarService;
import io.kimmking.dubbo.demo.api.entity.AccountDollarDTO;
import io.kimmking.dubbo.dba.provider.mapper.AccountDollarMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/18 22:21
 * version: 1.0
 */
@DubboService(version = "1.0.0")
public class AccountDollarServiceImpl implements AccountDollarService {

    private final AccountDollarMapper accountDollarMapper;

    @Autowired(required = false)
    public AccountDollarServiceImpl(final AccountDollarMapper accountDollarMapper) {
        this.accountDollarMapper = accountDollarMapper;
    }

    @Override
    public AccountDollarDTO findByUserId(String userId) {
        List<AccountDollarDTO> list = accountDollarMapper.findByUserId(userId);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public boolean freezeMoney(AccountDollarDTO accountDollarDTO) {
        return accountDollarMapper.freezeDollar(accountDollarDTO) > 0;
    }

    @Override
    public boolean confirmMoney(AccountDollarDTO accountDollarDTO) {
        return accountDollarMapper.addDollar(accountDollarDTO) > 0;
    }

    @Override
    public boolean release(AccountDollarDTO accountDollarDTO) {
        return accountDollarMapper.cancel(accountDollarDTO) > 0;
    }
}
