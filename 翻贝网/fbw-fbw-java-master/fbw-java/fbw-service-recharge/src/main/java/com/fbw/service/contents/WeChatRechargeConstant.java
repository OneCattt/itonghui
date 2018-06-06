package com.fbw.service.contents;

/**
 * 
 * <功能详细描述> 微信充值常量类
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface WeChatRechargeConstant
{
    /**
     * 微信充值加密签名
     */
    static final String RECHARGE_WECHAT_ENCRYPT_SIGN = "puFGp3FTn4wPW3MIMoQFZYSe0Jxm1eOR";

    /**
     * 
     * 应用ID 微信开放平台审核通过的应用APPID
     */
    static final String RECHARGE_WECHAT_APPID = "wx5a540a7265ed4c94";

    /**
     * 商户号 微信支付分配的商户号
     */
    static final String RECHARGE_WECHAT_MCHID = "1408190402";

    /**
     * 微信回调地址
     */
    static final String RECHARGE_WECHAT_NOTIFY_URL = "http://release.fbw-china.com:8040/appwechatnotify";

    /**
     * 微信统一订单接口请求地址
     */
    static final String RECHARGE_WECHAT_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

}
