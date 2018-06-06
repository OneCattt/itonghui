package com.fbw.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbw.service.entity.ground.GroundShopInfoBusinEntity;
import com.fbw.service.entity.portal.HomeReqEntity;
import com.fbw.service.entity.shop.ShopInfoEntity;
import com.fbw.service.entity.user.UserInfoBusinEntity;
import com.fbw.service.feign.ShopFeignClient;
import com.fbw.service.feign.UserFeignClient;
import com.fbw.service.mapper.GroundShopInfoBusinEntityMapper;

@Service
public class GroundShopInfoService
{
    @Autowired
    private GroundShopInfoBusinEntityMapper groundShopInfoBusinEntityMapper;

    @Autowired
    private ShopFeignClient shopFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 存储商户地推信息 <功能详细描述>
     * @param shopCode
     * @param cityId
     * @param shopGroundType
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int saveGroundShopInfo(String shopCode, String cityId, String shopGroundType)
    {
        // 查询是否该商家已是地推商家
        int flag = groundShopInfoBusinEntityMapper.selectIsExistShopCode(shopCode);
        if (flag == 0)
        {
            return -3;
        }
        // 根据商家代码查询用户详细信息
        HomeReqEntity homeReqEntity = new HomeReqEntity();
        homeReqEntity.setCode(shopCode);
        ShopInfoEntity shopsInfoEntity = shopFeignClient.getShopsInfoByShopId(homeReqEntity);
        if (null == shopsInfoEntity)
        {
            return -2;
        }
        GroundShopInfoBusinEntity groundShopInfoBusinEntity = new GroundShopInfoBusinEntity();
        groundShopInfoBusinEntity.setCityId(cityId);
        groundShopInfoBusinEntity.setShopGroundType(shopGroundType);
        groundShopInfoBusinEntity.setShopCode(shopCode);
        groundShopInfoBusinEntity.setShopId(String.valueOf(shopsInfoEntity.getShopId()));
        groundShopInfoBusinEntity.setShopAddress(shopsInfoEntity.getAddress());
        groundShopInfoBusinEntity.setShopSalesmanId(String.valueOf(shopsInfoEntity.getMaintainerId()));
        // 根据商务人员id查询用户表
        UserInfoBusinEntity userInfoBusinEntity = userFeignClient
                .getUserBaseInfoById(Integer.parseInt(groundShopInfoBusinEntity.getShopSalesmanId()));
        if (null == userInfoBusinEntity)
        {
            return -1;
        }
        groundShopInfoBusinEntity.setShopSalesmanName(userInfoBusinEntity.getRealName());
        int flag1 = groundShopInfoBusinEntityMapper.saveGroundShopInfo(groundShopInfoBusinEntity);
        if (flag1 == 1)
        {
            return 1;
        }
        return 0;

    }

}
