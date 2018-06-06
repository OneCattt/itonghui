package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.common.CityBusinEntity;

/**
 * 
 * <功能详细描述>城市处理mapper
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface CityBusinMapper
{

    /**
     * 
     * <功能详细描述>获取城市列表
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<CityBusinEntity> getCityList();

    /**
     * 
     * <功能详细描述>根据cityId获取城市信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    CityBusinEntity getCityInfoByCityId(int cityId);

}