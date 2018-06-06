package com.fbw.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.user.UserCollectBusinEntity;
import com.fbw.service.mappers.UserCollectBusinEntityMapper;
import com.fbw.service.services.UserCollectService;

@Service
public class UserCollectServiceImpl implements UserCollectService
{

    @Autowired
    private UserCollectBusinEntityMapper userCollectMapper;

    @Override
    public int insertUserCollect(Integer userId, Integer shopId)
    {
        return userCollectMapper.insertUserCollect(userId, shopId);
    }

    @Override
    public List<UserCollectBusinEntity> getUserCollectList(Integer userId, int begin)
    {
        return userCollectMapper.getUserCollectList(userId, begin);
    }

    @Override
    public int updateUserCollect(Integer userId, Integer shopId, Integer status)
    {
        return userCollectMapper.updateUserCollect(userId, shopId, status);
    }

    @Override
    public int insertUserCollectInterviewShop(Integer userId, Integer interviewShopId)
    {
        return userCollectMapper.insertUserCollectInterviewShop(userId, interviewShopId);
    }

    @Override
    public List<String> getUserCollectInterviewShopList(Integer userId, int begin)
    {
        return userCollectMapper.getUserCollectInterviewShopList(userId, begin);
    }

    @Override
    public UserCollectBusinEntity getUserIsCollect(Integer userId, Integer shopId)
    {
        return userCollectMapper.getUserIsCollect(userId, shopId);
    }

    @Override
    public UserCollectBusinEntity getUserIsCollectInterviewShop(Integer userId, Integer interviewShopId)
    {
        return userCollectMapper.getUserIsCollectInterviewShop(userId, interviewShopId);
    }

    @Override
    public int updateUserCollectInterviewShop(Integer userId, Integer interviewShopId, Integer status)
    {
        return userCollectMapper.updateUserCollectInterviewShop(userId, interviewShopId, status);
    }

    @Override
    public int getCollectNumByShopId(Integer shopId)
    {
        return userCollectMapper.getCollectNumByShopId(shopId);
    }

    @Override
    public int getCollectInterviewNumByShopId(Integer interviewShopId)
    {
        return userCollectMapper.getCollectInterviewNumByShopId(interviewShopId);
    }

}
