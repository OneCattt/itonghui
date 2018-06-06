package com.fbw.service.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundPersonPlanHistoryConfEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundRegisterUserDetailBusinEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.GroundPersonPlanHistoryConfEntityMapper;
import com.fbw.service.mapper.GroundPersonalInfoBusinMapper;
import com.fbw.service.mapper.GroundRegisterUserDetailBusinEntityMapper;
import com.fbw.service.mapper.GroundShopInfoBusinEntityMapper;
import com.fbw.service.mapper.UserGroundInfoBusinEntityMapper;

/**
 * 用户注册Service <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class GroundRegisterService
{

    @Autowired
    private GroundRegisterUserDetailBusinEntityMapper groundRegisterUserDetailBusinEntityMapper;

    @Autowired
    private GroundPersonalInfoBusinMapper groundPersonalInfoBusinMapper;

    @Autowired
    private GroundCommissionService groundCommissionService;

    @Autowired
    private UserGroundInfoBusinEntityMapper userGroundInfoBusinEntityMapper;

    @Autowired
    private GroundShopInfoBusinEntityMapper groundShopInfoBusinEntityMapper;

    @Autowired
    private GroundPersonPlanHistoryConfEntityMapper groundPersonPlanHistoryConfEntityMapper;

    @Autowired
    private GroundPersonPlanHistoryConfService groundPersonPlanHistoryConfService;

    /**
     * 
     * <功能详细描述>绑定商户无效的注册用户
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void bindingShopInVaildRegister(RegisterEntity registerEntity) throws InnerException
    {
        // 插入用户地推详细信息
        insertUserGroundDetailInfo(registerEntity);
        // 更新商户地推注册信息
        updateShopGroundInVaildRegInfo(registerEntity.getShopId());

    }

    /**
     * 
     * <功能详细描述>绑定商户有效的注册用户
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void bindingShopVaildRegister(RegisterEntity registerEntity) throws InnerException
    {
        // 插入用户地推详细信息
        insertUserGroundDetailInfo(registerEntity);
        // 更新商户地推注册信息(更新注册数，有效注册数，注册收入)
        updatePersonalGroundVaildRegInfo(registerEntity.getShopId(), registerEntity.getMobile());
    }

    /**
     * 
     * <功能详细描述> 插入用户地推详细信息
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    private void insertUserGroundDetailInfo(RegisterEntity registerEntity) throws InnerException
    {
        // 保存用户地推人员信息
        int flag2 = userGroundInfoBusinEntityMapper.insertUserGround((buildGroundInfo(registerEntity)));
        if (1 != flag2)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_USER_INFO_DB_FAIL, "保存用户地推信息失败");
        }
        // 保存注册用户详细地推信息
        int flag1 = groundRegisterUserDetailBusinEntityMapper
                .saveRegisterUserDetailWithGround(buildGroundRegisterUser(registerEntity));
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_REGISTER_USER_DETAIL_DB_FAIL, "保存注册用户详细地推信息失败");
        }
    }

    /**
     * 
     * <功能详细描述>绑定个人无效的注册用户
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void bindingPersonalInVaildRegister(RegisterEntity registerEntity) throws InnerException
    {
        // 插入用户地推详细信息
        insertUserGroundDetailInfo(registerEntity);
        // 更新个人地推无效注册信息(更新注册数)
        updatePersonalGroundInVaildRegInfo(registerEntity.getSalesManId());

    }

    /**
     * 
     * <功能详细描述>绑定个人有效的注册用户
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void bindingPersonalVaildRegister(RegisterEntity registerEntity) throws InnerException
    {
        // 插入用户地推详细信息
        insertUserGroundDetailInfo(registerEntity);
        // 更新个人地推有效注册信息(更新注册数，有效注册数，注册收入)
        updateVaildPersonalRegGroundInfo(registerEntity);
    }

    /**
     * 
     * <功能详细描述>更新无效个人注册地推信息
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void updateInVaildPersonalRegGroundInfo(RegisterEntity registerEntity) throws InnerException
    {

        // 更新个人地推无效注册信息(更新注册数)
        updatePersonalGroundInVaildRegInfo(registerEntity.getSalesManId());
        // 保存注册用户详细地推信
        int flag = groundRegisterUserDetailBusinEntityMapper
                .saveRegisterUserDetailWithGround(buildGroundRegisterUser(registerEntity));
        // 更新无效地推用户注册状态
        if (1 != flag)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_REGISTER_USER_DETAIL_DB_FAIL, "更新无效地推用户注册状态失败");
        }

    }

    /**
     * 更新商铺地推无效注册信息 <功能详细描述>
     * @param shopId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void updateShopGroundInVaildRegInfo(String shopId) throws InnerException
    {
        GroundShopInfoBusinEntity groundPersonalInfoBusinEntity = groundShopInfoBusinEntityMapper
                .queryShopGroundByShopId(Integer.parseInt(shopId));
        if (null == groundPersonalInfoBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_SELECT_FAIL, "通过商户ID查询商户地推信息失败");
        }
        int registerAmount = groundPersonalInfoBusinEntity.getRegisterAmount();
        AtomicInteger atomicInteger = new AtomicInteger(registerAmount);
        GroundShopInfoBusinEntity groundPersonalInfoBusinEntitys = new GroundShopInfoBusinEntity();
        groundPersonalInfoBusinEntitys.setRegisterAmount(atomicInteger.addAndGet(1));
        groundPersonalInfoBusinEntitys.setShopId(shopId);
        int flag = groundShopInfoBusinEntityMapper.updateShopGroundRegInfo(groundPersonalInfoBusinEntitys);
        if (1 != flag)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_DB_FAIL, "更新商家地推注册信息失败");
        }
    }

    /**
     * 更新用户商户地推有效信息 <功能详细描述>
     * @param shopId
     * @param mobile
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void updatePersonalGroundVaildRegInfo(String shopId, String mobile) throws InnerException
    {
        // 通过shopId获取商户地推基本信息
        GroundShopInfoBusinEntity groundShopInfoBusinEntity = groundShopInfoBusinEntityMapper
                .queryShopGroundByShopId(Integer.parseInt(shopId));
        if (null == groundShopInfoBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_SELECT_FAIL, "通过商户ID查询商户地推信息失败");
        }
        if (!groundShopInfoBusinEntity.getStatus().equals("1"))
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_STATUS_FAIL, "商户地推状态已失效");
        }
        // 获取金额
        CommissionResEntity commissionResEntity = groundCommissionService.getCommission("0", mobile);
        if (null == commissionResEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_GET_COMMISSION_FAIL, "获取佣金失败");
        }
        if (commissionResEntity.isVaildRegisterFlag() == false)
        {
            return;
        }
        int registerAmount = groundShopInfoBusinEntity.getRegisterAmount();
        int validRegisterAmount = groundShopInfoBusinEntity.getValidRegisterAmount();
        AtomicInteger atomicInteger = new AtomicInteger(registerAmount);
        AtomicInteger validAtomicInteger = new AtomicInteger(validRegisterAmount);
        BigDecimal registerFee = groundShopInfoBusinEntity.getGroundRegisterFee();
        BigDecimal totalFee = groundShopInfoBusinEntity.getAccountBalance();
        GroundShopInfoBusinEntity groundShopInfoBusin = new GroundShopInfoBusinEntity();
        groundShopInfoBusin.setRegisterAmount(atomicInteger.addAndGet(1));
        groundShopInfoBusin.setValidRegisterAmount(validAtomicInteger.addAndGet(1));
        registerFee = registerFee.add(commissionResEntity.getRegisterFee());
        totalFee = totalFee.add(commissionResEntity.getRegisterFee());
        groundShopInfoBusin.setGroundRegisterFee(registerFee);
        groundShopInfoBusin.setAccountBalance(totalFee);
        groundShopInfoBusin.setShopId(shopId);
        // 更新注册以及相应金额
        int flag1 = groundShopInfoBusinEntityMapper.updateShopGroundRegInfo(groundShopInfoBusin);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_SHOP_INFO_DB_FAIL, "更新商家地推注册信息失败");
        }
    }

    /**
     * 更新个人地推无效信息 <功能详细描述>
     * @param salesManId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void updatePersonalGroundInVaildRegInfo(String salesManId) throws InnerException
    {
        GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = groundPersonalInfoBusinMapper
                .queryPersonalGroundBySalesManId(salesManId);
        if (null == groundPersonalInfoBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_SELECT_FAIL, "通过salesmanID查询商户地推信息失败");
        }
        if (!groundPersonalInfoBusinEntity.getStatus().equals("1"))
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_STATUS_FAIL, "个人地推状态已失效");
        }
        int registerAmount = groundPersonalInfoBusinEntity.getRegisterAmount();
        AtomicInteger atomicInteger = new AtomicInteger(registerAmount);
        groundPersonalInfoBusinEntity.setRegisterAmount(atomicInteger.addAndGet(1));
        int flag = groundPersonalInfoBusinMapper.updatePersonalGroundRegisterInfo(groundPersonalInfoBusinEntity);
        if (1 != flag)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_DB_FAIL, "更新个人地推注册信息 失败");
        }
    }

    /**
     * 
     * <功能详细描述> 更新有效个人注册地推信息
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void updateVaildPersonalRegGroundInfo(RegisterEntity registerEntity) throws InnerException
    {
        // 根据地推人员id获取地推人员详细信息
        GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = groundPersonalInfoBusinMapper
                .queryPersonalGroundBySalesManId(registerEntity.getSalesManId());
        if (null == groundPersonalInfoBusinEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_SELECT_FAIL, "通过salesmanID查询商户地推信息失败");
        }
        if (!groundPersonalInfoBusinEntity.getStatus().equals("1"))
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_STATUS_FAIL, "个人地推状态已失效");
        }
        // 获取金额
        CommissionResEntity commissionResEntity = groundCommissionService.getCommission("0",
                registerEntity.getMobile());
        if (null == commissionResEntity)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_GET_COMMISSION_FAIL, "获取佣金失败");
        }
        if (commissionResEntity.isVaildRegisterFlag() == false)
        {
            return;
        }
        int validRegisterAmount = groundPersonalInfoBusinEntity.getValidRegisterAmount();
        AtomicInteger vatomicInteger = new AtomicInteger(validRegisterAmount);
        groundPersonalInfoBusinEntity.setValidRegisterAmount(vatomicInteger.addAndGet(1));
        BigDecimal registerFee = groundPersonalInfoBusinEntity.getGroundRegisterFee();
        BigDecimal totalFee = groundPersonalInfoBusinEntity.getGroundTotalFee();
        registerFee = registerFee.add(commissionResEntity.getRegisterFee());
        totalFee = totalFee.add(commissionResEntity.getRegisterFee());
        int registerAmount = groundPersonalInfoBusinEntity.getRegisterAmount();
        AtomicInteger atomicInteger = new AtomicInteger(registerAmount);
        GroundPersonalInfoBusinEntity groundPersonalInfoBusin = new GroundPersonalInfoBusinEntity();
        groundPersonalInfoBusin.setRegisterAmount(atomicInteger.addAndGet(1));
        groundPersonalInfoBusin.setGroundRegisterFee(registerFee);
        groundPersonalInfoBusin.setGroundTotalFee(totalFee);
        groundPersonalInfoBusin.setSalesmanId(registerEntity.getSalesManId());
        // 更新注册用户以及相应金额
        int flag1 = groundPersonalInfoBusinMapper.updatePersonalGroundRegisterInfo(groundPersonalInfoBusin);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSONAL_INFO_DB_FAIL, "更新个人地推注册信息失败");
        }
        // 通过地推人员ID查询个人地推配置表 并更新 该方案地推费用（groundFee）
        GroundPersonPlanHistoryConfEntity groundPersonPlanHistory = groundPersonPlanHistoryConfEntityMapper
                .queryGroundPersonPlanHistoryBySalesId(groundPersonalInfoBusinEntity.getSalesmanId());
        if (null == groundPersonPlanHistory)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSON_PLAN_HISTORY_SELECT_FAIL,
                    "通过地推人员ID查询当前最新个人地推配置表失败");
        }
        BigDecimal groundHistoryFee = groundPersonPlanHistory.getGroundFee();
        groundHistoryFee = groundHistoryFee.add(commissionResEntity.getRegisterFee());
        boolean flag = groundPersonPlanHistoryConfService.updateGroundFee(groundPersonPlanHistory.getId(),
                groundHistoryFee);
        if (flag == false)
        {
            throw new InnerException(ErrorMsgConstant.GROUND_PERSON_PLAN_HISTORY_DB_FAIL, "更新地推收入失败");
        }
    }

    /**
     * 
     * <功能详细描述>构造地推注册用户详细实体类
     * @param registerEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private GroundRegisterUserDetailBusinEntity buildGroundRegisterUser(RegisterEntity registerEntity)
    {
        GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusin = new GroundRegisterUserDetailBusinEntity();
        groundRegisterUserDetailBusin.setCityId(registerEntity.getCityId());
        groundRegisterUserDetailBusin.setRegisterStatus(registerEntity.getRegisterStatus());
        groundRegisterUserDetailBusin.setRegisterDate(new Date());
        groundRegisterUserDetailBusin.setSalesmanId(registerEntity.getSalesManId());
        groundRegisterUserDetailBusin.setShopAssistantId(registerEntity.getShopAssistantId());
        groundRegisterUserDetailBusin.setGroundType(registerEntity.getGroundType());
        groundRegisterUserDetailBusin.setRegisterSource(registerEntity.getRegisterResource());
        groundRegisterUserDetailBusin.setShopId(registerEntity.getShopId());
        groundRegisterUserDetailBusin.setRegisterMobile(registerEntity.getMobile());
        return groundRegisterUserDetailBusin;

    }

    /**
     * 
     * <功能详细描述> 构造商户地推信息
     * @param registerEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserGroundInfoBusinEntity buildGroundInfo(RegisterEntity registerEntity)
    {
        UserGroundInfoBusinEntity userGroundInfoBusin = new UserGroundInfoBusinEntity();
        userGroundInfoBusin.setMobile(registerEntity.getMobile());
        userGroundInfoBusin.setGroundType(registerEntity.getGroundType());
        userGroundInfoBusin.setSalesmanId(registerEntity.getSalesManId());
        userGroundInfoBusin.setShopAssistantId(registerEntity.getShopAssistantId());
        userGroundInfoBusin.setShopId(registerEntity.getShopId());
        return userGroundInfoBusin;
    }

    /**
     * 
     * <功能详细描述> 更新邀请码渠道有效的地推注册用户
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void updateVaildGroundRegUserWithInviteCode(RegisterEntity registerEntity) throws InnerException
    {
        // 商户类型
        if ("shop".equals(registerEntity.getGroundType()))
        {
            bindingShopVaildRegister(registerEntity);
        }
        // 个人类型
        else if ("user".equals(registerEntity.getGroundType()))
        {
            bindingPersonalVaildRegister(registerEntity);
        }
    }

    /**
     * 
     * <功能详细描述> 更新邀请码渠道无效的地推注册用户
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void updateInVaildGroundRegUserWithInviteCode(RegisterEntity registerEntity) throws InnerException
    {

        if ("shop".equals(registerEntity.getGroundType()))
        {
            bindingShopInVaildRegister(registerEntity);
        }
        else if ("user".equals(registerEntity.getGroundType()))
        {
            bindingPersonalInVaildRegister(registerEntity);
        }
    }

}
