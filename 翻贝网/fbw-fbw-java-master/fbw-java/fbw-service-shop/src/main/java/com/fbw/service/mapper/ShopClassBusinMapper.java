package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopClassBusinEntity;

/**
 * 
 * <功能详细描述>首页分类mapper
 * @author FBW0115
 * @version [版本号, 2017年8月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopClassBusinMapper
{
    /**
     * 
     * <功能详细描述>查询首页分类列表
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopClassBusinEntity> getShopsClass(int cityId);

}