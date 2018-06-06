package com.fbw.service.entity.recharge;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_recharge_log")
public class RechargeLogEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 充值金额
     */
    @Column(name = "recharge_fee")
    private BigDecimal money;

    /**
     * 充值来源
     */
    @Column(name = "recharge_source")
    private String source;

    /**
     * 充值订单号码
     */
    @Column(name = "recharge_order_number")
    private String orderNum;

    /**
     * 充值号码
     */
    @Column(name = "recharge_mobile")
    private String mobilePhone;

    /**
     * 充值错误日志
     */
    @Column(name = "recharge_error_msg")
    private String errorMsg;

    /**
     * 请求报文
     */
    @Column(name = "request_msg")
    private String requestMsg;

    /**
     * 充值状态
     */
    @Column(name = "recharge_status")
    private String rechargeStatus;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getRequestMsg()
    {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg)
    {
        this.requestMsg = requestMsg;
    }

    public String getRechargeStatus()
    {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus)
    {
        this.rechargeStatus = rechargeStatus;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RechargeLogEntity [id=").append(id).append(", money=").append(money).append(", source=")
                .append(source).append(", orderNum=").append(orderNum).append(", mobilePhone=").append(mobilePhone)
                .append(", errorMsg=").append(errorMsg).append(", requestMsg=").append(requestMsg)
                .append(", rechargeStatus=").append(rechargeStatus).append("]");
        return builder.toString();
    }

}
