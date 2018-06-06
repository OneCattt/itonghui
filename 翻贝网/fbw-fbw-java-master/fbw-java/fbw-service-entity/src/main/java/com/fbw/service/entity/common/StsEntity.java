package com.fbw.service.entity.common;

/**
 * 
 * <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StsEntity
{

    private String AccessKeySecret;

    private String AccessKeyId;

    private String SecurityToken;

    public String getAccessKeySecret()
    {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret)
    {
        AccessKeySecret = accessKeySecret;
    }

    public String getAccessKeyId()
    {
        return AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId)
    {
        AccessKeyId = accessKeyId;
    }

    public String getSecurityToken()
    {
        return SecurityToken;
    }

    public void setSecurityToken(String securityToken)
    {
        SecurityToken = securityToken;
    }

}
