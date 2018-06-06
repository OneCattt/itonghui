package com.fbw.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.mapper.GroundPlanConfEntityMapper;

@Service
public class GrounPlanConfService
{
    @Autowired
    private GroundPlanConfEntityMapper GroundPlanConfEntityMapper;

    public List<GroundPlanConfEntity> selectByCityId(String cityId)
    {
        return GroundPlanConfEntityMapper.selectByCityId(cityId);
    }

    /**
     * 通过地推方案id查询所有对应数据 <功能详细描述>
     * @param groudId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public GroundPlanConfEntity selectByGroundId(Integer groudId)
    {
        return GroundPlanConfEntityMapper.selectByGroundId(groudId);
    }

}
