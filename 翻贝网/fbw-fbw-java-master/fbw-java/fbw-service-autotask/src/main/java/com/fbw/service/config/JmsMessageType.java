package com.fbw.service.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * <功能详细描述> 定义消息队列类型
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class JmsMessageType
{
    /**
     * 发送短信队列
     */
    private final String PORTAL_SMS_QUEUE = "portal-sms-queue";

    /**
     * 发送短信队列
     */
    private final String PORTAL_SMS_TOPIC = "portal-sms-queue";

    /**
     * 发送更新用户设备信息队列
     */
    private final String PORTAL_LOGIN_UPDATE_USERDEVICE_QUEUE = "portal-update-userDevice-queue";

    /**
     * 发送更新用户微信号队列
     */
    private final String PORTAL_LOGIN_UPDATE_OPENID_QUEUE = "portal-update-openId-queue";

    /**
     * 更新登陆地推信息
     */
    private final String PORTAL_UPDATE_LOGIN_GROUND_QUEUE = "protal-update-login-ground-queue";

    /**
     * 更新订单地推信息
     */
    private final String PORTAL_UPDATE_ORDER_GROUND_QUEUE = "protal-update-order-ground-queue";

    /**
     * 
     * <功能详细描述> 短信队列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "smsQueue")
    public Queue queue()
    {
        return new ActiveMQQueue(PORTAL_SMS_QUEUE);
    }

    /**
     * 
     * <功能详细描述> 短信订阅
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "smsTopic")
    public Topic topic()
    {
        return new ActiveMQTopic(PORTAL_SMS_TOPIC);
    }

    /**
     * 
     * <功能详细描述> 更新用户信息队列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "updateUserDeviceInfoQueue")
    public Queue updateUserDeviceInfoQueue()
    {
        return new ActiveMQQueue(PORTAL_LOGIN_UPDATE_USERDEVICE_QUEUE);
    }

    /**
     * 
     * <功能详细描述>更新用户微信号信息队列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "updateUserOpenIdQueue")
    public Queue updateUserOpenIdQueue()
    {
        return new ActiveMQQueue(PORTAL_LOGIN_UPDATE_OPENID_QUEUE);
    }

    /**
     * 
     * <功能详细描述>插入地推信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "updateLoginGroundInfo")
    public Queue updateLoginGroundInfo()
    {
        return new ActiveMQQueue(PORTAL_UPDATE_LOGIN_GROUND_QUEUE);
    }

    /**
     * 
     * <功能详细描述>插入订单地推信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "updateOrderGroundInfo")
    public Queue updateOrderGroundInfo()
    {
        return new ActiveMQQueue(PORTAL_UPDATE_ORDER_GROUND_QUEUE);
    }

}
