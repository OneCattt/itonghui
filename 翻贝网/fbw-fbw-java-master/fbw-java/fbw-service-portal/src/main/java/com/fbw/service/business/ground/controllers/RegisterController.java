package com.fbw.service.business.ground.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.business.ground.entity.GroundInfoEntity;
import com.fbw.service.business.ground.service.RegisterService;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.GroundFeignClient;
import com.fbw.service.util.CommonUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述>地推注册控制层
 * @author JACK HUANG
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class RegisterController extends BaseController
{
    @Autowired
    RegisterService registerService;

    @Autowired
    GroundFeignClient groundFeignClient;

    @Autowired
    CommonUtil commonUtil;

    /**
     * 有效注册状态
     */
    final String VAILD_REGISTER_STATUS = "0";

    /**
     * 无效注册状态
     */
    final String INVAILD_REGISTER_STATUS = "1";

    /**
     * 个人地推类型
     */
    final String PERSONAL_GROUND_TYPE = "1";

    /**
     * 
     * <功能详细描述> 地推个人注册用户
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/registerPersonalGround", method = RequestMethod.GET)
    public Map<String, Object> registerPersonalGround(HttpServletRequest request)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        String trackId = request.getAttribute("trackId").toString();
        // 构造注册用户实体类
        RegisterEntity registerEntity = buildPersonalRegisterEntity(request);
        try
        {
            // 保存用户地推信息
            registerService.saveUserGroundInfo(registerEntity);
        }
        catch (InnerException e)
        {
            getErrorLog(trackId + ":register error:" + e.getMessage());
            return failedMessage(e.getMessage());
        }
        return successData(data);

    }

    /**
     * 
     * <功能详细描述>地推商户注册用户
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> registerShopGround(HttpServletRequest request)
    {
        // 构造商户注册用户实体类
        RegisterEntity registerEntity = buildShopRegisterEntity(request);
        // 判断是否是无效的地推用户
        if (commonUtil.isGroundInvaildRegisterUser(registerEntity.getMobile(), registerEntity.getCityId(),
                registerEntity.getDeviceId()))
        {
            registerEntity.setRegisterStatus(INVAILD_REGISTER_STATUS);
            // 绑定商户无效的注册用户
            groundFeignClient.bindingShopInVaildRegister(registerEntity);
        }
        else
        {
            registerEntity.setRegisterStatus(VAILD_REGISTER_STATUS);
            // 绑定商户有效的注册用户
            groundFeignClient.bindingPersonalVaildRegister(registerEntity);
        }
        return successMessage("register success");

    }

    /**
     * 
     * <功能详细描述>获取个人地推信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/qrcode/user/{salesManId}", method = RequestMethod.GET)
    public Map<String, Object> getPersonalGroundInfo(@PathVariable String salesManId)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        // 查询个人地推信息
        GroundInfoEntity groundInfoEntity = registerService.queryPersonalGroundInfo(salesManId);
        data.put("mobile", groundInfoEntity.getMobile());
        data.put("salesManName", groundInfoEntity.getSalesManName());
        return successData(data);

    }

    /**
     * 
     * <功能详细描述>获取商户地推信息
     * @param shopId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/qrcode/shop/{shopId}", method = RequestMethod.GET)
    public Map<String, Object> getShopGroundInfo(@PathVariable String shopId, HttpServletRequest request)
    {
        Map<String, Object> data = new HashMap<String, Object>();
        GroundInfoEntity groundInfoEntity = registerService.queryShopGroundInfo(shopId);
        data.put("shopAdress", groundInfoEntity.getShopAdress());
        data.put("shopName", groundInfoEntity.getShopName());
        data.put("shopId", groundInfoEntity.getShopId());
        return data;

    }

    /**
     * 
     * <功能详细描述> 构造注册用户实体类
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    private RegisterEntity buildPersonalRegisterEntity(HttpServletRequest request)
    {
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setMobile(request.getParameter("mobile"));
        registerEntity.setSalesManId(request.getParameter("salesManId"));
        registerEntity.setGroundType(PERSONAL_GROUND_TYPE);
        return registerEntity;

    }

    /**
     * 
     * <功能详细描述> 构造注册用户实体类
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    private RegisterEntity buildShopRegisterEntity(HttpServletRequest request)
    {
        RegisterEntity registerEntity = new RegisterEntity();
        Map<String, String> groundParm = NomalUtil.analysisGroundUrl(request.getRequestURL().toString());
        String shopAssistantId = groundParm.get("shopAssistantId");
        if (!NomalUtil.isNullOrEmpty(shopAssistantId))
        {
            // TODO: 表未确定，查询店员姓名
        }
        registerEntity.setMobile(request.getParameter("mobile"));
        registerEntity.setSalesManId(request.getParameter("deviceId"));
        registerEntity.setCityId(request.getParameter("cityId"));
        registerEntity.setShopAssistantId(shopAssistantId);
        registerEntity.setShopId(groundParm.get("shopId"));
        return registerEntity;

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(RegisterController.class, errorMsg);
    }
}
