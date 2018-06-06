package com.fbw.service.services;

import java.util.List;
import java.util.Map;

import com.fbw.service.entity.user.UserOrderInfoBusinEntity;

public interface UserOrderInfoSevice
{
    /**
     * 获取当前用户所有订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserOrderInfoBusinEntity> getUserOrderList(Integer userId, Integer begin);

    /**
     * 获取当前用户所有未评价订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserOrderInfoBusinEntity> getUserNoCommOrderList(Integer userId, Integer begin);

    /**
     * 获取某一订单详细信息 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserOrderInfoBusinEntity getUserOrderInfo(String orderNumber, String refundStatus);

    /**
     * 获取当前用户暂未评价数量 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getCountUserNoCommOrder(Integer userId);

    /**
     * 保存用户订单信息 <功能详细描述>
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int saveUserOrderInfo(UserOrderInfoBusinEntity userOrderInfoBusinEntity);

    /**
     * 获取用户当前店铺消费次数 <功能详细描述>
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getUserConsumptionTimes(UserOrderInfoBusinEntity userOrderInfoBusinEntity);

    /**
     * 根据用户手机号获取订单列表 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Map<String, Object>> getUserOrderRechargeListByMobile(Integer mobile, Integer begin);

}
