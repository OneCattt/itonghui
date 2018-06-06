package com.fbw.service.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fbw.service.task.FbwCalendarTask;
import com.fbw.service.task.UserScheduled;

/**
 * 
 * <功能详细描述>手动执行定时任务
 * @author FBW0115
 * @version [版本号, 2017年10月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
public class ManualTaskController
{

    @Autowired
    private FbwCalendarTask fbwCalendarTask;

    @Autowired
    private UserScheduled userScheduled;

    /**
     * 
     * <功能详细描述>刷新接收短信号码
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/refactorSmsMoblies", method = RequestMethod.GET)
    public String refactorSmsMoblies()
    {
        fbwCalendarTask.refactorSmsMoblies();
        return "SUCCESS";
    }

    /**
     * 凌晨1:05执行 <功能详细描述>每天凌晨更新商家新店状态
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/updateShopsIsNew", method = RequestMethod.GET)
    public String updateShopsIsNew()
    {
        System.out.println("task updateNewShops" + new Date());
        return fbwCalendarTask.updateShopsIsNew();
    }

    /**
     * 凌晨1:20执行 <功能详细描述>每天凌晨更新商家翻倍日状态
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/updateShopsIsEvent", method = RequestMethod.GET)
    public String updateShopsIsEvent()
    {
        System.out.println("task updateEventShops" + new Date());
        return fbwCalendarTask.updateShopsIsEvent();
    }

    /**
     * 
     * <功能详细描述>每天更新商家收藏量
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/updateShopsCollect", method = RequestMethod.GET)
    public String updateShopsCollect()
    {
        System.out.println("task updateShopsCollect" + new Date());
        return fbwCalendarTask.updateShopsCollect();
    }

    /**
     * 
     * <功能详细描述>每天更新商家浏览量
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/updateShopsBrowse", method = RequestMethod.GET)
    public String updateShopsBrowse()
    {
        System.out.println("task updateShopsBrowse" + new Date());
        return fbwCalendarTask.updateShopsBrowse();
    }

    /**
     * 
     * <功能详细描述>每天更新订单数量
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/updateShopsOrderNum", method = RequestMethod.GET)
    public String updateShopsOrderNum()
    {
        return fbwCalendarTask.updateShopsOrderNum();
    }

    /**
     * 
     * <功能详细描述>每天更新评价信息（评价数量、评价平均分、评价总分）
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/updateShopsComment", method = RequestMethod.GET)
    public String updateShopsComment()
    {
        return fbwCalendarTask.updateShopsComment();
    }

    /**
     * 
     * <功能详细描述>7天自动评价
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/sevenDayAutoComment", method = RequestMethod.GET)
    public String sevenDayAutoComment()
    {
        return userScheduled.sevenDayAutoComment();
    }

    /**
     * 
     * <功能详细描述>红包自动过期
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/v3/manualTask/autoRedPacketOutDate", method = RequestMethod.GET)
    public String autoRedPacketOutDate()
    {
        return userScheduled.autoRedPacketOutDate();
    }

}
