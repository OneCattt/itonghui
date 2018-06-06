package com.fbw.service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.ground.GroundRegisterUserDetailBusinEntity;
import com.fbw.service.mapper.GroundRegisterUserDetailBusinEntityMapper;

@RestController
@RequestMapping("/groundRegisterUserDetail")
public class GroundRegisterUserDetailController extends BaseController
{
    @Autowired
    private GroundRegisterUserDetailBusinEntityMapper groundRegisterUserDetailBusinEntityMapper;

    /**
     * 查询是否存在手机号 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/selectIsExistMobile", method = RequestMethod.GET)
    public boolean selectIsExistMobile(String mobile)
    {
        int flag = groundRegisterUserDetailBusinEntityMapper.selectIsExistMobile(mobile);
        if (flag == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * 根据地推方案id查询出所属所有地推用户 <功能详细描述>
     * @param historyGroundId
     * @param begin
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectAllByGroundId")
    public List<GroundRegisterUserDetailBusinEntity> selectAllByGroundId(String historyGroundId, Integer begin,
            Integer end)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("historyGroundId", historyGroundId);
        map.put("begin", begin);
        map.put("end", end);

        return groundRegisterUserDetailBusinEntityMapper.selectAllByGroundId(map);

    }

    /**
     * 查询该商家每一天注册用户、有效注册用户、订单数、地推订单数、地推订单实付数 <功能详细描述>
     * @param shopId
     * @param ShopAssistantId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectAllByShopIdAndShopAssistantId")
    public List<Map<String, Object>> selectAllByShopIdAndShopAssistantId(String shopId, String cityId)
    {
        return groundRegisterUserDetailBusinEntityMapper.selectAllByShopIdAndShopAssistantId(shopId, cityId);
    }

    /**
     * 查询该商家当前日期所有注册用户 <功能详细描述>
     * @param shopId
     * @param cityId
     * @param Date
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectUserByShopIdAndDate")
    public List<GroundRegisterUserDetailBusinEntity> selectUserByShopIdAndDate(String shopId, String cityId,
            String date, String mobile)
    {
        return groundRegisterUserDetailBusinEntityMapper.selectUserByShopIdAndDate(shopId, cityId, date, mobile);
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
