package com.fbw.service.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundPersonPlanHistoryConfEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.mapper.GroundPersonPlanHistoryConfEntityMapper;
import com.fbw.service.mapper.GroundPersonalInfoBusinMapper;
import com.fbw.service.mapper.GroundShopInfoBusinEntityMapper;
import com.fbw.service.mapper.UserGroundInfoBusinEntityMapper;
import com.fbw.service.service.commission.PersonalCommissionPlanFive;
import com.fbw.service.service.commission.PersonalCommissionPlanFour;
import com.fbw.service.service.commission.PersonalCommissionPlanOne;
import com.fbw.service.service.commission.PersonalCommissionPlanSix;
import com.fbw.service.service.commission.PersonalCommissionPlanThree;
import com.fbw.service.service.commission.PersonalCommissionPlanTwo;
import com.fbw.service.service.commission.ShopCommissionPlanOne;
import com.fbw.service.service.commission.ShopCommissionPlanTwo;

/**
 * 
 * <功能详细描述>抽象地推父类
 * @author JACK HUANG
 * @version [版本号, 2017年9月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class GroundCommissionService
{
    /**
     * 个人地推类型
     */
    final String PERSONAL_GROUND_TYPE = "1";

    /**
     * 商户地推类型
     */
    final String SHOP_GROUND_TYPE = "2";

    /**
     * 每个有效地推的注册用户 xx元
     */
    final String PERSONAL_PLAN_ONE = "1";

    /**
     * 每个有效地推的注册用户 XX元，且该用户的每笔消费实付金额满 XX以上的订单按 XX元／单计为地推订单收入
     */
    final String PERSONAL_PLAN_TWO = "2";

    /**
     * 每个有效地推的注册用户 xx元，且该用户的每笔消费按实付金额的 xx%计为地推订单收入。
     */
    final String PERSONAL_PLAN_THREE = "3";

    /**
     * 前 0 名有效地推用户：每个有效地推的注册用户XX 元，且该用户的每笔消费按实付金额的XX
     * %计为地推订单收入；之后的有效地推用户：每个有效地推的注册用户XX元，且该用户的每笔消费按实付金额的XX%计为地推订单收入。
     */
    final String PERSONAL_PLAN_FOUR = "4";

    /**
     * 每个有效地推的注册用户 0 元，且该用户首笔金额满 0 元以上的充值按 0 元／单计为地推充值收入
     */
    final String PERSONAL_PLAN_FIVE = "5";

    /**
     * 每个有效地推的注册用户 0 元，且该用户前 0 笔金额满 0 元以上的充值按 0 元／单计为地推充值收入
     */
    final String PERSONAL_PLAN_SIX = "6";

    /**
     * 期限x年 注册用户 XX元/个
     */
    final String SHOP_PLAN_ONE = "1";

    /**
     * 期限x年、前x名注册用户x元／个，x名以后注册用户x元／个；地推订单：按订单实付金额的x%计为地推订单的收益
     */
    final String SHOP_PLAN_TWO = "2";

    @Autowired
    UserGroundInfoBusinEntityMapper userGroundInfoBusinEntityMapper;

    @Autowired
    PersonalCommissionPlanOne personalCommissionPlanOne;

    @Autowired
    PersonalCommissionPlanTwo personalCommissionPlanTwo;

    @Autowired
    PersonalCommissionPlanThree personalCommissionPlanThree;

    @Autowired
    PersonalCommissionPlanFour personalCommissionPlanFour;

    @Autowired
    PersonalCommissionPlanFive personalCommissionPlanFive;

    @Autowired
    PersonalCommissionPlanSix personalCommissionPlanSix;

    @Autowired
    ShopCommissionPlanOne shopCommissionPlanOne;

    @Autowired
    ShopCommissionPlanTwo shopCommissionPlanTwo;

    @Autowired
    GroundPersonPlanHistoryConfService groundPersonPlanHistoryConfService;

    @Autowired
    GroundPersonalInfoBusinMapper groundPersonalInfoBusinMapper;

    @Autowired
    GroundShopInfoBusinEntityMapper groundShopInfoBusinEntityMapper;

    @Autowired
    GroundPersonPlanHistoryConfEntityMapper groundPersonPlanHistoryConfEntityMapper;

    /**
     * 
     * <功能详细描述> 获取佣金
     * @param parm
     * @return
     * @see [类、类#方法、类#成员]
     */
    public CommissionResEntity getCommission(String money, String mobile)
    {
        // 构造佣金入参实体类
        CommissionReqEntity commissionReqEntity = buildCommissionReqEntity(money, mobile);
        // 计算佣金
        return calculateCommission(commissionReqEntity);

    }

    /**
     * 
     * <功能详细描述>构造佣金入参实体类
     * @param moneys 订单金额（充值金额）
     * @param mobile 手机号
     * @return
     * @see [类、类#方法、类#成员]
     */
    private CommissionReqEntity buildCommissionReqEntity(String moneys, String mobile)
    {

        CommissionReqEntity commissionReqEntity = new CommissionReqEntity();
        // 查询用户地推信息
        UserGroundInfoBusinEntity userGroundInfoBusinEntity = userGroundInfoBusinEntityMapper
                .queryUserGroundInfo(mobile);

        String groundType = userGroundInfoBusinEntity.getGroundType();
        if (PERSONAL_GROUND_TYPE.equals(groundType))
        {
            // 通过地推人员ID查询个人地推配置表
            GroundPersonPlanHistoryConfEntity groundPersonPlanHistory = groundPersonPlanHistoryConfEntityMapper
                    .queryGroundPersonPlanHistoryBySalesId(userGroundInfoBusinEntity.getSalesmanId());
            // 获取有效的注册用户数量和有效充值数量
            GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = groundPersonalInfoBusinMapper
                    .queryPersonalGroundBySalesManId(userGroundInfoBusinEntity.getSalesmanId());
            commissionReqEntity
                    .setVaildRegister(new BigDecimal(groundPersonalInfoBusinEntity.getValidRegisterAmount()));
            commissionReqEntity
                    .setVaildRecharge(new BigDecimal(groundPersonalInfoBusinEntity.getValidRechargeAmount()));
            commissionReqEntity.setGroundPlanType(groundPersonPlanHistory.getGroundSchemeType());
            commissionReqEntity.setGroundId(groundPersonPlanHistory.getGroundId());

        }
        else if (SHOP_GROUND_TYPE.equals(groundType))
        {
            GroundShopInfoBusinEntity groundShopInfoBusinEntity = groundShopInfoBusinEntityMapper
                    .queryShopGroundByShopId(Integer.parseInt(userGroundInfoBusinEntity.getShopId()));
            commissionReqEntity.setVaildRegister(new BigDecimal(groundShopInfoBusinEntity.getValidRegisterAmount()));
            commissionReqEntity.setGroundId(groundShopInfoBusinEntity.getShopGroundType());
            commissionReqEntity.setGroundPlanType(groundShopInfoBusinEntity.getShopGroundType());
        }
        if (null != moneys)
        {
            BigDecimal money = new BigDecimal(moneys);
            commissionReqEntity.setOrderMoney(money);
            commissionReqEntity.setRechargeMoney(money);
        }
        commissionReqEntity.setSalesManId(userGroundInfoBusinEntity.getSalesmanId());
        commissionReqEntity.setGroundType(groundType);
        return commissionReqEntity;

    }

    /**
     * 
     * <功能详细描述>计算佣金
     * @param commissionReqEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private CommissionResEntity calculateCommission(CommissionReqEntity commissionReqEntity)
    {
        CommissionResEntity commissionResEntity = null;
        String groundPlanType = commissionReqEntity.getGroundPlanType();
        String groundType = commissionReqEntity.getGroundType();
        if (PERSONAL_GROUND_TYPE.equals(groundType))
        {
            if (PERSONAL_PLAN_ONE.equals(commissionReqEntity.getGroundPlanType()))
            {
                return personalCommissionPlanOne.getCommissionFee(commissionReqEntity);
            }
            else if (PERSONAL_PLAN_TWO.equals(groundPlanType))
            {
                return personalCommissionPlanTwo.getCommissionFee(commissionReqEntity);
            }
            else if (PERSONAL_PLAN_THREE.equals(groundPlanType))
            {
                return personalCommissionPlanThree.getCommissionFee(commissionReqEntity);
            }
            else if (PERSONAL_PLAN_FOUR.equals(groundPlanType))
            {
                return personalCommissionPlanFour.getCommissionFee(commissionReqEntity);
            }
            else if (PERSONAL_PLAN_FIVE.equals(groundPlanType))
            {
                return personalCommissionPlanFive.getCommissionFee(commissionReqEntity);
            }
            else if (PERSONAL_PLAN_SIX.equals(groundPlanType))
            {
                return personalCommissionPlanSix.getCommissionFee(commissionReqEntity);
            }
        }
        else if (SHOP_GROUND_TYPE.equals(groundType))
        {
            if (SHOP_PLAN_ONE.equals(groundPlanType))
            {
                return shopCommissionPlanOne.getCommissionFee(commissionReqEntity);
            }
            else if (SHOP_PLAN_TWO.equals(groundPlanType))
            {
                return shopCommissionPlanTwo.getCommissionFee(commissionReqEntity);
            }

        }
        return commissionResEntity;

    }
}
