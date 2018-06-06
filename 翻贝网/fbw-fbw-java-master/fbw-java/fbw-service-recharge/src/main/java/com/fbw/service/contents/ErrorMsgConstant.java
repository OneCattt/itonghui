package com.fbw.service.contents;

/**
 * 
 * <一句话功能简述> 公共套件常量类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ErrorMsgConstant
{
    /**
     * 发送POST请求异常
     */
    static final String RECHARGE_SEND_POST_ERROR = "1100";

    /**
     * 发送GET请求异常
     */
    static final String RECHARGE_SEND_GET_ERROR = "1101";

    /**
     * 充值阿里验签异常
     */
    static final String RECHARGE_ALIPAY_SIGN_ERROR = "1102";

    /**
     * 充值阿里转码异常
     */
    static final String RECHARGE_ALIPAY_ENCODING_ERROR = "1103";

    /**
     * 微信异步回调请求报文为空
     */
    static final String RECHARGE_WECHAT_REQ_PARM_ERROR = "1104";

    /**
     * 微信异步回调请求验签错误
     */
    static final String RECHARGE_WECHAT_REQ_SIGN_ERROR = "1105";

    /**
     * XML转换MAP异常
     */
    static final String RECHARGE_XML_CONVERT_MAP_ERROR = "1106";

    /**
     * 微信统一下单失败
     */
    static final String RECHARGE_WECHAT_UNIFIED_ORDER_FAIL = "1107";

    /**
     * 微信充值获取返回报文异常
     */
    static final String RECHARGE_WECHAT_GET_RETURN_MSG_ERROR = "1108";

    /**
     * 微信充值通知支付失败
     */
    static final String RECHARGE_WECHAT_NOTIFY_PAY_ERROR = "1109";

    /**
     * 微信充值更新订单错误
     */
    static final String RECHARGE_WECHAT_UPDATE_ORDER_ERROR = "1110";

    /**
     * 数据库查询异常
     */
    static final String RECHARGE_DB_QUERY_ERROR = "1111";

    /**
     * 数据库更新异常
     */
    static final String RECHARGE_DB_UPDATE_ERROR = "1112";

    /**
     * 数据库保存异常
     */
    static final String RECHARGE_DB_SAVE_ERROR = "1113";

    /**
     * 订单不存在
     */
    static final String RECHARGE_ORDER_NOT_EXICT = "1114";

    /**
     * 订单重复
     */
    static final String RECHARGE_ORDER_PAY_SUCCESS = "1115";

    /**
     * 数据库更新充值订单状态异常
     */
    static final String RECHARGE_DB_UPDATE_ORDER_ERROR = "1116";

    /**
     * 数据库更新充值余额异常
     */
    static final String RECHARGE_DB_UPDATE_BALANCE_ERROR = "1117";

    /**
     * 支付宝充值更新订单错误
     */
    static final String RECHARGE_ALIPAY_UPDATE_ORDER_ERROR = "1118";

    /**
     * 微信异步回调请求签名为空
     */
    static final String RECHARGE_WECHAT_SIGN_NULL_ERROR = "1119";

    /**
     * 充值异步回调参数错误
     */
    static final String RECHARGE_CALL_BACK_PARM_ERROR = "1120";

}
