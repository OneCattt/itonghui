package com.fbw.service.business.sms.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mq.SmsProductMq;

/**
 * 
 * <功能详细描述>短信发送controller
 * @author FBW0115
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "/smsQueue")
public class SendMessageComtroller extends BaseController
{

    @Autowired
    private SmsProductMq smsMq;

    @RequestMapping(value = "/smsQueue")
    public Map<String, Object> smsQueue(String reqString)
    {
        try
        {
            smsMq.smsSend(reqString);
        }
        catch (InnerException e)
        {
            return failedMessage(e.getMessage());
        }
        finally
        {
        }
        return successData(null);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
