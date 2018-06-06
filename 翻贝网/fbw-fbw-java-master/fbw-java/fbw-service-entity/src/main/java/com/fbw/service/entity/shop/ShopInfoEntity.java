package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商户信息实体
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopInfoEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer shopId;

    /**
     * 商家代码
     */
    private String code;

    /**
     * 商家名称
     */
    private String name;

    /**
     * 商家logo
     */
    private String logo;

    /**
     * 一级分类id
     */
    private Integer firstClassId;

    /**
     * 二级分类id
     */
    private Integer secondClassId;

    /**
     * 标签
     */
    private String shopLabel;

    /**
     * 省份id
     */
    private Integer provinceId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 区县id
     */
    private Integer districtId;

    /**
     * 商圈id
     */
    private Integer businessAreaId;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 是否上架
     */
    private Integer status;

    /**
     * 商家分类
     */
    private String grade;

    /**
     * 列表展示图
     */
    private String listPhoto;

    /**
     * 是否今日翻倍日（每天定时任务更新）
     */
    private Integer isEvent;

    /**
     * 下次活动日期
     */
    private String nextEvent;

    /**
     * 一级分类名称
     */
    private String firstClassName;

    /**
     * 二级分类名称
     */
    private String secondClassName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 商圈名称
     */
    private String businessAreaName;

    /**
     * 订单数量
     */
    private Integer orderNumber;

    /**
     * 评论数量
     */
    private Integer commentNumber;

    /**
     * 评论总分数
     */
    private BigDecimal commentScores;

    /**
     * 评论分数
     */
    private BigDecimal commentMark;

    /**
     * 系统维护人id
     */
    private Integer maintainerId;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

    /**
     * 距离
     */
    private Integer distance;

    /**
     * 智能综合分
     */
    private Double defaultSort;

    /**
     * 人气综合分
     */
    private Double popularity;

    /**
     * 是否品质好店（为1则是）
     */
    private Integer isQuality;

    /**
     * 是否新店（1：新店、0：非新店）
     */
    private Integer isNewShop;

    /**
     * 是否收藏
     */
    private Integer isCollect;

    /**
     * 浏览量
     */
    private Integer browseNum;

    /**
     * 收藏
     */
    private Integer collectNum;

    /**
     * 所属榜单名称
     */
    private String rankName;

    /**
     * 所属榜单排行
     */
    private Integer rankNumber;

    private Integer rankId;

    /**
     * 最近翻倍日
     */
    private ShopEventEntity shopsEventsEntity;

    private String userName;

    private String userComment;

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    public String getShopLabel()
    {
        return shopLabel;
    }

    public void setShopLabel(String shopLabel)
    {
        this.shopLabel = shopLabel;
    }

    public Integer getProvinceId()
    {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId)
    {
        this.provinceId = provinceId;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public Integer getDistrictId()
    {
        return districtId;
    }

    public void setDistrictId(Integer districtId)
    {
        this.districtId = districtId;
    }

    public Integer getBusinessAreaId()
    {
        return businessAreaId;
    }

    public void setBusinessAreaId(Integer businessAreaId)
    {
        this.businessAreaId = businessAreaId;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getListPhoto()
    {
        return listPhoto;
    }

    public void setListPhoto(String listPhoto)
    {
        this.listPhoto = listPhoto;
    }

    public Integer getIsEvent()
    {
        return isEvent;
    }

    public void setIsEvent(Integer isEvent)
    {
        this.isEvent = isEvent;
    }

    public String getNextEvent()
    {
        return nextEvent;
    }

    public void setNextEvent(String nextEvent)
    {
        this.nextEvent = nextEvent;
    }

    public String getFirstClassName()
    {
        return firstClassName;
    }

    public void setFirstClassName(String firstClassName)
    {
        this.firstClassName = firstClassName;
    }

    public String getSecondClassName()
    {
        return secondClassName;
    }

    public void setSecondClassName(String secondClassName)
    {
        this.secondClassName = secondClassName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getDistrictName()
    {
        return districtName;
    }

    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }

    public String getBusinessAreaName()
    {
        return businessAreaName;
    }

    public void setBusinessAreaName(String businessAreaName)
    {
        this.businessAreaName = businessAreaName;
    }

    public Integer getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public Integer getCommentNumber()
    {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber)
    {
        this.commentNumber = commentNumber;
    }

    public BigDecimal getCommentScores()
    {
        return commentScores;
    }

    public void setCommentScores(BigDecimal commentScores)
    {
        this.commentScores = commentScores;
    }

    public BigDecimal getCommentMark()
    {
        return commentMark;
    }

    public void setCommentMark(BigDecimal commentMark)
    {
        this.commentMark = commentMark;
    }

    public Integer getMaintainerId()
    {
        return maintainerId;
    }

    public void setMaintainerId(Integer maintainerId)
    {
        this.maintainerId = maintainerId;
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

    public Integer getDistance()
    {
        return distance;
    }

    public void setDistance(Integer distance)
    {
        this.distance = distance;
    }

    public Double getDefaultSort()
    {
        return defaultSort;
    }

    public void setDefaultSort(Double defaultSort)
    {
        this.defaultSort = defaultSort;
    }

    public Double getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Double popularity)
    {
        this.popularity = popularity;
    }

    public Integer getIsQuality()
    {
        return isQuality;
    }

    public void setIsQuality(Integer isQuality)
    {
        this.isQuality = isQuality;
    }

    public Integer getIsNewShop()
    {
        return isNewShop;
    }

    public void setIsNewShop(Integer isNewShop)
    {
        this.isNewShop = isNewShop;
    }

    public Integer getIsCollect()
    {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect)
    {
        this.isCollect = isCollect;
    }

    public Integer getBrowseNum()
    {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum)
    {
        this.browseNum = browseNum;
    }

    public Integer getCollectNum()
    {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum)
    {
        this.collectNum = collectNum;
    }

    public String getRankName()
    {
        return rankName;
    }

    public void setRankName(String rankName)
    {
        this.rankName = rankName;
    }

    public Integer getRankNumber()
    {
        return rankNumber;
    }

    public void setRankNumber(Integer rankNumber)
    {
        this.rankNumber = rankNumber;
    }

    public Integer getRankId()
    {
        return rankId;
    }

    public void setRankId(Integer rankId)
    {
        this.rankId = rankId;
    }

    public ShopEventEntity getShopsEventsEntity()
    {
        return shopsEventsEntity;
    }

    public void setShopsEventsEntity(ShopEventEntity shopsEventsEntity)
    {
        this.shopsEventsEntity = shopsEventsEntity;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserComment()
    {
        return userComment;
    }

    public void setUserComment(String userComment)
    {
        this.userComment = userComment;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopsInfoEntity [shopId=").append(shopId).append(", code=").append(code).append(", name=")
                .append(name).append(", logo=").append(logo).append(", firstClassId=").append(firstClassId)
                .append(", secondClassId=").append(secondClassId).append(", shopLabel=").append(shopLabel)
                .append(", provinceId=").append(provinceId).append(", cityId=").append(cityId).append(", districtId=")
                .append(districtId).append(", businessAreaId=").append(businessAreaId).append(", longitude=")
                .append(longitude).append(", latitude=").append(latitude).append(", status=").append(status)
                .append(", grade=").append(grade).append(", listPhoto=").append(listPhoto).append(", isEvent=")
                .append(isEvent).append(", nextEvent=").append(nextEvent).append(", firstClassName=")
                .append(firstClassName).append(", secondClassName=").append(secondClassName).append(", cityName=")
                .append(cityName).append(", address=").append(address).append(", districtName=").append(districtName)
                .append(", businessAreaName=").append(businessAreaName).append(", orderNumber=").append(orderNumber)
                .append(", commentNumber=").append(commentNumber).append(", commentScores=").append(commentScores)
                .append(", commentMark=").append(commentMark).append(", maintainerId=").append(maintainerId)
                .append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append(", distance=")
                .append(distance).append(", defaultSort=").append(defaultSort).append(", popularity=")
                .append(popularity).append(", isQuality=").append(isQuality).append(", isNewShop=").append(isNewShop)
                .append(", isCollect=").append(isCollect).append(", browseNum=").append(browseNum)
                .append(", collectNum=").append(collectNum).append(", rankName=").append(rankName)
                .append(", rankNumber=").append(rankNumber).append(", rankId=").append(rankId)
                .append(", shopsEventsEntity=").append(shopsEventsEntity).append(", userName=").append(userName)
                .append(", userComment=").append(userComment).append("]");
        return builder.toString();
    }

}