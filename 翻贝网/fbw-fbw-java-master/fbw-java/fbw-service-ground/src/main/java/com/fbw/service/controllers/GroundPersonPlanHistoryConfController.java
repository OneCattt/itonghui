package com.fbw.service.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.ground.GroundPersonPlanHistoryConfEntity;
import com.fbw.service.entity.ground.GroundPersonalInfoBusinEntity;
import com.fbw.service.entity.ground.GroundPlanConfEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.GroundPersonPlanHistoryConfEntityMapper;
import com.fbw.service.service.GrounPlanConfService;
import com.fbw.service.service.GroundPersonPlanHistoryConfService;
import com.fbw.service.service.GroundPersonalInfoService;

@RestController
@RequestMapping("/groundPersonPlanHistoryConf")
public class GroundPersonPlanHistoryConfController extends BaseController
{
    @Autowired
    private GroundPersonPlanHistoryConfService groundPersonPlanHistoryConfService;

    @Autowired
    private GroundPersonPlanHistoryConfEntityMapper groundPersonPlanHistoryConfEntityMapper;

    @Autowired
    private GroundPersonalInfoService groundPersonalInfoService;

    @Autowired
    private GrounPlanConfService grounPlanConfService;

    /**
     * 通过地推人员id查询所有地推方案列表 <功能详细描述>
     * @param salesmanId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectBySalesmanId")
    public Map<String, Object> selectBySalesmanId(String salesmanId)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        // 通过地推人员id查询所有地推方案列表
        List<GroundPersonPlanHistoryConfEntity> groundPersonPlanHistoryConfEntities = groundPersonPlanHistoryConfService
                .selectBySalesmanId(salesmanId);
        GroundPersonalInfoBusinEntity groundPersonalInfoBusinEntity = groundPersonalInfoService
                .queryPersonalGroundBySalesManId(salesmanId);
        for (Iterator<GroundPersonPlanHistoryConfEntity> iter = groundPersonPlanHistoryConfEntities.iterator(); iter
                .hasNext();)
        {
            GroundPersonPlanHistoryConfEntity groundPersonPlanHistoryConfEntity = iter.next();
            // 通过salesmanId和地推方案id 查询充值数，订单数，订单实付，注册用户数，收入等
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("salesmanId", groundPersonPlanHistoryConfEntity.getSalesmanId());
            map.put("historyGroundId", groundPersonPlanHistoryConfEntity.getId());
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("groundHistoryPlan", groundPersonPlanHistoryConfEntity);
            map1.put("groundHistoryPlanResult", groundPersonPlanHistoryConfEntityMapper.selectAllByGroundId(map));
            list.add(map1);
        }
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("list", list);
        map2.put("groundPersonalInfo", groundPersonalInfoBusinEntity);
        return map2;

    }

    /**
     * 点击变更地推方案 <功能详细描述>
     * @param salesmanId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/queryGroundPersonPlanHistoryBySalesId")
    public Map<String, Object> queryGroundPersonPlanHistoryBySalesId(String salesmanId, String cityId)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        GroundPersonPlanHistoryConfEntity groundPersonPlanHistory = groundPersonPlanHistoryConfService
                .queryGroundPersonPlanHistoryBySalesId(salesmanId);
        List<GroundPlanConfEntity> groundPlanConfs = grounPlanConfService.selectByCityId(cityId);
        map.put("groundPersonPlanHistory", groundPersonPlanHistory);
        map.put("groundPlanConfs", groundPlanConfs);
        return map;
    }

    /**
     * 更换地推方案 <功能详细描述>
     * @param groundId
     * @param newGroundId
     * @return -1:地推方案与之间，0:更换失败，1:更换成功
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/changeSalesmanGroundPlan", method = RequestMethod.POST)
    public int changeSalesmanGroundPlan(String groundId, String newGroundId, String salesmanId) throws InnerException
    {
        if (groundId.equals(newGroundId))
        {
            return -1;
        }
        boolean flag = groundPersonPlanHistoryConfService.changeSalesmanGroundPlan(salesmanId, newGroundId);
        if (flag == true)
        {
            return 1;
        }
        return 0;

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
