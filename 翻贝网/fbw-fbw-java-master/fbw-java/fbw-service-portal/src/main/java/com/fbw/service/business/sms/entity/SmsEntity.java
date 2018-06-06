package com.fbw.service.business.sms.entity;

import java.io.Serializable;

/**
 * 
 * <功能详细描述>短信实体类
 * @author FBW0115
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SmsEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 接受号码
     */
    private String mobile;

    /**
     * 短信模版id
     */
    private String smsmodel;

    /**
     * 短信参数
     */
    private String smsParam;

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getSmsmodel()
    {
        return smsmodel;
    }

    public void setSmsmodel(String smsmodel)
    {
        this.smsmodel = smsmodel;
    }

    public String getSmsParam()
    {
        return smsParam;
    }

    public void setSmsParam(String smsParam)
    {
        this.smsParam = smsParam;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("SmsEntity [mobile=");
        builder.append(mobile);
        builder.append(", smsmodel=");
        builder.append(smsmodel);
        builder.append(", smsParam=");
        builder.append(smsParam);
        builder.append("]");
        return builder.toString();
    }

}
