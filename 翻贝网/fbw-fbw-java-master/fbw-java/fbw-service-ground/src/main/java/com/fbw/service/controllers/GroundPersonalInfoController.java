package com.fbw.service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundRegisterUserDetailBusinEntity;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.GroundPersonalInfoBusinMapper;
import com.fbw.service.mapper.GroundRegisterUserDetailBusinEntityMapper;
import com.fbw.service.service.GroundPersonalInfoService;
import com.fbw.service.service.GroundRechargeOrderService;
import com.fbw.service.service.GroundRegisterService;

@RestController
@RequestMapping("/groundPersonInfo")
public class GroundPersonalInfoController extends BaseController
{
    @Autowired
    private GroundPersonalInfoService groundPersonalInfoService;

    @Autowired
    private GroundPersonalInfoBusinMapper groundPersonalInfoBusinMapper;

    @Autowired
    private GroundRegisterUserDetailBusinEntityMapper groundRegisterUserDetailBusinEntityMapper;

    @Autowired
    private GroundRegisterService groundRegisterService;

    @Autowired
    private GroundRechargeOrderService groundRechargeOrderService;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 根据城市id查询所有地推人员,根据手机号模糊查询该城市地推人员 <功能详细描述>
     * @param cityId
     * @param begin
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectByCityId")
    public Map<String, Object> selectByCityId(String cityId, String mobile, Integer begin, Integer end)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityId", cityId);
        map.put("begin", begin);
        map.put("mobile", mobile);
        map.put("end", end);
        List<GroundPersonalInfoBusinEntity> groundPersonalInfos = groundPersonalInfoService.selectByCityId(map);
        int count = groundPersonalInfoBusinMapper.selectCountByCityId(map);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("groundPersonalInfos", groundPersonalInfos);
        map1.put("count", count);
        return map1;
    }

    /**
     * 通过cityId查询个人地推列表上方合计内容，进入个人详情页面时传入salesmanId <功能详细描述>
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectAllByCityId")
    public Map<String, Object> selectAllByCityId(String cityId, String salesmanId)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        GroundPersonalInfoBusinEntity groundPersonalInfo = groundPersonalInfoBusinMapper.selectCount(cityId,
                salesmanId);
        GroundRegisterUserDetailBusinEntity groundRegisterUserDetail = groundRegisterUserDetailBusinEntityMapper
                .selectCount(cityId, salesmanId);
        if (null != groundPersonalInfo)
        {
            map.put("registerAmount", groundPersonalInfo.getRegisterAmount());
            map.put("validOrderAmount", groundPersonalInfo.getValidOrderAmount());// ground_order_amount
            map.put("validRechargeAmount", groundPersonalInfo.getValidRechargeAmount());
            map.put("groundTotalFee", groundPersonalInfo.getGroundTotalFee());
            map.put("id", groundPersonalInfo.getId());
        }
        if (null != groundRegisterUserDetail)
        {
            map.put("orderAmount", groundRegisterUserDetail.getOrderAmount());
            map.put("groundOrderActivityFee", groundRegisterUserDetail.getGroundOrderActivityFee());
        }

        return map;

    }

    /**
     * 新增一个用户地推人员 <功能详细描述>
     * @param cityId
     * @param name
     * @param mobile
     * @param remark
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveGroundPersonalInfo", method = RequestMethod.POST)
    public int saveGroundPersonalInfo(String cityId, String name, String mobile, String remark, String groundId)
            throws InnerException
    {
        // 判断手机号是否存在或已是地推人员
        int status = groundPersonalInfoService.checkMobile(mobile);
        if (0 != status)
        {
            return status;
        }
        // 保存前端填写数据
        int savaStatus = groundPersonalInfoService.saveGroundPersonal(cityId, name, mobile, remark, groundId);
        if (savaStatus == 1)
        {
            status = 1;
            return status;
        }
        return status;
    }

    /**
     * 更新个人地推状态 <功能详细描述>
     * @param salesmanId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updatePersonalGroundStatus", method = RequestMethod.POST)
    public boolean updatePersonalGroundStatus(String salesmanId, int status)
    {
        boolean istrue = false;
        // status=1:地推状态可用 status=0:地推状态不可用
        if (status == 1)
        {
            int flag = groundPersonalInfoService.updatePersonalGroundStatus(salesmanId, "0");
            if (flag == 1)
            {
                istrue = true;
            }
        }
        if (status == 0)
        {
            int flag = groundPersonalInfoService.updatePersonalGroundStatus(salesmanId, "1");
            if (flag == 1)
            {
                istrue = true;
            }
        }
        return istrue;
    }

    /**
     * 更新用户地推二维码 <功能详细描述>
     * @param salesmanId
     * @param salesmanQrCode
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updatePersonalGroundQrCode", method = RequestMethod.POST)
    public boolean updatePersonalGroundQrCode(String salesmanId, String salesmanQrCode)
    {
        int flag = groundPersonalInfoService.updatePersonalGroundQrCode(salesmanId, salesmanQrCode);
        if (flag == 1)
        {
            return true;
        }
        return false;

    }

    /**
     * 
     * <功能详细描述> 绑定个人无效信息
     * @param rechargeEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/bindingPersonalInVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingPersonalInVaildRegister(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.bindingPersonalInVaildRegister(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;

    }

    /**
     * 
     * <功能详细描述>绑定个人有效信息
     * @param salesManId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/bindingPersonalVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingPersonalVaildRegister(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.bindingPersonalVaildRegister(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;
    }

    /**
     * 更新个人无效注册信息 <功能详细描述>
     * @param registerEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateInVaildPersonalRegGroundInfo", method = RequestMethod.POST)
    public CommonRsEntity updateInVaildPersonalRegGroundInfo(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.updateInVaildPersonalRegGroundInfo(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;
    }

    /**
     * 更新个人有效地推信息 <功能详细描述>
     * @param registerEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateVaildPersonalRegGroundInfo", method = RequestMethod.POST)
    public CommonRsEntity updateVaildPersonalRegGroundInfo(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.updateVaildPersonalRegGroundInfo(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;

    }

    /**
     * 
     * <功能详细描述> 更新邀请码渠道无效的地推注册用户
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateInVaildGroundRegUserWithInviteCode", method = RequestMethod.POST)
    public CommonRsEntity updateInVaildGroundRegUserWithInviteCode(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.updateInVaildGroundRegUserWithInviteCode(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;
    }

    /**
     * 
     * <功能详细描述> 更新邀请码渠道有效的地推注册用户
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateVaildGroundRegUserWithInviteCode", method = RequestMethod.POST)
    public CommonRsEntity updateVaildGroundRegUserWithInviteCode(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.updateVaildGroundRegUserWithInviteCode(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;
    }

    /**
     * <功能详细描述> 更新个人/商户 有效订单
     * @param rechargeEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserGroundVaildOrderInfo", method = RequestMethod.POST)
    public CommonRsEntity updateUserGroundVaildOrderInfo(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        UserGroundInfoBusinEntity userGroundInfoBusinEntity = userFeignClient
                .queryUserGroundInfo(registerEntity.getMobile());
        if (null == userGroundInfoBusinEntity)
        {
            commonRsEntity.setErrorCode(ErrorMsgConstant.GROUND_GET_USER_INFO_FAIL);
            commonRsEntity.setErrorMessage("通过手机号查询用户信息失败");
            commonRsEntity.setOperationFlag(false);
            return commonRsEntity;
        }
        if (userGroundInfoBusinEntity.getGroundType().equals("1"))
        {
            registerEntity.setSalesManId(userGroundInfoBusinEntity.getSalesmanId());
            registerEntity.setGroundType(userGroundInfoBusinEntity.getGroundType());
            try
            {
                groundRechargeOrderService.updateUserGroundVaildOrderInfo(registerEntity);
            }
            catch (InnerException e)
            {
                commonRsEntity.setErrorCode(e.getErrorCode());
                commonRsEntity.setErrorMessage(e.getMessage());
                commonRsEntity.setOperationFlag(false);
            }
        }
        if (userGroundInfoBusinEntity.getGroundType().equals("2"))
        {
            registerEntity.setGroundType(userGroundInfoBusinEntity.getGroundType());
            registerEntity.setShopAssistantId(userGroundInfoBusinEntity.getShopAssistantId());
            registerEntity.setShopId(userGroundInfoBusinEntity.getShopId());
            try
            {
                groundRechargeOrderService.updateShopGroundVaildOrderInfo(registerEntity);
            }
            catch (InnerException e)
            {
                commonRsEntity.setErrorCode(e.getErrorCode());
                commonRsEntity.setErrorMessage(e.getMessage());
                commonRsEntity.setOperationFlag(false);
            }

        }

        return commonRsEntity;
    }

    /**
     * <功能详细描述> 更新个人 有效充值
     * @param rechargeEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserGroundVaildRechargeInfo", method = RequestMethod.POST)
    public CommonRsEntity updateUserGroundVaildRechargeInfo(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        UserGroundInfoBusinEntity userGroundInfoBusinEntity = userFeignClient
                .queryUserGroundInfo(registerEntity.getMobile());
        if (null == userGroundInfoBusinEntity)
        {
            commonRsEntity.setErrorCode(ErrorMsgConstant.GROUND_GET_USER_INFO_FAIL);
            commonRsEntity.setErrorMessage("通过手机号查询用户信息失败");
            commonRsEntity.setOperationFlag(false);
            return commonRsEntity;
        }
        registerEntity.setSalesManId(userGroundInfoBusinEntity.getSalesmanId());
        registerEntity.setGroundType(userGroundInfoBusinEntity.getGroundType());
        try
        {
            groundRechargeOrderService.updateUserGroundVaildRechargeInfo(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
