package com.fbw.service.entity.portal;

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
     * 生成订单失败
     */
    static final String PORTAL_CREATE_RECHARGE_ORDER_FAIL = "1207";

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
    static final String PORTAL_UPDATE_DB_FAIL = "1221";

    /**
     * 地推类型不正常
     */
    static final String PORTAL_GROUND_TYPE_FAIL = "1222";

    /**
     * 登陆微信号为空
     */
    static final String PORTAL_LOGIN_OPENID_NULL_FAIL = "1223";

    /**
     * 商家信息为空
     */
    static final String PORTAL_HOME_SHOPINFO_FAIL = "1224";

    /**
     * 买单用户不存在
     */
    static final String PORTAL_PAY_USER_NOT_EXIST = "1225";

    /**
     * 更新翻倍日失败
     */
    static final String PORTAL_UPDATE_DOUBLEDAY_FAIL = "1226";

    /**
     * 获取充值记录失败
     */
    static final String PORTAL_GET_RECHARGE_RECORD_FAIL = "1227";

    /**
     * 系统异常
     */
    static final String PORTAL_HOME_SYSTEM_FAIL = "9001";

    /**
     * 参数异常
     */
    static final String PORTAL_HOME_PARAM_FAIL = "9002";

    /**
     * 获取城市信息失败！
     */
    static final String PORTAL_HOME_CITY_LIST_FAIL = "9003";

    /**
     * 申请代理城市失败！
     */
    static final String PORTAL_HOME_SAVE_CITYAGENT_FAIL = "9004";

    /**
     * 提交商家错误信息失败！
     */
    static final String PORTAL_HOME_SAVE_SHOPINFO_ERROR_FAIL = "9005";

    /**
     * 身份证号错误
     */
    static final String PORTAL_USER_ID_CARD_ERROR = "11000";

    /**
     * 姓名错误
     */
    static final String PORTAL_USER_REAL_NAME_ERROR = "11001";

    /**
     * 身份证号或姓名错误
     */
    static final String PORTAL_USER_REAL_NAME_ID_CARD_ERROR = "11002";

    /**
     * 身份证号重复
     */
    static final String PORTAL_USER_ID_CARD_REPEAT = "11003";

    /**
     * 插入用户评论失败
     */
    static final String PORTAL_USER_COMMENT_DB_ERROR = "11004";

    /**
     * 插入用户评论状态（订单表）失败
     */
    static final String PORTAL_USER_ORDER_COMMENT_STATUS_DB_ERROR = "11005";

    /**
     * 删除或点击收藏的店铺失败
     */
    static final String PORTAL_USER_COLLECT_SHOP_ERROR = "11006";

    /**
     * 删除或点击收藏的探店失败
     */
    static final String PORTAL_USER_COLLECT_INTERVIEW_SHOP_ERROR = "11007";

    /**
     * 获取评论失败
     */
    static final String PORTAL_USER_GET_COMMENT_ERROR = "11008";

    /**
     * 获取90天内余额明细失败
     */
    static final String PORTAL_USER_GET_NINTEEN_BALANCE_ERROR = "11009";

    /**
     * 获取订单列表失败
     */
    static final String PORTAL_USER_ORDER_LIST_ERROR = "11010";

    /**
     * 获取订单列表失败
     */
    static final String PORTAL_USER_NOCOMMENT_ORDER_LIST_ERROR = "11011";

    /**
     * 获取订单详情失败
     */
    static final String PORTAL_USER_ORDER_DETAIL_ERROR = "11012";

    /**
     * 获取用户红包列表失败
     */
    static final String PORTAL_USER_REDPACKET_LIST_ERROR = "11013";

    /**
     * 获取用户历史红包列表失败
     */
    static final String PORTAL_USER_USED_REDPACKET_LIST_ERROR = "11014";

    /**
     * 获取用户信息失败
     */
    static final String PORTAL_USER_DB_ERROR = "11015";

    /**
     * 查询数据失败
     */
    static final String PORTAL_SELECT_DB_ERROR = "11016";

    /**
     * 更新用户表积分余额失败
     */
    static final String PORTAL_UPDATE_USER_POINT_FAIL = "11017";

    /**
     * 更新用户积分详情失败
     */
    static final String PORTAL_UPDATE_USER_POINT_DETAIL_FAIL = "11018";

    /**
     * 积分余额不足
     */
    static final String PORTAL_UPDATE_USER_POINT_BALANCE_FAIL = "11019";

    /**
     * 积分remark非法
     */
    static final String PORTAL_USER_POINT_REMARK_FAIL = "11020";

    /**
     * 存储用户、商家反馈失败
     */
    static final String PORTAL_USER_SHOP_FEEDBACK_DB_FAIL = "11021";

    /**
     * Oss失败
     */
    static final String PORTAL_OSS_FAIL = "11022";
}
