package com.fbw.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fbw.service.entity.DictEntity;
import com.fbw.service.enums.CacheKeyEnums;
import com.fbw.service.mapper.DictMapper;

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

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> vOps;

    @Autowired
    private DictMapper dictMapper;

    /**
     * <功能详细描述>获取字典号码值
     * @param dictKey 字典key值
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<String> getDictNumList(String dictKey)
    {
        List<String> dictList = new ArrayList<>();
        // 获取缓存的值
        dictList = stringRedisTemplate.opsForList().range(CacheKeyEnums.getMsg(dictKey), 0, -1);
        if (dictList.isEmpty())
        { // 查询数据库值
            dictList = dictMapper.getDictValue(new DictEntity(dictKey, "0"));
            // 如果没值就保存到缓存中
            setListRedisVal(CacheKeyEnums.getMsg(dictKey), dictList);
        }
        return dictList;

    }

    /**
     * <功能详细描述>获取字典号码值
     * @param dictKey 字典key值
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getDictVal(String dictKey)
    {
        String dictVal = "";
        // 获取缓存的值
        dictVal = getStringRedisVal(CacheKeyEnums.getMsg(dictKey));
        if (NomalUtil.isNullOrEmpty(dictVal))
        { // 查询数据库值
            dictVal = dictMapper.getDictValue(new DictEntity(dictKey, "0")).get(0);
            // 如果没值就保存到缓存中
            setStringRedisVal(CacheKeyEnums.getMsg(dictKey), dictVal);
        }
        return dictVal;

    }

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
     * <功能详细描述>获取List类型的redis值
     * @param key
     * @param values
     * @see [类、类#方法、类#成员]
     */
    public List<String> getListRedisVal(String key)
    {
        return stringRedisTemplate.opsForList().range(key, 0, -1);
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
