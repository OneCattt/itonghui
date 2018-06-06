package com.fbw.service.entity.common;

import java.io.Serializable;

/**
 * 
 * <一句话功能简述> 字典表实体类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DictEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 字典key
     */
    private String dictKey;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 生效状态 0表示生效 1表示失效
     */
    private String status;

    private Integer trackId;

    /**
     * 
     * @param dictKey
     * @param status <默认构造函数>
     */
    public DictEntity(String dictKey, String status)
    {
        this.dictKey = dictKey;
        this.status = status;
    }

    public String getDictKey()
    {
        return dictKey;
    }

    public void setDictKey(String dictKey)
    {
        this.dictKey = dictKey;
    }

    public String getDictValue()
    {
        return dictValue;
    }

    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
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

    @Override
    public String toString()
    {
        return "DictEntity [dictKey=" + dictKey + ", dictValue=" + dictValue + ", status=" + status + ", trackId="
                + trackId + "]";
    }

}
