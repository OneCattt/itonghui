package com.fbw.service.service.commission;

import org.springframework.stereotype.Service;

import com.fbw.service.context.AbstractCommissionSchemeSuper;
import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述>每个有效地推的注册用户 XX元，且该用户的每笔消费实付金额满 XX以上的订单按 XX元／单计为地推订单收入
 * @author JACK HUANG
 * @version [版本号, 2017年9月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class PersonalCommissionPlanTwo extends AbstractCommissionSchemeSuper
{

    @Override
    public CommissionResEntity getCommissionFee(CommissionReqEntity commissionReqEntity)
    {
        CommissionResEntity commissionResEntity = new CommissionResEntity();
        // 获取个人地推方案规则
        GroundPlanConfEntity groundPlanConfEntity = getPersonalPlanRule(commissionReqEntity);
        // 判断实付金额是否大于等于配置金额
        if (NomalUtil.AgreaterThanB(commissionReqEntity.getOrderMoney(),
                groundPlanConfEntity.getConsumeActivityFeeLimit()))
        {
            commissionResEntity.setOrderFee(groundPlanConfEntity.getEachOrderPieceFee());
            commissionResEntity.setVaildOrderFlag(true);
        }
        else
        {
            commissionResEntity.setVaildOrderFlag(false);
        }
        // 注册佣金
        commissionResEntity.setRegisterFee(groundPlanConfEntity.getValidRegisterFee());
        commissionResEntity.setVaildRegisterFlag(true);
        commissionResEntity.setVaildRechargeFlag(false);
        return commissionResEntity;
    }

}
