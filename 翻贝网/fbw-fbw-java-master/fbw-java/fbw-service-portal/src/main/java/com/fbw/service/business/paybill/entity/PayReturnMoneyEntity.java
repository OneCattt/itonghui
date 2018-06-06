package com.fbw.service.business.paybill.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述> 买单返回金额实体类
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class PayReturnMoneyEntity
{
    /**
     * 实付金额
     */
    private double activityPaid;

    /**
     * 节省金额
     */
    private double saveMoney;

    /**
     * 可用红包标志 true为有可用红包, false为无可用红包
     */
    private boolean redPacketFlag;

    /**
     * 红包ID
     */
    private int redPacketId;

    /**
     * 红包金额
     */
    private double redPacketFee;

    /**
     * 地推人员ID
     */
    private Integer salesmanId;

    /**
     * 订单金额
     */
    private double orderMoney;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 买单前余额
     */
    private BigDecimal beforebalance;

    /**
     * 买单后余额
     */
    private BigDecimal afterbalance;

    public BigDecimal getBeforebalance()
    {
        return beforebalance;
    }

    public void setBeforebalance(BigDecimal beforebalance)
    {
        this.beforebalance = beforebalance;
    }

    public BigDecimal getAfterbalance()
    {
        return afterbalance;
    }

    public void setAfterbalance(BigDecimal afterbalance)
    {
        this.afterbalance = afterbalance;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public double getOrderMoney()
    {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney)
    {
        this.orderMoney = orderMoney;
    }

    public Integer getSalesmanId()
    {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId)
    {
        this.salesmanId = salesmanId;
    }

    public int getRedPacketId()
    {
        return redPacketId;
    }

    public void setRedPacketId(int redPacketId)
    {
        this.redPacketId = redPacketId;
    }

    public double getActivityPaid()
    {
        return activityPaid;
    }

    public void setActivityPaid(double activityPaid)
    {
        this.activityPaid = activityPaid;
    }

    public double getSaveMoney()
    {
        return saveMoney;
    }

    public void setSaveMoney(double saveMoney)
    {
        this.saveMoney = saveMoney;
    }

    public boolean isRedPacketFlag()
    {
        return redPacketFlag;
    }

    public void setRedPacketFlag(boolean redPacketFlag)
    {
        this.redPacketFlag = redPacketFlag;
    }

    public double getRedPacketFee()
    {
        return redPacketFee;
    }

    public void setRedPacketFee(double redPacketFee)
    {
        this.redPacketFee = redPacketFee;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("PayReturnMoneyEntity [activityPaid=").append(activityPaid).append(", saveMoney=")
                .append(saveMoney).append(", redPacketFlag=").append(redPacketFlag).append(", redPacketId=")
                .append(redPacketId).append(", redPacketFee=").append(redPacketFee).append(", salesmanId=")
                .append(salesmanId).append(", orderMoney=").append(orderMoney).append(", mobile=").append(mobile)
                .append(", orderNumber=").append(orderNumber).append(", cityId=").append(cityId).append("]");
        return builder.toString();
    }

}
