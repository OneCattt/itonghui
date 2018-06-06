package com.fbw.service.business.user.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.user.entity.ShopUserReqEntity;
import com.fbw.service.business.user.service.ShopUserService;
import com.fbw.service.exception.InnerException;

/**
 * 
 * <功能详细描述>用户controller
 * @author FBW0115
 * @version [版本号, 2017年9月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "/shopUser")
public class ShopUserController extends BaseController
{
    @Autowired
    private ShopUserService shopUserService;

    /**
     * 
     * <功能详细描述>重置密码
     * @param mobile：登录号码
     * @param newPwd：新密码
     * @param checkPwd：确认密码
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public Map<String, Object> resetPwd(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        ShopUserReqEntity entity = new ShopUserReqEntity();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            shopUserService.resetPwd(entity);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":shopUser resetPwd:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":shopUser resetPwd:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":shopUser resetPwd:" + e.getMessage() + parameterMap.toString());
            return failedMessage(e.getMessage());
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":shopUser resetPwd:密码设置失败" + entity.getUserId());
            return failedMessage("密码设置失败，请稍后再试！");
        }
        return successMessage("resetPwd SUCCESS");
    }

    /**
     * 
     * <功能详细描述>修改密码
     * @param mobile：登录号码
     * @param newPwd：新密码
     * @param checkPwd：确认密码
     * @param oldPwd：就密码
     * @param code：短信验证码
     * @param modifyType：修改类型（smsModifyType、oldModifyType）
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    public Map<String, Object> modifyPwd(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        ShopUserReqEntity entity = new ShopUserReqEntity();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            shopUserService.modifyPwd(entity);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":shopUser modifyPwd:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":shopUser modifyPwd:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":shopUser modifyPwd:" + e.getMessage() + parameterMap.toString());
            return failedMessage(e.getMessage());
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":shopUser modifyPwd:修改密码失败" + entity.getUserId());
            return failedMessage("修改密码失败，请稍后再试！");
        }
        return successMessage("modifyPwd SUCCESS");
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(ShopUserController.class, errorMsg);
    }

}
