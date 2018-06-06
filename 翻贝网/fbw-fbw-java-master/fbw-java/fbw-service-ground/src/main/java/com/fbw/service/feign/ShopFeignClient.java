package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.shop.ShopDetailEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;

/**
 * 
 * <功能详细描述>shop套件feign客户端
 * @author FBW0115
 * @version [版本号, 2017年8月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-shop", configuration = FeignDisableHystrixConfiguration.class)
public interface ShopFeignClient
{

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsInfoByShopId", method = RequestMethod.GET)
    ShopInfoEntity getShopsInfoByShopId(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsInfoByCode", method = RequestMethod.GET)
    ShopInfoEntity getShopsInfoByCode(@RequestBody HomeReqEntity entity);

    /**
     * 
     * <功能详细描述>根据shopid查看商户详情
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/shop/getShopsDetailByShopid", method = RequestMethod.GET)
    ShopDetailEntity getShopsDetailByShopid(@RequestBody HomeReqEntity entity);

}
