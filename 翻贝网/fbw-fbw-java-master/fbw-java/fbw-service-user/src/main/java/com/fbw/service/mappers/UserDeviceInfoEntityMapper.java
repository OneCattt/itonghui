package com.fbw.service.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserDeviceInfoEntity;

/**
 * 用户设备信息实体类 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月26日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserDeviceInfoEntityMapper
{

    int insertSelective(UserDeviceInfoEntity record);

    UserDeviceInfoEntity selectByPrimaryKey(UserDeviceInfoEntity record);

    int updateByPrimaryKeySelective(UserDeviceInfoEntity record);

}