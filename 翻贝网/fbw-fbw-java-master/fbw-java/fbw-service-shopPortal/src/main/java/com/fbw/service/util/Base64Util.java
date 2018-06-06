package com.fbw.service.util;

import java.io.UnsupportedEncodingException;

import Decoder.BASE64Decoder;

/**
 * 
 * <功能详细描述> Base64加密
 * @author JACK HUANG
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Base64Util
{
    // 加密
    @SuppressWarnings("restriction")
    public static String getBase64(String str)
    {
        byte[] b = null;
        String s = null;
        try
        {
            b = str.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        if (b != null)
        {
            s = new sun.misc.BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s)
    {
        byte[] b = null;
        String result = null;
        if (s != null)
        {
            BASE64Decoder decoder = new BASE64Decoder();
            try
            {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

}
