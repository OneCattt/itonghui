package com.fbw.service.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.fbw.service.contents.ErrorMsgConstant;
import com.fbw.service.exception.InnerCode;
import com.fbw.service.exception.InnerException;

import Decoder.BASE64Encoder;

/**
 * 
 * <功能详细描述> MD5工具类
 * @author JACK HUANG
 * @version [版本号, 2017年8月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MD5Util
{

    /**
     * 
     * <功能详细描述> 通过MD5的方式加密
     * @param str
     * @return
     * @throws InnerException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @see [类、类#方法、类#成员]
     */
    public static String EncoderByMd5(String str) throws InnerException
    {
        // 确定计算方法
        String newstr = "";
        MessageDigest md5;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            throw new InnerException(ErrorMsgConstant.PORTAL_UPDATE_RED_PACKET_STATUS_FAIL,
                    InnerCode.geterrorMsg(ErrorMsgConstant.PORTAL_UPDATE_RED_PACKET_STATUS_FAIL));
        }

        return newstr;
    }

}
