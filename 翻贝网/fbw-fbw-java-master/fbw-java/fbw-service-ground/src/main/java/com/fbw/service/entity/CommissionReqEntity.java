package com.fbw.service.entity;

import java.math.BigDecimal;

/**
 * 
 * <功能详细描述> 佣金请求实体类
 * @author JACK HUANG
 * @version [版本号, 2017年9月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommissionReqEntity
{

    /**
     * 地推人员ID
     */
    private String salesManId;

    /**
     * 商户ID
     */
    private String shopId;

    /**
     * 地推类型
     */
    private String groundType;

    /**
     * 订单金额
     */
    private BigDecimal orderMoney;

    /**
     * 充值金额
     */
    private BigDecimal rechargeMoney;

    /**
     * 有效充值数量
     */
    private BigDecimal vaildRecharge;

    /**
     * 有效注册用户数量
     */
    private BigDecimal vaildRegister;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地推方案ID
     */
    private String groundId;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 地推方案类型
     */
    private String groundPlanType;

    public String getGroundPlanType()
    {
        return groundPlanType;
    }

    public void setGroundPlanType(String groundPlanType)
    {
        this.groundPlanType = groundPlanType;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getGroundId()
    {
        return groundId;
    }

    public void setGroundId(String groundId)
    {
        this.groundId = groundId;
    }

    public BigDecimal getVaildRecharge()
    {
        return vaildRecharge;
    }

    public void setVaildRecharge(BigDecimal vaildRecharge)
    {
        this.vaildRecharge = vaildRecharge;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getSalesManId()
    {
        return salesManId;
    }

    public void setSalesManId(String salesManId)
    {
        this.salesManId = salesManId;
    }

    public String getGroundType()
    {
        return groundType;
    }

    public void setGroundType(String groundType)
    {
        this.groundType = groundType;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public BigDecimal getOrderMoney()
    {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney)
    {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getRechargeMoney()
    {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney)
    {
        this.rechargeMoney = rechargeMoney;
    }

    public BigDecimal getVaildRegister()
    {
        return vaildRegister;
    }

    public void setVaildRegister(BigDecimal vaildRegister)
    {
        this.vaildRegister = vaildRegister;
    }

}
