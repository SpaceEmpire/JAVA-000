package com.liq.hmilydemo.common.account.mapper;

import com.liq.hmilydemo.common.account.entity.AccountDTO;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/09 18:35
 * version: 1.0
 */

public interface AccountMapper {

    int update(AccountDTO accountDTO);

    int confirm(AccountDTO accountDTO);

    int cancel(AccountDTO accountDTO);
}
