package com.fbw.service.base;

import org.slf4j.LoggerFactory;

/**
 * 
 * <一句话功能简述> 日志基类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface BaseLogger
{
    /**
     * logflag
     */
    boolean DEBUG = true;

    public static boolean isDebugEnabled()
    {
        return DEBUG;
    }

    /**
     * <一句话功能简述> get with the base logger object <功能详细描述>
     * @param clazz
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public default void getBaseLogger(Class<?> clazz, String errorMsg)
    {
        if (isDebugEnabled())
        {
            LoggerFactory.getLogger(clazz).error(errorMsg);

        }

    }
}
