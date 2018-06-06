package com.fbw.service.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <功能详细描述> 支付宝核心工具类
 * @author JACK HUANG
 * @version [版本号, 2017年8月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AlipayCore
{

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray)
    {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0)
        {
            return result;
        }

        for (String key : sArray.keySet())
        {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type"))
            {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     * @throws UnsupportedEncodingException
     */
    public static String createLinkString(Map<String, String> params) throws UnsupportedEncodingException
    {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);
            String value = URLEncoder.encode(params.get(key), "UTF-8");

            if (i == keys.size() - 1)
            {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            }
            else
            {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

}
