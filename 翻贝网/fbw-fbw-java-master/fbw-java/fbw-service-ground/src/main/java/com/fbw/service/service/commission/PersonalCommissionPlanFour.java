package com.fbw.service.service.commission;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.fbw.service.context.AbstractCommissionSchemeSuper;
import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述 > 前 0 名有效地推用户：每个有效地推的注册用户XX 元，且该用户的每笔消费按实付金额的XX
 * %计为地推订单收入；之后的有效地推用户：每个有效地推的注册用户XX元，且该用户的每笔消费按实付金额的XX%计为地推订单收入。
 * @author JACK HUANG
 * @version [版本号, 2017年9月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class PersonalCommissionPlanFour extends AbstractCommissionSchemeSuper
{

    @Override
    public CommissionResEntity getCommissionFee(CommissionReqEntity commissionReqEntity)
    {
        CommissionResEntity commissionResEntity = new CommissionResEntity();
        // 获取个人地推方案规则
        GroundPlanConfEntity groundPlanConfEntity = getPersonalPlanRule(commissionReqEntity);
        // 前 xx名有效地推用户：每个有效地推的注册用户XX 元,且该用户的每笔消费按实付金额的XX%计为地推订单收入
        if (NomalUtil.AgreaterThanB(commissionReqEntity.getVaildRegister(),
                groundPlanConfEntity.getRegisterAmountBefore()))
        {
            BigDecimal orderFee = commissionReqEntity.getOrderMoney()
                    .multiply(groundPlanConfEntity.getEachOrderPiecePercentBefore());
            commissionResEntity.setOrderFee(orderFee);
            commissionResEntity.setRechargeFee(groundPlanConfEntity.getRegisterFeeBefore());
            commissionResEntity.setVaildRegisterFlag(true);
        }
        // 后 xx名有效地推用户：每个有效地推的注册用户XX 元,且该用户的每笔消费按实付金额的XX%计为地推订单收入
        else if (NomalUtil.AgreaterThanB(commissionReqEntity.getVaildRegister(),
                groundPlanConfEntity.getRegisterAmountAfter()))
        {
            BigDecimal orderFee = commissionReqEntity.getOrderMoney()
                    .multiply(groundPlanConfEntity.getEachOrderPiecePercentAfter());
            commissionResEntity.setOrderFee(orderFee);
            commissionResEntity.setRechargeFee(groundPlanConfEntity.getRegisterFeeBefore());
            commissionResEntity.setVaildRegisterFlag(true);
        }
        commissionResEntity.setVaildRechargeFlag(false);

        return commissionResEntity;
    }

}
