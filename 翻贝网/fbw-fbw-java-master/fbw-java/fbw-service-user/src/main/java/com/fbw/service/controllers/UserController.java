package com.fbw.service.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.contents.UserConstant;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.user.UserDeviceInfoEntity;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserPointDetailEntity;
import com.fbw.service.entity.user.UserRedPacketBusinEntity;
import com.fbw.service.entity.user.UserShopFeedbackBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mappers.UserDeviceInfoEntityMapper;
import com.fbw.service.mappers.UserGroundInfoBusinEntityMapper;
import com.fbw.service.mappers.UserInfoBusinEntityMapper;
import com.fbw.service.mappers.UserShopFeedbackBusinEntityMapper;
import com.fbw.service.services.UserInfoBusinService;
import com.fbw.service.services.UserRedPacketBusinService;
import com.fbw.service.util.IDHelper;

/**
 * 
 * <功能详细描述>用户信息Controller
 * @author jiangruliang
 * @version [版本号, 2017年8月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController
{
    @Autowired
    private UserInfoBusinService userInfoService;

    @Autowired
    private UserRedPacketBusinService userRedPacketBusinService;

    @Autowired
    private UserInfoBusinEntityMapper userInfoBusinEntityMapper;

    @Autowired
    private UserDeviceInfoEntityMapper userDeviceInfoEntityMapper;

    @Autowired
    private UserGroundInfoBusinEntityMapper userGroundInfoBusinEntityMapper;

    @Autowired
    private UserShopFeedbackBusinEntityMapper userShopFeedbackMapper;

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserController.class, errorMsg);

    }

    /**
     * 通过手机号获取用户详细信息 <功能详细描述>
     * @param mobile
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserBaseInfo", method = RequestMethod.GET)
    public UserInfoBusinEntity getUserBaseInfo(String mobile) throws InnerException
    {
        UserInfoBusinEntity userInforBusinEntity = userInfoService.selectByMobile(mobile);
        return userInforBusinEntity;

    }

    /**
     * 通过微信号获取用户详细信息 <功能详细描述>
     * @param mobile
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectByOpenId", method = RequestMethod.GET)
    public UserInfoBusinEntity selectByOpenId(String openId)
    {
        UserInfoBusinEntity userInforBusinEntity = userInfoBusinEntityMapper.selectByOpenId(openId);
        return userInforBusinEntity;

    }

    /**
     * 通过用户id获取用户详细信息 <功能详细描述>
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserBaseInfoById", method = RequestMethod.GET)
    public UserInfoBusinEntity getUserBaseInfoById(Integer id) throws InnerException
    {
        UserInfoBusinEntity userInforBusinEntity = userInfoService.selectByPrimaryKey(id);
        return userInforBusinEntity;
    }

    /**
     * 存储用户部分相关信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveUserBaseInfo", method = RequestMethod.POST)
    public boolean saveUserBaseInfo(@RequestBody UserInfoBusinEntity userInforBusinEntity) throws InnerException
    {
        int flag = userInfoService.insertSelective(userInforBusinEntity);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * 根据用户Id更新用户基本信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserBaseInfoById", method = RequestMethod.POST)
    public boolean updateUserBaseInfoById(@RequestBody UserInfoBusinEntity userInforBusinEntity)
    {
        int flag = userInfoService.updateByPrimaryKeySelective(userInforBusinEntity);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * 根据mobile更新用户余额 <功能详细描述>
     * @param mobile
     * @param balance
     * @param newBalance
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserbalance", method = RequestMethod.POST)
    public boolean updateUserbalance(String mobile, String balance, String newBalance) throws InnerException
    {
        BigDecimal balance1 = new BigDecimal(balance);
        BigDecimal balance2 = new BigDecimal(newBalance);
        int flag = userInfoService.updateNewBalanceByMobileBalance(mobile, balance1, balance2);
        if (flag == 1)
        {
            return true;
        }
        return false;

    }

    /**
     * 根据mobile查询用户余额 <功能详细描述>
     * @param mobile
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryUserBalance", method = RequestMethod.GET)
    public String getBalanceByMobile(String mobile) throws InnerException
    {
        String bdBalance = userInfoService.selectBalanceByMobile(mobile);
        return bdBalance;

    }

    /**
     * 获取可用最大的红包 <功能详细描述>
     * @param userRedPacketBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getRedPacketWithBig", method = RequestMethod.POST)
    public UserRedPacketBusinEntity getRedPacketWithBig(@RequestBody UserRedPacketBusinEntity userRedPacketBusinEntity)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("firstClassId", userRedPacketBusinEntity.getFirstClassId());
        map.put("secondClassId", userRedPacketBusinEntity.getSecondClassId());
        map.put("cityId", userRedPacketBusinEntity.getCityId());
        map.put("minimumAmount", userRedPacketBusinEntity.getMinimumAmount());
        map.put("userId", userRedPacketBusinEntity.getUserId());
        UserRedPacketBusinEntity userRedPacket = userRedPacketBusinService.getRedPacketWithBig(map);
        if (null == userRedPacket)
        {
            userRedPacket = new UserRedPacketBusinEntity();
        }
        return userRedPacket;

    }

    /**
     * 更新红包状态 <功能详细描述>
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateRedPacketStatus", method = RequestMethod.POST)
    public boolean updateRedPacketStatus(Integer id) throws InnerException
    {
        int flag = userRedPacketBusinService.updateRedPacketStatus(id);
        if (flag == 1)
        {
            return true;
        }
        return false;

    }

    /**
     * 获取当前用户可用红包列表 <功能详细描述>
     * @param status
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectRedPacketListById", method = RequestMethod.GET)
    public List<UserRedPacketBusinEntity> selectRedPacketListById(Integer userId, Integer begin)
    {
        List<UserRedPacketBusinEntity> userRedPacketBusinEntities = userRedPacketBusinService
                .selectRedPacketListById(userId, begin);
        return userRedPacketBusinEntities;

    }

    /**
     * 获取用户已使用、已过期红包列表 <功能详细描述>
     * @param status
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectUsedRedPacketListById", method = RequestMethod.GET)
    public List<UserRedPacketBusinEntity> selectUsedRedPacketListById(Integer userId, Integer begin)
    {

        List<UserRedPacketBusinEntity> userRedPacketBusinEntities = userRedPacketBusinService
                .selectUsedRedPacketListById(userId, begin);
        return userRedPacketBusinEntities;

    }

    /**
     * 获取用户信息列表 <功能详细描述>
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserInfoList", method = RequestMethod.GET)
    public List<UserInfoBusinEntity> getUserInfoList() throws InnerException
    {
        List<UserInfoBusinEntity> userInfoBusinEntities = new ArrayList<UserInfoBusinEntity>();
        userInfoBusinEntities = userInfoService.selectAll();
        return userInfoBusinEntities;
    }

    /**
     * 用户实名认证 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateRealName", method = RequestMethod.POST)
    public CommonRsEntity updateRealName(String realName, String IDCard, Integer id) throws InnerException
    {
        CommonRsEntity CommonRsEntity = new CommonRsEntity();
        CommonRsEntity.setOperationFlag(true);
        boolean isIDCard = IDHelper.isIDCard(IDCard);
        if (isIDCard == false)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_ID_CARD_ERROR);
            CommonRsEntity.setOperationFlag(false);
            return CommonRsEntity;
        }
        boolean isRealName = IDHelper.isChinese(realName);
        if (isRealName == false)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_REAL_NAME_ERROR);
            CommonRsEntity.setOperationFlag(false);
            return CommonRsEntity;
        }
        UserInfoBusinEntity userInfoBusinEntity = new UserInfoBusinEntity();
        userInfoBusinEntity.setUserId(id);
        userInfoBusinEntity.setIdentityCard(IDCard);
        userInfoBusinEntity.setRealName(realName);
        int flag1 = userInfoService.updateByPrimaryKeySelective(userInfoBusinEntity);
        if (flag1 == 0)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_REAL_NAME_ID_CARD_ERROR);
            CommonRsEntity.setOperationFlag(false);
        }
        return CommonRsEntity;
    }

    /**
     * 验证身份信息 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectRealNameIsTrue", method = RequestMethod.POST)
    public CommonRsEntity selectRealNameIsTrue(String realName, String IDCard, Integer id)
    {
        CommonRsEntity CommonRsEntity = new CommonRsEntity();
        CommonRsEntity.setOperationFlag(true);
        boolean isIDCard = IDHelper.isIDCard(IDCard);
        if (isIDCard == false)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_ID_CARD_ERROR);
            CommonRsEntity.setOperationFlag(false);
            return CommonRsEntity;
        }
        boolean isRealName = IDHelper.isChinese(realName);
        if (isRealName == false)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_REAL_NAME_ERROR);
            CommonRsEntity.setOperationFlag(false);
            return CommonRsEntity;
        }
        UserInfoBusinEntity userInfoBusinEntity = new UserInfoBusinEntity();
        userInfoBusinEntity.setUserId(id);
        userInfoBusinEntity.setIdentityCard(IDCard);
        userInfoBusinEntity.setRealName(realName);
        int flag = userInfoService.selectRealNameIsTrue(userInfoBusinEntity);
        if (flag == 0)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_REAL_NAME_ID_CARD_ERROR);
            CommonRsEntity.setOperationFlag(false);
        }
        return CommonRsEntity;
    }

    /**
     * 设置支付密码 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updatePayPassword", method = RequestMethod.POST)
    public boolean updatePayPassword(String payPassword, String mobile) throws InnerException
    {
        UserInfoBusinEntity userInfoBusinEntity = new UserInfoBusinEntity();
        userInfoBusinEntity.setMobile(mobile);
        userInfoBusinEntity.setPayPassword(payPassword);
        userInfoBusinEntity.setIsUsePassword("1");
        int flag = userInfoService.updateByMobileSelective(userInfoBusinEntity);
        if (flag == 1)
        {
            return true;
        }

        return false;

    }

    /**
     * 通过id更新用户信息 <功能详细描述>
     * @param userInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserInfoSelective", method = RequestMethod.POST)
    public boolean updateUserInfoSelective(@RequestBody UserInfoBusinEntity userInfoBusinEntity)
    {
        int flag = userInfoService.updateByPrimaryKeySelective(userInfoBusinEntity);
        if (flag == 1)
        {
            return true;
        }

        return false;

    }

    /**
     * 判断支付密码是否正确 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectPayPwdIsRight", method = RequestMethod.POST)
    public boolean selectPayPwdIsRight(String payPassword, String mobile)
    {
        UserInfoBusinEntity userInfoBusinEntity = new UserInfoBusinEntity();
        userInfoBusinEntity.setMobile(mobile);
        userInfoBusinEntity.setPayPassword(payPassword);
        int flag = userInfoService.selectPayPwdIsRight(userInfoBusinEntity);
        if (flag == 1)
        {
            return true;
        }

        return false;

    }

    /**
     * 判断是否设置支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/isSetPayPasswordRight", method = RequestMethod.POST)
    public int isSetPayPasswordRight(String mobile)
    {
        int flag = userInfoService.isSetPayPasswordRight(mobile);
        if (flag == 1 || flag == 0)
        {
            return flag;
        }
        return -1;
    }

    /**
     * 关闭支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateClosePayPassword", method = RequestMethod.POST)
    public boolean updateClosePayPassword(String mobile)
    {
        int flag = userInfoService.updateClosePayPassword(mobile);
        if (flag == 1)
        {
            return true;
        }

        return false;

    }

    /**
     * 保存用户地推信息 <功能详细描述>
     * @param userGroundInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveUserGroundInfo", method = RequestMethod.POST)
    public boolean saveUserGroundInfo(@RequestBody UserGroundInfoBusinEntity userGroundInfoBusinEntity)
    {
        int flag = userInfoService.insertUserGround(userGroundInfoBusinEntity);
        if (flag == 1)
        {
            return true;
        }

        return false;
    }

    /**
     * 
     * <功能详细描述>查询用户的设备ID信息
     * @param deviceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryUserDeviceIdInfo", method = RequestMethod.GET)
    public boolean queryUserDeviceIdInfo(String deviceId)
    {
        int flag = userInfoService.queryUserDeviceIdInfo(deviceId);
        if (flag == 1)
        {
            return true;
        }

        return false;
    }

    @RequestMapping(value = "/queryUserGroundInfo", method = RequestMethod.GET)
    public UserGroundInfoBusinEntity queryUserGroundInfo(String mobile)
    {
        return userInfoService.queryUserGroundInfo(mobile);
    }

    /**
     * 
     * <功能详细描述>查询是否存在手机号
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectIsExistMobile", method = RequestMethod.GET)
    public boolean selectIsExistMobile(String mobile)
    {
        int flag = userInfoService.selectIsExistMobile(mobile);
        if (flag == 1)
        {
            return true;
        }

        return false;
    }

    /**
     * 保存用户设备信息 <功能详细描述>
     * @param userGroundInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveUserDeiviceInfo", method = RequestMethod.POST)
    public boolean saveUserDeiviceInfo(@RequestBody UserDeviceInfoEntity userDeviceInfoEntity)
    {
        int flag = userDeviceInfoEntityMapper.insertSelective(userDeviceInfoEntity);
        if (flag == 1)
        {
            return true;
        }

        return false;
    }

    /**
     * 查询该手机号是否是地推人员 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectIsGroundByMobile", method = RequestMethod.POST)
    public boolean selectIsGroundByMobile(String mobile)
    {
        int flag = userGroundInfoBusinEntityMapper.selectIsGroundByMobile(mobile);
        if (flag == 1)
        {
            return true;
        }

        return false;
    }

    /**
     * 更新用户积分<功能详细描述>
     * @param remark（1:消费买单，2:评论十字以上,3:带图评论,4:带图评论以及十字以上,5:消费买单会员日双倍，6:积分兑换奖品,7:大转盘抽奖，8:优质评论）
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserPoint", method = RequestMethod.POST)
    public CommonRsEntity updateUserPoint(@RequestBody UserPointDetailEntity userPointDetail)
    {
        CommonRsEntity CommonRsEntity = new CommonRsEntity();
        CommonRsEntity.setOperationFlag(true);
        try
        {
            userInfoService.updateUserPoint(userPointDetail);
        }
        catch (InnerException e)
        {
            CommonRsEntity.setErrorCode(e.getMessage());
            CommonRsEntity.setOperationFlag(false);
            return CommonRsEntity;
        }
        return CommonRsEntity;
    }

    /**
     * 翻贝轨迹 <功能详细描述>
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectUserdoubleTrail", method = RequestMethod.GET)
    public UserInfoBusinEntity selectUserdoubleTrail(Integer id)
    {
        UserInfoBusinEntity userInforBusinEntity = userInfoBusinEntityMapper.selectUserdoubleTrail(id);
        return userInforBusinEntity;
    }

    /**
     * 存储用户部分相关信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveUserAndShopfeedBack", method = RequestMethod.POST)
    public boolean saveUserAndShopfeedBack(@RequestBody UserShopFeedbackBusinEntity userInforBusinEntity)
    {
        int flag = userShopFeedbackMapper.insertUserFeedBack(userInforBusinEntity);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

}
