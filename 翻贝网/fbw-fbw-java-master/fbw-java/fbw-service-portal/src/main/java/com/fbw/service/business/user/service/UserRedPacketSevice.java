package com.fbw.service.business.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.user.UserRedPacketBusinEntity;
import com.fbw.service.feign.UserFeignClient;

/**
 * 
 * <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserRedPacketSevice
{
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取可用最大的红包 <功能详细描述>
     * @param userRedPacketBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserRedPacketBusinEntity getRedPacketWithBig(UserRedPacketBusinEntity userRedPacketBusinEntity)
    {
        return userFeignClient.getRedPacketWithBig(userRedPacketBusinEntity);
    }

    /**
     * 更新红包状态 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean updateRedPacketStatus(int id)
    {
        return userFeignClient.updateRedPacketStatus(id);
    }

    /**
     * 获取当前用户红包列表 <功能详细描述>
     * @param status
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserRedPacketBusinEntity> selectRedPacketListById(Integer userId, Integer begin)
    {
        return userFeignClient.selectRedPacketListById(userId, begin);

    }

    /**
     * 获取当前用户已过期、已使用红包列表 <功能详细描述>
     * @param status
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserRedPacketBusinEntity> selectUsedRedPacketListById(Integer userId, Integer begin)
    {
        return userFeignClient.selectUsedRedPacketListById(userId, begin);

    }

}
