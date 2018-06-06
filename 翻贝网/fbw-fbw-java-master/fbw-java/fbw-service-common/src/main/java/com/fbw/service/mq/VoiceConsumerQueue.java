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
 * <功能详细描述> 语音通知消费者
 * @author JACK HUANG
 * @version [版本号, 2017年10月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class VoiceConsumerQueue extends BaseMq
{
    @Autowired
    private SendSmsMessageService sendSmsMessageService;

    @Override
    @JmsListener(destination = "portal-voice-sms-queue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueue(String text)
    {
        Map<String, Object> map = JsonUtil.convertMapByJson(text);
        sendSmsMessageService.sendVoiceCode(String.valueOf(map.get("mobile")), String.valueOf(map.get("code")));
    }

    @Override
    public void receiveTopic(String text)
    {
        // TODO Auto-generated method stub

    }

}
