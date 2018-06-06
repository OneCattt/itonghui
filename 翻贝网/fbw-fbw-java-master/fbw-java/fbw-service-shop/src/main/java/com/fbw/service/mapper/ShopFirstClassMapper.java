package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopFirstClassEntity;

/**
 * 
 * <功能详细描述>商户一级分类mapper
 * @author FBW0115
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopFirstClassMapper
{

    /**
     * 
     * <功能详细描述>根据cityid查询一级分类
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopFirstClassEntity> getShopsFirstClassList(int cityId);

}