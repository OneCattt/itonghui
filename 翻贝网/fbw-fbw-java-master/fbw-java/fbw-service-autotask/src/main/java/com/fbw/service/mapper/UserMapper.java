package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;

/**
 * 用户Mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年10月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserMapper
{
    /**
     * 获取7天后未评价订单 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserOrderInfoBusinEntity> selectSevenDayNocommentOrder();

    /**
     * 插入用户评论 <功能详细描述>
     * @param userCommentBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserComment(UserCommentBusinEntity userCommentBusinEntity);

    /**
     * 获取某一订单详细信息 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserOrderInfoBusinEntity getUserOrderInfo(String param1, String param2);

    /**
     * 根据订单号更新评论状态 <功能详细描述>
     * @param orderNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateCommentStatus(String orderNumber);

    UserInfoBusinEntity selectByPrimaryKey(Integer id);

    /**
     * 红包自动过期 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    int autoRedPacketOutDate();

}
