package com.fbw.service.base;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbw.service.dao.RechargeDao;
import com.fbw.service.entity.RechargeReqEntity;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.entity.user.UserBalanceDetailEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.GroundFeignClient;
import com.fbw.service.feign.UserFeignClient;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * <一句话功能简述> 控制层基类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseController implements BaseLogger
{
    @Autowired
    GroundFeignClient groundFeignClient;

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    RechargeDao rechargeDao;

    /**
     * 充值类型
     */
    final String RECHARGE_TYPE = "6";

    /**
     * <一句话功能简述> 失败返回信息 <功能详细描述>
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> failedMessage(String message)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "failed");
        map.put("message", message);
        return map;
    }

    /**
     * <一句话功能简述> 成功返回信息 <功能详细描述>
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> successMessage(String message)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("message", message);
        return map;
    }

    /**
     * <一句话功能简述> 成功返回数据 <功能详细描述>
     * @param data
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> successData(Map<String, Object> data)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("data", data);
        return map;
    }

    /**
     * 
     * <功能详细描述> 获取支付宝异步请求的报文
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected Map<String, String> getAliPayReqMsg(HttpServletRequest request)
    {
        Map<String, String> tempMap = new HashMap<String, String>();
        Map<String, String[]> reqMap = request.getParameterMap();
        Set<Entry<String, String[]>> set = reqMap.entrySet();
        Iterator<Entry<String, String[]>> it = set.iterator();
        while (it.hasNext())
        {
            Entry<String, String[]> entry = it.next();
            for (String str : entry.getValue())
            {
                tempMap.put(entry.getKey(), str);
            }
        }
        return tempMap;
    }

    /**
     * 
     * <功能详细描述> 构造充值实体类
     * @param notify
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public RechargeEntity buildRechargeEntity(RechargeReqEntity rechargeReqEntity) throws InnerException
    {
        RechargeEntity rechargeEntity = new RechargeEntity();
        rechargeEntity.setOrderNum(rechargeReqEntity.getOrderNum());
        rechargeEntity.setActualMoney(rechargeReqEntity.getActualMoney());
        rechargeEntity.setMobilePhone(rechargeReqEntity.getMobile());
        rechargeEntity.setDoubleMoney(rechargeReqEntity.getDoubleMoney());
        return rechargeEntity;
    }

    /**
     * 
     * <功能详细描述> 插入充值地推信息
     * @param rechargeEntity
     * @see [类、类#方法、类#成员]
     */
    public void insertRechargeWithGround(RechargeReqEntity rechargeReqEntity)
    {

        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setMobile(rechargeReqEntity.getMobile());
        registerEntity.setMoney(
                (rechargeReqEntity.getActualMoney() == null ? "0" : rechargeReqEntity.getActualMoney()).toString());
        registerEntity.setOrderNumber(rechargeReqEntity.getOrderNum());
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
                groundFeignClient.updateUserGroundVaildRechargeInfo(registerEntity);
            }
        });
    }

    /**
     * 
     * <功能详细描述> 插入余额详情信息
     * @param rechargeReqEntity
     * @see [类、类#方法、类#成员]
     */
    public void insertBalanceDetail(RechargeReqEntity rechargeReqEntity)
    {
        Observable.create(new ObservableOnSubscribe<UserBalanceDetailEntity>()
        {
            @Override
            public void subscribe(ObservableEmitter<UserBalanceDetailEntity> e) throws Exception
            {
                // 构造用户余额详情实体类
                UserBalanceDetailEntity userBalanceDetailEntity = buildUserBalanceDetailEntity(rechargeReqEntity);
                e.onNext(userBalanceDetailEntity);
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
     * @param rechargeReqEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserBalanceDetailEntity buildUserBalanceDetailEntity(RechargeReqEntity rechargeReqEntity)
    {
        UserBalanceDetailEntity userBalanceDetailEntity = new UserBalanceDetailEntity();
        UserInfoBusinEntity userInfoBusinEntity = userFeignClient.getUserBaseInfo(rechargeReqEntity.getMobile());
        userBalanceDetailEntity.setBalanceChange(new BigDecimal(rechargeReqEntity.getDoubleMoney()));
        userBalanceDetailEntity.setAfterBalanceChange(userInfoBusinEntity.getBalance());
        userBalanceDetailEntity.setGroupId(RECHARGE_TYPE);
        userBalanceDetailEntity.setUserId(userInfoBusinEntity.getUserId());
        return userBalanceDetailEntity;

    }

    /**
     * <功能详细描述>效验充值订单信息
     * @param reqXml
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkRechargeOrderInfo(RechargeReqEntity rechargeReqEntity) throws InnerException
    {
        // 效验充值订单状态
        rechargeDao.checkRechargeOrderStatus(rechargeReqEntity.getOrderNum(), rechargeReqEntity.getActualMoney());
    }

    /**
     * <一句话功能简述> define error level log <功能详细描述>
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public abstract void getErrorLog(String errorMsg);
}
