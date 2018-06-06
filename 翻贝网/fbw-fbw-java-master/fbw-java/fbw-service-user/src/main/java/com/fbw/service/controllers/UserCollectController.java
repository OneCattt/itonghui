package com.fbw.service.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.contents.UserConstant;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.user.UserCollectBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mappers.UserCollectBusinEntityMapper;
import com.fbw.service.services.UserCollectService;

/**
 * 
 * <功能详细描述>用户收藏信息Controller
 * @author jiangruliang
 * @version [版本号, 2017年8月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/userCollect")
public class UserCollectController extends BaseController
{
    @Resource
    private UserCollectService userCollectService;

    @Autowired
    private UserCollectBusinEntityMapper userCollectBusinEntityMapper;

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(UserCollectController.class, errorMsg);
    }

    /**
     * 获取当前用户所有收藏列表 <功能详细描述>
     * @param userId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserCollectList", method = RequestMethod.GET)
    public List<UserCollectBusinEntity> getUserCollectList(int userId, int begin)
    {
        List<UserCollectBusinEntity> userCollectBusinEntities = new ArrayList<UserCollectBusinEntity>();
        userCollectBusinEntities = userCollectService.getUserCollectList(userId, begin);
        return userCollectBusinEntities;

    }

    /**
     * 删除或点击收藏的店铺 <功能详细描述>
     * @param userId
     * @param shopId
     * @param status
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserCollect", method = RequestMethod.POST)
    public CommonRsEntity updateUserCollect(Integer userId, Integer shopId, Integer status) throws InnerException
    {
        CommonRsEntity CommonRsEntity = new CommonRsEntity();
        CommonRsEntity.setOperationFlag(true);
        try
        {
            if (status == -1)
            {
                userCollectService.insertUserCollect(userId, shopId);
            }
            if (status == 0)
            {
                userCollectService.updateUserCollect(userId, shopId, 1);
            }
            if (status == 1)
            {
                userCollectService.updateUserCollect(userId, shopId, 0);
            }

        }
        catch (Exception e)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_COLLECT_SHOP_ERROR);
            CommonRsEntity.setOperationFlag(false);
        }
        return CommonRsEntity;
    }

    /**
     * 获取当前用户所有收藏探店id <功能详细描述>
     * @param userId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserCollectInterviewShopList", method = RequestMethod.GET)
    public List<String> getUserCollectInterviewShopList(Integer userId, Integer begin) throws InnerException
    {
        List<String> ulList = new ArrayList<String>();
        ulList = userCollectService.getUserCollectInterviewShopList(userId, begin);
        return ulList;
    }

    /**
     * 用户点击取消或收藏探店 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @param status
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateUserCollectInterviewShop", method = RequestMethod.POST)
    public CommonRsEntity updateUserCollectInterviewShop(Integer userId, Integer interviewShopId, Integer status)
    {
        CommonRsEntity CommonRsEntity = new CommonRsEntity();
        CommonRsEntity.setOperationFlag(true);
        try
        {
            if (status == -1)
            {
                userCollectService.insertUserCollectInterviewShop(userId, interviewShopId);
            }
            if (status == 0)
            {
                userCollectService.updateUserCollectInterviewShop(userId, interviewShopId, 1);
            }
            if (status == 1)
            {
                userCollectService.updateUserCollectInterviewShop(userId, interviewShopId, 0);
            }

        }
        catch (Exception e)
        {
            CommonRsEntity.setErrorCode(UserConstant.USER_COLLECT_INTERVIEW_SHOP_ERROR);
            CommonRsEntity.setOperationFlag(false);
        }
        return CommonRsEntity;
    }

    /**
     * 判断当前商户是否被该用户收藏 <功能详细描述>
     * @param userId
     * @param shopId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserIsCollect", method = RequestMethod.GET)
    public int getUserIsCollect(Integer userId, Integer shopId) throws InnerException
    {
        int flag = -1;
        UserCollectBusinEntity userCollect = userCollectService.getUserIsCollect(userId, shopId);
        if (null == userCollect)
        {
            return flag;
        }
        return userCollect.getStatus();
    }

    /**
     * 判断当前探店是否被该用户收藏 <功能详细描述>
     * @param userId
     * @param interviewShopId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getUserIsCollectInterviewShop", method = RequestMethod.GET)
    public int getUserIsCollectInterviewShop(Integer userId, Integer interviewShopId) throws InnerException
    {
        int flag = -1;
        UserCollectBusinEntity userCollect = userCollectService.getUserIsCollectInterviewShop(userId, interviewShopId);
        if (null == userCollect)
        {
            return flag;
        }
        return userCollect.getStatus();
    }

    /**
     * 获取当前店铺收藏数量 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getCollectNumByShopId")
    public int getCollectNumByShopId(Integer shopId)
    {
        return userCollectService.getCollectNumByShopId(shopId);
    }

    /**
     * 获取当前探店收藏数量 <功能详细描述>
     * @param interviewShopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getCollectInterviewNumByShopId")
    public int getCollectInterviewNumByShopId(Integer interviewShopId)
    {
        return userCollectService.getCollectInterviewNumByShopId(interviewShopId);
    }

    /**
     * 获取所有店铺收藏量 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/getAllShopCollectNumber")
    public List<UserCollectBusinEntity> getAllShopCollectNumber()
    {
        return userCollectBusinEntityMapper.getAllShopCollectNumber();
    }

}
