package com.fbw.service.task;
/**
 * 
 * <一句话功能简述>
 * 翻贝日历字典任务
 * <功能详细描述>
 * 
 * @author  JACK HUANG
 * @version  [版本号, 2017年8月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fbw.service.mapper.SmsMobileConfMapper;
import com.fbw.service.mq.SmsProductMq;
import com.fbw.service.service.TaskService;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

@Service
public class FbwCalendarTask
{

    public static final String FBW_CALENDAR_TASK_SMS_MOBILES = "FBW:CALENDAR:TASK:SMS:MOBILES";

    public static final int FBW_CALENDAR_TASK_SMS_TYPE = 0;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SmsMobileConfMapper smsMobileConfMapper;

    @Autowired
    private SmsProductMq smsProductMq;

    @Autowired
    private GetCacheUtil getCacheUtil;

    /**
     * 凌晨1:05执行 <功能详细描述>每天凌晨更新商家新店状态
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 5 1 * * ?")
    public String updateShopsIsNew()
    {
        System.out.println("task updateNewShops" + new Date());
        String result = null;
        try
        {
            result = taskService.updateShopsIsNew();
        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "updateShopsIsNew");
        }
        return result;
    }

    /**
     * 凌晨1:20执行 <功能详细描述>每天凌晨更新商家翻倍日状态
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 15 1 * * ?")
    public String updateShopsIsEvent()
    {
        System.out.println("task updateEventShops" + new Date());
        String result = null;
        try
        {
            result = taskService.updateShopsIsEvent();
        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "updateShopsIsEvent");
        }
        return result;
    }

    /**
     * 
     * <功能详细描述>每天更新商家收藏量
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 25 1 * * ?")
    public String updateShopsCollect()
    {
        System.out.println("task updateShopsCollect" + new Date());
        String result = null;
        try
        {
            result = taskService.updateShopsCollect();
        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "updateShopsCollect");
        }
        return result;
    }

    /**
     * 
     * <功能详细描述>每天更新商家浏览量
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 35 1 * * ?")
    public String updateShopsBrowse()
    {
        // TODO 假数据
        System.out.println("task updateShopsBrowse" + new Date());
        String result = null;
        try
        {
            result = taskService.updateShopsBrowse();
        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "updateShopsBrowse");
        }
        return result;
    }

    /**
     * 
     * <功能详细描述>每天更新探店浏览量
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 35 1 * * ?")
    public String updateInterviewBrowse()
    {
        // TODO 假数据
        System.out.println("task updateInterviewBrowse" + new Date());
        String result = null;
        try
        {
            result = taskService.updateInterviewBrowse();
        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "updateInterviewBrowse");
        }
        return result;
    }

    /**
     * 
     * <功能详细描述>每天更新订单数量
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 45 1 * * ?")
    public String updateShopsOrderNum()
    {
        System.out.println("task updateShopsOrderNum" + new Date());
        String result = null;
        try
        {
            result = taskService.updateShopsOrderNum();
        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "updateShopsOrderNum");
        }
        return result;
    }

    /**
     * 
     * <功能详细描述>每天更新评价信息（评价数量、评价平均分、评价总分）
     * @see [类、类#方法、类#成员]
     */
    @Scheduled(cron = "0 55 1 * * ?")
    public String updateShopsComment()
    {
        System.out.println("task updateShopsComment" + new Date());
        String result = null;
        try
        {
            result = taskService.updateShopsComment();
        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "updateShopsComment");
        }
        return result;
    }

    private void smsMq(String result, String methodName)
    {
        if ("SUCCESS".equals(result))
        {
            return;
        }
        List<String> smsMobiles = getCacheUtil.getListRedisVal(FBW_CALENDAR_TASK_SMS_MOBILES);
        if (NomalUtil.isNullOrEmpty(smsMobiles))
        {
            smsMobiles = smsMobileConfMapper.getSmsMobilesByType(FBW_CALENDAR_TASK_SMS_TYPE);
            getCacheUtil.setListRedisVal(FBW_CALENDAR_TASK_SMS_MOBILES, smsMobiles);
        }
        if (NomalUtil.isNullOrEmpty(smsMobiles))
        {
            System.out.println("接受短信号码查询失败！");
            ;
        }
        for (String mobile : smsMobiles)
        {
            smsProductMq.smsSend(mobile, methodName);
        }
    }

    /**
     * 
     * <功能详细描述>刷新接收短信号码
     * @see [类、类#方法、类#成员]
     */
    public void refactorSmsMoblies()
    {
        List<String> smsMobiles = smsMobileConfMapper.getSmsMobilesByType(FBW_CALENDAR_TASK_SMS_TYPE);
        getCacheUtil.setListRedisVal(FBW_CALENDAR_TASK_SMS_MOBILES, smsMobiles);
    }

}
