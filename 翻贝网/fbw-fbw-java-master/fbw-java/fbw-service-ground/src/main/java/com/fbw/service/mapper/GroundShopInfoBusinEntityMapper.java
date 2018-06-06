package com.fbw.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;

/**
 * 地推商户信息Mapper<功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface GroundShopInfoBusinEntityMapper
{
    /**
     * 更新商家地推注册信息 <功能详细描述>
     * @param groundShopInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopGroundRegInfo(GroundShopInfoBusinEntity groundShopInfoBusinEntity);

    /**
     * 更新商家地推有效注册信息 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Update("UPDATE t_ground_shop_info_busin SET validate_register_amount=#{param3} WHERE shop_id=#{param1} and validate_register_amount=#{param2}")
    int updateShopGroundWithVaildRegAndRegInfo(int param1, int param2, int param3);

    /**
     * 通过地推ID查询商户地推信息 <功能详细描述>
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Select("SELECT validate_register_amount as validateRegisterAmount,id,city_id as cityId,shop_id as shopId,shop_ground_type as shopGroundType,register_shop_account as registerShopAccount,shop_address as shopAddress,shop_name as shopName,shop_salesman_id as shopSalesmanId,shop_salesman_name as shopSalesmanName,shop_qr_code as shopQrCode,register_amount as registerAmount,ground_total_fee as groundTotalFee,vaild_order_amount as vaildOrderAmount,ground_withdrawals_fee as groundWithdrawalsFee,account_balance as accountBalance,status,created_date as createdDate,contract_start_date as contractStartDate,contract_end_date as contractEndDate from t_ground_shop_info_busin WHERE shop_salesman_id=#{salesManId} or shop_qr_code=#{salesManId}")
    GroundShopInfoBusinEntity queryShopGroundBySalesManId(@Param("salesManId") int salesManId);

    /**
     * 通过商户ID查询商户地推信息 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    GroundShopInfoBusinEntity queryShopGroundByShopId(int shopId);

    /**
     * 查询是否存在地推商家代码 <功能详细描述>
     * @param shopCode
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectIsExistShopCode(String shopCode);

    /**
     * 保存商家地推商家信息 <功能详细描述>
     * @param groundShopInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int saveGroundShopInfo(GroundShopInfoBusinEntity groundShopInfoBusinEntity);

    /**
     * 通过cityId查询该城市所有地推商家信息 <功能详细描述>
     * @param param1:cityId,param2:registerShopAccount
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<GroundShopInfoBusinEntity> selectByCityId(String param1, String param2);

    /**
     * 更新地推商户状态 <功能详细描述>
     * @param param1:shopId
     * @param param2:status
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateGroundShopStatus(String param1, String param2);

}