package com.fbw.service.base;
/**
 * 
 * <功能详细描述>
 * MQ基础类
 * @author  JACK HUANG
 * @version  [版本号, 2017年8月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class BaseMq
{
    /**
     * 
     * <功能详细描述>
     * 接受队列报文
     * @param text 报文(默认是JSON报文)
     * @see [类、类#方法、类#成员]
     */
    public abstract void receiveQueue(String text);
    /**
     * 
     * <功能详细描述>
     * 接受订阅报文
     * @param text 报文(默认是JSON报文)
     * @see [类、类#方法、类#成员]
     */
    public abstract void receiveTopic(String text);

}
