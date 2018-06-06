package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>搜索热词实体
 * @author FBW0115
 * @version [版本号, 2017年8月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopHotWordEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 热词类型
     */
    private Integer hotType;

    /**
     * 热词名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 内容
     */
    private String content;

    private Integer isRed;

    private Integer adminId;

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

    public Integer getHotType()
    {
        return hotType;
    }

    public void setHotType(Integer hotType)
    {
        this.hotType = hotType;
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

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getIsRed()
    {
        return isRed;
    }

    public void setIsRed(Integer isRed)
    {
        this.isRed = isRed;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
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
        builder.append("ShopsHotWordsEntity [id=").append(id).append(", hotType=").append(hotType).append(", name=")
                .append(name).append(", sort=").append(sort).append(", cityId=").append(cityId).append(", content=")
                .append(content).append(", isRed=").append(isRed).append(", adminId=").append(adminId)
                .append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append(", trackId=")
                .append(trackId).append("]");
        return builder.toString();
    }

}