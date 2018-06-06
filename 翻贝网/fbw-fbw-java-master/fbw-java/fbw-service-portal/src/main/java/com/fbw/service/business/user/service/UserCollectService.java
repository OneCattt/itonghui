package com.fbw.service.business.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.user.UserCollectBusinEntity;
import com.fbw.service.feign.UserFeignClient;

/**
 * 用户收藏Service <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserCollectService
{
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取当前用户所有收藏列表 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserCollectBusinEntity> getUserCollectList(Integer userId, Integer begin)
    {
        return userFeignClient.getUserCollectList(userId, begin);
    }

    /**
     * 获取当前用户所有收藏探店id <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<String> getUserCollectInterviewShopList(Integer userId, Integer begin)
    {
        return userFeignClient.getUserCollectInterviewShopList(userId, begin);

    }

    /**
     * 删除或点击收藏的店铺 <功能详细描述>
     * @param userId
     * @param shopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    public CommonRsEntity updateUserCollect(Integer userId, Integer shopId, Integer status)
    {
        return userFeignClient.updateUserCollect(userId, shopId, status);
    }

    /**
     * 
     * <功能详细描述>用户点击取消或收藏探店
     * @param userid
     * @param interviewShopId
     * @see [类、类#方法、类#成员]
     */
    public CommonRsEntity updateUserCollectInterviewShop(Integer userId, Integer interviewShopId, Integer status)
    {
        return userFeignClient.updateUserCollectInterviewShop(userId, interviewShopId, status);

    }

    /**
     * 
     * <功能详细描述>判断当前商户是否被该用户收藏
     * @param userid
     * @param shopid
     * @see [类、类#方法、类#成员]
     */
    public int getUserIsCollect(Integer userId, Integer shopId)
    {
        return userFeignClient.getUserIsCollect(userId, shopId);
    }

    /**
     * 
     * <功能详细描述>判断当前探店是否被该用户收藏
     * @param userid
     * @param shopid
     * @see [类、类#方法、类#成员]
     */

    public int getUserIsCollectInterviewShop(Integer userId, Integer interviewShopId)
    {
        return userFeignClient.getUserIsCollectInterviewShop(userId, interviewShopId);

    }

}
