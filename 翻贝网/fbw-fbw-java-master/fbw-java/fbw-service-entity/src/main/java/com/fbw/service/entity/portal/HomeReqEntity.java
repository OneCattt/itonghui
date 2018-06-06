package com.fbw.service.entity.portal;

import java.io.Serializable;

public class HomeReqEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 广告位置类型
     */
    private String locationType;

    /**
     * 
     */
    private String code;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 分页（第一页传0，第二页传1...）
     */
    private String page;

    /**
     * 翻倍日
     */
    private String eventDate;

    /**
     * 翻倍类型
     */
    private String eventType;

    /**
     * 筛选地区
     */
    private String districtName;

    /**
     * 筛选一级分类
     */
    private String firstClassId;

    /**
     * 筛选二级分类
     */
    private String secondClassId;

    /**
     * 地区id
     */
    private String districtId;

    /**
     * 商圈id
     */
    private String businessAreaId;

    /**
     * 筛选距离
     */
    private String distance;

    /**
     * 筛选排序（0:智能排序、1：距离排序、2:人气排序、3:最新上线、4:好评优先）
     */
    private String sortType;

    /**
     * 是否筛选新店
     */
    private String isNewShop;

    /**
     * 搜索词
     */
    private String searchName;

    /**
     * 商家id
     */
    private String shopId;

    /**
     * 商家详情查询翻倍日
     */
    private String data;

    /**
     * 跟踪ID
     */
    private String trackId;

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getLocationType()
    {
        return locationType;
    }

    public void setLocationType(String locationType)
    {
        this.locationType = locationType;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    public String getPage()
    {
        return page;
    }

    public void setPage(String page)
    {
        this.page = page;
    }

    public String getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(String eventDate)
    {
        this.eventDate = eventDate;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getDistrictName()
    {
        return districtName;
    }

    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }

    public String getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(String firstClassId)
    {
        this.firstClassId = firstClassId;
    }

    public String getSecondClassId()
    {
        return secondClassId;
    }

    public void setSecondClassId(String secondClassId)
    {
        this.secondClassId = secondClassId;
    }

    public String getDistrictId()
    {
        return districtId;
    }

    public void setDistrictId(String districtId)
    {
        this.districtId = districtId;
    }

    public String getBusinessAreaId()
    {
        return businessAreaId;
    }

    public void setBusinessAreaId(String businessAreaId)
    {
        this.businessAreaId = businessAreaId;
    }

    public String getDistance()
    {
        return distance;
    }

    public void setDistance(String distance)
    {
        this.distance = distance;
    }

    public String getSortType()
    {
        return sortType;
    }

    public void setSortType(String sortType)
    {
        this.sortType = sortType;
    }

    public String getIsNewShop()
    {
        return isNewShop;
    }

    public void setIsNewShop(String isNewShop)
    {
        this.isNewShop = isNewShop;
    }

    public String getSearchName()
    {
        return searchName;
    }

    public void setSearchName(String searchName)
    {
        this.searchName = searchName;
    }

    public String getShopId()
    {
        return shopId;
    }

    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("HomeReqEntity [cityId=").append(cityId).append(", userId=").append(userId)
                .append(", locationType=").append(locationType).append(", code=").append(code).append(", longitude=")
                .append(longitude).append(", latitude=").append(latitude).append(", page=").append(page)
                .append(", eventDate=").append(eventDate).append(", eventType=").append(eventType)
                .append(", districtName=").append(districtName).append(", firstClassId=").append(firstClassId)
                .append(", secondClassId=").append(secondClassId).append(", districtId=").append(districtId)
                .append(", businessAreaId=").append(businessAreaId).append(", distance=").append(distance)
                .append(", sortType=").append(sortType).append(", isNewShop=").append(isNewShop).append(", searchName=")
                .append(searchName).append(", shopId=").append(shopId).append(", data=").append(data)
                .append(", trackId=").append(trackId).append("]");
        return builder.toString();
    }

}
