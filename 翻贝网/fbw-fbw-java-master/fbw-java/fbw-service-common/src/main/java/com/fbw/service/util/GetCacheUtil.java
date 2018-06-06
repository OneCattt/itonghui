package com.fbw.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.fbw.service.entity.DictEntity;
import com.fbw.service.enums.CommonEnums;
import com.fbw.service.mapper.DictMapper;

/**
 * 
 * <一句话功能简述> 获取缓存 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GetCacheUtil
{
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private static DictMapper dictMapper;

    /**
     * <一句话功能简述> 获取字典值 <功能详细描述>
     * @param dictKey 字典key值
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String  getDictval(String dictKey)
    {
        String dictVal = "";
        // 获取缓存的值
        dictVal = stringRedisTemplate.opsForValue().get(dictKey);
        if (NomalUtil.isNull(dictVal))
        {
            // 如果没值就保存到缓存中
            stringRedisTemplate.opsForValue().set(CommonEnums.getMsg(dictKey),
                    dictMapper.getDictValue(new DictEntity(dictKey, "0")));
        }
        return dictKey;

    }

}
