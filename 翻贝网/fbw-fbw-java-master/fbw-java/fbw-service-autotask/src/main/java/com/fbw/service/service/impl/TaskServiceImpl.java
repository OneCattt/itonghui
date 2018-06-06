package com.fbw.service.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.user.UserCollectBusinEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.ShopsInfoMapper;
import com.fbw.service.mapper.ShopsSpuriousNumberMapper;
import com.fbw.service.mapper.UserCollectBusinEntityMapper;
import com.fbw.service.mapper.UserMapper;
import com.fbw.service.service.TaskService;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

/**
 * <功能详细描述>商户信息service实现
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class TaskServiceImpl implements TaskService
{

    @Autowired
    private ShopsInfoMapper shopsInfoMapper;

    @Autowired
    private GetCacheUtil getCacheUtil;

    @Autowired
    private ShopsSpuriousNumberMapper shopsSpuriousNumberMapper;

    @Autowired
    private UserCollectBusinEntityMapper userCollectBusinEntityMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 
     * <功能详细描述>每天凌晨更新商家是否是翻倍日
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = Exception.class)
    public String updateShopsIsEvent()
    {
        System.out.println("updateShopsIsEvent begin: " + new Date());
        // 全部状态改为非翻倍日
        shopsInfoMapper.updateShopsIsEventAll();
        // 修改当日为翻倍日的商家
        shopsInfoMapper.updateShopsIsEvent();
        System.out.println("updateShopsIsEvent end: " + new Date());
        return "SUCCESS";
    }

    /**
     * 
     * <功能详细描述>每天凌晨更新商家新店状态
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = Exception.class)
    public String updateShopsIsNew()
    {
        System.out.println("updateShopsIsNew begin: " + new Date());
        // 全部状态改为旧店
        shopsInfoMapper.updateShopsIsNewAll();
        // 30天内上线的改为新店
        shopsInfoMapper.updateShopsIsNew();
        System.out.println("updateShopsIsNew end: " + new Date());
        return "SUCCESS";
    }

    /**
     * 
     * <功能详细描述>每天凌晨更新商家收藏量
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = Exception.class)
    public String updateShopsCollect()
    {
        System.out.println(new Date());
        List<UserCollectBusinEntity> allShopCollectNumber = userCollectBusinEntityMapper.getAllShopCollectNumber();
        if (NomalUtil.isNullOrEmpty(allShopCollectNumber))
        {
            return "FAILED";
        }
        for (UserCollectBusinEntity userCollectBusinEntity : allShopCollectNumber)
        {
            // 假数据
            int spuriousCollectNum = shopsSpuriousNumberMapper
                    .getShopsSpuriousCollectNum(userCollectBusinEntity.getShopId());
            shopsInfoMapper.updateShopsCollectByShopId(userCollectBusinEntity.getShopId(),
                    userCollectBusinEntity.getCollectNumber() + spuriousCollectNum);
        }
        System.out.println(new Date());
        return "SUCCESS";
    }

    /**
     * 
     * <功能详细描述>每天凌晨更新商家浏览量
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = Exception.class)
    public String updateShopsBrowse()
    {
        System.out.println("updateShopsBrowse begin :" + new Date());
        List<ShopInfoEntity> shopsBrowseNumList = shopsInfoMapper.getShopsBrowseNum();
        if (NomalUtil.isNullOrEmpty(shopsBrowseNumList))
        {
            return "FAILED";
        }
        if (!NomalUtil.isNullOrEmpty(shopsBrowseNumList))
        {
            for (ShopInfoEntity shopsInfoEntity : shopsBrowseNumList)
            {
                int browseNum = getCacheUtil.getBrowseNum(shopsInfoEntity.getShopId());
                if (browseNum <= shopsInfoEntity.getBrowseNum())
                {
                    continue;
                }
                shopsInfoMapper.updateShopsBrowseByShopId(shopsInfoEntity.getShopId(), browseNum);
            }
        }
        System.out.println("updateShopsBrowse end :" + new Date());
        return "SUCCESS";
    }

    /**
     * 
     * <功能详细描述>每天凌晨更新商家浏览量
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = Exception.class)
    public String updateInterviewBrowse()
    {
        System.out.println("updateShopsBrowse begin :" + new Date());
        List<ShopInfoEntity> shopsBrowseNumList = shopsInfoMapper.getShopsBrowseNum();
        if (NomalUtil.isNullOrEmpty(shopsBrowseNumList))
        {
            return "FAILED";
        }
        if (!NomalUtil.isNullOrEmpty(shopsBrowseNumList))
        {
            for (ShopInfoEntity shopsInfoEntity : shopsBrowseNumList)
            {
                int browseNum = getCacheUtil.getBrowseNum(shopsInfoEntity.getShopId());
                if (browseNum <= shopsInfoEntity.getBrowseNum())
                {
                    continue;
                }
                shopsInfoMapper.updateShopsBrowseByShopId(shopsInfoEntity.getShopId(), browseNum);
            }
        }
        System.out.println("updateShopsBrowse end :" + new Date());
        return "SUCCESS";
    }

    /**
     * 
     * <功能详细描述>每天更新订单数量
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = Exception.class)

    public String updateShopsOrderNum()
    {
        // TODO Auto-generated method stub
        System.out.println("updateShopsComment begin :" + new Date());
        List<UserOrderInfoBusinEntity> shopOrderNumberList = userCollectBusinEntityMapper.getShopOrderNumber();
        if (NomalUtil.isNullOrEmpty(shopOrderNumberList))
        {
            return "FAILED";
        }
        if (!NomalUtil.isNullOrEmpty(shopOrderNumberList))
        {
            for (UserOrderInfoBusinEntity entity : shopOrderNumberList)
            {
                shopsInfoMapper.updateShopsOrderNum(entity.getShopId(), entity.getEveryShopOrder());
            }
        }
        System.out.println("updateShopsComment end: " + new Date());
        return "SUCCESS";
    }

    /**
     * 
     * <功能详细描述>每天更新评价信息（评价数量、评价平均分、评价总分）
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 20, rollbackFor = Exception.class)
    public String updateShopsComment()
    {
        // TODO Auto-generated method stub
        System.out.println("updateShopsComment begin :" + new Date());
        List<UserCommentBusinEntity> shopScoreAvgScoreCommentNum = userCollectBusinEntityMapper
                .getShopScoreAvgScoreCommentNum();
        if (NomalUtil.isNullOrEmpty(shopScoreAvgScoreCommentNum))
        {
            return "FAILED";
        }
        if (!NomalUtil.isNullOrEmpty(shopScoreAvgScoreCommentNum))
        {
            for (UserCommentBusinEntity entity : shopScoreAvgScoreCommentNum)
            {
                shopsInfoMapper.updateShopsComment(entity);
            }
        }
        System.out.println("updateShopsComment end: " + new Date());
        return "SUCCESS";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, timeout = 2000, rollbackFor = InnerException.class)
    public void autoInsertUserComment(String orderNumber, Integer userId) throws InnerException
    {
        UserOrderInfoBusinEntity userOrderInfo = userMapper.getUserOrderInfo(orderNumber, "0");
        if (NomalUtil.isNullOrEmpty(userOrderInfo))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_ORDER_DETAIL_ERROR);
        }
        int flag = userMapper.updateCommentStatus(orderNumber);
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
        userInfo = userMapper.selectByPrimaryKey(userId);
        userComment.setUserAvatar(userInfo.getAvatar());
        userComment.setNickName(userInfo.getNickName());
        userComment.setContent("默认好评！");
        int flag1 = userMapper.insertUserComment(userComment);
        if (1 != flag1)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_USER_COMMENT_DB_ERROR);
        }
    }

}
