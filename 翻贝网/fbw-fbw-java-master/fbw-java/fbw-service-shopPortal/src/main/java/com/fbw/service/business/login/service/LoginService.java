package com.fbw.service.business.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.login.entity.LoginReqEntity;
import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.entity.portal.RolePrivilegeEntity;
import com.fbw.service.entity.portal.ShopUserEntity;
import com.fbw.service.entity.portal.UserRoleEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.ShopUserMapper;
import com.fbw.service.mapper.ShopUserPrivilegeMapper;
import com.fbw.service.mq.SmsProductMq;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.JsonUtil;
import com.fbw.service.util.MD5Util;
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
public class LoginService
{

    @Autowired
    private SmsProductMq smsMq;

    @Autowired
    private GetCacheUtil cacheUtil;

    @Autowired
    private ShopUserMapper shopUserMapper;

    @Autowired
    private ShopUserPrivilegeMapper shopUserPrivilegeMapper;

    /**
     * 
     * <功能详细描述>短信验证码登录
     * @param mobile
     * @param code
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkSmsCode(String mobile, String code) throws InnerException
    {
        // 短线验证码验证
        String cacheCode = cacheUtil
                .getStringRedisVal(CacheKeyEnums.getMsg(CacheKeyConstant.SHOP_PORTAL_LOGIN_SMS_CODE) + mobile);
        // 判断缓存里验证码和客户端验证码是否一致
        if (!code.equals(cacheCode))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_SMS_CODE_FAIL_MSG,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_SMS_CODE_FAIL_MSG));
        }
    }

    /**
     * 
     * <功能详细描述>密码登录
     * @param loginEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkPwd(LoginReqEntity entity) throws InnerException
    {
        // 校验账号密码
        int num = shopUserMapper.checkShopUserPwd(entity.getMobile(), MD5Util.EncoderByMd5(entity.getPassword()));
        if (0 == num)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_LOGIN_PASSWORD_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_LOGIN_PASSWORD_FAIL));
        }

    }

    /**
     * 
     * <功能详细描述> 发送登陆短信
     * @param msg
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void sendLoginMsg(LoginReqEntity entity) throws InnerException
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String ramCode = NomalUtil.getRandomString(5);
        map.put("mobile", entity.getMobile());
        map.put("smsmodel", "SMS_105775012");
        map.put("code", ramCode);
        // 缓存10分钟登陆短信随机码
        cacheUtil.setTimeStringRedisVal(
                CacheKeyEnums.getMsg(CacheKeyConstant.SHOP_PORTAL_LOGIN_SMS_CODE) + entity.getMobile(), ramCode, 10,
                TimeUnit.MINUTES);
        String msg = JsonUtil.convertMapToJson(map);
        smsMq.smsSend(msg);
    }

    /**
     * 
     * <功能详细描述>校验账号是否存在
     * @param entity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkMobile(LoginReqEntity entity) throws InnerException
    {
        // 校验账号是否存在
        int num = shopUserMapper.getShopUserByMobile(entity.getMobile());
        if (0 == num)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_LOGIN_MOBILE_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_LOGIN_MOBILE_FAIL));
        }

    }

    /**
     * 
     * <功能详细描述>查询用户信息（角色、权限）
     * @param mobile
     * @see [类、类#方法、类#成员]
     */
    public ShopUserEntity getShopUserInfo(String mobile)
    {
        // 查询用户信息
        ShopUserEntity shopUserInfo = shopUserMapper.getShopUserInfoByMobile(mobile);
        // 隐藏密码，有密码为1，无密码为0
        if (!NomalUtil.isNullOrEmpty(shopUserInfo.getPassword()))
        {
            shopUserInfo.setPassword("1");
        }
        else
        {
            shopUserInfo.setPassword("0");
        }
        // 查询用户角色
        List<UserRoleEntity> userRoleList = shopUserPrivilegeMapper.getUserRoleByUserId(shopUserInfo.getUserId());
        if (!NomalUtil.isNullOrEmpty(userRoleList))
        {
            shopUserInfo.setUserRoleList(userRoleList);
            for (UserRoleEntity entity : userRoleList)
            {
                // 查询角色权限
                List<RolePrivilegeEntity> rolePrivilegeList = shopUserPrivilegeMapper
                        .getRolePrivilegeByRoleId(entity.getRoleId());
                entity.setRolePrivilegeList(rolePrivilegeList);
            }
        }

        return shopUserInfo;

    }

}
