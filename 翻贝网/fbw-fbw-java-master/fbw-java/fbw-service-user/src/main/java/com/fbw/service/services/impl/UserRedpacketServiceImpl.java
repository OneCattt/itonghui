package com.fbw.service.services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fbw.service.entity.user.UserRedPacketBusinEntity;
import com.fbw.service.mappers.UserRedPacketBusinEntityMapper;
import com.fbw.service.services.UserRedPacketBusinService;

@Service
public class UserRedpacketServiceImpl implements UserRedPacketBusinService
{
    @Resource
    private UserRedPacketBusinEntityMapper userRedPacketBusinEntityMapper;

    @Override
    public UserRedPacketBusinEntity getRedPacketWithBig(Map<String, Object> map)
    {
        return userRedPacketBusinEntityMapper.getRedPacketWithBig(map);
    }

    @Override
    public int updateRedPacketStatus(Integer id)
    {
        return userRedPacketBusinEntityMapper.updateRedPacketStatus(id);
    }

    @Override
    public List<UserRedPacketBusinEntity> selectRedPacketListById(Integer userId, Integer begin)
    {
        return userRedPacketBusinEntityMapper.selectRedPacketListById(userId, begin);
    }

    @Override
    public List<UserRedPacketBusinEntity> selectUsedRedPacketListById(Integer userId, Integer begin)
    {
        return userRedPacketBusinEntityMapper.selectUsedRedPacketListById(userId, begin);
    }

}
