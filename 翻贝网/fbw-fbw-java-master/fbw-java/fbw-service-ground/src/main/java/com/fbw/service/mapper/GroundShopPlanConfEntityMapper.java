package com.fbw.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.ground.GroundShopPlanConfEntity;

@Mapper
public interface GroundShopPlanConfEntityMapper
{
    GroundShopPlanConfEntity selectById(Integer id);

}