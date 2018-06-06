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
 * 地推方案规则配置表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_ground_plan_conf")
@JsonInclude(Include.NON_NULL)
public class GroundPlanConfEntity implements Serializable
{
    /**
     * 地推方案id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ground_id")
    private Integer groundId;

    @Column(name = "ground_name")
    private String groundName;

    /**
     * 城市id
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 地推方案类型
     */
    @Column(name = "groud_scheme_type")
    private String groudSchemeType;

    /**
     * 地推类型1：个人地推 2：商家地推
     */
    @Column(name = "ground_type")
    private String groundType;

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
     * 有效地推定义
     */
    @Column(name = "validate_ground_define")
    private String validateGroundDefine;

    /**
     * 地推结算描述
     */
    @Column(name = "ground_balance_describe")
    private String groundBalanceDescribe;

    /**
     * 有效地推的注册用户金额
     */
    @Column(name = "valid_register_fee")
    private BigDecimal validRegisterFee;

    /**
     * 每笔消费实付金额限制
     */
    @Column(name = "consume_activity_fee_limit")
    private BigDecimal consumeActivityFeeLimit;

    /**
     * 每笔订单计件金额
     */
    @Column(name = "each_order_piece_fee")
    private BigDecimal eachOrderPieceFee;

    /**
     * 每笔消费按实付金额百分比
     */
    @Column(name = "each_activity_fee_piece_percent")
    private BigDecimal eachActivityFeePiecePercent;

    /**
     * 用户首笔充值金额限制
     */
    @Column(name = "each_first_recharge_fee_limit")
    private BigDecimal eachFirstRechargeFeeLimit;

    /**
     * 每笔充值订单计件金额
     */
    @Column(name = "each_recharge_pirce_fee")
    private BigDecimal eachRechargePirceFee;

    /**
     * 用户充值金额限制
     */
    @Column(name = "each_recharge_fee_limit")
    private BigDecimal eachRechargeFeeLimit;

    /**
     * 之前用户充值数量
     */
    @Column(name = "user_recharge_amount_before")
    private BigDecimal userRechargeAmountBefore;

    /**
     * 之前注册用户数量
     */
    @Column(name = "register_amount_before")
    private BigDecimal registerAmountBefore;

    /**
     * 之后注册用户数量
     */
    @Column(name = "register_amount_after")
    private BigDecimal registerAmountAfter;

    /**
     * 之前注册用户金额
     */
    @Column(name = "register_fee_before")
    private BigDecimal registerFeeBefore;

    /**
     * 之后注册用户金额
     */
    @Column(name = "register_fee_after")
    private BigDecimal registerFeeAfter;

    /**
     * 之后每笔消费按实付金额百分比
     */
    @Column(name = "each_order_piece_percent_after")
    private BigDecimal eachOrderPiecePercentAfter;

    /**
     * 之前每笔消费按实付金额百分比
     */
    @Column(name = "each_order_piece_percent_Before")
    private BigDecimal eachOrderPiecePercentBefore;

    /**
     * 地推人员数量
     */
    @Column(name = "groud_salesman_amount")
    private BigDecimal groudSalesmanAmount;

    /**
     * 状态（0：为生效 1：为失效）
     */
    @Column(name = "status")
    private String status;

    /**
     * 生效时间
     */
    @Column(name = "effect_date")
    private String effectDate;

    /**
     * 失效时间
     */
    @Column(name = "invalid_date")
    private Date invalidDate;

    /**
     * 更新时间
     */
    @Column(name = "updated_date")
    private String updatedDate;

    /********************************************************************************/
    /**
     * 地推人员数量
     */
    private Integer salesmanNumber;

    private static final long serialVersionUID = 1L;

    public Integer getGroundId()
    {
        return groundId;
    }

    public void setGroundId(Integer groundId)
    {
        this.groundId = groundId;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getGroudSchemeType()
    {
        return groudSchemeType;
    }

    public void setGroudSchemeType(String groudSchemeType)
    {
        this.groudSchemeType = groudSchemeType;
    }

    public String getGroundType()
    {
        return groundType;
    }

    public void setGroundType(String groundType)
    {
        this.groundType = groundType;
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

    public String getValidateGroundDefine()
    {
        return validateGroundDefine;
    }

    public void setValidateGroundDefine(String validateGroundDefine)
    {
        this.validateGroundDefine = validateGroundDefine;
    }

    public String getGroundBalanceDescribe()
    {
        return groundBalanceDescribe;
    }

    public void setGroundBalanceDescribe(String groundBalanceDescribe)
    {
        this.groundBalanceDescribe = groundBalanceDescribe;
    }

    public BigDecimal getValidRegisterFee()
    {
        return validRegisterFee;
    }

    public void setValidRegisterree(BigDecimal validRegisterFee)
    {
        this.validRegisterFee = validRegisterFee;
    }

    public String getGroundName()
    {
        return groundName;
    }

    public void setGroundName(String groundName)
    {
        this.groundName = groundName;
    }

    public BigDecimal getConsumeActivityFeeLimit()
    {
        return consumeActivityFeeLimit;
    }

    public void setConsumeActivityFeeLimit(BigDecimal consumeActivityFeeLimit)
    {
        this.consumeActivityFeeLimit = consumeActivityFeeLimit;
    }

    public BigDecimal getEachOrderPieceFee()
    {
        return eachOrderPieceFee;
    }

    public void setEachOrderPieceFee(BigDecimal eachOrderPieceFee)
    {
        this.eachOrderPieceFee = eachOrderPieceFee;
    }

    public BigDecimal getEachActivityFeePiecePercent()
    {
        return eachActivityFeePiecePercent;
    }

    public void setEachActivityFeePiecePercent(BigDecimal eachActivityFeePiecePercent)
    {
        this.eachActivityFeePiecePercent = eachActivityFeePiecePercent;
    }

    public BigDecimal getEachFirstRechargeFeeLimit()
    {
        return eachFirstRechargeFeeLimit;
    }

    public void setEachFirstRechargeFeeLimit(BigDecimal eachFirstRechargeFeeLimit)
    {
        this.eachFirstRechargeFeeLimit = eachFirstRechargeFeeLimit;
    }

    public BigDecimal getEachRechargePirceFee()
    {
        return eachRechargePirceFee;
    }

    public void setEachRechargePirceFee(BigDecimal eachRechargePirceFee)
    {
        this.eachRechargePirceFee = eachRechargePirceFee;
    }

    public BigDecimal getEachRechargeFeeLimit()
    {
        return eachRechargeFeeLimit;
    }

    public void setEachRechargeFeeLimit(BigDecimal eachRechargeFeeLimit)
    {
        this.eachRechargeFeeLimit = eachRechargeFeeLimit;
    }

    public String getEffectDate()
    {
        return effectDate;
    }

    public void setEffectDate(String effectDate)
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

    public String getUpdatedDate()
    {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    public BigDecimal getRegisterAmountBefore()
    {
        return registerAmountBefore;
    }

    public void setRegisterAmountBefore(BigDecimal registerAmountBefore)
    {
        this.registerAmountBefore = registerAmountBefore;
    }

    public BigDecimal getRegisterAmountAfter()
    {
        return registerAmountAfter;
    }

    public void setRegisterAmountAfter(BigDecimal registerAmountAfter)
    {
        this.registerAmountAfter = registerAmountAfter;
    }

    public BigDecimal getRegisterFeeBefore()
    {
        return registerFeeBefore;
    }

    public void setRegisterFeeBefore(BigDecimal registerFeeBefore)
    {
        this.registerFeeBefore = registerFeeBefore;
    }

    public BigDecimal getRegisterFeeAfter()
    {
        return registerFeeAfter;
    }

    public void setRegisterFeeAfter(BigDecimal registerFeeAfter)
    {
        this.registerFeeAfter = registerFeeAfter;
    }

    public BigDecimal getGroudSalesmanAmount()
    {
        return groudSalesmanAmount;
    }

    public void setGroudSalesmanAmount(BigDecimal groudSalesmanAmount)
    {
        this.groudSalesmanAmount = groudSalesmanAmount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setValidRegisterFee(BigDecimal validRegisterFee)
    {
        this.validRegisterFee = validRegisterFee;
    }

    public BigDecimal getEachOrderPiecePercentAfter()
    {
        return eachOrderPiecePercentAfter;
    }

    public void setEachOrderPiecePercentAfter(BigDecimal eachOrderPiecePercentAfter)
    {
        this.eachOrderPiecePercentAfter = eachOrderPiecePercentAfter;
    }

    public BigDecimal getEachOrderPiecePercentBefore()
    {
        return eachOrderPiecePercentBefore;
    }

    public void setEachOrderPiecePercentBefore(BigDecimal eachOrderPiecePercentBefore)
    {
        this.eachOrderPiecePercentBefore = eachOrderPiecePercentBefore;
    }

    public BigDecimal getUserRechargeAmountBefore()
    {
        return userRechargeAmountBefore;
    }

    public void setUserRechargeAmountBefore(BigDecimal userRechargeAmountBefore)
    {
        this.userRechargeAmountBefore = userRechargeAmountBefore;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("GroundPlanConfEntity [groundId=").append(groundId).append(", cityId=").append(cityId)
                .append(", groundName=").append(groundName).append(", groudSchemeType=").append(groudSchemeType)
                .append(", groundType=").append(groundType).append(", balanceCycleType=").append(balanceCycleType)
                .append(", balanceDescribe=").append(balanceDescribe).append(", validateGroundDefine=")
                .append(validateGroundDefine).append(", groundBalanceDescribe=").append(groundBalanceDescribe)
                .append(", validRegisterFee=").append(validRegisterFee).append(", consumeActivityFeeLimit=")
                .append(consumeActivityFeeLimit).append(", eachOrderPieceFee=").append(eachOrderPieceFee)
                .append(", eachActivityFeePiecePercent=").append(eachActivityFeePiecePercent)
                .append(", eachFirstRechargeFeeLimit=").append(eachFirstRechargeFeeLimit)
                .append(", eachRechargePirceFee=").append(eachRechargePirceFee).append(", eachRechargeFeeLimit=")
                .append(eachRechargeFeeLimit).append(", userRechargeAmountBefore=").append(userRechargeAmountBefore)
                .append(", registerAmountBefore=").append(registerAmountBefore).append(", registerAmountAfter=")
                .append(registerAmountAfter).append(", registerFeeBefore=").append(registerFeeBefore)
                .append(", registerFeeAfter=").append(registerFeeAfter).append(", eachOrderPiecePercentAfter=")
                .append(eachOrderPiecePercentAfter).append(", eachOrderPiecePercentBefore=")
                .append(eachOrderPiecePercentBefore).append(", groudSalesmanAmount=").append(groudSalesmanAmount)
                .append(", status=").append(status).append(", effectDate=").append(effectDate).append(", invalidDate=")
                .append(invalidDate).append(", updatedDate=").append(updatedDate).append("]");
        return builder.toString();
    }

    public Integer getSalesmanNumber()
    {
        return salesmanNumber;
    }

    public void setSalesmanNumber(Integer salesmanNumber)
    {
        this.salesmanNumber = salesmanNumber;
    }

}