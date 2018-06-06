package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商户一级分类实体
 * @author FBW0115
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopFirstClassEntity implements Serializable
{
    private Integer firstClassId;

    private Integer cityId;

    /**
     * 名称
     */
    private String name;

    /**
     * logo
     */
    private String logo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    private Date createdAt;

    private Date updatedAt;

    private Integer trackId;

    /**
     * 二级分类
     */
    List<ShopSecondClassConfEntity> shopsSecondClassList;

    private static final long serialVersionUID = 1L;

    public Integer getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(Integer firstClassId)
    {
        this.firstClassId = firstClassId;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
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

    public List<ShopSecondClassConfEntity> getShopsSecondClassList()
    {
        return shopsSecondClassList;
    }

    public void setShopsSecondClassList(List<ShopSecondClassConfEntity> shopsSecondClassList)
    {
        this.shopsSecondClassList = shopsSecondClassList;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopsFirstClassEntity [firstClassId=").append(firstClassId).append(", cityId=").append(cityId)
                .append(", name=").append(name).append(", logo=").append(logo).append(", status=").append(status)
                .append(", sort=").append(sort).append(", createdAt=").append(createdAt).append(", updatedAt=")
                .append(updatedAt).append(", trackId=").append(trackId).append(", shopsSecondClassList=")
                .append(shopsSecondClassList).append("]");
        return builder.toString();
    }

}