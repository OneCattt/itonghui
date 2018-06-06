package com.fbw.service.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fbw.service.base.BaseService;
import com.fbw.service.contents.AliPayRechargeConstant;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.contents.RechargeConstant;
import com.fbw.service.dao.RechargeDao;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;

/**
 * 
 * <功能详细描述> 支付宝通知接口
 * @author JACK HUANG
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class AliPayAppNotifyService extends BaseService
{

    @Autowired
    RechargeDao rechargeDao;

    /**
     * 
     * <功能详细描述> 效验支付宝异步回调参数
     * @param notify
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkAliPayAsynCallBackReq(Map<String, String> notify) throws InnerException
    {
        // 效验阿里回调参数
        checkAliPayCallBackParm(notify);
        // 效验签名
        if (!checkSign(notify))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_ALIPAY_SIGN_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_ALIPAY_SIGN_ERROR));
        }
    }

    /**
     * 
     * <功能详细描述> 效验阿里回调参数
     * @param notify
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void checkAliPayCallBackParm(Map<String, String> notify) throws InnerException
    {
        if (!(AliPayRechargeConstant.RECHARGE_ALIPAY_APP_ID.equals(notify.get("app_id"))
                && AliPayRechargeConstant.RECHARGE_ALIPAY_SELLER_ID.equals(notify.get("seller_id"))
                && "TRADE_SUCCESS".equals(notify.get("trade_status"))))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_CALL_BACK_PARM_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_CALL_BACK_PARM_ERROR));
        }
    }

    /**
     * 
     * <功能详细描述> 效验签名
     * @param notify
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private boolean checkSign(Map<String, String> alipayNotify) throws InnerException
    {
        // 删除充值请求报文参数
        alipayNotify.remove(RechargeConstant.RECHARGE_LOG_REQUEST_MSG);
        try
        {
            // 调用支付宝SDK进行效验
            return AlipaySignature.rsaCheckV1(alipayNotify, AliPayRechargeConstant.RECHARGE_ALIPAY_PUBLIC_KEY, "UTF-8");
        }
        catch (AlipayApiException e)
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_ALIPAY_SIGN_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_ALIPAY_SIGN_ERROR));
        }

    }

    /**
     * 
     * <功能详细描述> 支付宝回调业务处理
     * @param notify
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void CallBackHandle(RechargeEntity rechargeEntity) throws InnerException
    {
        // 更新充值业务
        if (!updateRechargeBusin(rechargeEntity))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_ALIPAY_UPDATE_ORDER_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_ALIPAY_UPDATE_ORDER_ERROR));
        }
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
