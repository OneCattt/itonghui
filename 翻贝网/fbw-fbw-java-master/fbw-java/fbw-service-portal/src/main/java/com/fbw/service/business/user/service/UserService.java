package com.fbw.service.business.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.UserFeignClient;

/**
 * 用户Service <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserService
{
    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取用户基本信息list <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserInfoBusinEntity getUserBaseInfo(String mobile)
    {
        return userFeignClient.getUserBaseInfo(mobile);
    }

    /**
     * 保存用户基本信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean saveUserBaseInfo(UserInfoBusinEntity userInforBusinEntity)
    {
        return userFeignClient.saveUserBaseInfo(userInforBusinEntity);
    }

    /**
     * 通过用户id获取用户详细信息 <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserInfoBusinEntity getUserBaseInfoById(int id)
    {
        return userFeignClient.getUserBaseInfoById(id);
    }

    /**
     * 根据用户Id更新用户基本信息 <功能详细描述>
     * @param userInforBusinEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean updateUserBaseInfoById(UserInfoBusinEntity userInforBusinEntity)
    {
        return userFeignClient.updateUserBaseInfoById(userInforBusinEntity);
    }

    /**
     * 更新余额信息 <功能详细描述>
     * @param mobile
     * @param balance
     * @param newBalance
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean updateUserbalance(String mobile, String balance, String newBalance)
    {
        return userFeignClient.updateUserbalance(mobile, balance, newBalance);

    }

    /**
     * 根据mobile查询用户余额 <功能详细描述>
     * @param mobile
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public String getBalanceByMobile(String mobile) throws InnerException
    {
        return userFeignClient.getBalanceByMobile(mobile);

    }

    /**
     * 获取用户信息列表 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<UserInfoBusinEntity> getUserInfoList()
    {
        return userFeignClient.getUserInfoList();
    }

    /**
     * 用户实名认证 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public CommonRsEntity updateRealName(String realName, String IDCard, Integer id)
    {

        return userFeignClient.updateRealName(realName, IDCard, id);

    }

    /**
     * 验证身份信息 <功能详细描述>
     * @param realName
     * @param IDCard
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public CommonRsEntity selectRealNameIsTrue(String realName, String IDCard, Integer id)
    {
        return userFeignClient.selectRealNameIsTrue(realName, IDCard, id);
    }

    /**
     * 设置支付密码 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean updatePayPassword(String payPassword, String mobile)
    {

        return userFeignClient.updatePayPassword(payPassword, mobile);

    }

    /**
     * 判断支付密码是否正确 <功能详细描述>
     * @param payPassword
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean selectPayPwdIsRight(String payPassword, String mobile)
    {

        return userFeignClient.selectPayPwdIsRight(payPassword, mobile);

    }

    /**
     * 判断是否设置支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int isSetPayPasswordRight(String mobile)
    {
        return userFeignClient.isSetPayPasswordRight(mobile);

    }

    /**
     * 关闭支付密码 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean updateClosePayPassword(String mobile)
    {
        return userFeignClient.updateClosePayPassword(mobile);

    }

}
