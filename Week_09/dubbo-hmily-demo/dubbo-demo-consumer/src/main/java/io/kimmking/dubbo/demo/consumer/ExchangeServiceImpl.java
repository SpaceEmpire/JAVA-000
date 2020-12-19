package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.AccountDollarService;
import io.kimmking.dubbo.demo.api.AccountRMBService;
import io.kimmking.dubbo.demo.api.ExchangeService;
import io.kimmking.dubbo.demo.api.entity.AccountDollarDTO;
import io.kimmking.dubbo.demo.api.entity.AccountRMBDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @description TODO
 * author: liquan
 * date: 2020/12/19 16:01
 * version: 1.0
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private AccountRMBService dbaRMBService;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private AccountDollarService dbaDollarService;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12346")
    private AccountRMBService dbbRMBService;

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12346")
    private AccountDollarService dbbDollarService;

    /**
     * try 冻结
     * @param userAId A用户Id
     * @param userBId B用户Id
     * @return true
     */
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Override
    public boolean exchange(String userAId, String userBId) {
        //-------数据库A------
        //1、冻结A用户，1美元
        AccountDollarDTO userAdollarDTO = dbaDollarService.findByUserId(userAId);
        userAdollarDTO.setAmount(userAdollarDTO.getBalance());
        dbaDollarService.freezeMoney(userAdollarDTO);

        //-------数据库B------
        //2、冻结B用户，7人民币
        AccountRMBDTO userBRmbDTO = dbbRMBService.findByUserId(userBId);
        userBRmbDTO.setAmount(userBRmbDTO.getBalance());
        dbbRMBService.freezeMoney(userBRmbDTO);
        return true;
    }

    /**
     * 确认交易
     * @param userAId A用户Id
     * @param userBId B用户Id
     * @return true
     */
    private boolean confirm(String userAId, String userBId) {
        //-------数据库A------
        //1、A用户，人民币账户增加7人民币
        //2、A用户，释放冻结的1美元
        AccountRMBDTO userARmbDTO = dbaRMBService.findByUserId(userAId);
        userARmbDTO.setAmount(new BigDecimal(7));
        dbaRMBService.confirmMoney(userARmbDTO);

        AccountDollarDTO userAdollarDTO = dbaDollarService.findByUserId(userAId);
        userAdollarDTO.setAmount(new BigDecimal(1));
        dbaDollarService.release(userAdollarDTO);

        //-------数据库B------
        //3、B用户，美元账户增加1美元
        //4、B用户，释放冻结的7人民币
        AccountDollarDTO userBDollarDTO = dbbDollarService.findByUserId(userBId);
        userBDollarDTO.setAmount(new BigDecimal(1));
        dbbDollarService.confirmMoney(userBDollarDTO);

        AccountRMBDTO userBRmbDTO = dbbRMBService.findByUserId(userBId);
        userBRmbDTO.setAmount(new BigDecimal(7));
        dbbRMBService.release(userBRmbDTO);
        return true;
    }

    /**
     * 取消交易
     * @param userAId A用户Id
     * @param userBId B用户Id
     * @return true
     */
    private boolean cancel(String userAId, String userBId) {
        return true;
    }
}
