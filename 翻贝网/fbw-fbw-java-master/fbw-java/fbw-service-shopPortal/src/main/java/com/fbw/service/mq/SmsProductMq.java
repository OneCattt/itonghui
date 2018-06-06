package com.fbw.service.mq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fbw.service.config.mq.JmsMessageType;
import com.fbw.service.enums.SmsEnums;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
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
     * 身份验证验证码
     */
    private static final String SMS_105775012 = "SMS_105775012";

    /**
     * 登录确认验证码
     */
    private static final String SMS_12705223 = "SMS_12705223";

    /**
     * 登录异常验证码
     */
    private static final String SMS_12705222 = "SMS_12705222";

    /**
     * 用户注册验证码
     */
    private static final String SMS_12705221 = "SMS_12705221";

    /**
     * 活动确认验证码
     */
    private static final String SMS_12705220 = "SMS_12705220";

    /**
     * 修改密码验证码
     */
    private static final String SMS_12705219 = "SMS_12705219";

    /**
     * 信息变更验证码
     */
    private static final String SMS_12705218 = "SMS_12705218";

    /**
     * 商家续约短信通知SMS_64580023
     */
    private static final String SMS_64580023 = "SMS_64580023";

    /**
     * 商家上线短信通知SMS_64535035
     */
    private static final String SMS_64535035 = "SMS_64535035";

    /**
     * 财务结款提醒SMS_48320121
     */
    private static final String SMS_48320121 = "SMS_48320121";

    /**
     * 翻贝网用户订阅提醒SMS_39190380
     */
    private static final String SMS_39190380 = "SMS_39190380";

    /**
     * 翻贝日商家提醒SMS_39335358
     */
    private static final String SMS_39335358 = "SMS_39335358";

    /**
     * 商户消费提醒SMS_34870048
     */
    private static final String SMS_34870048 = "SMS_34870048";

    /**
     * 用户提醒：取消翻倍时间限制SMS_31530094
     */
    private static final String SMS_31530094 = "SMS_31530094";

    /**
     * 用户提醒：取消翻倍时间SMS_31520073
     */
    private static final String SMS_31520073 = "SMS_31520073";

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

    private String smsParam;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "smsQueue")
    private Queue smsQueue;

    // @Autowired
    // private Topic smsTopic;

    /**
     * 
     * <功能详细描述>
     * @param data
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void smsSend(String reqData) throws InnerException
    {
        smsParam = "";
        Map<String, Object> reqMap = JsonUtil.convertMapByJson(reqData);
        // Map<String, Object> reqMap = new HashMap<String, Object>();
        String smsmodel = String.valueOf(reqMap.get(REQ_SMSMODEL));
        // String smsmodel = "SMS_105775012";
        codeSms(smsmodel, reqMap);
        noticeSms(smsmodel, reqMap);
        spreadSms(smsmodel, reqMap);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(REQ_MOBILE, String.valueOf(reqMap.get(REQ_MOBILE)));
        // dataMap.put(REQ_MOBILE, "18770057504");
        dataMap.put(REQ_SMSMODEL, smsmodel);
        dataMap.put(REQ_SMSPARAM, smsParam);
        this.jmsMessagingTemplate.convertAndSend(this.smsQueue, JsonUtil.convertMapToJson(dataMap));
        // this.jmsMessagingTemplate.convertAndSend(this.smsTopic, "");
    }

    /**
     * 
     * <功能详细描述>验证码模版类型
     * @param smsmodel
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void codeSms(String smsmodel, Map<String, Object> reqMap) throws InnerException
    {
        StringBuilder builder = new StringBuilder();
        builder.append(SMS_12705218);
        builder.append(SMS_12705219);
        builder.append(SMS_12705220);
        builder.append(SMS_12705221);
        builder.append(SMS_12705222);
        builder.append(SMS_12705223);
        builder.append(SMS_105775012);
        if (!builder.toString().contains(smsmodel))
        {
            return;
        }
        // String product = String.valueOf(reqMap.get("product"));
        String product = "翻贝网";
        String code = String.valueOf(reqMap.get("code"));
        String value = SmsEnums.getMsg(smsmodel);
        value = value.replace("*product*", product);
        smsParam = value.replace("*code*", code);
    }

    /**
     * 
     * <功能详细描述>通知模版类型
     * @param smsmodel
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void noticeSms(String smsmodel, Map<String, Object> reqMap) throws InnerException
    {
        StringBuilder builder = new StringBuilder();
        builder.append(SMS_64580023);
        builder.append(SMS_64535035);
        builder.append(SMS_48320121);
        builder.append(SMS_39190380);
        builder.append(SMS_39335358);
        builder.append(SMS_34870048);
        if (!builder.toString().contentEquals(smsmodel))
        {
            return;
        }
        String value = SmsEnums.getMsg(smsmodel);
        if (SMS_64580023.equals(smsmodel) || SMS_64535035.equals(smsmodel))
        {
            String shopname = String.valueOf(reqMap.get("shopname"));
            String cardholder = String.valueOf(reqMap.get("cardholder"));
            String bankcard = String.valueOf(reqMap.get("bankcard"));
            if ("null".equals(shopname) || "null".equals(cardholder) || "null".equals(bankcard))
            {
                throw new InnerException("2001", smsmodel + InnerCode.geterrorMsg("2001"));
            }
            smsParam = value.replace("*", String.valueOf("shopname")).replace("**", String.valueOf("cardholder"))
                    .replace("***", String.valueOf("bankcard"));
        }
        if (SMS_48320121.equals(smsmodel))
        {
            String shopname = String.valueOf(reqMap.get("shopname"));
            String money = String.valueOf(reqMap.get("money"));
            String banknumber = String.valueOf(reqMap.get("banknumber"));
            if ("null".equals(shopname) || "null".equals(money) || "null".equals(banknumber))
            {
                throw new InnerException("2001", smsmodel + InnerCode.geterrorMsg("2001"));
            }
            smsParam = value.replace("*", String.valueOf("shopname")).replace("**", String.valueOf("money"))
                    .replace("***", String.valueOf("banknumber"));
        }
        if (SMS_39190380.equals(smsmodel) || SMS_39335358.equals(smsmodel))
        {
            String name = String.valueOf(reqMap.get("name"));
            if ("null".equals(name))
            {
                throw new InnerException("2001", smsmodel + InnerCode.geterrorMsg("2001"));
            }
            smsParam = value.replace("*", String.valueOf("name"));
        }
        if (SMS_34870048.equals(smsmodel))
        {
            String money = String.valueOf(reqMap.get("money"));
            if ("null".equals(money))
            {
                throw new InnerException("2001", smsmodel + InnerCode.geterrorMsg("2001"));
            }
            smsParam = value.replace("*", String.valueOf("money"));
        }
    }

    /**
     * 
     * <功能详细描述>推广模版类型
     * @param smsmodel
     * @see [类、类#方法、类#成员]
     */
    private void spreadSms(String smsmodel, Map<String, Object> reqMap)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(SMS_31530094);
        builder.append(SMS_31520073);
        if (!builder.toString().contentEquals(smsmodel))
        {
            return;
        }
    }

}
