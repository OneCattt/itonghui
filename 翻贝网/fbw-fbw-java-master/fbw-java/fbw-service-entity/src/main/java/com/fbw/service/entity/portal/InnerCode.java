package com.fbw.service.entity.portal;

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
    NULL_PONINT("空指针异常", "1001"),

    ARRAY_INDEX_OUT_OF_BOUNDS("数组越界", "1002"),

    Login_SAVE_USER_ERROR("登陆保存用户信息失败", "1003"),

    PORTAL_HOME_SHOPINFO_FAIL("该商家不存在！", "1224"),

    SMS_PARAM_ERROR("发送短信参数异常", "2001"),

    PORTAL_BLACK_USER_FAIL_MSG("黑名单失败提示", "1200"),

    PORTAL_MOBILE_FORMAT_FAIL_MSG("手机号格式失败提示", "1201"),

    PORTAL_SMS_CODE_FAIL_MSG("短信验证码失败提示", "1202"),

    PORTAL_SMS_TOKEN_INVALID_MSG("短信TOKEN失效提示", "1203"),

    PORTAL_LOGIN_SAVE_USER_ERROR("短信验证码失败提示", "1204"),

    PORTAL_MONEY_FORMAT_ERROR("金额格式不正确", "1205"),

    PORTAL_WECHAT_PAY_FAIL("微信支付失败", "1206"),

    PORTAL_CREATE_RECHARGE_ORDER_FAIL("生成充值订单失败", "1207"),

    PORTAL_PAY_TYPE_FAIL("支付类型错误", "1208"),

    PORTAL_PAY_INSUFFICIENT_BALANCE_FAIL("买单余额不足", "1209"),

    PORTAL_PAY_BALANCE_FAIL("更新用户余额不成功", "1210"),

    PORTAL_NULL_PARM_FAIL("空参数异常", "1211"),

    PORTAL_UPDATE_RED_PACKET_STATUS_FAIL("更新用户红包状态失败", "1212"),

    PORTAL_EXCEED_CUSTOMER_MONEY_LIMIT_FAIL("超过消费金额封顶", "1213"),

    PORTAL_EXCEED_CUSTOMER_TIMES_FAIL("超过消费次数", "1214"),

    PORTAL_PAY_PASSWORD_FAIL("支付密码不正确", "1215"),

    PORTAL_SAVE_ORDER_FAIL("保存用户订单失败", "1216"),

    PORTAL_MD5_ENCODER_FAIL("md5加密失败", "1217"),

    PORTAL_PAY_BILL_WAY_FAIL("买单方式错误", "1218"),

    PORTAL_LOGIN_REPEAT_REGISTER_FAIL("重复注册账号", "1219"),

    PORTAL_SAVE_DB_FAIL("保存数据库失败", "1220"),

    PORTAL_UPDATE_DB_FAIL("更新数据库失败", "1221"),

    PORTAL_GROUND_TYPE_FAIL("地推类型不正常", "1222"),

    PORTAL_LOGIN_OPENID_NULL_FAIL("登陆微信号为空", "1223"),

    PORTAL_PAY_USER_NOT_EXIST("买单用户不存在", "1225"),

    PORTAL_UPDATE_DOUBLEDAY_FAIL("更新翻倍日失败", "1226"),

    PORTAL_GET_RECHARGE_RECORD_FAIL("获取充值记录失败", "1227"),

    PORTAL_HOME_SYSTEM_FAIL("系统异常", "9001"),

    PORTAL_HOME_PARAM_FAIL("参数异常！", "9002"),

    PORTAL_HOME_CITY_LIST_FAIL("获取城市信息失败！", "9003"),

    PORTAL_HOME_SAVE_CITYAGENT_FAIL("申请代理城市失败！", "9004"),

    PORTAL_HOME_SAVE_SHOPINFO_ERROR_FAIL("提交商家错误信息失败！", "9005"),

    PORTAL_USER_ID_CARD_ERROR("身份证号错误", "11000"),

    PORTAL_USER_REAL_NAME_ERROR("姓名错误", "11001"),

    PORTAL_USER_REAL_NAME_ID_CARD_ERROR("实名认证失败", "11002"),

    PORTAL_USER_ID_CARD_REPEAT("身份证号重复", "11003"),

    PORTAL_USER_COMMENT_DB_ERROR("插入用户评论失败", "11004"),

    PORTAL_USER_ORDER_COMMENT_STATUS_DB_ERROR("插入用户评论状态（订单表）失败", "11005"),

    PORTAL_USER_COLLECT_SHOP_ERROR("删除或点击收藏的店铺失败", "11006"),

    PORTAL_USER_COLLECT_INTERVIEW_SHOP_ERROR("删除或点击收藏的探店失败", "11007"),

    PORTAL_USER_GET_COMMENT_ERROR("获取评论失败", "11008"),

    PORTAL_USER_GET_NINTEEN_BALANCE_ERROR("获取90天内余额明细失败", "11009"),

    PORTAL_USER_ORDER_LIST_ERROR("获取订单列表失败", "11010"),

    PORTAL_USER_NOCOMMENT_ORDER_LIST_ERROR("获取订单列表失败", "11011"),

    PORTAL_USER_ORDER_DETAIL_ERROR("获取订单详情失败", "11012"),

    PORTAL_USER_REDPACKET_LIST_ERROR("获取用户红包列表失败", "11013"),

    PORTAL_USER_USED_REDPACKET_LIST_ERROR("获取用户历史红包列表失败", "11014"),

    PORTAL_USER_DB_ERROR("获取用户信息失败", "11015"),

    PORTAL_SELECT_DB_ERROR("查询数据失败", "11016"),

    PORTAL_UPDATE_USER_POINT_FAIL("更新用户积分失败", "11017"),

    PORTAL_UPDATE_USER_POINT_DETAIL_FAIL("更新用户积分详情失败", "11018"),

    PORTAL_UPDATE_USER_POINT_BALANCE_FAIL("积分余额不足", "11019"),

    PORTAL_USER_POINT_REMARK_FAIL("积分remark非法", "11020"),

    PORTAL_USER_SHOP_FEEDBACK_DB_FAIL("存储用户、商家反馈失败", "11021"), PORTAL_OSS_FAIL("获取oss失败", "11022");

    /**
     * 异常信息
     */
    private String errorMsg;

    /**
     * 异常编码
     */
    private String errorCode;

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
        return null;
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
