package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>商户详细信息实体
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopDetailEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商户id
     */
    private Integer shopId;

    /**
     * 最大消费金额
     */
    private BigDecimal maxSales;

    /**
     * 营业时间
     */
    private String businessHours;

    /**
     * 当日最大消费次数
     */
    private Integer buyNumber;

    /**
     * 每周最多翻倍天数
     */
    private Integer weekDoubleDay;

    /**
     * 环境组图
     */
    private String photos;

    /**
     * 其他
     */
    private String content;

    /**
     * 备注
     */
    private String tips;

    /**
     * 发票信息
     */
    private String invoice;

    /**
     * 不参与的商品
     */
    private String notPartakeGoods;

    /**
     * 商家简介
     */
    private String description;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 接收短信手机
     */
    private String smsPhone;

    /**
     * 最后活动日期
     */
    private String lastEvent;

    /**
     * 随机二维码
     */
    private String qrCode;

    /**
     * 原系统id
     */
    private Integer limitedType;

    /**
     * 原系统id
     */
    private Integer limitedAmount;

    /**
     * 原系统id
     */
    private Integer limitedNum;

    /**
     * 原系统id
     */
    private Integer oldId;

    /**
     * 原系统维护人
     */
    private String oldMaintainer;

    /**
     * 绑定时间
     */
    private String bindingTime;

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

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public BigDecimal getMaxSales()
    {
        return maxSales;
    }

    public void setMaxSales(BigDecimal maxSales)
    {
        this.maxSales = maxSales;
    }

    public String getBusinessHours()
    {
        return businessHours;
    }

    public void setBusinessHours(String businessHours)
    {
        this.businessHours = businessHours;
    }

    public Integer getBuyNumber()
    {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber)
    {
        this.buyNumber = buyNumber;
    }

    public Integer getWeekDoubleDay()
    {
        return weekDoubleDay;
    }

    public void setWeekDoubleDay(Integer weekDoubleDay)
    {
        this.weekDoubleDay = weekDoubleDay;
    }

    public String getPhotos()
    {
        return photos;
    }

    public void setPhotos(String photos)
    {
        this.photos = photos;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTips()
    {
        return tips;
    }

    public void setTips(String tips)
    {
        this.tips = tips;
    }

    public String getInvoice()
    {
        return invoice;
    }

    public void setInvoice(String invoice)
    {
        this.invoice = invoice;
    }

    public String getNotPartakeGoods()
    {
        return notPartakeGoods;
    }

    public void setNotPartakeGoods(String notPartakeGoods)
    {
        this.notPartakeGoods = notPartakeGoods;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getSmsPhone()
    {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone)
    {
        this.smsPhone = smsPhone;
    }

    public String getLastEvent()
    {
        return lastEvent;
    }

    public void setLastEvent(String lastEvent)
    {
        this.lastEvent = lastEvent;
    }

    public String getQrCode()
    {
        return qrCode;
    }

    public void setQrCode(String qrCode)
    {
        this.qrCode = qrCode;
    }

    public Integer getLimitedType()
    {
        return limitedType;
    }

    public void setLimitedType(Integer limitedType)
    {
        this.limitedType = limitedType;
    }

    public Integer getLimitedAmount()
    {
        return limitedAmount;
    }

    public void setLimitedAmount(Integer limitedAmount)
    {
        this.limitedAmount = limitedAmount;
    }

    public Integer getLimitedNum()
    {
        return limitedNum;
    }

    public void setLimitedNum(Integer limitedNum)
    {
        this.limitedNum = limitedNum;
    }

    public Integer getOldId()
    {
        return oldId;
    }

    public void setOldId(Integer oldId)
    {
        this.oldId = oldId;
    }

    public String getOldMaintainer()
    {
        return oldMaintainer;
    }

    public void setOldMaintainer(String oldMaintainer)
    {
        this.oldMaintainer = oldMaintainer;
    }

    public String getBindingTime()
    {
        return bindingTime;
    }

    public void setBindingTime(String bindingTime)
    {
        this.bindingTime = bindingTime;
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
        builder.append("ShopDetailEntity [id=").append(id).append(", shopId=").append(shopId).append(", maxSales=")
                .append(maxSales).append(", businessHours=").append(businessHours).append(", buyNumber=")
                .append(buyNumber).append(", weekDoubleDay=").append(weekDoubleDay).append(", photos=").append(photos)
                .append(", content=").append(content).append(", tips=").append(tips).append(", invoice=")
                .append(invoice).append(", notPartakeGoods=").append(notPartakeGoods).append(", description=")
                .append(description).append(", contactNumber=").append(contactNumber).append(", smsPhone=")
                .append(smsPhone).append(", lastEvent=").append(lastEvent).append(", qrCode=").append(qrCode)
                .append(", limitedType=").append(limitedType).append(", limitedAmount=").append(limitedAmount)
                .append(", limitedNum=").append(limitedNum).append(", oldId=").append(oldId).append(", oldMaintainer=")
                .append(oldMaintainer).append(", bindingTime=").append(bindingTime).append(", createdAt=")
                .append(createdAt).append(", updatedAt=").append(updatedAt).append(", trackId=").append(trackId)
                .append("]");
        return builder.toString();
    }

}