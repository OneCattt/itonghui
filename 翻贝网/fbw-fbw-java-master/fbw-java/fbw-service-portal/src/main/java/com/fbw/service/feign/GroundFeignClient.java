package com.fbw.service.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbw.service.config.feign.FeignDisableHystrixConfiguration;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundRegisterUserDetailBusinEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
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
     * 
     * <功能详细描述> 更新个人地推无效注册信息(更新注册数)
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/bindingPersonalInVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingPersonalInVaildRegister(@RequestBody RegisterEntity registerEntity);

    /**
     * 
     * <功能详细描述>更新个人地推有效注册信息(更新注册数，有效注册数，注册收入，总收入)
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/bindingPersonalVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingPersonalVaildRegister(@RequestBody RegisterEntity registerEntity);

    /**
     * 
     * <功能详细描述> 更新商户地推注册信息(更新注册数)
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundShopInfo/bindingShopInVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingShopInVaildRegister(@RequestBody RegisterEntity registerEntity);

    /**
     * 
     * <功能详细描述>更新商户地推注册信息(更新注册数，有效注册数，注册收入，账户余额)
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundShopInfo/bindingShopVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingShopVaildRegister(@RequestBody RegisterEntity registerEntity);

    /**
     * 
     * <功能详细描述> 通过地推ID查询个人地推信息
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/queryPersonalGroundBySalesManId", method = RequestMethod.GET)
    public GroundPersonalInfoBusinEntity queryPersonalGroundBySalesManId(@RequestParam("salesManId") String salesManId);

    /**
     * 
     * <功能详细描述> 通过地推ID查询商户地推信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/queryShopGroundBySalesManId", method = RequestMethod.GET)
    public GroundShopInfoBusinEntity queryShopGroundBySalesManId(@RequestParam("salesManId") String salesManId);

    /**
     * 
     * <功能详细描述> 保存注册用户详细地推信息
     * @param groundRegisterUserDetailBusin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ground/queryShopGroundBySalesManId", method = RequestMethod.POST)
    public boolean saveRegisterUserDetailWithGround(
            @RequestBody GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusin);

    /**
     * 查询是否存在手机号 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundRegisterUserDetail/selectIsExistMobile", method = RequestMethod.GET)
    public boolean selectIsExistMobile(@RequestParam("mobile") String mobile);

    /**
     * 更新个人无效注册信息 <功能详细描述>
     * @param registerEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/updateInVaildPersonalRegGroundInfo", method = RequestMethod.POST)
    public CommonRsEntity updateInVaildPersonalRegGroundInfo(@RequestBody RegisterEntity registerEntity);

    /**
     * 更新个人有效地推信息 <功能详细描述>
     * @param registerEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/updateVaildPersonalRegGroundInfo", method = RequestMethod.POST)
    public CommonRsEntity updateVaildPersonalRegGroundInfo(@RequestBody RegisterEntity registerEntity);

    /**
     * 
     * <功能详细描述> 更新邀请码渠道无效的地推注册用户
     * @param registerEntity
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/updateInVaildGroundRegUserWithInviteCode", method = RequestMethod.POST)
    public CommonRsEntity updateInVaildGroundRegUserWithInviteCode(@RequestBody RegisterEntity registerEntity);

    /**
     * 
     * <功能详细描述> 更新邀请码渠道有效的地推注册用户
     * @param registerEntity
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/updateVaildGroundRegUserWithInviteCode", method = RequestMethod.POST)
    public CommonRsEntity updateVaildGroundRegUserWithInviteCode(@RequestBody RegisterEntity registerEntity);

    /**
     * <功能详细描述> 更新个人/商家 订单
     * @param rechargeEntity:cityId,mobile,money
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/groundPersonInfo/updateUserGroundVaildOrderInfo", method = RequestMethod.POST)
    public CommonRsEntity updateUserGroundVaildOrderInfo(@RequestBody RegisterEntity registerEntity);

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
