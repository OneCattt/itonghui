package com.fbw.service.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户设备信息实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserDeviceInfoEntity implements Serializable
{
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户手机号
     */
    private String userMobile;

    /**
     * 注册来源
     */
    private String registerSource;

    /**
     * 设备id号
     */
    private String deviceId;

    /**
     * 设备id号
     */
    private String osVersion;

    /**
     * 设备id号
     */
    private String phoneType;

    /**
     * 注册ip
     */
    private String ip;

    /**
     * 注册地区
     */
    private String registerArea;

    /**
     * ios或者安卓
     */
    private String iosOrAndroid;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public String getRegisterSource()
    {
        return registerSource;
    }

    public void setRegisterSource(String registerSource)
    {
        this.registerSource = registerSource;
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

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getRegisterArea()
    {
        return registerArea;
    }

    public void setRegisterArea(String registerArea)
    {
        this.registerArea = registerArea;
    }

    public String getIosOrAndroid()
    {
        return iosOrAndroid;
    }

    public void setIosOrAndroid(String iosOrAndroid)
    {
        this.iosOrAndroid = iosOrAndroid;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
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

    public String getUserMobile()
    {
        return userMobile;
    }

    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userMobile=").append(userMobile);
        sb.append(", registerSource=").append(registerSource);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", osVersion=").append(osVersion);
        sb.append(", phoneType=").append(phoneType);
        sb.append(", ip=").append(ip);
        sb.append(", registerArea=").append(registerArea);
        sb.append(", iosOrAndroid=").append(iosOrAndroid);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}