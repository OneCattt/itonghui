package com.fbw.service.business.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.login.context.AbstractSuperLogin;
import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.business.login.entity.LoginReturnEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mq.GroundProductMq;
import com.fbw.service.mq.LoginProductMq;
import com.fbw.service.util.CommonUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 微信登陆策略
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class WechatLoginService extends AbstractSuperLogin
{

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    LoginProductMq loginMq;

    @Autowired
    GroundProductMq groundProductMq;

    @Autowired
    CommonUtil commonUtil;

    @Autowired
    LoginService loginService;

    @Override
    public LoginReturnEntity login(LoginEntity loginEntity) throws InnerException
    {
        //// 判断登陆条件
        LoginReturnEntity returnEntity = checkLoginParm(loginEntity);
        // 构造相同设备ID参数
        buildSameDeviceIdParm(loginEntity);
        // 打印日志
        getErrorLog(loginEntity.getTrackId() + ":wx_checkLogin:" + returnEntity.toString());
        // 免登陆标志为true，直接返回token登陆
        if (returnEntity.isNoLandingFlag())
        {
            return getLoginToken(loginEntity.getMobile());
        }
        // 效验通过则开始注册
        if (returnEntity.isCheckLoginFlag())
        {
            // 老用户
            if (!commonUtil.isNewUser(loginEntity.getMobile()))
            {
                // 更新用户的微信号信息
                loginMq.updateUserOpenId(loginEntity);
                return getLoginToken(loginEntity.getMobile());
            }
            // 新用户保存用户信息,如果失败直接抛出异常
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
        throw new InnerException(returnEntity.getFailReason(), InnerCode.geterrorMsg(returnEntity.getFailReason()));

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(WechatLoginService.class, errorMsg);
    }

    @Override
    public LoginReturnEntity checkLoginParm(LoginEntity loginEntity)
    {
        // 效验微信号是否为空
        LoginReturnEntity loginReturnEntity = new LoginReturnEntity();
        if (NomalUtil.isNullOrEmpty(loginEntity.getOpenId()))
        {
            loginReturnEntity.setCheckLoginFlag(false);
            loginReturnEntity.setFailReason(ErrorMsgConstant.PORTAL_LOGIN_OPENID_NULL_FAIL);
            return loginReturnEntity;
        }
        // 判断公共登陆条件
        loginReturnEntity = checkCommonLogin(loginEntity);

        return loginReturnEntity;
    }
}
