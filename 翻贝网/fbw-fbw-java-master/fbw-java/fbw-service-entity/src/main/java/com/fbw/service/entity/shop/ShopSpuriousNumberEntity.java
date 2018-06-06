package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商家虚假数据实体
 * @author FBW0115
 * @version [版本号, 2017年10月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopSpuriousNumberEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 虚假收藏量
     */
    private Integer spuriousCollectNum;

    /**
     * 虚假浏览恋
     */
    private Integer spuriousBrowseNum;

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

    public Integer getSpuriousCollectNum()
    {
        return spuriousCollectNum;
    }

    public void setSpuriousCollectNum(Integer spuriousCollectNum)
    {
        this.spuriousCollectNum = spuriousCollectNum;
    }

    public Integer getSpuriousBrowseNum()
    {
        return spuriousBrowseNum;
    }

    public void setSpuriousBrowseNum(Integer spuriousBrowseNum)
    {
        this.spuriousBrowseNum = spuriousBrowseNum;
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
        builder.append("ShopsSpuriousNumber [id=").append(id).append(", shopId=").append(shopId)
                .append(", spuriousCollectNum=").append(spuriousCollectNum).append(", spuriousBrowseNum=")
                .append(spuriousBrowseNum).append(", createdAt=").append(createdAt).append(", updatedAt=")
                .append(updatedAt).append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}
