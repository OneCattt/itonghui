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
    NULL_PONINT("空指针异常", "1001"),

    ARRAY_INDEX_OUT_OF_BOUNDS("数组越界", "1002"),

    Login_SAVE_USER_ERROR("登陆保存用户信息失败", "1003"),

    PORTAL_SMS_CODE_FAIL_MSG("验证码错误", "1202"),

    PORTAL_LOGIN_MOBILE_FAIL("该手机号不是商户或者被禁用!", "1222"),

    PORTAL_LOGIN_PASSWORD_FAIL("手机号或密码错误！", "1223"),

    OLDPWD_PASSWORD_FAIL("旧密码错误,请重新输入！", "1225"),

    USER_ROLE_FAIL("该子账户已存在，请查看！", "1226"),

    SMS_PARAM_ERROR("发送短信参数异常", "2001");
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
            if (c.getErrorCode() == errorCode)
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
