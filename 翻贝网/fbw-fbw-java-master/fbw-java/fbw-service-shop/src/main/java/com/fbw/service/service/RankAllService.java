package com.fbw.service.service;

import java.util.List;

import com.fbw.service.entity.shop.RankFatherBusinEntity;
import com.fbw.service.entity.shop.RankSonBusinEntity;

/**
 * 
 * <功能详细描述>榜单(父/子)Service
 * @author jiangruliang
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RankAllService
{
    /**
     * 
     * <功能详细描述>获取固定榜单banner
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<RankFatherBusinEntity> getRankFather(Integer cityId);

    /**
     * 
     * <功能详细描述>根据所属榜单获取对应榜单列表
     * @param belongedRankId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<RankSonBusinEntity> getShopByRankid(Integer belongedRankId);

    /**
     * 根据shopId获取所属榜单以及排名 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    RankSonBusinEntity getRankByshopId(Integer shopId);

}
