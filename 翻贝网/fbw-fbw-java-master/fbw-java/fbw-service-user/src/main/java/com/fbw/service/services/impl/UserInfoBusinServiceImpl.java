package com.fbw.service.services.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.user.UserConstant;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserPointDetailEntity;
import com.fbw.service.enums.UserEnums;
import com.fbw.service.enums.UserTypeEnums;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mappers.UserGroundInfoBusinEntityMapper;
import com.fbw.service.mappers.UserInfoBusinEntityMapper;
import com.fbw.service.mappers.UserPointDetailMapper;
import com.fbw.service.services.UserInfoBusinService;
import com.fbw.service.util.NomalUtil;

@Service
public class UserInfoBusinServiceImpl implements UserInfoBusinService
{

    @Resource
    private UserInfoBusinEntityMapper userInfoBusinEntityMapper;

    @Resource
    private UserGroundInfoBusinEntityMapper userGroundInfoBusinEntityMapper;

    @Autowired
    private UserPointDetailMapper userPointDetailMapper;

    @Override
    public UserInfoBusinEntity selectByPrimaryKey(Integer id)
    {
        return userInfoBusinEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfoBusinEntity selectByMobile(String mobile)
    {
        return userInfoBusinEntityMapper.selectByMobile(mobile);
    }

    @Override
    public int insertSelective(UserInfoBusinEntity record) throws InnerException
    {
        return userInfoBusinEntityMapper.insertSelective(record);

    }

    @Override
    public int updateByPrimaryKeySelective(UserInfoBusinEntity record)
    {
        return userInfoBusinEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByMobileSelective(UserInfoBusinEntity record)
    {
        if (null != record.getMobile())
        {
            return userInfoBusinEntityMapper.updateByMobileSelective(record);
        }
        return 0;
    }

    @Override
    public String selectBalanceByMobile(String mobile)
    {
        return userInfoBusinEntityMapper.selectBalanceByMobile(mobile);
    }

    @Override
    public int updateNewBalanceByMobileBalance(String mobile, BigDecimal balance, BigDecimal newBalance)
    {
        return userInfoBusinEntityMapper.updateNewBalanceByMobileBalance(mobile, balance, newBalance);
    }

    @Override
    public List<UserInfoBusinEntity> selectAll()
    {
        return userInfoBusinEntityMapper.selectAll();
    }

    @Override
    public int selectPayPwdIsRight(UserInfoBusinEntity UserInfoBusinEntity)
    {
        return userInfoBusinEntityMapper.selectPayPwdIsRight(UserInfoBusinEntity);
    }

    @Override
    public int isSetPayPasswordRight(String mobile)
    {
        return userInfoBusinEntityMapper.isSetPayPasswordRight(mobile);
    }

    @Override
    public int selectRealNameIsTrue(UserInfoBusinEntity userInfoBusinEntity)
    {
        return userInfoBusinEntityMapper.selectRealNameIsTrue(userInfoBusinEntity);
    }

    @Override
    public int updateClosePayPassword(String mobile)
    {
        return userInfoBusinEntityMapper.updateClosePayPassword(mobile);
    }

    @Override
    public int insertUserGround(UserGroundInfoBusinEntity userGroundInfoBusinEntity)
    {
        return userGroundInfoBusinEntityMapper.insertUserGround(userGroundInfoBusinEntity);
    }

    @Override
    public int queryUserDeviceIdInfo(String deviceId)
    {
        return userInfoBusinEntityMapper.queryUserDeviceIdInfo(deviceId);
    }

    @Override
    public int selectIsExistMobile(String mobile)
    {
        return userInfoBusinEntityMapper.selectIsExistMobile(mobile);
    }

    @Override
    public UserGroundInfoBusinEntity queryUserGroundInfo(String mobile)
    {
        return userGroundInfoBusinEntityMapper.queryUserGroundInfo(mobile);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public void updateUserPoint(UserPointDetailEntity userPointDetail) throws InnerException
    {
        int pointChange = getPointChange(userPointDetail.getRemark(), userPointDetail.getOrderAmount());
        int type = getType(userPointDetail.getRemark());
        if (0 == pointChange)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_UPDATE_USER_POINT_BALANCE_FAIL);
        }
        userPointDetail.setPointChange(pointChange);
        int point = userInfoBusinEntityMapper.selectPointByUserId(userPointDetail.getUserId());
        if (type == 1)
        {
            userPointDetail.setAfterChangePoint(point + userPointDetail.getPointChange());
        }
        if (type == 2)
        {
            userPointDetail.setAfterChangePoint(point - userPointDetail.getPointChange());
        }
        if (0 > userPointDetail.getAfterChangePoint())
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_UPDATE_USER_POINT_BALANCE_FAIL);
        }
        int flag = userInfoBusinEntityMapper.updateUserPoint(userPointDetail.getPointChange(), String.valueOf(type),
                userPointDetail.getUserId());
        if (1 != flag)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_UPDATE_USER_POINT_FAIL);
        }
        userPointDetail.setType(type);
        int flag1 = userPointDetailMapper.insertSelective(userPointDetail);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_UPDATE_USER_POINT_DETAIL_FAIL);
        }
    }

    /**
     * 获取积分变动 <功能详细描述>
     * @param remark
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Integer getPointChange(String remark, String orderAmount)
    {
        int i = 0;
        if (!NomalUtil.isNullOrEmpty(orderAmount) && remark.equals(UserConstant.USER_CONSUME_TWO_YUAN))
        {
            double doubleorderAmount = Double.parseDouble(orderAmount);
            i = (int) (doubleorderAmount / UserEnums.getMsg(UserConstant.USER_CONSUME_TWO_YUAN));
        }
        if (remark.equals(UserConstant.USER_MORE_THAN_TEN_WORD))
        {
            return UserEnums.getMsg(UserConstant.USER_MORE_THAN_TEN_WORD);
        }
        if (remark.equals(UserConstant.USER_PIC_COMMENT))
        {
            return UserEnums.getMsg(UserConstant.USER_PIC_COMMENT);
        }
        if (remark.equals(UserConstant.USER_PIC_COMMENT_AND_TEN_WORD))
        {
            return UserEnums.getMsg(UserConstant.USER_PIC_COMMENT_AND_TEN_WORD);
        }
        return i;
    }

    /**
     * 获取type <功能详细描述>
     * @param remark
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Integer getType(String remark)
    {
        int i = 0;

        if (remark.equals(UserConstant.USER_CONSUME_ORDER))
        {
            return UserTypeEnums.getMsg(UserConstant.USER_CONSUME_ORDER);
        }
        if (remark.equals(UserConstant.USER_COMMENT_TEN_WORD))
        {
            return UserTypeEnums.getMsg(UserConstant.USER_COMMENT_TEN_WORD);
        }
        if (remark.equals(UserConstant.USER_PICTURE_COMMENT))
        {
            return UserTypeEnums.getMsg(UserConstant.USER_PICTURE_COMMENT);
        }
        if (remark.equals(UserConstant.USER_PICTURE_AND_TEN_WORD_COMMENT))
        {
            return UserTypeEnums.getMsg(UserConstant.USER_PICTURE_AND_TEN_WORD_COMMENT);
        }
        if (remark.equals(UserConstant.USER_VIP_CONSUME_ORDER_DOUBLE))
        {
            return UserTypeEnums.getMsg(UserConstant.USER_VIP_CONSUME_ORDER_DOUBLE);
        }
        if (remark.equals(UserConstant.USER_POINT_EXCHANGE_GOODS))
        {
            return UserTypeEnums.getMsg(UserConstant.USER_POINT_EXCHANGE_GOODS);
        }
        if (remark.equals(UserConstant.USER_ROTARY_LUCKY_DRAW))
        {
            return UserTypeEnums.getMsg(UserConstant.USER_ROTARY_LUCKY_DRAW);
        }
        return i;
    }

}
