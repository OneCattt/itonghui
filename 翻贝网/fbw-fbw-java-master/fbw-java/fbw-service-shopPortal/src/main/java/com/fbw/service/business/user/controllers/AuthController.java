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
import com.fbw.service.business.user.entity.AuthReqEntity;
import com.fbw.service.business.user.service.AuthService;
import com.fbw.service.exception.InnerException;

/**
 * 
 * <功能详细描述>权限controller
 * @author FBW0115
 * @version [版本号, 2017年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController extends BaseController
{

    @Autowired
    private AuthService authService;

    /**
     * 
     * <功能详细描述>添加子账号
     * @param parentMobile：登录号码
     * @param sonMobile：子账号号码
     * @param code：短信验证码
     * @param shopId：商家id
     * @param shopName：商家名称
     * @param payCashier：买单收银（100001）
     * @param editEvent：设置翻倍日（100002）
     * @param orderManager：订单管理（100003）
     * @param commentManager：评价管理（100004）
     * @param moneyManager：财务结款（100005）
     * @param eventCalendar：查看翻倍日（100006）
     * @param groundManager：地推管理（100007）
     * @param exclusiveQR：专属二维码（100008）
     * @param admin：授权管理（100009）
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public Map<String, Object> saveAccount(HttpServletRequest request)
    {

        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        AuthReqEntity entity = new AuthReqEntity();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            authService.saveAccount(entity);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":login editUserAuth:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":login editUserAuth:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":login error:" + e.getMessage() + parameterMap.toString());
            return failedMessage(e.getMessage());
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":login editUserAuth:更新权限失败，roleId" + entity.getRoleId());
            return failedMessage("编辑失败，请稍后再试！");
        }
        return successMessage("edit SUCCESS");

    }

    /**
     * 
     * <功能详细描述>编辑权限
     * @param roleId：角色id
     * @param payCashier：买单收银（100001）
     * @param editEvent：设置翻倍日（100002）
     * @param orderManager：订单管理（100003）
     * @param commentManager：评价管理（100004）
     * @param moneyManager：财务结款（100005）
     * @param eventCalendar：查看翻倍日（100006）
     * @param groundManager：地推管理（100007）
     * @param exclusiveQR：专属二维码（100008）
     * @param admin：授权管理（100009）
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/editUserAuth", method = RequestMethod.POST)
    public Map<String, Object> editUserAuth(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        AuthReqEntity entity = new AuthReqEntity();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            authService.editUserAuth(entity);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":auth editUserAuth:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":auth editUserAuth:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage("系统异常！");
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":auth editUserAuth:更新权限失败，roleId" + entity.getRoleId());
            return failedMessage("编辑失败，请稍后再试！");
        }
        return successMessage("edit SUCCESS");
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(AuthController.class, errorMsg);

    }
}
