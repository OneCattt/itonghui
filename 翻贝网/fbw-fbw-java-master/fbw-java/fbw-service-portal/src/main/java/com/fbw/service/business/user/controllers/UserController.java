package com.fbw.service.business.user.controllers;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.business.login.entity.LoginReturnEntity;
import com.fbw.service.business.login.service.SmsLoginService;
import com.fbw.service.business.paybill.service.PayService;
import com.fbw.service.business.user.service.UserOrderInfoService;
import com.fbw.service.business.user.service.UserService;
import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.user.UserBalanceDetailEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserShopFeedbackBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;
import com.fbw.service.util.ReqUtils;

@RestController
public class UserController extends BaseController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserOrderInfoService userOrderInfoService;

    @Autowired
    private PayService payService;

    @Autowired
    private GetCacheUtil getCacheUtil;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    SmsLoginService smsLoginService;

    @Autowired
    GetCacheUtil cacheUtil;

    /**
     * 点击我的：通过用户id获取用户详细信息、获取未评价数量、是否是地推人员 <功能详细描述>
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/getUserBaseInfoById", method = RequestMethod.GET)
    public Map<String, Object> getUserBaseInfoById(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int id = ReqUtils.getInt(request, "userId");
        Map<String, Object> data = new HashMap<String, Object>();
        UserInfoBusinEntity userInfoBusinEntity = userService.getUserBaseInfoById(id);
        if (NomalUtil.isNullOrEmpty(userInfoBusinEntity))
        {
            getErrorLog(trackId + ":user getUserBaseInfoById:" + "查询用户基本信息失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }
        userInfoBusinEntity.setRechargeRate(getCacheUtil.getDictVal(CacheKeyConstant.PORTAL_RECHARGE_RATE));
        data.put("userInfoBusinEntity", userInfoBusinEntity);
        return successData(data);
    }

    /**
     * 根据用户Id更新用户基本信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/updateUserBaseInfoById", method = RequestMethod.POST)
    public Map<String, Object> updateUserBaseInfoById(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        UserInfoBusinEntity userInforBusinEntity = new UserInfoBusinEntity();
        userInforBusinEntity.setAvatar(request.getParameter("avatar"));
        userInforBusinEntity.setSex(request.getParameter("sex"));
        // birthday格式 YYYY-MM-DD
        userInforBusinEntity.setBirthday(request.getParameter("birthday"));
        userInforBusinEntity.setUserId(ReqUtils.getInt(request, "userId"));
        userInforBusinEntity.setNickName(request.getParameter("nickName"));
        boolean flag = false;
        try
        {
            flag = userService.updateUserBaseInfoById(userInforBusinEntity);
            if (flag == false)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user updateUserBaseInfoById:" + "更新用户基本信息失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_UPDATE_DB_FAIL);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);

    }

    /**
     * 用户实名认证 <功能详细描述>
     * @param realName 真实姓名
     * @param IDCard 身份证号
     * @param id 用户id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/updateRealName", method = RequestMethod.POST)
    public Map<String, Object> updateRealName(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        String realName = request.getParameter("realName");
        String IDCard = request.getParameter("IDCard");
        int id = ReqUtils.getInt(request, "userId");
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        try
        {
            commonRsEntity = userService.updateRealName(realName, IDCard, id);
            if (commonRsEntity.isOperationFlag() == false)
            {
                throw new Exception(commonRsEntity.getErrorMessage());
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user updateRealName:" + "用户实名认证失败！");
            if (null != e.getMessage())
            {
                return failedMessage(e.getMessage());
            }
            return failedMessage(ErrorMsgConstant.PORTAL_UPDATE_DB_FAIL);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 验证身份信息 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/selectRealNameIsTrue", method = RequestMethod.POST)
    public Map<String, Object> selectRealNameIsTrue(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        String realName = request.getParameter("realName");
        String IDCard = request.getParameter("IDCard");
        int id = ReqUtils.getInt(request, "userId");
        CommonRsEntity commonRsEntity = null;
        try
        {
            commonRsEntity = userService.selectRealNameIsTrue(realName, IDCard, id);
            if (commonRsEntity.isOperationFlag() == false)
            {
                throw new Exception(commonRsEntity.getErrorCode());
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user selectRealNameIsTrue:" + "验证身份信息失败！");
            if (null != e.getMessage())
            {
                return failedMessage(e.getMessage());
            }
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 设置支付密码 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/updatePayPassword", method = RequestMethod.POST)
    public Map<String, Object> updatePayPassword(HttpServletRequest request)
    {
        String payPassword = request.getParameter("payPassword");
        String mobile = request.getParameter("mobile");
        String trackId = String.valueOf(request.getAttribute("trackId"));
        boolean flag = false;
        try
        {
            flag = userService.updatePayPassword(payPassword, mobile);
            if (flag == false)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user updatePayPassword:" + "验证身份信息失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_UPDATE_DB_FAIL);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 判断支付密码是否正确 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/selectPayPwdIsRight", method = RequestMethod.POST)
    public Map<String, Object> selectPayPwdIsRight(HttpServletRequest request)
    {
        String payPassword = request.getParameter("payPassword");
        String mobile = request.getParameter("mobile");
        String trackId = String.valueOf(request.getAttribute("trackId"));
        try
        {
            payService.checkUserPassWord(mobile, payPassword);

        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user selectPayPwdIsRight:" + "判断支付密码是否正确失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_DB_ERROR);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 判断是否设置支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/isSetPayPasswordRight", method = RequestMethod.POST)
    public Map<String, Object> isSetPayPasswordRight(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        String mobile = request.getParameter("mobile");
        int flag;
        try
        {
            flag = userService.isSetPayPasswordRight(mobile);
            if (flag == -1)
            {
                throw new Exception();
            }
            if (flag == 0)
            {
                Map<String, Object> data = new HashMap<String, Object>();
                return successData(data);
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user isSetPayPasswordRight:" + "判断是否设置支付密码失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 关闭支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/updateClosePayPassword", method = RequestMethod.POST)
    public Map<String, Object> updateClosePayPassword(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        String mobile = request.getParameter("mobile");
        String payPassword = request.getParameter("payPassword");
        boolean flag = false;
        try
        {
            payService.checkUserPassWord(mobile, payPassword);
            flag = userService.updateClosePayPassword(mobile);
            if (flag == false)
            {
                throw new InnerException("");
            }
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":user isSetPayPasswordRight:" + "关闭支付密码失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_UPDATE_DB_FAIL);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 通过手机号获取用户90天内余额明细 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/getUserBalanceInfoByUserId")
    public Map<String, Object> getUserBalanceInfoByUserId(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int begin = ReqUtils.getInt(request, "begin") * 20;
        List<UserBalanceDetailEntity> userBalanceDetail = new ArrayList<UserBalanceDetailEntity>();
        try
        {
            userBalanceDetail = userOrderInfoService.getUserBalanceDetailById(userId, begin);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user getUserBalanceInfoByMobile:" + "获取90天内余额明细失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userBalanceDetail", userBalanceDetail);
        return successData(data);
    }

    /**
     * 翻贝轨迹 <功能详细描述>
     * @param id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/selectUserdoubleTrail", method = RequestMethod.GET)
    public Map<String, Object> selectUserdoubleTrail(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int id = ReqUtils.getInt(request, "userId");
        Map<String, Object> data = new HashMap<String, Object>();
        UserInfoBusinEntity userInfoBusinEntity = getCacheUtil.getUserdoubleTrail(id);
        if (NomalUtil.isNullOrEmpty(userInfoBusinEntity.getUserId()))
        {
            getErrorLog(trackId + ":user selectUserdoubleTrail:" + "查询翻贝轨迹失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }
        if (NomalUtil.isNullOrEmpty(userInfoBusinEntity.getRowNum()))
        {
            userInfoBusinEntity.setConsumeIndex("0.00%");
        }
        else
        {
            DecimalFormat df = new DecimalFormat("0.00");
            String consumeIndex = df.format(
                    ((float) userInfoBusinEntity.getRowNum() / userInfoBusinEntity.getAllUserNumber()) * 100) + "%";
            userInfoBusinEntity.setConsumeIndex(consumeIndex);
        }
        data.put("userInfoBusinEntity", userInfoBusinEntity);
        return successData(data);
    }

    /**
     * 插入用户评论 <功能详细描述>
     * @param userCommentBusinEntity
     * @param score,commentLabel,userId,content,picture,isNoName,orderNumber,shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/saveUserAndShopfeedBack", method = RequestMethod.POST)
    public Map<String, Object> saveUserAndShopfeedBack(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserShopFeedbackBusinEntity entity = new UserShopFeedbackBusinEntity();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            boolean flag = userFeignClient.saveUserAndShopfeedBack(entity);
            if (flag == false)
            {
                getErrorLog(trackId + ":user saveUserAndShopfeedBack:" + "评价失败！");
                return failedMessage(ErrorMsgConstant.PORTAL_USER_SHOP_FEEDBACK_DB_FAIL);
            }
        }
        catch (IllegalAccessException e1)
        {

            getErrorLog(trackId + ":user saveUserAndShopfeedBack:参数解析失败InvocationTargetException:"
                    + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_USER_SHOP_FEEDBACK_DB_FAIL);
        }
        catch (InvocationTargetException e1)
        {
            getErrorLog(
                    trackId + ":user saveUserAndShopfeedBack:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_USER_SHOP_FEEDBACK_DB_FAIL);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 更换手机号发送验证码 <功能详细描述>
     * @param request
     * @param response
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/sendSmsCodeByChangeMobile", method = RequestMethod.POST)
    public Map<String, Object> sendSmsCodeByChangeMobile(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        String trackId = request.getAttribute("trackId").toString();
        Map<String, Object> data = new HashMap<String, Object>();
        String mobile = request.getParameter("mobile");
        try
        {
            // 效验手机号格式
            NomalUtil.checkMobileFormat(mobile);
            // 构造JSON格式登陆短信
            String msg = smsLoginService.buildLoginSmsByJson(mobile);
            // 发送登陆短信
            smsLoginService.sendLoginMsg(msg);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + "user:sendSmsCodeByChangeMobile error:" + e.getMessage());
            return failedMessage(e.getErrorCode());
        }
        return successData(data);
    }

    /**
     * 判断旧手机验证码是否正确 <功能详细描述>
     * @param request
     * @param response
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/judgeOldMobileCode", method = RequestMethod.POST)
    public Map<String, Object> judgeOldMobileCode(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        // 构造请求实体类
        LoginEntity loginEntity = new LoginEntity();
        String trackId = request.getAttribute("trackId").toString();
        NomalUtil.buildRequestEntity(loginEntity, request);
        if (NomalUtil.isNullOrEmpty(loginEntity.getMobile()))
        {
            loginEntity.setMobile(request.getAttribute("mobile").toString());
        }
        loginEntity.setTrackId(trackId);
        loginEntity.setIp(getIpAddress(request));
        Map<String, Object> data = new HashMap<String, Object>();
        LoginReturnEntity loginReturn = new LoginReturnEntity();
        try
        {
            loginReturn = smsLoginService.checkLoginParm(loginEntity);
            if (false == loginReturn.isCheckLoginFlag())
            {
                throw new Exception(loginReturn.getFailReason());
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + "user:changeUserMobile error:" + "更换手机号失败！");
            return failedMessage(e.getMessage());
        }
        return successData(data);
    }

    /**
     * 更换新手机号 <功能详细描述>
     * @param request
     * @param response
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/user/changeUserMobile", method = RequestMethod.POST)
    public Map<String, Object> changeUserMobile(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        // 构造请求实体类
        LoginEntity loginEntity = new LoginEntity();
        UserInfoBusinEntity userInforBusinEntity = new UserInfoBusinEntity();
        String trackId = request.getAttribute("trackId").toString();
        NomalUtil.buildRequestEntity(loginEntity, request);
        if (NomalUtil.isNullOrEmpty(loginEntity.getMobile()))
        {
            loginEntity.setMobile(request.getAttribute("mobile").toString());
        }
        loginEntity.setTrackId(trackId);
        loginEntity.setIp(getIpAddress(request));
        Map<String, Object> data = new HashMap<String, Object>();
        LoginReturnEntity loginReturn = new LoginReturnEntity();
        try
        {
            loginReturn = smsLoginService.checkLoginParm(loginEntity);
            if (false == loginReturn.isCheckLoginFlag())
            {
                throw new Exception(loginReturn.getFailReason());
            }
            userInforBusinEntity.setUserId(loginEntity.getUserId());
            userInforBusinEntity.setMobile(loginEntity.getMobile());
            userService.updateUserBaseInfoById(userInforBusinEntity);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + "user:changeUserMobile error:" + "更换手机号失败！");
            return failedMessage(e.getMessage());
        }
        return successData(data);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserController.class, errorMsg);

    }

}
