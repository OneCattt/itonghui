package com.fbw.service.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户商家反馈实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserShopFeedbackBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private String type;

    /**
     * 系统版本号
     */
    private String osVersion;

    /**
     * 手机型号
     */
    private String phoneType;

    /**
     * app版本
     */
    private String versionCode;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 状态，0未处理1已处理
     */
    private Byte status;

    /**
     * 用户昵称
     */
    private Integer adminUserId;

    /**
     * 类型
     */
    private String note;

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

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getOsVersion()
    {
        return osVersion;
    }

    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    public String getPhoneType()
    {
        return phoneType;
    }

    public void setPhoneType(String phoneType)
    {
        this.phoneType = phoneType;
    }

    public String getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(String versionCode)
    {
        this.versionCode = versionCode;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public Byte getStatus()
    {
        return status;
    }

    public void setStatus(Byte status)
    {
        this.status = status;
    }

    public Integer getAdminUserId()
    {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId)
    {
        this.adminUserId = adminUserId;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
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
        return "UserShopFeedbackBusinEntity [id=" + id + ", userId=" + userId + ", content=" + content + ", type="
                + type + ", osVersion=" + osVersion + ", phoneType=" + phoneType + ", versionCode=" + versionCode
                + ", cityId=" + cityId + ", shopId=" + shopId + ", status=" + status + ", adminUserId=" + adminUserId
                + ", note=" + note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", trackId=" + trackId
                + "]";
    }

}