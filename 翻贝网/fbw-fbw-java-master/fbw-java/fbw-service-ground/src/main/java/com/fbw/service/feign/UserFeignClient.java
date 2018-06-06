package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.exception.InnerException;

/**
 * 
 * <功能详细描述> 用户套件的feign客户端
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "fbw-service-user", configuration = FeignDisableHystrixConfiguration.class)
public interface UserFeignClient
{
    /**
     * 
     * <功能详细描述>更新余额信息
     * @param mobile 手机号
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/updateUserbalance?mobile={mobile}&balance={balance}&newBalance={newBalance}", method = RequestMethod.POST)
    public boolean updateUserbalance(@PathVariable("mobile") String mobile, @PathVariable("balance") String balance,
            @PathVariable("newBalance") String newBalance);

    /**
     * 
     * <功能详细描述> 查询用户余额信息
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/queryUserBalance?mobile={mobile}", method = RequestMethod.GET)
    public String queryUserBalance(@PathVariable("mobile") String mobile);

    /**
     * 
     * <功能详细描述>查询是否存在手机号
     * @param deviceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/selectIsExistMobile", method = RequestMethod.GET)
    public boolean selectIsExistMobile(@RequestParam("mobile") String mobile);

    /**
     * 
     * <功能详细描述>查询用户地推信息
     * @param mobile
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/queryUserGroundInfo", method = RequestMethod.GET)
    public UserGroundInfoBusinEntity queryUserGroundInfo(@RequestParam("mobile") String mobile);

    /**
     * 通过用户id获取用户详细信息 <功能详细描述>
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/getUserBaseInfoById", method = RequestMethod.GET)
    public UserInfoBusinEntity getUserBaseInfoById(@RequestParam("id") Integer id);

}
