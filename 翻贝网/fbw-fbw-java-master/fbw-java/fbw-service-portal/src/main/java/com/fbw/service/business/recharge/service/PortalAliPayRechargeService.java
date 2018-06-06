package com.fbw.service.business.recharge.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.recharge.context.AbstractSuperRecharge;
import com.fbw.service.business.recharge.entity.RechargeReqEntity;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.RechargeFeignClient;

/**
 * 
 * <功能详细描述> 支付宝充值接口
 * @author JACK HUANG
 * @version [版本号, 2017年8月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class PortalAliPayRechargeService extends AbstractSuperRecharge
{
    @Autowired
    RechargeFeignClient rechargeFeignClient;

    @Override
    public Map<String, Object> sendRechargePayRequest(RechargeReqEntity rechargeReqEntity) throws InnerException
    {
        Map<String, Object> data = null;
        // 构造支付宝充值订单实体类
        RechargeEntity rechargeEntity = buildAliPayRecharge(rechargeReqEntity);
        // 发送支付宝支付请求
        data = rechargeFeignClient.sendAppAiPay(rechargeEntity.getOrderNum(), rechargeEntity.getActualMoney(),
                rechargeReqEntity.getMobile());
        // 生成充值订单
        createRechargeOrder(rechargeEntity);
        return data;
    }

    public List<Map<String, Object>> getRechargeListByUserId(String mobile)
    {
        return rechargeFeignClient.getRechargeListByUserId(mobile);
    }

    /**
     * 
     * <功能详细描述>
     * @param mobilePhone
     * @param money
     * @param payType
     * @param cityId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private RechargeEntity buildAliPayRecharge(RechargeReqEntity rechargeReqEntity) throws InnerException
    {
        RechargeEntity rechargeEntity = buildRechargeEntity(rechargeReqEntity.getMobile(),
                rechargeReqEntity.getRechargeMoney(), rechargeReqEntity.getCityId());
        rechargeEntity.setOrderNum(getAliPayOrderNum(rechargeReqEntity.getMobile()));
        rechargeEntity.setSource("AliPay");
        rechargeEntity.setUserId(Integer.parseInt(rechargeReqEntity.getUserId()));
        return rechargeEntity;
    }

}
