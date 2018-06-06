package com.fbw.service.business.recharge.context;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbw.service.business.recharge.entity.RechargeReqEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.RechargeFeignClient;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 充值抽象父类
 * @author JACK HUANG
 * @version [版本号, 2017年8月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractSuperRecharge
{
    @Autowired
    RechargeFeignClient rechargeFeignClient;

    @Autowired
    CommonFeignClient commonFeignClient;

    /**
     * 微信订单生成格式
     */
    private final String WECHAT_ORDER_FORMAT = "yyyyMMddHHmmss";

    /**
     * 支付宝订单生成格式
     */
    private final String ALIPAY_ORDER_FORMAT = "yyMMddHHmmss";

    private final String ALIPAY_TYPE = "1";

    /**
     * 未支付订单状态
     */
    private final String UNPAID_ORDER_STATUS = "1";

    /**
     * 
     * <功能详细描述>发送充值支付请求
     * @param rechargePayEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract Map<String, Object> sendRechargePayRequest(RechargeReqEntity rechargeReqEntity)
            throws InnerException;

    /**
     * <功能详细描述>生成充值订单
     * @param rechargeEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void createRechargeOrder(RechargeEntity rechargeEntity) throws InnerException
    {
        if (!rechargeFeignClient.insertRechargeOrderRecord(rechargeEntity))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_CREATE_RECHARGE_ORDER_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_CREATE_RECHARGE_ORDER_FAIL));
        }

    }

    public static void checkParm(String mobilePhone, String money) throws InnerException
    {
        // 手机号格式效验
        if (!NomalUtil.isMobileNo(mobilePhone))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_MOBILE_FORMAT_FAIL_MSG,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_MOBILE_FORMAT_FAIL_MSG));
        }
        // 金额格式效验
        if (!NomalUtil.isMoneyFormat(money))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_MONEY_FORMAT_ERROR,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_MONEY_FORMAT_ERROR));
        }

    }

    /**
     * 
     * <功能详细描述>获取微信订单号码
     * @param rechargeNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getWeChatOrderNum(String rechargeNumber)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(WECHAT_ORDER_FORMAT);
        return sdf.format(new Date()) + rechargeNumber.substring(5, 11);

    }

    /**
     * 
     * <功能详细描述>获取支付宝订单号码
     * @param rechargeNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getAliPayOrderNum(String rechargeNumber)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(ALIPAY_ORDER_FORMAT);
        String orderNumber = new StringBuffer().append(ALIPAY_TYPE).append(sdf.format(new Date()))
                .append(NomalUtil.getRandomString(4)).append(rechargeNumber.substring(8, 11)).toString();
        return orderNumber;

    }

    /**
     * 
     * <功能详细描述> 构造充值订单实体类
     * @param mobilePhone
     * @param rechargeMoney
     * @param payType
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public RechargeEntity buildRechargeEntity(String mobilePhone, String rechargeMoney, String cityId)
            throws InnerException
    {

        // 效验充值支付的参数
        checkParm(mobilePhone, rechargeMoney);
        double rechargeMoneys = Double.parseDouble(rechargeMoney);
        double serviceFee = rechargeMoneys * 0.08;
        String doubleMoney = String.valueOf(rechargeMoneys * 2);
        RechargeEntity rechargeEntity = new RechargeEntity();
        rechargeEntity.setActualMoney(new BigDecimal(String.valueOf(serviceFee + rechargeMoneys)));
        rechargeEntity.setMobilePhone(mobilePhone);
        rechargeEntity.setStatus(UNPAID_ORDER_STATUS);
        rechargeEntity.setServiceFee(new BigDecimal(String.valueOf(serviceFee)));
        rechargeEntity.setMoney(new BigDecimal(rechargeMoneys));
        rechargeEntity.setDoubleMoney(doubleMoney);
        rechargeEntity.setCityId(cityId);
        return rechargeEntity;

    }
}
