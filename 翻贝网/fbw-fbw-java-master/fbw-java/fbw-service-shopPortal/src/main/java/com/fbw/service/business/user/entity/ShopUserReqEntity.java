package com.fbw.service.business.user.entity;

/**
 * 
 * <功能详细描述>商家用户实体
 * @author FBW0115
 * @version [版本号, 2017年9月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ShopUserReqEntity
{

    /**
     * 用户号码
     */
    private String mobile;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 旧密码
     */
    private String oldPwd;

    /**
     * 新密码
     */
    private String newPwd;

    /**
     * 校验密码
     */
    private String checkPwd;

    /**
     * 短线验证码
     */
    private String code;

    /**
     * 更改类型
     */
    private String modifyType;

    /**
     * 跟踪id
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

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getOldPwd()
    {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd)
    {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd()
    {
        return newPwd;
    }

    public void setNewPwd(String newPwd)
    {
        this.newPwd = newPwd;
    }

    public String getCheckPwd()
    {
        return checkPwd;
    }

    public void setCheckPwd(String checkPwd)
    {
        this.checkPwd = checkPwd;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getModifyType()
    {
        return modifyType;
    }

    public void setModifyType(String modifyType)
    {
        this.modifyType = modifyType;
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
        builder.append("ShopUserReqEntity [mobile=").append(mobile).append(", userId=").append(userId)
                .append(", oldPwd=").append(oldPwd).append(", newPwd=").append(newPwd).append(", checkPwd=")
                .append(checkPwd).append(", code=").append(code).append(", modifyType=").append(modifyType)
                .append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}
