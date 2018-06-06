package com.fbw.service.business.ground.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.business.ground.entity.GroundInfoEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
import com.fbw.service.entity.ground.RegisterEntity;
import com.fbw.service.entity.portal.ErrorMsgConstant;
import com.fbw.service.entity.portal.InnerCode;
import com.fbw.service.entity.user.UserGroundInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.feign.GroundFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.util.CommonUtil;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 
 * <功能详细描述> 注册接口层
 * @author JACK HUANG
 * @version [版本号, 2017年9月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class RegisterService
{

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    GroundFeignClient groundFeignClient;

    @Autowired
    CommonUtil commonUtil;

    @Autowired
    GetCacheUtil cacheUtil;

    /**
     * 登陆时间控制
     */
    final String LOGIN_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 有效注册状态
     */
    final String VAILD_REGISTER_STATUS = "0";

    /**
     * 无效注册状态
     */
    final String INVAILD_REGISTER_STATUS = "1";

    /**
     * 
     * <功能详细描述>保存用户地推信息
     * @param registerEntity
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    public void saveUserGroundInfo(RegisterEntity registerEntity) throws InnerException
    {

        // 保存用户地推信息
        if (!userFeignClient.saveUserGroundInfo(bulidPersonalGroundInfo(registerEntity)))
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_SAVE_DB_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_SAVE_DB_FAIL));
        }
        // 保存地推登陆时间
        commonUtil.saveGroundLoginDate(registerEntity.getMobile());

    }

    /**
     * 
     * <功能详细描述> 构造个人地推信息
     * @param registerEntity
     * @return
     * @see [类、类#方法、类#成员]
     */
    private UserGroundInfoBusinEntity bulidPersonalGroundInfo(RegisterEntity registerEntity)
    {
        UserGroundInfoBusinEntity userGroundInfoBusin = new UserGroundInfoBusinEntity();
        userGroundInfoBusin.setMobile(registerEntity.getMobile());
        userGroundInfoBusin.setGroundType(registerEntity.getGroundType());
        userGroundInfoBusin.setSalesmanId(registerEntity.getSalesManId());
        return userGroundInfoBusin;
    }

    /**
     * <功能详细描述>查询个人地推信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public GroundInfoEntity queryPersonalGroundInfo(String salesManId)
    {
        GroundInfoEntity groundInfo = new GroundInfoEntity();
        // 通过地推ID查询个人地推信息
        GroundPersonalInfoBusinEntity groundPersonalInfoBusin = groundFeignClient
                .queryPersonalGroundBySalesManId(salesManId);
        StringBuffer buffer = new StringBuffer(groundPersonalInfoBusin.getSalesmanMobile());
        String encryptMobile = buffer.replace(3, 7, "****").toString();
        groundInfo.setMobile(encryptMobile);
        groundInfo.setSalesManName(groundPersonalInfoBusin.getSalesmanName());
        return groundInfo;

    }

    /**
     * 
     * <功能详细描述>查询商户地推信息
     * @param salesManId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public GroundInfoEntity queryShopGroundInfo(String shopId)
    {
        GroundInfoEntity groundInfo = new GroundInfoEntity();
        GroundShopInfoBusinEntity groundShopInfoBusin = groundFeignClient.queryShopGroundBySalesManId(shopId);
        groundInfo.setShopName(groundShopInfoBusin.getShopName());
        groundInfo.setShopId(groundShopInfoBusin.getShopId().toString());
        groundInfo.setShopAdress(groundShopInfoBusin.getShopAddress());
        return groundInfo;

    }

    /**
     * 
     * <功能详细描述> 是否是地推绑定用户
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean isGroundBindingUser(String mobile)
    {
        UserGroundInfoBusinEntity userGroundInfoBusin = userFeignClient.queryUserGroundInfo(mobile);
        if (NomalUtil.isNullOrEmpty(userGroundInfoBusin))
        {
            return false;
        }
        return true;
    }

    /**
     * 
     * <功能详细描述> 构造登陆地推实体类
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public RegisterEntity buildLoginGroundEntity(Map<String, Object> map)
    {
        RegisterEntity registerEntity = new RegisterEntity();
        String mobile = map.get("mobile").toString();
        // 解析地推Url信息
        if (!"".equals(map.get("inviteUrl").toString()))
        {
            Map<String, String> groundParm = NomalUtil.analysisGroundUrl(map.get("inviteUrl").toString());
            registerEntity.setSalesManId(groundParm.get("salesManId"));
            registerEntity.setGroundType(groundParm.get("groundType"));
            registerEntity.setShopId(groundParm.get("shopId"));
            registerEntity.setShopAssistantId(groundParm.get("shopAssistantId"));
            registerEntity.setInviteFlag(map.get("inviteFlag").toString());
        }
        else
        {
            UserGroundInfoBusinEntity userGroundInfoBusinEntity = userFeignClient.queryUserGroundInfo(mobile);
            registerEntity.setSalesManId(userGroundInfoBusinEntity.getSalesmanId());
            registerEntity.setGroundType(userGroundInfoBusinEntity.getGroundType());
            registerEntity.setShopId(userGroundInfoBusinEntity.getShopId());
            registerEntity.setShopAssistantId(userGroundInfoBusinEntity.getShopAssistantId());
        }
        registerEntity.setCityId(map.get("cityId").toString());
        registerEntity.setSameDeviceFlag(map.get("sameDeviceFlag").toString());
        registerEntity.setMobile(map.get("mobile").toString());
        return registerEntity;

    }

}
