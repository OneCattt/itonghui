package com.fbw.service.business.paybill.context;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbw.service.business.paybill.entity.PayEntity;
import com.fbw.service.business.paybill.entity.PayReturnMoneyEntity;
import com.fbw.service.business.paybill.service.PayService;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.shop.ShopDetailEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.entity.user.UserRedPacketBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.UserMapper;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;
import com.google.common.util.concurrent.AtomicDouble;

/**
 * 
 * <功能详细描述> 抽象买单父类
 * @author JACK HUANG
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractSuperPay
{
    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    ShopFeignClient shopFeignClient;

    @Autowired
    CommonFeignClient commonFeignClient;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GetCacheUtil getCache;

    @Autowired
    PayService payService;

    private final String PAY_ORDER_FORMAT = "yyMMddHHmmss";

    /**
     * 商户每日单数限制类型
     */
    final String LIMIT_EVERYDAY_ORDER_TYPE = "1";

    /**
     * 
     * <功能详细描述> 生成支付订单
     * @param payEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract UserOrderInfoBusinEntity createPayOrder(PayEntity payEntity) throws InnerException;

    /**
     * 
     * <功能详细描述> 生成买单金额
     * @param payEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract PayReturnMoneyEntity createPayMoney(PayEntity payEntity) throws InnerException;

    /**
     * 
     * <功能详细描述>获取实付金额
     * @param shopId 商户ID
     * @param mobiles 用户手机号
     * @param orderFee 订单金额
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    protected PayReturnMoneyEntity getActivityPaid(String shopId, String mobiles, double orderFee, Integer userId)
            throws InnerException
    {
        PayReturnMoneyEntity payReturnMoneyEntity = new PayReturnMoneyEntity();
        // 红包金额
        double redPacketFee = 0;
        int shopIds = Integer.parseInt(shopId);
        HomeReqEntity homeReqEntity = new HomeReqEntity();
        homeReqEntity.setShopId(shopId);
        ShopInfoEntity shopsInfoEntity = shopFeignClient.getShopsInfoByShopId(homeReqEntity);
        // 构造用户红包实体类
        UserRedPacketBusinEntity userRedPacketBusinEntity = new UserRedPacketBusinEntity();
        userRedPacketBusinEntity.setShopId(shopIds);
        userRedPacketBusinEntity.setCityId(shopsInfoEntity.getCityId());
        userRedPacketBusinEntity.setFirstClassId(shopsInfoEntity.getFirstClassId());
        userRedPacketBusinEntity.setSecondClassId(shopsInfoEntity.getSecondClassId());
        userRedPacketBusinEntity.setMinimumAmount(new BigDecimal(String.valueOf(orderFee)));
        userRedPacketBusinEntity.setUserId(userId);

        // 获取获取用户可用的最大红包
        UserRedPacketBusinEntity userRedPacketBusin = userFeignClient.getRedPacketWithBig(userRedPacketBusinEntity);
        if (null != userRedPacketBusin.getAmount())
        {
            redPacketFee = userRedPacketBusin.getAmount().doubleValue();
            // 如果红包金额大于订单金额认为红包不可用
            if (redPacketFee > orderFee)
            {
                payReturnMoneyEntity.setRedPacketFlag(false);
            }
            else
            {
                payReturnMoneyEntity.setRedPacketFlag(true);
                payReturnMoneyEntity.setRedPacketFee(redPacketFee);
            }
        }
        else
        {
            payReturnMoneyEntity.setRedPacketFlag(false);
        }
        // 实付余额=订单金额-红包金额
        payReturnMoneyEntity.setActivityPaid(orderFee - redPacketFee);
        payReturnMoneyEntity.setRedPacketId(userRedPacketBusin.getId() == null ? 0 : userRedPacketBusin.getId());
        payReturnMoneyEntity.setSalesmanId(shopsInfoEntity.getMaintainerId());
        payReturnMoneyEntity.setOrderMoney(orderFee);
        payReturnMoneyEntity.setMobile(mobiles);
        payReturnMoneyEntity.setCityId(shopsInfoEntity.getCityId().toString());
        return payReturnMoneyEntity;
    }

    /**
     * 
     * <功能详细描述> 效验生成买单订单参数
     * @param payEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    protected void checkPayOrderParm(PayEntity payEntity) throws InnerException
    {
        // 效验手机号格式
        NomalUtil.checkMobileFormat(payEntity.getMobile());
        // 效验金额格式
        NomalUtil.checkMoneyFormat(payEntity.getOrderMoney());
        // 效验支付密码
        checkPayPassWord(payEntity);
    }

    /**
     * 
     * <功能详细描述>效验支付密码
     * @param payEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    private void checkPayPassWord(PayEntity payEntity) throws InnerException
    {
        // 获取加密的支付密码
        String encoderPassword = payService.getEncoderPayPassWord(payEntity.getPayPassWord());
        // 检查用户支付密码
        payService.checkUserPassWord(payEntity.getMobile(), encoderPassword);
    }

    /**
     * 
     * <功能详细描述> 转换订单金额(保留小数点2位，不四舍五入)
     * @param orderMoney
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected double conversionsOrderMoney(double orderMoney)
    {

        return Double.parseDouble(NomalUtil.calculateProfit(orderMoney));
    }

    /**
     * 
     * <功能详细描述> 获取节省金额
     * @param orderMoney
     * @param activityPaid
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected double getSaveMoney(double orderMoney, double activityPaid)
    {
        // 节省的金额=订单金额-实付余额/2
        return orderMoney - (activityPaid / 2);

    }

    /**
     * 
     * <功能详细描述> 插入用户订单记录
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    protected UserOrderInfoBusinEntity insertUserOrderRecord(PayEntity payEntity,
            PayReturnMoneyEntity payReturnMoneyEntity) throws InnerException
    {
        UserOrderInfoBusinEntity userOrderInfoBusin = buildUserOrderEntity(payEntity, payReturnMoneyEntity);
        if (userMapper.saveUserOrderInfo(userOrderInfoBusin) <= 0)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_SAVE_ORDER_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_SAVE_ORDER_FAIL));
        }
        return userOrderInfoBusin;
    }

    /**
     * 
     * <功能详细描述> 构造用户订单实体类
     * @param payEntity
     * @param payReturnMoney
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserOrderInfoBusinEntity buildUserOrderEntity(PayEntity payEntity,
            PayReturnMoneyEntity payReturnMoneyEntity) throws InnerException
    {
        int shopId = Integer.parseInt(payEntity.getShopId());
        // 获取详细的商户信息
        HomeReqEntity homeReqEntity = new HomeReqEntity();
        homeReqEntity.setShopId(payEntity.getShopId());
        ShopInfoEntity shopsInfoEntity = shopFeignClient.getShopsInfoByShopId(homeReqEntity);
        UserOrderInfoBusinEntity userOrderInfoBusinEntity = new UserOrderInfoBusinEntity();
        userOrderInfoBusinEntity.setShopId(shopId);
        userOrderInfoBusinEntity.setUserId(Integer.parseInt(payEntity.getUserId()));
        userOrderInfoBusinEntity.setOrderAmount(new BigDecimal(String.valueOf(payReturnMoneyEntity.getOrderMoney())));
        userOrderInfoBusinEntity
                .setActualAmount(new BigDecimal(String.valueOf(payReturnMoneyEntity.getActivityPaid())));
        userOrderInfoBusinEntity.setCityId(shopsInfoEntity.getCityId());
        userOrderInfoBusinEntity.setMaintainerId(shopsInfoEntity.getMaintainerId());
        userOrderInfoBusinEntity
                .setRedEnvelopeAmount(new BigDecimal(String.valueOf(payReturnMoneyEntity.getRedPacketFee())));
        userOrderInfoBusinEntity.setRedEnvelopeId(payReturnMoneyEntity.getRedPacketId());
        userOrderInfoBusinEntity.setOrderNumber(getPayOrderNumber(payEntity.getMobile()));
        userOrderInfoBusinEntity.setDoubleFlowerStatus(payEntity.getPayType());
        userOrderInfoBusinEntity.setStartBalance(payReturnMoneyEntity.getBeforebalance());
        userOrderInfoBusinEntity.setLastBalance(payReturnMoneyEntity.getAfterbalance());
        userOrderInfoBusinEntity.setAddress(shopsInfoEntity.getAddress());
        userOrderInfoBusinEntity.setName(shopsInfoEntity.getName());
        userOrderInfoBusinEntity.setInputAmount(new BigDecimal(payEntity.getOrderMoney()));
        userOrderInfoBusinEntity.setPayType(payEntity.getPayType());
        return userOrderInfoBusinEntity;

    }

    /**
     * 
     * <功能详细描述>获取买单的订单号码
     * @param mobilePhone
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected String getPayOrderNumber(String mobilePhone) throws InnerException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(PAY_ORDER_FORMAT);
        String orderNumber = new StringBuffer().append(sdf.format(new Date())).append(NomalUtil.getRandomString(4))
                .append(mobilePhone.substring(7, 10)).toString();
        return orderNumber;

    }

    /**
     * <功能详细描述>更新用户余额记录
     * @param mobile
     * @param activityPaid
     * @param balance
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public synchronized void updateUserBalanceRecord(String mobile, double activityPaid) throws InnerException
    {
        UserInfoBusinEntity userInfoBusinEntity = userFeignClient.getUserBaseInfo(mobile);
        double balances = userInfoBusinEntity.getBalance().doubleValue();
        // 余额小于订单金额，抛出余额不足的异常
        if (balances < activityPaid)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_PAY_INSUFFICIENT_BALANCE_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_INSUFFICIENT_BALANCE_FAIL));
        }
        AtomicDouble ai = new AtomicDouble(balances);
        if (userMapper.updateNewBalanceByMobileBalance(mobile, new BigDecimal(String.valueOf(balances)),
                new BigDecimal(ai.addAndGet(-activityPaid))) <= 0)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_PAY_BALANCE_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_BALANCE_FAIL));
        }

    }

    /**
     * 
     * <功能详细描述> 构造买单金额信息
     * @param payReturnMoneyEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public PayReturnMoneyEntity buildPayBillMoney(PayReturnMoneyEntity payReturnMoneyEntity) throws InnerException
    {
        UserInfoBusinEntity userInfoBusinEntity = userFeignClient.getUserBaseInfo(payReturnMoneyEntity.getMobile());
        double balances = userInfoBusinEntity.getBalance().doubleValue();
        // 余额小于订单金额，抛出余额不足的异常
        if (balances < payReturnMoneyEntity.getActivityPaid())
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_PAY_INSUFFICIENT_BALANCE_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_PAY_INSUFFICIENT_BALANCE_FAIL));
        }
        AtomicDouble ai = new AtomicDouble(balances);
        payReturnMoneyEntity.setBeforebalance(new BigDecimal(balances));
        payReturnMoneyEntity.setAfterbalance(new BigDecimal(ai.addAndGet(-payReturnMoneyEntity.getActivityPaid())));
        return payReturnMoneyEntity;

    }

    /**
     * 
     * <功能详细描述> 更新用户红包状态
     * @param redPacketId
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void updateUserRedPacketStatus(PayReturnMoneyEntity payReturnMoneyEntity) throws InnerException
    {
        // 如果有红包就更新红包状态
        if (payReturnMoneyEntity.isRedPacketFlag()
                && (userMapper.updateRedPacketStatus(payReturnMoneyEntity.getRedPacketId()) <= 0))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_UPDATE_RED_PACKET_STATUS_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_UPDATE_RED_PACKET_STATUS_FAIL));
        }

    }

    /**
     * 
     * <功能详细描述> 效验买单资格
     * @param payEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void checkPayQuality(PayEntity payEntity) throws InnerException
    {
        int shopId = Integer.parseInt(payEntity.getShopId());
        HomeReqEntity homeReqEntity = new HomeReqEntity();
        homeReqEntity.setShopId(payEntity.getShopId());
        ShopDetailEntity shopsDetailEntity = shopFeignClient.getShopsDetailByShopid(homeReqEntity);
        int userCustomerTimes = userFeignClient.getUserConsumptionTimes(shopId,
                Integer.parseInt(payEntity.getUserId()));
        if (NomalUtil.isNullOrEmpty(shopsDetailEntity))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_HOME_SHOPINFO_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_HOME_SHOPINFO_FAIL));
        }
        BigDecimal maxSale = shopsDetailEntity.getMaxSales();
        // 判断用户的消费金额是否大于商户的最大封顶金额
        if (Double.parseDouble(payEntity.getOrderMoney()) >= maxSale.doubleValue())
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_EXCEED_CUSTOMER_MONEY_LIMIT_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_EXCEED_CUSTOMER_MONEY_LIMIT_FAIL));
        }
        // 判断用户的消费次数是否大于商户的最大消费次数
        if (userCustomerTimes >= shopsDetailEntity.getBuyNumber())
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_EXCEED_CUSTOMER_TIMES_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_EXCEED_CUSTOMER_TIMES_FAIL));
        }

    }

}
