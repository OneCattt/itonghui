package com.fbw.service.business.login.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.entity.user.UserDeviceInfoEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.feign.UserFeignClient;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * <功能详细描述> 登陆接口层
 * @author JACK HUANG
 * @version [版本号, 2017年9月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class LoginService
{
    @Autowired
    UserFeignClient userFeignClient;

    /**
     * 昵称生成规则
     */
    private final String NICK_NAME_FORMAT = "yyMMddHHmmss";

    /**
     * 激活用户账户
     */
    private final String ACTIVE_USER_ACCOUNT = "0";

    /**
     * 
     * <功能详细描述> 查询登陆用户信息
     * @param loginEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserInfoBusinEntity queryLoginUserInfo(LoginEntity loginEntity)
    {
        return userFeignClient.getUserBaseInfo(loginEntity.getMobile());
    }

    /**
     * 
     * <功能详细描述> 效验是否是微信免登陆
     * @param openId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserInfoBusinEntity checkWeChatNoLanding(String openId)
    {
        return userFeignClient.selectByOpenId(openId);

    }

    /**
     * 
     * <功能详细描述> 保存登陆用户信息
     * @param userInforBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean saveLoginUserInfo(LoginEntity loginEntity)
    {
        boolean flag = false;
        // 保存用户基本信息
        flag = userFeignClient.saveUserBaseInfo(buildSaveUserInfoEntity(loginEntity));
        // 如果没有设备信息就不保存
        if (null != loginEntity.getDeviceId() && null != loginEntity.getOsVersion()
                && null != loginEntity.getPhoneType())
        {

            Observable.create(new ObservableOnSubscribe<UserDeviceInfoEntity>()
            {

                @Override
                public void subscribe(ObservableEmitter<UserDeviceInfoEntity> e) throws Exception
                {
                    e.onNext(buildUserDeviceInfoEntity(loginEntity));
                }
            }).observeOn(Schedulers.io()).subscribe(new Consumer<UserDeviceInfoEntity>()
            {

                @Override
                public void accept(UserDeviceInfoEntity t) throws Exception
                {
                    // 保存用户设备信息
                    userFeignClient.saveUserDeiviceInfo(t);
                }
            });

        }
        return flag;

    }

    /**
     * 
     * <功能详细描述> 构造保存用户信息实体类
     * @param loginEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserInfoBusinEntity buildSaveUserInfoEntity(LoginEntity loginEntity)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(NICK_NAME_FORMAT);
        UserInfoBusinEntity userInfoBusinEntity = new UserInfoBusinEntity();
        userInfoBusinEntity.setMobile(loginEntity.getMobile());
        userInfoBusinEntity.setUserName(loginEntity.getUserName());
        userInfoBusinEntity.setNickName("fbw_" + sdf.format(new Date()));
        userInfoBusinEntity.setOpenId(loginEntity.getOpenId());
        userInfoBusinEntity.setCity(loginEntity.getCityId());
        userInfoBusinEntity.setStatus(ACTIVE_USER_ACCOUNT);
        return userInfoBusinEntity;

    }

    /**
     * 
     * <功能详细描述> 构造用户设备信息实体类
     * @param loginEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserDeviceInfoEntity buildUserDeviceInfoEntity(LoginEntity loginEntity)
    {
        UserDeviceInfoEntity userDeviceInfoEntity = new UserDeviceInfoEntity();
        userDeviceInfoEntity.setDeviceId(loginEntity.getDeviceId());
        userDeviceInfoEntity.setIosOrAndroid(loginEntity.getIosOrAndroid());
        userDeviceInfoEntity.setOsVersion(loginEntity.getOsVersion());
        userDeviceInfoEntity.setRegisterArea(loginEntity.getRegisterArea());
        userDeviceInfoEntity.setRegisterSource(loginEntity.getRegisterSource());
        userDeviceInfoEntity.setUserMobile(loginEntity.getMobile());
        userDeviceInfoEntity.setIp(loginEntity.getIp());
        return userDeviceInfoEntity;

    }
}
