package com.liq.hmilydemo.account.service;

import com.liq.hmilydemo.common.account.entity.AccountDTO;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/09 19:13
 * version: 1.0
 */
public interface AccountService {

    /**
     * 扣款支付.
     *
     * @param accountDTO 参数dto
     * @return true boolean
     */
    boolean payment(AccountDTO accountDTO);

}
