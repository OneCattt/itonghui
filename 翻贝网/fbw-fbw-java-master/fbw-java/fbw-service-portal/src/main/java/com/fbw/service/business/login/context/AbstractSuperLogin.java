package com.fbw.service.business.login.context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbw.service.base.BaseLogger;
import com.fbw.service.business.login.entity.LoginEntity;
import com.fbw.service.business.login.entity.LoginReturnEntity;
import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * <功能详细描述> 抽象登陆父类
 * @author JACK HUANG
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractSuperLogin implements BaseLogger
{

    @Autowired
    GetCacheUtil cacheUtil;

    @Autowired
    CommonFeignClient commonFeignClient;

    @Autowired
    UserFeignClient userFeignClient;

    /**
     * 相同设备标志
     */
    private final String SAME_DEVICE_FALG = "0";

    /**
     * 不是相同设备标志
     */
    private final String NOT_SAME_DEVICE_FALG = "1";

    /**
     * 
     * <功能详细描述> 登陆
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public abstract LoginReturnEntity login(LoginEntity loginEntity) throws InnerException;

    /**
     * <一句话功能简述> define error level log <功能详细描述>
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public abstract void getErrorLog(String errorMsg);

    /**
     * 
     * <功能详细描述> 效验登陆参数
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public abstract LoginReturnEntity checkLoginParm(LoginEntity loginEntity);

    /**
     * 
     * <功能详细描述>构造相同设备ID参数
     * @param loginEntity
     * @see [类、类#方法、类#成员]
     */
    public void buildSameDeviceIdParm(LoginEntity loginEntity)
    {

        Observable.create(new ObservableOnSubscribe<LoginEntity>()
        {

            @Override
            public void subscribe(ObservableEmitter<LoginEntity> e) throws Exception
            {
                e.onNext(loginEntity);
            }
        }).observeOn(Schedulers.io()).subscribe(new Consumer<LoginEntity>()
        {

            @Override
            public void accept(LoginEntity t) throws Exception
            {
                if (userFeignClient.queryUserDeviceIdInfo(loginEntity.getDeviceId()))
                {
                    loginEntity.setSameDeviceFlag(SAME_DEVICE_FALG);
                }
                else
                {
                    loginEntity.setSameDeviceFlag(NOT_SAME_DEVICE_FALG);
                }
            }
        });

    }

    /**
     * 
     * <功能详细描述> 判断是否是测试号码
     * @return true 为测试号码 false不是测试号码
     * @see [类、类#方法、类#成员]
     */
    private LoginReturnEntity isTestMobiles(String mobiles)
    {
        // 检验是否是测试号码
        return commonReturnMsg(checkMobiles(CacheKeyConstant.PORTAL_TEST_MOBILES_KEY, mobiles));
    }

    /**
     * 
     * <功能详细描述> 判断是否是黑名单号码
     * @return true 为黑名单号码 false不是黑名单号码
     * @see [类、类#方法、类#成员]
     */
    private LoginReturnEntity isBlackMobiles(String mobiles)
    {
        // 检验是否是黑名单号码
        return commonReturnMsg(checkMobiles(CacheKeyConstant.PORTAL_BLACK_MOBILES_KEY, mobiles));
    }

    /**
     * 
     * <功能详细描述> 判断是否是手机格式
     * @return true 为手机号码 false不是手机号码
     * @see [类、类#方法、类#成员]
     */
    private LoginReturnEntity isMobilesFormat(String mobiles)
    {
        LoginReturnEntity returnEntity = new LoginReturnEntity(true);
        if (!NomalUtil.isMobileNo(mobiles))
        {
            returnEntity.setCheckLoginFlag(false);
            returnEntity.setFailReason(ErrorMsgConstant.PORTAL_MOBILE_FORMAT_FAIL_MSG);
            return returnEntity;
        }
        return returnEntity;

    }

    /**
     * 
     * <功能详细描述> 检测短信验证码是否成功
     * @return true 验证通过 false 验证失败
     * @see [类、类#方法、类#成员]
     */
    protected LoginReturnEntity checkSmsCode(String mobiles, String code)
    {
        LoginReturnEntity returnEntity = new LoginReturnEntity(true);
        String cacheCode = cacheUtil
                .getStringRedisVal(CacheKeyEnums.getMsg(CacheKeyConstant.PORTAL_LOGIN_SMS_CODE) + mobiles);
        // 判断缓存里验证码和客户端验证码是否一致
        if (!code.equals(cacheCode))
        {
            returnEntity.setCheckLoginFlag(false);
            returnEntity.setFailReason(ErrorMsgConstant.PORTAL_SMS_CODE_FAIL_MSG);
            return returnEntity;
        }
        return returnEntity;
    }

    /**
     * 
     * <功能详细描述>检验号码
     * @param checkType 检测类型
     * @return
     * @see [类、类#方法、类#成员]
     */
    private LoginReturnEntity checkMobiles(String checkType, String mobiles)
    {
        LoginReturnEntity returnEntity = new LoginReturnEntity(true);
        List<String> mobileList = cacheUtil.getDictNumList(checkType);
        for (String mobilePhone : mobileList)
        {
            // 如果是测试号码直接返回免登陆
            if (mobilePhone.equals(mobiles) && checkType.equals(CacheKeyConstant.PORTAL_TEST_MOBILES_KEY))
            {
                returnEntity.setCheckLoginFlag(false);
                returnEntity.setNoLandingFlag(true);
                return returnEntity;
            }
            // 如果是黑名单返回提示信息
            else if (mobilePhone.equals(mobiles) && checkType.equals(CacheKeyConstant.PORTAL_BLACK_MOBILES_KEY))
            {
                returnEntity.setCheckLoginFlag(false);
                returnEntity.setFailReason(ErrorMsgConstant.PORTAL_BLACK_USER_FAIL_MSG);
                return returnEntity;
            }

        }
        return returnEntity;

    }

    /**
     * 
     * <功能详细描述> 生成登陆token
     * @return
     * @see [类、类#方法、类#成员]
     */
    public LoginReturnEntity getLoginToken(String mobile)
    {
        UserInfoBusinEntity userInfoEntity = userFeignClient.getUserBaseInfo(mobile);
        LoginReturnEntity returnEntity = new LoginReturnEntity(true);
        String token = commonFeignClient.getrandomstring(125);
        StringBuffer sbf = new StringBuffer();
        String value = sbf.append(userInfoEntity.getUserId()).append(",").append(mobile).toString();
        String key = CacheKeyEnums.getMsg(CacheKeyConstant.PORTAL_LOGIN_TOKEN_KEY) + token;
        cacheUtil.setTimeStringRedisVal(key, value, 43200L, TimeUnit.MINUTES);
        returnEntity.setToken(token);
        return returnEntity;

    }

    /**
     * <功能详细描述>是否免登陆的号码(30天免登陆)
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected LoginReturnEntity isNoLandingMobile(LoginEntity loginEntity)
    {
        String key = CacheKeyEnums.getMsg(CacheKeyConstant.PORTAL_LOGIN_TOKEN_KEY) + loginEntity.getToken();
        // 检验是否是免登陆号码
        return commonReturnMsg(checkToken(key, loginEntity.getMobile()));
    }

    /**
     * 
     * <功能详细描述> 效验Token的正确性
     * @param key
     * @param token
     * @return
     * @see [类、类#方法、类#成员]
     */
    private LoginReturnEntity checkToken(String key, String mobile)
    {
        LoginReturnEntity returnEntity = new LoginReturnEntity(true);
        String cacheLoginToken = cacheUtil.getStringRedisVal(key);
        String[] userInfoArray = cacheLoginToken.split(",");
        if (!NomalUtil.isNullOrEmpty(cacheLoginToken) && userInfoArray[1].equals(mobile))
        {
            returnEntity.setCheckLoginFlag(false);
            returnEntity.setNoLandingFlag(true);
            return returnEntity;
        }
        return returnEntity;

    }

    /**
     * 
     * <功能详细描述> 公共返回参数
     * @param returnEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private LoginReturnEntity commonReturnMsg(LoginReturnEntity returnEntity)
    {
        if (!returnEntity.isCheckLoginFlag())
        {
            return returnEntity;
        }
        returnEntity.setCheckLoginFlag(true);
        return returnEntity;

    }

    /**
     * 
     * <功能详细描述> 判断公共登陆条件
     * @return
     * @see [类、类#方法、类#成员]
     */

    public LoginReturnEntity checkCommonLogin(LoginEntity loginEntity)
    {
        String mobiles = loginEntity.getMobile();
        LoginReturnEntity returnEntity = new LoginReturnEntity(true);
        // 判断是否是测试号码
        LoginReturnEntity testMobileEntity = isTestMobiles(mobiles);
        // 判断是否是黑名单号码
        LoginReturnEntity blackMobileEntity = isBlackMobiles(mobiles);
        // 判断是否是手机格式
        LoginReturnEntity mobileFormatEntity = isMobilesFormat(mobiles);
        // 检测短信验证码是否成功
        LoginReturnEntity checkSmsEntity = checkSmsCode(loginEntity.getMobile(),
                loginEntity.getCode() == null ? "" : loginEntity.getCode());
        // 判断是否是测试号码
        if (!testMobileEntity.isCheckLoginFlag())
        {
            return testMobileEntity;
        }
        // 判断是否是黑名单号码
        if (!blackMobileEntity.isCheckLoginFlag())
        {
            return blackMobileEntity;
        }
        // 判断是否是手机格式
        if (!mobileFormatEntity.isCheckLoginFlag())
        {
            return mobileFormatEntity;
        }
        // 检测短信验证码是否成功
        if (!checkSmsEntity.isCheckLoginFlag())
        {
            return checkSmsEntity;
        }
        return returnEntity;
    }

    protected void getSuperErrorLog(String errorMsg)
    {
        getBaseLogger(AbstractSuperLogin.class, errorMsg);
    }

}
