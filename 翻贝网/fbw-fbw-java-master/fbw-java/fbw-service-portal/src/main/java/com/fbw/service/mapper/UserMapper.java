package com.fbw.service.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserOrderInfoBusinEntity;

@Mapper
public interface UserMapper
{

    /**
     * 保存用户订单信息 <功能详细描述>
     * @param userOrderInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int saveUserOrderInfo(UserOrderInfoBusinEntity userOrderInfoBusinEntity);

    /**
     * 根据手机号更新用户余额 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateNewBalanceByMobileBalance(String param1, BigDecimal param2, BigDecimal param3);

    /**
     * 更新红包状态 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateRedPacketStatus(Integer id);
}