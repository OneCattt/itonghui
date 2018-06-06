package com.fbw.service.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 获取缓存 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class GetCacheUtil
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 
     * <功能详细描述> 设置List类型redis缓存值
     * @param key
     * @param value
     * @see [类、类#方法、类#成员]
     */
    public void setListRedisVal(String key, List<String> values)
    {
        stringRedisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 
     * <功能详细描述> 设置String类型redis缓存值
     * @param key
     * @param values
     * @see [类、类#方法、类#成员]
     */
    public void setStringRedisVal(String key, String values)
    {
        stringRedisTemplate.opsForValue().set(key, values);
    }

    /**
     * 
     * <功能详细描述> 获取String类型redis缓存值
     * @param key
     * @see [类、类#方法、类#成员]
     */
    public String getStringRedisVal(String key)
    {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置String类型时间控制的redis缓存值 <功能详细描述>
     * @param key
     * @param value
     * @param timeout
     * @see [类、类#方法、类#成员]
     */
    public void setTimeStringRedisVal(String key, String value, long timeout, TimeUnit unit)
    {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 
     * <功能详细描述> 获取redis失效时间
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getRedisTimeOut(String key)
    {
        return stringRedisTemplate.getExpire(key);
    }

}
