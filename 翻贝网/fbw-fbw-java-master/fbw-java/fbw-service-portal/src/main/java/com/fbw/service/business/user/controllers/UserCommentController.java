package com.fbw.service.business.user.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.user.service.UserCommentService;
import com.fbw.service.business.user.service.UserService;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.user.UserCommentBannerConfEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.ReqUtils;

@RestController
public class UserCommentController extends BaseController
{
    @Autowired
    private UserCommentService userCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取当前商店所有评论/带图评论,根据isNoPic判断是否带图片（0：带图片） <功能详细描述>
     * @param shopId
     * @param begin
     * @param isNoPic
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userComment/getOneShopCommentByBannerId", method = RequestMethod.GET)
    public Map<String, Object> getOneShopCommentByBannerId(HttpServletRequest request)
    {

        String trackId = String.valueOf(request.getAttribute("trackId"));
        int shopId = ReqUtils.getInt(request, "shopId");
        int id = ReqUtils.getInt(request, "commentBannerId");
        int begin = ReqUtils.getInt(request, "begin");

        List<UserCommentBusinEntity> userCommentList = null;
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            // 当begin=0时，获取获取当前商店的评论banner以及banner数量
            if (0 == begin)
            {
                List<UserCommentBannerConfEntity> userCommentBannerList = new ArrayList<UserCommentBannerConfEntity>();
                List<UserCommentBannerConfEntity> userCommentBanners = userFeignClient.getCommentBanner();
                for (UserCommentBannerConfEntity userCommentBann : userCommentBanners)
                {
                    userCommentBann.setShopId(shopId);
                    int commNum = userFeignClient.getCommentBannerNumber(userCommentBann);
                    userCommentBann.setCommNum(commNum);
                    userCommentBannerList.add(userCommentBann);
                }
                data.put("userCommentBannerList", userCommentBannerList);
            }
            UserCommentBannerConfEntity userCommentBanner = userFeignClient.getCommentBannerById(id);
            userCommentList = userCommentService.getOneShopAllComment(shopId, begin, userCommentBanner);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":userComment getOneShopAllComment:" + "获取评论失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_USER_GET_COMMENT_ERROR);
        }
        data.put("userCommentList", userCommentList);
        return successData(data);
    }

    /**
     * 插入用户评论 <功能详细描述>
     * @param userCommentBusinEntity
     * @param score,commentLabel,userId,content,picture,isNoName,orderNumber,shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/userComment/insertUserComment", method = RequestMethod.POST)
    public Map<String, Object> insertUserComment(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserInfoBusinEntity userInfoBusinEntity = null;
        UserCommentBusinEntity entity = new UserCommentBusinEntity();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            userInfoBusinEntity = userService.getUserBaseInfoById(entity.getUserId());
            entity.setUserAvatar(userInfoBusinEntity.getAvatar());
            entity.setNickName(userInfoBusinEntity.getNickName());
            entity.setTrackId(trackId);
            CommonRsEntity commonRsEntity = userCommentService.insertUserComment(entity);
            if (commonRsEntity.isOperationFlag() == false)
            {
                getErrorLog(trackId + ":user insertUserComment:" + "评价失败！");
                return failedMessage(commonRsEntity.getErrorCode());
            }
        }
        catch (IllegalAccessException e1)
        {

            getErrorLog(trackId + ":userComment insertUserComment:参数解析失败InvocationTargetException:"
                    + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_USER_COMMENT_DB_ERROR);
        }
        catch (InvocationTargetException e1)
        {
            getErrorLog(
                    trackId + ":userComment insertUserComment:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_USER_COMMENT_DB_ERROR);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        return successData(data);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserCommentController.class, errorMsg);

    }

}
