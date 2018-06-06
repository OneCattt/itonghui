package com.fbw.service.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * <功能详细描述>json工具类
 * @author FBW0115
 * @version [版本号, 2017年8月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JsonUtil
{

    /**
     * 
     * <功能详细描述>将json转map
     * @param originalStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> convertMapByJson(String originalStr)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> productMap = mapper.readValue(originalStr, Map.class);// 转成map
            return productMap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * <功能详细描述>将Map转成json
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String convertMapToJson(Map<String, Object> map)
    {
        return JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
    }

}
