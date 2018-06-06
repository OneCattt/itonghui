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
 * 商户地推方案历史配置表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_shop_plan_history_conf")
@JsonInclude(Include.NON_NULL)
public class GroundShopPlanHistoryConfEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 商户id
     */
    @Column(name = "shop_id")
    private String shopId;

    /**
     * 缴纳金额
     */
    @Column(name = "pay_money")
    private BigDecimal payMoney;

    /**
     * 地推方案id
     */
    @Column(name = "ground_id")
    private String groundId;

    /**
     * 结算周期类型
     */
    @Column(name = "balance_cycle_type")
    private String balanceCycleType;

    /**
     * 结算周期描述
     */
    @Column(name = "balance_describe")
    private String balanceDescribe;

    /**
     * 地退结算描述
     */
    @Column(name = "ground_balance_describe")
    private String groundBalanceDescribe;

    /**
     * 地推名称
     */
    @Column(name = "ground_name")
    private String groundName;

    /**
     * 有效地推描述
     */
    @Column(name = "valid_ground_describe")
    private String validGroundDescribe;

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

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public BigDecimal getPayMoney()
    {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney)
    {
        this.payMoney = payMoney;
    }

    public String getGroundId()
    {
        return groundId;
    }

    public void setGroundId(String groundId)
    {
        this.groundId = groundId;
    }

    public String getBalanceCycleType()
    {
        return balanceCycleType;
    }

    public void setBalanceCycleType(String balanceCycleType)
    {
        this.balanceCycleType = balanceCycleType;
    }

    public String getBalanceDescribe()
    {
        return balanceDescribe;
    }

    public void setBalanceDescribe(String balanceDescribe)
    {
        this.balanceDescribe = balanceDescribe;
    }

    public String getGroundBalanceDescribe()
    {
        return groundBalanceDescribe;
    }

    public void setGroundBalanceDescribe(String groundBalanceDescribe)
    {
        this.groundBalanceDescribe = groundBalanceDescribe;
    }

    public String getGroundName()
    {
        return groundName;
    }

    public void setGroundName(String groundName)
    {
        this.groundName = groundName;
    }

    public String getValidGroundDescribe()
    {
        return validGroundDescribe;
    }

    public void setValidGroundDescribe(String validGroundDescribe)
    {
        this.validGroundDescribe = validGroundDescribe;
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
        sb.append(", shopId=").append(shopId);
        sb.append(", payMoney=").append(payMoney);
        sb.append(", groundId=").append(groundId);
        sb.append(", balanceCycleType=").append(balanceCycleType);
        sb.append(", balanceDescribe=").append(balanceDescribe);
        sb.append(", groundBalanceDescribe=").append(groundBalanceDescribe);
        sb.append(", groundName=").append(groundName);
        sb.append(", validGroundDescribe=").append(validGroundDescribe);
        sb.append(", effectDate=").append(effectDate);
        sb.append(", invalidDate=").append(invalidDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}