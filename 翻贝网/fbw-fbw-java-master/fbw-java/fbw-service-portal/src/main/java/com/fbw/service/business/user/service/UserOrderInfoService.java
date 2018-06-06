package com.fbw.service.business.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.user.UserBalanceDetailEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.feign.UserFeignClient;

/**
 * 用户订单Service <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserOrderInfoService
{
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取当前用户所有订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserOrderInfoBusinEntity> getUserOrderList(int userId, int begin)
    {
        return userFeignClient.getUserOrderList(userId, begin);

    }

    /**
     * 获取当前用户所有未评价订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserOrderInfoBusinEntity> getUserNoCommOrderList(int userId, int begin)
    {
        return userFeignClient.getUserNoCommOrderList(userId, begin);
    }

    /**
     * 获取某一订单详细信息 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserOrderInfoBusinEntity getUserOrderInfo(String orderNumber, String refundStatus)
    {
        return userFeignClient.getUserOrderInfo(orderNumber, refundStatus);
    }

    /**
     * 保存用户订单信息 <功能详细描述>
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean saveUserOrderInfo(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        return userFeignClient.saveUserOrderInfo(userOrderInfoBusinEntity);

    }

    /**
     * 获取当前用户暂未评价数量 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int getCountUserNoCommOrder(Integer userId)
    {
        return userFeignClient.getCountUserNoCommOrder(userId);
    }

    /**
     * 根据手机号获取用户90天内所有订单 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserBalanceDetailEntity> getUserBalanceDetailById(Integer userId, Integer begin)
    {
        return userFeignClient.getUserBalanceDetailById(userId, begin);
    }

}
