package com.fbw.service.enums;

/**
 * 
 * <功能详细描述>短信Enums
 * @author FBW0115
 * @version [版本号, 2017年8月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum SmsEnums
{

    /**
     * 验证码模版SMS_12705218
     */
    COMMON_DICT_SMS_12705218_CONF("SMS_12705218", "{code:'*code*',product:'*product*'}"),

    /**
     * 验证码模版SMS_12705219
     */
    COMMON_DICT_SMS_12705219_CONF("SMS_12705219", "{code:'*code*',product:'*product*'}"),

    /**
     * 验证码模版SMS_12705220
     */
    COMMON_DICT_SMS_12705220_CONF("SMS_12705220", "{code:'*code*',product:'*product*'}"),

    /**
     * 验证码模版SMS_12705221
     */
    COMMON_DICT_SMS_12705221_CONF("SMS_12705221", "{code:'*code*',product:'*product*'}"),

    /**
     * 验证码模版SMS_12705222
     */
    COMMON_DICT_SMS_12705222_CONF("SMS_12705222", "{code:'*code*',product:'*product*'}"),

    /**
     * 验证码模版SMS_12705223
     */
    COMMON_DICT_SMS_12705223_CONF("SMS_12705223", "{code:'*code*',product:'*product*'}"),

    /**
     * 验证码模版SMS_12705224
     */
    COMMON_DICT_SMS_12705224_CONF("SMS_12705224", "{customer:'*'}"),

    /**
     * 验证码模版SMS_105775012
     */
    COMMON_DICT_SMS_105775012_CONF("SMS_105775012", "{code:'*code*',product:'*product*'}"),

    /**
     * 商家续约短信通知SMS_64580023
     */
    COMMON_DICT_SMS_64580023_CONF("SMS_64580023", "{shopname:'*',cardholder:'**',bankcard:'***'}"),

    /**
     * 商家上线短信通知SMS_64535035
     */
    COMMON_DICT_SMS_64535035_CONF("SMS_64535035", "{shopname:'*',cardholder:'**',bankcard:'***'}"),

    /**
     * 财务结款提醒SMS_48320121
     */
    COMMON_DICT_SMS_48320121_CONF("SMS_48320121", "{shopname:'*',money:'**',banknumber:'***'}"),

    /**
     * 翻贝网用户订阅提醒SMS_39190380
     */
    COMMON_DICT_SMS_39190380_CONF("SMS_39190380", "{name:'*'}"),

    /**
     * 翻贝日商家提醒SMS_39335358
     */
    COMMON_DICT_SMS_39335358_CONF("SMS_39335358", "{name:'*'}"),

    /**
     * 商户消费提醒SMS_34870048
     */
    COMMON_DICT_SMS_34870048_CONF("SMS_34870048", "{money:'*'}"),

    /**
     * 用户提醒：取消翻倍时间限制SMS_31530094
     */
    COMMON_DICT_SMS_31530094_CONF("SMS_31530094", ""),

    /**
     * 用户提醒：取消翻倍时间SMS_31520073
     */
    COMMON_DICT_SMS_31520073_CONF("SMS_31520073", "");

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
    private SmsEnums(String key, String value)
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
        for (SmsEnums c : SmsEnums.values())
        {
            if (c.getKey().equals(key))
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
