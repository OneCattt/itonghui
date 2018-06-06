package com.fbw.service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.base.BaseService;
import com.fbw.service.dao.RechargeDao;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.entity.recharge.RechargeLogEntity;
import com.fbw.service.exception.InnerException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 
 * <功能详细描述> 充值接口层
 * @author JACK HUANG
 * @version [版本号, 2017年8月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class RechargeService extends BaseService
{
    @Autowired
    RechargeDao rechargeDao;

    public void saveRechargeOrderRecord(RechargeEntity rechargeEntity) throws InnerException
    {
        rechargeDao.saveRechargeRecord(rechargeEntity);
    }

    public List<Map<String, Object>> getRechargeListByUserId(String mobile)
    {
        return rechargeDao.getRechargeListByUserId(mobile);

    }

    /**
     * 
     * <功能详细描述> 获取充值记录
     * @param orderNum
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public RechargeEntity getRechargeOrderRecord(String orderNum) throws InnerException
    {
        return rechargeDao.selectRechargeRecordByOrderNum(orderNum);
    }

    /**
     * 
     * <功能详细描述>保存充值日志记录
     * @param rechargeLogEntity
     * @see [类、类#方法、类#成员]
     */
    public void saveRechargeLog(RechargeLogEntity rechargeLogEntity)
    {
        Observable.create(new ObservableOnSubscribe<RechargeLogEntity>()
        {

            @Override
            public void subscribe(ObservableEmitter<RechargeLogEntity> e) throws Exception
            {
                e.onNext(rechargeLogEntity);
            }
        }).observeOn(Schedulers.io()).subscribe(new Consumer<RechargeLogEntity>()
        {

            @Override
            public void accept(RechargeLogEntity t) throws Exception
            {
                rechargeDao.saveRechargeLog(t);
            }
        });
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
