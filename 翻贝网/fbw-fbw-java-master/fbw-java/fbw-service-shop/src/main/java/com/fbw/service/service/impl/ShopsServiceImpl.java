package com.fbw.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.shop.InterviewParentEntity;
import com.fbw.service.entity.shop.ShopBannerEntity;
import com.fbw.service.entity.shop.ShopBusinessAreaEntity;
import com.fbw.service.entity.shop.ShopClassBusinEntity;
import com.fbw.service.entity.shop.ShopDetailEntity;
import com.fbw.service.entity.shop.ShopDistrictEntity;
import com.fbw.service.entity.shop.ShopEventEntity;
import com.fbw.service.entity.shop.ShopFirstClassEntity;
import com.fbw.service.entity.shop.ShopHotWordEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.shop.ShopInfoErrorEntity;
import com.fbw.service.entity.shop.ShopQualityEntity;
import com.fbw.service.entity.shop.ShopSecondClassConfEntity;
import com.fbw.service.entity.shop.ShopSignEntity;
import com.fbw.service.mapper.InterviewMapper;
import com.fbw.service.mapper.ShopBannerMapper;
import com.fbw.service.mapper.ShopBusinessAreaMapper;
import com.fbw.service.mapper.ShopClassBusinMapper;
import com.fbw.service.mapper.ShopDetailMapper;
import com.fbw.service.mapper.ShopDistrictMapper;
import com.fbw.service.mapper.ShopEventMapper;
import com.fbw.service.mapper.ShopFirstClassMapper;
import com.fbw.service.mapper.ShopHotWordMapper;
import com.fbw.service.mapper.ShopInfoErrorMapper;
import com.fbw.service.mapper.ShopInfoMapper;
import com.fbw.service.mapper.ShopQualityMapper;
import com.fbw.service.mapper.ShopSecondClassConfMapper;
import com.fbw.service.mapper.ShopSignMapper;
import com.fbw.service.service.ShopsService;
import com.fbw.service.util.NomalUtil;

/**
 * <功能详细描述>商户信息service实现
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class ShopsServiceImpl implements ShopsService
{
    @Autowired
    private ShopClassBusinMapper shopClassBannerMapper;

    @Autowired
    private ShopInfoMapper shopInfoMapper;

    @Autowired
    private ShopDetailMapper shopDetailMapper;

    @Autowired
    private InterviewMapper interviewMapper;

    @Autowired
    private ShopBannerMapper shopBannerMapper;

    @Autowired
    private ShopQualityMapper shopQualityMapper;

    @Autowired
    private ShopEventMapper shopEventsMapper;

    @Autowired
    private ShopSignMapper shopSignMapper;

    @Autowired
    private ShopFirstClassMapper shopFirstClassMapper;

    @Autowired
    private ShopSecondClassConfMapper shopSecondClassMapper;

    @Autowired
    private ShopDistrictMapper shopDistrictMapper;

    @Autowired
    private ShopBusinessAreaMapper shopBusinessAreaMapper;

    @Autowired
    private ShopHotWordMapper shopHotWordsMapper;

    @Autowired
    private ShopInfoErrorMapper shopInfoErrorMapper;

    /**
     * 
     * <功能详细描述>查询首页分类列表
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopClassBusinEntity> getShopsClass(int cityId)
    {
        return shopClassBannerMapper.getShopsClass(cityId);
    }

    /**
     * 
     * <功能详细描述>搜索
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopInfoEntity> getSearchShop(Map<String, Object> map)
    {
        List<ShopInfoEntity> nearbyShops = shopInfoMapper.getSearchShop(map);
        if (null != nearbyShops && !nearbyShops.isEmpty())
        {
            for (ShopInfoEntity entity : nearbyShops)
            {
                getShopsEventsOne(null, entity);
            }

        }
        return nearbyShops;
    }

    /**
     * 
     * <功能详细描述>根据城市id查询探店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<InterviewParentEntity> getInterviewByCityId(int cityId, int page)
    {
        List<InterviewParentEntity> interviewList = interviewMapper.getInterviewByCityId(cityId, page);
        if (!NomalUtil.isNullOrEmpty(interviewList))
        {
            for (InterviewParentEntity entity : interviewList)
            {
                List<ShopInfoEntity> interviewChild = shopInfoMapper.getInterviewChild(entity.getInterviewId());
                entity.setInvolvedShopsInfo(interviewChild);
            }
        }
        return interviewList;
    }

    /**
     * 
     * <功能详细描述>根据探店id查询提到的店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopInfoEntity> getInterviewChild(int interviewId)
    {
        return shopInfoMapper.getInterviewChild(interviewId);
    }

    /**
     * 
     * <功能详细描述>根据城市id查询首页banner
     * @param CityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopBannerEntity> getBannerByCityId(int cityId, int locationTpye, int firstClassId)
    {
        return shopBannerMapper.getBannerByCityId(cityId, locationTpye, firstClassId);
    }

    /**
     * 
     * <功能详细描述>首页根据城市id查询品质好店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopQualityEntity> getShopsQualityByCityId(int cityId)
    {
        List<ShopQualityEntity> shopsQualityByCityId = shopQualityMapper.getShopsQualityByCityId(cityId);
        if (!NomalUtil.isNullOrEmpty(shopsQualityByCityId))
        {
            for (ShopQualityEntity entity : shopsQualityByCityId)
            {
                ShopInfoEntity shopsInfoEntity = shopInfoMapper.getShopsNameByShopId(entity.getShopId());
                entity.setName(shopsInfoEntity.getName());
                entity.setShopLogo(shopsInfoEntity.getLogo());
            }
        }
        return shopsQualityByCityId;
    }

    /**
     * 
     * <功能详细描述>根据城市id查询品质好店列表分页
     * @param cityId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopQualityEntity> getShopsQualityAll(int cityId, int begin, String latitude, String longitude)
    {
        List<ShopQualityEntity> shopsQualityAll = shopQualityMapper.getShopsQualityAll(cityId, begin);
        if (null != shopsQualityAll && !shopsQualityAll.isEmpty())
        {
            Map<String, Object> map = new HashMap<>();
            map.put("latitude", latitude);
            map.put("longitude", longitude);
            for (ShopQualityEntity entity : shopsQualityAll)
            {
                map.put("shopId", entity.getShopId());
                ShopInfoEntity shopsInfoEntity = shopInfoMapper.getShopsInfoByShopIdTwo(map);
                getShopsEventsOne(null, shopsInfoEntity);
                entity.setShopsInfoEntity(shopsInfoEntity);
            }
        }
        return shopsQualityAll;
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
    // @Override
    // public List<ShopsInfoEntity> getNearbyShops(Map<String, Object> map)
    // {
    // List<ShopsInfoEntity> nearbyShops = shopsInfoMapper.getNearbyShops(map);
    // if (null != nearbyShops && !nearbyShops.isEmpty())
    // {
    // for (ShopsInfoEntity entity : nearbyShops)
    // {
    // getShopsEventsOne(null, entity);
    // }
    // }
    // return nearbyShops;
    // }

    /**
     * 
     * <功能详细描述>筛选
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopInfoEntity> getScreenShops(Map<String, Object> map)
    {
        String event = String.valueOf(map.get("event"));
        List<ShopInfoEntity> nearbyShops;
        // 判断是否需要筛选翻倍日
        if (null == event || "".equals(event) || "null".equals(event))
        {
            nearbyShops = shopInfoMapper.getScreenShops(map);
        }
        else
        {
            nearbyShops = shopInfoMapper.getScreenShopsByevent(map);
        }

        if (null != nearbyShops && !nearbyShops.isEmpty())
        {
            for (ShopInfoEntity entity : nearbyShops)
            {
                getShopsEventsOne(event, entity);
            }
        }
        return nearbyShops;
    }

    /**
     * 
     * <功能详细描述>根据shopid查看商户详情
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ShopDetailEntity getShopsDetailByShopid(int shopId)
    {
        return shopDetailMapper.getShopsDetailByShopid(shopId);
    }

    /**
     * 
     * <功能详细描述>查询翻倍日
     * @param map(shopId、date)
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopEventEntity> getShopsEventsByDate(Map<String, Object> map)
    {
        return shopEventsMapper.getShopsEventsByDate(map);
    }

    /**
     * 
     * <功能详细描述>根据shopId查询招牌菜
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopSignEntity> getShopsSignByShopId(int shopId)
    {
        return shopSignMapper.getShopsSignByShopId(shopId);
    }

    /**
     * 
     * <功能详细描述>根据shopId查询探店
     * @param code
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<InterviewParentEntity> getInterviewByCode(String code)
    {
        return interviewMapper.getInterviewByCode(code);
    }

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ShopInfoEntity getShopsInfoByShopId(int shopId, String latitude, String longitude)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        map.put("shopId", shopId);
        ShopInfoEntity entity;
        if (null == latitude || "".equals(latitude) || null == longitude || "".equals(longitude))
        {
            // 不需要查询距离
            entity = shopInfoMapper.getShopsInfoByShopIdOne(map);
        }
        else
        {
            entity = shopInfoMapper.getShopsInfoByShopIdTwo(map);
        }
        if (null != entity)
        {
            getShopsEventsOne(null, entity);
        }
        return entity;
    }

    /**
     * 
     * <功能详细描述>根据code查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ShopInfoEntity getShopsInfoByCode(String code, String latitude, String longitude)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("latitude", latitude);
        map.put("longitude", longitude);
        map.put("code", code);
        return shopInfoMapper.getShopsInfoByCode(map);
    }

    /**
     * 
     * <功能详细描述>根据cityid获取分类信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopFirstClassEntity> getShopsClassByCityId(int cityId)
    {
        List<ShopFirstClassEntity> list = shopFirstClassMapper.getShopsFirstClassList(cityId);
        if (null != list && !list.isEmpty())
        {
            for (ShopFirstClassEntity entity : list)
            {
                List<ShopSecondClassConfEntity> shopsSecondClass = shopSecondClassMapper
                        .getShopsSecondClass(entity.getFirstClassId());
                entity.setShopsSecondClassList(shopsSecondClass);
            }

        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据cityid查询地区
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopDistrictEntity> getShopsDistrictByCityId(int cityId)
    {
        List<ShopDistrictEntity> list = shopDistrictMapper.getShopsDistrictByCityId(cityId);
        if (null != list && !list.isEmpty())
        {
            for (ShopDistrictEntity entity : list)
            {
                List<ShopBusinessAreaEntity> shopsBusinessAreaList = shopBusinessAreaMapper
                        .getShopsBusinessArea(entity.getDistrictId());
                entity.setShopsBusinessAreaList(shopsBusinessAreaList);
            }

        }
        return list;
    }

    /**
     * 
     * <功能详细描述>根据城市id查询热词
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ShopHotWordEntity> getHotWordsBycityId(int cityId)
    {
        return shopHotWordsMapper.getHotWordsBycityId(cityId);
    }

    /**
     * 
     * <功能详细描述>保存商家报错信息
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int saveShopsInfoError(ShopInfoErrorEntity entity)
    {
        return shopInfoErrorMapper.insertShopsInfoError(entity);
    }

    /**
     * 
     * <功能详细描述>查询最近的翻倍日
     * @param nearbyShops
     * @see [类、类#方法、类#成员]
     */
    private void getShopsEventsOne(String event, ShopInfoEntity entity)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("eventDate", NomalUtil.isNullOrEmpty(event) ? null : event.substring(0, 10));
        map.put("shopId", entity.getShopId());
        ShopEventEntity shopsEvents = shopEventsMapper.getShopsEventsOne(map);
        entity.setShopsEventsEntity(shopsEvents);
    }

    /**
     * 
     * <功能详细描述>查询所有的品质好店的shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<Integer> getShopsQualityShopId()
    {
        return shopQualityMapper.getShopsQualityShopId();
    }

    /**
     * 
     * <功能详细描述>根据interviewId查询探店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public InterviewParentEntity getInterviewByInterviewId(int interviewId)
    {
        return interviewMapper.getInterviewByInterviewId(interviewId);
    }

    /**
     * 
     * <功能详细描述>根据shopId获取当前翻倍日
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ShopEventEntity getShopsNowEventsByShopId(int shopId)
    {
        return shopEventsMapper.getShopsNowEventsByShopId(shopId);
    }

    /**
     * 
     * <功能详细描述>根据shopId和event取消翻倍日
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int updateShopEventStatus(ShopEventEntity entity)
    {
        return shopEventsMapper.updateShopEventStatus(entity);
    }

    /**
     * 
     * <功能详细描述>查询新店数量
     * @param cityId
     * @param firstClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int getNewShopNum(Map<String, Object> map)
    {
        return shopInfoMapper.getNewShopNum(map);
    }

}
