package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopBannerEntity;

/**
 * 
 * <功能详细描述>首页bannermapper
 * @author FBW0115
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopBannerMapper
{

    /**
     * 
     * <功能详细描述>根据城市id查询首页banner
     * @param1 城市
     * @param1 bannner位置类型
     * @param1 firstClassId 一级分类id
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopBannerEntity> getBannerByCityId(int param1, int param2, int param3);
}
