package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.exception.InnerException;

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
     * <功能详细描述> 更新个人 充值
     * @param rechargeEntity:cityId,mobile,money
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/updateUserGroundVaildRechargeInfo", method = RequestMethod.POST)
    public CommonRsEntity updateUserGroundVaildRechargeInfo(@RequestBody RegisterEntity registerEntity);

}
