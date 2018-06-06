package com.fbw.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;

/**
 * 个人地推信息 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface GroundPersonalInfoBusinMapper
{
    /**
     * 查询地推列表 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundPersonalInfoBusinEntity> selectByCityId(Map<String, Object> map);

    /**
     * 查询地推列表总数 <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectCountByCityId(Map<String, Object> map);

    /**
     * 存储用户地推信息 <功能详细描述>
     * @param groundPersonalInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int saveGroundPersonalInfo(GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity);

    /**
     * 更新用户 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updatePersonalGroundStatus(String param1, String param2);

    /**
     * 更新用户地推二维码 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updatePersonalGroundQrCode(String param1, String param2);

    /**
     * 判断手机号是否是地推人员 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectIsExistMobile(String param1);

    /**
     * 更新个人地推注册信息 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updatePersonalGroundRegisterInfo(GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity);

    /**
     * 更新个人地推有效注册信息 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Update("UPDATE t_ground_personal_info_busin SET valid_register_amount=#{param3} WHERE (salesman_id=#{param1} or salesman_qr_code=#{param1}) and valid_register_amount=#{param2}")
    int updatePersonalGroundWithVaildRegAndRegInfo(String param1, int param2, int param3);

    /**
     * 通过salesmanId查询地推人员信息 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    GroundPersonalInfoBusinEntity queryPersonalGroundBySalesManId(String param1);

    /**
     * 通过salesmanId查询当前地推人员状态 <功能详细描述>
     * @param param1:salesmanId
     * @return
     * @see [类、类#方法、类#成员]
     */
    String selectPersonalGroundStatusBySalemanId(String param1);

    /**
     * 通过cityId查询个人地推数、注册用户数、地推订单数、充值数、地推收入 <功能详细描述>
     * @param cityId,mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    GroundPersonalInfoBusinEntity selectCount(String param1, String param2);

}