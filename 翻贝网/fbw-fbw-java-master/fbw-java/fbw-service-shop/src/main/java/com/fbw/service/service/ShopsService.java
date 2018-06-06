package com.fbw.service.service;

import java.util.List;
import java.util.Map;

import com.fbw.service.entity.shop.InterviewParentEntity;
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

/**
 * <功能详细描述>商户信息service接口
 * @author FBW0115
 * @version [版本号, 2017年8月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ShopsService
{

    /**
     * 
     * <功能详细描述>查询首页分类列表
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopClassBusinEntity> getShopsClass(int cityId);

    /**
     * 
     * <功能详细描述>搜索
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getSearchShop(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>根据城市id查询探店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InterviewParentEntity> getInterviewByCityId(int cityId, int page);

    /**
     * 
     * <功能详细描述>根据探店id查询提到的店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getInterviewChild(int interviewId);

    /**
     * 
     * <功能详细描述>根据城市id查询首页banner
     * @param CityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopBannerEntity> getBannerByCityId(int cityId, int locationTpye, int firstClassId);

    /**
     * 
     * <功能详细描述>首页根据城市id查询品质好店
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopQualityEntity> getShopsQualityByCityId(int cityId);

    /**
     * 
     * <功能详细描述>根据城市id查询品质好店列表分页
     * @param cityId
     * @param begin
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopQualityEntity> getShopsQualityAll(int cityId, int begin, String latItude, String longItude);

    /**
     * 
     * <功能详细描述>查询附近的店
     * @param latItude 纬度
     * @param longItude 经度
     * @param page 分页，0开始
     * @return
     * @see [类、类#方法、类#成员]
     */
    // List<ShopsInfoEntity> getNearbyShops(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>筛选
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopInfoEntity> getScreenShops(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>根据shopid查看商户详情
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopDetailEntity getShopsDetailByShopid(int shopId);

    /**
     * 
     * <功能详细描述>查询翻倍日
     * @param map(shopId、date)
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopEventEntity> getShopsEventsByDate(Map<String, Object> map);

    /**
     * 
     * <功能详细描述>根据shopId查询招牌菜
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopSignEntity> getShopsSignByShopId(int shopId);

    /**
     * 
     * <功能详细描述>根据shopId查询探店
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<InterviewParentEntity> getInterviewByCode(String code);

    /**
     * 
     * <功能详细描述>根据shopid查询商户信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopInfoEntity getShopsInfoByShopId(int shopId, String latItude, String longItude);

    /**
     * 
     * <功能详细描述>根据code查询商户信息
     * @param code
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopInfoEntity getShopsInfoByCode(String code, String latItude, String longItude);

    /**
     * 
     * <功能详细描述>根据cityid获取分类信息
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopFirstClassEntity> getShopsClassByCityId(int cityId);

    /**
     * 
     * <功能详细描述>根据cityid查询地区
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopDistrictEntity> getShopsDistrictByCityId(int cityId);

    /**
     * 
     * <功能详细描述>根据城市id查询热词
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<ShopHotWordEntity> getHotWordsBycityId(int cityId);

    /**
     * 
     * <功能详细描述>保存商家报错信息
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int saveShopsInfoError(ShopInfoErrorEntity entity);

    /**
     * 
     * <功能详细描述>查询所有的品质好店的shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Integer> getShopsQualityShopId();

    /**
     * 
     * <功能详细描述>根据interviewId查询探店
     * @param interviewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    InterviewParentEntity getInterviewByInterviewId(int interviewId);

    /**
     * 
     * <功能详细描述>根据shopId获取当前翻倍日
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    ShopEventEntity getShopsNowEventsByShopId(int shopId);

    /**
     * 
     * <功能详细描述>根据shopId和event取消翻倍日
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    int updateShopEventStatus(ShopEventEntity entity);

    /**
     * 
     * <功能详细描述>查询新店数量
     * @param cityId
     * @param firstClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getNewShopNum(Map<String, Object> map);

}
