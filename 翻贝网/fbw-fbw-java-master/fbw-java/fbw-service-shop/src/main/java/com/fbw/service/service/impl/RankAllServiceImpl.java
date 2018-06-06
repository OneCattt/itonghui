package com.fbw.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.shop.RankFatherBusinEntity;
import com.fbw.service.entity.shop.RankSonBusinEntity;
import com.fbw.service.mapper.RankFatherBusinEntityMapper;
import com.fbw.service.mapper.RankSonBusinEntityMapper;
import com.fbw.service.service.RankAllService;

@Service
public class RankAllServiceImpl implements RankAllService
{
    @Autowired
    private RankFatherBusinEntityMapper rankFatherBusinEntityMapper;

    @Autowired
    private RankSonBusinEntityMapper rankSonBusinEntityMapper;

    @Override
    public List<RankFatherBusinEntity> getRankFather(Integer cityId)
    {
        return rankFatherBusinEntityMapper.getRankFather(cityId);
    }

    @Override
    public List<RankSonBusinEntity> getShopByRankid(Integer belongedRankId)
    {
        return rankSonBusinEntityMapper.getShopByRankid(belongedRankId);
    }

    @Override
    public RankSonBusinEntity getRankByshopId(Integer shopId)
    {
        return rankSonBusinEntityMapper.getRankingByshopId(shopId);
    }

}
