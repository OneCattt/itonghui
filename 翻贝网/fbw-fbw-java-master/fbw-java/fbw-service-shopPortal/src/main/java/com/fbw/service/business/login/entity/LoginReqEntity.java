package com.fbw.service.business.login.entity;

/**
 * 
 * <功能详细描述> 登陆入参实体类
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginReqEntity
{
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 密码
     */
    private String password;

    /**
     * 登陆类型
     */
    private String loginType;

    /**
     * 短信类型
     */
    private String smsType;

    /**
     * 微信号
     */
    private String openId;

    /**
     * 注册IP
     */
    private String ip;

    /**
     * 跟踪ID
     */
    private String trackId;

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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLoginType()
    {
        return loginType;
    }

    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }

    public String getSmsType()
    {
        return smsType;
    }

    public void setSmsType(String smsType)
    {
        this.smsType = smsType;
    }

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginReqEntity [mobile=").append(mobile).append(", code=").append(code).append(", password=")
                .append(password).append(", loginType=").append(loginType).append(", smsType=").append(smsType)
                .append(", openId=").append(openId).append(", ip=").append(ip).append(", trackId=").append(trackId)
                .append("]");
        return builder.toString();
    }

}
