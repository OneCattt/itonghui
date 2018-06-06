package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopSignEntity;

/**
 * 
 * <功能详细描述>招牌菜mapper
 * @author FBW0115
 * @version [版本号, 2017年8月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopSignMapper
{
    /**
     * 
     * <功能详细描述>根据shopId查询招牌菜
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopSignEntity> getShopsSignByShopId(int shopId);

}