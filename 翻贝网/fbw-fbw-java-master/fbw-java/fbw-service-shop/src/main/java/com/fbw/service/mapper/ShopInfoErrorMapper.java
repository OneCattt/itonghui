package com.fbw.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopInfoErrorEntity;

/**
 * 
 * <功能详细描述>商家报错mapepr
 * @author FBW0115
 * @version [版本号, 2017年9月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopInfoErrorMapper
{

    /**
     * 
     * <功能详细描述>保存商家报错信息
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertShopsInfoError(ShopInfoErrorEntity entity);

}
