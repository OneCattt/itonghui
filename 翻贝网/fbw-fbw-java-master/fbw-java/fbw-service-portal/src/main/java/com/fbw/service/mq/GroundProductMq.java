package com.fbw.service.mq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.business.paybill.entity.PayReturnMoneyEntity;
import com.fbw.service.config.mq.JmsMessageType;
import com.fbw.service.util.JsonUtil;

/**
 * 
 * <功能详细描述> 地推生产者MQ
 * @author JACK HUANG
 * @version [版本号, 2017年9月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
@AutoConfigureAfter(
{ JmsMessageType.class })
public class GroundProductMq
{
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 更新用户设备信息
     */
    @Resource(name = "updateLoginGroundInfo")
    private Queue updateLoginGroundInfo;

    /**
     * 更新订单地推信息
     */
    @Resource(name = "updateOrderGroundInfo")
    private Queue updateOrderGroundInfo;

    /**
     * 
     * <功能详细描述> 更新登陆注册地推信息
     * @param loginEntity
     * @see [类、类#方法、类#成员]
     */
    public void updateLoginRegisterWithGround(LoginEntity loginEntity)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("cityId", loginEntity.getCityId() == null ? "" : loginEntity.getCityId());
        dataMap.put("mobile", loginEntity.getMobile() == null ? "" : loginEntity.getMobile());
        dataMap.put("inviteFlag", loginEntity.getInviteFlag() == null ? "" : loginEntity.getInviteFlag());
        dataMap.put("inviteUrl", loginEntity.getInviteUrl() == null ? "" : loginEntity.getInviteUrl());
        dataMap.put("sameDeviceFlag", loginEntity.getSameDeviceFlag() == null ? "" : loginEntity.getSameDeviceFlag());
        jmsMessagingTemplate.convertAndSend(this.updateLoginGroundInfo, JsonUtil.convertMapToJson(dataMap));
    }

    /**
     * 
     * <功能详细描述>更新订单地推信息
     * @param payReturnMoneyEntity
     * @see [类、类#方法、类#成员]
     */
    public void updateOrderWithGround(PayReturnMoneyEntity payReturnMoneyEntity)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("cityId", payReturnMoneyEntity.getCityId() == null ? "" : payReturnMoneyEntity.getCityId());
        dataMap.put("mobile", payReturnMoneyEntity.getMobile() == null ? "" : payReturnMoneyEntity.getMobile());
        dataMap.put("orderNumber",
                payReturnMoneyEntity.getOrderNumber() == null ? "" : payReturnMoneyEntity.getOrderNumber());
        dataMap.put("money", payReturnMoneyEntity.getOrderMoney());
        jmsMessagingTemplate.convertAndSend(this.updateOrderGroundInfo, JsonUtil.convertMapToJson(dataMap));
    }
}
