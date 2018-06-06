package com.fbw.service.business.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.user.UserCommentBannerConfEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.feign.UserFeignClient;

/**
 * 用户评论Service <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserCommentService
{
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取当前用户某一个订单的评论 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserCommentBusinEntity getOneUserCommentInfo(String orderId)
    {
        return userFeignClient.getOneUserCommentInfo(orderId);
    }

    /**
     * 获取当前店铺最近最好的评价内容<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserCommentBusinEntity getOneRecentShopComment(Integer shopId)
    {
        return userFeignClient.getOneRecentShopComment(shopId);
    }

    /**
     * 获取当前商店的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Integer getOneShopCommentNum(Integer shopId)
    {
        return userFeignClient.getOneShopCommentNum(shopId);
    }

    /**
     * 获取当前商店所有评论,根据isNoPic判断是否带图片 <功能详细描述>
     * @param shopId
     * @param begin
     * @param isNoPic
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserCommentBusinEntity> getOneShopAllComment(Integer shopId, Integer begin,
            UserCommentBannerConfEntity userCommentBanner)
    {
        return userFeignClient.getOneShopAllComment(shopId, begin, userCommentBanner);
    }

    /**
     * 获取当前商店带图片的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Integer getOneShopAllPicNum(Integer shopId)
    {
        return userFeignClient.getOneShopAllPicNum(shopId);
    }

    /**
     * 插入用户评论 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public CommonRsEntity insertUserComment(UserCommentBusinEntity userCommentBusinEntity)
    {
        return userFeignClient.insertUserComment(userCommentBusinEntity);

    }

}
