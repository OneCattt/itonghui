package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>品质好店实体
 * @author FBW0115
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopQualityEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 商户id
     */
    private Integer shopId;

    /**
     * 商户名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 商家logo
     */
    private String shopLogo;

    /**
     * 列表展示图
     */
    private String listPhoto;

    /**
     * 品质评语，多个评语用与（&）分隔
     */
    private String qualityComment;

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

    private ShopInfoEntity shopsInfoEntity;

    /**
     * 浏览量
     */
    private Integer browser;

    /**
     * 收藏
     */
    private Integer collection;

    private Integer trackId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getShopLogo()
    {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo)
    {
        this.shopLogo = shopLogo;
    }

    public String getListPhoto()
    {
        return listPhoto;
    }

    public void setListPhoto(String listPhoto)
    {
        this.listPhoto = listPhoto;
    }

    public String getQualityComment()
    {
        return qualityComment;
    }

    public void setQualityComment(String qualityComment)
    {
        this.qualityComment = qualityComment;
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

    public ShopInfoEntity getShopsInfoEntity()
    {
        return shopsInfoEntity;
    }

    public void setShopsInfoEntity(ShopInfoEntity shopsInfoEntity)
    {
        this.shopsInfoEntity = shopsInfoEntity;
    }

    public Integer getBrowser()
    {
        return browser;
    }

    public void setBrowser(Integer browser)
    {
        this.browser = browser;
    }

    public Integer getCollection()
    {
        return collection;
    }

    public void setCollection(Integer collection)
    {
        this.collection = collection;
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
        builder.append("ShopsQualityEntity [id=").append(id).append(", cityId=").append(cityId).append(", shopId=")
                .append(shopId).append(", name=").append(name).append(", sort=").append(sort).append(", shopLogo=")
                .append(shopLogo).append(", listPhoto=").append(listPhoto).append(", qualityComment=")
                .append(qualityComment).append(", status=").append(status).append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt).append(", shopsInfoEntity=").append(shopsInfoEntity)
                .append(", browser=").append(browser).append(", collection=").append(collection).append(", trackId=")
                .append(trackId).append("]");
        return builder.toString();
    }

}