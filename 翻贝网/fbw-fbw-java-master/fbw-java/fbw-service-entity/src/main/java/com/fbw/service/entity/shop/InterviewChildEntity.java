package com.fbw.service.entity.shop;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * <功能详细描述>探店子实体
 * @author FBW0115
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonInclude(Include.NON_NULL)
public class InterviewChildEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * id
     * 
     */
    private Integer id;

    /**
     * 探店id
     */
    private Integer interviewId;

    /**
     * code
     */
    private Integer code;

    /**
     * 状态1：生效、0：失效
     */
    private Integer status;

    private Integer trackId;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getInterviewId()
    {
        return interviewId;
    }

    public void setInterviewId(Integer interviewId)
    {
        this.interviewId = interviewId;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getTrackId()
    {
        return trackId;
    }

    public void setTrackId(Integer trackId)
    {
        this.trackId = trackId;
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
        StringBuilder builder = new StringBuilder();
        builder.append("InterviewChildEntity [id=").append(id).append(", interviewId=").append(interviewId)
                .append(", code=").append(code).append(", status=").append(status).append(", trackId=").append(trackId)
                .append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append("]");
        return builder.toString();
    }

}
