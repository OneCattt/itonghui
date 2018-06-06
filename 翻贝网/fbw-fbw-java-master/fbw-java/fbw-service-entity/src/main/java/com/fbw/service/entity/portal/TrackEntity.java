package com.fbw.service.entity.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <功能详细描述> 跟踪实体类
 * @author JACK HUANG
 * @version [版本号, 2017年9月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_portal_track_log")
public class TrackEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 跟踪ID
     */
    @Column(name = "track_id")
    private String trackId;

    /**
     * 请求方法
     */
    @Column(name = "request_method")
    private String reqMethod;

    /**
     * 请求参数
     */
    @Column(name = "request_parm")
    private String reqParm;

    public String getTrackId()
    {
        return trackId;
    }

    public void setTrackId(String trackId)
    {
        this.trackId = trackId;
    }

    public String getReqMethod()
    {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod)
    {
        this.reqMethod = reqMethod;
    }

    public String getReqParm()
    {
        return reqParm;
    }

    public void setReqParm(String reqParm)
    {
        this.reqParm = reqParm;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("TrackEntity [trackId=").append(trackId).append(", reqMethod=").append(reqMethod)
                .append(", reqParm=").append(reqParm).append("]");
        return builder.toString();
    }

}
