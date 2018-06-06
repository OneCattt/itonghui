package com.fbw.serivce.tools;

import java.util.List;

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
public class CacheUtils
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
     * 
     * <功能详细描述>获取List类型的redis值
     * @param key
     * @param values
     * @see [类、类#方法、类#成员]
     */
    public List<String> getListRedisVal(String key)
    {
        return stringRedisTemplate.opsForList().range(key, 0, -1);
    }

}
