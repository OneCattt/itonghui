package com.fbw.service.business.login.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.login.context.AbstractSuperLogin;
import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.business.login.entity.LoginReturnEntity;
import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.GroundFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mq.GroundProductMq;
import com.fbw.service.mq.LoginProductMq;
import com.fbw.service.mq.SmsProductMq;
import com.fbw.service.util.CommonUtil;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.JsonUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 短信登陆策略
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class SmsLoginService extends AbstractSuperLogin
{
    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    GroundFeignClient groundFeignClient;

    @Autowired
    CommonFeignClient commonFeignClient;

    @Autowired
    CommonUtil commonUtil;

    @Autowired
    LoginProductMq loginMq;

    @Autowired
    SmsProductMq smsMq;

    @Autowired
    GetCacheUtil cacheUtil;

    @Autowired
    GroundProductMq groundProductMq;

    @Autowired
    LoginService loginService;

    /**
     * 登陆短信验证码类型
     */
    final String LOGIN_SMS_CODE_TYPE = "SMS_105775012";

    @Override
    public LoginReturnEntity login(LoginEntity loginEntity) throws InnerException
    {
        // 判断登陆条件
        LoginReturnEntity returnEntity = checkLoginParm(loginEntity);
        // 构造相同设备ID参数
        buildSameDeviceIdParm(loginEntity);
        // 打印日志
        getErrorLog(loginEntity.getTrackId() + ":sms_checkLogin:" + returnEntity.toString());
        // 免登陆标志为true，直接返回token登陆
        if (returnEntity.isNoLandingFlag())
        {
            return getLoginToken(loginEntity.getMobile());
        }
        // 效验通过则开始注册
        if (returnEntity.isCheckLoginFlag())
        {
            // 判断是否是新用户
            if (commonUtil.isNewUser(loginEntity.getMobile()))
            {
                // 保存用户信息,如果失败直接抛出异常
                if (!loginService.saveLoginUserInfo(loginEntity))
                {
                    throw new InnerException(ErrorMsgConstant.PORTAL_LOGIN_SAVE_USER_ERROR,
                            InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_LOGIN_SAVE_USER_ERROR));
                }
                // 更新登陆注册地推信息
                groundProductMq.updateLoginRegisterWithGround(loginEntity);
                // 保存成功，则返回token登陆
                return getLoginToken(loginEntity.getMobile());
            }
            // 更新用户手机设备信息后，直接返回token登陆
            loginMq.updateUserMobileDeviceInfo(loginEntity);
            return getLoginToken(loginEntity.getMobile());
        }
        throw new InnerException(returnEntity.getFailReason(), InnerCode.geterrorMsg(returnEntity.getFailReason()));
    }

    /**
     * 
     * <功能详细描述> 构造JSON格式登陆短信
     * @param request
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public String buildLoginSmsByJson(String mobile) throws InnerException
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String ramCode = NomalUtil.getRandomString(4);
        map.put("mobile", mobile);
        map.put("smsmodel", LOGIN_SMS_CODE_TYPE);
        map.put("code", ramCode);
        // 缓存10分钟登陆短信随机码
        cacheUtil.setTimeStringRedisVal(CacheKeyEnums.getMsg(CacheKeyConstant.PORTAL_LOGIN_SMS_CODE) + mobile, ramCode,
                10, TimeUnit.MINUTES);
        return JsonUtil.convertMapToJson(map);

    }

    /**
     * 
     * <功能详细描述> 判断是否已经注册的用户
     * @param mobile
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void isRegisterUser(HttpServletRequest request) throws InnerException
    {
        String mobile = request.getParameter("mobile");
        // 效验手机号格式
        NomalUtil.checkMobileFormat(mobile);
        // 获取用户基本信息
        UserInfoBusinEntity userInfoBusinEntity = userFeignClient.getUserBaseInfo(mobile);
        if (null != userInfoBusinEntity.getMobile())
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_LOGIN_REPEAT_REGISTER_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_LOGIN_REPEAT_REGISTER_FAIL));
        }

    }

    /**
     * 
     * <功能详细描述> 发送登陆短信
     * @param msg
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void sendLoginMsg(String msg) throws InnerException
    {
        smsMq.smsSend(msg);
    }

    /**
     * 
     * <功能详细描述> 发送语音登陆短信
     * @param msg
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void sendVoiceLoginMsg(String msg) throws InnerException
    {
        smsMq.voiceSmsSend(msg);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(SmsLoginService.class, errorMsg);
    }

    @Override
    public LoginReturnEntity checkLoginParm(LoginEntity loginEntity)
    {
        // 判断公共登陆条件
        LoginReturnEntity loginReturnEntity = checkCommonLogin(loginEntity);
        // 判断公共登录验证是否成功
        if (!loginReturnEntity.isCheckLoginFlag())
        {
            return loginReturnEntity;
        }
        return loginReturnEntity;
    }

}
