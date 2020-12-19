package io.kimmking.dubbo.dbb.provider.mapper;

import io.kimmking.dubbo.demo.api.entity.AccountRMBDTO;

import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/09 18:35
 * version: 1.0
 */

public interface AccountRMBMapper {

    List<AccountRMBDTO> findByUserId(String userId);

    int freezeRmb(AccountRMBDTO accountDTO);

    int addRmb(AccountRMBDTO accountDTO);

    int unfreeze(AccountRMBDTO accountDTO);

    int cancel(AccountRMBDTO accountDTO);
}
