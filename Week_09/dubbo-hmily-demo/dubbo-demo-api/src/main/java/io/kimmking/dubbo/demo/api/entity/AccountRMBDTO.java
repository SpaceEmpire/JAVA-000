

package io.kimmking.dubbo.demo.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountRMBDTO implements Serializable {

    private static final long serialVersionUID = 7223470850578998427L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;

    /**
     * 转账金额
     */
    private BigDecimal amount;

}
