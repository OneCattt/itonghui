package com.fbw.service.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户订单实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserOrderInfoBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家地址
     */
    private String address;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 输入金额
     */
    private BigDecimal inputAmount;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 商家实收金额
     */
    private BigDecimal shopAmount;

    /**
     * 实付金额
     */
    private BigDecimal actualAmount;

    /**
     * 翻倍金额
     */
    private BigDecimal doubleAmount;

    /**
     * 红包金额
     */
    private BigDecimal redEnvelopeAmount;

    /**
     * 红包id
     */
    private Integer redEnvelopeId;

    /**
     * 评论状态
     */
    private Integer commentStatus;

    /**
     * 是否结算
     */
    private Integer status;

    /**
     * 商家收款人ID
     */
    private Integer shopuserId;

    /**
     * 是否结款
     */
    private Integer accountStatus;

    /**
     * 结款时间
     */
    private String accountTime;

    /**
     * 结款金额
     */
    private BigDecimal accountAmount;

    /**
     * 退款状态
     */
    private Integer refundStatus;

    /**
     * 交易前余额
     */
    private BigDecimal startBalance;

    /**
     * 交易后余额
     */
    private BigDecimal lastBalance;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 维护人id
     */
    private Integer maintainerId;

    /**
     * 翻贝花状态
     */
    private String doubleFlowerStatus;

    /**
     * 预留字段
     */
    private String info;

    /**
     * 更新时间
     */
    private String createdAt;

    /**
     * 创建时间
     */
    private String updatedAt;

    /**
     * 跟踪ID
     */
    private String trackId;

    /**
     * 每家店铺订单量
     */
    private Integer everyShopOrder;

    /**
     * 一级分类id
     */
    private Integer firstClassId;

    /**
     * 二级分类id
     */
    private Integer secondClassId;

    /**
     * 一级分类名称
     */
    private String firstClassName;

    /**
     * 二级分类名称
     */
    private String secondClassName;

    /**
     * 支付时间
     */
    private String payDate;

    /**
     * 支付类型
     */
    private String payType;

    public Integer getCommentStatus()
    {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus)
    {
        this.commentStatus = commentStatus;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getAccountStatus()
    {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus)
    {
        this.accountStatus = accountStatus;
    }

    public Integer getRefundStatus()
    {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus)
    {
        this.refundStatus = refundStatus;
    }

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

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderAmount()
    {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getShopAmount()
    {
        return shopAmount;
    }

    public void setShopAmount(BigDecimal shopAmount)
    {
        this.shopAmount = shopAmount;
    }

    public BigDecimal getActualAmount()
    {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount)
    {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getRedEnvelopeAmount()
    {
        return redEnvelopeAmount;
    }

    public void setRedEnvelopeAmount(BigDecimal redEnvelopeAmount)
    {
        this.redEnvelopeAmount = redEnvelopeAmount;
    }

    public Integer getRedEnvelopeId()
    {
        return redEnvelopeId;
    }

    public void setRedEnvelopeId(Integer redEnvelopeId)
    {
        this.redEnvelopeId = redEnvelopeId;
    }

    public Integer getShopuserId()
    {
        return shopuserId;
    }

    public void setShopuserId(Integer shopuserId)
    {
        this.shopuserId = shopuserId;
    }

    public String getAccountTime()
    {
        return accountTime;
    }

    public void setAccountTime(String accountTime)
    {
        this.accountTime = accountTime;
    }

    public BigDecimal getAccountAmount()
    {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount)
    {
        this.accountAmount = accountAmount;
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

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public Integer getMaintainerId()
    {
        return maintainerId;
    }

    public void setMaintainerId(Integer maintainerId)
    {
        this.maintainerId = maintainerId;
    }

    public String getDoubleFlowerStatus()
    {
        return doubleFlowerStatus;
    }

    public void setDoubleFlowerStatus(String doubleFlowerStatus)
    {
        this.doubleFlowerStatus = doubleFlowerStatus;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getDoubleAmount()
    {
        return doubleAmount;
    }

    public void setDoubleAmount(BigDecimal doubleAmount)
    {
        this.doubleAmount = doubleAmount;
    }

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    public String getPayDate()
    {
        return payDate;
    }

    public void setPayDate(String payDate)
    {
        this.payDate = payDate;
    }

    public String getPayType()
    {
        return payType;
    }

    public void setPayType(String payType)
    {
        this.payType = payType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Integer getEveryShopOrder()
    {
        return everyShopOrder;
    }

    public void setEveryShopOrder(Integer everyShopOrder)
    {
        this.everyShopOrder = everyShopOrder;
    }

    public Integer getSecondClassId()
    {
        return secondClassId;
    }

    public void setSecondClassId(Integer secondClassId)
    {
        this.secondClassId = secondClassId;
    }

    public Integer getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(Integer firstClassId)
    {
        this.firstClassId = firstClassId;
    }

    public String getFirstClassName()
    {
        return firstClassName;
    }

    public void setFirstClassName(String firstClassName)
    {
        this.firstClassName = firstClassName;
    }

    public String getSecondClassName()
    {
        return secondClassName;
    }

    public void setSecondClassName(String secondClassName)
    {
        this.secondClassName = secondClassName;
    }

    public BigDecimal getInputAmount()
    {
        return inputAmount;
    }

    public void setInputAmount(BigDecimal inputAmount)
    {
        this.inputAmount = inputAmount;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("UserOrderInfoBusinEntity [id=").append(id).append(", userId=").append(userId)
                .append(", shopId=").append(shopId).append(", name=").append(name).append(", address=").append(address)
                .append(", orderNumber=").append(orderNumber).append(", inputAmount=").append(inputAmount)
                .append(", orderAmount=").append(orderAmount).append(", shopAmount=").append(shopAmount)
                .append(", actualAmount=").append(actualAmount).append(", doubleAmount=").append(doubleAmount)
                .append(", redEnvelopeAmount=").append(redEnvelopeAmount).append(", redEnvelopeId=")
                .append(redEnvelopeId).append(", commentStatus=").append(commentStatus).append(", status=")
                .append(status).append(", shopuserId=").append(shopuserId).append(", accountStatus=")
                .append(accountStatus).append(", accountTime=").append(accountTime).append(", accountAmount=")
                .append(accountAmount).append(", refundStatus=").append(refundStatus).append(", startBalance=")
                .append(startBalance).append(", lastBalance=").append(lastBalance).append(", cityId=").append(cityId)
                .append(", maintainerId=").append(maintainerId).append(", doubleFlowerStatus=")
                .append(doubleFlowerStatus).append(", info=").append(info).append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt).append(", trackId=").append(trackId)
                .append(", everyShopOrder=").append(everyShopOrder).append(", firstClassId=").append(firstClassId)
                .append(", secondClassId=").append(secondClassId).append(", firstClassName=").append(firstClassName)
                .append(", secondClassName=").append(secondClassName).append(", payDate=").append(payDate)
                .append(", payType=").append(payType).append("]");
        return builder.toString();
    }

}