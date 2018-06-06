package com.fbw.service.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserGroundInfoBusinEntity;

@Mapper
public interface UserGroundInfoBusinEntityMapper
{
    /**
     * 插入地推信息 <功能详细描述>
     * @param userGroundPushBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserGround(UserGroundInfoBusinEntity userGroundInfoBusinEntity);

    /**
     * 查询所有用户地推信息 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserGroundInfoBusinEntity queryUserGroundInfo(String param1);

    /**
     * 查询该手机号是否是地推人员 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectIsGroundByMobile(String param1);

}