package com.fbw.service.business.user.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.user.service.UserCollectService;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.shop.InterviewParentEntity;
import com.fbw.service.entity.user.UserCollectBusinEntity;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.util.ReqUtils;

@RestController
public class UserCollectController extends BaseController
{
    @Autowired
    private UserCollectService userCollectService;

    @Autowired
    private ShopFeignClient shopFeignClient;

    /**
     * 获取当前用户所有收藏列表 <功能详细描述>
     * @param userId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userCollect/getUserCollectList", method = RequestMethod.GET)
    public Map<String, Object> getUserCollectList(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int begin = ReqUtils.getInt(request, "begin") * 20;
        List<UserCollectBusinEntity> shopsInfoEntities = new ArrayList<UserCollectBusinEntity>();
        try
        {
            shopsInfoEntities = userCollectService.getUserCollectList(userId, begin);

        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":userCollect getUserCollectList:" + "获取收藏店铺失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_DB_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("shopsInfoEntities", shopsInfoEntities);
        return successData(data);

    }

    /**
     * 
     * <功能详细描述>获取当前用户所有收藏探店
     * @param userid
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userCollect/getUserCollectInterviewShopList", method = RequestMethod.GET)
    public Map<String, Object> getUserCollectInterviewShopList(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int begin = ReqUtils.getInt(request, "begin") * 20;
        List<String> interviewId = null;
        List<InterviewParentEntity> interviewParents = new ArrayList<InterviewParentEntity>();
        try
        {
            interviewId = userCollectService.getUserCollectInterviewShopList(userId, begin);
            InterviewParentEntity interviewParentEntity = new InterviewParentEntity();
            for (String interview : interviewId)
            {
                interviewParentEntity = shopFeignClient.getInterviewByInterviewId(Integer.parseInt(interview), trackId);
                interviewParents.add(interviewParentEntity);
            }
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":userCollect getUserCollectInterviewShopList:" + "获取探店列表失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_DB_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("interviewParents", interviewParents);
        return successData(data);

    }

    /**
     * 删除或点击收藏的店铺 <功能详细描述>
     * @param userId
     * @param shopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userCollect/updateUserCollect", method = RequestMethod.POST)
    public Map<String, Object> updateUserCollect(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int shopId = ReqUtils.getInt(request, "shopId");
        int status = ReqUtils.getInt(request, "status");
        CommonRsEntity commonRsEntity = userCollectService.updateUserCollect(userId, shopId, status);
        if (commonRsEntity.isOperationFlag() == false)
        {
            getErrorLog(trackId + ":userCollect updateUserCollect:" + "收藏店铺失败！");
            return failedMessage(commonRsEntity.getErrorCode());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    /**
     * 用户点击取消或收藏探店 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userCollect/updateUserCollectInterviewShop", method = RequestMethod.POST)
    public Map<String, Object> updateUserCollectInterviewShop(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int userId = ReqUtils.getInt(request, "userId");
        int interviewShopId = ReqUtils.getInt(request, "interviewShopId");
        int status = ReqUtils.getInt(request, "status");
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity = userCollectService.updateUserCollectInterviewShop(userId, interviewShopId, status);
        if (commonRsEntity.isOperationFlag() == false)
        {
            getErrorLog(trackId + ":userCollect updateUserCollectInterviewShop:" + "收藏探店失败！");
            return failedMessage(commonRsEntity.getErrorCode());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserCollectController.class, errorMsg);
    }

}
