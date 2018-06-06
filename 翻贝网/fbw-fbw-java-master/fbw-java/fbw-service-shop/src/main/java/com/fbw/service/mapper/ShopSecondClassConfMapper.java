package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopSecondClassConfEntity;

/**
 * 
 * <功能详细描述>商户二级分类mapper
 * @author FBW0115
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopSecondClassConfMapper
{

    /**
     * 
     * <功能详细描述>根据一级id查询二级分类
     * @param firstClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopSecondClassConfEntity> getShopsSecondClass(int firstClassId);

}