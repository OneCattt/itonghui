package com.fbw.service.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.exception.InnerException;
import com.fbw.service.service.AliPayAppPayService;
import com.fbw.service.service.WeChatAppPayService;

/**
 * 
 * <功能详细描述> 充值支付controller层
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "/recharge")
public class RechargePayController extends BaseController
{
    @Autowired
    WeChatAppPayService weChatRechargeService;

    @Autowired
    AliPayAppPayService aliPayAppPayService;

    /**
     * 
     * <功能详细描述>获取APP版微信支付请求
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/sendAppWechatPay", method = RequestMethod.GET)
    public Map<String, Object> sendAppWechatPay(String rechargeOrderNumber, BigDecimal money, String reqestIp,
            String mobiles)
    {
        Map<String, Object> map = null;
        try
        {
            // 微信统一下单接口
            map = weChatRechargeService.unifiedOrder(rechargeOrderNumber, money, reqestIp, mobiles);
        }
        catch (Exception e)
        {
            return failedMessage(e.getMessage());
        }
        return successWeChatData(map, rechargeOrderNumber);
    }

    /**
     * 
     * <功能详细描述>获取APP版阿里支付请求
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/sendAppAiPay", method = RequestMethod.GET)
    public Map<String, Object> sendAppAiPay(String rechargeOrderNumber, BigDecimal money, String mobiles)
    {
        String aliPayMsg = "";
        try
        {
            // 支付宝统一下单接口
            aliPayMsg = aliPayAppPayService.unifiedOrder(rechargeOrderNumber, money, mobiles);
        }
        catch (InnerException e)
        {
            return failedMessage(e.getErrorCode());
        }
        return successAliPayData(aliPayMsg, rechargeOrderNumber);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * <功能详细描述> 微信返回成功报文
     * @param data
     * @param orderNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    private Map<String, Object> successWeChatData(Map<String, Object> data, String orderNumber)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("data", data);
        map.put("ordernumber", orderNumber);
        return map;
    }

    /**
     * 
     * <功能详细描述>支付宝返回成功报文
     * @param data
     * @param orderNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    private Map<String, Object> successAliPayData(String data, String orderNumber)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("data", data);
        map.put("ordernumber", orderNumber);
        return map;
    }

}
