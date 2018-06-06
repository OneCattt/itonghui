package com.fbw.service.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserCollectBusinEntity;

/**
 * 用户收藏mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserCollectBusinEntityMapper
{

    /**
     * 用户首次点击收藏店铺插入数据库 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserCollect(Integer param1, Integer param2);

    /**
     * 获取当前用户所有收藏店铺id <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCollectBusinEntity> getUserCollectList(Integer param1, Integer param2);

    /**
     * 用户点击取消或收藏操作，但数据库只改变状态 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateUserCollect(Integer param1, Integer param2, Integer param3);

    /**
     * 用户首次点击收藏探店插入数据库 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserCollectInterviewShop(Integer param1, Integer param2);

    /**
     * 获取当前用户所有收藏探店id <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<String> getUserCollectInterviewShopList(Integer param1, Integer param2);

    /**
     * 用户点击取消或者收藏探店 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateUserCollectInterviewShop(Integer param1, Integer param2, Integer param3);

    /**
     * 判断当前商户是否被该用户收藏 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCollectBusinEntity getUserIsCollect(Integer param1, Integer param2);

    /**
     * 判断当前探店是否被该用户收藏 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserCollectBusinEntity getUserIsCollectInterviewShop(Integer param1, Integer param2);

    /**
     * 查询当前店铺收藏总数 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getCollectNumByShopId(Integer param1);

    /**
     * 查询当前探店收藏总数 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getCollectInterviewNumByShopId(Integer param1);

    /**
     * 查询所有店铺收藏量 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCollectBusinEntity> getAllShopCollectNumber();
}