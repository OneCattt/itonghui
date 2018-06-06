package com.fbw.service.mq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fbw.service.config.JmsMessageType;
import com.fbw.service.util.JsonUtil;

/**
 * 
 * <功能详细描述> 短信发送生产者MQ
 * @author JACK HUANG
 * @version [版本号, 2017年9月1日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@AutoConfigureAfter(
{ JmsMessageType.class })
public class SmsProductMq
{

    /**
     * 短信提醒
     */
    private static final String SMS_1103010002 = "SMS_103010002";

    /**
     * 短信内容
     */
    private String smsParam = "{methodName:'*methodName*'}";

    /**
     * 电话号码
     */
    private static final String REQ_MOBILE = "mobile";

    /**
     * 模版
     */
    private static final String REQ_SMSMODEL = "smsmodel";

    /**
     * 短信参数
     */
    private static final String REQ_SMSPARAM = "smsParam";

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "smsQueue")
    private Queue smsQueue;

    /**
     * 
     * <功能详细描述>
     * @param data
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void smsSend(String mobile, String methodName)
    {
        smsParam = smsParam.replace("*methodName*", methodName);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(REQ_MOBILE, mobile);
        dataMap.put(REQ_SMSMODEL, SMS_1103010002);
        dataMap.put(REQ_SMSPARAM, smsParam);
        this.jmsMessagingTemplate.convertAndSend(this.smsQueue, JsonUtil.convertMapToJson(dataMap));
    }

}
