package com.fbw.service.base;

/**
 * 
 * <功能详细描述> dao层基类
 * @author JACK HUANG
 * @version [版本号, 2017年9月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseDao implements BaseLogger
{
    /**
     * <一句话功能简述> define error level log <功能详细描述>
     * @param errorMsg
     * @see [类、类#方法、类#成员]
     */
    public abstract void getErrorLog(String errorMsg);
}
