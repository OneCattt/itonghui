package com.fbw.service.business.paybill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.business.paybill.context.AbstractSuperPay;
import com.fbw.service.business.paybill.entity.PayEntity;
import com.fbw.service.business.paybill.entity.PayReturnMoneyEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.UserFeignClient;

/**
 * 
 * <功能详细描述> 全天翻倍花策略
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class AllDayPayService extends AbstractSuperPay
{

    @Autowired
    UserFeignClient userFeignClient;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public UserOrderInfoBusinEntity createPayOrder(PayEntity payEntity) throws InnerException
    {

        // 效验生成买单订单参数
        checkPayOrderParm(payEntity);
        // 效验买单资格
        checkPayQuality(payEntity);
        // 获取买单金额
        PayReturnMoneyEntity payReturnMoneyEntity = buildPayBillMoney(createPayMoney(payEntity));
        // 更新用户余额记录
        updateUserBalanceRecord(payEntity.getMobile(), payReturnMoneyEntity.getActivityPaid());
        // 插入用户订单记录
        UserOrderInfoBusinEntity userOrderInfoBusinEntity = insertUserOrderRecord(payEntity, payReturnMoneyEntity);
        // 更新用户红包状态
        updateUserRedPacketStatus(payReturnMoneyEntity);

        return userOrderInfoBusinEntity;
    }

    @Override
    public PayReturnMoneyEntity createPayMoney(PayEntity payEntity) throws InnerException
    {
        // 订单金额
        double orderMoney = conversionsOrderMoney(Double.parseDouble(payEntity.getOrderMoney()));
        // 获取实付金额
        PayReturnMoneyEntity payReturnMoneyEntity = getActivityPaid(payEntity.getShopId(), payEntity.getMobile(),
                orderMoney, Integer.parseInt(payEntity.getUserId()));
        // 获取节省的金额
        payReturnMoneyEntity
                .setSaveMoney(conversionsOrderMoney(getSaveMoney(orderMoney, payReturnMoneyEntity.getActivityPaid())));
        return payReturnMoneyEntity;
    }

}
