package com.fbw.service.mappers;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fbw.service.entity.user.UserInfoBusinEntity;

/**
 * 
 * <功能详细描述>用户信息Mapper
 * @author jiangruliang
 * @version [版本号, 2017年8月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Mapper
public interface UserInfoBusinEntityMapper
{

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoBusinEntity record);

    int insertSelective(UserInfoBusinEntity record);

    UserInfoBusinEntity selectByPrimaryKey(Integer id);

    UserInfoBusinEntity selectByMobile(String mobile);

    int updateByPrimaryKeySelective(UserInfoBusinEntity record);

    int updateByMobileSelective(UserInfoBusinEntity record);

    int updateByPrimaryKey(UserInfoBusinEntity record);

    int updateBalanceByMobile(BigDecimal param1, String param2);

    String selectBalanceByMobile(String param1);

    /**
     * 根据微信号查询用户信息 <功能详细描述>
     * @param openId
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserInfoBusinEntity selectByOpenId(String openId);

    /**
     * 根据手机号更新用户余额 <功能详细描述>
     * @param param1
     * @param param2
     * @param param3
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateNewBalanceByMobileBalance(String param1, BigDecimal param2, BigDecimal param3);

    /**
     * 查询用户列表 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserInfoBusinEntity> selectAll();

    /**
     * 查询用户支付密码是否正确 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectPayPwdIsRight(UserInfoBusinEntity UserInfoBusinEntity);

    /**
     * 判断是否设置手机号 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    int isSetPayPasswordRight(String param1);

    /**
     * 查询用户输入身份证号是否正确 <功能详细描述>
     * @param UserInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectRealNameIsTrue(UserInfoBusinEntity userInfoBusinEntity);

    /**
     * 关闭支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateClosePayPassword(String param1);

    /**
     * 查询设备id号是否存在 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int queryUserDeviceIdInfo(String param1);

    /**
     * 查询手机号是否存在 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectIsExistMobile(String param1);

    /**
     * 更新用户积分 <功能详细描述>
     * @param param1
     * @param param2
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateUserPoint(Integer param1, String param2, Integer param3);

    /**
     * 根据userId查询积分余额 <功能详细描述>
     * @param param1
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectPointByUserId(Integer param1);

    /**
     * 翻倍轨迹 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserInfoBusinEntity selectUserdoubleTrail(Integer id);
}