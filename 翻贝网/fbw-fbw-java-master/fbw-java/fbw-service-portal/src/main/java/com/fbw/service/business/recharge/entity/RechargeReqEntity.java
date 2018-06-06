package com.fbw.service.business.recharge.entity;

/**
 * 
 * <功能详细描述> 充值请求实体类
 * @author JACK HUANG
 * @version [版本号, 2017年10月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RechargeReqEntity
{

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 充值类型
     */
    private String rechargeType;

    /**
     * 充值金额
     */
    private String rechargeMoney;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 请求IP
     */
    private String requestIp;

    /**
     * 跟踪ID
     */
    private String trackId;

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    public String getRequestIp()
    {
        return requestIp;
    }

    public void setRequestIp(String requestIp)
    {
        this.requestIp = requestIp;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getRechargeType()
    {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType)
    {
        this.rechargeType = rechargeType;
    }

    public String getRechargeMoney()
    {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney)
    {
        this.rechargeMoney = rechargeMoney;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RechargeReqEntity [mobile=").append(mobile).append(", userId=").append(userId)
                .append(", rechargeType=").append(rechargeType).append(", rechargeMoney=").append(rechargeMoney)
                .append(", cityId=").append(cityId).append(", requestIp=").append(requestIp).append("]");
        return builder.toString();
    }

}
