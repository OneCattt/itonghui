package com.fbw.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.feign.UserFeignClient;

/**
 * 
 * <功能详细描述> 公共方法工具类
 * @author JACK HUANG
 * @version [版本号, 2017年9月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class CommonUtil
{
    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    GetCacheUtil cacheUtil;

    /**
     * 相同设备标志
     */
    private final String SAME_DEVICE_FALG = "0";

    /**
     * 登陆时间控制
     */
    final String LOGIN_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 
     * <功能详细描述> 判断是否是新用户
     * @param loginEntity
     * @return true为新用户 false为老用户
     * @see [类、类#方法、类#成员]
     */
    public boolean isNewUser(String mobile)
    {
        UserInfoBusinEntity userInfoEntity = userFeignClient.getUserBaseInfo(mobile);
        if (NomalUtil.isNullOrEmpty(userInfoEntity))
        {
            return true;
        }
        return false;

    }

    /**
     * 
     * <功能详细描述>判断是否是无效的地推用户
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isGroundInvaildRegisterUser(String mobile, String cityId, String sameDeviceFlag)
    {
        UserInfoBusinEntity userInfoEntity = userFeignClient.getUserBaseInfo(mobile);
        String loginKey = CacheKeyConstant.PORTAL_GROUND_REGISTER_LOGIN_DATE_CONF + mobile;
        String loginDate = cacheUtil.getStringRedisVal(loginKey);
        // 为空认为是无效用户
        if (NomalUtil.isNullOrEmpty(loginDate))
        {
            return true;
        }
        // 不是同城用户认为无效
        if (!cityId.equals(userInfoEntity.getCity()))
        {
            return true;
        }
        // 同一设备登录的新用户
        if (SAME_DEVICE_FALG.equals(sameDeviceFlag))
        {
            return true;
        }
        return false;

    }

    /**
     * 
     * <功能详细描述>判断是否是无效的邀请码渠道注册地推用户
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isInVaildGroundRegUserWithInviteCode(String mobile, String cityId, String sameDeviceFlag)
    {
        UserInfoBusinEntity userInfoEntity = userFeignClient.getUserBaseInfo(mobile);
        // 不是同城用户认为无效
        if (!cityId.equals(userInfoEntity.getCity()))
        {
            return true;
        }
        // 同一设备登录的新用户
        if (SAME_DEVICE_FALG.equals(sameDeviceFlag))
        {
            return true;
        }
        return false;

    }

    /**
     * 
     * <功能详细描述> 保存地推登陆时间
     * @param mobile
     * @see [类、类#方法、类#成员]
     */
    public void saveGroundLoginDate(String mobile)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(LOGIN_DATE_FORMAT);
        String loginKey = CacheKeyConstant.PORTAL_GROUND_REGISTER_LOGIN_DATE_CONF + mobile;
        // 保存用户地推登陆时间
        cacheUtil.setTimeStringRedisVal(loginKey, sdf.format(new Date()), 2, TimeUnit.HOURS);
    }

}
