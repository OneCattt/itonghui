package com.fbw.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.common.CommonRsEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.GroundShopInfoBusinEntityMapper;
import com.fbw.service.service.GroundRegisterService;
import com.fbw.service.service.GroundShopInfoService;

@RestController
@RequestMapping("/groundShopInfo")
public class GroundShopInfoController extends BaseController
{

    @Autowired
    private GroundRegisterService groundRegisterService;

    @Autowired
    private GroundShopInfoService groundShopInfoService;

    @Autowired
    private GroundShopInfoBusinEntityMapper groundShopInfoBusinEntityMapper;

    /**
     * 
     * <功能详细描述> 绑定商家无效注册信息
     * @param rechargeEntity
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/bindingShopInVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingShopInVaildRegister(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.bindingShopInVaildRegister(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;

    }

    /**
     * 通过cityId查询所有地推商铺列表 <功能详细描述>
     * @param cityId
     * @param registerShopAccount
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectByCityId")
    public List<GroundShopInfoBusinEntity> selectByCityId(String cityId, String registerShopAccount)
    {
        return groundShopInfoBusinEntityMapper.selectByCityId(cityId, registerShopAccount);
    }

    /**
     * 通过cityId查询所有地推商铺列表 <功能详细描述>
     * @param cityId
     * @param registerShopAccount
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectByCityId", method = RequestMethod.POST)
    public boolean updateGroundShopStatus(String shopId, String status)
    {
        int flag = groundShopInfoBusinEntityMapper.updateGroundShopStatus(shopId, status);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * 
     * <功能详细描述>绑定商家有效注册信息
     * @param salesManId
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/bindingShopVaildRegister", method = RequestMethod.POST)
    public CommonRsEntity bindingShopVaildRegister(@RequestBody RegisterEntity registerEntity)
    {
        CommonRsEntity commonRsEntity = new CommonRsEntity();
        commonRsEntity.setOperationFlag(true);
        try
        {
            groundRegisterService.bindingShopVaildRegister(registerEntity);
        }
        catch (InnerException e)
        {
            commonRsEntity.setErrorCode(e.getErrorCode());
            commonRsEntity.setErrorMessage(e.getMessage());
            commonRsEntity.setOperationFlag(false);
        }
        return commonRsEntity;
    }

    /**
     * <功能详细描述> 新增商家普通地推
     * @param rechargeEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/saveGroundShopInfo", method = RequestMethod.POST)
    public int saveGroundShopInfo(String shopCode, String cityId, String shopGroundType)
    {
        return groundShopInfoService.saveGroundShopInfo(cityId, shopCode, shopGroundType);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
