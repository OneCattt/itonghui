package com.fbw.service.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserRedPacketBusinEntity;

/**
 * 用户红包Mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserRedPacketBusinEntityMapper
{
    /**
     * 获取可用最大红包 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserRedPacketBusinEntity getRedPacketWithBig(Map<String, Object> map);

    /**
     * 更新红包状态 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateRedPacketStatus(Integer id);

    /**
     * 获取当前用户可用红包列表 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserRedPacketBusinEntity> selectRedPacketListById(Integer param1, Integer param2);

    /**
     * 获取当前用户已过期/已使用红包列表 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserRedPacketBusinEntity> selectUsedRedPacketListById(Integer param1, Integer param2);

}