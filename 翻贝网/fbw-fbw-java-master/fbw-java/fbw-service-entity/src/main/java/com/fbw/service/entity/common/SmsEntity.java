package com.fbw.service.entity.common;

import java.io.Serializable;

/**
 * 
 * <功能详细描述> 发送短信模板实体类
 * @author JACK HUANG
 * @version [版本号, 2017年8月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SmsEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 验证码
     */
    private String code;

    /**
     * 公司名称
     */
    private String product;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 短信模板
     */
    private String smsModel;

    private Integer trackId;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct(String product)
    {
        this.product = product;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getSmsModel()
    {
        return smsModel;
    }

    public void setSmsModel(String smsModel)
    {
        this.smsModel = smsModel;
    }

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
    }

    @Override
    public String toString()
    {
        return "SmsEntity [code=" + code + ", product=" + product + ", mobilePhone=" + mobilePhone + ", smsModel="
                + smsModel + ", trackId=" + trackId + "]";
    }

}
