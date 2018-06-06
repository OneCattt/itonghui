package com.fbw.service.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserConstant;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.entity.user.UserPointDetailEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mappers.UserCommentBusinEntityMapper;
import com.fbw.service.mappers.UserInfoBusinEntityMapper;
import com.fbw.service.mappers.UserOrderInfoBusinEntityMapper;
import com.fbw.service.services.UserCommentService;
import com.fbw.service.services.UserInfoBusinService;
import com.fbw.service.util.NomalUtil;

@Service
public class UserCommentServiceImpl implements UserCommentService
{
    @Resource
    private UserCommentBusinEntityMapper userCommentMapper;

    @Resource
    private UserInfoBusinEntityMapper userInfoBusinEntityMapper;

    @Autowired
    private UserInfoBusinService userInfoService;

    @Autowired
    private UserOrderInfoBusinEntityMapper userOrderInfoBusinEntityMapper;

    @Override
    public UserCommentBusinEntity getOneUserCommentInfo(String orderId)
    {
        return userCommentMapper.getOneUserCommentInfo(orderId);
    }

    @Override
    public UserCommentBusinEntity getOneRecentShopComment(Integer shopId)
    {
        return userCommentMapper.getOneRecentShopComment(shopId);
    }

    @Override
    public UserCommentBusinEntity getOneRecentShopCommentMore(Integer shopId)
    {
        return userCommentMapper.getOneRecentShopCommentMore(shopId);
    }

    @Override
    public int getOneShopCommentNum(Integer shopId)
    {
        return userCommentMapper.getOneShopCommentNum(shopId);
    }

    @Override
    public List<UserCommentBusinEntity> getOneShopAllComment(Map<String, Object> map)
    {
        return userCommentMapper.getOneShopAllComment(map);
    }

    @Override
    public List<UserCommentBusinEntity> getOneShopAllPicComment(Integer shopId, Integer begin)
    {
        return userCommentMapper.getOneShopAllPicComment(shopId, begin);
    }

    @Override
    public int getOneShopAllPicNum(Integer shopId)
    {
        return userCommentMapper.getOneShopAllPicNum(shopId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = InnerException.class)
    public boolean insertUserComment(UserCommentBusinEntity userCommentBusinEntity) throws InnerException
    {
        int flag = 0;
        int flag1 = 0;
        try
        {
            flag = userCommentMapper.insertUserComment(userCommentBusinEntity);
            flag1 = userOrderInfoBusinEntityMapper.updateCommentStatus(userCommentBusinEntity.getOrderNumber());
            UserPointDetailEntity userPointDetail = new UserPointDetailEntity();
            if (!NomalUtil.isNullOrEmpty(userCommentBusinEntity.getPicture())
                    && userCommentBusinEntity.getContent().length() > 10)
            {
                userPointDetail.setRemark(UserConstant.USER_PICTURE_AND_TEN_WORD_COMMENT);
            }
            if (NomalUtil.isNullOrEmpty(userCommentBusinEntity.getPicture())
                    && userCommentBusinEntity.getContent().length() > 10)
            {
                userPointDetail.setRemark(UserConstant.USER_COMMENT_TEN_WORD);
            }
            if (!NomalUtil.isNullOrEmpty(userCommentBusinEntity.getPicture())
                    && userCommentBusinEntity.getContent().length() < 10)
            {
                userPointDetail.setRemark(UserConstant.USER_PICTURE_COMMENT);
            }
            userPointDetail.setUserId(userCommentBusinEntity.getUserId());
            userInfoService.updateUserPoint(userPointDetail);
        }
        catch (Exception e)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_COMMENT_DB_ERROR, "评论失败！");
        }
        if (1 != flag)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_COMMENT_DB_ERROR, "评论失败！");
        }

        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_ORDER_COMMENT_STATUS_DB_ERROR, "更新订单表评论状态失效！");
        }
        return true;

    }

    @Override
    public UserCommentBusinEntity getOneShopAvgScore(Integer shopId)
    {
        return userCommentMapper.getOneShopAvgScore(shopId);
    }

    @Override
    public String getOneScoreLabel(Integer score)
    {
        return userCommentMapper.getOneScoreLabel(score);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 2000, rollbackFor = InnerException.class)
    public void autoInsertUserComment(String orderNumber, Integer userId) throws InnerException
    {
        UserOrderInfoBusinEntity userOrderInfo = userOrderInfoBusinEntityMapper.getUserOrderInfo(orderNumber, "0");
        if (NomalUtil.isNullOrEmpty(userOrderInfo))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_ORDER_DETAIL_ERROR);
        }
        int flag = userOrderInfoBusinEntityMapper.updateCommentStatus(orderNumber);
        if (1 != flag)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_ORDER_COMMENT_STATUS_DB_ERROR);
        }
        UserCommentBusinEntity userComment = new UserCommentBusinEntity();
        userComment.setUserId(userId);
        userComment.setOrderNumber(userOrderInfo.getOrderNumber());
        userComment.setShopId(userOrderInfo.getShopId());
        userComment.setScore(new BigDecimal(5.0));
        UserInfoBusinEntity userInfo = new UserInfoBusinEntity();
        userInfo = userInfoBusinEntityMapper.selectByPrimaryKey(userId);
        userComment.setUserAvatar(userInfo.getAvatar());
        userComment.setNickName(userInfo.getNickName());
        userComment.setContent("默认好评！");
        int flag1 = userCommentMapper.insertUserComment(userComment);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_COMMENT_DB_ERROR);
        }
    }

}
