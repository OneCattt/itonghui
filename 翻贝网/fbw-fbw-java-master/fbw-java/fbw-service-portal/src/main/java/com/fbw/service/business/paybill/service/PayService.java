package com.fbw.service.business.paybill.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.paybill.entity.PayEntity;
import com.fbw.service.contents.PortalConstant;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.shop.ShopDetailEntity;
import com.fbw.service.entity.shop.ShopEventEntity;
import com.fbw.service.entity.user.UserBalanceDetailEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.entity.user.UserPointDetailEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.GroundFeignClient;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.MD5Util;
import com.fbw.service.util.NomalUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * <功能详细描述> 买单接口类
 * @author JACK HUANG
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class PayService
{
    @Autowired
    GetCacheUtil getCache;

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    GroundFeignClient groundFeignClient;

    @Autowired
    ShopFeignClient shopFeignClient;

    /**
     * 拥有密码
     */
    final String HAS_PASSWORD = "1";

    /**
     * 商户每日订单限制类型
     */
    final String LIMIT_EVERYDAY_ORDER_TYPE = "1";

    /**
     * 更新翻倍日期类型
     */
    final String UPDATE_DOUBLEDAY_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 消费买单类型
     */
    final String CONSUME_PAYBILL_TYPE = "1";

    /**
     * 
     * <功能详细描述> 检查用户支付密码
     * @param mobile
     * @param passWord
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkUserPassWord(String mobile, String passWord) throws InnerException
    {
        UserInfoBusinEntity userInfoBusin = userFeignClient.getUserBaseInfo(mobile);
        // 用户信息不为空且有支付密码且密码不正确
        if (!NomalUtil.isNullOrEmpty(userInfoBusin))
        {
            if (HAS_PASSWORD.equals(userInfoBusin.getIsUsePassword())
                    && !passWord.equals(userInfoBusin.getPayPassword()))
            {
                throw new InnerException(ErrorMsgConstant.PORTAL_PAY_PASSWORD_FAIL,
                        InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_PASSWORD_FAIL));
            }
        }
        else
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_PAY_USER_NOT_EXIST,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_USER_NOT_EXIST));
        }
    }

    /**
     * 
     * <功能详细描述>获取加密的支付密码
     * @param passWord
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public String getEncoderPayPassWord(String passWord) throws InnerException
    {
        return MD5Util.EncoderByMd5(PortalConstant.PORTAL_PAY_PASSWORD_MD5_KEY + passWord);
    }

    /**
     * 
     * <功能详细描述> 插入地推订单信息
     * @param registerEntity
     * @see [类、类#方法、类#成员]
     */
    public void insertOrderWithGround(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setCityId(userOrderInfoBusinEntity.getCityId().toString());
        registerEntity.setOrderNumber(userOrderInfoBusinEntity.getOrderNumber());
        registerEntity.setMoney(userOrderInfoBusinEntity.getOrderAmount().toString());
        Observable.create(new ObservableOnSubscribe<RegisterEntity>()
        {
            @Override
            public void subscribe(ObservableEmitter<RegisterEntity> e) throws Exception
            {
                e.onNext(registerEntity);
            }
        }).observeOn(Schedulers.io()).subscribe(new Consumer<RegisterEntity>()
        {
            @Override
            public void accept(RegisterEntity t) throws Exception
            {
                groundFeignClient.updateUserGroundVaildOrderInfo(registerEntity);
            }
        });
    }

    /**
     * 
     * <功能详细描述>效验买单状态
     * @param payEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkPayBillStatus(PayEntity payEntity) throws InnerException
    {
        ShopEventEntity shopsEventEntity = shopFeignClient
                .getShopsNowEventsByShopId(Integer.parseInt(payEntity.getShopId()), payEntity.getTrackId());
        if (NomalUtil.isNullOrEmpty(shopsEventEntity)
                || !payEntity.getPayType().equals(shopsEventEntity.getEventType().toString()))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_PAY_BILL_WAY_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_BILL_WAY_FAIL));
        }
    }

    /**
     * 
     * <功能详细描述>更新翻倍日
     * @param payEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void updateDoubleDay(PayEntity payEntity) throws InnerException
    {
        HomeReqEntity homeReqEntity = new HomeReqEntity();
        homeReqEntity.setShopId(payEntity.getShopId());
        ShopDetailEntity shopsDetailEntity = shopFeignClient.getShopsDetailByShopid(homeReqEntity);
        if (NomalUtil.isNullOrEmpty(shopsDetailEntity))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_HOME_SHOPINFO_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_HOME_SHOPINFO_FAIL));
        }
        // 判断是否是商户每日订单限制类型
        if (LIMIT_EVERYDAY_ORDER_TYPE.equals(shopsDetailEntity.getLimitedType()))
        {
            int ShopConsumptionTimes = userFeignClient.getShopConsumptionTimes(Integer.parseInt(payEntity.getShopId()));
            // 判断是否达到商户的每日限制次数
            if (shopsDetailEntity.getLimitedNum() == ShopConsumptionTimes)
            {
                SimpleDateFormat sdfs = new SimpleDateFormat(UPDATE_DOUBLEDAY_DATE_FORMAT);
                ShopEventEntity entity = new ShopEventEntity();
                entity.setShopId(Integer.parseInt(payEntity.getShopId()));
                entity.setEvent(sdfs.format(new Date()));
                // 更新对应的商户类
                if (!shopFeignClient.updateShopEventStatus(entity))
                {
                    throw new InnerException(ErrorMsgConstant.PORTAL_UPDATE_DOUBLEDAY_FAIL,
                            InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_UPDATE_DOUBLEDAY_FAIL));
                }
            }
        }

    }

    /**
     * 
     * <功能详细描述> 插入余额明细信息
     * @param userOrderInfoBusinEntity
     * @see [类、类#方法、类#成员]
     */
    public void insertBalanceDetail(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        // 构造用户余额详情实体类
        UserBalanceDetailEntity userBalanceDetail = buildUserBalanceDetailEntity(userOrderInfoBusinEntity);
        Observable.create(new ObservableOnSubscribe<UserBalanceDetailEntity>()
        {
            @Override
            public void subscribe(ObservableEmitter<UserBalanceDetailEntity> e) throws Exception
            {
                e.onNext(userBalanceDetail);
            }
        }).observeOn(Schedulers.io()).subscribe(new Consumer<UserBalanceDetailEntity>()
        {
            @Override
            public void accept(UserBalanceDetailEntity t) throws Exception
            {
                userFeignClient.insertUserBalanceDetail(t);
            }
        });
    }

    /**
     * 
     * <功能详细描述> 构造用户余额详情实体类
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserBalanceDetailEntity buildUserBalanceDetailEntity(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        UserBalanceDetailEntity userBalanceDetail = new UserBalanceDetailEntity();
        userBalanceDetail.setBalanceChange(userOrderInfoBusinEntity.getActualAmount());
        userBalanceDetail.setAfterBalanceChange(userOrderInfoBusinEntity.getLastBalance());
        userBalanceDetail.setGroupId(userOrderInfoBusinEntity.getOrderNumber());
        userBalanceDetail.setType(userOrderInfoBusinEntity.getPayType());
        userBalanceDetail.setShopId(userOrderInfoBusinEntity.getShopId());
        userBalanceDetail.setUserId(userOrderInfoBusinEntity.getUserId());
        return userBalanceDetail;

    }

    /**
     * 
     * <功能详细描述> 插入用户积分信息
     * @param userOrderInfoBusinEntity
     * @see [类、类#方法、类#成员]
     */
    public void insertUserScoreInfo(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        Observable.create(new ObservableOnSubscribe<UserPointDetailEntity>()
        {
            @Override
            public void subscribe(ObservableEmitter<UserPointDetailEntity> e) throws Exception
            {
                // 构造用户积分详情实体类
                UserPointDetailEntity userPointDetailEntity = buildUserPointDetailEntity(userOrderInfoBusinEntity);
                e.onNext(userPointDetailEntity);
            }
        }).observeOn(Schedulers.io()).subscribe(new Consumer<UserPointDetailEntity>()
        {
            @Override
            public void accept(UserPointDetailEntity t) throws Exception
            {
                userFeignClient.updateUserPoint(t);
            }
        });
    }

    /**
     * 
     * <功能详细描述> 构造用户积分详情实体类
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserPointDetailEntity buildUserPointDetailEntity(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        UserPointDetailEntity userPointDetailEntity = new UserPointDetailEntity();
        userPointDetailEntity.setOrderAmount(userOrderInfoBusinEntity.getOrderAmount() == null ? ""
                : userOrderInfoBusinEntity.getOrderAmount().toString());
        userPointDetailEntity.setRemark(CONSUME_PAYBILL_TYPE);
        userPointDetailEntity.setUserId(userOrderInfoBusinEntity.getUserId());
        return userPointDetailEntity;

    }

}
