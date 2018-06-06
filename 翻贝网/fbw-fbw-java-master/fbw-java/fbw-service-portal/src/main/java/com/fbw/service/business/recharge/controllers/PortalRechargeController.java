package com.fbw.service.business.recharge.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.recharge.entity.RechargeReqEntity;
import com.fbw.service.business.recharge.service.PortalAliPayRechargeService;
import com.fbw.service.business.recharge.service.PortalWeChatRechargeService;
import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.RechargeFeignClient;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 充值控制层
 * @author JACK HUANG
 * @version [版本号, 2017年8月23日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class PortalRechargeController extends BaseController
{
    @Autowired
    RechargeFeignClient rechargeFeignClient;

    @Autowired
    CommonFeignClient commonFeignClient;

    @Autowired
    PortalWeChatRechargeService weChatRechargeService;

    @Autowired
    PortalAliPayRechargeService aliPayRechargeService;

    @Autowired
    GetCacheUtil cacheUtil;

    /**
     * <功能详细描述>发送微信支付请求
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/recharge", method = RequestMethod.POST)
    public Map<String, Object> recharge(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        String trackId = request.getAttribute("trackId").toString();
        Map<String, Object> data = new HashMap<String, Object>();
        // 构造充值请求实体类
        RechargeReqEntity rechargeReqEntity = buildRechargeReqEntity(request, trackId);
        try
        {
            // 微信充值
            if ("weChat".equals(rechargeReqEntity.getRechargeType()))
            {
                data = weChatRechargeService.sendRechargePayRequest(rechargeReqEntity);
            }
            // 支付宝充值
            else if ("aliPay".equals(rechargeReqEntity.getRechargeType()))
            {
                data = aliPayRechargeService.sendRechargePayRequest(rechargeReqEntity);
            }
            else
            {
                throw new InnerException(ErrorMsgConstant.PORTAL_PAY_TYPE_FAIL,
                        InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_TYPE_FAIL));
            }
        }
        catch (InnerException e)
        {
            return failedMessage(e.getErrorCode());
        }
        return successData(data);

    }

    /**
     * 
     * <功能详细描述> 构造充值请求实体类
     * @param request
     * @param trackId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public RechargeReqEntity buildRechargeReqEntity(HttpServletRequest request, String trackId)
    {
        RechargeReqEntity rechargeReqEntity = new RechargeReqEntity();
        // 构造请求实体类
        NomalUtil.buildRequestEntity(rechargeReqEntity, request);
        if (NomalUtil.isNullOrEmpty(rechargeReqEntity.getMobile()))
        {
            rechargeReqEntity.setMobile(request.getAttribute("mobile").toString());
            rechargeReqEntity.setUserId(request.getAttribute("userId").toString());
        }
        rechargeReqEntity.setRequestIp(getIpAddress(request));
        rechargeReqEntity.setTrackId(trackId);
        return rechargeReqEntity;
    }

    /**
     * 
     * <功能详细描述> 获取支付列表
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/getPayList")
    public Map<String, Object> getPayList(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("payList", cacheUtil.getDictNumList(CacheKeyConstant.PORTAL_RECHARGE_PAY_LIST_KEY));
        return successData(data);
    }

    /**
     * 
     * <功能详细描述> 获取充值记录
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/getRechargeRecord")
    public Map<String, Object> getRechargeRecord(HttpServletRequest request, HttpServletResponse response)
    {
        setFisrtRequestFlag(response);
        Map<String, Object> data = new HashMap<String, Object>();

        RechargeEntity rechargeEntity = rechargeFeignClient
                .getRechargeRecordByOrderNum(request.getParameter("orderNum").toString());
        if (NomalUtil.isNullOrEmpty(rechargeEntity))
        {
            return failedMessage(ErrorMsgConstant.PORTAL_GET_RECHARGE_RECORD_FAIL);
        }
        data.put("rechargeRecoreList", rechargeEntity);
        return successData(data);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
