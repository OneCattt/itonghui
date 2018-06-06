package com.fbw.service.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户信息地推业务表 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserGroundInfoBusinEntity implements Serializable
{
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 商户id
     */
    private String shopId;

    /**
     * 商户地推店员id
     */
    private String shopAssistantId;

    /**
     * 个人地推id
     */
    private String salesmanId;

    /**
     * 商家地推类型 1:普通地推 2：合伙人地推
     */
    private String shopGroundType;

    /**
     * 地推类型1：个人地推 2：商家地推
     */
    private String groundType;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getShopAssistantId()
    {
        return shopAssistantId;
    }

    public void setShopAssistantId(String shopAssistantId)
    {
        this.shopAssistantId = shopAssistantId;
    }

    public String getSalesmanId()
    {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId)
    {
        this.salesmanId = salesmanId;
    }

    public String getShopGroundType()
    {
        return shopGroundType;
    }

    public void setShopGroundType(String shopGroundType)
    {
        this.shopGroundType = shopGroundType;
    }

    public String getGroundType()
    {
        return groundType;
    }

    public void setGroundType(String groundType)
    {
        this.groundType = groundType;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobile=").append(mobile);
        sb.append(", shopId=").append(shopId);
        sb.append(", shopAssistantId=").append(shopAssistantId);
        sb.append(", salesmanId=").append(salesmanId);
        sb.append(", shopGroundType=").append(shopGroundType);
        sb.append(", groundType=").append(groundType);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
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
}