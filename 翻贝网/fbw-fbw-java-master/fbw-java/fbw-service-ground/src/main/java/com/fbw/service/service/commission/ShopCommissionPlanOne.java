package com.fbw.service.service.commission;

import org.springframework.stereotype.Service;

import com.fbw.service.context.AbstractCommissionSchemeSuper;
import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundShopPlanConfEntity;

/**
 * 
 * <功能详细描述> 注册用户 5元/个
 * @author JACK HUANG
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class ShopCommissionPlanOne extends AbstractCommissionSchemeSuper
{

    @Override
    public CommissionResEntity getCommissionFee(CommissionReqEntity commissionReqEntity)
    {
        CommissionResEntity commissionResEntity = new CommissionResEntity();
        // 获取商铺地推方案规则
        GroundShopPlanConfEntity groundShopPlanConfEntity = getShopPlanRule(commissionReqEntity);
        commissionResEntity.setRegisterFee(groundShopPlanConfEntity.getEveryDowappAmount());
        commissionResEntity.setVaildRegisterFlag(true);
        commissionResEntity.setVaildOrderFlag(false);
        commissionResEntity.setVaildRechargeFlag(false);
        return commissionResEntity;
    }

}
