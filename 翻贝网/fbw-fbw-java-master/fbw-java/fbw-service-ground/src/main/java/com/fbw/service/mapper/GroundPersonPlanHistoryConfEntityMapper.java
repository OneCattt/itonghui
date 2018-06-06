package com.fbw.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.ground.GroundPersonPlanHistoryConfEntity;

/**
 * 个人地推历史配置Mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface GroundPersonPlanHistoryConfEntityMapper
{
    /**
     * 通过salesmanId查询该地推人员所有历史配置地推方案 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundPersonPlanHistoryConfEntity> selectBySalesmanId(String param1);

    /**
     * 存储用户所选择地推方案 <功能详细描述>
     * @param groundPersonPlanHistoryConfEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int saveGroundPersonPlan(GroundPersonPlanHistoryConfEntity groundPersonPlanHistoryConfEntity);

    /**
     * 
     * <功能详细描述> 通过地推人员ID查询当前最新个人地推配置表
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    GroundPersonPlanHistoryConfEntity queryGroundPersonPlanHistoryBySalesId(String param1);

    /**
     * 更新最新地推方案截止时间 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateLatestInvalidDate(Integer id);

    /**
     * 更新地推收入 <功能详细描述>
     * @param param1:id
     * @param param2:ground_fee
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateGroundFee(Map<String, Object> map);

    /**
     * 查询每个方案的充值数，订单数，订单实付，注册用户数，收入等 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    Map<String, Object> selectAllByGroundId(Map<String, Object> map);

}