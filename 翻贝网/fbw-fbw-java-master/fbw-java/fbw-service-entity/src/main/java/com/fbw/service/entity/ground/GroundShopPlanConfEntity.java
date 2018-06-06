package com.fbw.service.entity.ground;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 地推商户配置表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_shop_plan_conf")
@JsonInclude(Include.NON_NULL)
public class GroundShopPlanConfEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 地推类型
     */
    @Column(name = "ground_style")
    private String groundStyle;

    /**
     * 每个下载ap注册用户金额
     */
    @Column(name = "every_dowapp_amount")
    private BigDecimal everyDowappAmount;

    /**
     * 限制前多少位用户
     */
    @Column(name = "limit_how_many_user")
    private BigDecimal limitHowManyUser;

    /**
     * 前多少位注册用户每个金额
     */
    @Column(name = "before_every_user_amount")
    private BigDecimal beforeEveryUserAmount;

    /**
     * 之后每个注册用户金额
     */
    @Column(name = "after_every_user_amount")
    private BigDecimal afterEveryUserAmount;

    /**
     * 是否限制前多少位注册用户
     */
    @Column(name = "is_limit_how_many_user")
    private String isLimitHowManyUser;

    /**
     * 订单实付金额百分比
     */
    @Column(name = "order_actual_pay_percent")
    private BigDecimal orderActualPayPercent;

    /**
     * 地推时限
     */
    @Column(name = "ground_time_limit")
    private Integer groundTimeLimit;

    /**
     * 生效时间
     */
    @Column(name = "effect_date")
    private Date effectDate;

    /**
     * 失效时间
     */
    @Column(name = "invalid_date")
    private Date invalidDate;

    private static final long serialVersionUID = 1L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getGroundStyle()
    {
        return groundStyle;
    }

    public void setGroundStyle(String groundStyle)
    {
        this.groundStyle = groundStyle;
    }

    public BigDecimal getEveryDowappAmount()
    {
        return everyDowappAmount;
    }

    public void setEveryDowappAmount(BigDecimal everyDowappAmount)
    {
        this.everyDowappAmount = everyDowappAmount;
    }

    public BigDecimal getLimitHowManyUser()
    {
        return limitHowManyUser;
    }

    public void setLimitHowManyUser(BigDecimal limitHowManyUser)
    {
        this.limitHowManyUser = limitHowManyUser;
    }

    public BigDecimal getBeforeEveryUserAmount()
    {
        return beforeEveryUserAmount;
    }

    public void setBeforeEveryUserAmount(BigDecimal beforeEveryUserAmount)
    {
        this.beforeEveryUserAmount = beforeEveryUserAmount;
    }

    public BigDecimal getAfterEveryUserAmount()
    {
        return afterEveryUserAmount;
    }

    public void setAfterEveryUserAmount(BigDecimal afterEveryUserAmount)
    {
        this.afterEveryUserAmount = afterEveryUserAmount;
    }

    public String getIsLimitHowManyUser()
    {
        return isLimitHowManyUser;
    }

    public void setIsLimitHowManyUser(String isLimitHowManyUser)
    {
        this.isLimitHowManyUser = isLimitHowManyUser;
    }

    public BigDecimal getOrderActualPayPercent()
    {
        return orderActualPayPercent;
    }

    public void setOrderActualPayPercent(BigDecimal orderActualPayPercent)
    {
        this.orderActualPayPercent = orderActualPayPercent;
    }

    public Integer getGroundTimeLimit()
    {
        return groundTimeLimit;
    }

    public void setGroundTimeLimit(Integer groundTimeLimit)
    {
        this.groundTimeLimit = groundTimeLimit;
    }

    public Date getEffectDate()
    {
        return effectDate;
    }

    public void setEffectDate(Date effectDate)
    {
        this.effectDate = effectDate;
    }

    public Date getInvalidDate()
    {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate)
    {
        this.invalidDate = invalidDate;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groundStyle=").append(groundStyle);
        sb.append(", everyDowappAmount=").append(everyDowappAmount);
        sb.append(", limitHowManyUser=").append(limitHowManyUser);
        sb.append(", beforeEveryUserAmount=").append(beforeEveryUserAmount);
        sb.append(", afterEveryUserAmount=").append(afterEveryUserAmount);
        sb.append(", isLimitHowManyUser=").append(isLimitHowManyUser);
        sb.append(", orderActualPayPercent=").append(orderActualPayPercent);
        sb.append(", groundTimeLimit=").append(groundTimeLimit);
        sb.append(", effectDate=").append(effectDate);
        sb.append(", invalidDate=").append(invalidDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}