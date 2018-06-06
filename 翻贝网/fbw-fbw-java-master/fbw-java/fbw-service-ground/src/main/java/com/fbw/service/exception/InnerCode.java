package com.fbw.service.exception;

/**
 * 
 * <一句话功能简述> 自定义异常编码<功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum InnerCode
{
    SEND_POST_ERROR("发送POST请求出现异常", "1100"), SEND_GET_ERROR("发送GET请求出现异常", "1101"), ALIPAY_SIGN_ERROR("充值阿里验签异常",
            "1102"), ALIPAY_ENCODING_ERROR("充值阿里转码异常", "1103"), WECHAT_REQ_PARM_ERROR("微信异步回调请求报文为空",
                    "1104"), WECHAT_REQ_SIGN_ERROR("微信异步回调请求验签错误", "1105"), XML_CONVERT_MAP_ERROR("XML转换MAP异常",
                            "1106"), WECHAT_UNIFIED_ORDER_FAIL("微信统一下单失败", "1107"), WECHAT_GET_RETURN_MSG_ERROR(
                                    "微信充值获取返回报文异常", "1108"), WECHAT_NOTIFY_PAY_ERROR("微信充值通知支付失败",
                                            "1109"), WECHAT_UPDATE_ORDER_ERROR("微信充值更新订单错误", "1110"), DB_QUERY_ERROR(
                                                    "数据库查询异常", "1111"), DB_UPDATE_ERROR("数据库更新异常",
                                                            "1112"), DB_SAVE_ERROR("数据库保存异常", "1113"), ORDER_NOT_EXICT(
                                                                    "订单不存在", "1114"), ORDER_PAY_SUCCESS("订单重复", "1115");

    /**
     * 异常信息
     */
    private String errorMsg;

    /**
     * 异常编码
     */
    private String errorCode;

    /**
     * 未定义异常
     */
    private final static String UNDEFINE_ERROR = "undenfine_error";

    /**
     * 
     * @param errorMsg
     * @param errorCode <默认构造函数>
     */
    private InnerCode(String errorMsg, String errorCode)
    {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    /**
     * <一句话功能简述> 获取异常信息 <功能详细描述>
     * @param errorCode
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String geterrorMsg(String errorCode)
    {
        for (InnerCode c : InnerCode.values())
        {
            if (c.getErrorCode().equals(errorCode))
            {
                return c.errorMsg;
            }
        }
        return UNDEFINE_ERROR;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

}
