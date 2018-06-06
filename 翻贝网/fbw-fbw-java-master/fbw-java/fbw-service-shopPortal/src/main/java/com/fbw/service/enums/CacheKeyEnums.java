package com.fbw.service.enums;

import com.fbw.service.contents.CacheKeyConstant;

/**
 * 
 * <一句话功能简述> 公共套件枚举类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum CacheKeyEnums
{
    /**
     * 测试手机号
     */
    DICT_TESTNUM_CONF(CacheKeyConstant.PORTAL_TEST_MOBILES_KEY, "PORTAL:LOGIN:TEST_NUM_CONF"),
    /**
     * 黑名单
     */
    DICT_BLACKNUM_CONF(CacheKeyConstant.PORTAL_BLACK_MOBILES_KEY, "PORTAL:LOGIN:BLACK_NUM_CONF"),
    /**
     * 限时买单开始时间
     */
    PAY_START_DATE_CONF(CacheKeyConstant.PORTAL_LOGIN_TOKEN_KEY, "PORTAL:WALLET:TIMELIMIT:STARTDATE"),
    /**
     * 限时买单结束时间
     */
    PAY_END_DATE_CONF(CacheKeyConstant.PORTAL_LOGIN_TOKEN_KEY, "PORTAL:WALLET:TIMELIMIT:ENDDATE"),
    /**
     * 登陆token
     */
    DICT_LOGIN_TOKEN_CONF(CacheKeyConstant.PORTAL_LOGIN_TOKEN_KEY, "PORTAL:LOGIN:TOKEN_"),
    /**
     * 登陆短信验证码
     */
    DICT_LOGIN_SMS_CODE_CONF(CacheKeyConstant.SHOP_PORTAL_LOGIN_SMS_CODE, "SHOP:PORTAL:LOGIN:SMS_CODE_"),
    /**
     * 地推注册登陆时间
     */
    GROUND_REGISTER_LOGIN_DATE_CONF(CacheKeyConstant.PORTAL_GROUND_REGISTER_LOGIN_DATE_CONF, "PORTAL:GROUND:LOGIN_DATE");
    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

    /**
     * 
     * @param key
     * @param value <默认构造函数>
     */
    private CacheKeyEnums(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * <一句话功能简述> 获取key对应的值 <功能详细描述>
     * @param errorCode
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getMsg(String key)
    {
        for (CacheKeyEnums c : CacheKeyEnums.values())
        {
            if (c.getKey() == key)
            {
                return c.value;
            }
        }
        return "not key";
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}
