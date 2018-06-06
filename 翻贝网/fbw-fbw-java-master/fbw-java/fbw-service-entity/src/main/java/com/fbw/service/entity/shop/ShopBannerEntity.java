package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>首页banner
 * @author FBW0115
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class ShopBannerEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 是否全国显示(1:是、0:否)
     */
    private Integer isGlobal;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 位置类型（1:首页顶部、2:首页本地生活、3：分类列表广告）
     */
    private Integer locationType;

    /**
     * 一级分类id（location_type为3时需要的值）
     */
    private Integer firstClassId;

    /**
     * 类型
     */
    private String type;

    /**
     * 说明
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date lastTime;

    /**
     * 是否需要登录
     */
    private Integer isLogin;

    /**
     * 生效（1:生效、2:失效）
     */
    private Integer status;

    /**
     * 操作人id
     */
    private Integer adminId;

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Integer getIsGlobal()
    {
        return isGlobal;
    }

    public void setIsGlobal(Integer isGlobal)
    {
        this.isGlobal = isGlobal;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public Integer getLocationType()
    {
        return locationType;
    }

    public void setLocationType(Integer locationType)
    {
        this.locationType = locationType;
    }

    public Integer getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(Integer firstClassId)
    {
        this.firstClassId = firstClassId;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getLastTime()
    {
        return lastTime;
    }

    public void setLastTime(Date lastTime)
    {
        this.lastTime = lastTime;
    }

    public Integer getIsLogin()
    {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin)
    {
        this.isLogin = isLogin;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
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
        builder.append("ShopBannerEntity [id=").append(id).append(", name=").append(name).append(", url=").append(url)
                .append(", isGlobal=").append(isGlobal).append(", cityId=").append(cityId).append(", locationType=")
                .append(locationType).append(", firstClassId=").append(firstClassId).append(", type=").append(type)
                .append(", content=").append(content).append(", sort=").append(sort).append(", startTime=")
                .append(startTime).append(", lastTime=").append(lastTime).append(", isLogin=").append(isLogin)
                .append(", status=").append(status).append(", adminId=").append(adminId).append(", createdAt=")
                .append(createdAt).append(", updatedAt=").append(updatedAt).append(", trackId=").append(trackId)
                .append("]");
        return builder.toString();
    }

}
