package com.fbw.service.entity;

import java.math.BigDecimal;

/**
 * 
 * <功能详细描述> 佣金响应实体类
 * @author JACK HUANG
 * @version [版本号, 2017年9月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommissionResEntity
{
    /**
     * 注册佣金
     */
    private BigDecimal registerFee;

    /**
     * 订单佣金
     */
    private BigDecimal orderFee;

    /**
     * 充值佣金
     */
    private BigDecimal rechargeFee;

    /**
     * 注册有效标志
     */
    private boolean vaildRegisterFlag;

    /**
     * 订单有效标志
     */
    private boolean vaildOrderFlag;

    /**
     * 充值有效标志
     */
    private boolean vaildRechargeFlag;

    public boolean isVaildRegisterFlag()
    {
        return vaildRegisterFlag;
    }

    public void setVaildRegisterFlag(boolean vaildRegisterFlag)
    {
        this.vaildRegisterFlag = vaildRegisterFlag;
    }

    public boolean isVaildOrderFlag()
    {
        return vaildOrderFlag;
    }

    public void setVaildOrderFlag(boolean vaildOrderFlag)
    {
        this.vaildOrderFlag = vaildOrderFlag;
    }

    public boolean isVaildRechargeFlag()
    {
        return vaildRechargeFlag;
    }

    public void setVaildRechargeFlag(boolean vaildRechargeFlag)
    {
        this.vaildRechargeFlag = vaildRechargeFlag;
    }

    public BigDecimal getRegisterFee()
    {
        return registerFee;
    }

    public void setRegisterFee(BigDecimal registerFee)
    {
        this.registerFee = registerFee;
    }

    public BigDecimal getOrderFee()
    {
        return orderFee;
    }

    public void setOrderFee(BigDecimal orderFee)
    {
        this.orderFee = orderFee;
    }

    public BigDecimal getRechargeFee()
    {
        return rechargeFee;
    }

    public void setRechargeFee(BigDecimal rechargeFee)
    {
        this.rechargeFee = rechargeFee;
    }

}
