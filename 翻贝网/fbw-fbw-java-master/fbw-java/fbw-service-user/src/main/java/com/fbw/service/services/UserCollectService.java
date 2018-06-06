package com.fbw.service.services;

import java.util.List;

import com.fbw.service.entity.user.UserCollectBusinEntity;

/**
 * 
 * <功能详细描述>用户收藏Service
 * @author jiangruliang
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserCollectService
{
    /**
     * 根据用户Id和店铺Id插入收藏表 <功能详细描述>
     * @param userId
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserCollect(Integer userId, Integer shopId);

    /**
     * 根据用户Id获取当前用户所有信息 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCollectBusinEntity> getUserCollectList(Integer userId, int begin);

    /**
     * 用户点击取消或添加收藏动作 <功能详细描述>
     * @param userId
     * @param shopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateUserCollect(Integer userId, Integer shopId, Integer status);

    /**
     * 用户首次点击收藏探店插入数据库 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserCollectInterviewShop(Integer userId, Integer interviewShopId);

    /**
     * 获取当前用户所有收藏探店id <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<String> getUserCollectInterviewShopList(Integer userId, int begin);

    /**
     * 用户点击取消或收藏探店 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateUserCollectInterviewShop(Integer userId, Integer interviewShopId, Integer status);

    /**
     * 判断当前商户是否被该用户收藏 <功能详细描述>
     * @param userId
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCollectBusinEntity getUserIsCollect(Integer userId, Integer shopId);

    /**
     * 判断当前探店是否被该用户收藏 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCollectBusinEntity getUserIsCollectInterviewShop(Integer userId, Integer interviewShopId);

    /**
     * 查询当前店铺收藏总数 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getCollectNumByShopId(Integer shopId);

    /**
     * 查询当前探店收藏总数 <功能详细描述>
     * @param interviewShopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getCollectInterviewNumByShopId(Integer interviewShopId);

}
