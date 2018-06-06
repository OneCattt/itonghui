package com.fbw.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.common.CityIntentionAgentEntity;
import com.fbw.service.entity.common.CityBusinEntity;
import com.fbw.service.serivces.CityBusinService;

/**
 * 
 * <功能详细描述>城市处理congroller
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "city")
public class CityInfoController extends BaseController
{
    @Autowired
    private CityBusinService cityBusinService;

    @RequestMapping(value = "/getCityList", method = RequestMethod.GET)
    public List<CityBusinEntity> getCityList()
    {
        return cityBusinService.getCityList();
    }

    /**
     * 
     * <功能详细描述>根据cityId获取城市信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getCityInfoByCityId", method = RequestMethod.GET)
    public CityBusinEntity getCityInfoByCityId(int cityId)
    {
        return cityBusinService.getCityInfoByCityId(cityId);
    }

    /**
     * 
     * <功能详细描述>保存城市代理申请
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveCityAgent", method = RequestMethod.POST)
    public int saveCityAgent(@RequestBody CityIntentionAgentEntity entity)
    {
        return cityBusinService.insertCityAgent(entity);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
