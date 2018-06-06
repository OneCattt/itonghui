package com.fbw.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbw.service.entity.recharge.RechargeEntity;

/**
 * 
 * <功能详细描述> 充值JPA类
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository
public interface RechargeRepository extends JpaRepository<RechargeEntity, Long>
{

    List<RechargeEntity> findByOrderNum(String orderNum);
}