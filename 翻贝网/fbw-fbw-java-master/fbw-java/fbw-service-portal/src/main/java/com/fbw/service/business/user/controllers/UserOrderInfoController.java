package com.fbw.service.business.user.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.user.service.UserCommentService;
import com.fbw.service.business.user.service.UserOrderInfoService;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.NomalUtil;
import com.fbw.service.util.ReqUtils;

@RestController
public class UserOrderInfoController extends BaseController
{
    @Autowired
    private UserOrderInfoService userOrderInfoService;

    @Autowired
    private UserCommentService userCommentService;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取当前用户所有订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userOrder/getUserOrderList", method = RequestMethod.GET)
    public Map<String, Object> getUserOrderList(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int begin = ReqUtils.getInt(request, "begin") * 20;
        List<UserOrderInfoBusinEntity> userOrderInfo = null;
        try
        {
            userOrderInfo = userOrderInfoService.getUserOrderList(userId, begin);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user getUserOrderList:" + "获取订单列表失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_ORDER_LIST_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userOrderInfo", userOrderInfo);
        return successData(data);

    }

    /**
     * 获取当前用户所有未评价订单列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userOrder/getUserNoCommOrderList", method = RequestMethod.GET)
    public Map<String, Object> getUserNoCommOrderList(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int begin = ReqUtils.getInt(request, "begin") * 20;
        List<UserOrderInfoBusinEntity> userOrderInfo = new ArrayList<UserOrderInfoBusinEntity>();
        try
        {
            userOrderInfo = userOrderInfoService.getUserNoCommOrderList(userId, begin);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user getUserNoCommOrderList:" + "获取未评价订单列表失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_NOCOMMENT_ORDER_LIST_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userOrderInfo", userOrderInfo);
        return successData(data);

    }

    /**
     * 获取某一订单详细信息 <功能详细描述>
     * @param orderId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userOrder/getUserOrderInfo", method = RequestMethod.GET)
    public Map<String, Object> getUserOrderInfo(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        String orderNumber = request.getParameter("orderNumber");
        Map<String, Object> data = new HashMap<String, Object>();
        UserOrderInfoBusinEntity userOrderInfo = null;

        try
        {
            UserOrderInfoBusinEntity userOrder = userFeignClient.getUserOrderCommentAndRefund(orderNumber);

            if (!NomalUtil.isNullOrEmpty(userOrder))
            {
                userOrderInfo = userOrderInfoService.getUserOrderInfo(orderNumber,
                        String.valueOf(userOrder.getRefundStatus()));
                if (1 == userOrder.getCommentStatus() && 0 == userOrder.getRefundStatus())
                {
                    UserCommentBusinEntity userComment = userCommentService.getOneUserCommentInfo(orderNumber);
                    data.put("userComment", userComment);
                }
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":user getUserOrderInfo:" + "获取订单详情失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_ORDER_DETAIL_ERROR);
        }
        data.put("userOrderInfo", userOrderInfo);
        return successData(data);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserOrderInfoController.class, errorMsg);

    }

}
