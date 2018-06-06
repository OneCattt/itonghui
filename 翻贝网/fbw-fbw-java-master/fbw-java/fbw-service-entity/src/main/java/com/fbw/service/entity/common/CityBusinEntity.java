package com.fbw.service.entity.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>城市实体
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class CityBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer cityId;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 省份id
     */
    private Integer provinceId;

    /**
     * 首字母
     */
    private String letter;

    /**
     * 代理商比例
     */
    private Integer agentRatio;

    /**
     * 总部比例
     */
    private Integer hqRatio;

    /**
     * 红包比例
     */
    private Integer ratio;

    /**
     * 红包有效天数
     */
    private Integer validDays;

    /**
     * 操作人
     */
    private Integer adminId;

    /**
     * 状态,1:上线、2:未上线、0:失效
     */
    private Integer status;

    private String createdAt;

    private String updatedAt;

    private Integer trackId;

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getProvinceId()
    {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId)
    {
        this.provinceId = provinceId;
    }

    public String getLetter()
    {
        return letter;
    }

    public void setLetter(String letter)
    {
        this.letter = letter;
    }

    public Integer getAgentRatio()
    {
        return agentRatio;
    }

    public void setAgentRatio(Integer agentRatio)
    {
        this.agentRatio = agentRatio;
    }

    public Integer getHqRatio()
    {
        return hqRatio;
    }

    public void setHqRatio(Integer hqRatio)
    {
        this.hqRatio = hqRatio;
    }

    public Integer getRatio()
    {
        return ratio;
    }

    public void setRatio(Integer ratio)
    {
        this.ratio = ratio;
    }

    public Integer getValidDays()
    {
        return validDays;
    }

    public void setValidDays(Integer validDays)
    {
        this.validDays = validDays;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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
        builder.append("CityBusinEntity [cityId=").append(cityId).append(", name=").append(name).append(", provinceId=")
                .append(provinceId).append(", letter=").append(letter).append(", agentRatio=").append(agentRatio)
                .append(", hqRatio=").append(hqRatio).append(", ratio=").append(ratio).append(", validDays=")
                .append(validDays).append(", adminId=").append(adminId).append(", status=").append(status)
                .append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append(", trackId=")
                .append(trackId).append("]");
        return builder.toString();
    }

}
