package com.fbw.service.service.commission;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.fbw.service.context.AbstractCommissionSchemeSuper;
import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;

/**
 * 
 * <功能详细描述>每个有效地推的注册用户 xx元，且该用户的每笔消费按实付金额的 xx%计为地推订单收入。
 * @author JACK HUANG
 * @version [版本号, 2017年9月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class PersonalCommissionPlanThree extends AbstractCommissionSchemeSuper
{

    @Override
    public CommissionResEntity getCommissionFee(CommissionReqEntity commissionReqEntity)
    {
        CommissionResEntity commissionResEntity = new CommissionResEntity();
        // 获取个人地推方案规则
        GroundPlanConfEntity groundPlanConfEntity = getPersonalPlanRule(commissionReqEntity);
        // 订单佣金(每笔消费按实付金额的 xx%计为地推订单收入)
        BigDecimal orderFee = commissionReqEntity.getOrderMoney()
                .multiply(groundPlanConfEntity.getEachActivityFeePiecePercent());
        commissionResEntity.setOrderFee(orderFee);
        // 注册佣金
        commissionResEntity.setRegisterFee(groundPlanConfEntity.getValidRegisterFee());
        commissionResEntity.setVaildRegisterFlag(true);
        commissionResEntity.setVaildOrderFlag(true);
        commissionResEntity.setVaildRechargeFlag(false);
        return commissionResEntity;
    }

}
