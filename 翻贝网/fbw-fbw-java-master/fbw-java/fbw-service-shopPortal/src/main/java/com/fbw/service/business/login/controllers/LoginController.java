package com.fbw.service.business.login.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.login.entity.LoginReqEntity;
import com.fbw.service.business.login.service.LoginService;
import com.fbw.service.entity.portal.ShopUserEntity;
import com.fbw.service.exception.InnerException;
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
@RequestMapping(value = "/shopPortal")
public class LoginController extends BaseController
{

    private static final String SMS_LOGIN_TYPE = "smsLogin";

    private static final String PWD_LOGIN_TYPE = "pwdLogin";

    private static final String SMS_TYPE = "smsType";

    @Autowired
    private LoginService loginService;

    /**
     * 
     * <功能详细描述> 登陆
     * @param mobile：登录号码
     * @param code：短信验证码
     * @param password：登录密码
     * @param loginType：登录类型（smsLogin、pwdLogin）
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        LoginReqEntity entity = new LoginReqEntity();
        ShopUserEntity shopUserInfo = null;
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            if (!NomalUtil.isMobileNo(entity.getMobile()))
            {
                return failedMessage("请输入正确的手机号码！");
            }
            // 切换登陆方式
            shopUserInfo = login(entity);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":login error:" + e.getMessage() + parameterMap.toString());
            return failedMessage(e.getMessage());
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":login error:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":login error:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("shopUserInfo", shopUserInfo);
        return successData(data);
    }

    /**
     * 
     * <功能详细描述> 发送手机验证码
     * @param mobile：接收验证码号码
     * @param sms_type:发送类型
     * @param request
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/sendLoginSms", method = RequestMethod.POST)
    public Map<String, Object> sendLoginSms(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        LoginReqEntity entity = new LoginReqEntity();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            if (!NomalUtil.isMobileNo(entity.getMobile()))
            {
                return failedMessage("请输入正确的手机号码！");
            }
            if (SMS_TYPE.equals(entity.getSmsType()))
            {
                // 校验账号是否存在
                loginService.checkMobile(entity);
            }
            // 发送登陆短信
            loginService.sendLoginMsg(entity);
        }
        catch (InnerException e)
        {
            return failedMessage(e.getMessage());
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":login error:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":login error:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        return successMessage("sendLoginSms success");
    }

    /**
     * 
     * <功能详细描述> 切换登陆方式
     * @param loginEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private ShopUserEntity login(LoginReqEntity entity) throws InnerException
    {
        if (SMS_LOGIN_TYPE.equals(entity.getLoginType()))
        {
            loginService.checkSmsCode(entity.getMobile(), entity.getCode());
        }
        else if (PWD_LOGIN_TYPE.equals(entity.getLoginType()))
        {
            loginService.checkPwd(entity);
        }

        // 查询用户信息（角色、权限）
        return loginService.getShopUserInfo(entity.getMobile());
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(LoginController.class, errorMsg);
    }

}
