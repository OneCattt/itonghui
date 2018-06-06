package com.fbw.service.entity.shop;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>首页分类实体
 * @author FBW0115
 * @version [版本号, 2017年8月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopClassBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 一级分类id
     */
    private Integer firstClassId;

    /**
     * 二级分类id
     */
    private Integer secondClassId;

    /**
     * 分类logo
     */
    private String logo;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 是否生效1：生效、0：失效
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

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

    public Integer getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(Integer firstClassId)
    {
        this.firstClassId = firstClassId;
    }

    public Integer getSecondClassId()
    {
        return secondClassId;
    }

    public void setSecondClassId(Integer secondClassId)
    {
        this.secondClassId = secondClassId;
    }

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt)
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
        builder.append("ShopsClassBusinEntity [id=").append(id).append(", cityId=").append(cityId)
                .append(", firstClassId=").append(firstClassId).append(", secondClassId=").append(secondClassId)
                .append(", logo=").append(logo).append(", sort=").append(sort).append(", name=").append(name)
                .append(", status=").append(status).append(", createdAt=").append(createdAt).append(", updatedAt=")
                .append(updatedAt).append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}