package com.fbw.service.entity.portal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * <功能详细描述>商家用户实体
 * @author FBW0115
 * @version [版本号, 2017年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ShopUserEntity implements Serializable
{
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户号码
     */
    private String mobile;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private Integer trackId;

    /**
     * 角色集合
     */
    private List<UserRoleEntity> UserRoleList;

    private static final long serialVersionUID = 1L;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
    }

    public List<UserRoleEntity> getUserRoleList()
    {
        return UserRoleList;
    }

    public void setUserRoleList(List<UserRoleEntity> userRoleList)
    {
        UserRoleList = userRoleList;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopUserEntity [userId=").append(userId).append(", mobile=").append(mobile)
                .append(", userName=").append(userName).append(", password=").append(password).append(", status=")
                .append(status).append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt)
                .append(", trackId=").append(trackId).append(", UserRoleList=").append(UserRoleList).append("]");
        return builder.toString();
    }
}