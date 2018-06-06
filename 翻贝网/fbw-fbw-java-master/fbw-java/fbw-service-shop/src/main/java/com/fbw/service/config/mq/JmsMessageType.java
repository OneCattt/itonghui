package com.fbw.service.config.mq;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;

/**
 * 
 * <功能详细描述> 定义消息队列类型
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JmsMessageType
{
    /**
     * 发送短信队列
     */
    private static String PORTAL_SMS_QUEUE = "portal-sms-queue";

    /**
     * 发送短信订阅
     */
    private static String PORTAL_SMS_Topic = "portal-sms-topic";

    /**
     * 
     * <功能详细描述> 短信队列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "smsTopic")
    public Queue queue()
    {
        return new ActiveMQQueue(PORTAL_SMS_Topic);
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
        return new ActiveMQTopic(PORTAL_SMS_QUEUE);
    }

}
