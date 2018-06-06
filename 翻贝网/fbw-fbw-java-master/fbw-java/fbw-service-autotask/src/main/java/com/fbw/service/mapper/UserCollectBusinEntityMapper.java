package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserCollectBusinEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;

/**
 * 用户收藏mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserCollectBusinEntityMapper
{

    /**
     * <功能详细描述>查询所有店铺收藏量
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCollectBusinEntity> getAllShopCollectNumber();

    /**
     * 
     * <功能详细描述>评价信息（评价数量、评价平均分、评价总分）
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserCommentBusinEntity> getShopScoreAvgScoreCommentNum();

    /**
     * 订单量 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserOrderInfoBusinEntity> getShopOrderNumber();
}