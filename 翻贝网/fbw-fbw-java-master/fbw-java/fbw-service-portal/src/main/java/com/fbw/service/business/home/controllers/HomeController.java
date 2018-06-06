package com.fbw.service.business.home.controllers;

import java.lang.reflect.InvocationTargetException;
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
import com.fbw.service.business.home.service.HomeService;
import com.fbw.service.entity.common.CityBusinEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.shop.ShopBannerEntity;
import com.fbw.service.entity.shop.ShopHotWordEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.shop.ShopQualityEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述>首页controller
 * @author FBW0115
 * @version [版本号, 2017年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class HomeController extends BaseController
{

    @Autowired
    private HomeService homeService;

    /**
     * 
     * <功能详细描述>获取城市信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getCityList", method = RequestMethod.GET)
    public Map<String, Object> getCityList(HttpServletRequest request)
    {

        String trackId = String.valueOf(request.getAttribute("trackId"));
        List<CityBusinEntity> cityList = homeService.getCityList();
        if (NomalUtil.isNullOrEmpty(cityList))
        {
            getErrorLog(trackId + ":home getCityList:" + "获取城市信息失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_CITY_LIST_FAIL);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("cityList", cityList);
        return successData(data);

    }

    /**
     * 
     * <功能详细描述>保存城市代理申请
     * @param entity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/saveCityAgent", method = RequestMethod.POST)
    public Map<String, Object> saveCityAgent(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        String agentName = String.valueOf(request.getParameter("agentName"));
        String agentTelnum = String.valueOf(request.getParameter("agentTelnum"));
        String intentAgentCity = String.valueOf(request.getParameter("intentAgentCity"));
        try
        {
            homeService.saveCityAgent(agentName, agentTelnum, intentAgentCity);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":home saveCityAgent:" + e.getMessage());
            return failedMessage(e.getErrorCode());
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home saveCityAgent:" + e.getMessage());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }

        return successMessage("申请成功！");
    }

    /**
     * 
     * <功能详细描述>首页信息（首页banner、分类banner、此刻翻倍花、品质好店、附近的店）
     * @param cityId:城市id
     * @param longItude:经度
     * @param latItude:纬度
     * @param page:0
     * @param sortType:1
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getHomeInfo", method = RequestMethod.GET)
    public Map<String, Object> getHomeInfo(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            data = homeService.getHomeInfo(entity);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getHomeInfo:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":home getHomeInfo:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getHomeInfo:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>此刻翻倍花下拉刷新
     * @param cityId:城市id
     * @param longItude:经度
     * @param latItude:纬度
     * @param page:0
     * @param sortType:1
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getEventShops", method = RequestMethod.GET)
    public Map<String, Object> getEventShops(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            List<ShopInfoEntity> eventShopList = homeService.getEventShops(entity);
            data.put("eventShopList", eventShopList);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getEventShops:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":home getEventShops:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getEventShops:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>筛选(此刻翻倍花、附近的店)
     * @param cityId:城市id
     * @param longItude：经度："115.848766"
     * @param latItude：纬度："28.693304"
     * @param eventDate：翻倍日
     * @param eventType：翻倍类型
     * @param districtName：地区
     * @param firstclassId：一级分类
     * @param secondclassId：二级分类
     * @param distance：距离
     * @param page：分页（第一页传0，第二页传1...）
     * @param sortType：筛选排序（0:智能排序、1：距离排序、2:人气排序、3:最新上线、4:好评优先）
     * @param newShops：是否筛选新店
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getScreenShops", method = RequestMethod.GET)
    public Map<String, Object> getScreenShops(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            List<ShopInfoEntity> screenShopsList = homeService.getScreenShops(entity);
            data.put("screenShopsList", screenShopsList);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getScreenShops:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":home getScreenShops:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getScreenShops:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>获取筛选类型
     * @param cityId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getScreenClass", method = RequestMethod.GET)
    public Map<String, Object> getScreenClass(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            data = homeService.getScreenClass(entity);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getScreenClass:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":home getScreenClass:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getScreenClass:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>搜索
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getSearchShop", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getSearchShop(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            List<ShopInfoEntity> searchShopsList = homeService.getSearchShop(entity);
            data.put("searchShopsList", searchShopsList);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getSearchShop:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":home getSearchShop:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getSearchShop:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>商家详情
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getShopInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getShopInfo(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            data = homeService.getShopInfo(entity);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":home getShopInfo:" + e.getMessage() + parameterMap.toString());
            return failedMessage(e.getErrorCode());
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getShopInfo:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(trackId + ":home getShopInfo:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getShopInfo:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>品质好店列表
     * @param cityId:城市id
     * @param longItude：经度："115.848766"
     * @param latItude：纬度："28.693304"
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getShopsQualityAll", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getShopsQualityAll(HttpServletRequest request)
    {

        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            List<ShopQualityEntity> shopsQualityAll = homeService.getShopsQualityAll(entity);
            data.put("shopsQualityAll", shopsQualityAll);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getShopsQualityAll:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(
                    trackId + ":home getShopsQualityAll:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getShopsQualityAll:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>商家信息报错
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/savaShopInfoError", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> savaShopInfoError(HttpServletRequest request)
    {
        String trackId = String.valueOf(request.getAttribute("trackId"));
        String userId = String.valueOf(request.getParameter("userId"));
        String shopId = String.valueOf(request.getParameter("shopId"));
        String infoError = String.valueOf(request.getParameter("infoError"));
        int result = homeService.savaShopInfoError(userId, shopId, infoError, trackId);
        if (1 != result)
        {
            getErrorLog(trackId + ":home saveCityAgent:" + "提交商家错误信息失败！");
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SAVE_CITYAGENT_FAIL);
        }
        return successMessage("提交成功！");

    }

    /**
     * 
     * <功能详细描述>根据城市id查询热词
     * @param cityid
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getHotWordsBycityId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getHotWordsBycityId(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        int cityId = Integer.parseInt(request.getParameter("cityId"));
        Map<String, Object> data = new HashMap<String, Object>();
        List<ShopHotWordEntity> shopsHotWordsList = homeService.getHotWordsBycityId(cityId, trackId);
        data.put("shopsHotWordsList", shopsHotWordsList);
        return successData(data);
    }

    /**
     * 
     * <功能详细描述>分类banner
     * @param cityId
     * @param firstClassId
     * @param locationType
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/home/getShopClassBanner", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getShopClassBanner(HttpServletRequest request)
    {
        String trackId = request.getAttribute("trackId").toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        HomeReqEntity entity = new HomeReqEntity();
        Map<String, Object> data = new HashMap<String, Object>();
        try
        {
            BeanUtils.populate(entity, parameterMap);
            entity.setTrackId(trackId);
            List<ShopBannerEntity> classBannerList = homeService.getClassBannerByCityId(entity);
            data.put("classBannerList", classBannerList);
        }
        catch (IllegalAccessException e)
        {
            getErrorLog(trackId + ":home getShopClassBanner:参数解析失败IllegalAccessException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (InvocationTargetException e)
        {
            getErrorLog(
                    trackId + ":home getShopClassBanner:参数解析失败InvocationTargetException:" + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_PARAM_FAIL);
        }
        catch (Exception e)
        {
            getErrorLog(trackId + ":home getShopClassBanner:" + e.getMessage() + parameterMap.toString());
            return failedMessage(ErrorMsgConstant.PORTAL_HOME_SYSTEM_FAIL);
        }
        return successData(data);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(HomeController.class, errorMsg);

    }

}
