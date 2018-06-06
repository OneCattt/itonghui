package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopBusinessAreaEntity;

/**
 * 
 * <功能详细描述>商圈mapper
 * @author FBW0115
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopBusinessAreaMapper
{

    /**
     * 
     * <功能详细描述>根据地区id获取商圈
     * @param districtId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopBusinessAreaEntity> getShopsBusinessArea(int districtId);

}