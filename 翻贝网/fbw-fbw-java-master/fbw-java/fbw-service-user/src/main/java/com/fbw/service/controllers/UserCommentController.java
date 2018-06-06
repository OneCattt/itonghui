package com.fbw.service.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.user.UserCommentBannerConfEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mappers.UserCommentBusinEntityMapper;
import com.fbw.service.services.UserCommentService;

@RestController
@RequestMapping("/userComment")
public class UserCommentController extends BaseController
{
    @Autowired
    private UserCommentService userCommentService;

    @Autowired
    private UserCommentBusinEntityMapper userCommentBusinEntityMapper;

    /**
     * 获取当前用户某一个订单的评论 <功能详细描述>
     * @param orderId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getOneUserCommentInfo", method = RequestMethod.GET)
    public UserCommentBusinEntity getOneUserCommentInfo(String orderId) throws InnerException
    {
        UserCommentBusinEntity userCommentBusinEntity = userCommentService.getOneUserCommentInfo(orderId);
        return userCommentBusinEntity;

    }

    /**
     * 获取当前店铺最近最好的评价内容 <功能详细描述>
     * @param shopId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getOneRecentShopComment", method = RequestMethod.GET)
    public UserCommentBusinEntity getOneRecentShopComment(Integer shopId) throws InnerException
    {
        UserCommentBusinEntity userCommentBusinEntity = new UserCommentBusinEntity();
        userCommentBusinEntity = userCommentService.getOneRecentShopCommentMore(shopId);
        return userCommentBusinEntity;

    }

    /**
     * 获取当前商店的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getOneShopCommentNum", method = RequestMethod.GET)
    public Integer getOneShopCommentNum(Integer shopId) throws InnerException
    {
        return userCommentService.getOneShopCommentNum(shopId);

    }

    /**
     * 获取当前商店所有评论,根据isNoPic(0：纯图片)判断是否带图片 <功能详细描述>
     * @param shopId
     * @param begin
     * @param isNoPic
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getOneShopAllComment")
    public List<UserCommentBusinEntity> getOneShopAllComment(Integer shopId, Integer begin,
            @RequestBody UserCommentBannerConfEntity userCommentBanner)
    {
        begin = begin * 20;
        Map<String, Object> map = new HashMap<String, Object>();
        if (userCommentBanner.getIsPic() == 1)
        {
            map.put("sql", "and picture<>'' and picture is not null and score <= 5");
        }
        // 不带图 、 大于分数
        if (userCommentBanner.getIsPic() == 0)
        {
            map.put("sql",
                    "and score <= " + userCommentBanner.getScore() + " and score >" + userCommentBanner.getMinScore());
        }
        map.put("shopId", shopId);
        map.put("begin", begin);
        List<UserCommentBusinEntity> userCommentBusinEntities = new ArrayList<UserCommentBusinEntity>();
        userCommentBusinEntities = userCommentService.getOneShopAllComment(map);
        return userCommentBusinEntities;

    }

    /**
     * 获取当前商店带图片的评论总数 <功能详细描述>
     * @param shopId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getOneShopAllPicNum", method = RequestMethod.GET)
    public Integer getOneShopAllPicNum(Integer shopId)
    {
        return userCommentService.getOneShopAllPicNum(shopId);

    }

    /**
     * 插入用户评论 <功能详细描述>
     * @param userCommentBusinEntity
     * @param isNoName
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/insertUserComment", method = RequestMethod.POST)
    public CommonRsEntity insertUserComment(@RequestBody UserCommentBusinEntity userCommentBusinEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        if (userCommentBusinEntity.getIsNoName() == 1)
        {
            userCommentBusinEntity.setNickName("匿名用户");
            userCommentBusinEntity.setUserAvatar("匿名评论头像");
        }
        try
        {
            userCommentService.insertUserComment(userCommentBusinEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setOperationFlag(false);
        }

        return commonRsEntity;

    }

    /**
     * 插入用户评论 <功能详细描述>
     * @param userCommentBusinEntity
     * @param isNoName
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/autoInsertUserComment", method = RequestMethod.POST)
    public CommonRsEntity autoInsertUserComment(String orderNumber, Integer userId)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            userCommentService.autoInsertUserComment(orderNumber, userId);
        }
        catch (InnerException e)
        {
            commonRsEntity.setOperationFlag(false);
            commonRsEntity.setErrorCode(e.getErrorCode());
        }
        return commonRsEntity;

    }

    /**
     * 获取当前商店的平均评论分数/评论数量/最近最好评论（头像、昵称等）<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getOneShopInfoMainComment", method = RequestMethod.GET)
    public UserCommentBusinEntity getOneShopInfoMainComment(Integer shopId)
    {
        UserCommentBusinEntity userCommentBusinEntity = userCommentService.getOneRecentShopCommentMore(shopId);
        if (null == userCommentBusinEntity)
        {
            return null;
        }
        UserCommentBusinEntity userComment = userCommentService.getOneShopAvgScore(shopId);
        userCommentBusinEntity.setAvgCommScore(userComment.getAvgCommScore());
        userCommentBusinEntity.setCommentNum(userComment.getCommentNum());
        userCommentBusinEntity.setSumCommScore(userComment.getSumCommScore());
        return userCommentBusinEntity;
    }

    /**
     * 获取评价banner<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getCommentBanner")
    public List<UserCommentBannerConfEntity> getCommentBanner()
    {
        return userCommentBusinEntityMapper.getCommentBanner();
    }

    /**
     * 获取评价banner<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getCommentBannerById")
    public UserCommentBannerConfEntity getCommentBannerById(Integer id)
    {
        return userCommentBusinEntityMapper.getCommentBannerById(id);
    }

    /**
     * 获取评价banner数量<功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getCommentBannerNumber")
    public int getCommentBannerNumber(@RequestBody UserCommentBannerConfEntity userCommentBanner)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userCommentBanner.getIsPic() == 1)
        {
            map.put("sql", "and picture<>'' and picture is not null and score <= 5");
        }
        // 不带图 、 大于分数
        if (userCommentBanner.getIsPic() == 0)
        {
            map.put("sql",
                    "and score <= " + userCommentBanner.getScore() + " and score >" + userCommentBanner.getMinScore());
        }
        map.put("shopId", userCommentBanner.getShopId());
        return userCommentBusinEntityMapper.getCommentBannerNumber(map);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserCommentController.class, errorMsg);

    }

}
