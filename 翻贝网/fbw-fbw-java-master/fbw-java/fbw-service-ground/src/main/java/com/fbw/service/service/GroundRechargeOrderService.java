package com.fbw.service.service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundOrderDetailBusinEntity;
import com.fbw.service.entity.ground.GroundPersonPlanHistoryConfEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundRegisterUserDetailBusinEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.mapper.GroundOrderDetailBusinEntityMapper;
import com.fbw.service.mapper.GroundPersonPlanHistoryConfEntityMapper;
import com.fbw.service.mapper.GroundPersonalInfoBusinMapper;
import com.fbw.service.mapper.GroundRegisterUserDetailBusinEntityMapper;
import com.fbw.service.mapper.GroundShopInfoBusinEntityMapper;

/**
 * 地推用户充值 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class GroundRechargeOrderService
{
    @Autowired
    private GroundRegisterUserDetailBusinEntityMapper groundRegisterUserDetailBusinEntityMapper;

    @Autowired
    private GroundPersonalInfoBusinMapper groundPersonalInfoBusinMapper;

    @Autowired
    private GroundCommissionService groundCommissionService;

    @Autowired
    private GroundShopInfoBusinEntityMapper groundShopInfoBusinEntityMapper;

    @Autowired
    private GroundPersonPlanHistoryConfEntityMapper groundPersonPlanHistoryConfEntityMapper;

    @Autowired
    private GroundOrderDetailBusinEntityMapper groundOrderDetailBusinEntityMapper;

    @Autowired
    private ShopFeignClient shopFeignClient;

    @Autowired
    private GroundPersonPlanHistoryConfService groundPersonPlanHistoryConfService;

    /**
     * 更新商户 有效订单 <功能详细描述>
     * @param shopId
     * @param cityId
     * @param mobile
     * @param money
     * @param orderNumber
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void updateShopGroundVaildOrderInfo(RegisterEntity registerEntity) throws InnerException
    {
        // 根据shopId查询该地推商户基本信息
        GroundShopInfoBusinEntity groundShopInfoBusinEntity = groundShopInfoBusinEntityMapper
                .queryShopGroundByShopId(Integer.parseInt(registerEntity.getShopId()));
        if (null == groundShopInfoBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_SELECT_FAIL, "通过手机号获取地推方案id失败");
        }
        if (!groundShopInfoBusinEntity.getStatus().equals("1"))
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_STATUS_FAIL, "该地推商户已失效");
        }
        CommissionResEntity commissionResEntity = groundCommissionService.getCommission(registerEntity.getMoney(),
                registerEntity.getMobile());
        if (null == commissionResEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_GET_COMMISSION_FAIL, "GroundRechargeOrderService:获取佣金失败");
        }
        if (commissionResEntity.isVaildOrderFlag() == false)
        {
            updateValidRegisterUserOrder(registerEntity);
            return;
        }
        int validAmount = groundShopInfoBusinEntity.getVaildOrderAmount();
        AtomicInteger atomicInteger = new AtomicInteger(validAmount);
        BigDecimal otherFee = groundShopInfoBusinEntity.getGroundOtherFee();
        BigDecimal totalFee = groundShopInfoBusinEntity.getAccountBalance();
        otherFee = otherFee.add(commissionResEntity.getOrderFee());
        totalFee = totalFee.add(commissionResEntity.getOrderFee());
        GroundShopInfoBusinEntity groundShopInfoBusin = new GroundShopInfoBusinEntity();
        // 更新有效注册数量以及相应费用
        groundShopInfoBusin.setVaildOrderAmount(atomicInteger.addAndGet(1));
        groundShopInfoBusin.setGroundOtherFee(otherFee);
        groundShopInfoBusin.setAccountBalance(totalFee);
        int flag1 = groundShopInfoBusinEntityMapper.updateShopGroundRegInfo(groundShopInfoBusinEntity);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_DB_FAIL, "更新商家有效注册数量以及相应费用失败");
        }
        // 更新 地推注册用户详细表
        String historyGroundId = updateRegisterUserOrder(registerEntity);
        // 插入数据到地推订单/充值详情表
        // 获取cityId、shopId、shopAssistantId、mobile、orderNumber、groundType、money
        GroundOrderDetailBusinEntity groundOrderDetailBusinEntity = buildGroundOrderDetail(registerEntity);
        // 通过shopId获取该商户基本信息
        HomeReqEntity homereg = new HomeReqEntity();
        homereg.setShopId(registerEntity.getShopId());
        ShopInfoEntity shopsInfoEntity = shopFeignClient.getShopsInfoByShopId(homereg);
        if (null == shopsInfoEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_GET_SHOP_INFO_FAIL, "通过shopId获取该商户基本信息失败");
        }
        groundOrderDetailBusinEntity.setShopName(groundShopInfoBusinEntity.getShopName());
        groundOrderDetailBusinEntity.setShopCode(groundShopInfoBusinEntity.getShopCode());
        groundOrderDetailBusinEntity.setGroundTotalFee(commissionResEntity.getOrderFee());
        groundOrderDetailBusinEntity.setHistoryGroundId(historyGroundId);
        groundOrderDetailBusinEntity.setGroundOrderType("2");
        int flag3 = groundOrderDetailBusinEntityMapper.savaGroundOrderDetail(groundOrderDetailBusinEntity);
        if (1 != flag3)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_ORDER_DETAIL_DB_FAIL, "更新订单详细信息表失败");
        }

    }

    /**
     * 更新个人 有效订单 <功能详细描述>
     * @param salesmanId
     * @param cityId
     * @param mobile
     * @param money
     * @param orderNumber
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void updateUserGroundVaildOrderInfo(RegisterEntity registerEntity) throws InnerException
    {
        // 根据地推人员id获取地推人员详细信息
        GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = groundPersonalInfoBusinMapper
                .queryPersonalGroundBySalesManId(registerEntity.getSalesManId());
        if (null == groundPersonalInfoBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_SELECT_FAIL, "查询该地推人员详细信息失败");
        }
        if (!groundPersonalInfoBusinEntity.getStatus().equals("1"))
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_STATUS_FAIL, "该地推人员已失效");
        }
        // 获取金额
        CommissionResEntity commissionResEntity = groundCommissionService.getCommission(registerEntity.getMoney(),
                registerEntity.getMobile());
        if (null == commissionResEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_GET_COMMISSION_FAIL, "获取佣金失败");
        }
        if (commissionResEntity.isVaildOrderFlag() == false)
        {
            updateValidRegisterUserOrder(registerEntity);
            return;
        }
        int validOrderAmount = groundPersonalInfoBusinEntity.getValidOrderAmount();
        BigDecimal otherFee = groundPersonalInfoBusinEntity.getGroundOtherFee();
        BigDecimal totalFee = groundPersonalInfoBusinEntity.getGroundTotalFee();
        otherFee = otherFee.add(commissionResEntity.getOrderFee());
        totalFee = totalFee.add(commissionResEntity.getOrderFee());
        AtomicInteger atomicInteger = new AtomicInteger(validOrderAmount);
        GroundPersonalInfoBusinEntity groundPersonalInfoBusin = new GroundPersonalInfoBusinEntity();
        groundPersonalInfoBusin.setValidOrderAmount(atomicInteger.addAndGet(1));
        groundPersonalInfoBusin.setGroundOtherFee(otherFee);
        groundPersonalInfoBusin.setGroundTotalFee(totalFee);
        groundPersonalInfoBusin.setSalesmanId(registerEntity.getSalesManId());
        // 更新订单数量以及相应金额
        int flag1 = groundPersonalInfoBusinMapper.updatePersonalGroundRegisterInfo(groundPersonalInfoBusin);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_DB_FAIL, "更新订单数量以及相应金额失败");
        }
        // 通过地推人员ID查询个人地推配置表 并更新 该方案地推费用（groundFee）
        GroundPersonPlanHistoryConfEntity groundPersonPlanHistory = groundPersonPlanHistoryConfEntityMapper
                .queryGroundPersonPlanHistoryBySalesId(groundPersonalInfoBusinEntity.getSalesmanId());
        if (null == groundPersonPlanHistory)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSON_PLAN_HISTORY_SELECT_FAIL, "通过地推人员ID查询个人地推配置表失败");
        }
        BigDecimal groundHistoryFee = groundPersonPlanHistory.getGroundFee();
        groundHistoryFee = groundHistoryFee.add(commissionResEntity.getOrderFee());
        boolean flag = groundPersonPlanHistoryConfService.updateGroundFee(groundPersonPlanHistory.getId(),
                groundHistoryFee);
        if (flag == false)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSON_PLAN_HISTORY_DB_FAIL, "更新个人地推历史配置表失败");
        }
        // 更新 地推注册用户详细表
        String historyGroundId = updateRegisterUserOrder(registerEntity);
        // 插入数据到地推订单/充值详情表
        // 获取cityId、salesmanId、mobile、orderNumber、groundType、money
        GroundOrderDetailBusinEntity groundOrderDetailBusinEntity = buildGroundOrderDetail(registerEntity);
        groundOrderDetailBusinEntity.setGroundTotalFee(commissionResEntity.getOrderFee());
        groundOrderDetailBusinEntity.setHistoryGroundId(historyGroundId);
        groundOrderDetailBusinEntity.setGroundOrderType("2");
        int flag3 = groundOrderDetailBusinEntityMapper.savaGroundOrderDetail(groundOrderDetailBusinEntity);
        if (1 != flag3)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_ORDER_DETAIL_DB_FAIL, "更新地推订单/充值详情表失败");
        }

    }

    /**
     * 更新个人 有效充值 <功能详细描述>
     * @param salesmanId
     * @param cityId
     * @param mobile
     * @param money
     * @param orderNumber
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void updateUserGroundVaildRechargeInfo(RegisterEntity registerEntity) throws InnerException
    {
        // 根据地推人员id获取地推人员详细信息
        GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = groundPersonalInfoBusinMapper
                .queryPersonalGroundBySalesManId(registerEntity.getSalesManId());
        if (null == groundPersonalInfoBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_SELECT_FAIL, "查询该地推人员详细信息失败");
        }
        if (!groundPersonalInfoBusinEntity.getStatus().equals("1"))
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_STATUS_FAIL, "该地推人员状态已失效");
        }
        // 获取金额
        CommissionResEntity commissionResEntity = groundCommissionService.getCommission(registerEntity.getMoney(),
                registerEntity.getMobile());
        if (null == commissionResEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_GET_COMMISSION_FAIL, "获取佣金失败");
        }
        if (commissionResEntity.isVaildRechargeFlag() == false)
        {
            return;
        }
        int validRechargeAmount = groundPersonalInfoBusinEntity.getValidRechargeAmount();
        BigDecimal otherFee = groundPersonalInfoBusinEntity.getGroundOtherFee();
        BigDecimal totalFee = groundPersonalInfoBusinEntity.getGroundTotalFee();
        otherFee = otherFee.add(commissionResEntity.getRechargeFee());
        totalFee = totalFee.add(commissionResEntity.getRechargeFee());
        AtomicInteger atomicInteger = new AtomicInteger(validRechargeAmount);
        GroundPersonalInfoBusinEntity groundPersonalInfoBusin = new GroundPersonalInfoBusinEntity();
        groundPersonalInfoBusin.setValidRechargeAmount(atomicInteger.addAndGet(1));
        groundPersonalInfoBusin.setGroundOtherFee(otherFee);
        groundPersonalInfoBusin.setGroundTotalFee(totalFee);
        groundPersonalInfoBusin.setSalesmanId(registerEntity.getSalesManId());
        // 更新订单数量以及相应金额
        int flag1 = groundPersonalInfoBusinMapper.updatePersonalGroundRegisterInfo(groundPersonalInfoBusin);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_DB_FAIL, "更新（个人地推信息表）订单数量以及相应金额失败");
        }
        // 通过地推人员ID查询个人地推配置表 并更新 该方案地推费用（groundFee）
        GroundPersonPlanHistoryConfEntity groundPersonPlanHistory = groundPersonPlanHistoryConfEntityMapper
                .queryGroundPersonPlanHistoryBySalesId(groundPersonalInfoBusinEntity.getSalesmanId());
        if (null == groundPersonPlanHistory)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSON_PLAN_HISTORY_SELECT_FAIL, "通过地推人员ID查询个人地推配置表失败");
        }
        BigDecimal groundHistoryFee = groundPersonPlanHistory.getGroundFee();
        groundHistoryFee = groundHistoryFee.add(commissionResEntity.getRechargeFee());
        boolean flag = groundPersonPlanHistoryConfService.updateGroundFee(groundPersonPlanHistory.getId(),
                groundHistoryFee);
        if (flag == false)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSON_PLAN_HISTORY_DB_FAIL, "更新（个人地推历史配置表）地推费用失败");
        }
        // 插入数据到地推订单/充值详情表
        // 获取cityId、salesmanId、mobile、orderNumber、groundType、money
        GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusinEntity = groundRegisterUserDetailBusinEntityMapper
                .selectByRegisterMobile(registerEntity.getMobile());
        if (null == groundRegisterUserDetailBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_REGISTER_USER_DETAIL_SELECT_FAIL, "通过手机号获取地推方案id失败");
        }
        GroundOrderDetailBusinEntity groundOrderDetailBusinEntity = buildGroundOrderDetail(registerEntity);
        groundOrderDetailBusinEntity.setGroundTotalFee(commissionResEntity.getRechargeFee());
        groundOrderDetailBusinEntity.setHistoryGroundId(groundRegisterUserDetailBusinEntity.getHistoryGroundId());
        groundOrderDetailBusinEntity.setGroundOrderType("1");
        int flag3 = groundOrderDetailBusinEntityMapper.savaGroundOrderDetail(groundOrderDetailBusinEntity);
        if (1 != flag3)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_ORDER_DETAIL_DB_FAIL, "存储个人地推订单详细信息失败");
        }

    }

    /**
     * 构造 充值/订单 详细信息实体类 <功能详细描述>
     * @param registerEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public GroundOrderDetailBusinEntity buildGroundOrderDetail(RegisterEntity registerEntity)
    {
        GroundOrderDetailBusinEntity groundOrderDetailBusinEntity = new GroundOrderDetailBusinEntity();
        groundOrderDetailBusinEntity.setMoney(new BigDecimal(registerEntity.getMoney()));
        groundOrderDetailBusinEntity.setCityId(registerEntity.getCityId());
        groundOrderDetailBusinEntity.setShopAssistantId(registerEntity.getShopAssistantId());
        groundOrderDetailBusinEntity.setShopId(registerEntity.getShopId());
        groundOrderDetailBusinEntity.setGroupSalesmanId(registerEntity.getSalesManId());
        groundOrderDetailBusinEntity.setRegisterMobile(registerEntity.getMobile());
        groundOrderDetailBusinEntity.setGroundType(registerEntity.getGroundType());
        groundOrderDetailBusinEntity.setOrderNumber(registerEntity.getOrderNumber());
        return groundOrderDetailBusinEntity;

    }

    /**
     * 订单 更新 地推注册用户详细表 <功能详细描述>
     * @param registerEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public String updateRegisterUserOrder(RegisterEntity registerEntity) throws InnerException
    {
        GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusinEntity = groundRegisterUserDetailBusinEntityMapper
                .selectByRegisterMobile(registerEntity.getMobile());
        if (null == groundRegisterUserDetailBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_REGISTER_USER_DETAIL_SELECT_FAIL, "通过手机号获取地推方案id失败");
        }
        BigDecimal orderFee = groundRegisterUserDetailBusinEntity.getGroundOrderActivityFee()
                .add(new BigDecimal(registerEntity.getMoney()));
        int orderAmount = groundRegisterUserDetailBusinEntity.getOrderAmount();
        int groundOrderAmount = groundRegisterUserDetailBusinEntity.getGroundOrderAmount();
        AtomicInteger atomicOrderAmount = new AtomicInteger(orderAmount);
        AtomicInteger atomicGroundOrderAmount = new AtomicInteger(groundOrderAmount);
        GroundRegisterUserDetailBusinEntity groundRegisterUserDetail = new GroundRegisterUserDetailBusinEntity();
        groundRegisterUserDetail.setGroundOrderActivityFee(orderFee);
        groundRegisterUserDetail.setOrderAmount(atomicOrderAmount.addAndGet(1));
        groundRegisterUserDetail.setGroundOrderAmount(atomicGroundOrderAmount.addAndGet(1));
        groundRegisterUserDetail.setRegisterMobile(registerEntity.getMobile());
        int flag2 = groundRegisterUserDetailBusinEntityMapper.updateRegisterUserOrder(groundRegisterUserDetail);
        if (1 != flag2)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_REGISTER_USER_DETAIL_DB_FAIL,
                    "GroundRechargeOrderService:通过手机号更新地推订单信息失败");
        }
        return groundRegisterUserDetailBusinEntity.getHistoryGroundId();
    }

    /**
     * 无效订单 更新 地推注册用户详细表 <功能详细描述>
     * @param registerEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updateValidRegisterUserOrder(RegisterEntity registerEntity) throws InnerException
    {
        GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusinEntity = groundRegisterUserDetailBusinEntityMapper
                .selectByRegisterMobile(registerEntity.getMobile());
        if (null == groundRegisterUserDetailBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_REGISTER_USER_DETAIL_SELECT_FAIL, "通过手机号获取地推方案id失败");
        }
        int orderAmount = groundRegisterUserDetailBusinEntity.getOrderAmount();
        AtomicInteger atomicOrderAmount = new AtomicInteger(orderAmount);
        GroundRegisterUserDetailBusinEntity groundRegisterUserDetail = new GroundRegisterUserDetailBusinEntity();
        groundRegisterUserDetail.setOrderAmount(atomicOrderAmount.addAndGet(1));
        groundRegisterUserDetail.setRegisterMobile(registerEntity.getMobile());
        int flag2 = groundRegisterUserDetailBusinEntityMapper.updateRegisterUserOrder(groundRegisterUserDetail);
        if (1 != flag2)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_REGISTER_USER_DETAIL_DB_FAIL, "通过手机号更新地推订单信息失败");
        }
        GroundOrderDetailBusinEntity groundOrderDetailBusinEntity = buildGroundOrderDetail(registerEntity);
        groundOrderDetailBusinEntity.setGroundOrderType("2");
        int flag3 = groundOrderDetailBusinEntityMapper.savaGroundOrderDetail(groundOrderDetailBusinEntity);
        if (1 != flag3)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_ORDER_DETAIL_DB_FAIL, "存储个人地推订单详细信息失败");
        }
    }

}
