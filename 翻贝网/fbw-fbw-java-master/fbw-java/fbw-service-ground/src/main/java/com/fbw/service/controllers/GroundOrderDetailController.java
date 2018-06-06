package com.fbw.service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.ground.GroundOrderDetailBusinEntity;
import com.fbw.service.mapper.GroundOrderDetailBusinEntityMapper;

@RestController
@RequestMapping("/groundOrderDetail")
public class GroundOrderDetailController extends BaseController
{
    @Autowired
    private GroundOrderDetailBusinEntityMapper groundOrderDetailBusinEntityMapper;

    /**
     * 通过用户手机号查询所有订单列表 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectGroundOrder")
    public List<GroundOrderDetailBusinEntity> selectGroundOrder(String mobile, Integer begin, Integer end)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        map.put("begin", begin);
        map.put("end", end);
        return groundOrderDetailBusinEntityMapper.selectGroundOrder(map);

    }

    /**
     * 通过历史地推方案id查询所有订单列表 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectGroundOrderBySalesmanId")
    public List<GroundOrderDetailBusinEntity> selectGroundOrderBySalesmanId(String salesmanId, Integer begin,
            Integer end)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salesmanId", salesmanId);
        map.put("begin", begin);
        map.put("end", end);
        return groundOrderDetailBusinEntityMapper.selectGroundOrderBySalesmanId(map);

    }

    /**
     * 通过历史地推方案id查询所有充值列表 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/selectGroundRechargeBySalesmanId")
    public List<GroundOrderDetailBusinEntity> selectGroundRechargeBySalesmanId(String salesmanId, Integer begin,
            Integer end)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("salesmanId", salesmanId);
        map.put("begin", begin);
        map.put("end", end);
        return groundOrderDetailBusinEntityMapper.selectGroundOrderBySalesmanId(map);

    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        getBaseLogger(GroundOrderDetailController.class, errorMsg);

    }

}
