package com.fbw.service.business.recharge.service;

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
 * <功能详细描述>
 * @author JACK HUANG
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class PortalWeChatRechargeService extends AbstractSuperRecharge
{

    @Autowired
    RechargeFeignClient rechargeFeignClient;

    @Override
    public Map<String, Object> sendRechargePayRequest(RechargeReqEntity rechargeReqEntity) throws InnerException
    {
        Map<String, Object> data = null;
        // 构造微信充值订单实体类
        RechargeEntity rechargeEntity = buildWeChatRecharge(rechargeReqEntity);
        // 发送微信支付请求
        data = rechargeFeignClient.sendAppWechatPay(getWeChatOrderNum(rechargeReqEntity.getMobile()),
                rechargeEntity.getActualMoney(), rechargeReqEntity.getRequestIp(), rechargeReqEntity.getMobile());
        // 生成充值订单
        createRechargeOrder(rechargeEntity);
        return data;
    }

    /**
     * 
     * <功能详细描述> 构造微信充值订单实体类
     * @param mobilePhone
     * @param money
     * @param cityId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private RechargeEntity buildWeChatRecharge(RechargeReqEntity rechargeReqEntity) throws InnerException
    {
        RechargeEntity rechargeEntity = buildRechargeEntity(rechargeReqEntity.getMobile(),
                rechargeReqEntity.getRechargeMoney(), rechargeReqEntity.getCityId());
        rechargeEntity.setOrderNum(getWeChatOrderNum(rechargeReqEntity.getMobile()));
        rechargeEntity.setSource("weChat");
        rechargeEntity.setUserId(Integer.parseInt(rechargeReqEntity.getUserId()));
        return rechargeEntity;
    }

}
