package com.fbw.service.service.commission;

import org.springframework.stereotype.Service;

import com.fbw.service.context.AbstractCommissionSchemeSuper;
import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述>每个有效地推的注册用户 0 元，且该用户前 0 笔金额满 0 元以上的充值按 0 元／单计为地推充值收入
 * @author JACK HUANG
 * @version [版本号, 2017年9月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class PersonalCommissionPlanSix extends AbstractCommissionSchemeSuper
{

    @Override
    public CommissionResEntity getCommissionFee(CommissionReqEntity commissionReqEntity)
    {
        CommissionResEntity commissionResEntity = new CommissionResEntity();
        // 获取个人地推方案规则
        GroundPlanConfEntity groundPlanConfEntity = getPersonalPlanRule(commissionReqEntity);
        // 且该用户前 0 笔金额满 0 元以上
        if (NomalUtil.AgreaterThanB(groundPlanConfEntity.getUserRechargeAmountBefore(),
                commissionReqEntity.getVaildRecharge())
                && NomalUtil.AgreaterThanB(groundPlanConfEntity.getEachRechargeFeeLimit(),
                        commissionReqEntity.getRechargeMoney()))
        {
            commissionResEntity.setRechargeFee(groundPlanConfEntity.getEachRechargePirceFee());
            commissionResEntity.setVaildRechargeFlag(true);
        }
        else
        {
            commissionResEntity.setVaildRechargeFlag(false);
        }
        // 注册佣金
        commissionResEntity.setRegisterFee(groundPlanConfEntity.getValidRegisterFee());
        commissionResEntity.setVaildRegisterFlag(true);
        commissionResEntity.setVaildOrderFlag(false);
        return commissionResEntity;
    }

}
