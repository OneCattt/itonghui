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
     * 黑名单失败提示
     */
    static final String PORTAL_BLACK_USER_FAIL_MSG = "1200";

    /**
     * 手机号格式失败提示
     */
    static final String PORTAL_MOBILE_FORMAT_FAIL_MSG = "1201";

    /**
     * 短信验证码失败提示
     */
    static final String PORTAL_SMS_CODE_FAIL_MSG = "1202";

    /**
     * 短信TOKEN失效提示
     */
    static final String PORTAL_SMS_TOKEN_INVALID_MSG = "1203";

    /**
     * 短信验证码失败提示
     */
    static final String PORTAL_LOGIN_SAVE_USER_ERROR = "1204";

    /**
     * 金额格式不正确
     */
    static final String PORTAL_MONEY_FORMAT_ERROR = "1205";

    /**
     * 微信支付失败
     */
    static final String PORTAL_WECHAT_PAY_FAIL = "1206";

    /**
     * 微信生成订单失败
     */
    static final String PORTAL_WECHAT_CREATE_ORDER_FAIL = "1207";

    /**
     * 支付类型错误
     */
    static final String PORTAL_PAY_TYPE_FAIL = "1208";

    /**
     * 买单余额不足
     */
    static final String PORTAL_PAY_INSUFFICIENT_BALANCE_FAIL = "1209";

    /**
     * 更新用户余额不成功
     */
    static final String PORTAL_PAY_BALANCE_FAIL = "1210";

    /**
     * 空参数异常
     */
    static final String PORTAL_NULL_PARM_FAIL = "1211";

    /**
     * 更新用户红包状态失败
     */
    static final String PORTAL_UPDATE_RED_PACKET_STATUS_FAIL = "1212";

    /**
     * 超过消费金额封顶
     */
    static final String PORTAL_EXCEED_CUSTOMER_MONEY_LIMIT_FAIL = "1213";

    /**
     * 超过消费次数
     */
    static final String PORTAL_EXCEED_CUSTOMER_TIMES_FAIL = "1214";

    /**
     * 支付密码不正确
     */
    static final String PORTAL_PAY_PASSWORD_FAIL = "1215";

    /**
     * 保存用户订单失败
     */
    static final String PORTAL_SAVE_ORDER_FAIL = "1216";

    /**
     * md5加密失败
     */
    static final String PORTAL_MD5_ENCODER_FAIL = "1217";

    /**
     * 买单方式错误
     */
    static final String PORTAL_PAY_BILL_WAY_FAIL = "1218";

    /**
     * 重复注册账号
     */
    static final String PORTAL_LOGIN_REPEAT_REGISTER_FAIL = "1219";

    /**
     * 保存数据库失败
     */
    static final String PORTAL_SAVE_DB_FAIL = "1220";

    /**
     * 更新数据库失败
     */
    static final String PORTAL_UPDATE_DB_FAIL = "1220";

    /**
     * 邀请码不正确
     */
    static final String PORTAL_LOGIN_INVITE_CODE_FAIL = "1221";

    /**
     * 账号不存在
     */
    static final String PORTAL_LOGIN_MOBILE_FAIL = "1222";

    /**
     * 密码错误
     */
    static final String PORTAL_LOGIN_PASSWORD_FAIL = "1223";

    /**
     * 密码错误
     */
    static final String CHECK_PASSWORD_FAIL = "1224";

    /**
     * 密码错误
     */
    static final String OLDPWD_PASSWORD_FAIL = "1225";

    /**
     * 密码错误
     */
    static final String USER_ROLE_FAIL = "1226";

}
