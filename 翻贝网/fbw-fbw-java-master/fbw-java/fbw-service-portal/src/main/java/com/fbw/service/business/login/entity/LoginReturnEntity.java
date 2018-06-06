package com.fbw.service.business.login.entity;

/**
 * 
 * <功能详细描述> 返回实体类
 * @author JACK HUANG
 * @version [版本号, 2017年8月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginReturnEntity
{

    /**
     * 效验登陆标识
     */
    private boolean checkLoginFlag;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 免登陆标识
     */
    private boolean noLandingFlag;

    /**
     * 登陆成功token
     */
    private String token;

    /**
     * 默认效验通过
     * @param checkLoginFlag <默认构造函数>
     */
    public LoginReturnEntity(boolean checkLoginFlag)
    {
        this.checkLoginFlag = checkLoginFlag;
    }

    /**
     * 
     * <默认构造函数>无参构造
     */
    public LoginReturnEntity()
    {

    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public boolean isCheckLoginFlag()
    {
        return checkLoginFlag;
    }

    public void setCheckLoginFlag(boolean checkLoginFlag)
    {
        this.checkLoginFlag = checkLoginFlag;
    }

    public boolean isNoLandingFlag()
    {
        return noLandingFlag;
    }

    public void setNoLandingFlag(boolean noLandingFlag)
    {
        this.noLandingFlag = noLandingFlag;
    }

    public String getFailReason()
    {
        return failReason;
    }

    public void setFailReason(String failReason)
    {
        this.failReason = failReason;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginReturnEntity [checkLoginFlag=").append(checkLoginFlag).append(", failReason=")
                .append(failReason).append(", noLandingFlag=").append(noLandingFlag).append(", token=").append(token)
                .append("]");
        return builder.toString();
    }

}
