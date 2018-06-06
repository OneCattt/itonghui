package com.fbw.service.business.discover.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.shop.InterviewParentEntity;
import com.fbw.service.entity.shop.RankFatherBusinEntity;
import com.fbw.service.entity.shop.RankSonBusinEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述>发现Service
 * @author FBW0115
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class DiscoverService
{

    @Autowired
    private ShopFeignClient shopFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private GetCacheUtil getCacheUtil;

    /**
     * 下拉刷新，根据城市id查询探店 <功能详细描述>
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<InterviewParentEntity> getInterviewByCityId(int cityId, int page)
    {
        return getCacheUtil.getInterviewByCityId(cityId, page);
    }

    /**
     * 发现 <功能详细描述>
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> getDiscover(int cityId, int page) throws InnerException
    {
        List<RankFatherBusinEntity> rankFatherBusinEntities = getCacheUtil.getRankFather(cityId);
        Map<String, Object> map = new HashMap<String, Object>();
        UserCommentBusinEntity userCommentBusinEntity = new UserCommentBusinEntity();
        if (null != rankFatherBusinEntities)
        {
            ArrayList<Object> rankList = new ArrayList<>();
            for (RankFatherBusinEntity rankFatherBusinEntity : rankFatherBusinEntities)
            {
                RankFatherBusinEntity rankFather = new RankFatherBusinEntity();
                List<RankSonBusinEntity> rankSonBusin = getCacheUtil.getShopByRankid(rankFatherBusinEntity.getId());
                List<RankSonBusinEntity> rankSon = new ArrayList<RankSonBusinEntity>();
                for (RankSonBusinEntity rankSonBusinEntity : rankSonBusin)
                {
                    userCommentBusinEntity = getCacheUtil.getOneRecentShopComment(rankSonBusinEntity.getShopId());

                    if (null != userCommentBusinEntity)
                    {
                        rankSonBusinEntity.setUserName(userCommentBusinEntity.getNickName());
                        rankSonBusinEntity.setUserComment(userCommentBusinEntity.getContent());
                    }
                    rankSon.add(rankSonBusinEntity);
                }
                rankFather.setRankSonBusinEntities(rankSon);
                rankFather.setRankName(rankFatherBusinEntity.getRankName());
                rankFather.setRankId(rankFatherBusinEntity.getId());
                rankList.add(rankFather);
            }
            map.put("rankList", rankList);
        }
        List<InterviewParentEntity> interviewParentList = getCacheUtil.getInterviewByCityId(cityId, page);
        if (!NomalUtil.isNullOrEmpty(interviewParentList))
        {
            for (InterviewParentEntity entity : interviewParentList)
            {
                // 收藏、浏览量
                int browseNum = getCacheUtil.getBrowseNum(
                        CacheKeyConstant.PORTAL_HOME_SHOP_BROWSENUM + entity.getInterviewId(), entity.getBrowseNum());
                entity.setBrowseNum(browseNum);
                // 收藏
                int collectNum = userFeignClient.getCollectInterviewNumByShopId(entity.getInterviewId());
                entity.setCollectNum(collectNum);
            }
        }
        map.put("interviewParentList", interviewParentList);
        return map;
    }

    /**
     * 
     * <功能详细描述>根据探店id查询探店信息和子类商户
     * @param interviewId
     * @param trackId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public InterviewParentEntity getInterviewByInterviewId(int interviewId, int userId, String trackId)
    {
        InterviewParentEntity entity = shopFeignClient.getInterviewByInterviewId(interviewId, trackId);
        if (!NomalUtil.isNullOrEmpty(entity))
        {
            // 浏览
            int browseNum = getCacheUtil.setBrowseNum(
                    CacheKeyConstant.PORTAL_HOME_SHOP_BROWSENUM + entity.getInterviewId(), entity.getBrowseNum());
            entity.setBrowseNum(browseNum);
            int isCollect = userFeignClient.getUserIsCollectInterviewShop(userId, interviewId);
            entity.setIsCollect(isCollect);
            // 收藏
            int collectNum = userFeignClient.getCollectInterviewNumByShopId(entity.getInterviewId());
            entity.setCollectNum(collectNum);
            List<ShopInfoEntity> interviewChild = shopFeignClient.getInterviewChild(interviewId);
            entity.setInvolvedShopsInfo(interviewChild);
        }
        return entity;
    }

    /**
     * 根据所属榜单获取对应榜单列表 <功能详细描述>
     * @param belongedRankId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<RankSonBusinEntity> getShopByRankid(Integer belongedRankId)
    {
        return getCacheUtil.getShopByRankid(belongedRankId);
    }

}
