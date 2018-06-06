package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>父榜单实体类
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class RankFatherBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 榜单id
     */
    private Integer id;

    /**
     * 榜单名
     */
    private String rankName;

    /**
     * 榜单id
     */
    private Integer rankId;

    /**
     * 榜单背景图片
     */
    private String rankBackUrl;

    /**
     * 榜单次序
     */
    private Integer rankOrder;

    /**
     * 榜单类型（1：主要榜单，2：自定义榜单）
     */
    private Integer cityId;

    /**
     * 操作人id
     */
    private Integer adminId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private Integer firstClassId;

    private Integer trackId;

    private List<RankSonBusinEntity> rankSonBusinEntities;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRankName()
    {
        return rankName;
    }

    public void setRankName(String rankName)
    {
        this.rankName = rankName;
    }

    public String getRankBackUrl()
    {
        return rankBackUrl;
    }

    public void setRankBackUrl(String rankBackUrl)
    {
        this.rankBackUrl = rankBackUrl;
    }

    public Integer getRankOrder()
    {
        return rankOrder;
    }

    public void setRankOrder(Integer rankOrder)
    {
        this.rankOrder = rankOrder;
    }

    public Integer getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Integer adminId)
    {
        this.adminId = adminId;
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

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
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
        return "RankFatherBusinEntity [id=" + id + ", rankName=" + rankName + ", rankBackUrl=" + rankBackUrl
                + ", rankOrder=" + rankOrder + ", cityId=" + cityId + ", adminId=" + adminId + ", createdDate="
                + createdAt + ", updatedDate=" + updatedAt + ", trackId=" + trackId + "]";
    }

    public List<RankSonBusinEntity> getRankSonBusinEntities()
    {
        return rankSonBusinEntities;
    }

    public void setRankSonBusinEntities(List<RankSonBusinEntity> rankSonBusinEntities)
    {
        this.rankSonBusinEntities = rankSonBusinEntities;
    }

    public Integer getRankId()
    {
        return rankId;
    }

    public void setRankId(Integer rankId)
    {
        this.rankId = rankId;
    }

    public Integer getFirstClassId()
    {
        return firstClassId;
    }

    public void setFirstClassId(Integer firstClassId)
    {
        this.firstClassId = firstClassId;
    }

}