package com.fbw.service.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserBalanceDetailEntity;

/**
 * 用户余额明细Mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserBalanceDetailMapper
{
    /**
     * 存储余额明细 <功能详细描述>
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertSelective(UserBalanceDetailEntity record);

    /**
     * 根据用户id查询所有余额明细 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserBalanceDetailEntity> selectByUserId(Integer param1, Integer param2);

}