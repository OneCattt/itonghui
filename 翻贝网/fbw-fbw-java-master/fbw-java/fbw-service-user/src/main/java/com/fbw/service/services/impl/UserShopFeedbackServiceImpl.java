package com.fbw.service.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fbw.service.entity.user.UserShopFeedbackBusinEntity;
import com.fbw.service.mappers.UserShopFeedbackBusinEntityMapper;
import com.fbw.service.services.UserShopFeedbackService;

@Service
public class UserShopFeedbackServiceImpl implements UserShopFeedbackService
{
    @Resource
    private UserShopFeedbackBusinEntityMapper userShopFeedbackMapper;

    @Override
    public int insertUserFeedBack(UserShopFeedbackBusinEntity record)
    {
        return userShopFeedbackMapper.insertUserFeedBack(record);
    }

}
