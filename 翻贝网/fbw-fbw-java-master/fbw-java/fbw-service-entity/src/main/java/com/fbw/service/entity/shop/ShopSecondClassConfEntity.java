package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商户二级分类实体
 * @author FBW0115
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopSecondClassConfEntity implements Serializable
{
    private Integer secondClassId;

    /**
     * 名称
     */
    private String name;

    /**
     * 一级分类id
     */
    private Integer firstClassId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private Integer trackId;

    private static final long serialVersionUID = 1L;

    public Integer getSecondClassId()
    {
        return secondClassId;
    }

    public void setSecondClassId(Integer secondClassId)
    {
        this.secondClassId = secondClassId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(Integer firstClassId)
    {
        this.firstClassId = firstClassId;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
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

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopsSecondClassEntity [secondClassId=").append(secondClassId).append(", name=").append(name)
                .append(", firstClassId=").append(firstClassId).append(", sort=").append(sort).append(", status=")
                .append(status).append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt)
                .append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}