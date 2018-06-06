package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商圈实体
 * @author FBW0115
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopBusinessAreaEntity implements Serializable
{
    private Integer businessAreaId;

    /**
     * 商圈名称
     */
    private String name;

    /**
     * 区县id
     */
    private Integer districtId;

    /**
     * 首字母
     */
    private String letter;

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

    public Integer getBusinessAreaId()
    {
        return businessAreaId;
    }

    public void setBusinessAreaId(Integer businessAreaId)
    {
        this.businessAreaId = businessAreaId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getDistrictId()
    {
        return districtId;
    }

    public void setDistrictId(Integer districtId)
    {
        this.districtId = districtId;
    }

    public String getLetter()
    {
        return letter;
    }

    public void setLetter(String letter)
    {
        this.letter = letter;
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
        builder.append("ShopsBusinessAreaEntity [businessAreaId=").append(businessAreaId).append(", name=").append(name)
                .append(", districtId=").append(districtId).append(", letter=").append(letter).append(", sort=")
                .append(sort).append(", status=").append(status).append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt).append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}