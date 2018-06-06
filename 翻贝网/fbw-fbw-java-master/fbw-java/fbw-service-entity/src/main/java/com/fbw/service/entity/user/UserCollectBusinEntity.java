package com.fbw.service.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户收藏实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserCollectBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 探店id
     */
    private Integer interviewShopId;

    /**
     * 收藏类型
     */
    private Integer collectType;

    /**
     * 收藏数量
     */
    private Integer collectNumber;

    /**
     * 预留字段
     */
    private String info;

    private String name;

    private String businessAreaName;

    private String secondClassName;

    private String listPhoto;

    private Date createdAt;

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

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
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

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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
        return "UserCollectBusinEntity [id=" + id + ", userId=" + userId + ", shopId=" + shopId + ", status=" + status
                + ", interviewShopId=" + interviewShopId + ", collectType=" + collectType + ", info=" + info
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

    public Integer getInterviewShopId()
    {
        return interviewShopId;
    }

    public void setInterviewShopId(Integer interviewShopId)
    {
        this.interviewShopId = interviewShopId;
    }

    public Integer getCollectType()
    {
        return collectType;
    }

    public void setCollectType(Integer collectType)
    {
        this.collectType = collectType;
    }

    public Integer getCollectNumber()
    {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber)
    {
        this.collectNumber = collectNumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBusinessAreaName()
    {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName)
    {
        this.businessAreaName = businessAreaName;
    }

    public String getSecondClassName()
    {
        return secondClassName;
    }

    public void setSecondClassName(String secondClassName)
    {
        this.secondClassName = secondClassName;
    }

    public String getListPhoto()
    {
        return listPhoto;
    }

    public void setListPhoto(String listPhoto)
    {
        this.listPhoto = listPhoto;
    }
}