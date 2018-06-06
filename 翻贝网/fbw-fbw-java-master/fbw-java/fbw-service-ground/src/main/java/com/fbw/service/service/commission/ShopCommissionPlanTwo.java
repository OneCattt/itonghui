package com.fbw.service.service.commission;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.fbw.service.context.AbstractCommissionSchemeSuper;
import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundShopPlanConfEntity;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 期限3年、前1000名注册用户10元／个，1000名以后注册用户5元／个；地推订单：按订单实付金额的1%计为地推订单的收益
 * @author JACK HUANG
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Service
public class ShopCommissionPlanTwo extends AbstractCommissionSchemeSuper
{

    @Override
    public CommissionResEntity getCommissionFee(CommissionReqEntity commissionReqEntity)
    {
        CommissionResEntity commissionResEntity = new CommissionResEntity();
        // 获取商铺地推方案规则
        GroundShopPlanConfEntity groundShopPlanConfEntity = getShopPlanRule(commissionReqEntity);
        // 前 xx名有效地推用户：每个有效地推的注册用户XX 元,且该用户的每笔消费按实付金额的XX%计为地推订单收入
        if (NomalUtil.AgreaterThanB(commissionReqEntity.getVaildRegister(),
                groundShopPlanConfEntity.getLimitHowManyUser()))
        {
            commissionResEntity.setRechargeFee(groundShopPlanConfEntity.getBeforeEveryUserAmount());
        }
        // 后 xx名有效地推用户：每个有效地推的注册用户XX 元,且该用户的每笔消费按实付金额的XX%计为地推订单收入
        else if (NomalUtil.AgreaterThanB(commissionReqEntity.getVaildRegister(),
                groundShopPlanConfEntity.getAfterEveryUserAmount()))
        {
            BigDecimal orderFee = commissionReqEntity.getOrderMoney()
                    .multiply(groundShopPlanConfEntity.getOrderActualPayPercent());
            commissionResEntity.setOrderFee(orderFee);
            commissionResEntity.setRechargeFee(groundShopPlanConfEntity.getBeforeEveryUserAmount());
            commissionResEntity.setVaildRegisterFlag(true);
        }
        return commissionResEntity;
    }

}
