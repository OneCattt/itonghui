package com.fbw.service.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述> 用户已退款订单实体类
 * @author jiangruliang
 * @version [版本号, 2017年8月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserRefundOrderInfoBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 退款金额
     */
    private BigDecimal money;

    /**
     * 退款前余额
     */
    private BigDecimal startBalance;

    /**
     * 退款后余额
     */
    private BigDecimal lastBalance;

    /**
     * 红包变更信息
     */
    private String redEnvelopeInfo;

    /**
     * 退款备注
     */
    private String remark;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 操作人id
     */
    private Integer adminId;

    private Date createdAt;

    private Date updatedAt;

    private Integer trackId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public Integer getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public BigDecimal getStartBalance()
    {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance)
    {
        this.startBalance = startBalance;
    }

    public BigDecimal getLastBalance()
    {
        return lastBalance;
    }

    public void setLastBalance(BigDecimal lastBalance)
    {
        this.lastBalance = lastBalance;
    }

    public String getRedEnvelopeInfo()
    {
        return redEnvelopeInfo;
    }

    public void setRedEnvelopeInfo(String redEnvelopeInfo)
    {
        this.redEnvelopeInfo = redEnvelopeInfo;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
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
        return "UserRefundOrderInfoBusinEntity [id=" + id + ", userId=" + userId + ", orderId=" + orderId
                + ", orderNumber=" + orderNumber + ", money=" + money + ", startBalance=" + startBalance
                + ", lastBalance=" + lastBalance + ", redEnvelopeInfo=" + redEnvelopeInfo + ", remark=" + remark
                + ", status=" + status + ", adminId=" + adminId + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", trackId=" + trackId + "]";
    }

}