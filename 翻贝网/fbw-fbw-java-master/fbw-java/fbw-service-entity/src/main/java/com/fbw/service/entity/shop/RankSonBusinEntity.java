package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 子榜单实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class RankSonBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家地址
     */
    private String address;

    /**
     * 商家logo
     */
    private String listPhoto;

    /**
     * 二级分类名称
     */
    private String secondClassName;

    /**
     * 商圈名称
     */
    private String businessAreaName;

    /**
     * 真实热度
     */
    private Integer realHot;

    /**
     * 修改后热度
     */
    private Integer changedHot;

    /**
     * 操作人id
     */
    private Integer adminId;

    /**
     * 所属榜单id
     */
    private Integer belongedRankId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 榜单名称
     */
    private String rankName;

    /**
     * 榜单id
     */
    private Integer rankId;

    /**
     * 榜单名次
     */
    private Integer rankNumber;

    /**
     * 
     */
    private String userComment;

    /**
     * 
     */
    private String userName;

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getSecondClassName()
    {
        return secondClassName;
    }

    public void setSecondClassName(String secondClassName)
    {
        this.secondClassName = secondClassName;
    }

    public String getBusinessAreaName()
    {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName)
    {
        this.businessAreaName = businessAreaName;
    }

    public Integer getRealHot()
    {
        return realHot;
    }

    public void setRealHot(Integer realHot)
    {
        this.realHot = realHot;
    }

    public Integer getChangedHot()
    {
        return changedHot;
    }

    public void setChangedHot(Integer changedHot)
    {
        this.changedHot = changedHot;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }

    public Integer getBelongedRankId()
    {
        return belongedRankId;
    }

    public void setBelongedRankId(Integer belongedRankId)
    {
        this.belongedRankId = belongedRankId;
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

    public String getRankName()
    {
        return rankName;
    }

    public void setRankName(String rankName)
    {
        this.rankName = rankName;
    }

    public Integer getRankId()
    {
        return rankId;
    }

    public void setRankId(Integer rankId)
    {
        this.rankId = rankId;
    }

    public Integer getRankNumber()
    {
        return rankNumber;
    }

    public void setRankNumber(Integer rankNumber)
    {
        this.rankNumber = rankNumber;
    }

    public String getUserComment()
    {
        return userComment;
    }

    public void setUserComment(String userComment)
    {
        this.userComment = userComment;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
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
        builder.append("RankSonBusinEntity [id=").append(id).append(", shopId=").append(shopId).append(", name=")
                .append(name).append(", address=").append(address).append(", secondClassName=").append(secondClassName)
                .append(", businessAreaName=").append(businessAreaName).append(", realHot=").append(realHot)
                .append(", changedHot=").append(changedHot).append(", adminId=").append(adminId)
                .append(", belongedRankId=").append(belongedRankId).append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt).append(", rankName=").append(rankName).append(", rankId=")
                .append(rankId).append(", rankNumber=").append(rankNumber).append(", userComment=").append(userComment)
                .append(", userName=").append(userName).append(", trackId=").append(trackId).append("]");
        return builder.toString();
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