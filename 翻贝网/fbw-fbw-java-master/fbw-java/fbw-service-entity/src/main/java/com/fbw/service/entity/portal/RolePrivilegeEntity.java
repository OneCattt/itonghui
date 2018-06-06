package com.fbw.service.entity.portal;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <功能详细描述>角色权限实体
 * @author FBW0115
 * @version [版本号, 2017年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RolePrivilegeEntity implements Serializable
{
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer privilegeId;

    /**
     * 权限名称
     */
    private String privilegeName;

    /**
     * 权限说明
     */
    private String privilegeDescribe;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private Integer trackId;

    private static final long serialVersionUID = 1L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId()
    {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId)
    {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName()
    {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName)
    {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeDescribe()
    {
        return privilegeDescribe;
    }

    public void setPrivilegeDescribe(String privilegeDescribe)
    {
        this.privilegeDescribe = privilegeDescribe;
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

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("RolePrivilegeEntity [id=").append(id).append(", roleId=").append(roleId)
                .append(", privilegeId=").append(privilegeId).append(", privilegeName=").append(privilegeName)
                .append(", privilegeDescribe=").append(privilegeDescribe).append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt).append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }
}