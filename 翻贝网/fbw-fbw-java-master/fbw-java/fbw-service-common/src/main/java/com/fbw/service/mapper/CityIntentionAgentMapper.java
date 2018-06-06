package com.fbw.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.common.CityIntentionAgentEntity;

/**
 * 
 * <功能详细描述>城市代理申请mapper
 * @author FBW0115
 * @version [版本号, 2017年9月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface CityIntentionAgentMapper
{
    /**
     * 
     * <功能详细描述>保存城市代理申请
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertCityAgent(CityIntentionAgentEntity entity);
}