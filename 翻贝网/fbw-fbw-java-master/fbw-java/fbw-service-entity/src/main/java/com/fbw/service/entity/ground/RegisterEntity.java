package com.fbw.service.entity.ground;

/**
 * 
 * <功能详细描述> 注册实体类
 * @author JACK HUANG
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RegisterEntity
{

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地推人员ID
     */
    private String salesManId;

    /**
     * 店员ID
     */
    private String shopAssistantId;

    /**
     * 商户ID
     */
    private String shopId;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 注册状态
     */
    private String registerStatus;

    /**
     * 注册来源
     */
    private String registerResource;

    /**
     * 邀请码标志位 0为有邀请码渠道 1位非邀请码渠道
     */
    private String inviteFlag;

    /**
     * 地推类型
     */
    private String groundType;

    private String money;

    private String orderNumber;

    /**
     * 相同设备ID 0为相同设备 ，1为不同设备
     */
    private String sameDeviceFlag;

    public String getSameDeviceFlag()
    {
        return sameDeviceFlag;
    }

    public void setSameDeviceFlag(String sameDeviceFlag)
    {
        this.sameDeviceFlag = sameDeviceFlag;
    }

    public String getGroundType()
    {
        return groundType;
    }

    public void setGroundType(String groundType)
    {
        this.groundType = groundType;
    }

    public String getInviteFlag()
    {
        return inviteFlag;
    }

    public void setInviteFlag(String inviteFlag)
    {
        this.inviteFlag = inviteFlag;
    }

    public String getRegisterResource()
    {
        return registerResource;
    }

    public void setRegisterResource(String registerResource)
    {
        this.registerResource = registerResource;
    }

    public String getRegisterStatus()
    {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus)
    {
        this.registerStatus = registerStatus;
    }

    public String getShopAssistantId()
    {
        return shopAssistantId;
    }

    public void setShopAssistantId(String shopAssistantId)
    {
        this.shopAssistantId = shopAssistantId;
    }

    public String getSalesManId()
    {
        return salesManId;
    }

    public void setSalesManId(String salesManId)
    {
        this.salesManId = salesManId;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RegisterEntity [mobile=").append(mobile).append(", salesManId=").append(salesManId)
                .append(", shopAssistantId=").append(shopAssistantId).append(", shopId=").append(shopId)
                .append(", cityId=").append(cityId).append(", deviceId=").append(deviceId).append(", money=")
                .append(money).append("]");
        return builder.toString();
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMoney()
    {
        return money;
    }

    public void setMoney(String money)
    {
        this.money = money;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

}
