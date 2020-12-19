package io.kimmking.dubbo.dba.provider;

import io.kimmking.dubbo.dba.provider.mapper.AccountRMBMapper;
import io.kimmking.dubbo.demo.api.entity.AccountRMBDTO;
import io.kimmking.dubbo.demo.api.AccountRMBService;
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
public class AccountRMBServiceImpl implements AccountRMBService {

    private final AccountRMBMapper accountRMBMapper;

    @Autowired(required = false)
    public AccountRMBServiceImpl(final AccountRMBMapper accountRMBMapper) {
        this.accountRMBMapper = accountRMBMapper;
    }

    @Override
    public AccountRMBDTO findByUserId(String userId) {
        List<AccountRMBDTO> list = accountRMBMapper.findByUserId(userId);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public boolean freezeMoney(AccountRMBDTO accountRMBDTO) {
        return accountRMBMapper.freezeRmb(accountRMBDTO) > 0;
    }

    @Override
    public boolean confirmMoney(AccountRMBDTO accountRMBDTO) {
        return accountRMBMapper.addRmb(accountRMBDTO) > 0;
    }

    @Override
    public boolean release(AccountRMBDTO accountRMBDTO) {
        return accountRMBMapper.cancel(accountRMBDTO) > 0;
    }
}
