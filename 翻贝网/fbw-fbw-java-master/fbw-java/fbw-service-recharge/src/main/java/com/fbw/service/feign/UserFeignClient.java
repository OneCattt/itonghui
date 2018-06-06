package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.user.UserBalanceDetailEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;

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
     * <功能详细描述> 插入用户余额明细
     * @param userBalanceDetail
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/userOrder/insertUserBalanceDetail", method = RequestMethod.POST)
    public boolean insertUserBalanceDetail(@RequestBody UserBalanceDetailEntity userBalanceDetail);

    /**
     * 
     * <功能详细描述>获取用户基本信息
     * @param mobile 手机号
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/user/getUserBaseInfo?mobile={mobile}", method = RequestMethod.GET)
    public UserInfoBusinEntity getUserBaseInfo(@PathVariable("mobile") String mobile);

}
