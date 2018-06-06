package com.fbw.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopEventEntity;

/**
 * 
 * <功能详细描述>翻倍日mapper
 * @author FBW0115
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopEventMapper
{

    /**
     * 
     * <功能详细描述>查询最近翻倍日
     * @param shopid
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopEventEntity getShopsEventsOne(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>查询翻倍日
     * @param map(shopId、date)
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopEventEntity> getShopsEventsByDate(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>根据shopId获取当前翻倍日
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopEventEntity getShopsNowEventsByShopId(int shopId);

    /**
     * 
     * <功能详细描述>根据shopId和event取消翻倍日
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopEventStatus(ShopEventEntity entity);
}