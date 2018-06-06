package com.fbw.service.entity.recharge;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <功能详细描述> 充值实体类
 * @author JACK HUANG
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_recharge_busin")
public class RechargeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 充值金额
     */
    @Column(name = "recharge_fee")
    private BigDecimal money;

    /**
     * 服务费用
     */
    @Column(name = "service_fee")
    private BigDecimal serviceFee;

    /**
     * 实付金额
     */
    @Column(name = "actual_fee")
    private BigDecimal actualMoney;

    /**
     * 充值状态
     */
    @Column(name = "recharge_status")
    private String status;

    /**
     * 充值来源
     */
    @Column(name = "recharge_source")
    private String source;

    /**
     * 充值号码
     */
    @Column(name = "recharge_mobile")
    private String mobilePhone;

    /**
     * 充值订单号码
     */
    @Column(name = "order_number")
    private String orderNum;

    /**
     * 支付商家反馈订单号
     */
    @Column(name = "shop_feedback_number")
    private String payShopNum;

    /**
     * 翻贝金额
     */
    @Column(name = "double_fee")
    private String doubleMoney;

    /**
     * 用户充值来源
     */
    @Column(name = "user_order_source")
    private String userOrderSource;

    /**
     * 城市ID
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 支付成功时间
     */
    @Column(name = "pay_success_date")
    private String payDate;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getUserOrderSource()
    {
        return userOrderSource;
    }

    public void setUserOrderSource(String userOrderSource)
    {
        this.userOrderSource = userOrderSource;
    }

    public BigDecimal getServiceFee()
    {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee)
    {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getActualMoney()
    {
        return actualMoney;
    }

    public void setActualMoney(BigDecimal actualMoney)
    {
        this.actualMoney = actualMoney;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getPayShopNum()
    {
        return payShopNum;
    }

    public void setPayShopNum(String payShopNum)
    {
        this.payShopNum = payShopNum;
    }

    public String getDoubleMoney()
    {
        return doubleMoney;
    }

    public void setDoubleMoney(String doubleMoney)
    {
        this.doubleMoney = doubleMoney;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public String getPayDate()
    {
        return payDate;
    }

    public void setPayDate(String payDate)
    {
        this.payDate = payDate;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RechargeEntity [id=").append(id).append(", userId=").append(userId).append(", money=")
                .append(money).append(", serviceFee=").append(serviceFee).append(", actualMoney=").append(actualMoney)
                .append(", status=").append(status).append(", source=").append(source).append(", mobilePhone=")
                .append(mobilePhone).append(", orderNum=").append(orderNum).append(", payShopNum=").append(payShopNum)
                .append(", doubleMoney=").append(doubleMoney).append(", userOrderSource=").append(userOrderSource)
                .append(", cityId=").append(cityId).append(", payDate=").append(payDate).append("]");
        return builder.toString();
    }

}
