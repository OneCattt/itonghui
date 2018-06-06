package com.fbw.service.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.base.BaseController;
import com.fbw.service.entity.recharge.RechargeEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.service.RechargeService;

/**
 * 
 * <功能详细描述> 充值controller层
 * @author JACK HUANG
 * @version [版本号, 2017年8月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "/recharge")
public class RechargeController extends BaseController
{

    @Autowired
    RechargeService rechargeService;

    /**
     * 插入充值记录 <功能详细描述>
     * @return
     * @throws InnerException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/insertRechargeRecord", method = RequestMethod.POST)
    public boolean insertRechargeRecord(@RequestBody RechargeEntity rechargeEntity)
    {
        try
        {
            rechargeService.saveRechargeOrderRecord(rechargeEntity);
        }
        catch (InnerException e)
        {
            return false;
        }
        return true;
    }

    /**
     * 通过手机号获取90天内充值列表 <功能详细描述>
     * @param mobile
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getRechargeListByUserId")
    public List<Map<String, Object>> getRechargeListByUserId(String mobile)
    {
        return rechargeService.getRechargeListByUserId(mobile);
    }

    /**
     * 通过订单号查询充值记录 <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getRechargeRecordByOrderNum")
    public RechargeEntity getRechargeRecordByOrderNum(String orderNum)
    {
        try
        {
            return rechargeService.getRechargeOrderRecord(orderNum);
        }
        catch (InnerException e)
        {
            // TODO Auto-generated catch block
            return null;
        }
    }

    @Override
    public void getErrorLog(String errorMsg)
    {
        // TODO Auto-generated method stub

    }

}
