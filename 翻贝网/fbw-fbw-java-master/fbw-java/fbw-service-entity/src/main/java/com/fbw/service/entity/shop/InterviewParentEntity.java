package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>探店实体（主）
 * @author FBW0115
 * @version [版本号, 2017年8月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class InterviewParentEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * id(mysql自动生成)
     */
    private Integer interviewId;

    /**
     * 城市id
     */
    private String cityId;

    /**
     * 探店标题
     */
    private String interviewTitle;

    /**
     * 探店主题图
     */
    private String interviewTheme;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 是否置顶（1:置顶、0:不置顶）
     */
    private Integer isTop;

    /**
     * 探店文章内容
     */
    private String interviewContent;

    /**
     * 生效时间
     */
    private String effDate;

    /**
     * 失效时间
     */
    private String expDate;

    /**
     * 状态（1:生效、0:失效）
     */
    private Integer status;

    /**
     * 浏览量
     */
    private Integer browseNum;

    /**
     * 收藏
     */
    private Integer collection;

    /**
     * 是否收藏
     */
    private Integer isCollect;

    private Integer trackId;

    /**
     * 收藏量
     */
    private Integer collectNum;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    private List<ShopInfoEntity> involvedShopsInfo;

    public Integer getInterviewId()
    {
        return interviewId;
    }

    public void setInterviewId(Integer interviewId)
    {
        this.interviewId = interviewId;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getInterviewTitle()
    {
        return interviewTitle;
    }

    public void setInterviewTitle(String interviewTitle)
    {
        this.interviewTitle = interviewTitle;
    }

    public String getInterviewTheme()
    {
        return interviewTheme;
    }

    public void setInterviewTheme(String interviewTheme)
    {
        this.interviewTheme = interviewTheme;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public Integer getIsTop()
    {
        return isTop;
    }

    public void setIsTop(Integer isTop)
    {
        this.isTop = isTop;
    }

    public String getInterviewContent()
    {
        return interviewContent;
    }

    public void setInterviewContent(String interviewContent)
    {
        this.interviewContent = interviewContent;
    }

    public String getEffDate()
    {
        return effDate;
    }

    public void setEffDate(String effDate)
    {
        this.effDate = effDate;
    }

    public String getExpDate()
    {
        return expDate;
    }

    public void setExpDate(String expDate)
    {
        this.expDate = expDate;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getBrowseNum()
    {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum)
    {
        this.browseNum = browseNum;
    }

    public Integer getCollection()
    {
        return collection;
    }

    public void setCollection(Integer collection)
    {
        this.collection = collection;
    }

    public Integer getIsCollect()
    {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect)
    {
        this.isCollect = isCollect;
    }

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
    }

    public Integer getCollectNum()
    {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum)
    {
        this.collectNum = collectNum;
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

    public List<ShopInfoEntity> getInvolvedShopsInfo()
    {
        return involvedShopsInfo;
    }

    public void setInvolvedShopsInfo(List<ShopInfoEntity> involvedShopsInfo)
    {
        this.involvedShopsInfo = involvedShopsInfo;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("InterviewParentEntity [interviewId=").append(interviewId).append(", cityId=").append(cityId)
                .append(", interviewTitle=").append(interviewTitle).append(", interviewTheme=").append(interviewTheme)
                .append(", publisher=").append(publisher).append(", isTop=").append(isTop).append(", interviewContent=")
                .append(interviewContent).append(", effDate=").append(effDate).append(", expDate=").append(expDate)
                .append(", status=").append(status).append(", browseNum=").append(browseNum).append(", collection=")
                .append(collection).append(", isCollect=").append(isCollect).append(", trackId=").append(trackId)
                .append(", collectNum=").append(collectNum).append(", createdAt=").append(createdAt)
                .append(", updatedAt=").append(updatedAt).append(", involvedShopsInfo=").append(involvedShopsInfo)
                .append("]");
        return builder.toString();
    }

}
