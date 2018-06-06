package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商家报错
 * @author FBW0115
 * @version [版本号, 2017年9月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopInfoErrorEntity implements Serializable
{
    private Integer id;

    private Integer shopId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 报错信息
     */
    private String infoError;

    /**
     * 处理状态
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

    private String trackId;

    private static final long serialVersionUID = 1L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getInfoError()
    {
        return infoError;
    }

    public void setInfoError(String infoError)
    {
        this.infoError = infoError;
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
        builder.append("ShopsInfoErrorEntity [id=").append(id).append(", shopId=").append(shopId).append(", userId=")
                .append(userId).append(", infoError=").append(infoError).append(", status=").append(status)
                .append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append(", trackId=")
                .append(trackId).append("]");
        return builder.toString();
    }

}