package com.fbw.service.contents;

/**
 * 
 * <功能详细描述> 充值常量类
 * @author JACK HUANG
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RechargeConstant
{
    /**
     * 充值日志订单号码
     */
    static final String RECHARGE_LOG_ORDER_NUMBER = "out_trade_no";

    /**
     * 充值日志总金额
     */
    static final String RECHARGE_LOG_TOTAL_MONEY = "total_fee";

    /**
     * 充值日志号码
     */
    static final String RECHARGE_LOG_MOBILE_PHONE = "mobile_phone";

    /**
     * 充值日志错误信息
     */
    static final String RECHARGE_LOG_ERROR_MSG = "error_msg";

    /**
     * 充值日志充值来源类型
     */
    static final String RECHARGE_LOG_SOURCE_TYPE = "source_type";

    /**
     * 充值请求报文
     */
    static final String RECHARGE_LOG_REQUEST_MSG = "requestMsg";

    /**
     * 商品描述
     */
    static final String RECHARGE_GOODS_DES = "翻贝网充值订单";

    /**
     * 充值请求IP
     */
    static final String RECHARGE_REQ_IP = "47.93.158.253";
}
