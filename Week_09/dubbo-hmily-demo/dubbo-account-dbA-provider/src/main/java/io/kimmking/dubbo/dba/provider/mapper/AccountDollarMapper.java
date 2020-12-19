package io.kimmking.dubbo.dba.provider.mapper;

import io.kimmking.dubbo.demo.api.entity.AccountDollarDTO;

import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/09 18:35
 * version: 1.0
 */

public interface AccountDollarMapper {

    List<AccountDollarDTO> findByUserId(String userId);

    int freezeDollar(AccountDollarDTO accountDTO);

    int addDollar(AccountDollarDTO accountDTO);

    int unfreeze(AccountDollarDTO accountDTO);

    int cancel(AccountDollarDTO accountDTO);
}
