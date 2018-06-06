package com.fbw.service.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.login.service.LoginService;
import com.fbw.service.business.user.entity.ShopUserReqEntity;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.ShopUserMapper;
import com.fbw.service.util.MD5Util;

/**
 * 
 * <功能详细描述>用户service
 * @author FBW0115
 * @version [版本号, 2017年9月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class ShopUserService
{

    private static final String SMS_MODIFY_TYPE = "smsModifyType";

    private static final String OLD_MODIFY_TYPE = "oldModifyType";

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Autowired
    private LoginService loginService;

    /**
     * 
     * <功能详细描述>重置密码
     * @param entity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void resetPwd(ShopUserReqEntity entity) throws InnerException
    {
        // 校验密码格式
        String msg = checkPwd(entity.getNewPwd(), entity.getCheckPwd(), entity.getModifyType(), entity.getOldPwd());
        if (null != msg)
        {
            throw new InnerException(ErrorMsgConstant.CHECK_PASSWORD_FAIL, msg);
        }
        // 更新密码
        shopUserMapper.updateShopUserPwd(entity.getMobile(), MD5Util.EncoderByMd5(entity.getNewPwd()));
    }

    /**
     * 
     * <功能详细描述>修改密码
     * @param entity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void modifyPwd(ShopUserReqEntity entity) throws InnerException
    {
        // 校验密码格式
        String msg = checkPwd(entity.getNewPwd(), entity.getCheckPwd(), entity.getModifyType(), entity.getOldPwd());
        if (null != msg)
        {
            throw new InnerException(ErrorMsgConstant.CHECK_PASSWORD_FAIL, msg);
        }
        // 判断修改类型
        if (SMS_MODIFY_TYPE.equals(entity.getModifyType()))
        {
            // 验证短信验证码
            loginService.checkSmsCode(entity.getMobile(), entity.getCode());
        }
        else if (OLD_MODIFY_TYPE.equals(entity.getModifyType()))
        {
            // 验证旧密码
            checkOlePwd(entity.getMobile(), MD5Util.EncoderByMd5(entity.getOldPwd()));
        }
        // 更新密码
        shopUserMapper.updateShopUserPwd(entity.getMobile(), MD5Util.EncoderByMd5(entity.getNewPwd()));
    }

    /**
     * 
     * <功能详细描述>校验密码格式
     * @param newPwd
     * @param checkPwd
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String checkPwd(String newPwd, String checkPwd, String modifyType, String oldPwd)
    {
        if (newPwd.length() < 6 || newPwd.length() > 16)
        {
            return "登录密码长度6-16位,请输入正确格式密码！";
        }
        if (!newPwd.equals(checkPwd))
        {
            return "两次密码不一致，请输入正确格式密码！";
        }
        if (OLD_MODIFY_TYPE.equals(modifyType) && oldPwd.equals(newPwd))
        {
            return "新密码与旧密码一致，请输入正确格式密码！";
        }
        return null;
    }

    /**
     * 
     * <功能详细描述>密码验证
     * @param mobile
     * @param oldPwd
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void checkOlePwd(String mobile, String oldPwd) throws InnerException
    {
        // 校验账号密码
        int num = shopUserMapper.checkShopUserPwd(mobile, oldPwd);
        if (0 == num)
        {
            throw new InnerException(ErrorMsgConstant.OLDPWD_PASSWORD_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.OLDPWD_PASSWORD_FAIL));
        }

    }
}
