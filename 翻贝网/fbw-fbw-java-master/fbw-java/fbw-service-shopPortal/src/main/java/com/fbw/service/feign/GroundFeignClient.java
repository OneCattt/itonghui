package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;

/**
 * 
 * <功能详细描述> 充值套件的Fegin的客户端
 * @author JACK HUANG
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-ground", configuration = FeignDisableHystrixConfiguration.class)
public interface GroundFeignClient
{
    /**
     * 
     * <功能详细描述> 更新个人地推注册信息
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/updatePersonalGroundRegInfo", method = RequestMethod.POST)
    public boolean updatePersonalGroundRegInfo(@RequestParam("salesManId") int salesManId);

    /**
     * 
     * <功能详细描述>更新个人地推有效注册和注册信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/updatePersonalGroundRegInfo", method = RequestMethod.POST)
    public boolean updatePersonalGroundWithVaildRegAndRegInfo(@RequestParam("salesManId") int salesManId);

    /**
     * 
     * <功能详细描述> 更新商户地推注册信息
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/updateShopGroundRegInfo", method = RequestMethod.POST)
    public boolean updateShopGroundRegInfo(@RequestParam("shopId") int shopId);

    /**
     * 
     * <功能详细描述>更新商户地推有效注册和注册信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/updateShopGroundWithVaildRegAndRegInfo", method = RequestMethod.POST)
    public boolean updateShopGroundWithVaildRegAndRegInfo(@RequestParam("shopId") int shopId);

    /**
     * 
     * <功能详细描述> 通过地推ID查询个人地推信息
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/queryPersonalGroundBySalesManId", method = RequestMethod.GET)
    public GroundPersonalInfoBusinEntity queryPersonalGroundBySalesManId(@RequestParam("salesManId") int salesManId);

    /**
     * 
     * <功能详细描述> 通过地推ID查询商户地推信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/queryShopGroundBySalesManId", method = RequestMethod.GET)
    public GroundShopInfoBusinEntity queryShopGroundBySalesManId(@RequestParam("salesManId") int salesManId);
}
