package com.fbw.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.user.UserBalanceDetailEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.mappers.UserBalanceDetailMapper;
import com.fbw.service.mappers.UserOrderInfoBusinEntityMapper;
import com.fbw.service.services.UserOrderInfoSevice;

@RestController
@RequestMapping("/userOrder")
public class UserOrderInfoController extends BaseController
{
    @Autowired
    private UserOrderInfoSevice userOrderInfoSevice;

    @Autowired
    private UserOrderInfoBusinEntityMapper userOrderInfoBusinEntityMapper;

    @Autowired
    private UserBalanceDetailMapper userBalanceDetailMapper;

    /**
     * 获取当前用户所有订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserOrderList", method = RequestMethod.GET)
    public List<UserOrderInfoBusinEntity> getUserOrderList(Integer userId, Integer begin)
    {
        List<UserOrderInfoBusinEntity> userOrderInfoBusinEntities = new ArrayList<UserOrderInfoBusinEntity>();
        userOrderInfoBusinEntities = userOrderInfoSevice.getUserOrderList(userId, begin);
        return userOrderInfoBusinEntities;

    }

    /**
     * 获取当前用户所有未评价订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserNoCommOrderList", method = RequestMethod.GET)
    public List<UserOrderInfoBusinEntity> getUserNoCommOrderList(Integer userId, Integer begin)
    {
        List<UserOrderInfoBusinEntity> userOrderInfoBusinEntities = new ArrayList<UserOrderInfoBusinEntity>();
        userOrderInfoBusinEntities = userOrderInfoSevice.getUserNoCommOrderList(userId, begin);
        return userOrderInfoBusinEntities;

    }

    /**
     * 获取某一订单详细信息 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserOrderInfo", method = RequestMethod.GET)
    public UserOrderInfoBusinEntity getUserOrderInfo(String orderNumber, String refundStatus)
    {
        UserOrderInfoBusinEntity userOrderInfoBusinEntity = new UserOrderInfoBusinEntity();
        userOrderInfoBusinEntity = userOrderInfoSevice.getUserOrderInfo(orderNumber, refundStatus);
        return userOrderInfoBusinEntity;

    }

    /**
     * 查询当前订单评论状态和退款状态 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserOrderCommentAndRefund", method = RequestMethod.GET)
    public UserOrderInfoBusinEntity getUserOrderCommentAndRefund(String orderNumber)
    {
        return userOrderInfoBusinEntityMapper.getUserOrderCommentAndRefund(orderNumber);

    }

    /**
     * 获取当前用户暂未评价数量 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getCountUserNoCommOrder", method = RequestMethod.GET)
    public int getCountUserNoCommOrder(Integer userId)
    {
        return userOrderInfoSevice.getCountUserNoCommOrder(userId);
    }

    /**
     * 保存用户订单信息 <功能详细描述>
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveUserOrderInfo", method = RequestMethod.POST)
    public boolean saveUserOrderInfo(@RequestBody UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        if (null == userOrderInfoBusinEntity)
        {
            return false;
        }
        int flag = userOrderInfoSevice.saveUserOrderInfo(userOrderInfoBusinEntity);

        if (flag == 1)
        {
            return true;
        }
        return false;

    }

    /**
     * 获取用户的消费次数 <功能详细描述>
     * @param shopId
     * @param userMobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserConsumptionTimes", method = RequestMethod.GET)
    public int getUserConsumptionTimes(int shopId, int userId)
    {
        UserOrderInfoBusinEntity userOrderInfoBusinEntity = new UserOrderInfoBusinEntity();
        userOrderInfoBusinEntity.setUserId(userId);
        userOrderInfoBusinEntity.setShopId(shopId);
        int flag = userOrderInfoSevice.getUserConsumptionTimes(userOrderInfoBusinEntity);
        return flag;

    }

    /**
     * 
     * <功能详细描述>获取商家的消费次数
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopConsumptionTimes", method = RequestMethod.GET)
    public int getShopConsumptionTimes(int shopId)
    {
        return userOrderInfoBusinEntityMapper.getShopConsumptionTimes(shopId);

    }

    /**
     * 根据用户id获取90天内用户余额明细 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getUserBalanceDetailById")
    public List<UserBalanceDetailEntity> getUserBalanceDetailById(Integer userId, Integer begin)
    {
        return userBalanceDetailMapper.selectByUserId(userId, begin);

    }

    /**
     * 存储用户余额明细 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/insertUserBalanceDetail", method = RequestMethod.POST)
    public boolean insertUserBalanceDetail(@RequestBody UserBalanceDetailEntity userBalanceDetail)
    {
        int flag = userBalanceDetailMapper.insertSelective(userBalanceDetail);
        if (1 == flag)
        {
            return true;
        }
        return false;
    }

    /**
     * 根据订单号更新评论状态 <功能详细描述>
     * @param orderNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateCommentStatus", method = RequestMethod.POST)
    public boolean updateCommentStatus(String orderNumber)
    {
        int flag = userOrderInfoBusinEntityMapper.updateCommentStatus(orderNumber);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * 获取7天后未评价订单 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectSevenDayNocommentOrder")
    public List<UserOrderInfoBusinEntity> selectSevenDayNocommentOrder()
    {
        return userOrderInfoBusinEntityMapper.selectSevenDayNocommentOrder();
    }

    /**
     * 获取当前店铺订单数量 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getShopOrderNumber")
    public int getShopOrderNumber(Integer shopId)
    {
        return userOrderInfoBusinEntityMapper.getShopOrderNumber(shopId);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserOrderInfoController.class, errorMsg);
    }

}
