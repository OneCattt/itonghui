package com.fbw.service.entity;

/**
 * 
 * <一句话功能简述> 字典表实体类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DictEntity
{

    /**
     * 字典key
     */
    private String dictKey;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 描述
     */
    private String dictDes;

    /**
     * 类型
     */
    private String dictType;

    /**
     * 字典类型描述
     */
    private String dictTypeDes;

    /**
     * 生效状态 0表示生效 1表示失效
     */
    private String status;

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

    public String getDictDes()
    {
        return dictDes;
    }

    public void setDictDes(String dictDes)
    {
        this.dictDes = dictDes;
    }

    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getDictTypeDes()
    {
        return dictTypeDes;
    }

    public void setDictTypeDes(String dictTypeDes)
    {
        this.dictTypeDes = dictTypeDes;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("DictEntity [dictKey=").append(dictKey).append(", dictValue=").append(dictValue)
                .append(", dictDes=").append(dictDes).append(", dictType=").append(dictType).append(", dictTypeDes=")
                .append(dictTypeDes).append(", status=").append(status).append("]");
        return builder.toString();
    }

}
