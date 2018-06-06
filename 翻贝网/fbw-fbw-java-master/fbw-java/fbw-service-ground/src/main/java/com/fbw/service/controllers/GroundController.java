package com.fbw.service.controllers;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundRegisterUserDetailBusinEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
import com.fbw.service.mapper.GroundRegisterUserDetailBusinEntityMapper;
import com.fbw.service.mapper.GroundShopInfoBusinEntityMapper;
import com.fbw.service.service.GroundPersonalInfoService;

/**
 * 地推Controller <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/ground")
public class GroundController extends BaseController
{

    @Autowired
    private GroundPersonalInfoService groundPersonalInfoService;

    @Autowired
    private GroundShopInfoBusinEntityMapper groundShopInfoBusinEntityMapper;

    @Autowired
    private GroundRegisterUserDetailBusinEntityMapper groundRegisterUserDetailBusinEntityMapper;

    /**
     * 更新个人地推有效注册信息 <功能详细描述>
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updatePersonalGroundWithVaildRegAndRegInfo", method = RequestMethod.POST)
    public boolean updatePersonalGroundWithVaildRegAndRegInfo(String salesManId)
    {
        GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = new GroundPersonalInfoBusinEntity();
        groundPersonalInfoBusinEntity = groundPersonalInfoService.queryPersonalGroundBySalesManId(salesManId);
        int registerAmount = groundPersonalInfoBusinEntity.getValidRegisterAmount();
        AtomicInteger atomicInteger = new AtomicInteger(registerAmount);
        int flag = groundPersonalInfoService.updatePersonalGroundWithVaildRegAndRegInfo(salesManId, registerAmount,
                atomicInteger.addAndGet(1));
        if (flag == 1)
        {
            return true;
        }
        return false;

    }

    /**
     * 更新商家地推有效注册信息 <功能详细描述>
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/updateShopGroundWithVaildRegAndRegInfo", method = RequestMethod.POST)
    public boolean updateShopGroundWithVaildRegAndRegInfo(int shopId)
    {
        GroundShopInfoBusinEntity groundPersonalInfoBusinEntity = new GroundShopInfoBusinEntity();
        groundPersonalInfoBusinEntity = groundShopInfoBusinEntityMapper.queryShopGroundByShopId(shopId);
        int registerAmount = groundPersonalInfoBusinEntity.getValidRegisterAmount();
        AtomicInteger atomicInteger = new AtomicInteger(registerAmount);
        int flag = groundShopInfoBusinEntityMapper.updateShopGroundWithVaildRegAndRegInfo(shopId, registerAmount,
                atomicInteger.addAndGet(1));
        if (flag == 1)
        {
            return true;
        }
        return false;

    }

    /**
     * 通过地推ID查询个人地推信息 <功能详细描述>
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryPersonalGroundBySalesManId", method = RequestMethod.GET)
    public GroundPersonalInfoBusinEntity queryPersonalGroundBySalesManId(String salesManId)
    {
        return groundPersonalInfoService.queryPersonalGroundBySalesManId(salesManId);

    }

    /**
     * 
     * <功能详细描述> 通过地推ID查询商户地推信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryShopGroundBySalesManId", method = RequestMethod.GET)
    public GroundShopInfoBusinEntity queryShopGroundBySalesManId(int salesManId)
    {
        return groundShopInfoBusinEntityMapper.queryShopGroundBySalesManId(salesManId);

    }

    /**
     * 
     * <功能详细描述> 保存注册用户详细地推信息
     * @param groundRegisterUserDetailBusin
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryShopGroundBySalesManId", method = RequestMethod.POST)
    public boolean saveRegisterUserDetailWithGround(
            @RequestBody GroundRegisterUserDetailBusinEntity groundRegisterUserDetailBusin)
    {
        int flag = groundRegisterUserDetailBusinEntityMapper
                .saveRegisterUserDetailWithGround(groundRegisterUserDetailBusin);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(GroundController.class, errorMsg);

    }

}
