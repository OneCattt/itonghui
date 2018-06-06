package com.fbw.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fbw.service.entity.user.UserOrderInfoBusinEntity;
import com.fbw.service.exception.InnerException;
import com.fbw.service.mapper.SmsMobileConfMapper;
import com.fbw.service.mapper.UserMapper;
import com.fbw.service.mq.SmsProductMq;
import com.fbw.service.service.TaskService;
import com.fbw.service.util.GetCacheUtil;
import com.fbw.service.util.NomalUtil;

/**
 * 订单7天后自动评价 <功能详细描述>
 * @author jiangruliang
 * @version [版本号, 2017年9月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class UserScheduled
{
    public static final String FBW_CALENDAR_TASK_SMS_MOBILES = "FBW:CALENDAR:TASK:SMS:MOBILES";

    public static final int FBW_CALENDAR_TASK_SMS_TYPE = 0;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SmsMobileConfMapper smsMobileConfMapper;

    @Autowired
    private SmsProductMq smsProductMq;

    @Autowired
    private GetCacheUtil getCacheUtil;

    @Scheduled(cron = "0 5 0 * * ?")
    public String sevenDayAutoComment()
    {
        String result = "SUCCESS";
        try
        {
            List<UserOrderInfoBusinEntity> userOrderInfos = userMapper.selectSevenDayNocommentOrder();
            for (UserOrderInfoBusinEntity userOrderInfo : userOrderInfos)
            {
                taskService.autoInsertUserComment(userOrderInfo.getOrderNumber(), userOrderInfo.getUserId());
            }
        }
        catch (InnerException e)
        {
            result = e.getMessage();
        }
        finally
        {
            smsMq(result, "sevenDayAutoComment");
        }
        return result;

    }

    @Scheduled(cron = "0 10 0 * * ?")
    public String autoRedPacketOutDate()
    {
        String result = "SUCCESS";
        try
        {
            userMapper.autoRedPacketOutDate();

        }
        catch (Exception e)
        {
            result = "FAILED";
        }
        finally
        {
            smsMq(result, "autoRedPacketOutDate");
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
