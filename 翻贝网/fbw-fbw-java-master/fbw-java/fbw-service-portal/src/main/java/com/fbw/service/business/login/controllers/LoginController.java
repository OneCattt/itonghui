package com.fbw.service.business.login.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.business.login.entity.LoginReturnEntity;
import com.fbw.service.business.login.service.LoginService;
import com.fbw.service.business.login.service.SmsLoginService;
import com.fbw.service.business.login.service.WechatLoginService;
import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.exception.InnerException;
import com.fbw.service.util.CommonUtil;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 登陆controller层
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class LoginController extends BaseController
{

    private static String WECHAT_LOGIN = "weChat";

    @Autowired
    SmsLoginService smsLoginService;

    @Autowired
    WechatLoginService weChatLoginService;

    @Autowired
    CommonUtil commonUtil;

    @Autowired
    GetCacheUtil cacheUtil;

    @Autowired
    LoginService loginService;

    /**
     * 短信类型
     */
    final String SMS_TYPE = "1";

    /**
     * 语音类型
     */
    final String VOICE_TYPE = "2";

    /**
     * 
     * <功能详细描述> 登陆
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/login")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        String trackId = request.getAttribute("trackId").toString();
        Map<String, Object> data = new HashMap<String, Object>();
        LoginReturnEntity loginReturn = new LoginReturnEntity();
        // 构造登陆实体类
        LoginEntity loginEntity = buildLoginEntity(request, trackId);
        try
        {
            // 切换登陆方式
            loginReturn = switchLogin(loginEntity);
            // 失效登陆短信验证码
            invalidLoginSmsCode(loginEntity);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":login error:" + e.getMessage());
            return failedMessage(e.getErrorCode());
        }
        // 返回token
        data.put("token", loginReturn.getToken());
        // 返回登陆用户基本信息
        data.put("userInfo", buildUserReturnInfo(loginService.queryLoginUserInfo(loginEntity)));
        return successData(data);
    }

    /**
     * 
     * <功能详细描述> 微信免登陆接口
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/weChatNoLanding", method = RequestMethod.POST)
    public Map<String, Object> weChatNoLanding(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        Map<String, Object> data = new HashMap<String, Object>();
        UserInfoBusinEntity userInfoBusinEntity = loginService.checkWeChatNoLanding(request.getParameter("openId"));
        if (!NomalUtil.isNullOrEmpty(userInfoBusinEntity))
        {
            LoginReturnEntity loginReturnEntity = weChatLoginService.getLoginToken(userInfoBusinEntity.getMobile());
            data.put("token", loginReturnEntity.getToken());
            // 返回登陆用户基本信息
            data.put("userInfo", buildUserReturnInfo(userInfoBusinEntity));
            data.put("isNoLanding", true);
        }
        else
        {
            data.put("isNoLanding", false);
        }
        return successData(data);

    }

    /**
     * 
     * <功能详细描述> 发送手机验证码
     * @param request
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/sendLoginSms", method = RequestMethod.POST)
    public Map<String, Object> sendLoginSms(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        String trackId = request.getAttribute("trackId").toString();
        Map<String, Object> data = new HashMap<String, Object>();
        String mobile = request.getParameter("mobile");
        String type = request.getParameter("type");
        try
        {
            // 效验手机号格式
            NomalUtil.checkMobileFormat(mobile);
            // 构造JSON格式登陆短信
            String msg = smsLoginService.buildLoginSmsByJson(mobile);
            // 发送登陆短信
            if (SMS_TYPE.equals(type))
            {
                // 发送登陆短信
                smsLoginService.sendLoginMsg(msg);
            }
            else if (VOICE_TYPE.equals(type))
            {
                // 发送语音登陆短信
                smsLoginService.sendVoiceLoginMsg(msg);
            }

        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":sendLoginSms error:" + e.getMessage());
            return failedMessage(e.getErrorCode());
        }
        // 判断是否是新用户
        data.put("isNewFlag", commonUtil.isNewUser(mobile));
        return successData(data);
    }

    /**
     * 
     * <功能详细描述> 退出登录接口
     * @param request
     * @param response
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/exitLogin", method = RequestMethod.POST)
    public Map<String, Object> exitLogin(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        Map<String, Object> data = new HashMap<String, Object>();
        String key = CacheKeyEnums.getMsg(CacheKeyConstant.PORTAL_LOGIN_TOKEN_KEY) + request.getParameter("token");
        cacheUtil.deleteStringRedisVal(key);
        return successData(data);

    }

    /**
     * 
     * <功能详细描述> 切换登陆方式
     * @param loginEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private LoginReturnEntity switchLogin(LoginEntity loginEntity) throws InnerException
    {
        if (WECHAT_LOGIN.equals(loginEntity.getLoginType()))
        {
            return weChatLoginService.login(loginEntity);
        }
        return smsLoginService.login(loginEntity);
    }

    /***
     * 
     * <功能详细描述> 失效登陆短信验证码
     * @param loginEntity
     * @see [类、类#方法、类#成员]
     */
    private void invalidLoginSmsCode(LoginEntity loginEntity)
    {
        cacheUtil.deleteStringRedisVal(
                CacheKeyEnums.getMsg(CacheKeyConstant.PORTAL_LOGIN_SMS_CODE) + loginEntity.getMobile());
    }

    /**
     * 
     * <功能详细描述> 构造用户返回信息
     * @param userInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserInfoBusinEntity buildUserReturnInfo(UserInfoBusinEntity userInfoBusinEntity)
    {
        userInfoBusinEntity.setBirthday(null);
        userInfoBusinEntity.setRealName(null);
        userInfoBusinEntity.setSex(null);
        userInfoBusinEntity.setIdentityCard(null);
        userInfoBusinEntity.setIsUsePassword(null);
        return userInfoBusinEntity;

    }

    /**
     * 
     * <功能详细描述> 构造登陆实体类
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    private LoginEntity buildLoginEntity(HttpServletRequest request, String trackId)
    {
        LoginEntity loginEntity = new LoginEntity();
        // 构造请求实体类
        NomalUtil.buildRequestEntity(loginEntity, request);
        if (NomalUtil.isNullOrEmpty(loginEntity.getMobile()))
        {
            loginEntity.setMobile(request.getAttribute("mobile").toString());
        }
        loginEntity.setTrackId(trackId);
        loginEntity.setIp(getIpAddress(request));
        return loginEntity;
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(LoginController.class, errorMsg);
    }

}
