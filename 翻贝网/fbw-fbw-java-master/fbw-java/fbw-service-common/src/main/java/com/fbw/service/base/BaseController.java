package com.fbw.service.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <一句话功能简述> 控制层基类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseController implements BaseLogger
{
    /**
     * <一句话功能简述> 失败返回信息 <功能详细描述>
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> failedMessage(String message)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "failed");
        map.put("message", message);
        return map;
    }

    /**
     * <一句话功能简述> 成功返回信息 <功能详细描述>
     * @param message
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> successMessage(String message)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("message", message);
        return map;
    }

    /**
     * <一句话功能简述> 成功返回数据 <功能详细描述>
     * @param data
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> successData(Map<String, Object> data)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("data", data);
        return map;
    }
    /**
     * <一句话功能简述>
     * define error level log
     * <功能详细描述>
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public abstract void getErrorLog(String errorMsg);
}
