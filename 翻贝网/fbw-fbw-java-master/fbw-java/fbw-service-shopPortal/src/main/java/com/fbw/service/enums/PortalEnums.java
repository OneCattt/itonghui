package com.fbw.service.enums;

/**
 * 
 * <一句话功能简述> 公共套件枚举类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum PortalEnums
{

    PORTAL_RECHARGE_WECHAT_PAY_TYPE("2", "weChat"), PORTAL_RECHARGE_ALIPAY_PAY_TYPE("1", "aliPay");
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
    private PortalEnums(String key, String value)
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
        for (PortalEnums c : PortalEnums.values())
        {
            if (c.getKey() == key)
            {
                return c.value;
            }
        }
        return null;
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
