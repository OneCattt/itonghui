package com.fbw.service.serivces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.common.CityIntentionAgentEntity;
import com.fbw.service.entity.common.CityBusinEntity;
import com.fbw.service.mapper.CityIntentionAgentMapper;
import com.fbw.service.mapper.CityBusinMapper;

/**
 * 
 * <功能详细描述>城市处理service
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class CityBusinService
{
    @Autowired
    private CityBusinMapper cityBusinMapper;

    @Autowired
    private CityIntentionAgentMapper cityAgentEntityMapper;

    /**
     * 
     * <功能详细描述>获取城市列表
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<CityBusinEntity> getCityList()
    {
        return cityBusinMapper.getCityList();
    }

    /**
     * 
     * <功能详细描述>根据cityId获取城市信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public CityBusinEntity getCityInfoByCityId(int cityId)
    {
        return cityBusinMapper.getCityInfoByCityId(cityId);
    }

    /**
     * 
     * <功能详细描述>保存城市代理申请
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int insertCityAgent(CityIntentionAgentEntity entity)
    {
        return cityAgentEntityMapper.insertCityAgent(entity);
    }

}
