package com.fbw.service.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fbw.service.base.BaseMq;
import com.fbw.service.serivces.SendSmsMessageService;
import com.fbw.service.util.JsonUtil;

/**
 * 
 * <功能详细描述> 发送短信消息对象
 * @author JACK HUANG
 * @version [版本号, 2017年8月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class SmsConsumerQueue extends BaseMq
{

    @Autowired
    private SendSmsMessageService sendSmsMessageService;

    /**
     * 处理短信发送功能
     * @param text
     */
    @Override
    @JmsListener(destination = "portal-sms-queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text)
    {
        Map<String, Object> map = JsonUtil.convertMapByJson(text);
        try
        {
            sendSmsMessageService.SendCode(String.valueOf(map.get("mobile")), String.valueOf(map.get("smsmodel")),
                    String.valueOf(map.get("smsParam")));
        }
        catch (Exception e)
        {
            // TODO 日志处理
            e.printStackTrace();
        }
        System.out.println("消费：" + text);

    }

    @Override
    public void receiveTopic(String text)
    {
        // TODO Auto-generated method stub

    }

}
