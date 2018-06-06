package com.fbw.service.contents;

/**
 * 
 * <一句话功能简述> 公共套件常量类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserConstant
{
    /**
     * 身份证号错误
     */
    static final String USER_ID_CARD_ERROR = "11000";

    /**
     * 姓名错误
     */
    static final String USER_REAL_NAME_ERROR = "11001";

    /**
     * 身份证号或姓名错误
     */
    static final String USER_REAL_NAME_ID_CARD_ERROR = "11002";

    /**
     * 身份证号重复
     */
    static final String USER_ID_CARD_REPEAT = "11003";

    /**
     * 插入用户评论失败
     */
    static final String USER_COMMENT_DB_ERROR = "11004";

    /**
     * 插入用户评论状态（订单表）失败
     */
    static final String USER_ORDER_COMMENT_STATUS_DB_ERROR = "11005";

    /**
     * 删除或点击收藏的店铺失败
     */
    static final String USER_COLLECT_SHOP_ERROR = "11006";

    /**
     * 删除或点击收藏的探店失败
     */
    static final String USER_COLLECT_INTERVIEW_SHOP_ERROR = "11007";

}
