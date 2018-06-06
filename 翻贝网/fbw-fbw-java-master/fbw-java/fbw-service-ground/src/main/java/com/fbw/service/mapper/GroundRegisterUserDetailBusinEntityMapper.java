package com.fbw.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.ground.GroundRegisterUserDetailBusinEntity;

/**
 * 地推注册用户详细信息Mapper <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface GroundRegisterUserDetailBusinEntityMapper
{
    /**
     * 保存注册用户详细地推信息 <功能详细描述>
     * @param groundRegisterUserDetailBusin
     * @return
     * @see [类、类#方法、类#成员]
     */
    int saveRegisterUserDetailWithGround(GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusin);

    /**
     * 查询当前城市地推订单实付、订单数 <功能详细描述>
     * @param cityId,salesmanId
     * @return
     * @see [类、类#方法、类#成员]
     */
    GroundRegisterUserDetailBusinEntity selectCount(String param1, String param2);

    /**
     * 查询是否存在手机号 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectIsExistMobile(String registerMobile);

    /**
     * 根据地推方案id查询出所属所有地推用户 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundRegisterUserDetailBusinEntity> selectAllByGroundId(Map<String, Object> map);

    /**
     * 查询该商家每一天注册用户、有效注册用户、订单数、地推订单数、地推订单实付数 <功能详细描述>
     * @param param1:shop_id
     * @param param2:shop_assistant_id
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Map<String, Object>> selectAllByShopIdAndShopAssistantId(String param1, String param2);

    /**
     * 查询该商家当前日期所有注册用户 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundRegisterUserDetailBusinEntity> selectUserByShopIdAndDate(String param1, String param2, String param3,
            String param4);

    /**
     * 通过手机号更新地推订单信息 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateRegisterUserOrder(GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusinEntity);

    /**
     * 通过手机号获取地推方案id <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    GroundRegisterUserDetailBusinEntity selectByRegisterMobile(String mobile);

}