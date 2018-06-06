package com.fbw.service.business.ground.entity;

/**
 * 
 * <功能详细描述> 地推信息
 * @author JACK HUANG
 * @version [版本号, 2017年9月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GroundInfoEntity
{

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地推人员姓名
     */
    private String salesManName;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 商户ID
     */
    private String shopId;

    /**
     * 商户地址
     */
    private String shopAdress;

    public String getShopAdress()
    {
        return shopAdress;
    }

    public void setShopAdress(String shopAdress)
    {
        this.shopAdress = shopAdress;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getSalesManName()
    {
        return salesManName;
    }

    public void setSalesManName(String salesManName)
    {
        this.salesManName = salesManName;
    }

}
