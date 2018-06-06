package com.fbw.service.services;

import java.util.List;
import java.util.Map;

import com.fbw.service.entity.user.UserRedPacketBusinEntity;

/**
 * 用户红包Sevice <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserRedPacketBusinService
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
     * 获取用户可用红包列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserRedPacketBusinEntity> selectRedPacketListById(Integer userId, Integer begin);

    /**
     * 获取用户已使用、已过期红包列表 <功能详细描述>
     * @param userId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserRedPacketBusinEntity> selectUsedRedPacketListById(Integer userId, Integer begin);

}
