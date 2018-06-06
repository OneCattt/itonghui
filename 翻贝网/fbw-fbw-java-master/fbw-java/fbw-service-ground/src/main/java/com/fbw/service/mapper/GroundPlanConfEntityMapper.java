package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.ground.GroundPlanConfEntity;

/**
 * 地推方案配置 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface GroundPlanConfEntityMapper
{
    /**
     * 通过cityId查询出所有 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundPlanConfEntity> selectByCityId(String param1);

    /**
     * 通过地推方案id查询所有对应数据 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    GroundPlanConfEntity selectByGroundId(Integer param1);

}