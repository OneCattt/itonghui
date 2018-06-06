package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>地区实体
 * @author FBW0115
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopDistrictEntity implements Serializable
{
    private Integer districtId;

    /**
     * 区县名称
     */
    private String name;

    /**
     * 城市id
     */
    private Integer cityId;

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

    private List<ShopBusinessAreaEntity> shopsBusinessAreaList;

    private static final long serialVersionUID = 1L;

    public Integer getDistrictId()
    {
        return districtId;
    }

    public void setDistrictId(Integer districtId)
    {
        this.districtId = districtId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
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

    public List<ShopBusinessAreaEntity> getShopsBusinessAreaList()
    {
        return shopsBusinessAreaList;
    }

    public void setShopsBusinessAreaList(List<ShopBusinessAreaEntity> shopsBusinessAreaList)
    {
        this.shopsBusinessAreaList = shopsBusinessAreaList;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopsDistrictEntity [districtId=").append(districtId).append(", name=").append(name)
                .append(", cityId=").append(cityId).append(", letter=").append(letter).append(", sort=").append(sort)
                .append(", status=").append(status).append(", createdAt=").append(createdAt).append(", updatedAt=")
                .append(updatedAt).append(", trackId=").append(trackId).append(", shopsBusinessAreaList=")
                .append(shopsBusinessAreaList).append("]");
        return builder.toString();
    }

}