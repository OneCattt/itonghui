package com.fbw.service.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.base.BaseDao;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.entity.recharge.RechargeLogEntity;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.RechargeMapper;
import com.fbw.service.mapper.UserMapper;
import com.fbw.service.repository.RechargeLogRepository;
import com.fbw.service.repository.RechargeRepository;
import com.fbw.service.util.NomalUtil;
import com.google.common.util.concurrent.AtomicDouble;

/**
 * 
 * <功能详细描述> 充值数据层
 * @author JACK HUANG
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class RechargeDao extends BaseDao
{
    @Autowired
    RechargeRepository rechargeRepository;

    @Autowired
    RechargeMapper rechargeMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RechargeLogRepository rechargeLogRepository;

    @Autowired
    UserFeignClient userFeignClient;

    /**
     * 
     * <功能详细描述>保存充值记录
     * @param rechargeEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void saveRechargeRecord(RechargeEntity rechargeEntity) throws InnerException
    {
        try
        {
            rechargeRepository.save(rechargeEntity);
        }
        catch (Exception e)
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_DB_SAVE_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_DB_SAVE_ERROR));
        }

    }

    /**
     * 
     * <功能详细描述> 获取充值记录
     * @param orderNum
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public RechargeEntity selectRechargeRecordByOrderNum(String orderNum) throws InnerException
    {
        try
        {
            List<RechargeEntity> rechargeList = rechargeRepository.findByOrderNum(orderNum);
            return rechargeList.get(0);
        }
        catch (Exception e)
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_DB_QUERY_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_DB_QUERY_ERROR));
        }

    }

    /**
     * 
     * <功能详细描述> 效验充值订单状态
     * @param orderNumber
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkRechargeOrderStatus(String orderNum, BigDecimal actualMoney) throws InnerException
    {

        String status = rechargeMapper.getRechargeOrderRecord(orderNum, actualMoney);
        // 已经支付成功，直接返回
        if ("0".equals(status))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_ORDER_PAY_SUCCESS,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_ORDER_PAY_SUCCESS));
        }
        // 没有订单直接返回订单不存在
        if (NomalUtil.isNull(status))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_ORDER_NOT_EXICT,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_ORDER_NOT_EXICT));
        }

    }

    /**
     * 
     * <功能详细描述> 更新充值订单状态状态
     * @param rechargeEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public boolean updateRechargeOrderStatus(RechargeEntity rechargeEntity) throws InnerException
    {
        try
        {
            return rechargeMapper.updateRechargeStatus(rechargeEntity) == 0 ? false : true;

        }
        catch (Exception e)
        {
            getErrorLog(e.getMessage());
            throw new InnerException(ErrorMsgConstant.RECHARGE_DB_UPDATE_ORDER_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_DB_UPDATE_ORDER_ERROR));
        }
    }

    /**
     * 
     * <功能详细描述> 保存充值日志记录
     * @param rechargeLogEntity
     * @see [类、类#方法、类#成员]
     */
    public void saveRechargeLog(RechargeLogEntity rechargeLogEntity)
    {
        rechargeLogRepository.saveAndFlush(rechargeLogEntity);
    }

    /**
     * 
     * <功能详细描述> 更新用户余额信息
     * @param mobile 手机号
     * @param balance 余额
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public boolean updateUserBalanceInfo(String mobile, BigDecimal rechargrMoney) throws InnerException
    {
        try
        {
            // 查询用户余额
            String money = userMapper.selectBalanceByMobile(mobile);
            AtomicDouble ai = new AtomicDouble(Double.parseDouble(money));
            return userMapper.updateNewBalanceByMobileBalance(mobile, new BigDecimal(money),
                    new BigDecimal(ai.addAndGet(rechargrMoney.doubleValue()))) == 0 ? false : true;

        }
        catch (Exception e)
        {
            getErrorLog(e.getMessage());
            throw new InnerException(ErrorMsgConstant.RECHARGE_DB_UPDATE_BALANCE_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_DB_UPDATE_BALANCE_ERROR));
        }

    }

    /**
     * 根据userId获取充值列表 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String, Object>> getRechargeListByUserId(String mobile)
    {
        return rechargeMapper.getRechargeListByUserId(mobile);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(RechargeDao.class, errorMsg);
    }

}
