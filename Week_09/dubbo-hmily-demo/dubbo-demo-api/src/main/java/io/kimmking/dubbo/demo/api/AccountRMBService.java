package io.kimmking.dubbo.demo.api;

import io.kimmking.dubbo.demo.api.entity.AccountRMBDTO;

/**
 * RMB账户操作
 *
 * @author liquan
 * date: 2020/12/18 22:05
 * version: 1.0
 */
public interface AccountRMBService {

    /**
     * 查询账户信息
     *
     * @param userId
     * @return
     */
    AccountRMBDTO findByUserId(String userId);

    /**
     * 冻结金额
     *
     * @param accountDTO
     * @return
     */
    boolean freezeMoney(AccountRMBDTO accountDTO);

    /**
     * 确定转账
     *
     * @param accountRMBDTO
     * @return
     */
    boolean confirmMoney(AccountRMBDTO accountRMBDTO);

    /**
     * 释放冻结金额
     *
     * @param accountDTO
     * @return
     */
    boolean release(AccountRMBDTO accountDTO);
}
