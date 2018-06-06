package com.fbw.service.services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.mappers.UserOrderInfoBusinEntityMapper;
import com.fbw.service.services.UserOrderInfoSevice;

@Service
public class UserOrderInfoSeviceImpl implements UserOrderInfoSevice
{
    @Resource
    private UserOrderInfoBusinEntityMapper userOrderInfoMapper;

    @Override
    public List<UserOrderInfoBusinEntity> getUserOrderList(Integer userId, Integer begin)
    {
        return userOrderInfoMapper.getUserOrderList(userId, begin);
    }

    @Override
    public List<UserOrderInfoBusinEntity> getUserNoCommOrderList(Integer userId, Integer begin)
    {
        return userOrderInfoMapper.getUserNoCommOrderList(userId, begin);
    }

    @Override
    public UserOrderInfoBusinEntity getUserOrderInfo(String orderNumber, String refundStatus)
    {
        return userOrderInfoMapper.getUserOrderInfo(orderNumber, refundStatus);
    }

    @Override
    public int getCountUserNoCommOrder(Integer userId)
    {
        return userOrderInfoMapper.getCountUserNoCommOrder(userId);
    }

    @Override
    public int saveUserOrderInfo(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        return userOrderInfoMapper.saveUserOrderInfo(userOrderInfoBusinEntity);
    }

    @Override
    public int getUserConsumptionTimes(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        return userOrderInfoMapper.getUserConsumptionTimes(userOrderInfoBusinEntity);
    }

    @Override
    public List<Map<String, Object>> getUserOrderRechargeListByMobile(Integer userId, Integer begin)
    {
        return userOrderInfoMapper.getUserOrderRechargeListByMobile(userId, begin);
    }

}
