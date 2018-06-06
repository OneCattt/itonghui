package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>招牌菜
 * @author FBW0115
 * @version [版本号, 2017年8月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopSignEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商户id
     */
    private Integer shopId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 招牌名称
     */
    private String signName;

    /**
     * 状态1：生效、0：失效
     */
    private Integer status;

    /**
     * 招牌图片url
     */
    private String signPhotoUrl;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private Integer trackId;

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

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getSignName()
    {
        return signName;
    }

    public void setSignName(String signName)
    {
        this.signName = signName;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getSignPhotoUrl()
    {
        return signPhotoUrl;
    }

    public void setSignPhotoUrl(String signPhotoUrl)
    {
        this.signPhotoUrl = signPhotoUrl;
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
        builder.append("ShopsSignEntity [id=").append(id).append(", shopId=").append(shopId).append(", sort=")
                .append(sort).append(", signName=").append(signName).append(", status=").append(status)
                .append(", signPhotoUrl=").append(signPhotoUrl).append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt).append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}