package com.fbw.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fbw.service.contents.CacheKeyConstant;
import com.fbw.service.entity.common.CityBusinEntity;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.shop.InterviewParentEntity;
import com.fbw.service.entity.shop.RankFatherBusinEntity;
import com.fbw.service.entity.shop.RankSonBusinEntity;
import com.fbw.service.entity.shop.ShopBannerEntity;
import com.fbw.service.entity.shop.ShopClassBusinEntity;
import com.fbw.service.entity.shop.ShopDistrictEntity;
import com.fbw.service.entity.shop.ShopFirstClassEntity;
import com.fbw.service.entity.shop.ShopHotWordEntity;
import com.fbw.service.entity.shop.ShopQualityEntity;
import com.fbw.service.entity.user.UserCommentBusinEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.feign.CommonFeignClient;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.DictMapper;

/**
 * 获取缓存 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class GetCacheUtil
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> vOps;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private ShopFeignClient shopFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CommonFeignClient commonFeignClient;

    /**
     * 
     * <功能详细描述>获取城市list(一天更新一次)
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<CityBusinEntity> getCityList()
    {
        // List<CityBusinEntity> list = (List<CityBusinEntity>)
        // vOps.get(CacheKeyConstant.PORTAL_HOME_CITYS_KEY);
        List<CityBusinEntity> list = null;
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = commonFeignClient.getCityList();
            vOps.set(CacheKeyConstant.PORTAL_HOME_CITYS_KEY, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>删除KEY值
     * @param key
     * @see [类、类#方法、类#成员]
     */
    public void deleteStringRedisVal(String key)
    {
        stringRedisTemplate.delete(key);
    }

    /**
     * 
     * <功能详细描述>根据cityid获取分类(一天更新一次)
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<ShopClassBusinEntity> getShopsClass(HomeReqEntity entity)
    {
        String key = CacheKeyConstant.PORTAL_HOME_SHOP_CLASS + entity.getCityId();
        List<ShopClassBusinEntity> list = (List<ShopClassBusinEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getShopsClass(entity);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据cityId获取首页广告banner(一天更新一次)
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getADBannerByCityId(HomeReqEntity entity)
    {

        String key = CacheKeyConstant.PORTAL_HOME_AD_BANNER + entity.getCityId();
        Map<String, Object> map = (Map<String, Object>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(map))
        {
            HomeReqEntity homeReqEntity = new HomeReqEntity();
            homeReqEntity.setCityId(entity.getCityId());
            map = new HashMap<String, Object>();
            homeReqEntity.setFirstClassId("0");
            homeReqEntity.setLocationType("1");
            List<ShopBannerEntity> topBanner = shopFeignClient.getBannerByCityId(homeReqEntity);
            homeReqEntity.setLocationType("2");
            List<ShopBannerEntity> localBanner = shopFeignClient.getBannerByCityId(homeReqEntity);
            homeReqEntity.setLocationType("3");
            homeReqEntity.setCityId("0");
            List<ShopBannerEntity> nationwideBanner = shopFeignClient.getBannerByCityId(homeReqEntity);
            map.put("topBanner", topBanner);
            map.put("localBanner", localBanner);
            map.put("nationwideBanner", nationwideBanner);
            vOps.set(key, map, 1, TimeUnit.DAYS);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public List<ShopBannerEntity> getADClassBannerByCityId(String key)
    {
        return (List<ShopBannerEntity>) vOps.get(key);

    }

    public void setADClassBannerByCityId(String key, List<ShopBannerEntity> list)
    {
        vOps.set(key, list, 1, TimeUnit.DAYS);

    }

    /**
     * 
     * <功能详细描述>根据cityid获取首页品质好店(一小时更新一次)
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<ShopQualityEntity> getShopsQualityByCityId(HomeReqEntity entity)
    {
        String key = CacheKeyConstant.PORTAL_HOME_QUALITY_SHOP + entity.getCityId();
        List<ShopQualityEntity> list = (List<ShopQualityEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getShopsQualityByCityId(entity);
            vOps.set(key, list, 1, TimeUnit.HOURS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据cityid获取商圈(一天更新一次)
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<ShopDistrictEntity> getShopsDistrictByCityId(HomeReqEntity entity)
    {
        String key = CacheKeyConstant.PORTAL_HOME_SHOP_DISTRICT + entity.getCityId();
        List<ShopDistrictEntity> list = (List<ShopDistrictEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getShopsDistrictByCityId(entity);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据cityid获取商户分类(一天更新一次)
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<ShopFirstClassEntity> getShopsClassByCityId(HomeReqEntity entity)
    {
        String key = CacheKeyConstant.PORTAL_HOME_SHOP_FIRST_CLASS + entity.getCityId();
        List<ShopFirstClassEntity> list = (List<ShopFirstClassEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getShopsClassByCityId(entity);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据城市id查询探店(一小时更新一次)
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<InterviewParentEntity> getInterviewByCityId(int cityId, int page)
    {
        String key = CacheKeyConstant.PORTAL_DISCOVER_INTERVIEW + cityId + ":" + page;
        List<InterviewParentEntity> list = (List<InterviewParentEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getInterviewByCityId(cityId, page);
            vOps.set(key, list, 1, TimeUnit.HOURS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>将搜索词的次数放入redis中，每次搜索加一次
     * @param searchName
     * @see [类、类#方法、类#成员]
     */
    public void hotSearchName(String searchName)
    {
        String key = CacheKeyConstant.PORTAL_HOME_SEARCH_ + searchName;
        String num = getStringRedisVal(key);
        if (null == num || "".equals(num))
        {
            setStringRedisVal(key, "1");
        }
        else
        {
            setStringRedisVal(key, String.valueOf(Integer.parseInt(num) + 1));
        }
    }

    /**
     * 
     * <功能详细描述>根据城市id查询热词(一天更新一次)
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<ShopHotWordEntity> getHotWordsBycityId(int cityId, String trackId)
    {
        String key = CacheKeyConstant.PORTAL_HOME_HOT_WORDS + cityId;
        List<ShopHotWordEntity> list = (List<ShopHotWordEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getHotWordsBycityId(cityId, trackId);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>品质好店shopid集合(一小时更新一次)
     * @param trackId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<Integer> getShopsQualityShopId(String trackId)
    {
        List<Integer> list = (List<Integer>) vOps.get(CacheKeyConstant.PORTAL_HOME_QUALITY_SHOPID);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getShopsQualityShopId(trackId);
            vOps.set(CacheKeyConstant.PORTAL_HOME_QUALITY_SHOPID, list, 5, TimeUnit.SECONDS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>更新浏览量
     * @param shopId
     * @param oldBrowseNum
     * @see [类、类#方法、类#成员]
     */
    public int setBrowseNum(String key, int oldBrowseNum)
    {
        String browseStr = getStringRedisVal(key);
        int newBrowseNum = 1;
        if (browseStr == null)
        {
            setStringRedisVal(key, String.valueOf(oldBrowseNum + 1));
            return oldBrowseNum + 1;
        }
        newBrowseNum = Integer.parseInt(browseStr.toString());
        if (newBrowseNum < oldBrowseNum)
        {
            // vOps.set(key, oldBrowseNum + 1);
            setStringRedisVal(key, String.valueOf(oldBrowseNum + 1));
            newBrowseNum = oldBrowseNum;
        }
        else
        {
            // vOps.set(key, newBrowseNum + 1);
            setStringRedisVal(key, String.valueOf(oldBrowseNum + 1));
        }
        return newBrowseNum + 1;
    }

    /**
     * 
     * <功能详细描述>获取浏览量
     * @param shopId
     * @param key
     * @see [类、类#方法、类#成员]
     */
    public int getBrowseNum(String key, int oldBrowseNum)
    {
        int newBrowseNum = 1;
        String browseStr = getStringRedisVal(key);
        if (browseStr == null)
        {
            setStringRedisVal(key, String.valueOf(oldBrowseNum));
            // vOps.set(key, oldBrowseNum);
            return oldBrowseNum;
        }
        newBrowseNum = Integer.parseInt(browseStr.toString());
        if (newBrowseNum < oldBrowseNum)
        {
            setStringRedisVal(key, String.valueOf(oldBrowseNum));
            // vOps.set(key, oldBrowseNum);
            newBrowseNum = oldBrowseNum;
        }
        return newBrowseNum;
    }

    /**
     * <功能详细描述>获取字典号码值
     * @param dictKey 字典key值
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<String> getDictNumList(String dictKey)
    {
        List<String> dictList = new ArrayList<>();
        // 获取缓存的值
        dictList = stringRedisTemplate.opsForList().range(CacheKeyEnums.getMsg(dictKey), 0, -1);
        if (dictList.isEmpty())
        { // 查询数据库值
            dictList = dictMapper.getDictValue(dictKey, "0");
            // 如果没值就保存到缓存中
            setListRedisVal(CacheKeyEnums.getMsg(dictKey), dictList);
        }
        return dictList;

    }

    /**
     * <功能详细描述>获取字典号码值
     * @param dictKey 字典key值
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getDictVal(String dictKey)
    {
        String dictVal = "";
        // 获取缓存的值
        dictVal = getStringRedisVal(CacheKeyEnums.getMsg(dictKey));
        if (NomalUtil.isNullOrEmpty(dictVal))
        { // 查询数据库值
            dictVal = dictMapper.getDictValue(dictKey, "0").get(0);
            // 如果没值就保存到缓存中
            setStringRedisVal(CacheKeyEnums.getMsg(dictKey), dictVal);
        }
        return dictVal;

    }

    /**
     * 
     * <功能详细描述>根据城市id查询榜单
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<RankFatherBusinEntity> getRankFather(Integer cityId)
    {
        String key = CacheKeyConstant.PORTAL_DISCOVER_FIX_RANK + cityId;
        List<RankFatherBusinEntity> list = (List<RankFatherBusinEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getRankFather(cityId);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据城市id查询子榜单
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<RankSonBusinEntity> getShopByRankid(Integer belongedRankId)
    {
        String key = CacheKeyConstant.PORTAL_DISCOVER_NOFIX_RANK + belongedRankId;
        List<RankSonBusinEntity> list = (List<RankSonBusinEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = shopFeignClient.getShopByRankid(belongedRankId);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据shopId查询最近最好评价
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserCommentBusinEntity getOneRecentShopComment(Integer shopId)
    {
        String key = CacheKeyConstant.PORTAL_DISCOVER_ONE_SHOP_COMMENT + shopId;
        UserCommentBusinEntity list = (UserCommentBusinEntity) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            // TODO 返回为空的问题
            list = userFeignClient.getOneRecentShopComment(shopId);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述>获取用户支付密码
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public List<UserInfoBusinEntity> getUserPayPassWord()
    {
        String key = CacheKeyConstant.PORTAL_HOME_PAY_PASSWORD;
        List<UserInfoBusinEntity> list = (List<UserInfoBusinEntity>) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = userFeignClient.getUserInfoList();
            vOps.set(key, list);
        }
        return list;

    }

    /**
     * 
     * <功能详细描述>翻倍轨迹
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public UserInfoBusinEntity getUserdoubleTrail(Integer userId)
    {
        String key = CacheKeyConstant.PORTAL_USER_DOUBLE_TRAIL + userId;
        UserInfoBusinEntity list = (UserInfoBusinEntity) vOps.get(key);
        if (NomalUtil.isNullOrEmpty(list))
        {
            list = userFeignClient.selectUserdoubleTrail(userId);
            vOps.set(key, list, 1, TimeUnit.DAYS);
        }
        return list;
    }

    /**
     * 
     * <功能详细描述> 设置List类型redis缓存值
     * @param key
     * @param value
     * @see [类、类#方法、类#成员]
     */
    public void setListRedisVal(String key, List<String> values)
    {
        stringRedisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 
     * <功能详细描述>获取List类型的redis值
     * @param key
     * @param values
     * @see [类、类#方法、类#成员]
     */
    public List<String> getListRedisVal(String key)
    {
        return stringRedisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 
     * <功能详细描述> 设置String类型redis缓存值
     * @param key
     * @param values
     * @see [类、类#方法、类#成员]
     */
    public void setStringRedisVal(String key, String values)
    {
        stringRedisTemplate.opsForValue().set(key, values);
    }

    /**
     * 
     * <功能详细描述> 获取String类型redis缓存值
     * @param key
     * @see [类、类#方法、类#成员]
     */
    public String getStringRedisVal(String key)
    {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置String类型时间控制的redis缓存值 <功能详细描述>
     * @param key
     * @param value
     * @param timeout
     * @see [类、类#方法、类#成员]
     */
    public void setTimeStringRedisVal(String key, String value, long timeout, TimeUnit unit)
    {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 
     * <功能详细描述> 获取redis失效时间
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getRedisTimeOut(String key)
    {
        return stringRedisTemplate.getExpire(key);
    }

}
