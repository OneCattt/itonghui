package com.fbw.service.services;

import java.math.BigDecimal;
import java.util.List;

import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserPointDetailEntity;
import com.fbw.service.exception.InnerException;

/**
 * 
 * <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserInfoBusinService
{
    /**
     * 根据用户Id获取用户基本信息 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserInfoBusinEntity selectByPrimaryKey(Integer id);

    /**
     * 根据用户mobile获取用户基本信息 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserInfoBusinEntity selectByMobile(String mobile);

    /**
     * 插入用户基本信息 <功能详细描述>
     * @param record
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    int insertSelective(UserInfoBusinEntity record) throws InnerException;

    /**
     * 通过id更新用户基本信息 <功能详细描述>
     * @param record
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateByPrimaryKeySelective(UserInfoBusinEntity record);

    /**
     * <功能详细描述>通过mobile更新用户基本信息
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateByMobileSelective(UserInfoBusinEntity record);

    /**
     * 通过mobile获取余额 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    String selectBalanceByMobile(String mobile);

    /**
     * 通过用户余额和mobile更新用户余额 <功能详细描述>
     * @param mobile
     * @param balance
     * @param newBalance
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateNewBalanceByMobileBalance(String mobile, BigDecimal balance, BigDecimal newBalance);

    /**
     * 查询用户列表 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<UserInfoBusinEntity> selectAll();

    /**
     * 查询用户支付密码是否正确 <功能详细描述>
     * @param UserInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectPayPwdIsRight(UserInfoBusinEntity UserInfoBusinEntity);

    /**
     * 判断是否设置支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    int isSetPayPasswordRight(String mobile);

    /**
     * 查询用户输入身份证号是否正确 <功能详细描述>
     * @param userInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectRealNameIsTrue(UserInfoBusinEntity userInfoBusinEntity);

    /**
     * 关闭支付密码 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateClosePayPassword(String mobile);

    /**
     * 插入地推信息 <功能详细描述>
     * @param userGroundInfoBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int insertUserGround(UserGroundInfoBusinEntity userGroundInfoBusinEntity);

    /**
     * 查询所有用户地推信息 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    UserGroundInfoBusinEntity queryUserGroundInfo(String mobile);

    /**
     * 查询设备id号是否存在 <功能详细描述>
     * @param deviceId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int queryUserDeviceIdInfo(String deviceId);

    /**
     * 查询手机号是否存在 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    int selectIsExistMobile(String mobile);

    /**
     * 更新用户积分 <功能详细描述>
     * @param userPointDetail
     * @see [类、类#方法、类#成员]
     */
    void updateUserPoint(UserPointDetailEntity userPointDetail) throws InnerException;

}
