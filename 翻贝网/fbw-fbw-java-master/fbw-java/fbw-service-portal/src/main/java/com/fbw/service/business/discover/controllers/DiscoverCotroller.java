package com.fbw.service.business.discover.controllers;

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
import com.fbw.service.business.discover.service.DiscoverService;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.shop.InterviewParentEntity;
import com.fbw.service.entity.shop.RankSonBusinEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.ReqUtils;

/**
 * 
 * <功能详细描述>发现controller
 * @author FBW0115
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class DiscoverCotroller extends BaseController
{
    @Autowired
    private GetCacheUtil getCacheUtil;

    @Autowired
    private DiscoverService discoverService;

    @Autowired
    private ShopFeignClient shopFeignClient;

    /**
     * 获取发现 <功能详细描述>
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/discover/getDisCover", method = RequestMethod.GET)
    public Map<String, Object> getDisCover(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int cityId = ReqUtils.getInt(request, "cityId");
        int page = ReqUtils.getInt(request, "page");
        Map<String, Object> map = new HashMap<String, Object>();
        try
        {
            map = discoverService.getDiscover(cityId, page);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":discover getDisCover:" + "获取发现失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }
        return successData(map);
    }

    /**
     * 下拉刷新，根据城市id查询探店 <功能详细描述>
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/discover/getInterviewByCityId", method = RequestMethod.GET)
    public Map<String, Object> getInterviewByCityId(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int cityId = ReqUtils.getInt(request, "cityId");
        int page = ReqUtils.getInt(request, "page");
        Map<String, Object> map = new HashMap<String, Object>();
        try
        {
            List<InterviewParentEntity> interviewByCityId = discoverService.getInterviewByCityId(cityId, page);
            map.put("interviewByCityId", interviewByCityId);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":discover getInterviewByCityId:" + "获取发现探店失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }
        return successData(map);
    }

    /**
     * 
     * <功能详细描述>根据探店id查询探店信息和子类商户
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/discover/getInterviewByInterviewId", method = RequestMethod.GET)
    public Map<String, Object> getInterviewByInterviewId(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int interviewId = ReqUtils.getInt(request, "interviewId");
        int userId = ReqUtils.getInt(request, "userId");
        InterviewParentEntity interviewEntity = discoverService.getInterviewByInterviewId(interviewId, userId, trackId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("interviewEntity", interviewEntity);
        return successData(map);
    }

    /**
     * 根据所属榜单获取对应榜单列表<功能详细描述>
     * @param belongedRankId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/discover/getShopListByRankid", method = RequestMethod.GET)
    public Map<String, Object> getShopListByRankid(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        int belongedRankId = ReqUtils.getInt(request, "belongedRankId");
        Map<String, Object> map = new HashMap<String, Object>();
        List<RankSonBusinEntity> rankSonBusinEntities = null;
        UserCommentBusinEntity userCommentBusinEntity = new UserCommentBusinEntity();
        try
        {
            rankSonBusinEntities = shopFeignClient.getShopByRankid(belongedRankId);
            List<RankSonBusinEntity> rankSon = new ArrayList<RankSonBusinEntity>();
            for (RankSonBusinEntity rankSonBusinEntity : rankSonBusinEntities)
            {
                userCommentBusinEntity = getCacheUtil.getOneRecentShopComment(rankSonBusinEntity.getShopId());

                if (null != userCommentBusinEntity)
                {
                    rankSonBusinEntity.setUserName(userCommentBusinEntity.getNickName());
                    rankSonBusinEntity.setUserComment(userCommentBusinEntity.getContent());
                }
                rankSon.add(rankSonBusinEntity);
            }
            map.put("rankSon", rankSon);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":discover getShopListByRankid:" + "获取对应榜单列表失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_SELECT_DB_ERROR);
        }
        return successData(map);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(DiscoverCotroller.class, errorMsg);
    }

}
