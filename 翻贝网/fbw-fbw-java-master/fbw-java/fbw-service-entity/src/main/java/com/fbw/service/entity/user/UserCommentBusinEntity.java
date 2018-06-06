package com.fbw.service.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 用户评论实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class UserCommentBusinEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 商家id
     */
    private Integer shopId;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论图片
     */
    private String picture;

    /**
     * 评论标签
     */
    private String commentLabel;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复时间
     */
    private Date replyTime;

    /**
     * 回复状态
     */
    private Integer replyStatus;

    /**
     * 举报状态0未举报1已举报2不处理3已删除
     */
    private Integer reportStatus;

    /**
     * 举报内容
     */
    private String reportContent;

    /**
     * 举报时间
     */
    private Date reportTime;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 操作人ID
     */
    private Integer adminId;

    private String createdAt;

    private Date updatedAt;

    private String trackId;

    private BigDecimal avgCommScore;

    private Integer commentNum;

    private BigDecimal sumCommScore;

    private Integer isNopic;

    private Integer isNoName;

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

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getUserAvatar()
    {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar)
    {
        this.userAvatar = userAvatar;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public Integer getShopId()
    {
        return shopId;
    }

    public void setShopId(Integer shopId)
    {
        this.shopId = shopId;
    }

    public BigDecimal getScore()
    {
        return score;
    }

    public void setScore(BigDecimal score)
    {
        this.score = score;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getReplyContent()
    {
        return replyContent;
    }

    public void setReplyContent(String replyContent)
    {
        this.replyContent = replyContent;
    }

    public Date getReplyTime()
    {
        return replyTime;
    }

    public void setReplyTime(Date replyTime)
    {
        this.replyTime = replyTime;
    }

    public Integer getReportStatus()
    {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus)
    {
        this.reportStatus = reportStatus;
    }

    public String getReportContent()
    {
        return reportContent;
    }

    public void setReportContent(String reportContent)
    {
        this.reportContent = reportContent;
    }

    public Date getReportTime()
    {
        return reportTime;
    }

    public void setReportTime(Date reportTime)
    {
        this.reportTime = reportTime;
    }

    public Integer getIsDel()
    {
        return isDel;
    }

    public void setIsDel(Integer isDel)
    {
        this.isDel = isDel;
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

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getCommentLabel()
    {
        return commentLabel;
    }

    public void setCommentLabel(String commentLabel)
    {
        this.commentLabel = commentLabel;
    }

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    public BigDecimal getAvgCommScore()
    {
        return avgCommScore;
    }

    public void setAvgCommScore(BigDecimal avgCommScore)
    {
        this.avgCommScore = avgCommScore;
    }

    public Integer getCommentNum()
    {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum)
    {
        this.commentNum = commentNum;
    }

    public Integer getIsNopic()
    {
        return isNopic;
    }

    public void setIsNopic(Integer isNopic)
    {
        this.isNopic = isNopic;
    }

    @Override
    public String toString()
    {
        return "UserCommentBusinEntity [id=" + id + ", userId=" + userId + ", nickName=" + nickName + ", userAvatar="
                + userAvatar + ", orderNumber=" + orderNumber + ", shopId=" + shopId + ", score=" + score + ", content="
                + content + ", picture=" + picture + ", commentLabel=" + commentLabel + ", replyContent=" + replyContent
                + ", replyTime=" + replyTime + ", reportStatus=" + reportStatus + ", reportContent=" + reportContent
                + ", reportTime=" + reportTime + ", isDel=" + isDel + ", adminId=" + adminId + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + ", trackId=" + trackId + "]";
    }

    public Integer getIsNoName()
    {
        return isNoName;
    }

    public void setIsNoName(Integer isNoName)
    {
        this.isNoName = isNoName;
    }

    public BigDecimal getSumCommScore()
    {
        return sumCommScore;
    }

    public void setSumCommScore(BigDecimal sumCommScore)
    {
        this.sumCommScore = sumCommScore;
    }

    public Integer getReplyStatus()
    {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus)
    {
        this.replyStatus = replyStatus;
    }

}