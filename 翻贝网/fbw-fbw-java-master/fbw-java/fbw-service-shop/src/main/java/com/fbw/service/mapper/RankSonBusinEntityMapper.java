package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.RankSonBusinEntity;

/**
 * 子榜单mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface RankSonBusinEntityMapper
{
    /**
     * 
     * <功能详细描述>查询每个榜单下面的商店信息
     * @param belongedRankId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<RankSonBusinEntity> getShopByRankid(Integer param1);

    /**
     * 通过shopId获取它所属的榜单信息 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    RankSonBusinEntity getRankingByshopId(Integer param1);

}