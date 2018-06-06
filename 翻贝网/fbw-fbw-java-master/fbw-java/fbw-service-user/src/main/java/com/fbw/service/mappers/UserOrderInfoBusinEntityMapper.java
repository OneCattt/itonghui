package com.fbw.service.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserOrderInfoBusinEntity;

/**
 * 用户订单Mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserOrderInfoBusinEntityMapper
{
    /**
     * 获取当前用户所有订单列表 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserOrderInfoBusinEntity> getUserOrderList(Integer param1, Integer param2);

    /**
     * 获取当前用户所有未评价订单列表 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserOrderInfoBusinEntity> getUserNoCommOrderList(Integer param1, Integer param2);

    /**
     * 获取某一订单详细信息 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserOrderInfoBusinEntity getUserOrderInfo(String param1, String param2);

    /**
     * 查询当前订单评论状态和退款状态 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserOrderInfoBusinEntity getUserOrderCommentAndRefund(String param1);

    /**
     * 获取当前用户暂未评价数量 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getCountUserNoCommOrder(Integer param1);

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
     * 
     * <功能详细描述>获取商家的消费次数
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getShopConsumptionTimes(int shopId);

    /**
     * 根据用户id获取订单列表 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Map<String, Object>> getUserOrderRechargeListByMobile(Integer param1, Integer param2);

    /**
     * 根据订单号更新评论状态 <功能详细描述>
     * @param orderNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateCommentStatus(String orderNumber);

    /**
     * 获取7天后未评价订单 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserOrderInfoBusinEntity> selectSevenDayNocommentOrder();

    /**
     * 获取当前商店所有订单数量 <功能详细描述>
     * @param orderNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getShopOrderNumber(Integer orderNumber);

}