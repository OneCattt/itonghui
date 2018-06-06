package com.fbw.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopInfoEntity;

/**
 * 
 * <功能详细描述>商户信息mapper
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopInfoMapper
{

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopInfoEntity getShopsInfoByShopIdOne(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息(带距离)
     * @param latItude
     * @param longItude
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopInfoEntity getShopsInfoByShopIdTwo(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param latItude
     * @param longItude
     * @param code
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopInfoEntity getShopsInfoByCode(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>查询附近的店
     * @param latItude 纬度
     * @param longItude 经度
     * @param page 分页，0开始
     * @return
     * @see [类、类#方法、类#成员]
     */
    // List<ShopsInfoEntity> getNearbyShops(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>搜索
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getSearchShop(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>筛选
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getScreenShops(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>筛选(需要根据翻倍日)
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getScreenShopsByevent(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>获取新店速递
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    // List<ShopsInfoEntity> getNewShopsByCityId(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>根据探店id查询提到的店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getInterviewChild(int interviewId);

    /**
     * 根据shopId获取商家名称 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopInfoEntity getShopsNameByShopId(int shopId);

    /**
     * 
     * <功能详细描述>查询新店数量
     * @param cityId
     * @param firstClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getNewShopNum(Map<String, Object> map);

}
