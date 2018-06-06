package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopQualityEntity;

/**
 * 
 * <功能详细描述>品质好店mapper
 * @author FBW0115
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopQualityMapper
{
    /**
     * 
     * <功能详细描述>首页根据城市id查询品质好店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopQualityEntity> getShopsQualityByCityId(int cityId);

    /**
     * 
     * <功能详细描述>根据城市id查询品质好店列表分页
     * @param cityId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopQualityEntity> getShopsQualityAll(int param1, int param2);

    /**
     * 
     * <功能详细描述>查询所有的品质好店的shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Integer> getShopsQualityShopId();

}