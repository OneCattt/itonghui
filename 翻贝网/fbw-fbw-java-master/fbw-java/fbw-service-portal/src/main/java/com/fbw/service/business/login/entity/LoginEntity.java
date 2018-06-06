package com.fbw.service.business.login.entity;

/**
 * 
 * <功能详细描述> 登陆实体类
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginEntity
{
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 验证码
     */
    private String code;

    /**
     * 登陆类型 sms为短信登陆，weChat为微信登陆
     */
    private String loginType;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 微信号
     */
    private String openId;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 操作系统版本
     */
    private String osVersion;

    /**
     * 手机类型
     */
    private String phoneType;

    /**
     * 版本编码
     */
    private String versionCode;

    /**
     * 注册来源
     */
    private String registerSource;

    /**
     * 注册地区
     */
    private String registerArea;

    /**
     * 注册IP
     */
    private String ip;

    /**
     * 跟踪ID
     */
    private String trackId;

    /**
     * 邀请码标志位 0为有邀请码 1为没有邀请码
     */
    private String inviteFlag;

    /**
     * 邀请url地址
     */
    private String inviteUrl;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 同一设备判断 0为同一设备 ,1为不是同一设备
     */
    private String sameDeviceFlag;

    /**
     * 苹果或者安卓
     */
    private String iosOrAndroid;

    /**
     * 登陆token
     */
    private String token;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getIosOrAndroid()
    {
        return iosOrAndroid;
    }

    public void setIosOrAndroid(String iosOrAndroid)
    {
        this.iosOrAndroid = iosOrAndroid;
    }

    public String getSameDeviceFlag()
    {
        return sameDeviceFlag;
    }

    public void setSameDeviceFlag(String sameDeviceFlag)
    {
        this.sameDeviceFlag = sameDeviceFlag;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getInviteFlag()
    {
        return inviteFlag;
    }

    public void setInviteFlag(String inviteFlag)
    {
        this.inviteFlag = inviteFlag;
    }

    public String getInviteUrl()
    {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl)
    {
        this.inviteUrl = inviteUrl;
    }

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getRegisterSource()
    {
        return registerSource;
    }

    public void setRegisterSource(String registerSource)
    {
        this.registerSource = registerSource;
    }

    public String getRegisterArea()
    {
        return registerArea;
    }

    public void setRegisterArea(String registerArea)
    {
        this.registerArea = registerArea;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getOsVersion()
    {
        return osVersion;
    }

    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    public String getPhoneType()
    {
        return phoneType;
    }

    public void setPhoneType(String phoneType)
    {
        this.phoneType = phoneType;
    }

    public String getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(String versionCode)
    {
        this.versionCode = versionCode;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getLoginType()
    {
        return loginType;
    }

    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginEntity [mobile=").append(mobile).append(", code=").append(code).append(", loginType=")
                .append(loginType).append(", userName=").append(userName).append(", openId=").append(openId)
                .append(", deviceId=").append(deviceId).append(", osVersion=").append(osVersion).append(", phoneType=")
                .append(phoneType).append(", versionCode=").append(versionCode).append(", registerSource=")
                .append(registerSource).append(", registerArea=").append(registerArea).append(", ip=").append(ip)
                .append(", trackId=").append(trackId).append(", inviteFlag=").append(inviteFlag).append(", inviteUrl=")
                .append(inviteUrl).append(", cityId=").append(cityId).append(", sameDeviceFlag=").append(sameDeviceFlag)
                .append(", iosOrAndroid=").append(iosOrAndroid).append("]");
        return builder.toString();
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

}
