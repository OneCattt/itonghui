package com.fbw.service.enums;

import com.fbw.service.entity.user.UserConstant;

/**
 * 
 * <功能详细描述> 公共套件枚举类
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum UserEnums
{

    /**
     * 消费2元
     */
    USER_CONSUME_TWO_YUAN(UserConstant.USER_CONSUME_TWO_YUAN, 1),
    /**
     * 评论十字以上
     */
    USER_MORE_THAN_TEN_WORD(UserConstant.USER_MORE_THAN_TEN_WORD, 5),
    /**
     * 带图评论
     */
    USER_PIC_COMMENT(UserConstant.USER_PIC_COMMENT, 5),
    /**
     * 带图评论以及十字以上
     */
    USER_PIC_COMMENT_AND_TEN_WORD(UserConstant.USER_PIC_COMMENT_AND_TEN_WORD, 10);
    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private Integer value;

    /**
     * 
     * @param key
     * @param value <默认构造函数>
     */
    private UserEnums(String key, Integer value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * <一句话功能简述> 获取key对应的值 <功能详细描述>
     * @param errorCode
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Integer getMsg(String key)
    {
        for (UserEnums c : UserEnums.values())
        {
            if (c.getKey().equals(key))
            {
                return c.value;
            }
        }
        return null;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }
}
