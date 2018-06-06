package com.fbw.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.ground.GroundOrderDetailBusinEntity;

/**
 * 用户 订单/充值 详细信息Mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface GroundOrderDetailBusinEntityMapper
{
    /**
     * 通过用户手机号查询所有订单列表 <功能详细描述>
     * @param registerMobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundOrderDetailBusinEntity> selectGroundOrder(Map<String, Object> map);

    /**
     * 通过地推方案id查询所有订单列表 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundOrderDetailBusinEntity> selectGroundOrderBySalesmanId(Map<String, Object> map);

    /**
     * 通过地推方案id查询所有充值列表 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundOrderDetailBusinEntity> selectGroundRechargeBySalesmanId(Map<String, Object> map);

    /**
     * 存储订单/充值信息 <功能详细描述>
     * @param groundOrderDetailBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int savaGroundOrderDetail(GroundOrderDetailBusinEntity groundOrderDetailBusinEntity);

}