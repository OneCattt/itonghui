package com.fbw.service.business.paybill.entity;

/**
 * 
 * <功能详细描述>买单入参实体类
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PayEntity
{

    /**
     * 支付类型 1:表示全天翻倍; 2:表示限时翻倍; 3:表示1/2翻倍花; 4:表示1/3翻倍花;
     */
    private String payType;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 订单金额
     */
    private String orderMoney;

    /**
     * 商家ID
     */
    private String shopId;

    /**
     * 支付密码
     */
    private String payPassWord;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 跟踪ID
     */
    private String trackId;

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getPayType()
    {
        return payType;
    }

    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getPayPassWord()
    {
        return payPassWord;
    }

    public void setPayPassWord(String payPassWord)
    {
        this.payPassWord = payPassWord;
    }

    public String getOrderMoney()
    {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney)
    {
        this.orderMoney = orderMoney;
    }

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("PayEntity [payType=").append(payType).append(", mobile=").append(mobile).append(", orderMoney=")
                .append(orderMoney).append(", shopId=").append(shopId).append(", payPassWord=").append(payPassWord)
                .append(", cityId=").append(cityId).append(", userId=").append(userId).append(", trackId=")
                .append(trackId).append("]");
        return builder.toString();
    }

}
