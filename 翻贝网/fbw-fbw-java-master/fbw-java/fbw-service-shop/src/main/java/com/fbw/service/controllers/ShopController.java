package com.fbw.service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.portal.HomeReqEntity;
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
import com.fbw.service.mapper.RankFatherBusinEntityMapper;
import com.fbw.service.service.RankAllService;
import com.fbw.service.service.ShopsService;
import com.fbw.service.util.NomalUtil;

@RestController
@RequestMapping(value = "/shop")
public class ShopController extends BaseController
{
    @Autowired
    private ShopsService shopsService;

    @Autowired
    private RankAllService rankAllService;

    @Autowired
    private RankFatherBusinEntityMapper rankFatherBusinEntityMapper;

    /**
     * 
     * <功能详细描述>查询首页分类列表
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsCityBanner")
    public List<ShopClassBusinEntity> getShopsClassr(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsClass(Integer.parseInt(entity.getCityId()));
    }

    /**
     * 
     * <功能详细描述>搜索
     * @param map(经度：LongItude、纬度：LatItude、搜索词：searchName、地市：CityId)
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getSearchShop")
    public List<ShopInfoEntity> getSearchShop(@RequestBody HomeReqEntity entity)
    {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("longitude", entity.getLongitude());
        m.put("latitude", entity.getLatitude());
        m.put("searchName", entity.getSearchName());
        m.put("cityId", entity.getCityId());
        m.put("page", Integer.parseInt(entity.getPage()) * 20);
        return shopsService.getSearchShop(m);
    }

    /**
     * 
     * <功能详细描述>根据城市id查询探店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getInterviewByCityId")
    public List<InterviewParentEntity> getInterviewByCityId(int cityId, int page)
    {
        return shopsService.getInterviewByCityId(cityId, page);
    }

    /**
     * 
     * <功能详细描述>根据探店id查询提到的店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getInterviewChild")
    public List<ShopInfoEntity> getInterviewChild(int interviewId)
    {
        return shopsService.getInterviewChild(interviewId);
    }

    /**
     * 
     * <功能详细描述>根据城市id查询首页banner
     * @param CityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getBannerByCityId")
    public List<ShopBannerEntity> getBannerByCityId(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getBannerByCityId(Integer.parseInt(entity.getCityId()),
                Integer.parseInt(entity.getLocationType()), Integer.parseInt(entity.getFirstClassId()));
    }

    /**
     * 
     * <功能详细描述>根据城市id查询品质好店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsQualityByCityId")
    public List<ShopQualityEntity> getShopsQualityByCityId(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsQualityByCityId(Integer.parseInt(entity.getCityId()));
    }

    /**
     * 
     * <功能详细描述>根据城市id查询品质好店列表分页
     * @param cityId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsQualityAll")
    public List<ShopQualityEntity> getShopsQualityAll(@RequestBody HomeReqEntity entity)
    {
        int begin = Integer.parseInt(entity.getPage()) * 20;
        return shopsService.getShopsQualityAll(Integer.parseInt(entity.getCityId()), begin, entity.getLatitude(),
                entity.getLongitude());
    }

    /**
     * 
     * <功能详细描述>查询附近的店
     * @param latItude 纬度
     * @param longItude 经度
     * @param page 分页，0开始
     * @return
     * @see [类、类#方法、类#成员]
     */
    // @RequestMapping(value = "/getNearbyShops")
    // public List<ShopsInfoEntity> getNearbyShops(@RequestBody HomeReqEntity
    // entity)
    // {
    // Map<String, Object> m = new HashMap<String, Object>();
    // m.put("longitude", entity.getLongitude());
    // m.put("latitude", entity.getLatitude());
    // m.put("cityId", entity.getCityId());// 城市id
    // m.put("event", entity.getEventDate());// 翻倍日
    // m.put("eventType", null);// 翻倍类型
    // m.put("districtName", null);// 地区
    // m.put("firstClassId", null);// 一级分类
    // m.put("secondClassId", null);// 二级分类
    // m.put("distance", null);// 距离多少m
    // m.put("page", 0 * 20);// 分页
    // m.put("sortType", "1");// 1：距离排序、2:人气排序、3:最新上线、4:好评优先
    // return shopsService.getNearbyShops(m);
    // }

    /**
     * 
     * <功能详细描述>筛选(此刻翻倍花)
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getScreenShops")
    public List<ShopInfoEntity> getScreenShops(@RequestBody HomeReqEntity entity)
    {
        Map<String, Object> m = new HashMap<String, Object>();

        m.put("longitude", entity.getLongitude());
        m.put("latitude", entity.getLatitude());
        m.put("cityId", entity.getCityId());// 城市id
        m.put("event", entity.getEventDate());// 翻倍日
        m.put("eventDate",
                NomalUtil.isNullOrEmpty(entity.getEventDate()) ? null : entity.getEventDate().substring(0, 10));// 翻倍日
        m.put("eventType", entity.getEventType());// 翻倍类型
        m.put("shopId", entity.getShopId());// 查询推荐的店，去掉自身
        m.put("firstClassId", entity.getFirstClassId());// 一级分类
        m.put("secondClassId", entity.getSecondClassId());// 二级分类
        m.put("districtId", entity.getDistrictId());// 地区id
        m.put("businessAreaId", entity.getBusinessAreaId());// 商圈id
        m.put("distance", entity.getDistance());// 距离多少m
        m.put("page", Integer.parseInt(entity.getPage()) * 20);// 分页
        m.put("sortType", entity.getSortType());// 0:智能排序、1：距离排序、2:人气排序、3:最新上线、4:好评优先
        m.put("isNewShop", entity.getIsNewShop());
        // TODO人气排行等待确认
        return shopsService.getScreenShops(m);
    }

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsInfoByShopId")
    public ShopInfoEntity getShopsInfoByShopId(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsInfoByShopId(Integer.parseInt(entity.getShopId()), entity.getLatitude(),
                entity.getLongitude());
    }

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsInfoByCode")
    public ShopInfoEntity getShopsInfoByCode(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsInfoByCode(entity.getCode(), entity.getLatitude(), entity.getLongitude());
    }

    /**
     * 
     * <功能详细描述>根据shopId查询商户详细信息
     * @param shopId
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsDetailByShopid")
    public ShopDetailEntity getShopsDetailByShopid(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsDetailByShopid(Integer.parseInt(entity.getShopId()));
    }

    /**
     * 
     * <功能详细描述>查询翻倍日
     * @param map(shopId、date)
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsEventsByDate")
    public List<ShopEventEntity> getShopsEventsByDate(@RequestBody HomeReqEntity entity)
    {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("shopId", entity.getShopId());
        m.put("date", entity.getData());
        return shopsService.getShopsEventsByDate(m);
    }

    /**
     * 
     * <功能详细描述>根据shopId查询招牌菜
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsSignByShopId")
    public List<ShopSignEntity> getShopsSignByShopId(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsSignByShopId(Integer.parseInt(entity.getShopId()));
    }

    /**
     * 
     * <功能详细描述>根据shopId查询探店
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getInterviewByCode")
    public List<InterviewParentEntity> getInterviewByCode(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getInterviewByCode(entity.getCode());
    }

    /**
     * 
     * <功能详细描述>根据cityid获取分类信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsClassByCityId")
    public List<ShopFirstClassEntity> getShopsClassByCityId(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsClassByCityId(Integer.parseInt(entity.getCityId()));
    }

    /**
     * 
     * <功能详细描述>根据cityid查询地区
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsDistrictByCityId")
    public List<ShopDistrictEntity> getShopsDistrictByCityId(@RequestBody HomeReqEntity entity)
    {
        return shopsService.getShopsDistrictByCityId(Integer.parseInt(entity.getCityId()));
    }

    /**
     * 
     * <功能详细描述>根据城市id查询热词
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getHotWordsBycityId")
    public List<ShopHotWordEntity> getHotWordsBycityId(int cityId, String trackId)
    {
        return shopsService.getHotWordsBycityId(cityId);
    }

    /**
     * 
     * <功能详细描述>保存商家报错信息
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveShopsInfoError", method = RequestMethod.POST)
    public int saveShopsInfoError(@RequestBody ShopInfoErrorEntity entity)
    {
        return shopsService.saveShopsInfoError(entity);
    }

    /**
     * 
     * <功能详细描述>获取固定榜单banner
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getRankFather")
    public List<RankFatherBusinEntity> getRankFather(Integer cityId)
    {
        return rankAllService.getRankFather(cityId);
    }

    /**
     * 
     * <功能详细描述>根据所属榜单获取对应榜单列表
     * @param belongedRankId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getShopByRankid")
    public List<RankSonBusinEntity> getShopByRankid(Integer belongedRankId)
    {
        return rankAllService.getShopByRankid(belongedRankId);
    }

    /**
     * 根据shopId获取所属榜单以及排名 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getRankByshopId")
    public RankSonBusinEntity getRankByshopId(Integer shopId)
    {
        return rankAllService.getRankByshopId(shopId);
    }

    /**
     * 根据shopId获取所属榜单以及排名 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getRankByFirstClassIdAndCityId")
    public RankFatherBusinEntity getRankByFirstClassIdAndCityId(Integer cityId, Integer firstClassId)
    {
        return rankFatherBusinEntityMapper.getRankByFirstAndCity(cityId, firstClassId);
    }

    /**
     * 
     * <功能详细描述>查询所有的品质好店的shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsQualityShopId")
    public List<Integer> getShopsQualityShopId(String trackId)
    {
        return shopsService.getShopsQualityShopId();
    }

    /**
     * 
     * <功能详细描述>根据interviewId查询探店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getInterviewByInterviewId")
    public InterviewParentEntity getInterviewByInterviewId(int interviewId, String trackId)
    {
        return shopsService.getInterviewByInterviewId(interviewId);
    }

    /**
     * 
     * <功能详细描述>根据shopId获取当前翻倍日
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getShopsNowEventsByShopId")
    public ShopEventEntity getShopsNowEventsByShopId(int shopId, String trackId)
    {
        return shopsService.getShopsNowEventsByShopId(shopId);
    }

    /**
     * 
     * <功能详细描述>根据shopId和event取消翻倍日
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateShopEventStatus")
    public boolean updateShopEventStatus(@RequestBody ShopEventEntity entity)
    {
        if (NomalUtil.isNullOrEmpty(entity.getEvent()))
        {
            return false;
        }
        int num = shopsService.updateShopEventStatus(entity);
        return num >= 1;
    }

    /**
     * 
     * <功能详细描述>查询新店数量
     * @param cityId
     * @param firstClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getNewShopNum")
    public int getNewShopNum(@RequestBody HomeReqEntity entity)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cityId", entity.getCityId());
        map.put("firstClassId", entity.getFirstClassId());
        return shopsService.getNewShopNum(map);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }
}
