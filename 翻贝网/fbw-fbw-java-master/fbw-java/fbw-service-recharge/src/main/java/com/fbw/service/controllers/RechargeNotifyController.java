package com.fbw.service.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.contents.RechargeConstant;
import com.fbw.service.dao.RechargeDao;
import com.fbw.service.entity.RechargeReqEntity;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.entity.recharge.RechargeLogEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.service.AliPayAppNotifyService;
import com.fbw.service.service.RechargeService;
import com.fbw.service.service.WeChatAppNotifyService;
import com.fbw.service.util.HttpRequestUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 支付回调controller层
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "/recharge")
public class RechargeNotifyController extends BaseController
{
    @Autowired
    WeChatAppNotifyService weChatNotifyService;

    @Autowired
    AliPayAppNotifyService aliPayNotifyService;

    @Autowired
    RechargeDao rechargeDao;

    @Autowired
    RechargeService rechargeService;

    // 支付通知商家的替换报文
    final String returnXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
            + "<return_msg><![CDATA[failure]]></return_msg>" + "</xml> ";

    /**
     * 
     * <功能详细描述>APP版微信异步回调通知
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/NotifyAppWechatPay", method = RequestMethod.POST)
    public void NotifyAppWechatPay(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> notify = new HashMap<String, Object>();
        // 默认通知微信支付成功
        String notifyXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml>";
        // 获取微信异步通知Map类型报文
        notify = getWeChatNotifyMsg(request);
        // 构造微信充值日志实体类
        RechargeLogEntity rechargeLogEntity = buildWeChatLogMap(notify);
        // 构造微信充值请求参数
        RechargeReqEntity rechargeReqEntity = buildWeChatRechargeReq(notify);
        try
        {
            // 效验充值订单信息
            checkRechargeOrderInfo(rechargeReqEntity);
            // 效验微信异步回调请求
            weChatNotifyService.checkWeChatAsynCallBackReq(notify);
            // 微信回调业务处理
            weChatNotifyService.CallBackHandle(buildRechargeEntity(rechargeReqEntity));
            // 插入充值地推信息
            insertRechargeWithGround(rechargeReqEntity);
            // 插入余额详情信息
            insertBalanceDetail(rechargeReqEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // 如果报错直接替换错误原因
            notifyXml = returnXml;
            rechargeLogEntity.setRechargeStatus("fail");
            rechargeLogEntity.setErrorMsg(e.getMessage() == null ? "null point" : e.getMessage());
        }
        finally
        {
            // 保存充值日志
            saveRechargeLog(rechargeLogEntity);
            try
            {
                // 商家通知微信支付结果
                HttpRequestUtil.notifyWeChatPayResult(notifyXml, response);
            }
            catch (InnerException e)
            {
                e.printStackTrace();
            }
        }

    }

    /**
     * 
     * <功能详细描述>APP版支付宝异步回调通知
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/NotifyAppAliPay", method = RequestMethod.POST)
    public void NotifyAppAiPay(HttpServletRequest request, HttpServletResponse response)
    {
        String notifyXml = "success";
        Map<String, String> notify = new HashMap<String, String>();
        // 将异步通知中收到的待验证所有参数都存放到map中
        notify = getAliPayReqMsg(request);
        // 构造支付宝日志实体类
        RechargeLogEntity rechargeLogEntity = buildAliPayLogMap(notify);
        // 构造阿里充值请求实体类
        RechargeReqEntity rechargeReqEntity = buildAliPayRechargeReq(notify);
        try
        {
            // 效验充值订单信息
            checkRechargeOrderInfo(rechargeReqEntity);
            // 效验支付宝异步回调参数
            aliPayNotifyService.checkAliPayAsynCallBackReq(notify);
            // 支付宝回调业务处理
            aliPayNotifyService.CallBackHandle(buildRechargeEntity(rechargeReqEntity));
            // 插入充值地推信息
            insertRechargeWithGround(rechargeReqEntity);
        }
        catch (InnerException e)
        {
            // 如果异常直接返回失败,通知支付宝
            notifyXml = "failure";
            rechargeLogEntity.setRechargeStatus("fail");
            rechargeLogEntity.setErrorMsg(e.getMessage() == null ? "null point" : e.getMessage());
        }
        finally
        {
            // 保存充值日志
            saveRechargeLog(rechargeLogEntity);
            try
            {
                HttpRequestUtil.notifyWeChatPayResult(notifyXml, response);
            }
            catch (InnerException e)
            {
                // TODO record log
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     * <功能详细描述> 构造微信充值请求参数
     * @param notify
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private RechargeReqEntity buildWeChatRechargeReq(Map<String, Object> notify)
    {
        RechargeReqEntity rechargeReqEntity = new RechargeReqEntity();
        String orderNum = (notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER) == null ? ""
                : notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER)).toString();
        try
        {
            // 获取翻倍金额
            RechargeEntity rechargeEntity = rechargeDao.selectRechargeRecordByOrderNum(orderNum);
            rechargeReqEntity.setDoubleMoney(rechargeEntity.getDoubleMoney());
        }
        catch (InnerException e)
        {
            e.printStackTrace();
        }

        rechargeReqEntity.setOrderNum(orderNum);
        rechargeReqEntity.setActualMoney(new BigDecimal(
                String.valueOf(Double.parseDouble((notify.get(RechargeConstant.RECHARGE_LOG_TOTAL_MONEY) == null ? "0"
                        : notify.get(RechargeConstant.RECHARGE_LOG_TOTAL_MONEY)).toString()) / 100)));
        rechargeReqEntity.setMobile((notify.get("attach") == null ? "" : notify.get("attach")).toString());
        return rechargeReqEntity;
    }

    /**
     * 
     * <功能详细描述> 构造阿里充值请求参数
     * @param notify
     * @return
     * @see [类、类#方法、类#成员]
     */
    private RechargeReqEntity buildAliPayRechargeReq(Map<String, String> notify)
    {
        RechargeReqEntity rechargeReqEntity = new RechargeReqEntity();
        String orderNum = notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER);
        try
        {
            // 获取翻倍金额
            RechargeEntity rechargeEntity = rechargeDao.selectRechargeRecordByOrderNum(orderNum);
            rechargeReqEntity.setDoubleMoney(rechargeEntity.getDoubleMoney());
        }
        catch (InnerException e)
        {
            e.printStackTrace();
        }
        rechargeReqEntity.setOrderNum(notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER));
        rechargeReqEntity.setActualMoney(new BigDecimal(notify.get("total_amount")));
        rechargeReqEntity.setMobile(notify.get("passback_params"));
        return rechargeReqEntity;
    }

    /**
     * 
     * <功能详细描述> 构造支付宝日志实体类
     * @param notify
     * @return
     * @see [类、类#方法、类#成员]
     */
    private RechargeLogEntity buildAliPayLogMap(Map<String, String> notify)
    {
        RechargeLogEntity rechargeLogEntity = new RechargeLogEntity();
        rechargeLogEntity.setRequestMsg(notify.toString());
        rechargeLogEntity.setMobilePhone(notify.get("passback_params") == null ? "" : notify.get("passback_params"));
        rechargeLogEntity.setOrderNum(notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER) == null ? ""
                : notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER));
        rechargeLogEntity
                .setMoney(new BigDecimal(notify.get("total_amount") == null ? "0" : notify.get("total_amount")));
        rechargeLogEntity.setSource("aliPay");
        rechargeLogEntity.setRechargeStatus("success");
        return rechargeLogEntity;
    }

    /**
     * 
     * <功能详细描述> 构造微信充值日志实体类
     * @param notify
     * @return
     * @see [类、类#方法、类#成员]
     */
    private RechargeLogEntity buildWeChatLogMap(Map<String, Object> notify)
    {
        RechargeLogEntity rechargeLogEntity = new RechargeLogEntity();
        rechargeLogEntity.setRequestMsg(notify.toString());
        rechargeLogEntity.setMobilePhone((notify.get("attach") == null ? "" : notify.get("attach")).toString());
        rechargeLogEntity.setOrderNum((notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER) == null ? ""
                : notify.get(RechargeConstant.RECHARGE_LOG_ORDER_NUMBER)).toString());
        rechargeLogEntity.setMoney(new BigDecimal((notify.get(RechargeConstant.RECHARGE_LOG_TOTAL_MONEY) == null ? "0"
                : notify.get(RechargeConstant.RECHARGE_LOG_TOTAL_MONEY)).toString()));
        rechargeLogEntity.setSource("WeChat");
        rechargeLogEntity.setRechargeStatus("success");
        return rechargeLogEntity;

    }

    /**
     * 
     * <功能详细描述> 保存充值日志
     * @param notify
     * @see [类、类#方法、类#成员]
     */
    private void saveRechargeLog(RechargeLogEntity rechargeLogEntity)
    {
        // 保存充值日志记录
        rechargeService.saveRechargeLog(rechargeLogEntity);
    }

    /**
     * 
     * <功能详细描述> 获取微信异步通知Map类型报文
     * @param request
     * @return
     * @throws InnerException
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    private Map<String, Object> getWeChatNotifyMsg(HttpServletRequest request)
    {
        try
        {
            // // 获取微信发送的xml报文
            String xml = HttpRequestUtil.inputStreamToString(request.getInputStream());
            // xml格式报文转Map
            return NomalUtil.getMapFromXML(xml);
        }
        catch (InnerException | IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
