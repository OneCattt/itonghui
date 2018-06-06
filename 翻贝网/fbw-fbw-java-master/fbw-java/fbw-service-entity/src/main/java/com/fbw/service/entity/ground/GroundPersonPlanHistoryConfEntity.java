package com.fbw.service.entity.ground;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 个人地推方案历史配置表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_person_plan_history_conf")
@JsonInclude(Include.NON_NULL)
public class GroundPersonPlanHistoryConfEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * 个人地推id
     */
    @Column(name = "salesman_id")
    private String salesmanId;

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
     * 地推结算描述
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
    private String effectDate;

    /**
     * 失效时间
     */
    @Column(name = "invalid_date")
    private String invalidDate;

    /**
     * 地推收入
     */
    @Column(name = "ground_fee")
    private BigDecimal groundFee;

    /**
     * 地推方案类型
     */
    @Column(name = "groud_scheme_type")
    private String groundSchemeType;

    // getSet数据
    /**
     * 地推人员姓名
     */
    private String salesmanName;

    /**
     * 地推人员二维码
     */
    private String salesmanQrCode;

    /**
     * 注册数量
     */
    private String registerAmount;

    /**
     * 地推人员状态
     */
    private String status;

    /**
     * 地推总费用
     */
    private BigDecimal groundTotalFee;

    private static final long serialVersionUID = 1L;

    public Integer getId()
    {
        return id;
    }

    public String getGroundSchemeType()
    {
        return groundSchemeType;
    }

    public void setGroundSchemeType(String groundSchemeType)
    {
        this.groundSchemeType = groundSchemeType;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getSalesmanId()
    {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId)
    {
        this.salesmanId = salesmanId;
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

    public String getEffectDate()
    {
        return effectDate;
    }

    public void setEffectDate(String effectDate)
    {
        this.effectDate = effectDate;
    }

    public String getInvalidDate()
    {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate)
    {
        this.invalidDate = invalidDate;
    }

    @Override
    public String toString()
    {
        return "GroundPersonPlanHistoryConfEntity [id=" + id + ", salesmanId=" + salesmanId + ", groundId=" + groundId
                + ", balanceCycleType=" + balanceCycleType + ", balanceDescribe=" + balanceDescribe
                + ", groundBalanceDescribe=" + groundBalanceDescribe + ", groundName=" + groundName
                + ", validGroundDescribe=" + validGroundDescribe + ", effectDate=" + effectDate + ", invalidDate="
                + invalidDate + ", groundFee=" + groundFee + ", groundSchemeType=" + groundSchemeType + "]";
    }

    public BigDecimal getGroundFee()
    {
        return groundFee;
    }

    public void setGroundFee(BigDecimal groundFee)
    {
        this.groundFee = groundFee;
    }

    public String getSalesmanName()
    {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName)
    {
        this.salesmanName = salesmanName;
    }

    public String getSalesmanQrCode()
    {
        return salesmanQrCode;
    }

    public void setSalesmanQrCode(String salesmanQrCode)
    {
        this.salesmanQrCode = salesmanQrCode;
    }

    public String getRegisterAmount()
    {
        return registerAmount;
    }

    public void setRegisterAmount(String registerAmount)
    {
        this.registerAmount = registerAmount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public BigDecimal getGroundTotalFee()
    {
        return groundTotalFee;
    }

    public void setGroundTotalFee(BigDecimal groundTotalFee)
    {
        this.groundTotalFee = groundTotalFee;
    }
}