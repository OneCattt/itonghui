package com.fbw.service.mq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fbw.service.base.BaseLogger;
import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.config.mq.JmsMessageType;
import com.fbw.service.util.JsonUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 登陆生产者Mq
 * @author JACK HUANG
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
@AutoConfigureAfter(
{ JmsMessageType.class })
public class LoginProductMq implements BaseLogger
{
    /**
     * 电话号码
     */
    private static final String REQ_MOBILE = "mobile";

    /**
     * 设备id号
     */
    private static final String REQ_DEVICE_ID = "deviceId";

    /**
     * 操作系统版本
     */
    private static final String REQ_OS_VERSION = "osVersion";

    /**
     * 手机类型
     */
    private static final String REQ_PHONE_TYPE = "phoneType";

    /**
     * 微信号
     */
    private static final String REQ_OPEN_ID = "openId";

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 更新用户设备信息
     */
    @Resource(name = "updateUserDeviceInfoQueue")
    private Queue updateUserDeviceInfoQueue;

    /**
     * 更新用户微信号信息
     */
    @Resource(name = "updateUserOpenIdQueue")
    private Queue updateUserOpenIdQueue;

    /**
     * 
     * <功能详细描述> 更新用户手机设备信息
     * @param loginEntity
     * @see [类、类#方法、类#成员]
     */
    public void updateUserMobileDeviceInfo(LoginEntity loginEntity)
    {
        getErrorLog(loginEntity.getTrackId() + ":updateUserMobileDeviceInfo:" + loginEntity.toString());
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(REQ_MOBILE, loginEntity.getMobile());
        dataMap.put(REQ_DEVICE_ID, loginEntity.getDeviceId());
        dataMap.put(REQ_OS_VERSION, loginEntity.getOsVersion());
        dataMap.put(REQ_PHONE_TYPE, loginEntity.getPhoneType());
        jmsMessagingTemplate.convertAndSend(this.updateUserDeviceInfoQueue, JsonUtil.convertMapToJson(dataMap));
    }

    /**
     * 
     * <功能详细描述> 更新用户的微信号信息
     * @param loginEntity
     * @see [类、类#方法、类#成员]
     */
    public void updateUserOpenId(LoginEntity loginEntity)
    {
        getErrorLog(loginEntity.getTrackId() + ":updateUserOpenId:" + loginEntity.toString());
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(REQ_MOBILE, loginEntity.getMobile());
        dataMap.put(REQ_OPEN_ID, loginEntity.getOpenId());
        jmsMessagingTemplate.convertAndSend(this.updateUserOpenIdQueue, JsonUtil.convertMapToJson(dataMap));
    }

    /**
     * 
     * <功能详细描述> 更新用户地推注册状态
     * @param String mobile
     * @see [类、类#方法、类#成员]
     */
    public void updateUserGroundRegisterStatus(String mobile, String groundUrl)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Map<String, String> data = NomalUtil.analysisGroundUrl(groundUrl);
        dataMap.put(REQ_MOBILE, mobile);
        dataMap.put("groundType", data.get("groundType"));
        dataMap.put("shopAssistantId", data.get("shopAssistantId"));
        dataMap.put("shopId", data.get("shopId"));
        dataMap.put("salesManId", data.get("salesManId"));
        jmsMessagingTemplate.convertAndSend(this.updateUserOpenIdQueue, mobile);
    }

    private void getErrorLog(String errorMsg)
    {
        getBaseLogger(LoginProductMq.class, errorMsg);
    }

}
