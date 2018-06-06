package com.fbw.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopDetailEntity;

/**
 * 
 * <功能详细描述>商户详细信息mapper
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopDetailMapper
{
    /**
     * 
     * <功能详细描述>根据shopid查看商户详情
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopDetailEntity getShopsDetailByShopid(int shopId);

}
