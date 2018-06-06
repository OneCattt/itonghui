package com.fbw.service.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.base.BaseService;
import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.contents.WeChatRechargeConstant;
import com.fbw.service.dao.RechargeDao;
import com.fbw.service.entity.RechargeReqEntity;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.RechargeMapper;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 微信通知接口
 * @author JACK HUANG
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class WeChatAppNotifyService extends BaseService
{

    /**
     * 微信请求验签
     */
    private final String WECHAT_RES_SIGN = "sign";

    @Autowired
    RechargeMapper rechargeMapper;

    @Autowired
    RechargeDao rechargeDao;

    /**
     * 
     * <功能详细描述> 效验微信异步回调请求
     * @param notify
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkWeChatAsynCallBackReq(Map<String, Object> notify) throws InnerException
    {
        // 效验回调参数
        checkWeChatCallBackParm(notify);
        // 效验微信签名
        checkSign(notify, getSignParm(notify));
    }

    /**
     * 
     * <功能详细描述> 获取签名参数
     * @param notify
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private String getSignParm(Map<String, Object> notify) throws InnerException
    {
        // 判断是否为空
        if (!NomalUtil.isNullOrEmpty(notify.get(WECHAT_RES_SIGN)))
        {
            return notify.get(WECHAT_RES_SIGN).toString();
        }
        else
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_WECHAT_REQ_PARM_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_WECHAT_REQ_PARM_ERROR));
        }

    }

    /**
     * 
     * <功能详细描述> 效验微信签名
     * @param original 原始报文
     * @param expect 期望报文
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void checkSign(Map<String, Object> notify, String sign) throws InnerException
    {
        System.out.println("sign:" + sign);
        // 移除签名属性
        notify.remove(WECHAT_RES_SIGN);
        // 效验签名
        if (!getWeChatSign(notify).equals(sign))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_WECHAT_REQ_SIGN_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_WECHAT_REQ_SIGN_ERROR));
        }

    }

    /**
     * 
     * <功能详细描述>效验回调参数
     * @param notify
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void checkWeChatCallBackParm(Map<String, Object> notify) throws InnerException
    {
        String returnCode = (notify.get("return_code") == null ? "fail" : notify.get("return_code")).toString();
        String resultCode = (notify.get("result_code") == null ? "fail" : notify.get("result_code")).toString();
        String appId = (notify.get("appid") == null ? "fail" : notify.get("appid")).toString();
        String mchId = (notify.get("mch_id") == null ? "fail" : notify.get("mch_id")).toString();
        if (!("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)
                && WeChatRechargeConstant.RECHARGE_WECHAT_APPID.equals(appId)
                && WeChatRechargeConstant.RECHARGE_WECHAT_MCHID.equals(mchId)))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_CALL_BACK_PARM_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_CALL_BACK_PARM_ERROR));
        }

    }

    /**
     * <功能详细描述>效验充值订单信息
     * @param reqXml
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkRechargeOrderInfo(RechargeReqEntity rechargeReqEntity) throws InnerException
    {
        // 效验充值订单状态
        rechargeDao.checkRechargeOrderStatus(rechargeReqEntity.getOrderNum(), rechargeReqEntity.getActualMoney());
    }

    /**
     * <功能详细描述>微信回调业务处理
     * @param notify 微信返回的报文
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void CallBackHandle(RechargeEntity rechargeEntity) throws InnerException
    {
        // 更新充值业务
        if (!updateRechargeBusin(rechargeEntity))
        {
            throw new InnerException(ErrorMsgConstant.RECHARGE_DB_UPDATE_ORDER_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.RECHARGE_DB_UPDATE_ORDER_ERROR));
        }
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
