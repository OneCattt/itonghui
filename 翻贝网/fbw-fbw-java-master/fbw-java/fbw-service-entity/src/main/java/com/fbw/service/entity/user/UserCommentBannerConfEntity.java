package com.fbw.service.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 评价上方banner <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserCommentBannerConfEntity implements Serializable
{
    private Integer bannerId;

    /**
     * banner名称
     */
    private String bannerName;

    /**
     * 最高评论分数
     */
    private BigDecimal score;

    /**
     * 最低评论分数
     */
    private BigDecimal minScore;

    /**
     * 是否带图片
     */
    private Integer isPic;

    /**
     * 评论数量
     */
    private Integer commNum;

    private Integer shopId;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public String getBannerName()
    {
        return bannerName;
    }

    public void setBannerName(String bannerName)
    {
        this.bannerName = bannerName;
    }

    public Integer getIsPic()
    {
        return isPic;
    }

    public void setIsPic(Integer isPic)
    {
        this.isPic = isPic;
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bannerName=").append(bannerName);
        sb.append(", score=").append(score);
        sb.append(", isPic=").append(isPic);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getCommNum()
    {
        return commNum;
    }

    public void setCommNum(Integer commNum)
    {
        this.commNum = commNum;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public BigDecimal getMinScore()
    {
        return minScore;
    }

    public void setMinScore(BigDecimal minScore)
    {
        this.minScore = minScore;
    }

    public BigDecimal getScore()
    {
        return score;
    }

    public void setScore(BigDecimal score)
    {
        this.score = score;
    }

    public Integer getBannerId()
    {
        return bannerId;
    }

    public void setBannerId(Integer bannerId)
    {
        this.bannerId = bannerId;
    }

}