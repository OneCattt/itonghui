package com.fbw.service.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户积分明细 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月19日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserPointDetailEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 类型（1:获得积分，2:消耗积分）
     */
    private Integer type;

    /**
     * 说明（1:消费买单，2:评论十字以上,3:带图评论,4:带图评论以及十字以上,5:消费买单会员日双倍，6:积分兑换奖品,7:大转盘抽奖，8:优质评论）
     */
    private String remark;

    /**
     * 积分变动
     */
    private Integer pointChange;

    /**
     * 变动后的积分
     */
    private Integer afterChangePoint;

    /**
     * 订单金额
     */
    private String orderAmount;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

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

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Integer getPointChange()
    {
        return pointChange;
    }

    public void setPointChange(Integer pointChange)
    {
        this.pointChange = pointChange;
    }

    public Integer getAfterChangePoint()
    {
        return afterChangePoint;
    }

    public void setAfterChangePoint(Integer afterChangePoint)
    {
        this.afterChangePoint = afterChangePoint;
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
        UserPointDetailEntity other = (UserPointDetailEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getPointChange() == null ? other.getPointChange() == null
                        : this.getPointChange().equals(other.getPointChange()))
                && (this.getAfterChangePoint() == null ? other.getAfterChangePoint() == null
                        : this.getAfterChangePoint().equals(other.getAfterChangePoint()))
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
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getPointChange() == null) ? 0 : getPointChange().hashCode());
        result = prime * result + ((getAfterChangePoint() == null) ? 0 : getAfterChangePoint().hashCode());
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
        sb.append(", type=").append(type);
        sb.append(", remark=").append(remark);
        sb.append(", pointChange=").append(pointChange);
        sb.append(", afterChangePoint=").append(afterChangePoint);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getOrderAmount()
    {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount)
    {
        this.orderAmount = orderAmount;
    }
}