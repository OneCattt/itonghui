package com.fbw.service.entity.portal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * <功能详细描述>商家角色实体
 * @author FBW0115
 * @version [版本号, 2017年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserRoleEntity implements Serializable
{
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 商户id
     */
    private Integer shopId;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户类型
     */
    private Integer roleType;

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
     * 权限集合
     */
    private List<RolePrivilegeEntity> rolePrivilegeList;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getRoleType()
    {
        return roleType;
    }

    public void setRoleType(Integer roleType)
    {
        this.roleType = roleType;
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

    public List<RolePrivilegeEntity> getRolePrivilegeList()
    {
        return rolePrivilegeList;
    }

    public void setRolePrivilegeList(List<RolePrivilegeEntity> rolePrivilegeList)
    {
        this.rolePrivilegeList = rolePrivilegeList;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("UserRoleEntity [roleId=").append(roleId).append(", shopId=").append(shopId)
                .append(", shopName=").append(shopName).append(", userId=").append(userId).append(", roleType=")
                .append(roleType).append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt)
                .append(", trackId=").append(trackId).append(", rolePrivilegeList=").append(rolePrivilegeList)
                .append("]");
        return builder.toString();
    }

}