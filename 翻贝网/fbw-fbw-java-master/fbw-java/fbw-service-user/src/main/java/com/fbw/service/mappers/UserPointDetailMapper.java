package com.fbw.service.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserPointDetailEntity;

/**
 * 用户积分详情mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserPointDetailMapper
{
    /**
     * 存储 <功能详细描述>
     * @param userPointDetail
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertSelective(UserPointDetailEntity userPointDetail);

}