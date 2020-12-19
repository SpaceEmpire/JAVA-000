package io.kimmking.dubbo.demo.api;

import io.kimmking.dubbo.demo.api.entity.AccountDollarDTO;

/**
 * RMB账户操作
 *
 * @author liquan
 * date: 2020/12/18 22:05
 * version: 1.0
 */
public interface AccountDollarService {

    /**
     * 查询账户信息
     *
     * @param userId
     * @return
     */
    AccountDollarDTO findByUserId(String userId);

    /**
     * 冻结金额
     *
     * @param accountDollarDTO
     * @return
     */
    boolean freezeMoney(AccountDollarDTO accountDollarDTO);

    /**
     * 确定转账
     *
     * @param accountDollarDTO
     * @return
     */
    boolean confirmMoney(AccountDollarDTO accountDollarDTO);

    /**
     * 释放冻结金额
     *
     * @param accountDollarDTO
     * @return
     */
    boolean release(AccountDollarDTO accountDollarDTO);
}
