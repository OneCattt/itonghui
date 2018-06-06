package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;

/**
 * 
 * <功能详细描述>商户信息mapper
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface ShopsInfoMapper
{

    /**
     * 
     * <功能详细描述>所有商家装该改为不是翻倍日
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopsIsEventAll();

    /**
     * 
     * <功能详细描述>当日是翻倍日的更新
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopsIsEvent();

    /**
     * 
     * <功能详细描述>所有商家改为不是新店
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopsIsNewAll();

    /**
     * 
     * <功能详细描述>30天内上架的改为新店
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopsIsNew();

    /**
     * 
     * <功能详细描述>更新收藏量
     * @param shopId:param1
     * @param collectNum:param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopsCollectByShopId(int param1, int param2);

    /**
     * 
     * <功能详细描述>更新浏览量
     * @param shopId:param1
     * @param browseNum:param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopsBrowseByShopId(int param1, int param2);

    /**
     * 
     * <功能详细描述>每天更新订单数量
     * @param shopId:param1
     * @param orderNum:param2
     * @see [类、类#方法、类#成员]
     */
    int updateShopsOrderNum(int param1, int param2);

    /**
     * 
     * <功能详细描述>每天更新评价信息（评价数量、评价平均分、评价总分）
     * @see [类、类#方法、类#成员]
     */
    // int updateShopsComment();

    /**
     * 
     * <功能详细描述>查询商家浏览量
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getShopsBrowseNum();

    /**
     * 
     * <功能详细描述>更新评价信息（评价数量、评价平均分、评价总分）
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopsComment(UserCommentBusinEntity entity);

}
