package com.fbw.service.context;

import org.springframework.beans.factory.annotation.Autowired;

import com.fbw.service.entity.CommissionReqEntity;
import com.fbw.service.entity.CommissionResEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.entity.ground.GroundShopPlanConfEntity;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.GroundShopPlanConfEntityMapper;
import com.fbw.service.service.GrounPlanConfService;

/**
 * 
 * <功能详细描述> 抽象佣金方案父类
 * @author JACK HUANG
 * @version [版本号, 2017年9月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractCommissionSchemeSuper
{

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    GrounPlanConfService grounPlanConfService;

    @Autowired
    GroundShopPlanConfEntityMapper groundShopPlanConfEntityMapper;

    /**
     * 
     * <功能详细描述>获取佣金费用
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract CommissionResEntity getCommissionFee(CommissionReqEntity commissionReqEntity);

    /**
     * 
     * <功能详细描述> 获取个人地推方案规则
     * @param commissionReqEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public GroundPlanConfEntity getPersonalPlanRule(CommissionReqEntity commissionReqEntity)
    {
        return grounPlanConfService.selectByGroundId(Integer.parseInt(commissionReqEntity.getGroundId()));

    }

    /**
     * 
     * <功能详细描述> 获取商铺地推方案规则
     * @param commissionReqEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public GroundShopPlanConfEntity getShopPlanRule(CommissionReqEntity commissionReqEntity)
    {
        return groundShopPlanConfEntityMapper.selectById(Integer.parseInt(commissionReqEntity.getGroundId()));

    }

}
