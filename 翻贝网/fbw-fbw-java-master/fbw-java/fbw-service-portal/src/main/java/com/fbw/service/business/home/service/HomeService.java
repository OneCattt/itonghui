package com.fbw.service.business.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.contents.PortalConstant;
import com.fbw.service.entity.DictEntity;
import com.fbw.service.entity.common.CityBusinEntity;
import com.fbw.service.entity.common.CityIntentionAgentEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.shop.InterviewParentEntity;
import com.fbw.service.entity.shop.RankFatherBusinEntity;
import com.fbw.service.entity.shop.RankSonBusinEntity;
import com.fbw.service.entity.shop.ShopBannerEntity;
import com.fbw.service.entity.shop.ShopClassBusinEntity;
import com.fbw.service.entity.shop.ShopDetailEntity;
import com.fbw.service.entity.shop.ShopDistrictEntity;
import com.fbw.service.entity.shop.ShopEventEntity;
import com.fbw.service.entity.shop.ShopFirstClassEntity;
import com.fbw.service.entity.shop.ShopHotWordEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.shop.ShopInfoErrorEntity;
import com.fbw.service.entity.shop.ShopQualityEntity;
import com.fbw.service.entity.shop.ShopSignEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.DictMapper;
import com.fbw.service.util.DateUtil;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述>首页service
 * @author FBW0115
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class HomeService
{
    @Autowired
    private CommonFeignClient commonFeignClient;

    @Autowired
    private ShopFeignClient shopFeignClient;

    @Autowired
    private GetCacheUtil getCacheUtil;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private DictMapper dictMapper;

    /**
     * 
     * <功能详细描述>获取城市信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<CityBusinEntity> getCityList()
    {
        return getCacheUtil.getCityList();
    }

    /**
     * 
     * <功能详细描述>保存城市代理申请
     * @param entity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void saveCityAgent(String agentName, String agentTelnum, String intentAgentCity) throws InnerException
    {
        CityIntentionAgentEntity entity = new CityIntentionAgentEntity();
        entity.setAgentName(agentName);
        entity.setAgentTelnum(agentTelnum);
        entity.setIntentAgentCity(intentAgentCity);
        if (NomalUtil.isNullOrEmpty(agentTelnum) || !NomalUtil.isMobileNo(agentTelnum))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_MOBILE_FORMAT_FAIL_MSG,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_MOBILE_FORMAT_FAIL_MSG));
        }
        int result = commonFeignClient.saveCityAgent(entity);
        if (1 != result)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_HOME_SAVE_CITYAGENT_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_HOME_SAVE_CITYAGENT_FAIL));
        }
    }

    /**
     * 
     * <功能详细描述>首页信息（首页banner、分类banner、此刻翻倍花、品质好店、附近的店）
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> getHomeInfo(HomeReqEntity entity)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        // 首页banner
        Map<String, Object> adBanner = getCacheUtil.getADBannerByCityId(entity);
        // 分类
        List<ShopClassBusinEntity> classBusin = getCacheUtil.getShopsClass(entity);
        // 品质好店
        List<ShopQualityEntity> shopsQuality = getCacheUtil.getShopsQualityByCityId(entity);
        // 附近的店
        List<ShopInfoEntity> nearbyShopList = shopFeignClient.getScreenShops(entity);
        // 此刻翻倍花
        String date = DateUtil.getStringDate();
        entity.setEventDate(date);
        List<ShopInfoEntity> eventShopList = shopFeignClient.getScreenShops(entity);
        if (!NomalUtil.isNullOrEmpty(eventShopList))
        {
            for (ShopInfoEntity shopsInfo : eventShopList)
            {
                // 榜单、收藏、浏览量
                shopsParam(shopsInfo, entity.getTrackId());
            }
        }
        map.put("adBanner", adBanner);
        map.put("classBusin", classBusin);
        map.put("shopsQuality", shopsQuality);
        map.put("nearbyShopList", nearbyShopList);
        map.put("eventShopList", eventShopList);
        return map;
    }

    /**
     * 
     * <功能详细描述>此刻翻倍花下拉刷新
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ShopInfoEntity> getEventShops(HomeReqEntity entity)
    {
        String date = DateUtil.getStringDate();
        entity.setEventDate(date);
        List<ShopInfoEntity> eventShopList = shopFeignClient.getScreenShops(entity);
        if (!NomalUtil.isNullOrEmpty(eventShopList))
        {
            for (ShopInfoEntity shopsInfo : eventShopList)
            {
                // 榜单、收藏、浏览量
                shopsParam(shopsInfo, entity.getTrackId());
            }
        }
        return eventShopList;
    }

    /**
     * 
     * <功能详细描述>筛选(此刻翻倍花)
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ShopInfoEntity> getScreenShops(HomeReqEntity entity)
    {
        List<ShopInfoEntity> screenShopsList = shopFeignClient.getScreenShops(entity);

        if (!NomalUtil.isNullOrEmpty(screenShopsList))
        {
            for (ShopInfoEntity shopsInfoEntity : screenShopsList)
            {
                // 榜单、收藏、浏览量
                shopsParam(shopsInfoEntity, entity.getTrackId());
            }
        }
        return screenShopsList;
    }

    /**
     * 
     * <功能详细描述>获取筛选类型
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> getScreenClass(HomeReqEntity entity)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ShopFirstClassEntity> shopsClassList = getCacheUtil.getShopsClassByCityId(entity);
        List<ShopDistrictEntity> shopsDistrictList = getCacheUtil.getShopsDistrictByCityId(entity);
        map.put("shopsClassList", shopsClassList);
        map.put("shopsDistrictList", shopsDistrictList);
        return map;
    }

    /**
     * 
     * <功能详细描述>根据城市id查询热词
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ShopHotWordEntity> getHotWordsBycityId(int cityId, String trackId)
    {
        return getCacheUtil.getHotWordsBycityId(cityId, trackId);
    }

    /**
     * 
     * <功能详细描述>搜索
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ShopInfoEntity> getSearchShop(HomeReqEntity entity)
    {
        // 热门搜索放入redis中
        getCacheUtil.hotSearchName(entity.getSearchName());
        List<ShopInfoEntity> searchShopsList = shopFeignClient.getSearchShop(entity);
        if (!NomalUtil.isNullOrEmpty(searchShopsList))
        {

            for (ShopInfoEntity shopsInfoEntity : searchShopsList)
            {
                // 榜单、收藏、浏览量
                shopsParam(shopsInfoEntity, entity.getTrackId());
            }
        }
        return searchShopsList;
    }

    /**
     * 
     * <功能详细描述>商家详情
     * @param longItude 经度
     * @param latItude 纬度
     * @param shopId 商家id
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> getShopInfo(HomeReqEntity entity) throws InnerException
    {
        // 商家信息、
        ShopInfoEntity shopsInfo = shopFeignClient.getShopsInfoByShopId(entity);
        if (NomalUtil.isNullOrEmpty(shopsInfo))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_HOME_SHOPINFO_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_HOME_SHOPINFO_FAIL));
        }
        // 榜单、收藏、浏览量
        shopsParam(shopsInfo, entity.getTrackId());
        // 商家详情、
        ShopDetailEntity shopsDetail = shopFeignClient.getShopsDetailByShopid(entity);
        // 招牌特色、
        List<ShopSignEntity> shopsSign = shopFeignClient.getShopsSignByShopId(entity);
        // 翻倍日、
        entity.setData(DateUtil.getFristDayByMonth());
        List<ShopEventEntity> shopsEvents = shopFeignClient.getShopsEventsByDate(entity);
        // 评价、
        UserCommentBusinEntity comment = userFeignClient.getOneShopInfoMainComment(shopsInfo.getShopId());
        entity.setShopId(String.valueOf(shopsInfo.getShopId()));
        // 推荐的店
        List<ShopInfoEntity> recommendShops = getRecommendShops(shopsInfo);
        // 探店、
        entity.setCode(shopsInfo.getCode());
        List<InterviewParentEntity> interviewList = shopFeignClient.getInterviewByCode(entity);
        // 浏览
        int browseNum = getCacheUtil.setBrowseNum(CacheKeyConstant.PORTAL_HOME_SHOP_BROWSENUM + shopsInfo.getShopId(),
                shopsInfo.getBrowseNum());
        shopsInfo.setBrowseNum(browseNum);
        // 是否收藏
        if (!NomalUtil.isNullOrEmpty(entity.getUserId()))
        {
            int isCollect = userFeignClient.getUserIsCollect(Integer.parseInt(entity.getUserId()),
                    shopsInfo.getShopId());
            shopsInfo.setIsCollect(isCollect);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shopsInfo", shopsInfo);
        map.put("shopsDetail", shopsDetail);
        map.put("shopsSign", shopsSign);
        map.put("interviewList", interviewList);
        map.put("comment", comment);
        map.put("recommendShops", recommendShops);
        map.put("shopsEvents", shopsEvents);
        return map;
    }

    /**
     * 
     * <功能详细描述>品质好店列表
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ShopQualityEntity> getShopsQualityAll(HomeReqEntity entity)
    {
        List<ShopQualityEntity> shopsQualityAll = shopFeignClient.getShopsQualityAll(entity);
        if (!NomalUtil.isNullOrEmpty(shopsQualityAll))
        {
            for (ShopQualityEntity shopsQualityEntity : shopsQualityAll)
            {
                ShopInfoEntity shopsInfo = shopsQualityEntity.getShopsInfoEntity();
                // 榜单、收藏、浏览量
                shopsParam(shopsInfo, entity.getTrackId());
            }
        }
        return shopsQualityAll;
    }

    /**
     * 
     * <功能详细描述>商家信息报错
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int savaShopInfoError(String userId, String shopId, String infoError, String trackId)
    {
        ShopInfoErrorEntity entity = new ShopInfoErrorEntity();
        entity.setUserId(Integer.parseInt(userId));
        entity.setShopId(Integer.parseInt(shopId));
        entity.setInfoError(infoError);
        entity.setTrackId(trackId);
        return shopFeignClient.saveShopsInfoError(entity);
    }

    /**
     * 
     * <功能详细描述>根据城市id查询分类banner
     * @param CityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ShopBannerEntity> getClassBannerByCityId(@RequestBody HomeReqEntity entity)
    {
        String key = CacheKeyConstant.PORTAL_HOME_AD_CLASS_BANNER + entity.getCityId() + ":" + entity.getFirstClassId();
        List<ShopBannerEntity> list = getCacheUtil.getADClassBannerByCityId(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getBannerByCityId(entity);
            if (NomalUtil.isNullOrEmpty(list))
            {
                list = new ArrayList<ShopBannerEntity>();
            }
            // TODO 判断是否有新店
            int newShopNum = shopFeignClient.getNewShopNum(entity);
            if (newShopNum > 0)
            {
                List<DictEntity> dictValue = dictMapper.getDictEntity(PortalConstant.PORTAL_DICT_SHOP_NEW_KEY);
                ShopBannerEntity shopBannerEntity = new ShopBannerEntity();
                shopBannerEntity.setName(dictValue.get(0).getDictDes());
                shopBannerEntity.setUrl(dictValue.get(0).getDictValue());
                shopBannerEntity.setType("newShop");
                shopBannerEntity.setIsLogin(0);
                list.add(shopBannerEntity);
            }
            // TODO 判断是否有榜单
            RankFatherBusinEntity rankByFirstClassIdAndCityId = shopFeignClient.getRankByFirstClassIdAndCityId(
                    Integer.parseInt(entity.getCityId()), Integer.parseInt(entity.getFirstClassId()));
            if (!NomalUtil.isNullOrEmpty(rankByFirstClassIdAndCityId))
            {
                ShopBannerEntity shopBannerEntity = new ShopBannerEntity();
                shopBannerEntity.setName(rankByFirstClassIdAndCityId.getRankName());
                shopBannerEntity.setUrl(rankByFirstClassIdAndCityId.getRankBackUrl());
                shopBannerEntity.setContent(rankByFirstClassIdAndCityId.getId().toString());
                shopBannerEntity.setType("rank");
                shopBannerEntity.setIsLogin(0);
                list.add(shopBannerEntity);
            }
            getCacheUtil.setADClassBannerByCityId(key, list);

        }

        return list;
    }

    /**
     * 
     * <功能详细描述>推荐的店（同类型，今日翻倍日、附近1000m内）
     * @param homeReqEntity
     * @param shopsInfoEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<ShopInfoEntity> getRecommendShops(ShopInfoEntity shopsInfoEntity)
    {
        HomeReqEntity entity = new HomeReqEntity();
        entity.setCityId(String.valueOf(shopsInfoEntity.getCityId()));
        entity.setLatitude(shopsInfoEntity.getLatitude());
        entity.setLongitude(shopsInfoEntity.getLongitude());
        entity.setPage("0");
        entity.setDistance("1000");
        entity.setFirstClassId(String.valueOf(shopsInfoEntity.getFirstClassId()));
        entity.setSecondClassId(String.valueOf(shopsInfoEntity.getSecondClassId()));
        entity.setEventDate(DateUtil.getStringDate());
        entity.setShopId(String.valueOf(shopsInfoEntity.getShopId()));
        List<ShopInfoEntity> recommendShops = getScreenShops(entity);
        return recommendShops;
    }

    /**
     * 
     * <功能详细描述>处理商家榜单、收藏、浏览量信息
     * @param entity
     * @param trackId
     * @see [类、类#方法、类#成员]
     */
    private void shopsParam(ShopInfoEntity entity, String trackId)
    {
        // 更新翻倍状态
        ShopEventEntity shopsEventsEntity = entity.getShopsEventsEntity();
        if (!NomalUtil.isNullOrEmpty(shopsEventsEntity))
        {
            if (DateUtil.getStringDateShort().equals(shopsEventsEntity.getEvent()))
            {
                entity.setIsEvent(1);
            }
            else
            {
                entity.setIsEvent(0);
            }
        }
        // 是否榜单店
        RankSonBusinEntity shopRank = shopFeignClient.getRankByshopId(entity.getShopId());
        if (!NomalUtil.isNullOrEmpty(shopRank))
        {
            entity.setRankName(shopRank.getRankName());
            entity.setRankId(shopRank.getRankId());
            entity.setRankNumber(shopRank.getRankNumber());
        }
        // 判断是否是品质好店
        if (isQualityShos(entity.getShopId(), trackId))
        {
            entity.setIsQuality(1);
        }
        // 查询浏览量
        int browseNum = getCacheUtil.getBrowseNum(CacheKeyConstant.PORTAL_HOME_SHOP_BROWSENUM + entity.getShopId(),
                entity.getBrowseNum());
        entity.setBrowseNum(browseNum);
    }

    /**
     * 
     * <功能详细描述>判断是否是品质好店
     * @param shopid
     * @param trackId
     * @return
     * @see [类、类#方法、类#成员]
     */
    private boolean isQualityShos(int shopid, String trackId)
    {
        List<Integer> qualityShopIdList = getCacheUtil.getShopsQualityShopId(trackId);
        if (NomalUtil.isNullOrEmpty(qualityShopIdList))
        {
            return false;
        }
        else
        {
            return qualityShopIdList.contains(shopid);
        }
    }

}