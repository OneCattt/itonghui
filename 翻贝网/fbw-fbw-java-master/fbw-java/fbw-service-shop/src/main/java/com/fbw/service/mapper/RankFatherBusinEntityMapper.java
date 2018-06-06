package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.shop.RankFatherBusinEntity;

/**
 * 父榜单mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface RankFatherBusinEntityMapper
{
    /**
     * 获取上方固定榜单栏 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<RankFatherBusinEntity> getRankFather(Integer param1);

    /**
     * 根据第一分类id和cityId查询榜单 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    RankFatherBusinEntity getRankByFirstAndCity(Integer param1, Integer param2);

}