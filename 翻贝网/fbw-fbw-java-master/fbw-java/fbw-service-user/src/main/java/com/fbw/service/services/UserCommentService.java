package com.fbw.service.services;

import java.util.List;
import java.util.Map;

import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.exception.InnerException;

public interface UserCommentService
{
    /**
     * 获取当前用户某一个订单的评论 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneUserCommentInfo(String orderId);

    /**
     * 获取一个最近的最好评论 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneRecentShopComment(Integer shopId);

    /**
     * 获取一个最近的最好评论详情 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneRecentShopCommentMore(Integer shopId);

    /**
     * 获取当前商店的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getOneShopCommentNum(Integer shopId);

    /**
     * 获取当前商家评论 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCommentBusinEntity> getOneShopAllComment(Map<String, Object> map);

    /**
     * 获取当前店铺带图评论的总数量 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getOneShopAllPicNum(Integer shopId);

    /**
     * 获取当前商家所有带图片评论 <功能详细描述>
     * @param shopId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCommentBusinEntity> getOneShopAllPicComment(Integer shopId, Integer begin);

    /**
     * 插入用户评论 <功能详细描述>
     * @param userCommentBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean insertUserComment(UserCommentBusinEntity userCommentBusinEntity) throws InnerException;

    /**
     * 获取当前店铺平均分 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCommentBusinEntity getOneShopAvgScore(Integer shopId);

    /**
     * 获取一个评分下的评论标签 <功能详细描述>
     * @param score
     * @return
     * @see [类、类#方法、类#成员]
     */
    String getOneScoreLabel(Integer score);

    /**
     * 自动评价功能 <功能详细描述>
     * @param orderNumber
     * @param userId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    void autoInsertUserComment(String orderNumber, Integer userId) throws InnerException;

}
