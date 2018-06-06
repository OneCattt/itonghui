package com.fbw.service.entity;

import java.math.BigDecimal;

/**
 * 
 * <功能详细描述> 充值请求实体类
 * @author JACK HUANG
 * @version [版本号, 2017年10月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RechargeReqEntity
{

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 实付金额
     */
    private BigDecimal actualMoney;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 翻倍金
     */
    private String doubleMoney;

    public String getDoubleMoney()
    {
        return doubleMoney;
    }

    public void setDoubleMoney(String doubleMoney)
    {
        this.doubleMoney = doubleMoney;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public BigDecimal getActualMoney()
    {
        return actualMoney;
    }

    public void setActualMoney(BigDecimal actualMoney)
    {
        this.actualMoney = actualMoney;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RechargeReqEntity [orderNum=").append(orderNum).append(", actualMoney=").append(actualMoney)
                .append(", mobile=").append(mobile).append("]");
        return builder.toString();
    }

}
