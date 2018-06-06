package com.fbw.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbw.service.entity.recharge.RechargeLogEntity;

/**
 * 
 * <功能详细描述> 充值日志JPA
 * @author JACK HUANG
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository
public interface RechargeLogRepository extends JpaRepository<RechargeLogEntity, Long>
{

}
