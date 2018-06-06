package com.fbw.service.entity.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <功能详细描述>城市代理申请实体
 * @author FBW0115
 * @version [版本号, 2017年9月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CityIntentionAgentEntity implements Serializable
{
    private Integer id;

    /**
     * 代理商姓名
     */
    private String agentName;

    /**
     * 代理商电话号码
     */
    private String agentTelnum;

    /**
     * 意向代理城市
     */
    private String intentAgentCity;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private Integer trackId;

    private static final long serialVersionUID = 1L;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getAgentName()
    {
        return agentName;
    }

    public void setAgentName(String agentName)
    {
        this.agentName = agentName;
    }

    public String getAgentTelnum()
    {
        return agentTelnum;
    }

    public void setAgentTelnum(String agentTelnum)
    {
        this.agentTelnum = agentTelnum;
    }

    public String getIntentAgentCity()
    {
        return intentAgentCity;
    }

    public void setIntentAgentCity(String intentAgentCity)
    {
        this.intentAgentCity = intentAgentCity;
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
        builder.append("CityAgentEntity [id=").append(id).append(", agentName=").append(agentName)
                .append(", agentTelnum=").append(agentTelnum).append(", intentAgentCity=").append(intentAgentCity)
                .append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append(", trackId=")
                .append(trackId).append("]");
        return builder.toString();
    }

}