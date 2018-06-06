package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>翻倍日实体
 * @author FBW0115
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopEventEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 活动日期
     */
    private String event;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 翻倍类型
     */
    private Integer eventType;

    private Integer trackId;

    private String beginDate;

    private String endDate;

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

    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
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

    public Integer getEventType()
    {
        return eventType;
    }

    public void setEventType(Integer eventType)
    {
        this.eventType = eventType;
    }

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
    }

    public String getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(String beginDate)
    {
        this.beginDate = beginDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopsEventsEntity [id=").append(id).append(", shopId=").append(shopId).append(", event=")
                .append(event).append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt)
                .append(", eventType=").append(eventType).append(", trackId=").append(trackId).append(", beginDate=")
                .append(beginDate).append(", endDate=").append(endDate).append("]");
        return builder.toString();
    }

}