package com.fbw.service.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户余额明细 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月19日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserBalanceDetailEntity implements Serializable
{
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单号、充值订单号，退款订单号
     */
    private String groupId;

    /**
     * 类型(1.全天翻贝花，2.限时翻贝花，3.1/2翻贝花，4.1/3翻贝花，5.买单，6.订单退款，7.充值，8.充值退款)
     */
    private String type;

    /**
     * 余额变动
     */
    private BigDecimal balanceChange;

    /**
     * 变动后的余额
     */
    private BigDecimal afterBalanceChange;

    /**
     * 消费门店
     */
    private Integer shopId;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;

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

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BigDecimal getBalanceChange()
    {
        return balanceChange;
    }

    public void setBalanceChange(BigDecimal balanceChange)
    {
        this.balanceChange = balanceChange;
    }

    public BigDecimal getAfterBalanceChange()
    {
        return afterBalanceChange;
    }

    public void setAfterBalanceChange(BigDecimal afterBalanceChange)
    {
        this.afterBalanceChange = afterBalanceChange;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
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

    @Override
    public boolean equals(Object that)
    {
        if (this == that)
        {
            return true;
        }
        if (that == null)
        {
            return false;
        }
        if (getClass() != that.getClass())
        {
            return false;
        }
        UserBalanceDetailEntity other = (UserBalanceDetailEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getGroupId() == null ? other.getGroupId() == null
                        : this.getGroupId().equals(other.getGroupId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getBalanceChange() == null ? other.getBalanceChange() == null
                        : this.getBalanceChange().equals(other.getBalanceChange()))
                && (this.getAfterBalanceChange() == null ? other.getAfterBalanceChange() == null
                        : this.getAfterBalanceChange().equals(other.getAfterBalanceChange()))
                && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null
                        : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null
                        : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getBalanceChange() == null) ? 0 : getBalanceChange().hashCode());
        result = prime * result + ((getAfterBalanceChange() == null) ? 0 : getAfterBalanceChange().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", groupId=").append(groupId);
        sb.append(", type=").append(type);
        sb.append(", balanceChange=").append(balanceChange);
        sb.append(", afterBalanceChange=").append(afterBalanceChange);
        sb.append(", shopId=").append(shopId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}