package com.fbw.service.business.paybill.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.paybill.entity.PayEntity;
import com.fbw.service.business.paybill.entity.PayReturnMoneyEntity;
import com.fbw.service.business.paybill.service.AllDayPayService;
import com.fbw.service.business.paybill.service.HalfPayService;
import com.fbw.service.business.paybill.service.PayService;
import com.fbw.service.business.paybill.service.ThirdPayService;
import com.fbw.service.business.paybill.service.TimeLimitPayService;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 买单controller层
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class PayController extends BaseController
{

    @Autowired
    AllDayPayService allDayPayService;

    @Autowired
    HalfPayService halfPayService;

    @Autowired
    ThirdPayService thirdPayService;

    @Autowired
    TimeLimitPayService timeLimitPayService;

    @Autowired
    PayService payService;

    /**
     * 
     * <功能详细描述> 买单
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/payBill", method = RequestMethod.POST)
    public Map<String, Object> payBill(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        String trackId = request.getAttribute("trackId").toString();
        Map<String, Object> data = new HashMap<String, Object>();
        UserOrderInfoBusinEntity userOrderInfoBusinEntity = new UserOrderInfoBusinEntity();
        try
        {
            // 构造买单请求实体类
            PayEntity payEntity = buildPayRequestEntity(request, trackId);
            getErrorLog(trackId + ":payBill input:" + payEntity.toString());
            // 选择支付方式
            userOrderInfoBusinEntity = switchPayWay(payEntity);
            // 插入地推订单信息
            payService.insertOrderWithGround(userOrderInfoBusinEntity);
            // 插入余额明细信息
            payService.insertBalanceDetail(userOrderInfoBusinEntity);
            // 插入用户积分信息
            payService.insertUserScoreInfo(userOrderInfoBusinEntity);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":payBill error:" + e.getMessage());
            return failedMessage(e.getErrorCode());
        }
        // 构造买单返回参数
        data.put("payBillReturnInfo", PayBillReturnParm(userOrderInfoBusinEntity));
        return successData(data);
    }

    /**
     * 
     * <功能详细描述> 获取买单金额
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/getPayBillMoney", method = RequestMethod.POST)
    public Map<String, Object> getPayBillMoney(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        String trackId = request.getAttribute("trackId").toString();
        Map<String, Object> data = new HashMap<String, Object>();
        PayReturnMoneyEntity payReturnMoneyEntity = new PayReturnMoneyEntity();
        try
        { // 构造买单请求实体类
            PayEntity payEntity = buildPayRequestEntity(request, trackId);
            getErrorLog(trackId + ":getPayBillMoney input:" + payEntity.toString());
            // 更新翻倍日
            payService.updateDoubleDay(payEntity);
            // 切换买单金额
            payReturnMoneyEntity = switchPayMoney(payEntity);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":getPayBillMoney error:" + e.getMessage());
            return failedMessage(e.getErrorCode());
        }
        data.put("payBillMoneyInfo", buildReturnPayBillMoneyParm(payReturnMoneyEntity));
        return successData(data);

    }

    /**
     * 
     * <功能详细描述>效验支付密码
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/checkPayPassWord", method = RequestMethod.POST)
    public Map<String, Object> checkPayPassWord(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        String trackId = request.getAttribute("trackId").toString();
        Map<String, Object> data = new HashMap<String, Object>();
        String password = request.getParameter("payPassWord");
        String mobile = request.getParameter("mobile");
        try
        { // 获取加密的支付密码
            String encoderPassword = payService.getEncoderPayPassWord(password);
            // 检查用户支付密码
            payService.checkUserPassWord(mobile, encoderPassword);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":getPayBillMoney error:" + e.getMessage());
            return failedMessage(e.getErrorCode());
        }
        return successData(data);

    }

    /**
     * 
     * <功能详细描述> 构造买单请求实体类
     * @param request
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private PayEntity buildPayRequestEntity(HttpServletRequest request, String trackId) throws InnerException
    {
        PayEntity payEntity = new PayEntity();
        // 构造请求实体类
        NomalUtil.buildRequestEntity(payEntity, request);
        if (NomalUtil.isNullOrEmpty(payEntity.getMobile()))
        {
            payEntity.setMobile(request.getAttribute("mobile").toString());
            payEntity.setUserId(request.getAttribute("userId").toString());
        }
        // 构造密码实体类
        payEntity.setTrackId(trackId);
        return payEntity;
    }

    /**
     * 
     * <功能详细描述> 选择支付方式
     * @param payEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private UserOrderInfoBusinEntity switchPayWay(PayEntity payEntity) throws InnerException
    {
        String payType = payEntity.getPayType();
        // 效验买单状态
        payService.checkPayBillStatus(payEntity);
        if ("1".equals(payType))
        {
            // 全天翻倍花策略
            return allDayPayService.createPayOrder(payEntity);
        }
        else if ("2".equals(payType))
        {
            // 1/2翻倍花策略
            return halfPayService.createPayOrder(payEntity);
        }
        else if ("3".equals(payType))
        {
            // 1/3翻倍花策略
            return thirdPayService.createPayOrder(payEntity);
        }
        else if ("0".equals(payType))
        {
            // 限时翻倍花策略
            return timeLimitPayService.createPayOrder(payEntity);
        }
        else
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_PAY_BILL_WAY_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_BILL_WAY_FAIL));
        }
    }

    /**
     * 
     * <功能详细描述> 切换买单金额
     * @param payEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private PayReturnMoneyEntity switchPayMoney(PayEntity payEntity) throws InnerException
    {
        String payType = payEntity.getPayType();
        // 效验买单状态
        payService.checkPayBillStatus(payEntity);
        if ("1".equals(payType))
        {
            // 全天翻倍花策略
            return allDayPayService.createPayMoney(payEntity);
        }
        else if ("2".equals(payType))
        {
            // 1/2翻倍花策略
            return halfPayService.createPayMoney(payEntity);
        }
        else if ("3".equals(payType))
        {
            // 1/3翻倍花策略
            return thirdPayService.createPayMoney(payEntity);
        }
        else if ("4".equals(payType))
        {
            // 限时翻倍花策略
            return timeLimitPayService.createPayMoney(payEntity);
        }
        else
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_PAY_BILL_WAY_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_BILL_WAY_FAIL));
        }

    }

    /**
     * 
     * <功能详细描述> 构造买单返回参数
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserOrderInfoBusinEntity PayBillReturnParm(UserOrderInfoBusinEntity userOrderInfoBusinEntity)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 去掉无用参数
        userOrderInfoBusinEntity.setId(null);
        userOrderInfoBusinEntity.setStartBalance(null);
        userOrderInfoBusinEntity.setLastBalance(null);
        userOrderInfoBusinEntity.setPayDate(sdf.format(new Date()));
        return userOrderInfoBusinEntity;
    }

    /**
     * 
     * <功能详细描述>构造买单金额返回参数
     * @param payReturnMoneyEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private PayReturnMoneyEntity buildReturnPayBillMoneyParm(PayReturnMoneyEntity payReturnMoneyEntity)
    {
        payReturnMoneyEntity.setBeforebalance(null);
        payReturnMoneyEntity.setAfterbalance(null);
        return payReturnMoneyEntity;

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(PayController.class, errorMsg);
    }

}
