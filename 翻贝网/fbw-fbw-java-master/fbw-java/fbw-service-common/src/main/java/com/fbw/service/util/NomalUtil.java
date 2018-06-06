package com.fbw.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 
 * <一句话功能简述> 常用工具类 <功能详细描述>
 * 
 * @author JACK HUANG
 * @version [版本号, 2017年7月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NomalUtil
{

    /**
     * <一句话功能简述> 判断字符串是否为空 <功能详细描述>
     * @param val
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isNull(String val)
    {
        if (null == val || val.length() <= 0 || "" == val)
        {
            return true;
        }
        ;
        return false;

    }

    /**
     * <一句话功能简述> 效验手机号格式 <功能详细描述>
     * @param mobilePhone
     * @return ture 为手机号， 反之不是
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMobileNo(String mobilePhone)
    {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobilePhone);
        return m.matches();

    }

    /**
     * MD5加密
     * @param decript
     * @return
     */
    public static String MD5(String decript)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
            {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2)
                {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
    }

    /**
     * 
     * <功能详细描述> 发送语音信息
     * @param url
     * @param voiceinfo
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String sendVoiceMsg(String url, String voiceinfo) throws Exception
    {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        PostMethod method = new PostMethod();
        try
        {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "/voice", true));
            method.setRequestBody(new NameValuePair[]
            { new NameValuePair("method", "vcfplay"), new NameValuePair("voiceinfo", voiceinfo) });
            HttpMethodParams params = new HttpMethodParams();
            params.setContentCharset("GBK");
            method.setParams(params);
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK)
            {
                byte[] ba = method.getResponseBody();
                method.getResponseBodyAsStream();
                String str = new String(ba, "GBK");

                return str;
            }
            else
            {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        }
        finally
        {
            method.releaseConnection();
        }
    }

}
