package com.fbw.service.enums;

import com.fbw.service.entity.user.UserConstant;

public enum UserTypeEnums
{
    /**
     * 消费订单
     */
    USER_CONSUME_ORDER(UserConstant.USER_CONSUME_ORDER, UserConstant.USER_GET_POINT),
    /**
     * 评论十字以上
     */
    USER_COMMENT_TEN_WORD(UserConstant.USER_COMMENT_TEN_WORD, UserConstant.USER_GET_POINT),
    /**
     * 带图评论
     */
    USER_PICTURE_COMMENT(UserConstant.USER_PICTURE_COMMENT, UserConstant.USER_GET_POINT),
    /**
     * 评论十字以上以及带图
     */
    USER_PICTURE_AND_TEN_WORD_COMMENT(UserConstant.USER_PICTURE_AND_TEN_WORD_COMMENT, UserConstant.USER_GET_POINT),
    /**
     * 会员消费日双倍
     */
    USER_VIP_CONSUME_ORDER_DOUBLE(UserConstant.USER_VIP_CONSUME_ORDER_DOUBLE, UserConstant.USER_USE_POINT),
    /**
     * 积分交换物品
     */
    USER_POINT_EXCHANGE_GOODS(UserConstant.USER_POINT_EXCHANGE_GOODS, UserConstant.USER_USE_POINT),
    /**
     * 积分抽奖
     */
    USER_ROTARY_LUCKY_DRAW(UserConstant.USER_ROTARY_LUCKY_DRAW, UserConstant.USER_USE_POINT);
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
    private UserTypeEnums(String key, Integer value)
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
        for (UserTypeEnums c : UserTypeEnums.values())
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
