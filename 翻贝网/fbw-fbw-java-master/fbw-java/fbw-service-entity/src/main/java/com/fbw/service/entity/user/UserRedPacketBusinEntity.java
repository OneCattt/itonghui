package com.fbw.service.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户红包表实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserRedPacketBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 红包金额
     */
    private BigDecimal amount;

    /**
     * 最低使用金额
     */
    private BigDecimal minimumAmount;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 城市姓名
     */
    private String cityName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 截止时间
     */
    private String endTime;

    /**
     * 使用时间
     */
    private String useTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 类型
     */
    private String type;

    /**
     * 总部红包金额
     */
    private BigDecimal hqAmount;

    /**
     * 代理商红包金额
     */
    private BigDecimal agentAmount;

    /**
     * 一级分类
     */
    private Integer firstClassId;

    /**
     * 二级分类
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
     * 领取位置，首页、充值、买单等
     */
    private String position;

    /**
     * 领取位置，首页、充值、买单等 id
     */
    private String positionId;

    /**
     * 每天使用时段：开始
     */
    private String evrydayUseTimeStart;

    /**
     * 每天使用时段：结束
     */
    private String evrydayUseTimeEnd;

    /**
     * 是否有使用时段（0：没有，1：有）
     */
    private String isEvrydayUserTime;

    /**
     * 是否限制使用日期
     */
    private String isLimitUseDay;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
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

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getMinimumAmount()
    {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount)
    {
        this.minimumAmount = minimumAmount;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getUseTime()
    {
        return useTime;
    }

    public void setUseTime(String useTime)
    {
        this.useTime = useTime;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BigDecimal getHqAmount()
    {
        return hqAmount;
    }

    public void setHqAmount(BigDecimal hqAmount)
    {
        this.hqAmount = hqAmount;
    }

    public BigDecimal getAgentAmount()
    {
        return agentAmount;
    }

    public void setAgentAmount(BigDecimal agentAmount)
    {
        this.agentAmount = agentAmount;
    }

    public Integer getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(Integer firstClassId)
    {
        this.firstClassId = firstClassId;
    }

    public Integer getSecondClassId()
    {
        return secondClassId;
    }

    public void setSecondClassId(Integer secondClassId)
    {
        this.secondClassId = secondClassId;
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

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getEvrydayUseTimeStart()
    {
        return evrydayUseTimeStart;
    }

    public void setEvrydayUseTimeStart(String evrydayUseTimeStart)
    {
        this.evrydayUseTimeStart = evrydayUseTimeStart;
    }

    public String getEvrydayUseTimeEnd()
    {
        return evrydayUseTimeEnd;
    }

    public void setEvrydayUseTimeEnd(String evrydayUseTimeEnd)
    {
        this.evrydayUseTimeEnd = evrydayUseTimeEnd;
    }

    public String getIsEvrydayUserTime()
    {
        return isEvrydayUserTime;
    }

    public void setIsEvrydayUserTime(String isEvrydayUserTime)
    {
        this.isEvrydayUserTime = isEvrydayUserTime;
    }

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
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

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getPositionId()
    {
        return positionId;
    }

    public void setPositionId(String positionId)
    {
        this.positionId = positionId;
    }

    public String getIsLimitUseDay()
    {
        return isLimitUseDay;
    }

    public void setIsLimitUseDay(String isLimitUseDay)
    {
        this.isLimitUseDay = isLimitUseDay;
    }

    @Override
    public String toString()
    {
        return "UserRedPacketBusinEntity [id=" + id + ", userId=" + userId + ", amount=" + amount + ", minimumAmount="
                + minimumAmount + ", shopId=" + shopId + ", cityId=" + cityId + ", startTime=" + startTime
                + ", endTime=" + endTime + ", useTime=" + useTime + ", status=" + status + ", type=" + type
                + ", hqAmount=" + hqAmount + ", agentAmount=" + agentAmount + ", firstClassId=" + firstClassId
                + ", secondClassId=" + secondClassId + ", firstClassName=" + firstClassName + ", secondClassName="
                + secondClassName + ", position=" + position + ", positionId=" + positionId + ", evrydayUseTimeStart="
                + evrydayUseTimeStart + ", evrydayUseTimeEnd=" + evrydayUseTimeEnd + ", isEvrydayUserTime="
                + isEvrydayUserTime + ", isLimitUseDay=" + isLimitUseDay + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", trackId=" + trackId + "]";
    }

}