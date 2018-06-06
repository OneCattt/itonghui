package com.fbw.service.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fbw.service.entity.recharge.RechargeEntity;

/**
 * 
 * <功能详细描述> 充值
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface RechargeMapper
{

    @Update("update t_recharge_busin set recharge_status ='0' where recharge_status = '1' and order_number = #{orderNum} and actual_fee =#{actualMoney}")
    int updateRechargeStatus(RechargeEntity rechargeEntity);

    @Select("select recharge_status from t_recharge_busin where order_number = #{orderNumber} and actual_fee = #{actualMoney}")
    String getRechargeOrderRecord(@Param("orderNumber") String orderNumber,
            @Param("actualMoney") BigDecimal actualMoney);

    /**
     * 根据userId获取所有充值订单 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Select("SELECT order_number as id,created_date as date,double_fee AS money,2 AS recharge_or_order FROM(SELECT created_date,double_fee,order_number,DATEDIFF(NOW(),created_date) date from t_recharge_busin WHERE recharge_mobile=#{mobile}) t WHERE t.date<=90")
    List<Map<String, Object>> getRechargeListByUserId(@Param("mobile") String mobile);
}